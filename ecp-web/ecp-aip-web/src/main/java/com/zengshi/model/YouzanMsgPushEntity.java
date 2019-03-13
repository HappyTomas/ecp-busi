package com.zengshi.model;

import com.alibaba.fastjson.annotation.JSONField;

public class YouzanMsgPushEntity extends TopMsgReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;

	private int sendCount;//重发的次数

	private int mode; // 0-商家自有消息推送 1-服务商消息推送

	@JSONField(name = "app_id")
	private String appId;//对应商户后台的app_id

	@JSONField(name = "client_id")
	private String clientId;

	private Long version;

	private String type;//消息业务类型：TRADE-交易 ITEM-商品

	private String id;//业务消息的标识: 如 订单消息为订单编号

	private String sign;

	@JSONField(name = "kdt_id")
	private Integer kdtId;//店铺ID

	private boolean test = false;//false-非测试消息 true- 测试消息 ，PUSH 服务会定期通过发送测试消息检查 商家服务是否正常

	private String status;

	@JSONField(name = "kdt_name")
	private String kdtName;

	//解析 解码后的 msg值
	private String message;
	
	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Integer getKdtId() {
		return kdtId;
	}

	public void setKdtId(Integer kdtId) {
		this.kdtId = kdtId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKdtName() {
		return kdtName;
	}

	public void setKdtName(String kdtName) {
		this.kdtName = kdtName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
