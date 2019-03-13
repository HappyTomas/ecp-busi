package com.zengshi.ecp.busi.seller.prom.createprom.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-2 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class GiftReqVO extends EcpBasePageReqVO{


    private static final long serialVersionUID = 1L;

    private Long id;//赠品id

    private Long gdsId;

    private Long skuId;

    private Long shopId;

    private String giftName;

    private String giftDesc;

    private Long giftValue;//赠品价值

    private String giftStatus;//赠品状态,1：有效 0：无效
    
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;//生效时间

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;//失效时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public Long getGiftValue() {
        return giftValue;
    }

    public void setGiftValue(Long giftValue) {
        this.giftValue = giftValue;
    }

    public String getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(String giftStatus) {
        this.giftStatus = giftStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

 
}
