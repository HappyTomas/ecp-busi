package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthPrivilege2RuleKey implements Serializable {
    private Long privilegeId;

    private Long ruleId;

    private static final long serialVersionUID = 1L;

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", privilegeId=").append(privilegeId);
        sb.append(", ruleId=").append(ruleId);
        sb.append("]");
        return sb.toString();
    }
}