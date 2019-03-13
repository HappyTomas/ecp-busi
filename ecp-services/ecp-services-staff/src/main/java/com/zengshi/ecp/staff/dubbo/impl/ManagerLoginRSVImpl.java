package com.zengshi.ecp.staff.dubbo.impl;


import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IManagerLoginRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IManagerLoginSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层管理人员登录SV实现类<br>
 * Date:2015-8-11下午4:22:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ManagerLoginRSVImpl implements IManagerLoginRSV{

    @Resource
    private IManagerLoginSV managerLoginSV;
    
    private static final String MODULE = ManagerLoginRSVImpl.class.getName();
    
    @Override
    public AuthStaffResDTO managerLogin(AuthStaffReqDTO authStaffReqDTO) throws BusinessException {
        //入参对象为空
        if (authStaffReqDTO == null) {
            LogUtil.info(MODULE, "入参对象不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        //登录用户名
        if (StringUtil.isBlank(authStaffReqDTO.getStaffCode())) {
            LogUtil.info(MODULE, "登录用户名不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"登录用户名"});
        }
        //登录密码
        if (StringUtil.isBlank(authStaffReqDTO.getStaffPasswd())) {
            LogUtil.info(MODULE, "登录密码不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"登录密码"});
        }
        return managerLoginSV.managerLogin(authStaffReqDTO);
    }

    @Override
    public int loginSuccess(Long staffId) throws BusinessException {
        //校验id是否有效
        if (staffId == null || staffId <= 0L) {
            throw new BusinessException("对不起，用户不存在！");
        }
        return managerLoginSV.loginSuccess(staffId);
    }
    
}

