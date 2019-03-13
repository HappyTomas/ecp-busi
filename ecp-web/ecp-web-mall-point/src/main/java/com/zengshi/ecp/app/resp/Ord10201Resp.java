package com.zengshi.ecp.app.resp;

import java.util.List;

public class Ord10201Resp{
    /**
     * 购物车ID
     */
    private Long cartId;
    /**
     * 登录用户ID
     */
    private Long staffId;
    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * 店铺名称
     */
    private String shopName;
    
    /**
     * 促销ID
     */
    private Long promId;
    /**
     * 购物车明细
     */
    private List<Ord10202Resp> ordCartItemList;

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

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public List<Ord10202Resp> getOrdCartItemList() {
        return ordCartItemList;
    }

    public void setOrdCartItemList(List<Ord10202Resp> ordCartItemList) {
        this.ordCartItemList = ordCartItemList;
    }


    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

}
