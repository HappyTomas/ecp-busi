package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsVerifyShopIdx implements Serializable {
    private Long shopId;

    private Long gdsId;

    private Long skuId;

    private String status;

    private String operateType;

    private Long operateStaff;

    private Timestamp operateTime;

    private String verifyStatus;

    private String verifyOption;

    private Timestamp verifyTime;

    private Long verifyStaff;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String gdsName;

    private String catgCode;

    private String isbn;

    private Long gdsTypeId;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public Long getOperateStaff() {
        return operateStaff;
    }

    public void setOperateStaff(Long operateStaff) {
        this.operateStaff = operateStaff;
    }

    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus == null ? null : verifyStatus.trim();
    }

    public String getVerifyOption() {
        return verifyOption;
    }

    public void setVerifyOption(String verifyOption) {
        this.verifyOption = verifyOption == null ? null : verifyOption.trim();
    }

    public Timestamp getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Timestamp verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Long getVerifyStaff() {
        return verifyStaff;
    }

    public void setVerifyStaff(Long verifyStaff) {
        this.verifyStaff = verifyStaff;
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

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Long getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(Long gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
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
        sb.append(", status=").append(status);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateStaff=").append(operateStaff);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", verifyOption=").append(verifyOption);
        sb.append(", verifyTime=").append(verifyTime);
        sb.append(", verifyStaff=").append(verifyStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", isbn=").append(isbn);
        sb.append(", gdsTypeId=").append(gdsTypeId);
        sb.append("]");
        return sb.toString();
    }
}
