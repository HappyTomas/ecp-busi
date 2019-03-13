package com.zengshi.ecp.general.order.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdCartItemCommRequest extends BaseInfo {
	
    private Long id;

    private Long cartId;

    private String cartType;

    private Long shopId;

    private String shopName;
    
    private String categoryCode;

    private Long gdsId;

    private String gdsName;

    private Long skuId;
    //计算优惠券优惠价格平摊每个单品的优惠券金额
    private Long coupMoney;

    private String skuInfo;

    private String groupType;

    private String groupDetail;

    private Long orderAmount;

    private Long promId;
    
    private Long ordPromId;

    private Long staffId;
    
    private Long basePrice;
    
    private Long buyPrice;
    
    private Long discountPrice;
    
    private Long orderMoney;
    
    private Long discountMoney;

    private Timestamp addTime;

    private Timestamp createTime;

    private Timestamp updateTime;
       
    private Long gdsType;
    
    private String orderSubId;
    
    private String orderId;
    
    private Long stockId;
    
    private Long repCode;
    
    private String priceType;
    
    private String siteId;
    
    //明细积分
    private Long score;
    
    //明细总积分
    private Long orderSubScore;
    
    //积分类型id
    private Long scoreTypeId;
    
    //数字印刷标示
    private String prnFlag;
    
    private boolean ifFulfillProm;// true 满足促销promId false不满足促销ID
    
    //子订单级别促销赠送积分
    private Long sendOrderSubScore;
    
    //子订单级别促销赠送积分倍数
    private double sendOrderSubScoreTimes;

    
    public Long getCoupMoney() {
		return coupMoney;
	}

	public void setCoupMoney(Long coupMoney) {
		this.coupMoney = coupMoney;
	}

	public boolean isIfFulfillProm() {
        return ifFulfillProm;
    }

    public void setIfFulfillProm(boolean ifFulfillProm) {
        this.ifFulfillProm = ifFulfillProm;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private Long createStaff;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType == null ? null : cartType.trim();
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
        this.shopName = shopName == null ? null : shopName.trim();
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

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Long getOrdPromId() {
        return ordPromId;
    }

    public void setOrdPromId(Long ordPromId) {
        this.ordPromId = ordPromId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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
        this.prnFlag = prnFlag;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Long getSendOrderSubScore() {
        return sendOrderSubScore;
    }

    public void setSendOrderSubScore(Long sendOrderSubScore) {
        this.sendOrderSubScore = sendOrderSubScore;
    }

    public double getSendOrderSubScoreTimes() {
        return sendOrderSubScoreTimes;
    }

    public void setSendOrderSubScoreTimes(double sendOrderSubScoreTimes) {
        this.sendOrderSubScoreTimes = sendOrderSubScoreTimes;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }




}

