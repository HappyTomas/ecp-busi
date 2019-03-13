package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class PromType implements Serializable {
    private String promTypeCode;

    private String promTypeName;

    private String nameShort;

    private String promTypeDesc;

    private String promImg;

    private String promClass;

    private String ifShow;

    private String ifComposit;

    private String status;

    private String jsonBeanId;

    private String serviceId;

    private BigDecimal sortNo;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode == null ? null : promTypeCode.trim();
    }

    public String getPromTypeName() {
        return promTypeName;
    }

    public void setPromTypeName(String promTypeName) {
        this.promTypeName = promTypeName == null ? null : promTypeName.trim();
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort == null ? null : nameShort.trim();
    }

    public String getPromTypeDesc() {
        return promTypeDesc;
    }

    public void setPromTypeDesc(String promTypeDesc) {
        this.promTypeDesc = promTypeDesc == null ? null : promTypeDesc.trim();
    }

    public String getPromImg() {
        return promImg;
    }

    public void setPromImg(String promImg) {
        this.promImg = promImg == null ? null : promImg.trim();
    }

    public String getPromClass() {
        return promClass;
    }

    public void setPromClass(String promClass) {
        this.promClass = promClass == null ? null : promClass.trim();
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow == null ? null : ifShow.trim();
    }

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit == null ? null : ifComposit.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getJsonBeanId() {
        return jsonBeanId;
    }

    public void setJsonBeanId(String jsonBeanId) {
        this.jsonBeanId = jsonBeanId == null ? null : jsonBeanId.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
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
        sb.append(", promTypeCode=").append(promTypeCode);
        sb.append(", promTypeName=").append(promTypeName);
        sb.append(", nameShort=").append(nameShort);
        sb.append(", promTypeDesc=").append(promTypeDesc);
        sb.append(", promImg=").append(promImg);
        sb.append(", promClass=").append(promClass);
        sb.append(", ifShow=").append(ifShow);
        sb.append(", ifComposit=").append(ifComposit);
        sb.append(", status=").append(status);
        sb.append(", jsonBeanId=").append(jsonBeanId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}