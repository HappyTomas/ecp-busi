package com.zengshi.ecp.aip.third.dubbo.dto.resp;


import java.util.Date;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class OrderRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
    
	//卖家昵称
	@JSONField(name="seller_nick")
	private String sellerNick;
	
	//商品图片绝对途径
	@JSONField(name="pic_path")
	private String picPath;
	
	//实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	@JSONField(name="payment")
	private String  payment;
	
	//卖家是否已评价。可选值:true(已评价),false(未评价)
	@JSONField(name="seller_rate")
	private Boolean  sellerRate;
	
	//邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
	@JSONField(name="post_fee")
	private String postFee;
	
	@JSONField(name="receiver_name")
	private String receiverName; //收货人的姓名
	
	@JSONField(name="receiver_country")
	private String receiverCountry;//收货人国籍
	
	@JSONField(name="receiver_state")
	private String receiverState;//收货人的所在省份
	
	@JSONField(name="receiver_city")
	private String receiverCity;//收货人所在城市
	
	@JSONField(name="receiver_district")
	private String receiverDistrict;//收货人所在区
	
	@JSONField(name="receiver_town")
	private String receiverTown;//收货人街道地址
	
	@JSONField(name="receiver_address")
	private String receiverAddress;//收货人的详细地址
	
	@JSONField(name="receiver_zip")
	private String receiverZip;//收货人的邮编
	
	@JSONField(name="receiver_mobile")
	private String receiverMobile;//收货人手机
	
	@JSONField(name="receiver_phone")
	private String receiverPhone;//收货人电话


	//卖家发货时间。格式:yyyy-MM-dd HH:mm:ss
	@JSONField(name="consign_time")
	private Date consignTime; 
	
	@JSONField(name="received_payment")
	private String receivedPayment;//卖家实际收到的打款金额
	//其他数据待补充
	
	@JSONField(name="est_con_time")
	private String estConTime;//商家的预计发货时间
	
	@JSONField(name="invoice_kind")
	private String  invoiceKind;//发票类型（ 1 电子发票 2 纸质发票 ）
	
	@JSONField(name="order_tax_feel")
	private String orderTaxFeel;//天猫国际官网直供主订单关税税费
	
	@JSONField(name="paid_coupon_fee")
	private String paidCouponFee;//满返红包的金额；如果没有满返红包，则值为 0.00
	
	@JSONField(name="shop_pick")
	private String shopPick;//
	
	@JSONField(name="tid")
	private Long tid;//交易编号 (父订单的交易编号)
	
	@JSONField(name="num")
	private Long num;//商品购买数量
	
	@JSONField(name="num_iid")
	private Long numIid;//商品数字编号
	
	@JSONField(name="status")
	private String status;//交易状态。可选值: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) * WAIT_BUYER_PAY(等待买家付款) * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRADE_CLOSED(付款以后用户退款成功，交易自动关闭) * TRADE_CLOSED_BY_TAOBAO(付款以前，卖家或买家主动关闭交易) * PAY_PENDING(国际信用卡支付付款确认中) * WAIT_PRE_AUTH_CONFIRM(0元购合约中)
	
	@JSONField(name="title")
	private String title;//交易标题，以店铺名作为此标题的值。注:taobao.trades.get接口返回的Trade中的title是商品名称
	
	@JSONField(name="type")
	private String type;//交易类型列表，同时查询多种交易类型可用逗号分隔。默认同时查询guarantee_trade, auto_delivery, ec, cod的4种交易类型的数据 可选值 fixed(一口价) auction(拍卖) guarantee_trade(一口价、拍卖) auto_delivery(自动发货) independent_simple_trade(旺店入门版交易) independent_shop_trade(旺店标准版交易) ec(直冲) cod(货到付款) fenxiao(分销) game_equipment(游戏装备) shopex_trade(ShopEX交易) netcn_trade(万网交易) external_trade(统一外部交易)o2o_offlinetrade（O2O交易）step (万人团)nopaid(无付款订单)pre_auth_type(预授权0元购机交易)
	
	@JSONField(name="price")
	private String price;//商品价格。精确到2位小数；单位：元。如：200.07，表示：200元7分
	
	@JSONField(name="discount_fee")
	private String discountFee;//可以使用trade.promotion_details查询系统优惠系统优惠金额（如打折，VIP，满就送等），精确到2位小数，单位：元。如：200.07，表示：200元7分
	
	@JSONField(name="has_post_fee")
	private Boolean hasPostFee;//是否包含邮费。与available_confirm_fee同时使用。可选值:true(包含),false(不包含)
	
	@JSONField(name="shipping_type")
	private String shippingType;//配送方式 创建交易时的物流方式（交易完成前，物流方式有可能改变，但系统里的这个字段一直不变）。可选值：free(卖家包邮),post(平邮),express(快递),ems(EMS),virtual(虚拟发货)，25(次日必达)，26(预约配送)。
	
	@JSONField(name="total_fee")
	private String totalFee;//商品金额（商品价格乘以数量的总金额）。精确到2位小数;单位:元。如:200.07，表示:200元7分
	
	@JSONField(name="created")
	private Date created;//交易创建时间。格式:yyyy-MM-dd HH:mm:ss
	
	@JSONField(name="pay_time")
	private Date payTime;//付款时间。格式:yyyy-MM-dd HH:mm:ss。订单的付款时间即为物流订单的创建时间
	
	@JSONField(name="invoice_name")
	private String invoiceName;//发票抬头
	
	private List<SubOrder> subOrders;//子订单
	
	@JSONField(name="buyer_message")
	private String  buyerMessage;
	
	@JSONField(name="buyer_memo")
	private String buyerMemo;//卖家备注
	
	@JSONField(name="buyer_nick")
	private String  buyerNick;//买家昵称
	
	private String platType;//接口平台
	private Long shopId;
	private String shopName;
	private String paramId="1";
	
	//订单编码
	private String orderId;
	
	//原始出参
	private String respParam;
	
	public String getSellerNick() {
		return sellerNick;
	}
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Boolean getSellerRate() {
		return sellerRate;
	}
	public void setSellerRate(Boolean sellerRate) {
		this.sellerRate = sellerRate;
	}
	public String getPostFee() {
		return postFee;
	}
	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverCountry() {
		return receiverCountry;
	}
	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverDistrict() {
		return receiverDistrict;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverTown() {
		return receiverTown;
	}
	public void setReceiverTown(String receiverTown) {
		this.receiverTown = receiverTown;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverZip() {
		return receiverZip;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public Date getConsignTime() {
		return consignTime;
	}
	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}
	public String getReceivedPayment() {
		return receivedPayment;
	}
	public void setReceivedPayment(String receivedPayment) {
		this.receivedPayment = receivedPayment;
	}
	public String getEstConTime() {
		return estConTime;
	}
	public void setEstConTime(String estConTime) {
		this.estConTime = estConTime;
	}
	public String getInvoiceKind() {
		return invoiceKind;
	}
	public void setInvoiceKind(String invoiceKind) {
		this.invoiceKind = invoiceKind;
	}
	public String getOrderTaxFeel() {
		return orderTaxFeel;
	}
	public void setOrderTaxFeel(String orderTaxFeel) {
		this.orderTaxFeel = orderTaxFeel;
	}
	public String getPaidCouponFee() {
		return paidCouponFee;
	}
	public void setPaidCouponFee(String paidCouponFee) {
		this.paidCouponFee = paidCouponFee;
	}
	public String getShopPick() {
		return shopPick;
	}
	public void setShopPick(String shopPick) {
		this.shopPick = shopPick;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Long getNumIid() {
		return numIid;
	}
	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}
	public Boolean getHasPostFee() {
		return hasPostFee;
	}
	public void setHasPostFee(Boolean hasPostFee) {
		this.hasPostFee = hasPostFee;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPlatType() {
		return platType;
	}
	public void setPlatType(String platType) {
		this.platType = platType;
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
	public String getBuyerMemo() {
		return buyerMemo;
	}
	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}
	public List<SubOrder> getSubOrders() {
		return subOrders;
	}
	public void setSubOrders(List<SubOrder> subOrders) {
		this.subOrders = subOrders;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getRespParam() {
		return respParam;
	}
	public void setRespParam(String respParam) {
		this.respParam = respParam;
	}
	
}

