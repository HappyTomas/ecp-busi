package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdZreoSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWaySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Map;

public class OrdZreoSVImpl implements IOrdZreoSV {
    
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Resource
    private IOrdOfflineChkSV ordOfflineChkSV;
    
    @Autowired(required = false)
    private IPayWaySV  payWaySV;
    
    @Resource
    private IMcSyncSendRSV mcSyncSendRSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    private static final String MODULE = OrdZreoSVImpl.class.getName();

    @Override
    public void payZeroOrder(final ROfflineReviewRequest rOfflineReviewRequest) {
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rOfflineReviewRequest));
        txMsg.setName("business-topic-pay");
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    LogUtil.info(MODULE, "1111"+JSON.toJSONString(rOfflineReviewRequest).toString());
                    PaySuccInfo  psi = new PaySuccInfo();
                    psi.setOrderId(rOfflineReviewRequest.getOrderId());
                    psi.setPayType(PayStatus.PAY_TYPE_02); 
                    psi.setPayWay(PayStatus.PAY_WAY_9998);
                    psi.setPayTransNo(rOfflineReviewRequest.getOrderId());
//                    psi.setStaffId(OrdConstants.Common.DEFAULT_STAFFID);
                    psi.setStaffId(rOfflineReviewRequest.getStaff().getId());
                    payWaySV.savehandlePaySucc(psi);
                    
                    try {
                        sendMSG(psi);
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "===发送短信异常===",e);
                    }
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常==="+be);
                    status.setRollbackOnly();
                    throw be;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LogUtil.error(MODULE, "===系统异常==="+e);
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320004);
                }
                return null;
            }});
    }
    public void sendMSG(PaySuccInfo paySuccInfo){
        OrdMain ordMain =  this.ordMainSV.queryOrderByOrderId(paySuccInfo.getOrderId());
        //不是积分订单的不发短信
        if(ordMain == null  || ordMain.getSiteId() != 2){
            return;
        }
        //没有使用积分不发短信
        if(ordMain.getOrderScore() <= 0){
            return;
        }
        LogUtil.debug(MODULE, "处理积分短信");
        McParamsDTO mcParamsDTO = new McParamsDTO();
        mcParamsDTO.setFromUserId(OrdConstants.Common.DEFAULT_STAFFID);
        mcParamsDTO.setToUserId(ordMain.getStaffId());
        mcParamsDTO.setMsgCode("ord.point.notice");
        Map<String,String> notice = new java.util.HashMap<>();
        notice.put("orderId", paySuccInfo.getOrderId());
        notice.put("usedPoint",ordMain.getOrderScore().toString());
        Long score = 0l;
        ScoreInfoResDTO scoreInfoResDTO = this.scoreInfoRSV.findScoreInfoByStaffId(ordMain.getStaffId());
        if(scoreInfoResDTO != null){
            score = scoreInfoResDTO.getScoreBalance();
        }
        notice.put("remPoint", score.toString());
        mcParamsDTO.setMsgParams(notice);
        LogUtil.debug(MODULE, "处理积分短信"+JSON.toJSONString(mcParamsDTO));
        this.mcSyncSendRSV.sendMsg(mcParamsDTO);
     }
}

