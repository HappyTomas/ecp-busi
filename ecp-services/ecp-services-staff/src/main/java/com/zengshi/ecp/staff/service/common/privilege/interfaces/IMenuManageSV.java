package com.zengshi.ecp.staff.service.common.privilege.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthMenu;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Menu;
import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 菜单管理功能接口<br>
 * Date:2015年8月29日上午9:59:36  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IMenuManageSV extends IGeneralSQLSV {
    
    /**
     * 
     * saveAuthMenu:(新增权限菜单). <br/>
     *  
     * @author linby 
     * @param am
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveAuthMenu(AuthMenu am) throws BusinessException;
    
    /**
     * 
     * listAuthMenu:(通过子系统编码得到菜单集合). <br/> 
     * 先查缓存，查无则查库
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
     * listAuthMenuFromDB:(通过子系统编码得到菜单集合). <br/> 
     * 
     * @author linby 
     * @param sysCodes
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthMenuResDTO> listAuthMenuFromDB(List<String> sysCodes) throws BusinessException;
    
    /**
     * 
     * listAuthMenuByPrivList:(通过菜单权限查询菜单). <br/> 
     * 
     * @author linby 
     * @param reqDto privList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthMenuResDTO> listAuthMenuByPrivList(AuthManageReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * savePrivilegeMenuRel:(新增权限菜单关系). <br/> 
     * 
     * @author linby 
     * @param ap2m
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void savePrivilegeMenuRel(AuthPrivilege2Menu ap2m) throws BusinessException; 
    
    
    /**
     * 
     * listPrivilegeMenuRel:(通过子系统编码得到权限菜单关系集合). <br/> 
     * 
     * @author linby 
     * @param sysCodes List<String> 子系统编码
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthPrivilege2Menu> listPrivilegeMenuRel(List<String> sysCodes) throws BusinessException;
    
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
    public void updateAuthMenuById(AuthMenu update) throws BusinessException;
    
    /**
     * 
     * deleteAuthMenuById:(通过菜单id删除). <br/> 
     * 
     * @author linby 
     * @param reqDto
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
}

