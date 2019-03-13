package com.zengshi.model;

/**
 * @author huangjx
 *
 */
public class ThirdMsgReq extends TopMsgReq{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;

    private String platType;

    private Long shopId;

    private String appkey;

    private String appscret;

    private String topicUrl;//消息链接地址

	public Long getId() {
		return id;
	}

	public String getPlatType() {
		return platType;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getAppkey() {
		return appkey;
	}

	public String getAppscret() {
		return appscret;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public void setAppscret(String appscret) {
		this.appscret = appscret;
	}

	public String getTopicUrl() {
		return topicUrl;
	}

	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}

 
	
}
