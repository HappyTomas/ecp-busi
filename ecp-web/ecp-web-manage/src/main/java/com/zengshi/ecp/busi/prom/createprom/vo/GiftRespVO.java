package com.zengshi.ecp.busi.prom.createprom.vo;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-20 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class GiftRespVO extends BaseResponseDTO{

    private static final long serialVersionUID = 1L;

    private Long id;//赠品id

    private Long gdsId;

    private Long skuId;

    private Long shopId;

    private String giftPic;//赠品图片

    private String giftName;

    private String giftDesc;

    private Long giftValue;//赠品价值

    private String giftStatus;//赠品状态,1：有效 0：无效

    private String giftStatusName;//赠品状态,1：有效 0：无效
    
    private Long giftAmount;//赠品总量

    private Long giftSend;//已赠量

    private Long giftValid;//可赠量
    
    private Long giftCnt;//填写数量

    private Date startTime;//生效时间

    private Date endTime;//失效时间
    
    private Long promId;

    private Long presentAllCnt;

    private Long presentCnt;

    private Long remaindCnt;
    
    private Date createTime;

    private Long createStaff;
    
    private Long everyTimeCnt;//每次赠送量
    
    private String giftType;//赠品类型
    
    private String giftTypeName;//赠品类型名称

    public Long getId() {
        return id;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
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

    public String getGiftPic() {
        return giftPic;
    }

    public void setGiftPic(String giftPic) {
        this.giftPic = giftPic;
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

    public String getGiftStatusName() {
        return giftStatusName;
    }

    public void setGiftStatusName(String giftStatusName) {
        this.giftStatusName = giftStatusName;
    }

    public Long getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Long giftAmount) {
        this.giftAmount = giftAmount;
    }

    public Long getGiftSend() {
        return giftSend;
    }

    public void setGiftSend(Long giftSend) {
        this.giftSend = giftSend;
    }

    public Long getGiftValid() {
        return giftValid;
    }

    public void setGiftValid(Long giftValid) {
        this.giftValid = giftValid;
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

    public Long getGiftCnt() {
        return giftCnt;
    }

    public void setGiftCnt(Long giftCnt) {
        this.giftCnt = giftCnt;
    }

    public Long getEveryTimeCnt() {
        return everyTimeCnt;
    }

    public void setEveryTimeCnt(Long everyTimeCnt) {
        this.everyTimeCnt = everyTimeCnt;
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
