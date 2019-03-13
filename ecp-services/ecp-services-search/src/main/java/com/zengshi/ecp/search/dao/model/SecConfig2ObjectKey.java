package com.zengshi.ecp.search.dao.model;

import java.io.Serializable;

public class SecConfig2ObjectKey implements Serializable {
    private Long configId;

    private Long objectId;

    private static final long serialVersionUID = 1L;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", configId=").append(configId);
        sb.append(", objectId=").append(objectId);
        sb.append("]");
        return sb.toString();
    }
}