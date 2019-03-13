package com.zengshi.ecp.search.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class SecConfigPlanReqDTO extends BaseInfo {

    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = -2750561474351917115L;
    
    private Long id;

    private Long configId;

    private String planName;

    private String planComment;

    private String planType;

    private String planIfClean;

    private String planDispatcherrule;

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

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getPlanComment() {
        return planComment;
    }

    public void setPlanComment(String planComment) {
        this.planComment = planComment == null ? null : planComment.trim();
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType == null ? null : planType.trim();
    }

    public String getPlanIfClean() {
        return planIfClean;
    }

    public void setPlanIfClean(String planIfClean) {
        this.planIfClean = planIfClean == null ? null : planIfClean.trim();
    }

    public String getPlanDispatcherrule() {
        return planDispatcherrule;
    }

    public void setPlanDispatcherrule(String planDispatcherrule) {
        this.planDispatcherrule = planDispatcherrule == null ? null : planDispatcherrule.trim();
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
        sb.append(", planName=").append(planName);
        sb.append(", planComment=").append(planComment);
        sb.append(", planType=").append(planType);
        sb.append(", planIfClean=").append(planIfClean);
        sb.append(", planDispatcherrule=").append(planDispatcherrule);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

}

