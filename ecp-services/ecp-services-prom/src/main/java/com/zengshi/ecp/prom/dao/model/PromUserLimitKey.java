package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;

public class PromUserLimitKey implements Serializable {
    private Long staffId;

    private Long promId;

    private String limitType;

    private String limitTypeValue;

    private String optValue;

    private static final long serialVersionUID = 1L;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType == null ? null : limitType.trim();
    }

    public String getLimitTypeValue() {
        return limitTypeValue;
    }

    public void setLimitTypeValue(String limitTypeValue) {
        this.limitTypeValue = limitTypeValue == null ? null : limitTypeValue.trim();
    }

    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue == null ? null : optValue.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffId=").append(staffId);
        sb.append(", promId=").append(promId);
        sb.append(", limitType=").append(limitType);
        sb.append(", limitTypeValue=").append(limitTypeValue);
        sb.append(", optValue=").append(optValue);
        sb.append("]");
        return sb.toString();
    }
}