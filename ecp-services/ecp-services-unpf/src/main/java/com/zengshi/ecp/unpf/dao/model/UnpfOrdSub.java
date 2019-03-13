package com.zengshi.ecp.unpf.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UnpfOrdSub implements Serializable {
    private String id;

    private String orderId;

    private String platType;

    private String outerSubId;

    private String outerId;

    private String gdsId;

    private String gdsName;

    private String picUrl;

    private String skuId;

    private String skuInfo;

    private String orderAmount;

    private Long orderPrice;

    private Long deliveryAmount;

    private String remark;

    private Timestamp orderTime;

    private String status;

    private Long shopId;

    private Long staffId;

    private String deliveryStatus;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String sysFlag;

    private Long discountMoney;

    private Long realMoney;

    private Long orderMoney;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public String getOuterSubId() {
        return outerSubId;
    }

    public void setOuterSubId(String outerSubId) {
        this.outerSubId = outerSubId == null ? null : outerSubId.trim();
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }

    public String getGdsId() {
        return gdsId;
    }

    public void setGdsId(String gdsId) {
        this.gdsId = gdsId == null ? null : gdsId.trim();
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(String skuInfo) {
        this.skuInfo = skuInfo == null ? null : skuInfo.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Long deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus == null ? null : deliveryStatus.trim();
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

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag == null ? null : sysFlag.trim();
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", platType=").append(platType);
        sb.append(", outerSubId=").append(outerSubId);
        sb.append(", outerId=").append(outerId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuInfo=").append(skuInfo);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", deliveryAmount=").append(deliveryAmount);
        sb.append(", remark=").append(remark);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", status=").append(status);
        sb.append(", shopId=").append(shopId);
        sb.append(", staffId=").append(staffId);
        sb.append(", deliveryStatus=").append(deliveryStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", sysFlag=").append(sysFlag);
        sb.append(", discountMoney=").append(discountMoney);
        sb.append(", realMoney=").append(realMoney);
        sb.append(", orderMoney=").append(orderMoney);
        sb.append("]");
        return sb.toString();
    }
}