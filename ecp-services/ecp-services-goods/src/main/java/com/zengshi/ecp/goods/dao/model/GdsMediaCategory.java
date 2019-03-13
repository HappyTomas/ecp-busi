package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsMediaCategory implements Serializable {
    private String catgCode;

    private String catgName;

    private Short catgLevel;

    private Integer sortNo;

    private String catgParent;

    private String catgUrl;

    private Long shopId;

    private String ifLeaf;

    private String mediaId;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

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

    public String getCatgUrl() {
        return catgUrl;
    }

    public void setCatgUrl(String catgUrl) {
        this.catgUrl = catgUrl == null ? null : catgUrl.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getIfLeaf() {
        return ifLeaf;
    }

    public void setIfLeaf(String ifLeaf) {
        this.ifLeaf = ifLeaf == null ? null : ifLeaf.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        sb.append(", catgCode=").append(catgCode);
        sb.append(", catgName=").append(catgName);
        sb.append(", catgLevel=").append(catgLevel);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", catgParent=").append(catgParent);
        sb.append(", catgUrl=").append(catgUrl);
        sb.append(", shopId=").append(shopId);
        sb.append(", ifLeaf=").append(ifLeaf);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
