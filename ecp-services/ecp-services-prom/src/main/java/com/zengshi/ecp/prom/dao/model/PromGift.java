package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromGift extends PromGiftKey implements Serializable {
    private Long shopId;

    private Long gdsId;

    private Long skuId;

    private Long presentAllCnt;

    private Long everyTimeCnt;

    private Long presentCnt;

    private Long remaindCnt;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Long getPresentAllCnt() {
        return presentAllCnt;
    }

    public void setPresentAllCnt(Long presentAllCnt) {
        this.presentAllCnt = presentAllCnt;
    }

    public Long getEveryTimeCnt() {
        return everyTimeCnt;
    }

    public void setEveryTimeCnt(Long everyTimeCnt) {
        this.everyTimeCnt = everyTimeCnt;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", presentAllCnt=").append(presentAllCnt);
        sb.append(", everyTimeCnt=").append(everyTimeCnt);
        sb.append(", presentCnt=").append(presentCnt);
        sb.append(", remaindCnt=").append(remaindCnt);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}