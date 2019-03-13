/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsCategoryCompareReqDTO.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.dto.category 
 * Date:2015-9-22上午10:15:44 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.dto.category;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: 商品分类比较请求DTO <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-22上午10:15:44  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsCategoryCompareReqDTO extends BaseInfo{
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -699681362847095894L;
	/**
	 * 源分类编码.
	 */
	private String sourceCode;
	/**
	 * 目标分类编码.
	 */
	private String targetCode;
	
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getTargetCode() {
		return targetCode;
	}
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	
	
	

}

