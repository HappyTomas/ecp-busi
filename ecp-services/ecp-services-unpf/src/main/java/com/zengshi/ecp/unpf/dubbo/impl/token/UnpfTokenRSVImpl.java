package com.zengshi.ecp.unpf.dubbo.impl.token;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.token.IUnpfTokenRSV;
import com.zengshi.ecp.unpf.service.busi.token.interfaces.IUnpfTokenSV;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月10日 上午9:10:38 
* @version 1.0 
* Copyright (c) 2016 AI <br>
* */
public class UnpfTokenRSVImpl implements IUnpfTokenRSV {

	 @Resource
	 private IUnpfTokenSV unpfTokenSV;

	@Override
	public void createToken(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException{
		unpfTokenSV.createToken(unpfShopAuthReqDTO);
	}
}


