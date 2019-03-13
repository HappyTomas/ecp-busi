package com.zengshi.ecp.im.service.busi.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.jivesoftware.smack.XMPPException;

import com.zengshi.ecp.im.dao.mapper.busi.ImOfuserRelIdxMapper;
import com.zengshi.ecp.im.dao.mapper.busi.ImOfuserRelMapper;
import com.zengshi.ecp.im.dao.model.ImOfuserRel;
import com.zengshi.ecp.im.dao.model.ImOfuserRelIdx;
import com.zengshi.ecp.im.dubbo.dto.CustInfoImResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelResDTO;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.service.busi.interfaces.IOfuserSV;
import com.zengshi.ecp.im.service.common.interfaces.IOpenFireUserSV;
import com.zengshi.ecp.im.service.util.ImUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-im-server <br>
 * Description: openfire用户相关接口实现类<br>
 * Date:2016年8月10日下午5:18:05  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6
 */
public class OfuserSVImpl implements IOfuserSV{

	@Resource
	private ImOfuserRelMapper imOfuserRelMapper;
	
	@Resource
	private ImOfuserRelIdxMapper imOfuserRelIdxMapper;
	
	@Resource
	private ICustManageRSV custManageRSV;
	
	@Resource
	private IOpenFireUserSV openFireUserSV;
	
	private static String MODULE = OfuserSVImpl.class.getName();
	
	@Override
	public String saveOfuser(ImOfuserRelReqDTO req) throws BusinessException {
		Map<String, String> map = new HashMap<String, String>();
		//openfire的用户，不支持@，所以如果其他平台过来的用户里有@，全替换成两个whl
		String ofStaffCode = "user_"+req.getStaffCode().replaceAll("@", "whl");
		try {
			/*ImUtil.addAccountManager(ofStaffCode,
					ImConstants.IM_USER_DEFAULT_PASSWORD, map);*/
			openFireUserSV.addOfUser(ofStaffCode, ImConstants.IM_USER_DEFAULT_PASSWORD);
			/*1、保存索引关系*/
			ImOfuserRelIdx idx = new ImOfuserRelIdx();
			idx.setOfStaffCode(ofStaffCode);
			idx.setStaffCode(req.getStaffCode());
			idx.setStaffId(req.getStaff().getId());
			imOfuserRelIdxMapper.insert(idx);
			
			/*2、保存关系数据*/
			ImOfuserRel record = new ImOfuserRel();
			ObjectCopyUtil.copyObjValue(req, record, null, false);
			record.setOfStaffCode(ofStaffCode);
			record.setCreateTime(DateUtil.getSysDate());
			imOfuserRelMapper.insert(record);
		} catch (BusinessException e) {
			LogUtil.debug(MODULE, e.getMessage(), e);
		}
		return ofStaffCode ;
	}

	@Override
	public CustInfoImResDTO findCustByOfuser(String ofStaffCode) throws BusinessException {
		ImOfuserRelIdx record = imOfuserRelIdxMapper.selectByPrimaryKey(ofStaffCode);
		if (record != null && StringUtil.isNotBlank(record.getStaffCode())) {
			CustInfoResDTO res = custManageRSV.findCustInfoById(record.getStaffId());
			CustInfoImResDTO im = new CustInfoImResDTO();
			ObjectCopyUtil.copyObjValue(res, im, null, false);
			return im;
		} else {
			return null;
		}
	}

	@Override
	public ImOfuserRelResDTO findofuserByCust(ImOfuserRelReqDTO dto) throws BusinessException {
		ImOfuserRelResDTO dto2 = new ImOfuserRelResDTO();
		ImOfuserRel imOfuserRel = imOfuserRelMapper.selectByPrimaryKey(dto.getStaffCode());
		String ofStaffCode="";
		if(null==imOfuserRel){
			ImOfuserRelReqDTO req = new ImOfuserRelReqDTO();
			req.setStaffCode(dto.getStaffCode());
			req.setStaffId(dto.getStaffId());
			req.setCreateStaff(dto.getStaffId());
			req.setCreateTime(DateUtil.getSysDate());
			ofStaffCode = this.saveOfuser(req);
			dto2.setOfStaffCode(ofStaffCode);
		}else{
			dto2.setOfStaffCode(imOfuserRel.getOfStaffCode());
		}
		
	
		dto2.setStaffCode(dto.getStaffCode());
		dto.setStaffId(dto.getStaffId());
		return dto2;
	}

}

