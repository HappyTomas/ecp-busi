package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdExpressReqLog implements Serializable {
    private Long id;

    private String orderId;

    private Long shopId;

    private String expressCode;

    private String expressNo;

    private String expressInterface;

    private Long staffId;

    private String result;

    private Timestamp createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getExpressInterface() {
        return expressInterface;
    }

    public void setExpressInterface(String expressInterface) {
        this.expressInterface = expressInterface == null ? null : expressInterface.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", shopId=").append(shopId);
        sb.append(", expressCode=").append(expressCode);
        sb.append(", expressNo=").append(expressNo);
        sb.append(", expressInterface=").append(expressInterface);
        sb.append(", staffId=").append(staffId);
        sb.append(", result=").append(result);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}