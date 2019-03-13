package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RPreOrdSubResponse  extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 528151482404226758L;

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
    
    private Long shopName;

    private String picUrl;
    
    private String gdsUrl;
    
    private String picId;
    
    private Long stockAvailCount;
    
    private Long gdsType;
    
    //为购物车转订单时候，订单明细对应的订单主表id使用
    private String ordMainId;
    //提交订单时候，子订单编号预生成
    private String ordSubId;
    
    //购物车明细ID
    private Long cartItemId;
    
    private String prnFlag;
    
    /** 
     * standardPrice:基准价. 
     * @since JDK 1.6 
     */ 
    private Long standardPrice;
    
    /**
     * 子订单优惠后原价
     */
    private Long baseDiscountMoney;
    
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

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
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

    public Long getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Long orderScore) {
        this.orderScore = orderScore;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
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

    public Long getShopName() {
        return shopName;
    }

    public void setShopName(Long shopName) {
        this.shopName = shopName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getGdsUrl() {
        return gdsUrl;
    }

    public void setGdsUrl(String gdsUrl) {
        this.gdsUrl = gdsUrl;
    }

    public Long getStockAvailCount() {
        return stockAvailCount;
    }

    public void setStockAvailCount(Long stockAvailCount) {
        this.stockAvailCount = stockAvailCount;
    }

    public Long getGdsType() {
        return gdsType;
    }

    public void setGdsType(Long gdsType) {
        this.gdsType = gdsType;
    }

    public String getOrdMainId() {
        return ordMainId;
    }

    public void setOrdMainId(String ordMainId) {
        this.ordMainId = ordMainId;
    }

    public String getOrdSubId() {
        return ordSubId;
    }

    public void setOrdSubId(String ordSubId) {
        this.ordSubId = ordSubId;
    }

    public String getPrnFlag() {
        return prnFlag;
    }

    public void setPrnFlag(String prnFlag) {
        this.prnFlag = prnFlag;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getGdsCateName() {
        return gdsCateName;
    }

    public void setGdsCateName(String gdsCateName) {
        this.gdsCateName = gdsCateName;
    }

    public Long getBaseDiscountMoney() {
        return baseDiscountMoney;
    }

    public void setBaseDiscountMoney(Long baseDiscountMoney) {
        this.baseDiscountMoney = baseDiscountMoney;
    }

  


}