package com.zengshi.ecp.order.service.busi.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.PayRefundResult;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRefundSuccInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayOrderSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRefundResultSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IRefundCallbackSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class RefundCallbackSV implements IRefundCallbackSV {
    
    public static final String module = RefundCallbackSV.class.getName();

    @Resource
    protected IPayRefundResultSV payRefundResultSV;
    
    @Resource
    protected IBackPayOrderSV backPayOrderSV;
    
    @Override
    public void saveRefundCallback(PayRefundSuccInfo payRefundSuccInfo) {
        try{
            //
            PayRefundResult bean = payRefundResultSV.getPayRefundResultByPaywayBackId(payRefundSuccInfo.getPayWay(), payRefundSuccInfo.getBackId());
            PayRefundResult payRefundResult = new PayRefundResult();
            payRefundResult.setId(bean.getId());
            payRefundResult.setRefundStatus(payRefundSuccInfo.getFlag());
            int dealCount = payRefundResultSV.updateRefund(payRefundResult);
            //过滤已经处理过的
            if(dealCount==0){
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310020);
            }else{
                //处理订单逻辑
                RBackConfirmReq rBackConfirmReq = new RBackConfirmReq();
                rBackConfirmReq.setBackId(payRefundSuccInfo.getBackId());
                rBackConfirmReq.setOrderId(payRefundSuccInfo.getOrderId());
                rBackConfirmReq.getStaff().setId(payRefundSuccInfo.getStaffId());
                backPayOrderSV.dealMethod(rBackConfirmReq);
            }
        }catch(Exception e){
            LogUtil.error(module, "严重错误：退款回调后订单状态变更失败！",e);
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310018);
        }
    }
    
}

