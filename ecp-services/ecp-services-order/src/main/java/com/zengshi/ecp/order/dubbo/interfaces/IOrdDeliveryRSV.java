package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintRequest;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdDeliveryRSV {
    /** 
     * orderDelivery:订单发货. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void orderDelivery(RConfirmDeliveRequest rConfirmDeliveRequest) throws BusinessException;
    
    /** 
     * queryInvoiceInfo:发货清单打印信息查询. <br/> 
     * @author cbl 
     * @param rDeliveryPrintRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public RDeliveryPrintResponse queryInvoiceInfo(RDeliveryPrintRequest rDeliveryPrintRequest);
}

