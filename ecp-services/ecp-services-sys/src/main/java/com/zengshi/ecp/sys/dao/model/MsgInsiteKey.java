package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;

public class MsgInsiteKey implements Serializable {
    private Long staffId;

    private Long msgInfoId;

    private static final long serialVersionUID = 1L;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getMsgInfoId() {
        return msgInfoId;
    }

    public void setMsgInfoId(Long msgInfoId) {
        this.msgInfoId = msgInfoId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffId=").append(staffId);
        sb.append(", msgInfoId=").append(msgInfoId);
        sb.append("]");
        return sb.toString();
    }
}
