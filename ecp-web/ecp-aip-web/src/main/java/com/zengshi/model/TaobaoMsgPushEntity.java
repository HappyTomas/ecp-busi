package com.zengshi.model;

import java.util.Date;

public class TaobaoMsgPushEntity extends TopMsgReq{

	private static final long serialVersionUID = 1L;
	
	private String content;//内容
	
	private Long id;//id
	
	private Date outgoingTime;
	
	private String pubAppKey;
	
	private Date pubTime;
	
	private String topic;//消息名
	
	private String userNick;//用户昵称

	public String getContent() {
		return content;
	}

	public Long getId() {
		return id;
	}

	public Date getOutgoingTime() {
		return outgoingTime;
	}

	public String getPubAppKey() {
		return pubAppKey;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public String getTopic() {
		return topic;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOutgoingTime(Date outgoingTime) {
		this.outgoingTime = outgoingTime;
	}

	public void setPubAppKey(String pubAppKey) {
		this.pubAppKey = pubAppKey;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
}
