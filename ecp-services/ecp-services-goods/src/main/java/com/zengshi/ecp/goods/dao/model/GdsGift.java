package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsGift implements Serializable {
    private Long id;

    private Long gdsId;

    private Long skuId;

    private Long shopId;

    private String giftPic;

    private String giftName;

    private String giftDesc;

    private Long giftValue;

    private String giftStatus;

    private Long giftAmount;

    private Long giftSend;

    private Long giftValid;

    private Timestamp startTime;

    private Timestamp endTime;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String giftType;

    private static final long serialVersionUID = 1L;

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

    public String getGiftPic() {
        return giftPic;
    }

    public void setGiftPic(String giftPic) {
        this.giftPic = giftPic == null ? null : giftPic.trim();
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName == null ? null : giftName.trim();
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc == null ? null : giftDesc.trim();
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
        this.giftStatus = giftStatus == null ? null : giftStatus.trim();
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

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType == null ? null : giftType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", shopId=").append(shopId);
        sb.append(", giftPic=").append(giftPic);
        sb.append(", giftName=").append(giftName);
        sb.append(", giftDesc=").append(giftDesc);
        sb.append(", giftValue=").append(giftValue);
        sb.append(", giftStatus=").append(giftStatus);
        sb.append(", giftAmount=").append(giftAmount);
        sb.append(", giftSend=").append(giftSend);
        sb.append(", giftValid=").append(giftValid);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", giftType=").append(giftType);
        sb.append("]");
        return sb.toString();
    }
}
