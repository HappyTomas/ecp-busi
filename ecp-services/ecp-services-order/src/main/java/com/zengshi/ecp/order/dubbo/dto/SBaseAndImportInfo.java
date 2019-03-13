package com.zengshi.ecp.order.dubbo.dto;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月18日下午3:09:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class SBaseAndImportInfo extends SBaseSplitInfo {

    /** 
     * importNo:实体编号相关的批次号. 
     * @since JDK 1.6 
     */ 
    private Long importNo;

    /** 
     * status:批次状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    
    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private String orderSubId;
    
    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }

    /** 
     * entityCode:实体编号. 
     * @since JDK 1.6 
     */ 
    private String entityCode;
    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getImportNo() {
        return importNo;
    }

    public void setImportNo(Long importNo) {
        this.importNo = importNo;
    }
}

