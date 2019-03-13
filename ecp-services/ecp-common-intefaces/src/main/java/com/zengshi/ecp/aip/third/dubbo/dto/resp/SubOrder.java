package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class SubOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONField(name="oid")
	private Long oid;//子订单编号
	@JSONField(name="outer_iid")
	private String  outerIid;//商家外部编码(可与商家外部系统对接)。外部商家自己定义的商品Item的id，可以通过taobao.items.custom.get获取商品的Item的信息
	@JSONField(name="status")
	private String status;//订单状态（请关注此状态，如果为TRADE_CLOSED_BY_TAOBAO状态，则不要对此订单进行发货，切记啊！）。可选值:TRADE_NO_CREATE_PAY(没有创建支付宝交易)WAIT_BUYER_PAY(等待买家付款)WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款)WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货)TRADE_BUYER_SIGNED(买家已签收,货到付款专用)TRADE_FINISHED(交易成功)TRADE_CLOSED(付款以后用户退款成功，交易自动关闭)TRADE_CLOSED_BY_TAOBAO(付款以前，卖家或买家主动关闭交易)PAY_PENDING(国际信用卡支付付款确认中)
	@JSONField(name="sku_id")
	private String  skuId;//商品的最小库存单位Sku的id.可以通过taobao.item.sku.get获取详细的Sku信息
	@JSONField(name="outer_sku_id")
	private String outerSkuId;//外部网店自己定义的Sku编号
	@JSONField(name="num_iid")
	private String  numIid;//商品数字ID
	@JSONField(name="title")
	private String  title;//商品标题
	@JSONField(name="is_sh_ship")
	private String isShShip;//是否发货
	@JSONField(name="num")
	private Long  num;//商品数量
	@JSONField(name="price")
	private String price;//商品价格
	@JSONField(name="total_fee")
	private String totalFee;//应付金额
	@JSONField(name="payment")
	private String  payment;//实付金额
	@JSONField(name="discount_fee")
	private String discountFee;//子订单级订单优惠金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	@JSONField(name="adjust_fee")
	private String adjustFee;//手工调整金额.格式为:1.01;单位:元;精确到小数点后两位.
	@JSONField(name="divide_order_fee")
	private String  divideOrderFee;//分摊之后的实付金额
	@JSONField(name="part_mjz_discount")
	private String partMjzDiscount;//优惠分摊
	@JSONField(name="seller_nick")
	private String sellerNick;//卖家昵称
	@JSONField(name="buyer_nick")
	private String  buyerNick;//买家昵称
	@JSONField(name="cid")
	private String cid;//交易商品对应的类目ID
	@JSONField(name="pic_path")
	private String picPath;
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getOuterIid() {
		return outerIid;
	}
	public void setOuterIid(String outerIid) {
		this.outerIid = outerIid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getOuterSkuId() {
		return outerSkuId;
	}
	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}
	public String getNumIid() {
		return numIid;
	}
	public void setNumIid(String numIid) {
		this.numIid = numIid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsShShip() {
		return isShShip;
	}
	public void setIsShShip(String isShShip) {
		this.isShShip = isShShip;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}
	public String getAdjustFee() {
		return adjustFee;
	}
	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}
	public String getDivideOrderFee() {
		return divideOrderFee;
	}
	public void setDivideOrderFee(String divideOrderFee) {
		this.divideOrderFee = divideOrderFee;
	}
	public String getPartMjzDiscount() {
		return partMjzDiscount;
	}
	public void setPartMjzDiscount(String partMjzDiscount) {
		this.partMjzDiscount = partMjzDiscount;
	}
	public String getSellerNick() {
		return sellerNick;
	}
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	
}
