/** 
 * Project Name:ecp-services-goods-server 
 * File Name:OperationCalServiceId.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.interfaces.gdslog 
 * Date:2016-3-31下午3:31:18 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.interfaces.gdslog;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 服务枚举<br>
 * Date:2016-3-31下午3:31:18  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public enum ServiceEnum {
	/**
	 * 商品信息操作类型计算服务。
	 */
	GDS_INFO_OPE_TYPE_CAL_SV("gdsInfoOpeTypeCalSV"),
	/**
	 * 商品操作审核操作类型计算服务。
	 */
	GDS_VERIFY_OPE_TYPE_CAL_SV("gdsVerifyOpeTypeCalSV"),
	/**
	 * 单品信息操作类型计算服务。
	 */
	GDS_SKU_INFO_OPE_TYPE_CAL_SV("gdsSkuInfoOpeTypeCalSV"),
	
	/**
	 * 单品操作审核操作类型计算服务。
	 */
	GDS_SKU_VERIFY_OPE_TYPE_CAL_SV("gdsSkuVerifyOpeTypeCalSV"),
	
	NULL_SV("NULL");
	
	private String beanId;
	
	private ServiceEnum(String beanId){
		this.beanId = beanId;
	}
	
	private ServiceEnum(){
	}
	
	public String getBeanId() {
		return beanId;
	}

	
	

}

