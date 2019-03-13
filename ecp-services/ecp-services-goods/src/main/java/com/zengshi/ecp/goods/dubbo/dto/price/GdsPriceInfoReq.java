package com.zengshi.ecp.goods.dubbo.dto.price;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:商品价格抽象父类 <br>
 * Date:2015年9月16日下午8:19:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsPriceInfoReq extends BaseInfo {
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1L;

	/**
	 * 价格id
	 */
	private Long id;
	
	/**
	 * 价格
	 */
	private Long price;
	
	/**
	 * 价格类型编码
	 */
	private String priceTypeCode;
	
	/**
	 * 价格对象
	 */
	private BaseInfo priceObj;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getPriceTypeCode() {
		return priceTypeCode;
	}

	public void setPriceTypeCode(String priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}

	public BaseInfo getPriceObj() {
		return priceObj;
	}

	public void setPriceObj(BaseInfo priceObj) {
		this.priceObj = priceObj;
	}
	

}
