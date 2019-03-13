package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdBackGds implements Serializable {
    private Long id;

    private Long backId;

    private String orderId;

    private String orderSubId;

    private String status;

    private Long gdsId;

    private String gdsName;

    private Long skuId;

    private Long num;

    private Long staffId;

    private Long shopId;

    private Long backMoney;

    private Long backScore;

    private Long backAccount;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId == null ? null : orderSubId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Long backMoney) {
        this.backMoney = backMoney;
    }

    public Long getBackScore() {
        return backScore;
    }

    public void setBackScore(Long backScore) {
        this.backScore = backScore;
    }

    public Long getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(Long backAccount) {
        this.backAccount = backAccount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", backId=").append(backId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSubId=").append(orderSubId);
        sb.append(", status=").append(status);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", skuId=").append(skuId);
        sb.append(", num=").append(num);
        sb.append(", staffId=").append(staffId);
        sb.append(", shopId=").append(shopId);
        sb.append(", backMoney=").append(backMoney);
        sb.append(", backScore=").append(backScore);
        sb.append(", backAccount=").append(backAccount);
        sb.append("]");
        return sb.toString();
    }
}