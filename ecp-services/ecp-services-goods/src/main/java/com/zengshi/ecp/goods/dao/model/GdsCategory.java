package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class GdsCategory implements Serializable {
    private String catgCode;

    private String catgName;

    private Short catgLevel;

    private String catgType;

    private Integer sortNo;

    private String catgParent;

    private Long catlogId;

    private String ifEntityCode;

    private String ifLeaf;

    private String catgUrl;

    private Long catgTypeUnit;

    private Long minPrice;

    private Long maxPrice;

    private Long shopId;

    private String mediaUuid;

    private String status;

    private String ifShow;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String ifAbleEdit;

    private BigDecimal commission;

    private static final long serialVersionUID = 1L;

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName == null ? null : catgName.trim();
    }

    public Short getCatgLevel() {
        return catgLevel;
    }

    public void setCatgLevel(Short catgLevel) {
        this.catgLevel = catgLevel;
    }

    public String getCatgType() {
        return catgType;
    }

    public void setCatgType(String catgType) {
        this.catgType = catgType == null ? null : catgType.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCatgParent() {
        return catgParent;
    }

    public void setCatgParent(String catgParent) {
        this.catgParent = catgParent == null ? null : catgParent.trim();
    }

    public Long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }

    public String getIfEntityCode() {
        return ifEntityCode;
    }

    public void setIfEntityCode(String ifEntityCode) {
        this.ifEntityCode = ifEntityCode == null ? null : ifEntityCode.trim();
    }

    public String getIfLeaf() {
        return ifLeaf;
    }

    public void setIfLeaf(String ifLeaf) {
        this.ifLeaf = ifLeaf == null ? null : ifLeaf.trim();
    }

    public String getCatgUrl() {
        return catgUrl;
    }

    public void setCatgUrl(String catgUrl) {
        this.catgUrl = catgUrl == null ? null : catgUrl.trim();
    }

    public Long getCatgTypeUnit() {
        return catgTypeUnit;
    }

    public void setCatgTypeUnit(Long catgTypeUnit) {
        this.catgTypeUnit = catgTypeUnit;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(String mediaUuid) {
        this.mediaUuid = mediaUuid == null ? null : mediaUuid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow == null ? null : ifShow.trim();
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

    public String getIfAbleEdit() {
        return ifAbleEdit;
    }

    public void setIfAbleEdit(String ifAbleEdit) {
        this.ifAbleEdit = ifAbleEdit == null ? null : ifAbleEdit.trim();
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catgCode=").append(catgCode);
        sb.append(", catgName=").append(catgName);
        sb.append(", catgLevel=").append(catgLevel);
        sb.append(", catgType=").append(catgType);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", catgParent=").append(catgParent);
        sb.append(", catlogId=").append(catlogId);
        sb.append(", ifEntityCode=").append(ifEntityCode);
        sb.append(", ifLeaf=").append(ifLeaf);
        sb.append(", catgUrl=").append(catgUrl);
        sb.append(", catgTypeUnit=").append(catgTypeUnit);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append(", shopId=").append(shopId);
        sb.append(", mediaUuid=").append(mediaUuid);
        sb.append(", status=").append(status);
        sb.append(", ifShow=").append(ifShow);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", ifAbleEdit=").append(ifAbleEdit);
        sb.append(", commission=").append(commission);
        sb.append("]");
        return sb.toString();
    }
}
