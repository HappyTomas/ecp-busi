package com.zengshi.ecp.staff.service.common.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.common.AuthGroup2RoleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthPrivilegeMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthRole2PrivilegeMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthRoleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthStaffGroupMapper;
import com.zengshi.ecp.staff.dao.model.AuthGroup2Role;
import com.zengshi.ecp.staff.dao.model.AuthGroup2RoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege;
import com.zengshi.ecp.staff.dao.model.AuthPrivilegeCriteria;
import com.zengshi.ecp.staff.dao.model.AuthRole;
import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dao.model.AuthRole2PrivilegeCriteria;
import com.zengshi.ecp.staff.dao.model.AuthRoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroup;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroupCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IPrivlgManageSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月28日下午2:05:46  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class PrivlgManageSVImpl extends GeneralSQLSVImpl implements IPrivlgManageSV {
    
    private static final String MODULE = PrivlgManageSVImpl.class.getName();

    @Resource
    private AuthRoleMapper roleMapper;//角色表操作
    @Resource
    private AuthRole2PrivilegeMapper role2PrivilegeMapper;//角色与权限表操作
    @Resource
    private AuthStaffGroupMapper staffGroupMapper;//用户组定义表操作
    @Resource
    private AuthGroup2RoleMapper group2RoleMapper;//用户与角色关系表操作
    
    @Resource
    private AuthPrivilegeMapper authPrivilegeMapper; //资源权限
    
    @Resource(name = "seq_auth_privilege_id")
    private Sequence seqAuthPrivlg; //权限表sequence 
    

    @Override
    public List<AuthRole> listRole(List<Long> pRoleIdList)throws BusinessException {
        if(CollectionUtils.isEmpty(pRoleIdList))
        {
            return null;
        }
        AuthRoleCriteria criteria = new AuthRoleCriteria();
        criteria.createCriteria().andIdIn(pRoleIdList);
        List<AuthRole> result = null;
        try {
            result = roleMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return  result;
    }

    @Override
    public List<AuthRole2Privilege> listPrivil(List<Long> pRoleIdList)throws BusinessException {
        if(CollectionUtils.isEmpty(pRoleIdList))
        {
            return null;
        }
        AuthRole2PrivilegeCriteria criteria = new AuthRole2PrivilegeCriteria();
        criteria.createCriteria().andRoleIdIn(pRoleIdList);
        List<AuthRole2Privilege> result = null;
        try {
            result = role2PrivilegeMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();;
            throw e;
        }
        
        return  result;
    }

    @Override
    public long saveAuthPrivilege(AuthPrivilege ap) throws BusinessException {
        ap.setId(seqAuthPrivlg.nextValue());
        if(StringUtil.isBlank(ap.getStatus())){
            ap.setStatus(StaffConstants.Privilege.STATUS_ACTIVE);
        }
        try {
            authPrivilegeMapper.insertSelective(ap);
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        
        return ap.getId();
    }

    @Override
    public List<AuthPrivilege> listAuthPrivilege(List<String> sysCodes, String privlgType) throws BusinessException {
        List<AuthPrivilege> res = new ArrayList<AuthPrivilege>();
        AuthPrivilegeCriteria apCriteria = new AuthPrivilegeCriteria();
        AuthPrivilegeCriteria.Criteria sql = apCriteria.createCriteria();
        sql.andStatusEqualTo(StaffConstants.Privilege.STATUS_ACTIVE);
        if(StringUtil.isNotBlank(privlgType)){
            sql.andPrivilegeTypeEqualTo(privlgType);
        }
        if(CollectionUtils.isNotEmpty(sysCodes)){
            sql.andSysCodeIn(sysCodes);
        }
        
        try {
            res = authPrivilegeMapper.selectByExample(apCriteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "数据库查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        return res;
    }
    @Override
    public List<AuthStaffGroup> listGroup(List<Long> pGroupIdList)throws BusinessException {
        AuthStaffGroupCriteria criteria = new AuthStaffGroupCriteria();
        AuthStaffGroupCriteria.Criteria cc =criteria.createCriteria();
        cc.andIdIn(pGroupIdList);
        cc.andStatusEqualTo(StaffConstants.StaffGroup.STAFF_GROUP_STATUS_ACTIVE);//用户组有效标志
        
        List<AuthStaffGroup> result = null;
        try {
            result = staffGroupMapper.selectByExample(criteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "数据库查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        
        return result;
    }

    @Override
    public List<AuthGroup2Role> listGroup2Role(List<Long> pGroupIdList)throws BusinessException {
        
        AuthGroup2RoleCriteria criteria = new AuthGroup2RoleCriteria();
        criteria.createCriteria().andGroupIdIn(pGroupIdList);
        List<AuthGroup2Role> result = null;
        try {
            result = group2RoleMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return result;
    }

    @Override
    public void updateAuthPrivilegeById(AuthPrivilege update) throws BusinessException {
        if(update==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        try {
            authPrivilegeMapper.updateByPrimaryKeySelective(update);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库更新失败", e);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
    }

    @Override
    public void deleteAuthPrivilegeById(AuthPrivilegeReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthPrivilege delete = new AuthPrivilege();
        delete.setId(reqDto.getId());
        delete.setStatus(StaffConstants.Privilege.STATUS_INVALID);//设置失效
        try {
            updateAuthPrivilegeById(delete);
        } catch (Exception e) {
            LogUtil.error(MODULE, "逻辑删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{"逻辑删除权限"});
        }
        
    }

    @Override
    public AuthPrivilege findAuthPrivilegeById(Long id) throws BusinessException {
        if(id==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthPrivilege find = new AuthPrivilege();
        try {
            find = authPrivilegeMapper.selectByPrimaryKey(id);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"权限查询"});
        }
        return find;
    }

    @Override
    public PageResponseDTO<AuthPrivilegeResDTO> listAuthPrivilege(AuthPrivilegeReqDTO reqDto)
            throws BusinessException {
        PageResponseDTO<AuthPrivilegeResDTO> res = new PageResponseDTO<AuthPrivilegeResDTO>();
        AuthPrivilegeCriteria apCriteria = new AuthPrivilegeCriteria();
        AuthPrivilegeCriteria.Criteria sql = apCriteria.createCriteria();
        if(StringUtil.isNotBlank(reqDto.getStatus())){
            sql.andStatusEqualTo(reqDto.getStatus());
        }else{//缺省查询“有效”
            sql.andStatusEqualTo(StaffConstants.Privilege.STATUS_ACTIVE);
        }
        if(StringUtil.isNotBlank(reqDto.getRoleAdmin())){
            sql.andRoleAdminLike("%"+reqDto.getRoleAdmin()+"%");
        }
        if(StringUtil.isNotBlank(reqDto.getPrivilegeType())){
            sql.andPrivilegeTypeEqualTo(reqDto.getPrivilegeType());
        }
        if(StringUtil.isNotBlank(reqDto.getSysCode())){
            sql.andSysCodeEqualTo(reqDto.getSysCode());
        }
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        
        apCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        apCriteria.setLimitClauseCount(reqDto.getPageSize());
        
        res = super.queryByPagination(reqDto, apCriteria, false, new PaginationCallback<AuthPrivilege, AuthPrivilegeResDTO>() {

            @Override
            public List<AuthPrivilege> queryDB(BaseCriteria arg0) {
                return authPrivilegeMapper.selectByExample((AuthPrivilegeCriteria) arg0);
            }

            @Override
            public long queryTotal(BaseCriteria arg0) {
                return authPrivilegeMapper.countByExample((AuthPrivilegeCriteria) arg0);
            }

            @Override
            public AuthPrivilegeResDTO warpReturnObject(AuthPrivilege arg0) {
                AuthPrivilegeResDTO dto = new AuthPrivilegeResDTO();
                ObjectCopyUtil.copyObjValue(arg0, dto, null, false);
                return dto;
            }
            
        });
        
        return res;
    }
}

