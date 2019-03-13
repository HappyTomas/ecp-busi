package com.zengshi.ecp.sys.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuResDTO;


public interface IWeixMenuRSV {
	
	
	/**
	 * 获取微信菜单
	 * @param menuReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public List<WeixMenuResDTO> getWeixMenu(WeixMenuReqDTO menuReqDTO)throws BusinessException;
}
