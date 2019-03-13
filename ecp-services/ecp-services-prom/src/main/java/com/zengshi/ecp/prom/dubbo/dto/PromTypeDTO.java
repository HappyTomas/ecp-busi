package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromTypeDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private String promTypeCode;

    private String promTypeName;

    private String nameShort;

    private String promTypeDesc;

    private String promImg;

    private String promClass;

    private String ifShow;

    private String status;

    private String jsonBeanId;

    private String serviceId;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;
    
    private String ifComposit;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit;
    }
}