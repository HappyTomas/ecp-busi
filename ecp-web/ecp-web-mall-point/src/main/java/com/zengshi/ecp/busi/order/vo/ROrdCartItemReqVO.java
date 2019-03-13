package com.zengshi.ecp.busi.order.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROrdCartItemReqVO extends EcpBasePageReqVO implements Serializable {

	private static final long serialVersionUID = -4496088999113275141L;
    private Long id;
    
    private Long cartId;
	
    @NotNull
    private String cartType;

    @NotNull
    private Long shopId;

    @NotNull
    private String shopName;

    @NotNull
    private Long gdsId;
    
    @NotNull
    private String gdsName;

    @NotNull
    private Long skuId;

    private String skuInfo;

    @NotNull
    private String groupType;

    @NotNull
    private String groupDetail;

    @NotNull
    private String isPrimeGds;

    @NotNull
    private Long orderAmount;

    private Long promId;

    @NotNull
    private Long staffId;

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

    public String getIsPrimeGds() {
        return isPrimeGds;
    }

    public void setIsPrimeGds(String isPrimeGds) {
        this.isPrimeGds = isPrimeGds == null ? null : isPrimeGds.trim();
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


    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

}
