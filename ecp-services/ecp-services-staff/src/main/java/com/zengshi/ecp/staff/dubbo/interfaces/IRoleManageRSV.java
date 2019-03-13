package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo角色管理服务接口<br>
 * Date:2015年9月17日下午3:59:34  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IRoleManageRSV {
    
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
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthRoleResDTO findAuthRoleById(AuthRoleReqDTO reqDto) throws BusinessException;
    
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
    
    

}

