package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AuthPrivilege2MenuKey implements Serializable {
    private Long privilegeId;

    private Long menuId;

    private static final long serialVersionUID = 1L;

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", privilegeId=").append(privilegeId);
        sb.append(", menuId=").append(menuId);
        sb.append("]");
        return sb.toString();
    }
}