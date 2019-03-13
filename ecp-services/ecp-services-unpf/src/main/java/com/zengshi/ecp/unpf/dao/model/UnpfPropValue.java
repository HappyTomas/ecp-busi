package com.zengshi.ecp.unpf.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UnpfPropValue implements Serializable {
    private Long id;

    private String propVCode;

    private String propVName;

    private String propVSname;

    private String propVDesc;

    private Integer sortNo;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String catgCode;

    private String platType;

    private Long shopId;

    private Long shopAuthId;

    private Long propRelaId;

    private String propCode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropVCode() {
        return propVCode;
    }

    public void setPropVCode(String propVCode) {
        this.propVCode = propVCode == null ? null : propVCode.trim();
    }

    public String getPropVName() {
        return propVName;
    }

    public void setPropVName(String propVName) {
        this.propVName = propVName == null ? null : propVName.trim();
    }

    public String getPropVSname() {
        return propVSname;
    }

    public void setPropVSname(String propVSname) {
        this.propVSname = propVSname == null ? null : propVSname.trim();
    }

    public String getPropVDesc() {
        return propVDesc;
    }

    public void setPropVDesc(String propVDesc) {
        this.propVDesc = propVDesc == null ? null : propVDesc.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
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

    public Long getPropRelaId() {
        return propRelaId;
    }

    public void setPropRelaId(Long propRelaId) {
        this.propRelaId = propRelaId;
    }

    public String getPropCode() {
        return propCode;
    }

    public void setPropCode(String propCode) {
        this.propCode = propCode == null ? null : propCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", propVCode=").append(propVCode);
        sb.append(", propVName=").append(propVName);
        sb.append(", propVSname=").append(propVSname);
        sb.append(", propVDesc=").append(propVDesc);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", platType=").append(platType);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopAuthId=").append(shopAuthId);
        sb.append(", propRelaId=").append(propRelaId);
        sb.append(", propCode=").append(propCode);
        sb.append("]");
        return sb.toString();
    }
}