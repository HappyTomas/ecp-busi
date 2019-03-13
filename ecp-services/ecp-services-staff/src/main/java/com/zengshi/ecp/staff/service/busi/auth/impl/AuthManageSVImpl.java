package com.zengshi.ecp.staff.service.busi.auth.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.util.CollectionUtil;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaff2GroupMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaff2RoleMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.AuthStaff2RoleCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月28日下午2:07:34  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AuthManageSVImpl extends GeneralSQLSVImpl implements IAuthManageSV {
    @Resource
    private AuthStaffMapper authStaffMapper;//用户登陆表操作
    @Resource
    private AuthStaff2RoleMapper staff2RoleMapper;//账号与角色关系表操作
    @Resource
    private AuthStaff2GroupMapper staff2GroupMapper;//用户与用户组关系表操作
    @Resource
    private IRoleManageSV roleManageSV;
    
    /**
     * 
     * TODO 通过用户ID，查找用户登陆信息. 
     * @see com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV#findStaff(java.lang.Long)
     */
    @Override
    public AuthStaff findStaff(Long pStaffID) throws BusinessException {
        if(pStaffID == null)
        {
            return null;
        }
        AuthStaff authStaff =null;
        try {
            authStaff = authStaffMapper.selectByPrimaryKey(pStaffID);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return authStaff;
    }
    
    /**
     * 
     * TODO 通过用户ID查询该用户的角色列表. 
     * @see com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV#listRole(java.lang.Long)
     */
    @Override
    public List<AuthStaff2Role> listRole(Long pStaffID) throws BusinessException {
        if(pStaffID == null)
        {
            return null;
        }
        AuthStaff2RoleCriteria criteria = new AuthStaff2RoleCriteria();
        criteria.createCriteria().andStaffIdEqualTo(pStaffID);
        List<AuthStaff2Role> result = null;
        try {
            result = staff2RoleMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return  result;
    }
    
    /**
     * 
     * TODO 通过用户ID，查找用户组列表. 
     * @see com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV#listGroup(java.lang.Long)
     */
    @Override
    public List<AuthStaff2Group> listGroup(Long pStaffID) throws BusinessException {
        
        AuthStaff2GroupCriteria criteria = new AuthStaff2GroupCriteria();
        criteria.createCriteria().andStaffIdEqualTo(pStaffID);
        List<AuthStaff2Group> result = null;
        try {
            result = staff2GroupMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return  result;
    }

    @Override
    public List<AuthRoleResDTO> findAuthRoleList(AuthStaffResDTO req) throws BusinessException {
        AuthStaff2GroupCriteria c = new AuthStaff2GroupCriteria();
        c.createCriteria().andStaffIdEqualTo(req.getId());
        List<AuthStaff2Group> list = staff2GroupMapper.selectByExample(c);
        if (CollectionUtils.isEmpty(list)) {
        	return null;
        } else {
        	List<Long> grouplist = new ArrayList<Long>();
            for (AuthStaff2Group authStaff2Group : list) {
                grouplist.add(authStaff2Group.getGroupId());
            }
            return roleManageSV.findRoleByGroupId(grouplist);
        }
        
    }
}

