package com.zengshi.ecp.sys.dubbo.impl;


import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IWeixMenuRSV;
import com.zengshi.ecp.sys.service.common.interfaces.IWeixMenuManageSV;

public class WeixMenuRSVImpl implements IWeixMenuRSV {
	
	@Resource
	private IWeixMenuManageSV weixMenuManageSV;

	@Override
	public List<WeixMenuResDTO> getWeixMenu(WeixMenuReqDTO menuReqDTO)
			throws BusinessException {
		
		return weixMenuManageSV.getWeixMenu(menuReqDTO);
	}
	
}
