package com.zengshi.ecp.demo.dao.model;

import java.io.Serializable;

public class DemoLog implements Serializable {
    private Long logId;

    private String logInfo;

    private String dbCode;

    private static final long serialVersionUID = 1L;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo == null ? null : logInfo.trim();
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode == null ? null : dbCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", logInfo=").append(logInfo);
        sb.append(", dbCode=").append(dbCode);
        sb.append("]");
        return sb.toString();
    }
}
