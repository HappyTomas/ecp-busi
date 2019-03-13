package com.zengshi.ecp.app.resp;

import java.util.List;

/**
 * Created by wang on 16/3/14.
 */
public class Ord00201Resp{
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
    private List<Ord00202Resp> ordCartItemList;
    /**
     * 组合商品明细
     */
    private Ord00204Resp groupLists;
    /**
     * 赠品信息
     */
    private String giftInfo;
    /**
     * 各种促销信息（列表展示）
     */
    private List<Ord00203Resp> promInfoDTOList;
    /**
     * 优惠条件是否满足
     */
    private boolean ifFulfillProm;
    /**
     * 满足优惠条件展示信息
     */
    private String fulfilMsg;
    /**
     * 不满足优惠条件展示信息
     */
    private String noFulfilMsg;
    /**
     * 店铺打折后价格
     */
    private Long discountPrice;
    /**
     * 订单级别数量
     */
    private Long discountAmount;
    /**
     * 店铺打折金额
     */
    private Long discountMoney;

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

    public List<Ord00202Resp> getOrdCartItemList() {
        return ordCartItemList;
    }

    public void setOrdCartItemList(List<Ord00202Resp> ordCartItemList) {
        this.ordCartItemList = ordCartItemList;
    }


    public Ord00204Resp getGroupLists() {
        return groupLists;
    }

    public void setGroupLists(Ord00204Resp groupLists) {
        this.groupLists = groupLists;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getGiftInfo() {
        return giftInfo;
    }

    public void setGiftInfo(String giftInfo) {
        this.giftInfo = giftInfo;
    }

    public List<Ord00203Resp> getPromInfoDTOList() {
        return promInfoDTOList;
    }

    public void setPromInfoDTOList(List<Ord00203Resp> promInfoDTOList) {
        this.promInfoDTOList = promInfoDTOList;
    }

    public boolean isIfFulfillProm() {
        return ifFulfillProm;
    }

    public void setIfFulfillProm(boolean ifFulfillProm) {
        this.ifFulfillProm = ifFulfillProm;
    }

    public String getFulfilMsg() {
        return fulfilMsg;
    }

    public void setFulfilMsg(String fulfilMsg) {
        this.fulfilMsg = fulfilMsg;
    }

    public String getNoFulfilMsg() {
        return noFulfilMsg;
    }

    public void setNoFulfilMsg(String noFulfilMsg) {
        this.noFulfilMsg = noFulfilMsg;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }
}
