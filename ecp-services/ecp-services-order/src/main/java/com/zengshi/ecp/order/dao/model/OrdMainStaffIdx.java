package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdMainStaffIdx implements Serializable {
    private Long id;

    private Long staffId;

    private Timestamp orderTime;

    private String status;

    private Timestamp deliverDate;

    private String orderId;

    private Long siteId;

    private String sysType;

    private String invoiceType;

    private String contactName;

    private String contactPhone;

    private String dispatchType;

    private String orderTwoStatus;

    private String payTranNo;

    private Long shopId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Timestamp deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType == null ? null : sysType.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType == null ? null : dispatchType.trim();
    }

    public String getOrderTwoStatus() {
        return orderTwoStatus;
    }

    public void setOrderTwoStatus(String orderTwoStatus) {
        this.orderTwoStatus = orderTwoStatus == null ? null : orderTwoStatus.trim();
    }

    public String getPayTranNo() {
        return payTranNo;
    }

    public void setPayTranNo(String payTranNo) {
        this.payTranNo = payTranNo == null ? null : payTranNo.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", status=").append(status);
        sb.append(", deliverDate=").append(deliverDate);
        sb.append(", orderId=").append(orderId);
        sb.append(", siteId=").append(siteId);
        sb.append(", sysType=").append(sysType);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", dispatchType=").append(dispatchType);
        sb.append(", orderTwoStatus=").append(orderTwoStatus);
        sb.append(", payTranNo=").append(payTranNo);
        sb.append(", shopId=").append(shopId);
        sb.append("]");
        return sb.toString();
    }
}