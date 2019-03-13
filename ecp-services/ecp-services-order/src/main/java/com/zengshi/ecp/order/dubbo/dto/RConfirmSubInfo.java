package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月18日下午3:11:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class RConfirmSubInfo implements Serializable{
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2170695093571514401L;
    /** 
     * orderSubId:子订单ID. 
     * @since JDK 1.6 
     */ 
    private String orderSubId;
    /** 
     * deliveryAmount:发货数量. 
     * @since JDK 1.6 
     */ 
    private Long    deliveryAmount;
    /** 
     * importNo:编码导入批次号. 
     * @since JDK 1.6 
     */ 
    private String importNo;
    /** 
     * stockId:库存量ID. 
     * @since JDK 1.6 
     */ 
    private Long stockId;
    /** 
     * isSendAll:子订单是否全部发货. 
     * @since JDK 1.6 
     */ 
    private Boolean isSendAll;
    /** 
     * isImport:是否发货时导入实体编号. 
     * @since JDK 1.6 
     */ 
    private String isImport;
    public Boolean getIsSendAll() {
        return isSendAll;
    }
    public void setIsSendAll(Boolean isSendAll) {
        this.isSendAll = isSendAll;
    }
    public Long getStockId() {
        return stockId;
    }
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
    public String getIsImport() {
        return isImport;
    }
    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }
    public String getOrderSubId() {
        return orderSubId;
    }
    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }
    public Long getDeliveryAmount() {
        return deliveryAmount;
    }
    public void setDeliveryAmount(Long deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }
    public String getImportNo() {
        return importNo;
    }
    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }
    
}

