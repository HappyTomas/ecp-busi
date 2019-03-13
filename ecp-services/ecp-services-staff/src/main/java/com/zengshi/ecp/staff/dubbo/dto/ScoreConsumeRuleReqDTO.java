package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ScoreConsumeRuleReqDTO extends BaseInfo {
    private Long id;

    private String consumeType;

    private String status;

    private Long moneyNeed;
    
    private Long moneyNeedFrom;
    
    private Long moneyNeedTo;
    
    private Long scoreNeed;
    
    private Long scoreNeedFrom;
    
    private Long scoreNeedTo;

    private Long skuId;

    private String skuName;

    private Long gdsId;

    private String gdsName;

    private Long shopId;

    private Long sortId;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String remark;
    
    

    public Long getMoneyNeedFrom() {
        return moneyNeedFrom;
    }

    public void setMoneyNeedFrom(Long moneyNeedFrom) {
        this.moneyNeedFrom = moneyNeedFrom;
    }

    public Long getMoneyNeedTo() {
        return moneyNeedTo;
    }

    public void setMoneyNeedTo(Long moneyNeedTo) {
        this.moneyNeedTo = moneyNeedTo;
    }

    public Long getScoreNeedFrom() {
        return scoreNeedFrom;
    }

    public void setScoreNeedFrom(Long scoreNeedFrom) {
        this.scoreNeedFrom = scoreNeedFrom;
    }

    public Long getScoreNeedTo() {
        return scoreNeedTo;
    }

    public void setScoreNeedTo(Long scoreNeedTo) {
        this.scoreNeedTo = scoreNeedTo;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType == null ? null : consumeType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getMoneyNeed() {
        return moneyNeed;
    }

    public void setMoneyNeed(Long moneyNeed) {
        this.moneyNeed = moneyNeed;
    }

    public Long getScoreNeed() {
        return scoreNeed;
    }

    public void setScoreNeed(Long scoreNeed) {
        this.scoreNeed = scoreNeed;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", consumeType=").append(consumeType);
        sb.append(", status=").append(status);
        sb.append(", moneyNeed=").append(moneyNeed);
        sb.append(", scoreNeed=").append(scoreNeed);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuName=").append(skuName);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", shopId=").append(shopId);
        sb.append(", sortId=").append(sortId);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", scoreNeedFrom=").append(scoreNeedFrom);
        sb.append(", scoreNeedTo=").append(scoreNeedTo);
        sb.append(", moneyNeedFrom=").append(moneyNeedFrom);
        sb.append(", moneyNeedTo=").append(moneyNeedTo);
        sb.append("]");
        return sb.toString();
    }
}