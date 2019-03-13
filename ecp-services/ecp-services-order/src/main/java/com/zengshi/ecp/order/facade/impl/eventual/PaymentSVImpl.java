package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPayScoreSV;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPaymentSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
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

public class PaymentSVImpl implements IPaymentSV {
    
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Autowired(required = false)
    private IPayWaySV payWaySV;
    
    @Resource
    private IPayScoreSV payScoreSV;
    
    @Resource
    private IMcSyncSendRSV mcSyncSendRSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    private static final String MODULE = PaymentSVImpl.class.getName();

    @Override
    public void savePaySucc(final PaySuccInfo paySuccInfo) {
        
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(paySuccInfo));
        txMsg.setName("business-topic-pay");
        
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    payWaySV.savehandlePaySucc(paySuccInfo);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sendMSG(paySuccInfo);
                            } catch (Exception e) {
                                LogUtil.error(MODULE, "===发送短信异常===",e);
                            }
                        }
                    });

                } catch (BusinessException be) {
                    LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
                    be.printStackTrace();
                    status.setRollbackOnly();
                    throw new BusinessException(be.getErrorCode());
                } catch (Exception e) {
                    LogUtil.error(MODULE, "支付回调失败", e);
                    status.setRollbackOnly();
                    throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310007);
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

