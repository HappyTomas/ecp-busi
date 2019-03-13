package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.staff.dubbo.interfaces.IManagerLoginRSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 配合框架权限改造<br>
 * Date:2016-4-13下午10:09:33  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ManagerRSVImpl implements com.zengshi.ecp.server.front.security.IManagerRSV{

    @Resource
    private IManagerLoginRSV managerLoginRSV;
    
    @Override
    public void loginSuccess(Long id) {
        managerLoginRSV.loginSuccess(id);
    }

}

