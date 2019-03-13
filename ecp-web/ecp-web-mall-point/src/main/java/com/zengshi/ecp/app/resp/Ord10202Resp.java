package com.zengshi.ecp.app.resp;

public class Ord10202Resp{
    /**
     * 购物车明细ID
     */
    private Long id;
    /**
     * 店铺促销ID
     */
    private Long ordPromId;
    /**
     * 促销ID
     */
    private Long promId;
    /**
     * 商品购买价格
     */
    private Long buyPrice;
    /**
     * 商品基准价格
     */
    private Long basePrice;
    /**
     * 商品状态
     */
    private boolean gdsStatus;
    /**
     * 单品ID
     */
    private Long skuId;
    /**
     * 上一级购物车ID
     */
    private Long cartId;
    /**
     * 商品URL
     */
    private String gdsUrl;
    /**
     * 商品图片ID
     */
    private String picId;
    /**
     * 商品图片地址
     */
    private String picUrl;
    /** 
     * gdsId:商品ID. 
     * @since JDK 1.6 
     */ 
    private Long gdsId;
    /**
     * 商品名称
     */
    private String gdsName;
    /**
     * 商品主分类名称
     */
    private String gdsCateName;
    /**
     * 商品l
     */
    private String gdsType;
    /** 
     * orderAmount:数量. 
     * @since JDK 1.6 
     */ 
    private Long orderAmount;
    
    /** 
     * score:积分. 
     * @since JDK 1.6 
     */ 
    private Long score;
    
    

    

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getOrdPromId() {
        return ordPromId;
    }

    public void setOrdPromId(Long ordPromId) {
        this.ordPromId = ordPromId;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getGdsType() {
        return gdsType;
    }

    public void setGdsType(String gdsType) {
        this.gdsType = gdsType;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(boolean gdsStatus) {
        this.gdsStatus = gdsStatus;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getGdsUrl() {
        return gdsUrl;
    }

    public void setGdsUrl(String gdsUrl) {
        this.gdsUrl = gdsUrl;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getGdsCateName() {
        return gdsCateName;
    }

    public void setGdsCateName(String gdsCateName) {
        this.gdsCateName = gdsCateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
