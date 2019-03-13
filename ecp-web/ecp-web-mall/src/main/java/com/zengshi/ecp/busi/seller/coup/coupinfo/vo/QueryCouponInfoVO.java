package com.zengshi.ecp.busi.seller.coup.coupinfo.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-08 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class QueryCouponInfoVO extends EcpBasePageReqVO{

    private static final long serialVersionUID = 1L;

    private Long siteId;//站点

    private Long shopId;//店铺

    private Long coupTypeId;//优惠券类型id

    private String coupName;//优惠券名称

    private String typeLimit;//类型

    private String status;//状态

    private Long coupAmount;//面额

    private String ifCode;//是否优惠码

    private String ifBack;//是否退货

    private String effType;//有效期类型

    private Long fixedTime;//浮动时间

    // 生效时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activeTime;

    // 截止时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inactiveTime;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCoupTypeId() {
        return coupTypeId;
    }

    public void setCoupTypeId(Long coupTypeId) {
        this.coupTypeId = coupTypeId;
    }


    public String getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(String typeLimit) {
        this.typeLimit = typeLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCoupAmount() {
        return coupAmount;
    }

    public void setCoupAmount(Long coupAmount) {
        this.coupAmount = coupAmount;
    }

    public String getIfCode() {
        return ifCode;
    }

    public void setIfCode(String ifCode) {
        this.ifCode = ifCode;
    }

    public String getIfBack() {
        return ifBack;
    }

    public void setIfBack(String ifBack) {
        this.ifBack = ifBack;
    }

    public String getEffType() {
        return effType;
    }

    public void setEffType(String effType) {
        this.effType = effType;
    }

    public Long getFixedTime() {
        return fixedTime;
    }

    public void setFixedTime(Long fixedTime) {
        this.fixedTime = fixedTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Date inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }
}
