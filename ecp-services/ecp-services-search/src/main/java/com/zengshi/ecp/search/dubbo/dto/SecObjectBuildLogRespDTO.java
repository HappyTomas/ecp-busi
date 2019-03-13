package com.zengshi.ecp.search.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SecObjectBuildLogRespDTO extends BaseResponseDTO{
    
private static final long serialVersionUID = 1L;
    
    private Long id;

    private Long configId;

    private Long objectId;

    private Timestamp startTime;

    private Timestamp nowTime;

    private Long datacount;

    private Long failurecount;

    private String failureinfo;

    private Integer seconds;

    private Short tps;

    private String error;

    private String indexStatus;
    
    private String args;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

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

    public String getIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(String indexStatus) {
        this.indexStatus = indexStatus == null ? null : indexStatus.trim();
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
        sb.append(", startTime=").append(startTime);
        sb.append(", nowTime=").append(nowTime);
        sb.append(", datacount=").append(datacount);
        sb.append(", failurecount=").append(failurecount);
        sb.append(", failureinfo=").append(failureinfo);
        sb.append(", seconds=").append(seconds);
        sb.append(", tps=").append(tps);
        sb.append(", error=").append(error);
        sb.append(", indexStatus=").append(indexStatus);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

}

