/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ShopShipperReqDTO.java 
 * Package Name:com.zengshi.ecp.staff.dubbo.dto 
 * Date:2015年9月16日下午7:53:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午7:53:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class ShopShipperReqDTO extends BaseInfo{
    private Long id;

    private Long shopId;

    private String shipperName;

    private String shipperPhone;

    private String shipperMobile;

    private String shipperAddress;

    private String postCode;

    private String remark;

    private String shipperUsingFlag;

    private String backUsingFlag;

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

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName == null ? null : shipperName.trim();
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone == null ? null : shipperPhone.trim();
    }

    public String getShipperMobile() {
        return shipperMobile;
    }

    public void setShipperMobile(String shipperMobile) {
        this.shipperMobile = shipperMobile == null ? null : shipperMobile.trim();
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress == null ? null : shipperAddress.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getShipperUsingFlag() {
        return shipperUsingFlag;
    }

    public void setShipperUsingFlag(String shipperUsingFlag) {
        this.shipperUsingFlag = shipperUsingFlag == null ? null : shipperUsingFlag.trim();
    }

    public String getBackUsingFlag() {
        return backUsingFlag;
    }

    public void setBackUsingFlag(String backUsingFlag) {
        this.backUsingFlag = backUsingFlag == null ? null : backUsingFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", shipperName=").append(shipperName);
        sb.append(", shipperPhone=").append(shipperPhone);
        sb.append(", shipperMobile=").append(shipperMobile);
        sb.append(", shipperAddress=").append(shipperAddress);
        sb.append(", postCode=").append(postCode);
        sb.append(", remark=").append(remark);
        sb.append(", shipperUsingFlag=").append(shipperUsingFlag);
        sb.append(", backUsingFlag=").append(backUsingFlag);
        sb.append("]");
        return sb.toString();
    }
}

