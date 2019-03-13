package com.zengshi.ecp.busi.seller.prom.myprom.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class PromQueryVO extends EcpBasePageReqVO implements Serializable{

	private static final long serialVersionUID = 1L;

	//生效开始
	 @JSONField(format="yyyy-MM-dd HH:mm:ss")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    //生效截止
	 @JSONField(format="yyyy-MM-dd HH:mm:ss")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    //展示开始
	 @JSONField(format="yyyy-MM-dd HH:mm:ss")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date showStartTime;

    //展示截止
	 @JSONField(format="yyyy-MM-dd HH:mm:ss")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date showEndTime;
    
    //促销类型
    private String promTypeCode;
    
    //促销名称
    private String promTheme;
    
    //店铺ID
    private Long shopId;


	//促销状态，00 草稿10生效 20失效 30草稿转删除 40 待审核 50 审核不通过 60系统删除
    private String status;
    
    private Long siteId;//站点
    
    private String ifFreePost;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(Date showStartTime) {
		this.showStartTime = showStartTime;
	}

	public Date getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(Date showEndTime) {
		this.showEndTime = showEndTime;
	}

	public String getPromTypeCode() {
		return promTypeCode;
	}

	public void setPromTypeCode(String promTypeCode) {
		this.promTypeCode = promTypeCode;
	}

	public String getPromTheme() {
		return promTheme;
	}

	public void setPromTheme(String promTheme) {
		this.promTheme = promTheme;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

	public String getIfFreePost() {
		return ifFreePost;
	}

	public void setIfFreePost(String ifFreePost) {
		this.ifFreePost = ifFreePost;
	}

}
