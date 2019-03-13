package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ScoreClearReqDTO extends BaseInfo {

    private Long staffId;
    
    private Timestamp deadLineTime;

   

    private static final long serialVersionUID = 1L;


    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
   
    public Timestamp getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(Timestamp deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffId=").append(staffId);
        sb.append(", deadLineTime=").append(deadLineTime);
        sb.append("]");
        return sb.toString();
    }
}