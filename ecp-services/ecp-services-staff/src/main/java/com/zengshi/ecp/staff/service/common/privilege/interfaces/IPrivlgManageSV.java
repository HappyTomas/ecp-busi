package com.zengshi.ecp.staff.service.common.privilege.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthGroup2Role;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege;
import com.zengshi.ecp.staff.dao.model.AuthRole;
import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroup;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 权限资源管理功能服务接口<br>
 * Date:2015年8月28日下午2:03:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IPrivlgManageSV extends IGeneralSQLSV {
    

    /**
     * 
     * findRoleByRoleIDList:(通过角色ID集合，查询角色详细信息). <br/> 
     * 
     * @author PJieWin 
     * @param pRoleIdList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthRole> listRole(List<Long> pRoleIdList) throws BusinessException; 
    /**
     * 
     * findRolePrivilByRoleIDList:(通过角色ID列表查询权限编码集合). <br/> 
     * 
     * @author PJieWin 
     * @param pRoleIdList
     * @return 
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthRole2Privilege> listPrivil(List<Long> pRoleIdList)throws BusinessException;
    
    /**
     * 
     * saveAuthPrivilege:(新增资源权限). <br/> 
     * 
     * @author linby 
     * @param ap
     * @return 
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveAuthPrivilege(AuthPrivilege ap) throws BusinessException;
    /**
     * 
     * updateAuthPrivilegeById:(通过id修改权限信息). <br/> 
     * 
     * @author linby 
     * @param update
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAuthPrivilegeById(AuthPrivilege update) throws BusinessException;
    
    /**
     * 
     * deleteAuthPrivilegeById:(通过id删除权限信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAuthPrivilegeById(AuthPrivilegeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findAuthPrivilegeById:(通过id查找权限). <br/> 
     * 
     * @author linby 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthPrivilege findAuthPrivilegeById(Long id) throws BusinessException;
    
    /**
     * 
     * listAuthPrivilege:(得到指定系统及权限类型的权限资源集合). <br/> 
     * 
     * @author linby 
     * @param sysCodes List<String> 子系统编码
     * @param privlgType String 权限类型
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthPrivilege> listAuthPrivilege(List<String> sysCodes, String privlgType) throws BusinessException;
    
    /**
     * 
     * listAuthPrivilege:(根据既定条件查询权限|分页). <br/> 
     * 不分页则可传pageno为0
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AuthPrivilegeResDTO> listAuthPrivilege(AuthPrivilegeReqDTO reqDto) throws BusinessException;
    
    public List<AuthStaffGroup> listGroup(List<Long> pGroupIdList)throws BusinessException; 

    public List<AuthGroup2Role> listGroup2Role(List<Long> pGroupIdList)throws BusinessException; 
}

