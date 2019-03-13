package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdAddressResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3294982629763512084L;
    /** 
     * chnlAddress:收货地址. 
     * @since JDK 1.6 
     */ 
    private String chnlAddress;

    /** 
     * postCode:邮政编码. 
     * @since JDK 1.6 
     */ 
    private String postCode;

    /** 
     * province:行政省份. 
     * @since JDK 1.6 
     */ 
    private String province;

    /** 
     * cityCode:行政地市编码. 
     * @since JDK 1.6 
     */ 
    private String cityCode;

    /** 
     * countyCode:行政区县编码. 
     * @since JDK 1.6 
     */ 
    private String countyCode;
    
    /** 
     * contactName:收货人. 
     * @since JDK 1.6 
     */ 
    private String contactName;
    
    /** 
     * contactPhone:手机号码. 
     * @since JDK 1.6 
     */ 
    private String contactPhone;
    
    /** 
     * contactNumber:固定号码. 
     * @since JDK 1.6 
     */ 
    private String contactNumber;
    
    

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public String getChnlAddress() {
        return chnlAddress;
    }

    public void setChnlAddress(String chnlAddress) {
        this.chnlAddress = chnlAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

}

