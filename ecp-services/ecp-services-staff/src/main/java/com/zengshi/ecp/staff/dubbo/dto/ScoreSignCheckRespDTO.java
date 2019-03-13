package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ScoreSignCheckRespDTO extends BaseResponseDTO {
    private Long staffId;

    private Timestamp signDateBegin;

    private Timestamp signDateEnd;

    private Long signCnt;

    private static final long serialVersionUID = 1L;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Timestamp getSignDateBegin() {
        return signDateBegin;
    }

    public void setSignDateBegin(Timestamp signDateBegin) {
        this.signDateBegin = signDateBegin;
    }

    public Timestamp getSignDateEnd() {
        return signDateEnd;
    }

    public void setSignDateEnd(Timestamp signDateEnd) {
        this.signDateEnd = signDateEnd;
    }

    public Long getSignCnt() {
        return signCnt;
    }

    public void setSignCnt(Long signCnt) {
        this.signCnt = signCnt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffId=").append(staffId);
        sb.append(", signDateBegin=").append(signDateBegin);
        sb.append(", signDateEnd=").append(signDateEnd);
        sb.append(", signCnt=").append(signCnt);
        sb.append("]");
        return sb.toString();
    }
}