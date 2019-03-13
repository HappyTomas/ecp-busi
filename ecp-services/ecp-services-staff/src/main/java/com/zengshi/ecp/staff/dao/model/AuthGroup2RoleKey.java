package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthGroup2RoleKey implements Serializable {
    private Long groupId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}