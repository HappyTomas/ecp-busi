package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class ShopTopicRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long shopAuthId;
	
	private String topic;
	
	private String nick;
	
	private String userId;
	
	private String ifValid;
	
	private String status;

	public Long getId() {
		return id;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public String getTopic() {
		return topic;
	}

	public String getNick() {
		return nick;
	}

	public String getUserId() {
		return userId;
	}

	public String getIfValid() {
		return ifValid;
	}

	public String getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setIfValid(String ifValid) {
		this.ifValid = ifValid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

