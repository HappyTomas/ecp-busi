package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.wechat.interfaces.ICustWechatRelSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 商城用户与微信用户关系接口RSV实现类<br>
 * Date:2016年7月21日下午4:11:13  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6
 */
public class CustWechatRelRSVImpl implements ICustWechatRelRSV{

	@Resource
	private ICustWechatRelSV custWechatRelSV;
	
	private static final String MODULE = CustWechatRelRSVImpl.class.getName();
	
	@Override
	public PageResponseDTO<CustWechatRelRespDTO> findCustWechatRel(CustWechatRelReqDTO req) throws BusinessException {
		return custWechatRelSV.findCustWechatRel(req);
	}

	@Override
	public long saveCustWechatRel(CustWechatRelReqDTO req) throws BusinessException {
		if (req == null) {
			LogUtil.info(MODULE, "入参对象不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
		}
		return custWechatRelSV.saveCustWechatRel(req);
	}

	@Override
	public long updateCustWechatRel(CustWechatRelReqDTO req) throws BusinessException {
		if (req == null) {
			LogUtil.info(MODULE, "入参对象不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
		}
		return custWechatRelSV.updateCustWechatRel(req);
	}

	@Override
	public CustWechatRelRespDTO findCustWechatRelByWechatId(String wechatId) throws BusinessException {
		if (StringUtil.isBlank(wechatId)) {
			throw new BusinessException("微信用户的openId不能为空。");
		}
		return custWechatRelSV.findCustWechatRelByWechatId(wechatId);
	}

	@Override
	public long deleteCustWechatRel(String wechatId) throws BusinessException {
		if (StringUtil.isBlank(wechatId)) {
			throw new BusinessException("微信用户的openId不能为空。");
		}
		return custWechatRelSV.deleteCustWechatRel(wechatId);
	}

	@Override
	public long deleteCustWechatRelBy(CustWechatRelReqDTO req) throws BusinessException {
		boolean flag = false;
		if (StringUtil.isNotBlank(req.getStaffCode())) {
			flag = true;
		}
		if (req.getStaffId() != null && req.getStaffId() != 0L) {
			flag = true;
		}
		if (!flag) {
			throw new BusinessException("请传入需要删除的用户名或id");
		}
		return custWechatRelSV.deleteCustWechatByRel(req);
	}

}

