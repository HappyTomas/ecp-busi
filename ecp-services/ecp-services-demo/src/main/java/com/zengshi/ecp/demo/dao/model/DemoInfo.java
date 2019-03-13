package com.zengshi.ecp.demo.dao.model;

import java.io.Serializable;

public class DemoInfo implements Serializable {
    private Long id;

    private String demoInfo;

    private Long value;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemoInfo() {
        return demoInfo;
    }

    public void setDemoInfo(String demoInfo) {
        this.demoInfo = demoInfo == null ? null : demoInfo.trim();
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", demoInfo=").append(demoInfo);
        sb.append(", value=").append(value);
        sb.append("]");
        return sb.toString();
    }
}
