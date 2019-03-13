package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdInvoiceTax implements Serializable {
    private Long id;

    private String orderId;

    private Long shopId;

    private String invoiceTitle;

    private String taxpayerNo;

    private String contactInfo;

    private String phone;

    private String bankName;

    private String acctInfo;

    private String takerPhone;

    private String takerEmail;

    private String takerProvince;

    private String takerCity;

    private String takerCounty;

    private String takerAddress;

    private String vfsId1;

    private String vfsId2;

    private String vfsId3;

    private String vfsId4;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo == null ? null : taxpayerNo.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getAcctInfo() {
        return acctInfo;
    }

    public void setAcctInfo(String acctInfo) {
        this.acctInfo = acctInfo == null ? null : acctInfo.trim();
    }

    public String getTakerPhone() {
        return takerPhone;
    }

    public void setTakerPhone(String takerPhone) {
        this.takerPhone = takerPhone == null ? null : takerPhone.trim();
    }

    public String getTakerEmail() {
        return takerEmail;
    }

    public void setTakerEmail(String takerEmail) {
        this.takerEmail = takerEmail == null ? null : takerEmail.trim();
    }

    public String getTakerProvince() {
        return takerProvince;
    }

    public void setTakerProvince(String takerProvince) {
        this.takerProvince = takerProvince == null ? null : takerProvince.trim();
    }

    public String getTakerCity() {
        return takerCity;
    }

    public void setTakerCity(String takerCity) {
        this.takerCity = takerCity == null ? null : takerCity.trim();
    }

    public String getTakerCounty() {
        return takerCounty;
    }

    public void setTakerCounty(String takerCounty) {
        this.takerCounty = takerCounty == null ? null : takerCounty.trim();
    }

    public String getTakerAddress() {
        return takerAddress;
    }

    public void setTakerAddress(String takerAddress) {
        this.takerAddress = takerAddress == null ? null : takerAddress.trim();
    }

    public String getVfsId1() {
        return vfsId1;
    }

    public void setVfsId1(String vfsId1) {
        this.vfsId1 = vfsId1 == null ? null : vfsId1.trim();
    }

    public String getVfsId2() {
        return vfsId2;
    }

    public void setVfsId2(String vfsId2) {
        this.vfsId2 = vfsId2 == null ? null : vfsId2.trim();
    }

    public String getVfsId3() {
        return vfsId3;
    }

    public void setVfsId3(String vfsId3) {
        this.vfsId3 = vfsId3 == null ? null : vfsId3.trim();
    }

    public String getVfsId4() {
        return vfsId4;
    }

    public void setVfsId4(String vfsId4) {
        this.vfsId4 = vfsId4 == null ? null : vfsId4.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", shopId=").append(shopId);
        sb.append(", invoiceTitle=").append(invoiceTitle);
        sb.append(", taxpayerNo=").append(taxpayerNo);
        sb.append(", contactInfo=").append(contactInfo);
        sb.append(", phone=").append(phone);
        sb.append(", bankName=").append(bankName);
        sb.append(", acctInfo=").append(acctInfo);
        sb.append(", takerPhone=").append(takerPhone);
        sb.append(", takerEmail=").append(takerEmail);
        sb.append(", takerProvince=").append(takerProvince);
        sb.append(", takerCity=").append(takerCity);
        sb.append(", takerCounty=").append(takerCounty);
        sb.append(", takerAddress=").append(takerAddress);
        sb.append(", vfsId1=").append(vfsId1);
        sb.append(", vfsId2=").append(vfsId2);
        sb.append(", vfsId3=").append(vfsId3);
        sb.append(", vfsId4=").append(vfsId4);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}