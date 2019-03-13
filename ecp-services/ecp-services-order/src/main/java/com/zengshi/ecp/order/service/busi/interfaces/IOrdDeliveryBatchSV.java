package com.zengshi.ecp.order.service.busi.interfaces;


import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdDeliveryBatch;
import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintRequest;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDelivery;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月6日下午2:38:08 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 *
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public interface IOrdDeliveryBatchSV {
    /**
     * saveOrdDeliveryBatch:(保存发货批次表信息). <br/>
     * @author cbl
     * @param ordDeliveryBatch
     * @since JDK 1.6
     */
    public OrdDeliveryBatch saveOrdDeliveryBatch(OrdDeliveryBatch ordDeliveryBatch);

    /** 
     * confirmDelive:确认发货. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public Long saveConfirmDeliveSV(RConfirmDeliveRequest rConfirmDeliveRequest);
    
    /** 
     * queryExpressInfoByOrderId:根据订单号查询物流相关信息. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<SOrderDetailsDelivery> queryExpressInfoByOrderId(String orderId);
    
    /** 
     * queryExpressInfoByOrderId:根据物流单号查询物流相关信息. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdDeliveryBatch> queryExpressInfoByexpressNo(String expressNo);
    
    /** 
     * queryInvoiceInfo:发货清单打印信息查询. <br/> 
     * @author cbl 
     * @param rDeliveryPrintRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public RDeliveryPrintResponse queryInvoiceInfo(RDeliveryPrintRequest rDeliveryPrintRequest);
    
}
