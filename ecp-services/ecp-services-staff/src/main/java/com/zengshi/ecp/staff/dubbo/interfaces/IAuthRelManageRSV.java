package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2GroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2GroupResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 权限关系接口<br>
 * Date:2015年9月24日下午5:29:02  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAuthRelManageRSV {
    /**
     * 
     * saveStaffGroupRel:(新增用户与用户组关系). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveStaffGroupRel(AuthStaff2GroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteStaffGroupRel:(删除用户与用户组关系). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteStaffGroupRel(AuthStaff2GroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findStaffGroupRelByKey:(通过关系键查询用户与用户组). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaff2GroupResDTO findStaffGroupRelByKey(AuthStaff2GroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listStaffGroupRel:(通过既定条件查询用户与用户组关系集合). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthStaff2GroupResDTO> listStaffGroupRel(AuthStaff2GroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveStaffRoleRel:(新增用户与角色关系). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveStaffRoleRel(AuthStaff2RoleReqDTO reqDto) throws BusinessException;
    
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
    public AuthStaff2RoleResDTO findStaffRoleRelByKey(AuthStaff2RoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteStaffRoleRel:(删除用户与角色关系). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteStaffRoleRel(AuthStaff2RoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listStaffRoleRel:(通过既定条件查询用户与角色关系集合). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthStaff2RoleResDTO> listStaffRoleRel(AuthStaff2RoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findAuthRoleList:(获取会员的角色列表). <br/> 
     * 
     * @author wangbh
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public List<AuthRoleResDTO> findAuthRoleList(AuthStaffResDTO req) throws BusinessException;
}

