package com.zengshi.ecp.search.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SecOperLog implements Serializable {
    private Long id;

    private Long configId;

    private Long objectId;

    private Long fieldId;

    private Timestamp startTime;

    private Timestamp nowTime;

    private Long datacount;

    private Long failurecount;

    private String failureinfo;

    private Integer seconds;

    private Short tps;

    private String error;

    private String operType;

    private String operConfigType;

    private String operObjectType;

    private String operFieldType;

    private String operCollType;

    private String operIndexType;

    private String args;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getNowTime() {
        return nowTime;
    }

    public void setNowTime(Timestamp nowTime) {
        this.nowTime = nowTime;
    }

    public Long getDatacount() {
        return datacount;
    }

    public void setDatacount(Long datacount) {
        this.datacount = datacount;
    }

    public Long getFailurecount() {
        return failurecount;
    }

    public void setFailurecount(Long failurecount) {
        this.failurecount = failurecount;
    }

    public String getFailureinfo() {
        return failureinfo;
    }

    public void setFailureinfo(String failureinfo) {
        this.failureinfo = failureinfo == null ? null : failureinfo.trim();
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Short getTps() {
        return tps;
    }

    public void setTps(Short tps) {
        this.tps = tps;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public String getOperConfigType() {
        return operConfigType;
    }

    public void setOperConfigType(String operConfigType) {
        this.operConfigType = operConfigType == null ? null : operConfigType.trim();
    }

    public String getOperObjectType() {
        return operObjectType;
    }

    public void setOperObjectType(String operObjectType) {
        this.operObjectType = operObjectType == null ? null : operObjectType.trim();
    }

    public String getOperFieldType() {
        return operFieldType;
    }

    public void setOperFieldType(String operFieldType) {
        this.operFieldType = operFieldType == null ? null : operFieldType.trim();
    }

    public String getOperCollType() {
        return operCollType;
    }

    public void setOperCollType(String operCollType) {
        this.operCollType = operCollType == null ? null : operCollType.trim();
    }

    public String getOperIndexType() {
        return operIndexType;
    }

    public void setOperIndexType(String operIndexType) {
        this.operIndexType = operIndexType == null ? null : operIndexType.trim();
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", configId=").append(configId);
        sb.append(", objectId=").append(objectId);
        sb.append(", fieldId=").append(fieldId);
        sb.append(", startTime=").append(startTime);
        sb.append(", nowTime=").append(nowTime);
        sb.append(", datacount=").append(datacount);
        sb.append(", failurecount=").append(failurecount);
        sb.append(", failureinfo=").append(failureinfo);
        sb.append(", seconds=").append(seconds);
        sb.append(", tps=").append(tps);
        sb.append(", error=").append(error);
        sb.append(", operType=").append(operType);
        sb.append(", operConfigType=").append(operConfigType);
        sb.append(", operObjectType=").append(operObjectType);
        sb.append(", operFieldType=").append(operFieldType);
        sb.append(", operCollType=").append(operCollType);
        sb.append(", operIndexType=").append(operIndexType);
        sb.append(", args=").append(args);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}