package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 获取微信菜单服务接口<br>
 * Date:2016-2-24上午11:32:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.6
 */
public interface IWeixMenuManageSV {
    
	/**
	 * 获取微信菜单
	 * @param menuReqDTO
	 * @return
	 * @throws BusinessException
	 */
   public List<WeixMenuResDTO> getWeixMenu(WeixMenuReqDTO menuReqDTO)throws BusinessException;
}

