package com.zengshi.ecp.staff.service.busi.manage.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 管理人员登录服务接口<br>
 * Date:2015-8-11上午11:19:06  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IManagerLoginSV {
    
    /**
     * 
     * queryStaffInfo:(根据用户账号/密码，查询用户信息). <br/> 
     * 
     * @author huangxl 
     * @param authStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffResDTO queryStaffInfo(AuthStaffReqDTO authStaff) throws BusinessException;
    
    /**
     * 
     * queryStaffByCIDX:(根据staff_code索引查出用户ID). <br/> 
     * 
     * @author huangxl 
     * @param authStaffCIDX
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffCIDX queryStaffByCIDX(AuthStaffCIDX authStaffCIDX) throws BusinessException;
    
    /**
     * 
     * updateAuthStaff:(更新用户信息表). <br/> 
     * 主要用于用户登录，更新登录信息。
     * @author huangxl 
     * @param authStaff
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updateAuthStaff(AuthStaffReqDTO authStaff) throws BusinessException;
    
    /**
     * 
     * findAuthStaffById:(根据主键ID查询用户信息). <br/> 
     * 
     * @author huangxl 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffResDTO findAuthStaffById(Long id) throws BusinessException;
    
    /**
     * 
     * managerLogin:(管理平台：用户登录). <br/> 
     * 
     * @author huangxl 
     * @param authStaffReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffResDTO managerLogin(AuthStaffReqDTO authStaffReqDTO) throws BusinessException;
    
    /**
     * 
     * loginSuccess:(用户成功登录后，更新用户登录表中的相关信息). <br/> 
     * 更新：登录成功次数、最后成功登录时间、数据最后更新时间
     * @author huangxl 
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int loginSuccess(Long staffId) throws BusinessException;
}

