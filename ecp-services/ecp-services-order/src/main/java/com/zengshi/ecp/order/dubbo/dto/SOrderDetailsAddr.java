package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

public class SOrderDetailsAddr implements Serializable{
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8406630002873608925L;
    /** 
     * contactName:收货人. 
     * @since JDK 1.6 
     */ 
    private String contactName;
    /** 
     * chnlAddress:地址. 
     * @since JDK 1.6 
     */ 
    private String chnlAddress;
    /** 
     * contactPhone:手机号. 
     * @since JDK 1.6 
     */ 
    private String contactPhone;
    /** 
     * contactNumber:固定电话. 
     * @since JDK 1.6 
     */ 
    private String contactNumber;
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getChnlAddress() {
        return chnlAddress;
    }
    public void setChnlAddress(String chnlAddress) {
        this.chnlAddress = chnlAddress;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

