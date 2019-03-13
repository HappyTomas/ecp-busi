package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RSumbitSubRequest  extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7474084395612949945L;

    /** 
     * cartItemId:购物车明细ID. 
     * @since JDK 1.6 
     */ 
    private Long cartItemId;
    
    private String categoryCode;

    private Long gdsId;

    private String gdsName;

    private Long skuId;

    private Long skuHisId;

    private String skuInfo;
    
    private String gdsCateName;

    private String groupType;

    private String groupDetail;

    private Long orderAmount;

    private Long basePrice;

    private Long buyPrice;
    
    //积分
    private Long score;
    
    //总积分
    private Long orderScore;
    
    private Long discountPrice;
    
    private Long detailPromReduceMoney;

    private Long orderMoney;

    private Long shareMoney;

    private Timestamp orderTime;

    private Long staffId;

    private Long shopId;
    
    private String shopName;
    
    private Long gdsType;
    
    private String orderSubId;
    
    private String orderId;
    
    private Long stockId;
    
    private Long repCode;
    
    private String prnFlag;
    
    /** 
     * standardPrice:基准价. 
     * @since JDK 1.6 
     */ 
    private Long standardPrice;

    //立即购买添加变量
    private String cartType;


    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public Long getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Long standardPrice) {
        this.standardPrice = standardPrice;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Long orderScore) {
        this.orderScore = orderScore;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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
        this.gdsName = gdsName;
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
        this.skuInfo = skuInfo;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupDetail() {
        return groupDetail;
    }

    public void setGroupDetail(String groupDetail) {
        this.groupDetail = groupDetail;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
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

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }



    public Long getDetailPromReduceMoney() {
        return detailPromReduceMoney;
    }

    public void setDetailPromReduceMoney(Long detailPromReduceMoney) {
        this.detailPromReduceMoney = detailPromReduceMoney;
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



    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public Long getGdsType() {
        return gdsType;
    }

    public void setGdsType(Long gdsType) {
        this.gdsType = gdsType;
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public String getPrnFlag() {
        return prnFlag;
    }

    public void setPrnFlag(String prnFlag) {
        this.prnFlag = prnFlag;
    }

    public String getGdsCateName() {
        return gdsCateName;
    }

    public void setGdsCateName(String gdsCateName) {
        this.gdsCateName = gdsCateName;
    }

  


}