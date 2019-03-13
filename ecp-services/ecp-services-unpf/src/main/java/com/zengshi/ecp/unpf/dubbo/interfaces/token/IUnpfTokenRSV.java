package com.zengshi.ecp.unpf.dubbo.interfaces.token;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月10日 上午9:08:23 
* @version 1.0 
* Copyright (c) 2016 AI 
*  */
public interface IUnpfTokenRSV {

	/**
	* token创建
	* 
	* @author huangjx
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	public void createToken(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;
	
	 
	}


