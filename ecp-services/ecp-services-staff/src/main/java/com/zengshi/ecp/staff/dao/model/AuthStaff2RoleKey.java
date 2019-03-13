package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthStaff2RoleKey implements Serializable {
    private Long staffId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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
        sb.append(", staffId=").append(staffId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}