 /**
 * @filename shopExpressVO.java
 * @package  com.zengshi.ecp.busi.staff.vo
 *
 * @author   PJieWin
 * @version  
 * @Date	 2015年9月7日		下午2:42:59
 *
 */


package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;

/**
 * Copyright (c) 2015, NEWLAND , LTD All Rights Reserved.
 *
 * @ClassName:shopExpressVO
 * @Description: TODO 一句话功能描述
 * @Funtion List:TODO 主要函数及其功能
 *
 * @author   PJieWin
 * @version  
 * @Date	 2015年9月7日		下午2:42:59
 *	 
 * @History: // 历史修改记录 
 * <author>  // 作者
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述
 */
public class ShopExpressVO implements Serializable
{

	
	/**
	 * serialVersionUID:TODO<用一句话描述这个变量表示什么>
	 *
	 */
	
	private static final long	serialVersionUID	= 1L;
	
	private String shopId;
	private String shopName;
	private String expressIds;
	/**
	 * shopId
	 *
	 * @return  the shopId
	 */
	
	public String getShopId()
	{
		
		return shopId;
	}
	/**
	 * shopId
	 *
	 * @param   the shopId to set
	 */
	
	public void setShopId(String shopId)
	{
		
		this.shopId = shopId;
	}
	/**
	 * shopName
	 *
	 * @return  the shopName
	 */
	
	public String getShopName()
	{
		
		return shopName;
	}
	/**
	 * shopName
	 *
	 * @param   the shopName to set
	 */
	
	public void setShopName(String shopName)
	{
		
		this.shopName = shopName;
	}
	/**
	 * expressIds
	 *
	 * @return  the expressIds
	 */
	
	public String getExpressIds()
	{
		
		return expressIds;
	}
	/**
	 * expressIds
	 *
	 * @param   the expressIds to set
	 */
	
	public void setExpressIds(String expressIds)
	{
		
		this.expressIds = expressIds;
	}
	
	/** 
	 * @Function: toString
	 * @Description: <一句话功能描述>
	 *               <功能详细描述>
	 *
	 * @return 
	 * @see java.lang.Object#toString() 
	 */ 
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("shopExpressVO [shopId=").append(shopId).append(", shopName=").append(shopName).append(", expressIds=").append(expressIds).append("]");
		return builder.toString();
	}
	
	
	
}

