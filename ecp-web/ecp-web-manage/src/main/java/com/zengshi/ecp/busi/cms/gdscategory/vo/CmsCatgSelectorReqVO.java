package com.zengshi.ecp.busi.cms.gdscategory.vo;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * 商品分类选择器请求参数VO <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月29日下午5:18:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class CmsCatgSelectorReqVO extends BaseInfo{
	
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 3933384267412252045L;

	/**
	 * 单选还是多选
	 */
    private String checkType;
    
    private Long siteId;
    
	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
    
    
}