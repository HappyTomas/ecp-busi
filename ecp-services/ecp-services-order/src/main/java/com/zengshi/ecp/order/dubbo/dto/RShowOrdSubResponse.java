package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月18日下午3:10:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class RShowOrdSubResponse extends BaseResponseDTO{
    /** 
     * serialVersionUID: 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7256480796550768149L;
    //子订单号
    private String OrderSubId;
    //商品名称
    private String gdsName;
    //单品属性串
    private String skuInfo;
    //价格
    private Long orderMoney;
    //订购数量
    private Long orderAmount;
    //剩余发货数量
    private Long remainAmount;
    //产品类型
    private Long categoryId;
    //商品编码
    private Long gdsId;
    //单品编码
    private Long skuId;
    //库存量ID
    private Long stockId;
    //已发货数量
    private Long deliverAmount;
    
    private String isbn;

    //条形码
    private String txCode;

    //制式编码
    private String zsCode;
    
    //商品详情URL
    private String gdsUrl;
    
    //商品图片路径
    private String imageUrl;
    
    public Long getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Long getDeliverAmount() {
        return deliverAmount;
    }
    public void setDeliverAmount(Long deliverAmount) {
        this.deliverAmount = deliverAmount;
    }
    public Long getStockId() {
        return stockId;
    }
    public void setStockId(Long stockId) {
        this.stockId = stockId;
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
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public String getOrderSubId() {
        return OrderSubId;
    }
    public void setOrderSubId(String orderSubId) {
        OrderSubId = orderSubId;
    }
    public String getGdsName() {
        return gdsName;
    }
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }
    public String getSkuInfo() {
        return skuInfo;
    }
    public void setSkuInfo(String skuInfo) {
        this.skuInfo = skuInfo;
    }
    public Long getOrderMoney() {
        return orderMoney;
    }
    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }
    public Long getRemainAmount() {
        return remainAmount;
    }
    public void setRemainAmount(Long remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public String getZsCode() {
        return zsCode;
    }

    public void setZsCode(String zsCode) {
        this.zsCode = zsCode;
    }
    
    public String getGdsUrl() {
        return gdsUrl;
    }
    public void setGdsUrl(String gdsUrl) {
        this.gdsUrl = gdsUrl;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

