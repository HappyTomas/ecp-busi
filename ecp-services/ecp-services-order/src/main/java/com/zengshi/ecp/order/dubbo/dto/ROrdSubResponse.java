package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdSubResponse extends BaseResponseDTO {
    private String id;

    private String orderId;

    private String status;

    private String orderCode;

    private Long categoryId;

    private Long gdsId;

    private String gdsName;

    private Long skuId;

    private Long skuHisId;

    private String skuInfo;

    private String groupType;

    private String groupDetail;

    private Long orderAmount;

    private Long agentPrice;

    private Long buyPrice;

    private Long orderMoney;

    private Long shareMoney;

    private Timestamp orderTime;

    private Long staffId;

    private Long shopId;

    private Long proxyShopId;

    private String deliverStatus;

    private Long remainAmount;

    private Long deliverAmount;

    private String remark;

    private Timestamp createTime;

    private String createStaff;

    private String updateStaff;

    private Timestamp updateTime;

    private Long repCode;

    private Long stockId;
    
    /** 
     * standardPrice:基准价. 
     * @since JDK 1.6 
     */ 
    private Long standardPrice;
    
    private String prnFlag;
    
    private Long hasBackNum;
    

    public Long getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Long standardPrice) {
        this.standardPrice = standardPrice;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Long getSkuHisId() {
        return skuHisId;
    }

    public void setSkuHisId(Long skuHisId) {
        this.skuHisId = skuHisId;
    }

    public String getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(String skuInfo) {
        this.skuInfo = skuInfo == null ? null : skuInfo.trim();
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public String getGroupDetail() {
        return groupDetail;
    }

    public void setGroupDetail(String groupDetail) {
        this.groupDetail = groupDetail == null ? null : groupDetail.trim();
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(Long agentPrice) {
        this.agentPrice = agentPrice;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Long getShareMoney() {
        return shareMoney;
    }

    public void setShareMoney(Long shareMoney) {
        this.shareMoney = shareMoney;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
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

    public Long getProxyShopId() {
        return proxyShopId;
    }

    public void setProxyShopId(Long proxyShopId) {
        this.proxyShopId = proxyShopId;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus == null ? null : deliverStatus.trim();
    }

    public Long getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Long remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Long getDeliverAmount() {
        return deliverAmount;
    }

    public void setDeliverAmount(Long deliverAmount) {
        this.deliverAmount = deliverAmount;
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

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
    
    public String getPrnFlag() {
        return prnFlag;
    }

    public void setPrnFlag(String prnFlag) {
        this.prnFlag = prnFlag;
    }

    public Long getHasBackNum() {
        return hasBackNum;
    }

    public void setHasBackNum(Long hasBackNum) {
        this.hasBackNum = hasBackNum;
    }
}