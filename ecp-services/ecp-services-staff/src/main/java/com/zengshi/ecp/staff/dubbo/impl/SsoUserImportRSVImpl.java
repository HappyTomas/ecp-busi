package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoMsgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ISsoUserImportRSV;
import com.zengshi.ecp.staff.service.busi.sso.interfaces.ISsoUserImportSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层：sso登录数据同步接口<br>
 * Date:2015-10-9下午5:43:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class SsoUserImportRSVImpl implements ISsoUserImportRSV{
    
    private static final String MODULE = SsoUserImportRSVImpl.class.getName();
    @Resource
    private ISsoUserImportSV ssoUserImportSV;

    @Override
    public SsoUserInfoMsgResDTO saveStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException {
        return ssoUserImportSV.saveStaffInfo(dto);
    }

    @Override
    public SsoUserInfoMsgResDTO updateStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException {
        return ssoUserImportSV.updateStaffInfo(dto);
    }

    @Override
    public SsoUserInfoMsgResDTO changeStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException {
        return ssoUserImportSV.updateAndSaveStaffInfo(dto);
    }
  
   
}

