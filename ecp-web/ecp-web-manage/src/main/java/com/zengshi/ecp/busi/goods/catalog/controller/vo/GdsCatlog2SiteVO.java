/** 
 * Project Name:ecp-web-manage 
 * File Name:GdsCatlog2ShopVO.java 
 * Package Name:com.zengshi.ecp.busi.goods.catalog.controller.vo 
 * Date:2016-4-6下午2:25:47 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.goods.catalog.controller.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016-4-6下午2:25:47  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author xiaosm3
 * @version  
 * @since JDK 1.6 
 */
public class GdsCatlog2SiteVO implements Serializable {

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 788002958269429790L;
	
	
	private Long siteId;

    private Long catlogId;
    
    private String catlogIds;

	

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long getCatlogId() {
		return catlogId;
	}

	public void setCatlogId(Long catlogId) {
		this.catlogId = catlogId;
	}

	public String getCatlogIds() {
		return catlogIds;
	}

	public void setCatlogIds(String catlogIds) {
		this.catlogIds = catlogIds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

    

}

