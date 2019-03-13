package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrdPresent implements Serializable {
    private Long id;

    private String orderId;

    private String subOrder;

    private Long promId;

    private Long credits;

    private String couponTypeId;

    private Long couponCnt;

    private String discountType;

    private Long createStaff;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Long updateStaff;

    private BigDecimal creditTimes;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder == null ? null : subOrder.trim();
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public String getCouponTypeId() {
        return couponTypeId;
    }

    public void setCouponTypeId(String couponTypeId) {
        this.couponTypeId = couponTypeId == null ? null : couponTypeId.trim();
    }

    public Long getCouponCnt() {
        return couponCnt;
    }

    public void setCouponCnt(Long couponCnt) {
        this.couponCnt = couponCnt;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType == null ? null : discountType.trim();
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public BigDecimal getCreditTimes() {
        return creditTimes;
    }

    public void setCreditTimes(BigDecimal creditTimes) {
        this.creditTimes = creditTimes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", subOrder=").append(subOrder);
        sb.append(", promId=").append(promId);
        sb.append(", credits=").append(credits);
        sb.append(", couponTypeId=").append(couponTypeId);
        sb.append(", couponCnt=").append(couponCnt);
        sb.append(", discountType=").append(discountType);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", creditTimes=").append(creditTimes);
        sb.append("]");
        return sb.toString();
    }
}