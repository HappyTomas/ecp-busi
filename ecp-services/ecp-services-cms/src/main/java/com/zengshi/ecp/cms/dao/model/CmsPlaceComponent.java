package com.zengshi.ecp.cms.dao.model;

import java.io.Serializable;

public class CmsPlaceComponent implements Serializable {
    private Long id;

    private Long placeId;

    private Long componentId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", placeId=").append(placeId);
        sb.append(", componentId=").append(componentId);
        sb.append("]");
        return sb.toString();
    }
}
