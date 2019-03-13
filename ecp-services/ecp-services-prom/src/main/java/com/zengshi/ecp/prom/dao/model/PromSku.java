package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromSku implements Serializable {
    private Long id;

    private Long siteId;

    private Long promId;

    private Long shopId;

    private String joinType;

    private String catgCode;

    private Long gdsId;

    private Long skuId;

    private Long priority;

    private String promClass;

    private String matchType;

    private String status;

    private Timestamp startTime;

    private Timestamp endTime;

    private String ifShow;

    private Long promCnt;

    private String ifComposit;

    private Timestamp showStartTime;

    private Timestamp showEndTime;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String ifValid;

    private String priceType;

    private Long priceValue;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType == null ? null : joinType.trim();
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
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

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getPromClass() {
        return promClass;
    }

    public void setPromClass(String promClass) {
        this.promClass = promClass == null ? null : promClass.trim();
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow == null ? null : ifShow.trim();
    }

    public Long getPromCnt() {
        return promCnt;
    }

    public void setPromCnt(Long promCnt) {
        this.promCnt = promCnt;
    }

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit == null ? null : ifComposit.trim();
    }

    public Timestamp getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Timestamp showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Timestamp getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(Timestamp showEndTime) {
        this.showEndTime = showEndTime;
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

    public String getIfValid() {
        return ifValid;
    }

    public void setIfValid(String ifValid) {
        this.ifValid = ifValid == null ? null : ifValid.trim();
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType == null ? null : priceType.trim();
    }

    public Long getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Long priceValue) {
        this.priceValue = priceValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", siteId=").append(siteId);
        sb.append(", promId=").append(promId);
        sb.append(", shopId=").append(shopId);
        sb.append(", joinType=").append(joinType);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", priority=").append(priority);
        sb.append(", promClass=").append(promClass);
        sb.append(", matchType=").append(matchType);
        sb.append(", status=").append(status);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", ifShow=").append(ifShow);
        sb.append(", promCnt=").append(promCnt);
        sb.append(", ifComposit=").append(ifComposit);
        sb.append(", showStartTime=").append(showStartTime);
        sb.append(", showEndTime=").append(showEndTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", ifValid=").append(ifValid);
        sb.append(", priceType=").append(priceType);
        sb.append(", priceValue=").append(priceValue);
        sb.append("]");
        return sb.toString();
    }
}