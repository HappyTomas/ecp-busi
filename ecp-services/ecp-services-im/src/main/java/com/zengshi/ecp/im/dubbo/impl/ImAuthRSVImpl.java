package com.zengshi.ecp.im.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IimAuthRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.service.common.interfaces.IStaffHotlineSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.security.AuthPrivilegeResDTO;
import com.zengshi.ecp.server.front.security.LoginPwdCntReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: im登录<br>
 * Date:2016-4-13涓嬪崍10:17:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.6
 */
public class ImAuthRSVImpl implements IimAuthRSV{

    @Resource
    private IAuthStaffRSV authStaffRSV;
    
    @Resource
    private IStaffHotlineSV staffHotlineSV;
    
    @Override
    public AuthPrivilegeResDTO findPrivilByStaffCode(String username, String staffClass) {
    	ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
    	dto.setStaffCode(username);
    	dto.setStaffClass(staffClass);
    	dto.setStatus(ImConstants.STATE_1);
    	ImStaffHotlineResDTO hotlineResDTO = staffHotlineSV.getHotlineByStaff(dto);
    	
    	if(hotlineResDTO!=null){
        com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO res = authStaffRSV.findPrivilByStaffCode(username, hotlineResDTO.getStaffClass());
        if (res == null) {
            return null;
         }
        AuthPrivilegeResDTO securityRes = new AuthPrivilegeResDTO();
        ObjectCopyUtil.copyObjValue(res, securityRes, null, true);
        return securityRes;
    	}
      return null;
    }

	@Override
	public int updateLoginPwdCnt(LoginPwdCntReqDTO staff) throws BusinessException {
		return authStaffRSV.updateStaffById(staff.getStaffLoginName(), staff.getLoginFlag());
	}

}

