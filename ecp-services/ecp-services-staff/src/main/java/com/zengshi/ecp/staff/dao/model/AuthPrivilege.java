package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthPrivilege implements Serializable {
    private Long id;

    private String roleAdmin;

    private String privilegeType;

    private String sysCode;

    private String status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(String roleAdmin) {
        this.roleAdmin = roleAdmin == null ? null : roleAdmin.trim();
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType == null ? null : privilegeType.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleAdmin=").append(roleAdmin);
        sb.append(", privilegeType=").append(privilegeType);
        sb.append(", sysCode=").append(sysCode);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}