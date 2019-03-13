package com.zengshi.ecp.search.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SecFieldProcessorRespDTO extends BaseResponseDTO {

    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 7119820489717730812L;
    
    private String processorName;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName == null ? null : processorName.trim();
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
        sb.append(", processorName=").append(processorName);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

}

