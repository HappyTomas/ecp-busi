package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

public class SOrderDetailsTax implements Serializable{
    
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4832264986364605131L;

    /** 
     * shopId:供货商编码. 
     * @since JDK 1.6 
     */ 
    private Long shopId;

    /** 
     * invoiceTitle:单位名称. 
     * @since JDK 1.6 
     */ 
    private String invoiceTitle;

    /** 
     * taxpayerNo:纳税人识别号. 
     * @since JDK 1.6 
     */ 
    private String taxpayerNo;

    /** 
     * contactInfo:注册地址. 
     * @since JDK 1.6 
     */ 
    private String contactInfo;

    /** 
     * phone:注册电话. 
     * @since JDK 1.6 
     */ 
    private String phone;

    /** 
     * bankName:开户行. 
     * @since JDK 1.6 
     */ 
    private String bankName;

    /** 
     * acctInfo:银行账号. 
     * @since JDK 1.6 
     */ 
    private String acctInfo;

    /** 
     * takerPhone:收票人手机. 
     * @since JDK 1.6 
     */ 
    private String takerPhone;

    /** 
     * takerEmail:收票人邮箱. 
     * @since JDK 1.6 
     */ 
    private String takerEmail;

    /** 
     * takerProvince:收票人省份. 
     * @since JDK 1.6 
     */ 
    private String takerProvince;

    /** 
     * takerCity:收票人地市. 
     * @since JDK 1.6 
     */ 
    private String takerCity;

    /** 
     * takerCounty:收票人区县. 
     * @since JDK 1.6 
     */ 
    private String takerCounty;

    /** 
     * takerAddress:收票人地址. 
     * @since JDK 1.6 
     */ 
    private String takerAddress;

    /** 
     * vfsId1:加盖公章的营业执照. 
     * @since JDK 1.6 
     */ 
    private String vfsId1;

    /** 
     * vfsId2:加盖公章的税务登记证. 
     * @since JDK 1.6 
     */ 
    private String vfsId2;

    /** 
     * vfsId3:加盖公章的一般纳税人资格证书. 
     * @since JDK 1.6 
     */ 
    private String vfsId3;

    /** 
     * vfsId4:加盖公章的银行开户许可证扫描件. 
     * @since JDK 1.6 
     */ 
    private String vfsId4;

    /** 
     * status:状态. 
     * @since JDK 1.6 
     */ 
    private String status;


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
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAcctInfo() {
        return acctInfo;
    }

    public void setAcctInfo(String acctInfo) {
        this.acctInfo = acctInfo;
    }

    public String getTakerPhone() {
        return takerPhone;
    }

    public void setTakerPhone(String takerPhone) {
        this.takerPhone = takerPhone;
    }

    public String getTakerEmail() {
        return takerEmail;
    }

    public void setTakerEmail(String takerEmail) {
        this.takerEmail = takerEmail;
    }

    public String getTakerProvince() {
        return takerProvince;
    }

    public void setTakerProvince(String takerProvince) {
        this.takerProvince = takerProvince;
    }

    public String getTakerCity() {
        return takerCity;
    }

    public void setTakerCity(String takerCity) {
        this.takerCity = takerCity;
    }

    public String getTakerCounty() {
        return takerCounty;
    }

    public void setTakerCounty(String takerCounty) {
        this.takerCounty = takerCounty;
    }

    public String getTakerAddress() {
        return takerAddress;
    }

    public void setTakerAddress(String takerAddress) {
        this.takerAddress = takerAddress;
    }

    public String getVfsId1() {
        return vfsId1;
    }

    public void setVfsId1(String vfsId1) {
        this.vfsId1 = vfsId1;
    }

    public String getVfsId2() {
        return vfsId2;
    }

    public void setVfsId2(String vfsId2) {
        this.vfsId2 = vfsId2;
    }

    public String getVfsId3() {
        return vfsId3;
    }

    public void setVfsId3(String vfsId3) {
        this.vfsId3 = vfsId3;
    }

    public String getVfsId4() {
        return vfsId4;
    }

    public void setVfsId4(String vfsId4) {
        this.vfsId4 = vfsId4;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

