package com.zengshi.ecp.staff.service.busi.auth.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2GroupKey;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.AuthStaff2RoleKey;

/**
 * 
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 用户权限关系管理功能服务接口<br>
 * Date:2015年9月2日下午2:35:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAuthRelManageSV {
    
    /**
     * 
     * saveStaffGroupRel:(新增用户与用户组关系). <br/> 
     * 
     * @author linby 
     * @param save
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveStaffGroupRel(AuthStaff2Group save) throws BusinessException;
    
    /**
     * 
     * deleteStaffGroupRel:(删除用户与用户组关系). <br/> 
     * 
     * @author linby 
     * @param delete
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteStaffGroupRel(AuthStaff2Group delete) throws BusinessException;
    
    /**
     * 
     * findStaffGroupRelByKey:(通过关系键查询用户与用户组). <br/> 
     * 
     * @author linby 
     * @param key
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaff2Group findStaffGroupRelByKey(AuthStaff2GroupKey key) throws BusinessException;
    
    /**
     * 
     * listStaffGroupRel:(通过既定条件查询用户与用户组关系集合). <br/> 
     * 
     * @author linby 
     * @param example
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthStaff2Group> listStaffGroupRel(AuthStaff2Group example) throws BusinessException;
    
    /**
     * 
     * saveStaffRoleRel:(新增用户与角色关系). <br/> 
     * 
     * @author linby 
     * @param save
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveStaffRoleRel(AuthStaff2Role save) throws BusinessException;
    
    /**
     * 
     * findStaffRoleRelByKey:(通过关系键查询用户与角色关系). <br/> 
     * 
     * @author linby 
     * @param key
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaff2Role findStaffRoleRelByKey(AuthStaff2RoleKey key) throws BusinessException;
    
    /**
     * 
     * deleteStaffRoleRel:(删除用户与角色关系). <br/> 
     * 
     * @author linby 
     * @param delete
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteStaffRoleRel(AuthStaff2Role delete) throws BusinessException;
    
    /**
     * 
     * listStaffRoleRel:(通过既定条件查询用户与角色关系集合). <br/> 
     * 
     * @author linby 
     * @param example
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthStaff2Role> listStaffRoleRel(AuthStaff2Role example) throws BusinessException;
    
}

