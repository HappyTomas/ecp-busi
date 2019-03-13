package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromGiftDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long promId;//促销编码

    private Long giftId;//赠品id
    
    private String giftName;//赠品名称

    private Long gdsId;//商品编码

    private Long skuId;//单品编码

    private Long presentAllCnt;//赠送总量

    private Long presentCnt;//已赠送量

    private Long everyTimeCnt;//需赠送量
    
    private Long remaindCnt;//剩余量

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;
    
    private Long shopId;//店铺编码
    
    private String giftType;//赠品类型
    
    private String giftTypeName;//赠品类型名称

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

    public Long getPresentAllCnt() {
        return presentAllCnt;
    }

    public void setPresentAllCnt(Long presentAllCnt) {
        this.presentAllCnt = presentAllCnt;
    }

    public Long getPresentCnt() {
        return presentCnt;
    }

    public void setPresentCnt(Long presentCnt) {
        this.presentCnt = presentCnt;
    }

    public Long getRemaindCnt() {
        return remaindCnt;
    }

    public void setRemaindCnt(Long remaindCnt) {
        this.remaindCnt = remaindCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getEveryTimeCnt() {
        return everyTimeCnt;
    }

    public void setEveryTimeCnt(Long everyTimeCnt) {
        this.everyTimeCnt = everyTimeCnt;
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

	public String getGiftType() {
		return giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}

	public String getGiftTypeName() {
		return giftTypeName;
	}

	public void setGiftTypeName(String giftTypeName) {
		this.giftTypeName = giftTypeName;
	}

   
}