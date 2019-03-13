package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.security.AuthPrivilegeResDTO;
import com.zengshi.ecp.server.front.security.LoginPwdCntReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 配合框架权限改造<br>
 * Date:2016-4-13下午10:17:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class AuthRSVImpl implements com.zengshi.ecp.server.front.security.IAuthRSV{

    @Resource
    private IAuthStaffRSV authStaffRSV;
    
    @Override
    public AuthPrivilegeResDTO findPrivilByStaffCode(String username, String staffClass) {
        com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO res = authStaffRSV.findPrivilByStaffCode(username, staffClass);
        if (res == null) {
            return null;
        }
        AuthPrivilegeResDTO securityRes = new AuthPrivilegeResDTO();
        ObjectCopyUtil.copyObjValue(res, securityRes, null, true);
        return securityRes;
    }

	@Override
	public int updateLoginPwdCnt(LoginPwdCntReqDTO staff) throws BusinessException {
		return authStaffRSV.updateStaffById(staff.getStaffLoginName(), staff.getLoginFlag());
	}

}

