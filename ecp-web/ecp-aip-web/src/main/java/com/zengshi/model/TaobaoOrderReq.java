package com.zengshi.model;

import com.alibaba.fastjson.annotation.JSONField;

public class TaobaoOrderReq extends TaobaoMsgPushEntity{

	private static final long serialVersionUID = 1L;
	
	@JSONField(name="buyer_nick")
	private String buyerNick;//买家昵称
	
	private Long oid;//子订单id
	
	private String payment;//订单实付金额
	
	@JSONField(name="seller_nick")
	private String sellerNick;//卖家昵称
	
	private String status;//交易状态
	/*订单状态（请关注此状态，如果为TRADE_CLOSED_BY_TAOBAO状态，则不要对此订单进行发货，切记啊！）。
	 * 可选值: TRADE_NO_CREATE_PAY(没有创建支付宝交易) 
	 * WAIT_BUYER_PAY(等待买家付款) 
	 * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) 
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) 
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) 
	 * TRADE_FINISHED(交易成功) 
	 * TRADE_CLOSED(付款以后用户退款成功，交易自动关闭) 
	 * TRADE_CLOSED_BY_TAOBAO(付款以前，卖家或买家主动关闭交易)
	 * PAY_PENDING(国际信用卡支付付款确认中)*/
	
	private Long tid;//主订单ID
	
	private String type;//交易类型
	/*交易类型列表，同时查询多种交易类型可用逗号分隔。
	 * 默认同时查询guarantee_trade, auto_delivery, ec, cod的4种交易类型的数据 可选值 fixed(一口价) auction(拍卖) guarantee_trade(一口价、拍卖) auto_delivery(自动发货) independent_simple_trade(旺店入门版交易) independent_shop_trade(旺店标准版交易) ec(直冲) cod(货到付款) fenxiao(分销) game_equipment(游戏装备) shopex_trade(ShopEX交易) netcn_trade(万网交易) external_trade(统一外部交易)o2o_offlinetrade（O2O交易）step (万人团)nopaid(无付款订单)pre_auth_type(预授权0元购机交易)*/

	public String getBuyerNick() {
		return buyerNick;
	}

	public Long getOid() {
		return oid;
	}

	public String getPayment() {
		return payment;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public String getStatus() {
		return status;
	}

	public Long getTid() {
		return tid;
	}

	public String getType() {
		return type;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public void setType(String type) {
		this.type = type;
	}
}
