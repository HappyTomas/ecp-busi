package com.zengshi.ecp.order.service.busi.interfaces.pay;

import com.zengshi.ecp.order.dubbo.dto.pay.PayRefundSuccInfo;

public interface IRefundCallbackSV {
    public void saveRefundCallback(PayRefundSuccInfo payRefundSuccInfo);
}

