package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class ShopVipLevelKey implements Serializable {
    private String vipLevelCode;

    private Long id;

    private static final long serialVersionUID = 1L;

    public String getVipLevelCode() {
        return vipLevelCode;
    }

    public void setVipLevelCode(String vipLevelCode) {
        this.vipLevelCode = vipLevelCode == null ? null : vipLevelCode.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vipLevelCode=").append(vipLevelCode);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}