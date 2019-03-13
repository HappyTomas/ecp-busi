package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PrivilegeMenuRelReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo服务接口：菜单及其权限<br>
 * Date:2015年8月29日下午2:16:14  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IMenuManageRSV {
    
    /**
     * 
     * listAuthMenu:(获取所有权限菜单). <br/> 
     * 根据所传子系统编码进行过滤
     * 
     * @author linby 
     * @param reqDto sysCodes
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthMenuResDTO> listAuthMenu(AuthManageReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveAuthMenu:(新增权限菜单). <br/>
     *  
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveAuthMenu(AuthMenuReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * savePrivilegeMenuRel:(新增权限菜单关系). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void savePrivilegeMenuRel(PrivilegeMenuRelReqDTO reqDto) throws BusinessException; 
    
    /**
     * 
     * listAuthMenu:(根据既定条件查询菜单列表|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AuthMenuResDTO> listAuthMenu(AuthMenuReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findAuthMenuById:(通过菜单id查找). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthMenuResDTO findAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAuthMenuById:(通过菜单id修改). <br/> 
     * 
     * @author linby 
     * @param update
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteAuthMenuById:(通过菜单id删除). <br/> 
     * 
     * @author linby 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * generateEntireTree:(生成完整树). <br/>
     * ztree 简单模式 
     * 必传: 1.sysCode
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthMenuResDTO> generateEntireTree(AuthMenuReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listAuthMenuByPrivList:(通过菜单权限查询菜单). <br/> 
     * 
     * @author linby 
     * @param reqDto  privList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthMenuResDTO> listAuthMenuByPrivList(AuthManageReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * clearMenuCache:(清理菜单缓存). <br/> 
     * 
     * @author linby 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long clearMenuCache() throws BusinessException;
}

