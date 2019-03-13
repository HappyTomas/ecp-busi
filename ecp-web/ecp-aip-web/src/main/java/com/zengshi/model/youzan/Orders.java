/**
 * Copyright 2017 aTool.org
 */
package com.zengshi.model.youzan;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 订单信息
 */
public class Orders {

    private String alias;
    /**
     * 交易明细编号。该编号并不唯一，只用于区分交易内的多条明细记录
     */
    private int oid;
    @JSONField(name = "outer_sku_id")
    private String outerSkuId;
    @JSONField(name = "outer_item_id")
    private String outerItemId;
    private String title;
    @JSONField(name = "seller_nick")
    private String sellerNick;
    @JSONField(name = "fenxiao_price")
    private String fenxiaoPrice;
    @JSONField(name = "fenxiao_payment")
    private String fenxiaoPayment;
    private String price;
    @JSONField(name = "total_fee")
    private String totalFee;
    private String payment;
    @JSONField(name = "discount_fee")
    private String discountFee;
    @JSONField(name = "sku_id")
    private String skuId;
    @JSONField(name = "sku_unique_code")
    private String skuUniqueCode;
    @JSONField(name = "sku_properties_name")
    private String skuPropertiesName;
    @JSONField(name = "pic_path")
    private String picPath;
    @JSONField(name = "pic_thumb_path")
    private String picThumbPath;
    @JSONField(name = "item_type")
    private int itemType;
    @JSONField(name = "buyer_messages")
    private List<String> buyerMessages;
    @JSONField(name = "order_promotion_details")
    private List<String> orderPromotionDetails;
    @JSONField(name = "state_str")
    private String stateStr;
    @JSONField(name = "allow_send")
    private int allowSend;
    @JSONField(name = "is_send")
    private int isSend;
    @JSONField(name = "item_refund_state")
    private String itemRefundState;
    @JSONField(name = "is_virtual")
    private int isVirtual;
    @JSONField(name = "is_present")
    private int isPresent;
    @JSONField(name = "refunded_fee")
    private String refundedFee;
    private String unit;
    @JSONField(name = "num_iid")
    private String numIid;
    private String num;

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getOid() {
        return oid;
    }

    public void setOuterSkuId(String outerSkuId) {
        this.outerSkuId = outerSkuId;
    }

    public String getOuterSkuId() {
        return outerSkuId;
    }

    public void setOuterItemId(String outerItemId) {
        this.outerItemId = outerItemId;
    }

    public String getOuterItemId() {
        return outerItemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setFenxiaoPrice(String fenxiaoPrice) {
        this.fenxiaoPrice = fenxiaoPrice;
    }

    public String getFenxiaoPrice() {
        return fenxiaoPrice;
    }

    public void setFenxiaoPayment(String fenxiaoPayment) {
        this.fenxiaoPayment = fenxiaoPayment;
    }

    public String getFenxiaoPayment() {
        return fenxiaoPayment;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setDiscountFee(String discountFee) {
        this.discountFee = discountFee;
    }

    public String getDiscountFee() {
        return discountFee;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuUniqueCode(String skuUniqueCode) {
        this.skuUniqueCode = skuUniqueCode;
    }

    public String getSkuUniqueCode() {
        return skuUniqueCode;
    }

    public void setSkuPropertiesName(String skuPropertiesName) {
        this.skuPropertiesName = skuPropertiesName;
    }

    public String getSkuPropertiesName() {
        return skuPropertiesName;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicThumbPath(String picThumbPath) {
        this.picThumbPath = picThumbPath;
    }

    public String getPicThumbPath() {
        return picThumbPath;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setBuyerMessages(List<String> buyerMessages) {
        this.buyerMessages = buyerMessages;
    }

    public List<String> getBuyerMessages() {
        return buyerMessages;
    }

    public void setOrderPromotionDetails(List<String> orderPromotionDetails) {
        this.orderPromotionDetails = orderPromotionDetails;
    }

    public List<String> getOrderPromotionDetails() {
        return orderPromotionDetails;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setAllowSend(int allowSend) {
        this.allowSend = allowSend;
    }

    public int getAllowSend() {
        return allowSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }

    public int getIsSend() {
        return isSend;
    }

    public void setItemRefundState(String itemRefundState) {
        this.itemRefundState = itemRefundState;
    }

    public String getItemRefundState() {
        return itemRefundState;
    }

    public void setIsVirtual(int isVirtual) {
        this.isVirtual = isVirtual;
    }

    public int getIsVirtual() {
        return isVirtual;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public void setRefundedFee(String refundedFee) {
        this.refundedFee = refundedFee;
    }

    public String getRefundedFee() {
        return refundedFee;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid;
    }

    public String getNumIid() {
        return numIid;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

}