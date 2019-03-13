package com.zengshi.ecp.staff.service.common.privilege.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2GroupReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 角色管理功能服务接口<br>
 * Date:2015年8月29日上午9:58:00  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IRoleManageSV extends IGeneralSQLSV {
    
    /**
     * 
     * saveAuthRole:(新增角色). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveAuthRole(AuthRoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteAuthRoleById:(根据角色id删除). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAuthRoleById(AuthRoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAuthRole:(根据角色id更新). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAuthRole(AuthRoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findAuthRoleById:(根据角色id查找). <br/> 
     * 
     * @author linby 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthRoleResDTO findAuthRoleById(Long id) throws BusinessException;
    
    /**
     * 
     * listAuthRole:(根据既定条件查询角色). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AuthRoleResDTO> listAuthRole(AuthRoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findAuthRoleByName:(通过角色名查找角色). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthRoleResDTO findAuthRoleByName(AuthRoleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * countAuthRole:(获得角色总数). <br/> 
     * 
     * @author linby 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long countAuthRole() throws BusinessException;
    
    /**
     * 
     * savePrivilegeRoleRel:(保存权限与角色关系). <br/> 
     * 
     * @author linby 
     * @param ar2p
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void savePrivilegeRoleRel(AuthRole2Privilege ar2p) throws BusinessException;
    
    /**
     * 
     * deletePrivilegeRoleRel:(根据既定条件删除). <br/> 
     * 关系删除处置为物理删除
     * 
     * @author linby 
     * @param ar2p
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deletePrivilegeRoleRel(AuthRole2Privilege ar2p) throws BusinessException;
    
    /**
     * 
     * deletePrivilegeRoleRelByKey:(根据key删除). <br/> 
     * 关系删除处置为物理删除
     * 
     * @author linby 
     * @param ar2p
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deletePrivilegeRoleRelByKey(AuthRole2Privilege ar2p) throws BusinessException;
    
    /**
     * 
     * listRolePrivlgRel:(根据既定条件查找). <br/> 
     * 
     * @author linby 
     * @param ar2p
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthRole2Privilege> listPrivilegeRoleRel(AuthRole2Privilege ar2p) throws BusinessException;
    
    
    
    /**
     * 
     * findRoleByStaffId:(通过用户id查询角色). <br/> 
     * 
     * @author wangbh
     * @param authStaffReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public List<AuthRoleResDTO> findRoleByGroupId(List<Long> groupId) throws BusinessException;
}

