package com.zengshi.ecp.staff.service.common.privilege.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.common.AuthGroup2RoleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthPrivilege2MenuMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthPrivilege2RuleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthRole2PrivilegeMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthRoleMapper;
import com.zengshi.ecp.staff.dao.model.AuthGroup2Role;
import com.zengshi.ecp.staff.dao.model.AuthGroup2RoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Menu;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2MenuCriteria;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Rule;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2RuleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthRole;
import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dao.model.AuthRole2PrivilegeCriteria;
import com.zengshi.ecp.staff.dao.model.AuthRoleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IDataAuthManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IMenuManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;
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
 * Description: 角色管理实现<br>
 * <pre>
 *  错误码：
 * </pre>
 * Date:2015年8月29日上午10:10:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class RoleManageSVImpl extends GeneralSQLSVImpl implements IRoleManageSV {
    
    private static final String MODULE = RoleManageSVImpl.class.getName();
    
    @Resource
    private AuthRoleMapper authRoleMapper; //角色
    
    @Resource
    private AuthRole2PrivilegeMapper rolePrivlgRelMapper;  //角色权限关系
    
    @Resource
    private AuthPrivilege2MenuMapper privilegeMenuRelMapper; //权限菜单

    @Resource
    private AuthPrivilege2RuleMapper privilegeRuleRelMapper; //权限规则
    
    @Resource
    private IAuthRelManageSV authRelManageSV; //用户相关权限关系
    
    @Resource
    private IMenuManageSV menuManageSV; //菜单服务
    
    @Resource
    private IDataAuthManageSV dataAuthManageSV; //权限规则服务
    
    @Resource
    private AuthGroup2RoleMapper authGroup2RoleMapper;
    
    @Resource(name = "seq_auth_role_id")
    private Sequence seqAuthRole; //角色表sequence

    @Override
    public long saveAuthRole(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //1.校验
        //1.1 角色名重复
        AuthRoleReqDTO findRoleDto = new AuthRoleReqDTO();
        findRoleDto.setRoleName(reqDto.getRoleName());
        AuthRoleResDTO findRole = findAuthRoleByName(findRoleDto);
        if(findRole!=null){
            throw new BusinessException(StaffConstants.Role.E_ROLE_NAME_EXIST);
        }
        //2.新增
        AuthRole ar = new AuthRole();
        ObjectCopyUtil.copyObjValue(reqDto, ar, null, false);
        ar.setId(seqAuthRole.nextValue());
        ar.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ar.setCreateStaff(reqDto.getStaff().getId());
        ar.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        ar.setUpdateStaff(reqDto.getStaff().getId());
        if(StringUtil.isBlank(ar.getStatus())){//缺省为有效
            ar.setStatus(StaffConstants.Auth.ROLE_STATUS_ACTIVE);
        }
        ar.setRoleOrder(countAuthRole());//缺省排序值为记录总数
        try {
            authRoleMapper.insertSelective(ar);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据保存异常", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "新增角色异常" });
        }
        LogUtil.info(MODULE, "新增角色:"+ar.getId());
        //3.角色与“菜单的权限”关系
        if(StringUtil.isNotBlank(reqDto.getPrivlgMenu())){
            String[] arrPrivlg = reqDto.getPrivlgMenu().split(",");
            for (String privlg : arrPrivlg) {
                AuthRole2Privilege ar2p = new AuthRole2Privilege();
                ar2p.setPrivilegeId(Long.valueOf(privlg));
                ar2p.setRoleId(ar.getId());
                ar2p.setCreateStaff(String.valueOf(reqDto.getStaff().getId()));
                savePrivilegeRoleRel(ar2p);
            }
        }
        //4.角色与“规则的权限”关系
        if(StringUtil.isNotBlank(reqDto.getPrivlgRule())){
            String[] arrPrivlg = reqDto.getPrivlgRule().split(",");
            for (String privlg : arrPrivlg) {
                AuthRole2Privilege ar2p = new AuthRole2Privilege();
                ar2p.setPrivilegeId(Long.valueOf(privlg));
                ar2p.setRoleId(ar.getId());
                ar2p.setCreateStaff(String.valueOf(reqDto.getStaff().getId()));
                savePrivilegeRoleRel(ar2p);
            }
        }
        
        return ar.getId();
    }

    @Override
    public void deleteAuthRoleById(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null||StringUtil.isBlank(reqDto.getSysCode())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        AuthRole ar = new AuthRole();
        ObjectCopyUtil.copyObjValue(reqDto, ar, null, false);
        ar.setUpdateStaff(reqDto.getStaff().getId());
        ar.setUpdateTime(DateUtil.getSysDate());
        ar.setSysCode(null);//“所属子系统”不可修改
        ar.setStatus(reqDto.getStatus());
        try {
            authRoleMapper.updateByPrimaryKeySelective(ar);
        } catch (Exception e) {
            LogUtil.error(MODULE, "逻辑删除异常", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "角色异常" });
        }
        /*
         * 20151229001 修改 linby 开始
         * 此方法为逻辑删除，保留权限关系，删除用户相关关系
         * 
         * 20151229001 结束
         */
        if (StaffConstants.Role.ROLE_STATUS_INVALID.equals(reqDto.getStatus())) {
        	AuthStaff2Role staffRoleRel = new AuthStaff2Role();
            staffRoleRel.setRoleId(reqDto.getId());
            staffRoleRel.setSysCode(reqDto.getSysCode());
            authRelManageSV.deleteStaffRoleRel(staffRoleRel);
        }
        
    }

    @Override
    public void updateAuthRole(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthRole ar = new AuthRole();
        ObjectCopyUtil.copyObjValue(reqDto, ar, null, false);
        ar.setUpdateStaff(reqDto.getStaff().getId());
        ar.setUpdateTime(DateUtil.getSysDate());
        ar.setSysCode(null);//“所属子系统”不可修改
        try {
            authRoleMapper.updateByPrimaryKeySelective(ar);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据修改异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{ "修改角色异常" });
        }
        //修改权限关系（菜单、规则）
        //a.删除原有关系
        AuthRole2Privilege delRel = new AuthRole2Privilege();
        delRel.setRoleId(reqDto.getId());
        deletePrivilegeRoleRel(delRel);

        //b.建立新的关系
        if (reqDto.getPrivlgMenu() == null) {
        	reqDto.setPrivlgMenu("");
        }
        if (reqDto.getPrivlgRule() == null) {
        	reqDto.setPrivlgRule("");
        }
        String[] arrPrivlg = reqDto.getPrivlgMenu().split(",");
        String[] arrPrivlg2 = reqDto.getPrivlgRule().split(",");
        arrPrivlg = (String[])ArrayUtils.addAll(arrPrivlg, arrPrivlg2);
        if(arrPrivlg.length>0){
	        for (String privlg : arrPrivlg) {
	        	if(StringUtil.isBlank(privlg)){
	        		continue;
	        	}
	            AuthRole2Privilege ar2p = new AuthRole2Privilege();
	            ar2p.setPrivilegeId(Long.valueOf(privlg));
	            ar2p.setRoleId(ar.getId());
	            ar2p.setCreateStaff(String.valueOf(reqDto.getStaff().getId()));
	            savePrivilegeRoleRel(ar2p);
	        }
        }
    }

    @Override
    public AuthRoleResDTO findAuthRoleById(Long id) throws BusinessException {
        if(id==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthRoleResDTO res = new AuthRoleResDTO();
        AuthRole ar = null;
        try {
            ar = authRoleMapper.selectByPrimaryKey(id);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询异常", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "查找角色异常" });
        }
        if(ar!=null){
            //基本信息
            ObjectCopyUtil.copyObjValue(ar, res, null, false);
            //权限（菜单） ///考虑使用复杂的sql（联表）查询获取结果
            AuthRole2Privilege rpRel = new AuthRole2Privilege();
            rpRel.setRoleId(res.getId());
            List<AuthRole2Privilege> listrpRel = listPrivilegeRoleRel(rpRel);
            if(CollectionUtils.isNotEmpty(listrpRel)){
                StringBuffer sbMenu = new StringBuffer(); //菜单相关
                StringBuffer sbMenuValue = new StringBuffer(); //菜单相关
                StringBuffer sbPrivlgMenu = new StringBuffer(); //菜单相关
                StringBuffer sbRule = new StringBuffer(); //规则相关
                StringBuffer sbRuleValue = new StringBuffer(); //规则相关
                StringBuffer sbPrivlgRule = new StringBuffer(); //规则相关
                
                List<Long> listp = new ArrayList<Long>();
                for (AuthRole2Privilege authRole2Privilege : listrpRel) {
                    listp.add(authRole2Privilege.getPrivilegeId());
                }
                //1.权限菜单
                AuthPrivilege2MenuCriteria pmRelCriteria = new AuthPrivilege2MenuCriteria();
                pmRelCriteria.createCriteria().andPrivilegeIdIn(listp).andSysCodeEqualTo(res.getSysCode());
                List<AuthPrivilege2Menu> listpmRel = privilegeMenuRelMapper.selectByExample(pmRelCriteria);
                if(CollectionUtils.isNotEmpty(listpmRel)){//有菜单
                    for (AuthPrivilege2Menu authPrivilege2Menu : listpmRel) {
                        AuthMenuReqDTO reqMenu = new AuthMenuReqDTO();
                        reqMenu.setId(authPrivilege2Menu.getMenuId());
                        AuthMenuResDTO menu = menuManageSV.findAuthMenuById(reqMenu);
                        if(menu!=null){
                            sbMenu.append(",").append(menu.getId());
                            sbMenuValue.append(",").append(menu.getMenuTitle());
                            sbPrivlgMenu.append(",").append(authPrivilege2Menu.getPrivilegeId());
                        }
                    }
                    if(StringUtil.isNotBlank(sbMenu.toString())){
	                    res.setMenu(sbMenu.substring(1));
	                    res.setMenuValue(sbMenuValue.substring(1));
	                    res.setPrivlgMenu(sbPrivlgMenu.substring(1));
                    }
                }
                
                //2.权限规则
                AuthPrivilege2RuleCriteria prRelCriteria = new AuthPrivilege2RuleCriteria();
                prRelCriteria.createCriteria().andPrivilegeIdIn(listp).andSysCodeEqualTo(res.getSysCode());
                List<AuthPrivilege2Rule> listprRel = privilegeRuleRelMapper.selectByExample(prRelCriteria);
                if(CollectionUtils.isNotEmpty(listprRel)){//有规则
                	for (AuthPrivilege2Rule authPrivilege2Rule : listprRel) {
                		DataAuthReqDTO reqRule = new DataAuthReqDTO();
                		reqRule.setId(authPrivilege2Rule.getRuleId());
                		DataAuthResDTO rule = dataAuthManageSV.findDataAuthById(reqRule);
                		if(rule!=null){
                			sbRule.append(",").append(rule.getId());
                			sbRuleValue.append(",").append(rule.getName());
                			sbPrivlgRule.append(",").append(authPrivilege2Rule.getPrivilegeId());
                		}
					}
                	if(StringUtil.isNotBlank(sbRule.toString())){
                		res.setRule(sbRule.substring(1));
                		res.setRuleValue(sbRuleValue.substring(1));
                		res.setPrivlgRule(sbPrivlgRule.substring(1));
                	}
                }
            }
            
            return res;
        }else {
            return null;
        }
    }

    @Override
    public PageResponseDTO<AuthRoleResDTO> listAuthRole(AuthRoleReqDTO reqDto) throws BusinessException {
        PageResponseDTO<AuthRoleResDTO> res = new PageResponseDTO<AuthRoleResDTO>();
        AuthRoleCriteria arCriteria = new AuthRoleCriteria();
        AuthRoleCriteria.Criteria sql = arCriteria.createCriteria();
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        if(StringUtil.isNotBlank(reqDto.getStatus())){
            sql.andStatusEqualTo(reqDto.getStatus());
        }
        if(StringUtil.isNotBlank(reqDto.getSysCode())){
            sql.andSysCodeEqualTo(reqDto.getSysCode());
        }
        if(StringUtil.isNotBlank(reqDto.getRoleName())){
            sql.andRoleNameLike("%"+reqDto.getRoleName()+"%");
        }
        
        arCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        arCriteria.setLimitClauseCount(reqDto.getPageSize());
        
        res = super.queryByPagination(reqDto, arCriteria, false, new PaginationCallback<AuthRole, AuthRoleResDTO>() {

            @Override
            public List<AuthRole> queryDB(BaseCriteria criteria) {
                return authRoleMapper.selectByExample((AuthRoleCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return authRoleMapper.countByExample((AuthRoleCriteria) criteria);
            }

            @Override
            public AuthRoleResDTO warpReturnObject(AuthRole t) {
                AuthRoleResDTO dto = new AuthRoleResDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                dto.setSysCodeValue(BaseParamUtil.fetchParamValue(
                        StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY, t.getSysCode()));
                dto.setStatusValue(BaseParamUtil.fetchParamValue(
                        StaffConstants.PublicParam.STATUS_PARAMKEY, t.getStatus()));
                //权限（菜单） ///考虑使用复杂的sql（联表）查询获取结果
                AuthRole2Privilege rpRel = new AuthRole2Privilege();
                rpRel.setRoleId(dto.getId());
                List<AuthRole2Privilege> listrpRel = listPrivilegeRoleRel(rpRel);
                if(CollectionUtils.isNotEmpty(listrpRel)){
                    StringBuffer sbMenu = new StringBuffer();
                    StringBuffer sbMenuValue = new StringBuffer();
                    StringBuffer sbRule = new StringBuffer();
                    StringBuffer sbRuleValue = new StringBuffer();
                    
                    List<Long> listp = new ArrayList<Long>();
                    for (AuthRole2Privilege authRole2Privilege : listrpRel) {
                        listp.add(authRole2Privilege.getPrivilegeId());
                    }
                    //菜单
                    AuthPrivilege2MenuCriteria pmRelCriteria = new AuthPrivilege2MenuCriteria();
                    pmRelCriteria.createCriteria().andPrivilegeIdIn(listp).andSysCodeEqualTo(dto.getSysCode());
                    List<AuthPrivilege2Menu> listpmRel = privilegeMenuRelMapper.selectByExample(pmRelCriteria);
                    if(CollectionUtils.isNotEmpty(listpmRel)){//有菜单
                        for (AuthPrivilege2Menu authPrivilege2Menu : listpmRel) {
                            AuthMenuReqDTO reqMenu = new AuthMenuReqDTO();
                            reqMenu.setId(authPrivilege2Menu.getMenuId());
                            AuthMenuResDTO menu = menuManageSV.findAuthMenuById(reqMenu);
                            if(menu!=null){
                                sbMenu.append(",").append(menu.getId());
                                sbMenuValue.append(",").append(menu.getMenuTitle());
                            }
                        }
                        if(StringUtil.isNotBlank(sbMenu.toString())) dto.setMenu(sbMenu.substring(1));
                        if(StringUtil.isNotBlank(sbMenu.toString())) dto.setMenuValue(sbMenuValue.substring(1));
                    }
                    //权限
                    AuthPrivilege2RuleCriteria prRelCriteria = new AuthPrivilege2RuleCriteria();
                    prRelCriteria.createCriteria().andPrivilegeIdIn(listp).andSysCodeEqualTo(dto.getSysCode());
                    List<AuthPrivilege2Rule> listprRel = privilegeRuleRelMapper.selectByExample(prRelCriteria);
                    if(CollectionUtils.isNotEmpty(listprRel)){//有规则
                    	for (AuthPrivilege2Rule authPrivilege2Rule : listprRel) {
                    		DataAuthReqDTO reqRule = new DataAuthReqDTO();
                    		reqRule.setId(authPrivilege2Rule.getRuleId());
                    		DataAuthResDTO rule = dataAuthManageSV.findDataAuthById(reqRule);
                    		if(rule!=null){
                    			sbRule.append(",").append(rule.getId());
                    			sbRuleValue.append(",").append(rule.getName());
                    		}
    					}
                    	if(StringUtil.isNotBlank(sbRule.toString())) dto.setRule(sbRule.substring(1));
                    	if(StringUtil.isNotBlank(sbRule.toString())) dto.setRuleValue(sbRuleValue.substring(1));
                    }
                }
                
                
                return dto;
            }
        });
        
        return res;
    }

    @Override
    public AuthRoleResDTO findAuthRoleByName(AuthRoleReqDTO reqDto) throws BusinessException {
        if(reqDto==null||StringUtil.isBlank(reqDto.getRoleName())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"角色名称"});
        }
        AuthRoleCriteria roleCriteria = new AuthRoleCriteria();
        AuthRoleCriteria.Criteria sql = roleCriteria.createCriteria();
        sql.andRoleNameEqualTo(reqDto.getRoleName());
        List<AuthRole> list = null;
        try {
            list = authRoleMapper.selectByExample(roleCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "角色名查找角色失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"角色名查找角色"});
        }
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            AuthRoleResDTO dto = new AuthRoleResDTO();
            ObjectCopyUtil.copyObjValue(list.get(0), dto, null, false);
            return dto;
        }
    }

    @Override
    public Long countAuthRole() throws BusinessException {
        AuthRoleCriteria roleCriteria = new AuthRoleCriteria();
        Long count = null;
        try {
            count = authRoleMapper.countByExample(roleCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "统计角色总数", e);
        }
        return count;
    }

    @Override
    public void savePrivilegeRoleRel(AuthRole2Privilege ar2p) throws BusinessException {
        if(StringUtil.isBlank(ar2p.getStatus())){
            ar2p.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);
        }
        ar2p.setCreateTime(DateUtil.getSysDate());
        try {
            rolePrivlgRelMapper.insertSelective(ar2p);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库，角色权限关系创建失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{"角色权限关系"});
        }
    }

    @Override
    public void deletePrivilegeRoleRel(AuthRole2Privilege ar2p) throws BusinessException {
        if(ar2p==null||(ar2p.getRoleId()==null&&ar2p.getPrivilegeId()==null)){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthRole2PrivilegeCriteria relCriteria = new AuthRole2PrivilegeCriteria();
        AuthRole2PrivilegeCriteria.Criteria sql = relCriteria.createCriteria();
        if(ar2p.getRoleId()!=null){
            sql.andRoleIdEqualTo(ar2p.getRoleId());
        }
        if(ar2p.getPrivilegeId()!=null){
            sql.andPrivilegeIdEqualTo(ar2p.getPrivilegeId());
        }
        try {
            rolePrivlgRelMapper.deleteByExample(relCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库，删除失败，角色权限关系", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{"角色权限关系"});
        }
    }

    @Override
    public void deletePrivilegeRoleRelByKey(AuthRole2Privilege ar2p) throws BusinessException {
        if(ar2p==null||ar2p.getPrivilegeId()==null||ar2p.getRoleId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        try {
            rolePrivlgRelMapper.deleteByPrimaryKey(ar2p);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库，删除失败，角色权限关系", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{"角色权限关系"});
        }
    }

    @Override
    public List<AuthRole2Privilege> listPrivilegeRoleRel(AuthRole2Privilege ar2p)
            throws BusinessException {
        List<AuthRole2Privilege> res = new ArrayList<AuthRole2Privilege>();
        AuthRole2PrivilegeCriteria relCriteria = new AuthRole2PrivilegeCriteria();
        AuthRole2PrivilegeCriteria.Criteria sql = relCriteria.createCriteria();
        if(StringUtil.isBlank(ar2p.getStatus())){//缺省查询“有效”
            sql.andStatusEqualTo(StaffConstants.PublicParam.STATUS_ACTIVE);
        }else{
        	sql.andStatusEqualTo(ar2p.getStatus());
        }
        if(ar2p.getRoleId()!=null){
            sql.andRoleIdEqualTo(ar2p.getRoleId());
        }
        if(ar2p.getPrivilegeId()!=null){
            sql.andPrivilegeIdEqualTo(ar2p.getPrivilegeId());
        }
        
        try {
            res = rolePrivlgRelMapper.selectByExample(relCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库，查询失败，角色权限关系", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"角色权限关系"});
        }
        return res;
    }

    @Override
    public List<AuthRoleResDTO> findRoleByGroupId(List<Long> groupId)
            throws BusinessException {
        List<AuthRoleResDTO> rolelist = new ArrayList<AuthRoleResDTO>();
        AuthGroup2RoleCriteria c = new AuthGroup2RoleCriteria();
       // c.createCriteria().andCreateStaffIn(groupId);
        c.createCriteria().andGroupIdIn(groupId);
        List<AuthGroup2Role> list =  authGroup2RoleMapper.selectByExample(c);
        
        if(CollectionUtils.isNotEmpty(list)){
        for (AuthGroup2Role authGroup2Role : list) {
            AuthRoleResDTO res = new AuthRoleResDTO();
            AuthRole authRole = authRoleMapper.selectByPrimaryKey(authGroup2Role.getRoleId());
            ObjectCopyUtil.copyObjValue(authRole, res, null, false);
            rolelist.add(res);
        }
         }
        
        return rolelist;
    }
}

