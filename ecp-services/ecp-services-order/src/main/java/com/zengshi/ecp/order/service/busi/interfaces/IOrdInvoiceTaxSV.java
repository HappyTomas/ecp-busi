package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdInvoiceTax;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTax;



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
public interface IOrdInvoiceTaxSV {
    /**
     * 
     * saveOrdInvoiceTax:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void saveOrdInvoiceTax(OrdInvoiceTax info);
    
    /** 
     * queryOrderDetailsTax:订单详情查询增值税发票相关信息. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public SOrderDetailsTax queryOrderDetailsTax(String orderId);
    
    /** 
     * updateOrdInvoiceTax:修改订单增值税发票相关信息. <br/> 
     * @author lwy 
     * @param info
     * @return 
     * @since JDK 1.6 
     */ 
    public void updateOrdInvoiceTax(OrdInvoiceTax info);

}

