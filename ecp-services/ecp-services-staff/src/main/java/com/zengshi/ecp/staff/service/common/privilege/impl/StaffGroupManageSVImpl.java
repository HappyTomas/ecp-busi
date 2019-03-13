package com.zengshi.ecp.staff.service.common.privilege.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.common.AuthGroup2RoleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthStaffGroupMapper;
import com.zengshi.ecp.staff.dao.model.AuthGroup2Role;
import com.zengshi.ecp.staff.dao.model.AuthGroup2RoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroup;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroupCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IStaffGroupManageSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月29日上午10:10:42  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class StaffGroupManageSVImpl extends GeneralSQLSVImpl implements IStaffGroupManageSV {
    
    private static String MODULE = StaffGroupManageSVImpl.class.getName();

    @Resource
    private AuthStaffGroupMapper authStaffGroupMapper;  //用户组
    
    @Resource
    private AuthGroup2RoleMapper authGroup2RoleMapper;
    
    @Resource
    private IRoleManageSV roleManageSV;
    
    @Resource(name = "seq_auth_staff_group_id")
    private Sequence seqAuthStaffGroup; //用户组表sequence
    
    @Override
    public long saveStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        } 
        Long id = seqAuthStaffGroup.nextValue();
        AuthStaffGroup asg = new AuthStaffGroup();
        ObjectCopyUtil.copyObjValue(reqDto, asg, null, false);
        asg.setId(id);
        asg.setCreateStaff(reqDto.getStaff().getId());
        asg.setCreateTime(new Timestamp(System.currentTimeMillis()));
        asg.setUpdateStaff(reqDto.getStaff().getId());
        asg.setUpdateTime(new Timestamp(System.currentTimeMillis())); 
        //验证分组名称是否重复
        checkStaffGroupName(asg);
        try {
            authStaffGroupMapper.insertSelective(asg);
            //新增分组与角色关系表
            AuthGroup2Role authGroup2Role = new AuthGroup2Role();
            authGroup2Role.setGroupId(id);
            authGroup2Role.setRoleId(reqDto.getRoleId());
            authGroup2Role.setCreateTime(DateUtil.getSysDate());
            authGroup2Role.setCreateStaff(reqDto.getCreateStaff());
            authGroup2Role.setStaffClass(reqDto.getStaffClass());
            AuthRoleResDTO authRole = roleManageSV.findAuthRoleById(reqDto.getRoleId());
            authGroup2Role.setSysCode(authRole.getSysCode());
            authGroup2RoleMapper.insertSelective(authGroup2Role);
        } catch (Exception e) {
            LogUtil.error(MODULE, "新增用户组失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "新增用户组失败" });
        }
        return asg.getId();
    }
    
    public void checkStaffGroupName(AuthStaffGroup asg) throws BusinessException{
        
        AuthStaffGroupCriteria c = new AuthStaffGroupCriteria();
        c.createCriteria().andGroupNameEqualTo(asg.getGroupName());
        List<AuthStaffGroup> list = authStaffGroupMapper.selectByExample(c);
        if(!CollectionUtils.isEmpty(list)){
            if(asg.getId().longValue()!=list.get(0).getId().longValue()){
            throw new BusinessException(StaffConstants.AUTH_STAFF_GROUP_NAME); 
            }
        }
    }
    
    @Override
    public void deleteStaffGroupById(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto.getId()==null||0==reqDto.getId()){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //TODO 事务
        //1.删除用户组
        /*try {
            authStaffGroupMapper.deleteByPrimaryKey(id);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "删除用户组失败" });
        }*/
       
        try {
            updateStaffGroup(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "逻辑删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "用户组失败" });
        }
        //2.删除关系 TODO
        //2.1 删除角色与用户组关系
        //2.2 删除用户与用户组关系
        
    }

    @Override
    public void updateStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        reqDto.setUpdateTime(DateUtil.getSysDate());
        AuthStaffGroup asg = new AuthStaffGroup();
        ObjectCopyUtil.copyObjValue(reqDto, asg, null, false);
        asg.setUpdateStaff(reqDto.getStaff().getId());
        //校验渠道分组名称重复
        checkStaffGroupName(asg);
        try {
            authStaffGroupMapper.updateByPrimaryKeySelective(asg);
            if(null!=reqDto.getRoleId()&&0!=reqDto.getRoleId()){
            //新增分组与角色关系表
            AuthGroup2Role authGroup2Role = new AuthGroup2Role();
            authGroup2Role.setRoleId(reqDto.getRoleId());
            authGroup2Role.setUpdateTime(DateUtil.getSysDate());
            authGroup2Role.setUpdateStaff(reqDto.getStaff().getId());
            AuthGroup2RoleCriteria c = new AuthGroup2RoleCriteria();
            c.createCriteria().andGroupIdEqualTo(asg.getId());
            authGroup2RoleMapper.updateByExampleSelective(authGroup2Role, c);
            }
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "更新失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{ "更新用户组失败" });
        }
    }

    @Override
    public AuthStaffGroupResDTO findStaffGroupById(Long id) throws BusinessException {
        AuthStaffGroup asg = null;
        AuthStaffGroupResDTO res = new AuthStaffGroupResDTO();
        try {
            asg = authStaffGroupMapper.selectByPrimaryKey(id);
            ObjectCopyUtil.copyObjValue(asg, res, null, false);
            //查询对应的roleId
            AuthGroup2RoleCriteria c = new AuthGroup2RoleCriteria();
            c.createCriteria().andGroupIdEqualTo(asg.getId());
            List<AuthGroup2Role> list = authGroup2RoleMapper.selectByExample(c);
            if(!CollectionUtils.isEmpty(list)&&list.size()==1){
                res.setRoleId(list.get(0).getRoleId());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "查询用户组失败" });
        }
        return res;
    }

    @Override
    public PageResponseDTO<AuthStaffGroupResDTO> listAuthStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException {
        PageResponseDTO<AuthStaffGroupResDTO> res = new PageResponseDTO<AuthStaffGroupResDTO>();
        AuthStaffGroupCriteria asgCriteria = new AuthStaffGroupCriteria();
        AuthStaffGroupCriteria.Criteria sql = asgCriteria.createCriteria();
        if(StringUtil.isNotBlank(reqDto.getGroupFlag())){
            sql.andGroupFlagEqualTo(reqDto.getGroupFlag());
        }
        if(StringUtil.isNotBlank(reqDto.getStatus())){
            sql.andStatusEqualTo(reqDto.getStatus());
        }
        if(StringUtil.isNotBlank(reqDto.getStaffClass())){
            sql.andStaffClassEqualTo(reqDto.getStaffClass());
        }
        if(StringUtil.isNotBlank(reqDto.getGroupName())){
            sql.andGroupNameLike("%"+reqDto.getGroupName()+"%");
        }
        if(null!=reqDto.getRoleId()&&reqDto.getRoleId()!=0){
	        AuthGroup2RoleCriteria c = new AuthGroup2RoleCriteria();
	        c.createCriteria().andRoleIdEqualTo(reqDto.getRoleId());
	        List<AuthGroup2Role> list = authGroup2RoleMapper.selectByExample(c);
	        List<Long> values = new ArrayList<Long>();
	        if(CollectionUtils.isNotEmpty(list)){
		        for (AuthGroup2Role authGroup2Role : list) {
		            values.add(authGroup2Role.getGroupId());
		        }
		    }else{
		    	values.add(0L);//没有关联数据，设置“0”，使返回结果集为空
		    }
		    sql.andIdIn(values);
	        
        }
        asgCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        asgCriteria.setLimitClauseCount(reqDto.getPageSize());
        res = super.queryByPagination(reqDto, asgCriteria, false, new PaginationCallback<AuthStaffGroup, AuthStaffGroupResDTO>() {

            @Override
            public List<AuthStaffGroup> queryDB(BaseCriteria criteria) {
                return authStaffGroupMapper.selectByExample((AuthStaffGroupCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return authStaffGroupMapper.countByExample((AuthStaffGroupCriteria) criteria);
            }

            @Override
            public AuthStaffGroupResDTO warpReturnObject(AuthStaffGroup t) {
                AuthStaffGroupResDTO dto = new AuthStaffGroupResDTO();
                AuthGroup2RoleCriteria c = new AuthGroup2RoleCriteria();
                c.createCriteria().andGroupIdEqualTo(t.getId());
                List<AuthGroup2Role> list = authGroup2RoleMapper.selectByExample(c);
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                if(!CollectionUtils.isEmpty(list)){
                AuthRoleResDTO authRole = roleManageSV.findAuthRoleById(list.get(0).getRoleId());
                dto.setRoleName(authRole.getRoleName());
                }
                dto.setStaffClass(BaseParamUtil.fetchParamValue("SYS_STAFF_CLASS", dto.getStaffClass()));
                dto.setGroupFlag(BaseParamUtil.fetchParamValue("SYS_GROUP_FLAG", dto.getGroupFlag()));
                return dto;
            }
            
        });
        
        return res;
    }
    
    
    
    
    

}

