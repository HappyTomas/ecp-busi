package com.zengshi.ecp.order.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdReceiptRSV {
    /** 
     * orderReceipt:订单收货确认. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void orderReceipt(ROrdReceiptRequest rOrdReceiptRequest) throws BusinessException;
    
    public List<ROrdMainResponse> queryNotReceiptOrder(ROrdReceiptRequest rOrdReceiptRequest) throws BusinessException;
}

