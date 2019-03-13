package com.zengshi.ecp.staff.dubbo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ShopVipLevelReqDTO extends BaseInfo {
    private String vipLevelCode;

    private Long id;

    private String vipLevelName;

    private Long shopId;

    private BigDecimal orderPay;

    private BigDecimal tradesNum;

    private String status;

    private String remark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

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

    public String getVipLevelName() {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName) {
        this.vipLevelName = vipLevelName == null ? null : vipLevelName.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(BigDecimal orderPay) {
        this.orderPay = orderPay;
    }

    public BigDecimal getTradesNum() {
        return tradesNum;
    }

    public void setTradesNum(BigDecimal tradesNum) {
        this.tradesNum = tradesNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vipLevelCode=").append(vipLevelCode);
        sb.append(", id=").append(id);
        sb.append(", vipLevelName=").append(vipLevelName);
        sb.append(", shopId=").append(shopId);
        sb.append(", orderPay=").append(orderPay);
        sb.append(", tradesNum=").append(tradesNum);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}