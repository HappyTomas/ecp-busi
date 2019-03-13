package com.zengshi.ecp.busi.unpf.msg.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月23日 上午11:08:50 
* @version 1.0 
**/
public class ShopAuthTopicVO extends EcpBasePageReqVO{

	private static final long serialVersionUID = -1L;
	
	private String shopAuthId;
	
	private Long shopId;
	
	private String platType;
	
	private String nick;
	
	private String topic;
	
	private String appkey;
	
	private Long topicId;
	
	public String getShopAuthId() {
		return shopAuthId;
	}

	public void setShopAuthId(String shopAuthId) {
		this.shopAuthId = shopAuthId;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getPlatType() {
		return platType;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public String getNick() {
		return nick;
	}

	public String getTopic() {
		return topic;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	

}


