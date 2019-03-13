package com.zengshi.ecp.staff.service.busi.manage.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdmin;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 管理员功能管理接口<br>
 * Date:2015年9月18日上午10:44:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAdminManageSV extends IGeneralSQLSV {
    
    /**
     * 
     * saveAuthStaffAdmin:(保存管理员). <br/> 
     * 
     * @author linby 
     * @param save
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveAuthStaffAdmin(AuthStaffAdminReqDTO save) throws BusinessException;
    
    /**
     * 
     * findAuthStaffAdminById:(通过id查询管理员). <br/> 
     * 
     * @author linby 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaffAdmin findAuthStaffAdminById(Long id) throws BusinessException;
    
    /**
     * 
     * findAuthStaffAdminByCode:(通过登录名[staffcode]查找管理员). <br/> 
     * 
     * @author linby 
     * @param staffCode
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaffAdmin findAuthStaffAdminByCode(String staffCode) throws BusinessException;
    
    /**
     * 
     * updateAuthStaffAdminById:(通过id修改管理员). <br/> 
     * 
     * @author linby 
     * @param update
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAuthStaffAdminById(AuthStaffAdminReqDTO update) throws BusinessException;
    
    /**
     * 
     * deleteAuthStaffAdminById:(通过id删除管理员). <br/> 
     * 
     * @author linby 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAuthStaffAdminById(Long id) throws BusinessException; 
    
    /**
     * 
     * listAuthStaffAdmin:(通过既定条件查找管理员|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AuthStaffAdminResDTO> listAuthStaffAdmin(AuthStaffAdminReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAuthStaffAdminFlag:(变更用户状态). <br/> 
     * 1：正常；2：加锁；3：失效；
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAuthStaffAdminFlag(AuthStaffAdminReqDTO reqDto) throws BusinessException;
}

