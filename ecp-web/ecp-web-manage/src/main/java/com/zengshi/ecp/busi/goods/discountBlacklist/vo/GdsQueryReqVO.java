package com.zengshi.ecp.busi.goods.discountBlacklist.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class GdsQueryReqVO extends EcpBasePageReqVO{


    private static final long serialVersionUID = 1L;
    private String id;
    private String shopId;
    private String shopName;
    private String gdsId;
    private String gdsName;
    private String skuId;
    private String skuName;
    private String gdsTypeId;//类别
    private String gdsTypeName;//类别
    private String skuSubHead;//副标题
    private String skuTypeId;//类别
    private String gdsStatus;//单品状态
    private String skuApprove;//审核状态
    private String platCatgs;//归属平台分类编码
    private String mainCatgs;//主分类编码
    private String shopCatgs;//归属店铺平台分类编码
    private String isbn;
    private Long siteId;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getGdsId() {
        return gdsId;
    }
    public void setGdsId(String gdsId) {
        this.gdsId = gdsId;
    }
    public String getGdsName() {
        return gdsName;
    }
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }
    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public String getSkuName() {
        return skuName;
    }
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public String getGdsTypeId() {
        return gdsTypeId;
    }
    public void setGdsTypeId(String gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }
    public String getGdsTypeName() {
        return gdsTypeName;
    }
    public void setGdsTypeName(String gdsTypeName) {
        this.gdsTypeName = gdsTypeName;
    }
    public String getSkuSubHead() {
        return skuSubHead;
    }
    public void setSkuSubHead(String skuSubHead) {
        this.skuSubHead = skuSubHead;
    }
    public String getSkuTypeId() {
        return skuTypeId;
    }
    public void setSkuTypeId(String skuTypeId) {
        this.skuTypeId = skuTypeId;
    }
    public String getSkuApprove() {
        return skuApprove;
    }
    public void setSkuApprove(String skuApprove) {
        this.skuApprove = skuApprove;
    }
    public String getPlatCatgs() {
        return platCatgs;
    }
    public void setPlatCatgs(String platCatgs) {
        this.platCatgs = platCatgs;
    }
    public String getShopCatgs() {
        return shopCatgs;
    }
    public void setShopCatgs(String shopCatgs) {
        this.shopCatgs = shopCatgs;
    }
    public String getGdsStatus() {
        return gdsStatus;
    }
    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }
    public String getMainCatgs() {
        return mainCatgs;
    }
    public void setMainCatgs(String mainCatgs) {
        this.mainCatgs = mainCatgs;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
}
