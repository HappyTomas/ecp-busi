package com.zengshi.ecp.order.dubbo.dto;

public class SBaseAndBatchInfo extends SBaseSplitInfo {
    
    /** 
     * batchId:发货批次. 
     * @since JDK 1.6 
     */ 
    private Long batchId;
    
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

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }
}

