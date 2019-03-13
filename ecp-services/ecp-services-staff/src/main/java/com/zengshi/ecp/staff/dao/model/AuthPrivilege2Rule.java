package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthPrivilege2Rule extends AuthPrivilege2RuleKey implements Serializable {
    private String sysCode;

    private static final long serialVersionUID = 1L;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sysCode=").append(sysCode);
        sb.append("]");
        return sb.toString();
    }
}