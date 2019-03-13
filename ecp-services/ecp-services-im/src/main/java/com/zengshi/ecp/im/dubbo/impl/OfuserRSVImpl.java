package com.zengshi.ecp.im.dubbo.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.im.dubbo.dto.CustInfoImResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IOfuserRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.service.busi.interfaces.IOfuserSV;
import com.zengshi.ecp.im.service.util.ImUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

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
public class OfuserRSVImpl implements IOfuserRSV{

	@Resource
	private IOfuserSV ofuserSV;
	
	@Override
	public String saveOfuser(String username,Long staffId,Map<String, String> map) throws BusinessException {
		if (StringUtil.isBlank(username)) {
			throw new BusinessException("用户名不能为空。");
		}
		if (staffId == null || staffId == 0L) {
			throw new BusinessException("用户ID不能为空。");
		}
		String staffPwd = "";
		if (map != null) {
			staffPwd = map.get("staffPwd");
		}
	
		ImOfuserRelReqDTO req = new ImOfuserRelReqDTO();
		req.setStaffCode(username);
		req.setStaffId(staffId);
		/*2、调用业务方法、保存of用户与第三方系统用户的关系*/
		String ofStaffCode = ofuserSV.saveOfuser(req);
		return ofStaffCode;
	}

	@Override
	public CustInfoImResDTO findCustByOfuser(String ofStaffCode)
			throws BusinessException {
		return ofuserSV.findCustByOfuser(ofStaffCode);
	}

	@Override
	public ImOfuserRelResDTO findofuserByCust(ImOfuserRelReqDTO dto) throws BusinessException {
		return ofuserSV.findofuserByCust(dto);
	}

}

