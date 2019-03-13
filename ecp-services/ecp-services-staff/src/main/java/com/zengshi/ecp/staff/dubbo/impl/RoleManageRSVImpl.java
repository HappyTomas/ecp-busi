package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IRoleManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo角色管理功能实现<br>
 * Date:2015年9月17日下午4:31:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class RoleManageRSVImpl implements IRoleManageRSV {

    public static final String MODULE = IRoleManageRSV.class.getName();
    
    @Resource
    private IRoleManageSV roleManageSV;

    @Override
    public long saveAuthRole(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        return roleManageSV.saveAuthRole(reqDto);
    }

    @Override
    public void deleteAuthRoleById(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        roleManageSV.deleteAuthRoleById(reqDto);
    }

    @Override
    public void updateAuthRole(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        roleManageSV.updateAuthRole(reqDto);
    }

    @Override
    public AuthRoleResDTO findAuthRoleById(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        return roleManageSV.findAuthRoleById(reqDto.getId());
    }

    @Override
    public PageResponseDTO<AuthRoleResDTO> listAuthRole(AuthRoleReqDTO reqDto)
            throws BusinessException {
        
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        return roleManageSV.listAuthRole(reqDto);
    }
    
    
}

