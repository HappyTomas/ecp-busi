package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdmin;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthAdminRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAdminManageSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 管理员功能管理实现类<br>
 * Date:2015年9月21日下午12:58:34  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AuthAdminRSVImpl implements IAuthAdminRSV {

    private static final String MODULE = AuthAdminRSVImpl.class.getName();
    
    @Resource
    private IAdminManageSV adminManageSV;
    
    @Override
    public long saveAuthStaffAdmin(AuthStaffAdminReqDTO save) throws BusinessException {
        return adminManageSV.saveAuthStaffAdmin(save);
    }

    @Override
    public AuthStaffAdminResDTO findAuthStaffAdminById(AuthStaffAdminReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaffAdminResDTO res = new AuthStaffAdminResDTO();
        AuthStaffAdmin find = adminManageSV.findAuthStaffAdminById(reqDto.getId());
        ObjectCopyUtil.copyObjValue(find, res, null, false);
        
        return res;
    }

    @Override
    public AuthStaffAdminResDTO findAuthStaffAdminByCode(AuthStaffAdminReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null||StringUtil.isBlank(reqDto.getStaffCode())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaffAdminResDTO res = new AuthStaffAdminResDTO();
        AuthStaffAdmin find = adminManageSV.findAuthStaffAdminByCode(reqDto.getStaffCode());
        ObjectCopyUtil.copyObjValue(find, res, null, false);
        
        return res;
    }

    @Override
    public void updateAuthStaffAdminById(AuthStaffAdminReqDTO update) throws BusinessException {
        if(update==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        adminManageSV.updateAuthStaffAdminById(update);
    }

    @Override
    public void deleteAuthStaffAdminById(AuthStaffAdminReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        adminManageSV.deleteAuthStaffAdminById(reqDto.getId());
    }

    @Override
    public PageResponseDTO<AuthStaffAdminResDTO> listAuthStaffAdmin(AuthStaffAdminReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        return adminManageSV.listAuthStaffAdmin(reqDto);
    }

    @Override
    public void updateAuthStaffAdminFlag(AuthStaffAdminReqDTO reqDto) throws BusinessException {
        adminManageSV.updateAuthStaffAdminFlag(reqDto);
    }

}

