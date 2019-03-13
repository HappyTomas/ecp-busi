package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class ShopStaff2GroupKey implements Serializable {
    private Long groupId;

    private Long staffId;

    private static final long serialVersionUID = 1L;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", staffId=").append(staffId);
        sb.append("]");
        return sb.toString();
    }
}