package com.zengshi.ecp.im.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IStaffHotlineRSV;
import com.zengshi.ecp.im.service.common.interfaces.IStaffHotlineSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-im-server <br>
 * Description: openfire用户相关接口实现类<br>
 * Date:2016年8月10日下午5:18:27  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class StaffHotlineRSVImpl implements IStaffHotlineRSV{

	@Resource
	private IStaffHotlineSV hotlineSV;
	
	
	@Override
	public ImStaffHotlineResDTO getStaffHotline(ImStaffHotlineReqDTO dto)
			throws BusinessException {
		ImStaffHotlineResDTO dto2 = hotlineSV.getHotlineByStaff(dto);
		return dto2;
	}


	@Override
	public int addHotlineStaff(ImStaffHotlineReqDTO dto)
			throws BusinessException {
		return hotlineSV.addHotlineStaff(dto);
	}


	@Override
	public PageResponseDTO<ImHotlineInfoResDTO> getHotlineList(ImHotlineInfoReqDTO dto) throws BusinessException {
		return hotlineSV.getHotlineList(dto);
	}


	@Override
	public int updateHotlineInfo(ImHotlineInfoReqDTO dto) throws BusinessException {
		return hotlineSV.updateHotlineInfo(dto);
	}


	@Override
	public int hotlineInfoOut(SessionReqDTO dto) throws BusinessException {
		int i  = hotlineSV.updateSession(dto);
		return i;
	}


}

