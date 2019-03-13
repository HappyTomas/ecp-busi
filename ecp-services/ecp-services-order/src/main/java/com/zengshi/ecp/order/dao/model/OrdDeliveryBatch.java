package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdDeliveryBatch implements Serializable {
    private Long id;

    private String orderId;

    private Long staffId;

    private Long shopId;

    private Long proxyShopId;

    private String deliveryType;

    private Timestamp sendDate;

    private String sendName;

    private String sendTel;

    private String sendPhone;

    private String sendAddress;

    private String sendZip;

    private Long expressId;

    private String expressNo;

    private String contactName;

    private String contactTel;

    private String contactPhone;

    private String contactZip;

    private String contactAddress;

    private String bringName;

    private String cardType;

    private String cardId;

    private String bringTel;

    private String bringPhone;

    private Timestamp contactDate;

    private String remark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

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

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getProxyShopId() {
        return proxyShopId;
    }

    public void setProxyShopId(Long proxyShopId) {
        this.proxyShopId = proxyShopId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName == null ? null : sendName.trim();
    }

    public String getSendTel() {
        return sendTel;
    }

    public void setSendTel(String sendTel) {
        this.sendTel = sendTel == null ? null : sendTel.trim();
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone == null ? null : sendPhone.trim();
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress == null ? null : sendAddress.trim();
    }

    public String getSendZip() {
        return sendZip;
    }

    public void setSendZip(String sendZip) {
        this.sendZip = sendZip == null ? null : sendZip.trim();
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactZip() {
        return contactZip;
    }

    public void setContactZip(String contactZip) {
        this.contactZip = contactZip == null ? null : contactZip.trim();
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress == null ? null : contactAddress.trim();
    }

    public String getBringName() {
        return bringName;
    }

    public void setBringName(String bringName) {
        this.bringName = bringName == null ? null : bringName.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getBringTel() {
        return bringTel;
    }

    public void setBringTel(String bringTel) {
        this.bringTel = bringTel == null ? null : bringTel.trim();
    }

    public String getBringPhone() {
        return bringPhone;
    }

    public void setBringPhone(String bringPhone) {
        this.bringPhone = bringPhone == null ? null : bringPhone.trim();
    }

    public Timestamp getContactDate() {
        return contactDate;
    }

    public void setContactDate(Timestamp contactDate) {
        this.contactDate = contactDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", staffId=").append(staffId);
        sb.append(", shopId=").append(shopId);
        sb.append(", proxyShopId=").append(proxyShopId);
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", sendDate=").append(sendDate);
        sb.append(", sendName=").append(sendName);
        sb.append(", sendTel=").append(sendTel);
        sb.append(", sendPhone=").append(sendPhone);
        sb.append(", sendAddress=").append(sendAddress);
        sb.append(", sendZip=").append(sendZip);
        sb.append(", expressId=").append(expressId);
        sb.append(", expressNo=").append(expressNo);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", contactZip=").append(contactZip);
        sb.append(", contactAddress=").append(contactAddress);
        sb.append(", bringName=").append(bringName);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardId=").append(cardId);
        sb.append(", bringTel=").append(bringTel);
        sb.append(", bringPhone=").append(bringPhone);
        sb.append(", contactDate=").append(contactDate);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}