package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdDeliveryMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryBatchSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.dto.McParamsWithOtherTypesDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

public class OrdDeliveryMainSVImpl implements IOrdDeliveryMainSV {
    
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Resource
    private IOrdDeliveryBatchSV ordDeliveryBatchSV;
    
    @Resource
    private IBaseExpressRSV baseExpressRSV;
    
    @Resource
    private IMcSyncSendRSV mcSyncSendRSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    private static final String MODULE = OrdDeliveryMainSVImpl.class.getName();

    @Override
    public void orderDelivery(final RConfirmDeliveRequest rConfirmDeliveRequest) {
        
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rConfirmDeliveRequest));
        txMsg.setName("business-topic-dely");
        
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    Long batchId = ordDeliveryBatchSV.saveConfirmDeliveSV(rConfirmDeliveRequest);
                    rConfirmDeliveRequest.setBatchId(batchId);
                    txMsg.setContent( JSON.toJSONString(rConfirmDeliveRequest));
                    //发送短信
                    try{
                        sendMSG(rConfirmDeliveRequest);
                    } catch (Exception e){
                        LogUtil.error(MODULE, "===发送短信异常===",e);
                    }
                    
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常===",be);
                    status.setRollbackOnly();
                    throw be;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LogUtil.error(MODULE, "===系统异常===",e);
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310021);
                }
                return null;
            }});
    }

    public void sendMSG(RConfirmDeliveRequest rConfirmDeliveRequest){
        //发货单号为空,不发送短信
        if(StringUtil.isBlank(rConfirmDeliveRequest.getExpressNo())){
            return;
        }
        OrdMain ordMain =  this.ordMainSV.queryOrderByOrderId(rConfirmDeliveRequest.getOrderId());
        if(ordMain == null){
            LogUtil.error(MODULE, "===发送短信异常==="+rConfirmDeliveRequest.getOrderId()+"查询出的订单信息为空");
            return;
        }
        LogUtil.debug(MODULE, "处理发货短信");
        
        Map<String,String> notice = new java.util.HashMap<>();
        int orderId = rConfirmDeliveRequest.getOrderId().length();
        notice.put("orderId", rConfirmDeliveRequest.getOrderId().substring(orderId-4, orderId));
        notice.put("expressNo", rConfirmDeliveRequest.getExpressNo());
        
        if(rConfirmDeliveRequest.getExpressId() != null){
            BaseExpressReqDTO baseExpressReqDTO = new BaseExpressReqDTO();
            baseExpressReqDTO.setId(rConfirmDeliveRequest.getExpressId());
            BaseExpressRespDTO baseExpressRespDTO = this.baseExpressRSV.fetchExpressInfo(baseExpressReqDTO);
            notice.put("expressName",baseExpressRespDTO.getExpressName());
        } else {
            notice.put("expressName","邮局挂号");
        }
        
//        McParamsDTO mcParamsDTO = new McParamsDTO();
//        mcParamsDTO.setFromUserId(rConfirmDeliveRequest.getStaff().getId());
//        mcParamsDTO.setToUserId(rConfirmDeliveRequest.getStaffId());
//        mcParamsDTO.setMsgCode("ord.send.notice");
//        
//        mcParamsDTO.setMsgParams(notice);
//        LogUtil.debug(MODULE, "处理发货短信"+JSON.toJSONString(mcParamsDTO));
//        this.mcSyncSendRSV.sendMsg(mcParamsDTO);
        
        //发短信给收货人
        McParamsWithOtherTypesDTO mcParamsWithOtherTypesDTO = new McParamsWithOtherTypesDTO();
        mcParamsWithOtherTypesDTO.setFromUserId(rConfirmDeliveRequest.getStaff().getId());
        mcParamsWithOtherTypesDTO.setMsgCode("ord.send.notice");
        mcParamsWithOtherTypesDTO.setToUserId(rConfirmDeliveRequest.getStaffId());
        mcParamsWithOtherTypesDTO.phoneNo(ordMain.getContactPhone());
        mcParamsWithOtherTypesDTO.setMsgParams(notice);
        LogUtil.debug(MODULE, "处理发货短信"+JSON.toJSONString(mcParamsWithOtherTypesDTO));
        this.mcSyncSendRSV.sendMsgBySpecial(mcParamsWithOtherTypesDTO);
    }
}

