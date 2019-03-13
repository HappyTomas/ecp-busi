package com.zengshi.ecp.busi.seller.prom.group.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class PromGroupVO extends EcpBasePageReqVO {
	
    private Long id;
    //判断编辑ID用的
    private Long eId;
    //促销主题
    @NotEmpty(message="{prom.promGroupVO.promTheme.null.error}")
    private String promTheme;
    //内容
    private String promContent;
    //状态
    private String status;
    //主题开始展示时间
    @NotNull(message="{prom.promGroupVO.showStartTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date showStartTime;
    //主题结束展示时间
    @NotNull(message="{prom.promGroupVO.showEndTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date showEndTime;
    //主题详情url 
    private String promUrl;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;
    //促销开始时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //促销结束时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //参与店铺促销数量
    private Long shopCnt;
    //促销主题判断是否是详情按钮
    private String type;
    private Long siteId;
    
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long geteId() {
		return eId;
	}

	public void seteId(Long eId) {
		this.eId = eId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getShopCnt() {
		return shopCnt;
	}

	public void setShopCnt(Long shopCnt) {
		this.shopCnt = shopCnt;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPromTheme() {
		return promTheme;
	}

	public void setPromTheme(String promTheme) {
		this.promTheme = promTheme;
	}

	public String getPromContent() {
		return promContent;
	}

	public void setPromContent(String promContent) {
		this.promContent = promContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPromUrl() {
		return promUrl;
	}

	public void setPromUrl(String promUrl) {
		this.promUrl = promUrl;
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
    
}
