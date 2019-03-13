package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsEvalShopIdx implements Serializable {
    private Long shopId;

    private Long gdsId;

    private Long evalId;

    private Long staffId;

    private String staffName;

    private Timestamp evaluationTime;

    private Timestamp buyTime;

    private String gdsName;

    private Long skuId;

    private String orderId;

    private String orderSubId;

    private Short score;

    private Short correspondencyScore;

    private Short serviceattitudeScore;

    private Short deliveryspeedScore;

    private String content;

    private String isAnonymous;

    private Short isReply;

    private String labelNames;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getEvalId() {
        return evalId;
    }

    public void setEvalId(Long evalId) {
        this.evalId = evalId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Timestamp getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Timestamp buyTime) {
        this.buyTime = buyTime;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId == null ? null : orderSubId.trim();
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public Short getCorrespondencyScore() {
        return correspondencyScore;
    }

    public void setCorrespondencyScore(Short correspondencyScore) {
        this.correspondencyScore = correspondencyScore;
    }

    public Short getServiceattitudeScore() {
        return serviceattitudeScore;
    }

    public void setServiceattitudeScore(Short serviceattitudeScore) {
        this.serviceattitudeScore = serviceattitudeScore;
    }

    public Short getDeliveryspeedScore() {
        return deliveryspeedScore;
    }

    public void setDeliveryspeedScore(Short deliveryspeedScore) {
        this.deliveryspeedScore = deliveryspeedScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous == null ? null : isAnonymous.trim();
    }

    public Short getIsReply() {
        return isReply;
    }

    public void setIsReply(Short isReply) {
        this.isReply = isReply;
    }

    public String getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(String labelNames) {
        this.labelNames = labelNames == null ? null : labelNames.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", evalId=").append(evalId);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffName=").append(staffName);
        sb.append(", evaluationTime=").append(evaluationTime);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", skuId=").append(skuId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSubId=").append(orderSubId);
        sb.append(", score=").append(score);
        sb.append(", correspondencyScore=").append(correspondencyScore);
        sb.append(", serviceattitudeScore=").append(serviceattitudeScore);
        sb.append(", deliveryspeedScore=").append(deliveryspeedScore);
        sb.append(", content=").append(content);
        sb.append(", isAnonymous=").append(isAnonymous);
        sb.append(", isReply=").append(isReply);
        sb.append(", labelNames=").append(labelNames);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
