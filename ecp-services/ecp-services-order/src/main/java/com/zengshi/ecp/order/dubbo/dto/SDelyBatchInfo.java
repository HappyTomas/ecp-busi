package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

public class SDelyBatchInfo {
    /** 
     * batchNo:发货批次号. 
     * @since JDK 1.6 
     */ 
    private Long batchNo;
    /** 
     * sendDate:发货时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp sendDate;
    public Long getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }
    public Timestamp getSendDate() {
        return sendDate;
    }
    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }
}

