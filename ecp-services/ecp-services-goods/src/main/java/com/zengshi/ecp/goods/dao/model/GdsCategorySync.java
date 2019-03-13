package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsCategorySync implements Serializable {
    private Long id;

    private String catgCode;

    private String catgName;

    private Integer sortNo;

    private String catgParent;

    private String catgParentName;

    private String srcSystem;

    private String status;

    private Timestamp operTime;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String mapCatgCode;

    private String catgType;

    private Long shopId;

    private Long shopAuthId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCatgParentName() {
        return catgParentName;
    }

    public void setCatgParentName(String catgParentName) {
        this.catgParentName = catgParentName == null ? null : catgParentName.trim();
    }

    public String getSrcSystem() {
        return srcSystem;
    }

    public void setSrcSystem(String srcSystem) {
        this.srcSystem = srcSystem == null ? null : srcSystem.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
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

    public String getMapCatgCode() {
        return mapCatgCode;
    }

    public void setMapCatgCode(String mapCatgCode) {
        this.mapCatgCode = mapCatgCode == null ? null : mapCatgCode.trim();
    }

    public String getCatgType() {
        return catgType;
    }

    public void setCatgType(String catgType) {
        this.catgType = catgType == null ? null : catgType.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopAuthId() {
        return shopAuthId;
    }

    public void setShopAuthId(Long shopAuthId) {
        this.shopAuthId = shopAuthId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", catgName=").append(catgName);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", catgParent=").append(catgParent);
        sb.append(", catgParentName=").append(catgParentName);
        sb.append(", srcSystem=").append(srcSystem);
        sb.append(", status=").append(status);
        sb.append(", operTime=").append(operTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", mapCatgCode=").append(mapCatgCode);
        sb.append(", catgType=").append(catgType);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopAuthId=").append(shopAuthId);
        sb.append("]");
        return sb.toString();
    }
}
