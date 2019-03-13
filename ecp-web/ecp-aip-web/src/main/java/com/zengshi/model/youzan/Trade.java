/**
 * Copyright 2017 aTool.org
 */
package com.zengshi.model.youzan;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 *交易信息
 */
public class Trade {

    private int num;
    @JSONField(name = "goods_kind")
    private int goodsKind;
    @JSONField(name = "num_iid")
    private String numIid;
    private String price;
    @JSONField(name = "pic_path")
    private String picPath;
    @JSONField(name = "pic_thumb_path")
    private String picThumbPath;
    private String title;
    private String type;
    @JSONField(name = "discount_fee")
    private String discountFee;
    @JSONField(name = "order_type")
    private String orderType;
    private String status;
    @JSONField(name = "status_str")
    private String statusStr;
    @JSONField(name = "refund_state")
    private String refundState;
    @JSONField(name = "shipping_type")
    private String shippingType;
    @JSONField(name = "post_fee")
    private String postFee;
    @JSONField(name = "total_fee")
    private String totalFee;
    @JSONField(name = "refunded_fee")
    private String refundedFee;
    private String payment;
    private String created;
    @JSONField(name = "update_time")
    private String updateTime;
    /**
     * 支付类型。取值范围：
     * WEIXIN (微信自有支付)
     * WEIXIN_DAIXIAO (微信代销支付)
     * ALIPAY (支付宝支付)
     * BANKCARDPAY (银行卡支付)
     * PEERPAY (代付)
     * CODPAY (货到付款)
     * BAIDUPAY (百度钱包支付)
     * PRESENTTAKE (直接领取赠品)
     * COUPONPAY（优惠券/码全额抵扣）
     * BULKPURCHASE（来自分销商的采购）
     * MERGEDPAY (合并付货款)
     * ECARD（有赞E卡支付）
     */
    @JSONField(name = "pay_time")
    private String payTime;
    @JSONField(name = "pay_type")
    private String payType;
    @JSONField(name = "consign_time")
    private String consignTime;
    @JSONField(name = "sign_time")
    private String signTime;
    @JSONField(name = "buyer_area")
    private String buyerArea;
    @JSONField(name = "seller_flag")
    private int sellerFlag;
    @JSONField(name = "buyer_message")
    private String buyerMessage;
    private List<Orders> orders;
    @JSONField(name = "fetch_detail")
    private String fetchDetail;
    @JSONField(name = "weixin_user_id")
    private String weixinUserId;
    @JSONField(name = "feedback_num")
    private int feedbackNum;
    @JSONField(name = "trade_memo")
    private String tradeMemo;
    @JSONField(name = "fans_info")
    private FansInfo fansInfo;
    @JSONField(name = "buy_way_str")
    private String buyWayStr;
    @JSONField(name = "pf_buy_way_str")
    private String pfBuyWayStr;
    @JSONField(name = "send_num")
    private int sendNum;
    @JSONField(name = "user_id")
    private String userId;
    private int kind;
    @JSONField(name = "relation_type")
    private String relationType;
    private List<String> relations;
    @JSONField(name = "out_trade_no")
    private List<String> outTradeNo;
    @JSONField(name = "group_no")
    private String groupNo;
    @JSONField(name = "outer_user_id")
    private int outerUserId;
    @JSONField(name = "shop_id")
    private String shopId;
    @JSONField(name = "shop_type")
    private String shopType;
    @JSONField(name = "points_price")
    private int pointsPrice;
    @JSONField(name = "delivery_start_time")
    private int deliveryStartTime;
    @JSONField(name = "delivery_end_time")
    private int deliveryEndTime;
    @JSONField(name = "tuan_no")
    private String tuanNo;
    @JSONField(name = "is_tuan_head")
    private int isTuanHead;
    @JSONField(name = "delivery_time_display")
    private String deliveryTimeDisplay;
    @JSONField(name = "hotel_info")
    private String hotelInfo;
    @JSONField(name = "buyer_nick")
    private String buyerNick;
    private String tid;
    @JSONField(name = "buyer_type")
    private String buyerType;
    @JSONField(name = "buyer_id")
    private String buyerId;
    @JSONField(name = "receiver_city")
    private String receiverCity;
    @JSONField(name = "receiver_district")
    private String receiverDistrict;
    @JSONField(name = "receiver_name")
    private String receiverName;
    @JSONField(name = "receiver_state")
    private String receiverState;
    @JSONField(name = "receiver_address")
    private String receiverAddress;
    @JSONField(name = "receiver_zip")
    private String receiverZip;
    @JSONField(name = "receiver_mobile")
    private String receiverMobile;
    private int feedback;
    @JSONField(name = "outer_tid")
    private String outerTid;
    @JSONField(name = "service_phone")
    private String servicePhone;

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setGoodsKind(int goodsKind) {
        this.goodsKind = goodsKind;
    }

