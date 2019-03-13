package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdSub implements Serializable {
    /**
     * 子订单编码，当有拆单行为时，填原订单的单号(拆单S+省份编码（2位）+YYMMDD+SEQ(8位)  -1  -2 -3 等)
S+省份编码（2位）+YYMMDD+SEQ(8位)
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private String orderCode;

    /**
     * 
     */
    private String categoryCode;

    /**
     * 商品编码
     */
    private Long gdsId;

    /**
     * 
     */
    private String gdsName;

    /**
     * 单品编号
     */
    private Long skuId;

    /**
     * 
     */
    private Long skuHisId;

    /**
     * 
     */
    private String skuInfo;

    /**
     * 组合类型，普通为01，组合搭配为02，特殊类比如卡包为03
     */
    private String groupType;

    /**
     * 
     */
    private String groupDetail;

    /**
     * 订购数量
     */
    private Long orderAmount;

    /**
     * 
     */
    private String priceType;

    /**
     * 提货价(单位分)，商品最原始价格
     */
    private Long basePrice;

    /**
     * 订购单价(单位分) 优惠后单价或者定金（团购定金 预售定金）
     */
    private Long buyPrice;

    /**
     * 订购总金额 =ORDER_PRICE X ORDER_AMOUNT
     */
    private Long orderMoney;

    /**
     * 优惠后的单价（参加单品优惠后的价格，如果没有优惠，就是提货价）。如果是团购，取的是团购活动中配置的团购价格（团购起始价），用于后面做二次支付的时候来抵减用，二次支付单价=优惠后单价-订购单价
     */
    private Long realMoney;

    /**
     * 订单生成时间
     */
    private Timestamp orderTime;

    /**
     * 
     */
    private Long staffId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 代发货SHOP_ID
     */
    private Long proxyShopId;

    /**
     * 发货状态(0 未发货 1 发货)
     */
    private String deliverStatus;

    /**
     * 
     */
    private Long remainAmount;

    /**
     * 已提货数量----针对二次支付使用
     */
    private Long deliverAmount;

    /**
     * 
     */
    private Long discountPrice;

    /**
     * 
     */
    private Long discountMoney;

    /**
     * 
     */
    private Long reduceMoney;

    /**
     * 
     */
    private Long score;

    /**
     * 
     */
    private Long orderSubScore;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private Long createStaff;

    /**
     * 修改人
     */
    private Long updateStaff;

    /**
     * 修改时间
     */
    private Timestamp updateTime;

    /**
     * 
     */
    private Long repCode;

    /**
     * 
     */
    private Long stockId;

    /**
     * 
     */
    private Long siteId;

    /**
     * 
     */
    private String sysType;

    /**
     * 
     */
    private Long gdsType;

    /**
     * 
     */
    private String evalFlag;

    /**
     * 
     */
    private Long scoreTypeId;

    /**
     * 
     */
    private String prnFlag;

    /**
     * 基准价
     */
    private Long standardPrice;

    /**
     * 
     */
    private Long hasBackNum;

    /**
     * 
     */
    private String orderType;

    /**
     * 
     */
    private String shopName;

    /**
     * 
     */
    private String source;

    /**
     * 
     */
    private String staffCode;

    /**
     * 
     */
    private String staffName;

    /**
     * 
     */
    private Long companyId;

    /**
     * 
     */
    private String companyName;

    /**
     * 
     */
    private Timestamp startTime;

    /**
     * 
     */
    private Timestamp endTime;

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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType == null ? null : priceType.trim();
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
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

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
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

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Long getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(Long reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getOrderSubScore() {
        return orderSubScore;
    }

    public void setOrderSubScore(Long orderSubScore) {
        this.orderSubScore = orderSubScore;
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

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType == null ? null : sysType.trim();
    }

    public Long getGdsType() {
        return gdsType;
    }

    public void setGdsType(Long gdsType) {
        this.gdsType = gdsType;
    }

    public String getEvalFlag() {
        return evalFlag;
    }

    public void setEvalFlag(String evalFlag) {
        this.evalFlag = evalFlag == null ? null : evalFlag.trim();
    }

    public Long getScoreTypeId() {
        return scoreTypeId;
    }

    public void setScoreTypeId(Long scoreTypeId) {
        this.scoreTypeId = scoreTypeId;
    }

    public String getPrnFlag() {
        return prnFlag;
    }

    public void setPrnFlag(String prnFlag) {
        this.prnFlag = prnFlag == null ? null : prnFlag.trim();
    }

    public Long getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Long standardPrice) {
        this.standardPrice = standardPrice;
    }

    public Long getHasBackNum() {
        return hasBackNum;
    }

    public void setHasBackNum(Long hasBackNum) {
        this.hasBackNum = hasBackNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", status=").append(status);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", categoryCode=").append(categoryCode);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuHisId=").append(skuHisId);
        sb.append(", skuInfo=").append(skuInfo);
        sb.append(", groupType=").append(groupType);
        sb.append(", groupDetail=").append(groupDetail);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", priceType=").append(priceType);
        sb.append(", basePrice=").append(basePrice);
        sb.append(", buyPrice=").append(buyPrice);
        sb.append(", orderMoney=").append(orderMoney);
        sb.append(", realMoney=").append(realMoney);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", staffId=").append(staffId);
        sb.append(", shopId=").append(shopId);
        sb.append(", proxyShopId=").append(proxyShopId);
        sb.append(", deliverStatus=").append(deliverStatus);
        sb.append(", remainAmount=").append(remainAmount);
        sb.append(", deliverAmount=").append(deliverAmount);
        sb.append(", discountPrice=").append(discountPrice);
        sb.append(", discountMoney=").append(discountMoney);
        sb.append(", reduceMoney=").append(reduceMoney);
        sb.append(", score=").append(score);
        sb.append(", orderSubScore=").append(orderSubScore);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", repCode=").append(repCode);
        sb.append(", stockId=").append(stockId);
        sb.append(", siteId=").append(siteId);
        sb.append(", sysType=").append(sysType);
        sb.append(", gdsType=").append(gdsType);
        sb.append(", evalFlag=").append(evalFlag);
        sb.append(", scoreTypeId=").append(scoreTypeId);
        sb.append(", prnFlag=").append(prnFlag);
        sb.append(", standardPrice=").append(standardPrice);
        sb.append(", hasBackNum=").append(hasBackNum);
        sb.append(", orderType=").append(orderType);
        sb.append(", shopName=").append(shopName);
        sb.append(", source=").append(source);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", staffName=").append(staffName);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append("]");
        return sb.toString();
    }
}