package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月5日下午9:01:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author pengjie
 * @version  0.0.1
 * @since JDK 1.6
 * 
 * The user receiving address return parameter
 */
public class CustAddrResDTO extends BaseResponseDTO implements Comparable<CustAddrResDTO> {
    private Long id;

    private Long staffId;

    private String contactName;

    private String contactNumber;

    private String contactPhone;

    private String postCode;

    private String chnlAddress;

    private String countryCode;

    private String province;

    private String cityCode;

    private String countyCode;

    private String status;

    private String usingFlag;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String bringName;

    private String cardType;

    private String cardId;

    private String bringNumber;

    private String bringPhone;
    
    private String pccName;//省份地市区县组成的名称

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getChnlAddress() {
        return chnlAddress;
    }

    public void setChnlAddress(String chnlAddress) {
        this.chnlAddress = chnlAddress == null ? null : chnlAddress.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUsingFlag() {
        return usingFlag;
    }

    public void setUsingFlag(String usingFlag) {
        this.usingFlag = usingFlag == null ? null : usingFlag.trim();
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

    public String getBringNumber() {
        return bringNumber;
    }

    public void setBringNumber(String bringNumber) {
        this.bringNumber = bringNumber == null ? null : bringNumber.trim();
    }

    public String getBringPhone() {
        return bringPhone;
    }

    public void setBringPhone(String bringPhone) {
        this.bringPhone = bringPhone == null ? null : bringPhone.trim();
    }

    public String getPccName() {
        return pccName;
    }

    public void setPccName(String pccName) {
        this.pccName = pccName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactNumber=").append(contactNumber);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", postCode=").append(postCode);
        sb.append(", chnlAddress=").append(chnlAddress);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", province=").append(province);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", status=").append(status);
        sb.append(", usingFlag=").append(usingFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", bringName=").append(bringName);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardId=").append(cardId);
        sb.append(", bringNumber=").append(bringNumber);
        sb.append(", bringPhone=").append(bringPhone);
        sb.append(", pccName=").append(pccName);
        sb.append("]");
        return sb.toString();
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Comparable#compareTo(java.lang.Object) 
     */
    @Override
    public int compareTo(CustAddrResDTO arg0) {
        //第一次根据默认收货地址标志位进行比较，因为1表示默认收货地址，排在前面
    	int flag = 0;
    	if ("1".equals(arg0.getUsingFlag())) {
    		flag = -1;
    	}
        if(flag == 0)
        {
            //类型一样，根据更新时间进行第二次比较
            return -this.getUpdateTime().compareTo(arg0.getUpdateTime());
        }
        return -flag;
    }
}

