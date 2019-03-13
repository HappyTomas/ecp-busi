package com.zengshi.ecp.aip.third.dubbo.dto.req;

public class OrderShipReqDTO extends BaseShopAuthReqDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	private Long tid;//(必填)淘宝交易ID
	private String tradeId;//交易id
	private String outSid;//(必填)运单号.具体一个物流公司的真实运单号码。淘宝官方物流会校验，请谨慎传入；(必填)
	private String companyCode; // (必填)物流公司代码.如"POST"就代表中国邮政,"ZJS"就代表宅急送.调用 taobao.logistics.companies.get 获取。
	private String subTid;//（可选 ）拆单子订单列表
	private Long isSplit = 0L;// （可选 ）表明是否是拆单，默认值0，1表示拆单 （可选 ）
	private String sellerIp;// （可选 ）商家的IP地址
	private Long senderId; //（可选 ）卖家联系人地址库ID，可以通过taobao.logistics.address.search接口查询到地址库ID。如果为空，取的卖家的默认取货地址 
	private Long cancelId;//（可选 ）卖家联系人地址库ID，可以通过taobao.logistics.address.search接口查询到地址库ID。 如果为空，取的卖家的默认退货地址 
	private String feature;//（可选 ）feature参数格式 范例: identCode=tid1:识别码1,识别码2|tid2:识别码3;machineCode=tid3:3C机器号A,3C机器号B identCode为识别码的KEY,machineCode为3C的KEY,多个key之间用”;”分隔 “tid1:识别码1,识别码2|tid2:识别码3”为identCode对应的value。 "|"不同商品间的分隔符。 例1商品和2商品，之间就用"|"分开。 TID就是商品代表的子订单号，对应taobao.trade.fullinfo.get 接口获得的oid字段。(通过OID可以唯一定位到当前商品上) ":"TID和具体传入参数间的分隔符。冒号前表示TID,之后代表该商品的参数属性。 "," 属性间分隔符。（对应商品数量，当存在一个商品的数量超过1个时，用逗号分开）。 具体:当订单中A商品的数量为2个，其中手机串号分别为"12345","67890"。 参数格式：identCode=TIDA:12345,67890。 TIDA对应了A宝贝，冒号后用逗号分隔的"12345","67890".说明本订单A宝贝的数量为2，值分别为"12345","67890"。 当存在"|"时，就说明订单中存在多个商品，商品间用"|"分隔了开来。|"之后的内容含义同上。 

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Long getTid() {
		return tid;
	}
	public String getSubTid() {
		return subTid;
	}
	public String getOutSid() {
		return outSid;
	}
	public Long getIsSplit() {
		return isSplit;
	}
	public String getSellerIp() {
		return sellerIp;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public void setSubTid(String subTid) {
		this.subTid = subTid;
	}
	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}
	public void setIsSplit(Long isSplit) {
		this.isSplit = isSplit;
	}
	public void setSellerIp(String sellerIp) {
		this.sellerIp = sellerIp;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getCancelId() {
		return cancelId;
	}
	public void setCancelId(Long cancelId) {
		this.cancelId = cancelId;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
}
