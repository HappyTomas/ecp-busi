package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackGdsResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7238203058793501966L;
    /** 
     * backId:退换货申请ID. 
     * @since JDK 1.6 
     */ 
    private Long backId;

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;

    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private String orderSubId;

    /** 
     * gdsId:商品编号. 
     * @since JDK 1.6 
     */ 
    private Long gdsId;

    /** 
     * gdsName:商品名称. 
     * @since JDK 1.6 
     */ 
    private String gdsName;

    /** 
     * skuId:单品编号. 
     * @since JDK 1.6 
     */ 
    private Long skuId;

    /** 
     * num:数量. 
     * @since JDK 1.6 
     */ 
    private Long num;

    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;

    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;

    /** 
     * backMoney:退款金额. 
     * @since JDK 1.6 
     */ 
    private Long backMoney;

    /** 
     * backScore:退货积分. 
     * @since JDK 1.6 
     */ 
    private Long backScore;

    /** 
     * backAccount:退货资金账户. 
     * @since JDK 1.6 
     */ 
    private Long backAccount;
    
    /** 
     * gdsUrl:商品详情URL. 
     * @since JDK 1.6 
     */ 
    private String gdsUrl;
    
    /** 
     * picId:图片ID. 
     * @since JDK 1.6 
     */ 
    private String picId;
    
    /** 
     * dscountMoney:优惠后总价. 
     * @since JDK 1.6 
     */ 
    private Long discountMoney;
    
    /** 
     * zsCode:书号. 
     * @since JDK 1.6 
     */ 
    private String zsCode;
    
    /** 
     * score:商品积分. 
     * @since JDK 1.6 
     */ 
    private Long score;
    
    
    
    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getZsCode() {
        return zsCode;
    }

    public void setZsCode(String zsCode) {
        this.zsCode = zsCode;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
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

    public String getGdsUrl() {
        return gdsUrl;
    }

    public void setGdsUrl(String gdsUrl) {
        this.gdsUrl = gdsUrl;
    }
    
}

