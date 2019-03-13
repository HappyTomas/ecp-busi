package com.zengshi.ecp.sys.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.mapper.common.WeixMenuMapper;
import com.zengshi.ecp.sys.dao.model.WeixMenu;
import com.zengshi.ecp.sys.dao.model.WeixMenuCriteria;
import com.zengshi.ecp.sys.dao.model.WeixMenuCriteria.Criteria;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuResDTO;
import com.zengshi.ecp.sys.service.common.interfaces.IWeixMenuManageSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

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
public class WeixMenuManageSVImpl implements IWeixMenuManageSV {

	
	@Resource
	private WeixMenuMapper weixMenuMapper;
	
	@Override
	public List<WeixMenuResDTO> getWeixMenu(WeixMenuReqDTO menuReqDTO)
			throws BusinessException {
		List<WeixMenuResDTO> listWeix = new ArrayList<WeixMenuResDTO>(); 
		WeixMenuCriteria example = new WeixMenuCriteria();
		Criteria sql =  example.createCriteria();
		if(menuReqDTO.getParentButtonId()!=null){
			sql.andParentButtonIdEqualTo(menuReqDTO.getParentButtonId());
		}
		if(StringUtil.isNotBlank(menuReqDTO.getStatus())){
			sql.andStatusEqualTo(menuReqDTO.getStatus());
		}
		List<WeixMenu> list = weixMenuMapper.selectByExample(example);
		for (WeixMenu weixMenu : list) {
			WeixMenuResDTO dto  =  new WeixMenuResDTO();
			ObjectCopyUtil.copyObjValue(weixMenu, dto, null, false);
			listWeix.add(dto);
		}
		return listWeix;
	}
    

}

