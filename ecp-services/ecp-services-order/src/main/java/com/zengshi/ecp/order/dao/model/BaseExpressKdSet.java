package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class BaseExpressKdSet implements Serializable {
    private Long id;

    private Long expressId;

    private Long kdExpressId;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public Long getKdExpressId() {
        return kdExpressId;
    }

    public void setKdExpressId(Long kdExpressId) {
        this.kdExpressId = kdExpressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", expressId=").append(expressId);
        sb.append(", kdExpressId=").append(kdExpressId);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}