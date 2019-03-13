package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffGroupManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IStaffGroupManageSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo用户组功能服务实现<br>
 * Date:2015年9月17日下午4:31:51  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class StaffGroupManageRSVImpl implements IStaffGroupManageRSV {
    
    public static final String MODULE = StaffGroupManageRSVImpl.class.getName();
    
    @Resource
    private IStaffGroupManageSV staffGroupManageSV;

    @Override
    public long saveStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        if(null==reqDto.getRoleId()&&reqDto.getRoleId()==0){
            
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"roleId"});
        }
        return staffGroupManageSV.saveStaffGroup(reqDto);
    }

    @Override
    public void deleteStaffGroupById(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"groupId"});
        }
        staffGroupManageSV.deleteStaffGroupById(reqDto);
    }

    @Override
    public void updateStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        if(null==reqDto.getRoleId()&&reqDto.getRoleId()==0){
            
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"roleId"});
        }
        staffGroupManageSV.updateStaffGroup(reqDto);
    }

    @Override
    public AuthStaffGroupResDTO findStaffGroupById(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        AuthStaffGroupResDTO find = staffGroupManageSV.findStaffGroupById(reqDto.getId());
    
        return find;
    }

    @Override
    public PageResponseDTO<AuthStaffGroupResDTO> listAuthStaffGroup(AuthStaffGroupReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        return staffGroupManageSV.listAuthStaffGroup(reqDto);
    }
    
    
}

