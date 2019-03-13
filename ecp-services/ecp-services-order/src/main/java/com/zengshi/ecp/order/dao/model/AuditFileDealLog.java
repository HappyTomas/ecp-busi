package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditFileDealLog implements Serializable {
    private Long id;

    private String fileName;

    private String fileHead;

    private String payWay;

    private Timestamp checkDate;

    private Long shopId;

    private Long totalNum;

    private Long totalTransAmount;

    private Timestamp createTime;

    private String executeMethods;

    private Long refundTotalNum;

    private Long refundTotalTransAmount;

    private String fileContent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileHead() {
        return fileHead;
    }

    public void setFileHead(String fileHead) {
        this.fileHead = fileHead == null ? null : fileHead.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

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

    public String getExecuteMethods() {
        return executeMethods;
    }

    public void setExecuteMethods(String executeMethods) {
        this.executeMethods = executeMethods == null ? null : executeMethods.trim();
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

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent == null ? null : fileContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileHead=").append(fileHead);
        sb.append(", payWay=").append(payWay);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", shopId=").append(shopId);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", totalTransAmount=").append(totalTransAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", executeMethods=").append(executeMethods);
        sb.append(", refundTotalNum=").append(refundTotalNum);
        sb.append(", refundTotalTransAmount=").append(refundTotalTransAmount);
        sb.append(", fileContent=").append(fileContent);
        sb.append("]");
        return sb.toString();
    }
}