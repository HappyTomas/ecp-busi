package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromSkuDTO extends BaseInfo {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long promId;

    private Long gdsId;

    private Long skuId;

    private Long priority;

    private Long shopId;

    private String promClass;
    
    private String promClassName;

    private String matchType;

    private String status;
    
    private String statusName;

    private Date startTime;

    private Date endTime;

    private String ifShow;

    private Long promCnt;

    private String priceType;//秒杀价类型
    
    private Long priceValue;//秒杀价
    
    private Date createTime;

    private Long createStaff;
    
    private Long siteId;//站点编码
    
    private String siteName;//站点名称
    
    private String joinType;

    private String catgCode;
    
    private String ifComposit;
    
    private Date showStartTime;

    private Date showEndTime;

    private String ifValid;
    
    private Date updateTime;

    private Long updateStaff;
    
    private Integer tableIndex;//分表下标
    
    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit;
    }

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
 
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPromClass() {
        return promClass;
    }

    public void setPromClass(String promClass) {
        this.promClass = promClass;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

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

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow;
    }

    public Long getPromCnt() {
        return promCnt;
    }

    public void setPromCnt(Long promCnt) {
        this.promCnt = promCnt;
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

    public String getPromClassName() {
        return promClassName;
    }

    public void setPromClassName(String promClassName) {
        this.promClassName = promClassName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    public String getIfValid() {
        return ifValid;
    }

    public void setIfValid(String ifValid) {
        this.ifValid = ifValid;
    }

    public Integer getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(Integer tableIndex) {
        this.tableIndex = tableIndex;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Long getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Long priceValue) {
        this.priceValue = priceValue;
    }
    
}