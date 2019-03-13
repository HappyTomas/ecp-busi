package com.zengshi.ecp.staff.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupKey;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.AuthStaff2RoleKey;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2GroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2GroupResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthRelManageRSV;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 鏉冮檺鍏崇郴闆嗕腑瀹炵幇锛堢敤鎴蜂笌瑙掕壊銆佸垎缁勶級<br>
 * Date:2015骞�鏈�4鏃ヤ笅鍗�:28:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AuthRelManageRSVImpl implements IAuthRelManageRSV {

    @Resource
    private IAuthRelManageSV authRelManageSV;  //鐢ㄦ埛鏉冮檺鍏崇郴
    
    @Resource
    private IAuthManageSV authManageSV;
    
    @Override
    public void saveStaffGroupRel(AuthStaff2GroupReqDTO reqDto) throws BusinessException {
        AuthStaff2Group save = new AuthStaff2Group();
        ObjectCopyUtil.copyObjValue(reqDto, save, null, false);
        save.setCreateStaff(reqDto.getStaff().getId());
        authRelManageSV.saveStaffGroupRel(save);
    }

    @Override
    public void deleteStaffGroupRel(AuthStaff2GroupReqDTO reqDto) throws BusinessException {
        AuthStaff2Group delete = new AuthStaff2Group();
        ObjectCopyUtil.copyObjValue(reqDto, delete, null, false);
        authRelManageSV.deleteStaffGroupRel(delete);
    }

    @Override
    public AuthStaff2GroupResDTO findStaffGroupRelByKey(AuthStaff2GroupReqDTO reqDto)
            throws BusinessException {
        AuthStaff2GroupKey key = new AuthStaff2GroupKey();
        ObjectCopyUtil.copyObjValue(reqDto, key, null, false);
        AuthStaff2Group find = authRelManageSV.findStaffGroupRelByKey(key);
        AuthStaff2GroupResDTO res = new AuthStaff2GroupResDTO();
        ObjectCopyUtil.copyObjValue(find, res, null, false);
        return res;
    }

    @Override
    public List<AuthStaff2GroupResDTO> listStaffGroupRel(AuthStaff2GroupReqDTO reqDto)
            throws BusinessException {
        AuthStaff2Group example = new AuthStaff2Group();
        ObjectCopyUtil.copyObjValue(reqDto, example, null, false);
        List<AuthStaff2Group> list = authRelManageSV.listStaffGroupRel(example);
        List<AuthStaff2GroupResDTO> res = new ArrayList<AuthStaff2GroupResDTO>();
        if(CollectionUtils.isNotEmpty(list)){
            for (AuthStaff2Group rel : list) {
                AuthStaff2GroupResDTO dto = new AuthStaff2GroupResDTO();
                ObjectCopyUtil.copyObjValue(rel, dto, null, false);
                res.add(dto);
            }
        }
        
        return res;
    }

    @Override
    public void saveStaffRoleRel(AuthStaff2RoleReqDTO reqDto) throws BusinessException {
        AuthStaff2Role save = new AuthStaff2Role();
        ObjectCopyUtil.copyObjValue(reqDto, save, null, false);
        save.setCreateStaff(reqDto.getStaff().getId());
        authRelManageSV.saveStaffRoleRel(save);
    }

    @Override
    public AuthStaff2RoleResDTO findStaffRoleRelByKey(AuthStaff2RoleReqDTO reqDto)
            throws BusinessException {
        AuthStaff2RoleKey key = new AuthStaff2RoleKey();
        ObjectCopyUtil.copyObjValue(reqDto, key, null, false);
        AuthStaff2Role find = authRelManageSV.findStaffRoleRelByKey(key);
        AuthStaff2RoleResDTO res = new AuthStaff2RoleResDTO();
        ObjectCopyUtil.copyObjValue(find, res, null, false);
        return res;
    }

    @Override
    public void deleteStaffRoleRel(AuthStaff2RoleReqDTO reqDto) throws BusinessException {
        AuthStaff2Role delete = new AuthStaff2Role();
        ObjectCopyUtil.copyObjValue(reqDto, delete, null, false);
        authRelManageSV.deleteStaffRoleRel(delete);
    }

    @Override
    public List<AuthStaff2RoleResDTO> listStaffRoleRel(AuthStaff2RoleReqDTO reqDto)
            throws BusinessException {
        AuthStaff2Role example = new AuthStaff2Role();
        ObjectCopyUtil.copyObjValue(reqDto, example, null, false);
        List<AuthStaff2Role> list = authRelManageSV.listStaffRoleRel(example);
        List<AuthStaff2RoleResDTO> res = new ArrayList<AuthStaff2RoleResDTO>();
        if(CollectionUtils.isNotEmpty(list)){
            for (AuthStaff2Role rel : list) {
                AuthStaff2RoleResDTO dto = new AuthStaff2RoleResDTO();
                ObjectCopyUtil.copyObjValue(rel, dto, null, false);
                res.add(dto);
            }
        }
        
        return res;
    }

    @Override
    public List<AuthRoleResDTO> findAuthRoleList(AuthStaffResDTO req) throws BusinessException {
        return authManageSV.findAuthRoleList(req);
    }

}

