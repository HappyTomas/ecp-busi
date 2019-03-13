package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdEntityChange implements Serializable {
    private Long id;

    private Long shopId;

    private String fromType;

    private Long importNo;

    private String status;

    private String orderId;

    private String entityCodeBf;

    private String entityCodeAf;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType == null ? null : fromType.trim();
    }

    public Long getImportNo() {
        return importNo;
    }

    public void setImportNo(Long importNo) {
        this.importNo = importNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getEntityCodeBf() {
        return entityCodeBf;
    }

    public void setEntityCodeBf(String entityCodeBf) {
        this.entityCodeBf = entityCodeBf == null ? null : entityCodeBf.trim();
    }

    public String getEntityCodeAf() {
        return entityCodeAf;
    }

    public void setEntityCodeAf(String entityCodeAf) {
        this.entityCodeAf = entityCodeAf == null ? null : entityCodeAf.trim();
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
        sb.append(", shopId=").append(shopId);
        sb.append(", fromType=").append(fromType);
        sb.append(", importNo=").append(importNo);
        sb.append(", status=").append(status);
        sb.append(", orderId=").append(orderId);
        sb.append(", entityCodeBf=").append(entityCodeBf);
        sb.append(", entityCodeAf=").append(entityCodeAf);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}