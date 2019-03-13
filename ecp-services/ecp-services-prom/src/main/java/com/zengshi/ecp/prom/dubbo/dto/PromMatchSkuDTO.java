package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromMatchSkuDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    private Long promId;

    private Long gdsId;

    private Long skuId;

    private Long price;

    private Long promCnt;

    private String status;

    private String matchType;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;
    
    private Long siteId;//站点编码
    
    private String siteName;//站点名称

    private Long shopId;//店铺编码

    private Date startTime;//开始时间

    private Date endTime;//截止时间

    private String promStatus;//促销状态
    
    private BigDecimal sortNum;//排序
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPromCnt() {
        return promCnt;
    }

    public void setPromCnt(Long promCnt) {
        this.promCnt = promCnt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public String getPromStatus() {
        return promStatus;
    }

    public void setPromStatus(String promStatus) {
        this.promStatus = promStatus;
    }

	public BigDecimal getSortNum() {
		return sortNum;
	}

	public void setSortNum(BigDecimal sortNum) {
		this.sortNum = sortNum;
	}

}