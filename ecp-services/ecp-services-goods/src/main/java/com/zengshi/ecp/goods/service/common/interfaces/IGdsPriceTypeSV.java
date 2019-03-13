package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsPriceType;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:价格类型服务 <br>
 * Date:2015年9月25日上午10:10:28  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsPriceTypeSV {
	
	/**
	 * 
	 * getPriceTypeByCode:(通过价格类型编码获取价格类型信息). <br/> 
	 * 
	 * @author linwb3
	 * @param priceTypeCode
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsPriceType getPriceType(String priceTypeCode);
	
	
	/**
	 * 
	 * getPriceTypeByCode:(通过价格类型Id获取价格类型信息). <br/> 
	 * 
	 * @author linwb3
	 * @param priceTypeCode
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsPriceType getPriceType(Long id);


	/**
	 * queryAllPriceType:(获取所有价格类型列表). <br/> 
	 * 
	 * @author linwb3
	 * @param status
	 * @return 
	 * @since JDK 1.6
	 */
	public List<GdsPriceType> queryAllPriceType(String status);

	/**
	 * 
	 * queryAllPriceType:(获取所有价格类型列表). <br/> 
	 * 
	 * @author linwb3
	 * @param extral 需要排除的类型编码
	 * @param status
	 * @return 
	 * @since JDK 1.6
	 */
	public List<GdsPriceType> queryAllPriceType(String[] extralCode, String status);
	
	

}

