package com.zengshi.ecp.staff.service.busi.auth.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaff2GroupMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaff2RoleMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupKey;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.AuthStaff2RoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff2RoleKey;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 用户权限关系管理功能服务实现<br>
 * Date:2015年9月2日下午2:37:14  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AuthRelManageSVImpl implements IAuthRelManageSV {
    
    private static final String MODULE = AuthRelManageSVImpl.class.getName();
    
    @Resource
    private AuthStaff2GroupMapper staff2GroupMapper; //用户与用户组关系
    
    @Resource
    private AuthStaff2RoleMapper staff2RoleMapper; //用户与角色关系

    @Override
    public void saveStaffGroupRel(AuthStaff2Group save) throws BusinessException {
        save.setCreateTime(new Timestamp(System.currentTimeMillis()));//新增时间
        try {
            staff2GroupMapper.insertSelective(save);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "用户与用户组关系新增失败" });
        }
        //用户申请企业会员成功，如果存在普通会员关系则删除普通会员关系
        if(StaffConstants.StaffGroup.STAFF_SYS_ENTERPRISE.equals(save.getGroupId())){
            AuthStaff2GroupKey key = new AuthStaff2GroupKey();
            key.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_GENERAL);
            key.setStaffId(save.getStaffId());
            AuthStaff2Group find = findStaffGroupRelByKey(key);
            if(find!=null){//确保删除前关系存在
                deleteStaffGroupRel(find);
            }
        }
        
    }

    @Override
    public void deleteStaffGroupRel(AuthStaff2Group delete) throws BusinessException {
        if(delete==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaff2GroupKey key = new AuthStaff2GroupKey();
        ObjectCopyUtil.copyObjValue(delete, key, null, false);
        AuthStaff2GroupCriteria criteria = new AuthStaff2GroupCriteria();
        Criteria sql = criteria.createCriteria();
        if (delete.getStaffId() == null || delete.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        } else {
            sql.andStaffIdEqualTo(delete.getStaffId());
        }
        if (delete.getGroupId() != null && delete.getGroupId() != 0L) {
            sql.andGroupIdEqualTo(delete.getGroupId());
        }
        try {
            staff2GroupMapper.deleteByExample(criteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "用户与用户组关系删除失败" });
        }
    }

    @Override
    public AuthStaff2Group findStaffGroupRelByKey(AuthStaff2GroupKey key) throws BusinessException {
        AuthStaff2Group res = new AuthStaff2Group();
        try {
            res = staff2GroupMapper.selectByPrimaryKey(key);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "用户与用户组关系查询失败" });
        }
        return res;
    }

    @Override
    public List<AuthStaff2Group> listStaffGroupRel(AuthStaff2Group example)
            throws BusinessException {
        List<AuthStaff2Group> res = new ArrayList<AuthStaff2Group>();
        AuthStaff2GroupCriteria sgRelCriteria = new AuthStaff2GroupCriteria();
        AuthStaff2GroupCriteria.Criteria sql = sgRelCriteria.createCriteria();
        if(example.getGroupId()!=null){
            sql.andGroupIdEqualTo(example.getGroupId());
        }
        if(example.getStaffId()!=null){
            sql.andStaffIdEqualTo(example.getStaffId());
        }
        if(StringUtil.isNotBlank(example.getStaffClass())){
            sql.andStaffClassEqualTo(example.getStaffClass());
        }
        try {
            res = staff2GroupMapper.selectByExample(sgRelCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "用户与用户组关系查询失败" });
        }
        return res;
    }

    @Override
    public void saveStaffRoleRel(AuthStaff2Role save) throws BusinessException {
        if(save==null||save.getStaffId()==null||save.getRoleId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        save.setCreateTime(new Timestamp(System.currentTimeMillis()));//创建时间
        save.setUpdateTime(new Timestamp(System.currentTimeMillis()));//更新时间
        try {
            staff2RoleMapper.insertSelective(save);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "用户与角色关系新增失败" });
        }
    }

    @Override
    public AuthStaff2Role findStaffRoleRelByKey(AuthStaff2RoleKey key) throws BusinessException {
        AuthStaff2Role res = new AuthStaff2Role();
        try {
            res = staff2RoleMapper.selectByPrimaryKey(key);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "用户与角色关系查询失败" });
        }
        return res;
    }

    @Override
    public void deleteStaffRoleRel(AuthStaff2Role delete) throws BusinessException {
        if(delete==null||(delete.getStaffId()==null&&delete.getRoleId()==null)){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaff2RoleCriteria criteria = new AuthStaff2RoleCriteria();
        AuthStaff2RoleCriteria.Criteria sql = criteria.createCriteria();
        if(delete.getStaffId()!=null){
            sql.andStaffIdEqualTo(delete.getStaffId());
        }
        if(delete.getRoleId()!=null){
            sql.andRoleIdEqualTo(delete.getRoleId());
        }
        if(StringUtil.isNotBlank(delete.getSysCode())){
            sql.andSysCodeEqualTo(delete.getSysCode());
        }
        if(StringUtil.isNotBlank(delete.getStaffClass())){
            sql.andStaffClassEqualTo(delete.getStaffClass());
        }
        try {
            staff2RoleMapper.deleteByExample(criteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库，用户角色关系删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "用户与角色关系删除失败" });
        }
    }

    @Override
    public List<AuthStaff2Role> listStaffRoleRel(AuthStaff2Role example) throws BusinessException {
        List<AuthStaff2Role> res = new ArrayList<AuthStaff2Role>();
        AuthStaff2RoleCriteria srRelCriteria = new AuthStaff2RoleCriteria();
        AuthStaff2RoleCriteria.Criteria sql = srRelCriteria.createCriteria();
        if(example.getStaffId()!=null){
            sql.andStaffIdEqualTo(example.getStaffId());
        }
        if(example.getRoleId()!=null){
            sql.andRoleIdEqualTo(example.getRoleId());
        }
        if(StringUtil.isNotBlank(example.getStaffClass())){
            sql.andStaffClassEqualTo(example.getStaffClass());
        }
        if(StringUtil.isNotBlank(example.getSysCode())){
            sql.andSysCodeEqualTo(example.getSysCode());
        }
        try {
            res = staff2RoleMapper.selectByExample(srRelCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "用户与角色关系查询失败" });
        }
        return res;
    }


}

