package com.zengshi.ecp.unpf.dubbo.interfaces.stock;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.stock.GdsStockReqDTO;

/**
* @author  huangjx: 
* @date 创建时间：2016年12月23日 上午9:08:23 
* @version 1.0 
* Copyright (c) 2016 AI 
*  */
public interface IUnpfStockRSV {

	/**
	* 库存
	* 
	* @author huangjx
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	public void updateStock(GdsStockReqDTO gdsStockReqDTO) throws BusinessException;
	
	 
	}


