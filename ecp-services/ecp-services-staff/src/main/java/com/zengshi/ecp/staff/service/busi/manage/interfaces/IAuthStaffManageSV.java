package com.zengshi.ecp.staff.service.busi.manage.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 登录用户管理接口<br>
 * Date:2015年9月3日下午7:33:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAuthStaffManageSV extends IGeneralSQLSV {
    
    public long saveAuthStaff(AuthStaff save) throws BusinessException;
    /**
     * 
     * deleteAuthStaffById:(删除登录用户). <br/> 
     * 
     * @author linby 
     * @param delete
     * @param isPhy     是否物理删除，true则物理删除，否则逻辑删除；默认为逻辑删除
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAuthStaffById(AuthStaff delete, Boolean isPhy) throws BusinessException;
    
    /**
     * 
     * deleteAuthStaffById:(删除登录用户|逻辑删除). <br/> 
     * 
     * @author linby 
     * @param delete
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAuthStaffById(AuthStaff delete) throws BusinessException;
    /**
     * 
     * updateAuthStaffById:(通过id更新登录用户). <br/> 
     * 
     * @author linby 
     * @param update
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAuthStaffById(AuthStaff update) throws BusinessException;
    /**
     * 
     * findAuthStaffById:(根据用户id查找用户基本信息). <br/> 
     * 
     * @author linby 
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaffResDTO findAuthStaffById(Long staffId) throws BusinessException;
    
    /**
     * 
     * listAuthStaff:(根据既定条件查询用户列表). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AuthStaffResDTO> listAuthStaff(AuthStaffReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updatePwdById:(修改密码). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updatePwdById(AuthStaffReqDTO req) throws BusinessException;
}

