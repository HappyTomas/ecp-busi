package com.zengshi.ecp.coupon.dao.model;

import java.io.Serializable;

public class CoupTypeLogKey implements Serializable {
    private Long typeId;

    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
        sb.append(", typeId=").append(typeId);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}
