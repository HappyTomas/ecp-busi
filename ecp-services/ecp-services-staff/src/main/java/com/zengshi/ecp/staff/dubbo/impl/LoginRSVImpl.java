package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.security.LoginAccessLogReqDTO;
import com.zengshi.ecp.server.front.security.LoginLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 配合框架权限改造<br>
 * Date:2016-4-13下午10:11:42  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class LoginRSVImpl implements com.zengshi.ecp.server.front.security.ILoginRSV{

    @Resource
    private IAuthLoginRSV authLoginRSV;
    
    @Override
    public void updateOutLoginLog(LoginLogInfoReqDTO loginLogInfoReqDTO) {
        com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO req = new com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO();
        ObjectCopyUtil.copyObjValue(loginLogInfoReqDTO, req, null, true);
        authLoginRSV.updateOutLoginLog(req);
    }

    @Override
    public void insertLoginLog(LoginAccessLogReqDTO loginAccessLogReqDTO,
            LoginLogInfoReqDTO loginLogInfoReqDTO) {
        com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO req = new com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO();
        ObjectCopyUtil.copyObjValue(loginLogInfoReqDTO, req, null, true);
        
        com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogReqDTO access = new com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogReqDTO();
        ObjectCopyUtil.copyObjValue(loginAccessLogReqDTO, access, null, true);
        authLoginRSV.insertLoginLog(access, req);
    }

}

