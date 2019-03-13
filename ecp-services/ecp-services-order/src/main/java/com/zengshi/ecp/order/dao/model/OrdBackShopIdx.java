package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdBackShopIdx implements Serializable {
    private Long id;

    private Long shopId;

    private Long backId;

    private String orderId;

    private Long staffId;

    private String applyType;

    private Timestamp applyTime;

    private String status;

    private String backType;

    private String payType;

    private Long siteId;

    private String payTranNo;

    private String isCompenstate;

    private Timestamp refundTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBackType() {
        return backType;
    }

    public void setBackType(String backType) {
        this.backType = backType == null ? null : backType.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getPayTranNo() {
        return payTranNo;
    }

    public void setPayTranNo(String payTranNo) {
        this.payTranNo = payTranNo == null ? null : payTranNo.trim();
    }

    public String getIsCompenstate() {
        return isCompenstate;
    }

    public void setIsCompenstate(String isCompenstate) {
        this.isCompenstate = isCompenstate == null ? null : isCompenstate.trim();
    }

    public Timestamp getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Timestamp refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", backId=").append(backId);
        sb.append(", orderId=").append(orderId);
        sb.append(", staffId=").append(staffId);
        sb.append(", applyType=").append(applyType);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", status=").append(status);
        sb.append(", backType=").append(backType);
        sb.append(", payType=").append(payType);
        sb.append(", siteId=").append(siteId);
        sb.append(", payTranNo=").append(payTranNo);
        sb.append(", isCompenstate=").append(isCompenstate);
        sb.append(", refundTime=").append(refundTime);
        sb.append("]");
        return sb.toString();
    }
}