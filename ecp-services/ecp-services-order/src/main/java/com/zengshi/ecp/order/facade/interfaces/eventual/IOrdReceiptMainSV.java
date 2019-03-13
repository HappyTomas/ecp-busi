package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;

public interface IOrdReceiptMainSV{

    public void orderReceipt(ROrdReceiptRequest rOrdReceiptRequest);
}

