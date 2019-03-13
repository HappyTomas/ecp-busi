package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditDailySum extends AuditDailySumKey implements Serializable {
    private Long totalNum;

    private Long totalTransAmount;

    private Timestamp createTime;

    private String fileName;

    private Long refundTotalNum;

    private Long refundTotalTransAmount;

    private static final long serialVersionUID = 1L;

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getTotalTransAmount() {
        return totalTransAmount;
    }

    public void setTotalTransAmount(Long totalTransAmount) {
        this.totalTransAmount = totalTransAmount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Long getRefundTotalNum() {
        return refundTotalNum;
    }

    public void setRefundTotalNum(Long refundTotalNum) {
        this.refundTotalNum = refundTotalNum;
    }

    public Long getRefundTotalTransAmount() {
        return refundTotalTransAmount;
    }

    public void setRefundTotalTransAmount(Long refundTotalTransAmount) {
        this.refundTotalTransAmount = refundTotalTransAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", totalNum=").append(totalNum);
        sb.append(", totalTransAmount=").append(totalTransAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", fileName=").append(fileName);
        sb.append(", refundTotalNum=").append(refundTotalNum);
        sb.append(", refundTotalTransAmount=").append(refundTotalTransAmount);
        sb.append("]");
        return sb.toString();
    }
}