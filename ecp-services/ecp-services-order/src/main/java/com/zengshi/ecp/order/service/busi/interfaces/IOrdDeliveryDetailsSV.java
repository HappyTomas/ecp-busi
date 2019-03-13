package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdDeliveryDetails;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月7日下午2:44:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 *
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public interface IOrdDeliveryDetailsSV {
    /** 
     * saveOrdDeliveryDetailsList:保存发货批次详情列表. <br/> 
     * @author cbl 
     * @param ordDeliveryDetails 
     * @since JDK 1.6 
     */ 
    public void saveOrdDeliveryDetailsList(List<OrdDeliveryDetails> ordDeliveryDetails);

    /** 
     * findByOrderSubId:根据子订单查询数据信息. <br/> 
     * @author cbl 
     * @param orderId
     * @param orderSubId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdDeliveryDetails> findByOrderSubId(String orderId,String orderSubId);
    
    /** 
     * findByOrderId:根据订单号查询数据信息. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdDeliveryDetails> findByOrderId(String orderId);
}
