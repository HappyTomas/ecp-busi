package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdInvoiceComm;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsComm;



/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月17日下午4:25:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdInvoiceCommSV {
    
    /**
     * 
     * saveOrdInvoiceComm:(保存普票信息). <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void saveOrdInvoiceComm(OrdInvoiceComm info);
    
    /** 
     * queryOrderDetailsComm:订单详情查询普通发票相关信息. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public SOrderDetailsComm queryOrderDetailsComm(String orderId);
    
    /** 
     * updateOrdInvoiceComm:修改订单普通发票相关信息. <br/> 
     * @author lwy 
     * @param info
     * @return 
     * @since JDK 1.6 
     */ 
    public void updateOrdInvoiceComm(OrdInvoiceComm info);
    
    /** 
     * 计算该订单下的发票张数. <br/> 
     * @author lwy 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public Long countOrdInvoiceCommByOrderId(String orderId);
    
    /** 
     * 删除该订单下的发票信息. <br/> 
     * @author lwy 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public void deleteInvoiceCommByOrderId(String orderId);

}

