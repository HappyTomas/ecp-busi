package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.pay.PayResultDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdOfflinePaySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.ImOrdBelongService;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayResultSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWaySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class OrdOfflinePaySVImpl implements IOrdOfflinePaySV {
    
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Resource
    private IOrdOfflineChkSV ordOfflineChkSV;
    
    @Autowired(required = false)
    private IPayWaySV  payWaySV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    protected IPayResultSV payResultSV;
       
    @Autowired(required = false)
    private ImOrdBelongService imOrdBelongService;
    
    
    private static final String MODULE = OrdOfflinePaySVImpl.class.getName();

    @Override
    public void saveOfflineReviewPass(final ROfflineReviewRequest rOfflineReviewRequest) {
        
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rOfflineReviewRequest));
        txMsg.setName("business-topic-pay");
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    LogUtil.info(MODULE, "1111"+JSON.toJSONString(rOfflineReviewRequest).toString());
                    ordOfflineChkSV.saveOfflineReview(rOfflineReviewRequest);
                    LogUtil.info(MODULE, "2222"+JSON.toJSONString(rOfflineReviewRequest).toString());
                    PaySuccInfo  psi = new PaySuccInfo();
                    psi.setOrderId(rOfflineReviewRequest.getOrderId());
                    psi.setPayType(PayStatus.PAY_TYPE_02); 
                    psi.setPayWay(PayStatus.PAY_WAY_9999);
                    psi.setPayTransNo(rOfflineReviewRequest.getOrderId());
                    psi.setStaffId(rOfflineReviewRequest.getStaffId());
                    ROrdMainResponse rOrdMainResponse = ordMainSV.findOrdMianByOrderId(rOfflineReviewRequest.getOrderId());
                    if(rOrdMainResponse != null){
                        psi.setPayment(rOrdMainResponse.getRealMoney());
                    }
                    // 记录支付成功日志
                    PayResultDTO payResult = new PayResultDTO();
                    payResult.setOrderId(psi.getOrderId());
                    payResult.setPayWay(psi.getPayWay());
                    payResult.setPayWayName(psi.getPayWayName());
                    payResult.setPayTransNo(psi.getPayTransNo());
                    payResult.setPayment(psi.getPayment());
                    payResult.setPayStatus("00");
                    payResult.setPayDesc("支付成功");
                    payResult.setPayTime(DateUtil.getSysDate());
                    payResult.setMerchAcct(psi.getMerchAcct());
                    payResult.setPayeeName(psi.getPayeeName());
                    payResult.setPayeeAcct(psi.getPayeeAcct());
                    payResultSV.addPayResult(payResult);
                    ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
                    rOrdPayRelReq.setOrderId(rOfflineReviewRequest.getOrderId());
                    rOrdPayRelReq.setJoinOrderid(rOfflineReviewRequest.getOrderId());
                    rOrdPayRelReq.setUpdateStaff(OrdConstants.Common.DEFAULT_STAFFID.toString());
                    ordMainSV.updateOrderPayTranNo(rOrdPayRelReq); //更新订单主表及索引表的商户订单号
                    payWaySV.savehandlePaySucc(psi);
                    
                    //后续扩展处理
                    imOrdBelongService.dealImOrdBelong(rOfflineReviewRequest);
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常==="+be);
                    status.setRollbackOnly();
                    throw be;
                } catch (Exception e) {
                    LogUtil.error(MODULE, "===系统异常==="+e);
                    status.setRollbackOnly();
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320003);
                }
                return null;
            }});
    }

    @Override
    public void saveOfflineReviewCancle(final ROfflineReviewRequest rOfflineReviewRequest) {
        //TODO:线下支付审核不通过的情况
        /*final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rOfflineReviewRequest));
        txMsg.setName("business-topic-chan");
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    ordOfflineChkSV.saveOfflineReview(rOfflineReviewRequest);
                } catch (BusinessException be) {
                    LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
                    be.printStackTrace();
//                    status.setRollbackOnly();
                    throw new BusinessException(be.getErrorCode());
                } catch (Exception e) {
                    e.printStackTrace();
//                    status.setRollbackOnly();
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320003);
                }
                return null;
            }});*/
            
    }

}

