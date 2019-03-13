package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class GdsPriceSnapKey implements Serializable {
    private Long snapId;

    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
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
        sb.append(", snapId=").append(snapId);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}
