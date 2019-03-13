package com.zengshi.ecp.busi.im.cust.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: 常见问题文章VO<br>
 * Date:2017年3月23日下午3:13:06  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class CustFaqVO extends EcpBaseResponseVO {

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1L;

	private String artName;
	
	private String artId;
	
	private String artUrl;

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
	}

	public String getArtId() {
		return artId;
	}

	public void setArtId(String artId) {
		this.artId = artId;
	}

	public String getArtUrl() {
		return artUrl;
	}

	public void setArtUrl(String artUrl) {
		this.artUrl = artUrl;
	}
	
	
}