    public int getGoodsKind() {
        return goodsKind;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid;
    }

    public String getNumIid() {
        return numIid;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDiscountFee(String discountFee) {
        this.discountFee = discountFee;
    }

    public String getDiscountFee() {
        return discountFee;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    public String getRefundState() {
        return refundState;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    public String getPostFee() {
        return postFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setRefundedFee(String refundedFee) {
        this.refundedFee = refundedFee;
    }

    public String getRefundedFee() {
        return refundedFee;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

    public void setConsignTime(String consignTime) {
        this.consignTime = consignTime;
    }

    public String getConsignTime() {
        return consignTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setBuyerArea(String buyerArea) {
        this.buyerArea = buyerArea;
    }

    public String getBuyerArea() {
        return buyerArea;
    }

    public void setSellerFlag(int sellerFlag) {
        this.sellerFlag = sellerFlag;
    }

    public int getSellerFlag() {
        return sellerFlag;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setFetchDetail(String fetchDetail) {
        this.fetchDetail = fetchDetail;
    }

    public String getFetchDetail() {
        return fetchDetail;
    }

    public void setWeixinUserId(String weixinUserId) {
        this.weixinUserId = weixinUserId;
    }

    public String getWeixinUserId() {
        return weixinUserId;
    }

    public void setFeedbackNum(int feedbackNum) {
        this.feedbackNum = feedbackNum;
    }

    public int getFeedbackNum() {
        return feedbackNum;
    }

    public void setTradeMemo(String tradeMemo) {
        this.tradeMemo = tradeMemo;
    }

    public String getTradeMemo() {
        return tradeMemo;
    }

    public void setFansInfo(FansInfo fansInfo) {
        this.fansInfo = fansInfo;
    }

    public FansInfo getFansInfo() {
        return fansInfo;
    }

    public void setBuyWayStr(String buyWayStr) {
        this.buyWayStr = buyWayStr;
    }

    public String getBuyWayStr() {
        return buyWayStr;
    }

    public void setPfBuyWayStr(String pfBuyWayStr) {
        this.pfBuyWayStr = pfBuyWayStr;
    }

    public String getPfBuyWayStr() {
        return pfBuyWayStr;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelations(List<String> relations) {
        this.relations = relations;
    }

    public List<String> getRelations() {
        return relations;
    }

    public void setOutTradeNo(List<String> outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public List<String> getOutTradeNo() {
        return outTradeNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setOuterUserId(int outerUserId) {
        this.outerUserId = outerUserId;
    }

    public int getOuterUserId() {
        return outerUserId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopType() {
        return shopType;
    }

    public void setPointsPrice(int pointsPrice) {
        this.pointsPrice = pointsPrice;
    }

    public int getPointsPrice() {
        return pointsPrice;
    }

    public void setDeliveryStartTime(int deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public int getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryEndTime(int deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public int getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setTuanNo(String tuanNo) {
        this.tuanNo = tuanNo;
    }

    public String getTuanNo() {
        return tuanNo;
    }

    public void setIsTuanHead(int isTuanHead) {
        this.isTuanHead = isTuanHead;
    }

    public int getIsTuanHead() {
        return isTuanHead;
    }

    public void setDeliveryTimeDisplay(String deliveryTimeDisplay) {
        this.deliveryTimeDisplay = deliveryTimeDisplay;
    }

    public String getDeliveryTimeDisplay() {
        return deliveryTimeDisplay;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public String getHotelInfo() {
        return hotelInfo;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setOuterTid(String outerTid) {
        this.outerTid = outerTid;
    }

    public String getOuterTid() {
        return outerTid;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getServicePhone() {
        return servicePhone;
    }

}