 package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月6日上午11:00:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface IAuthStaffRSV {
    
    /**
     * 
     * findPrivilByStaffCode:(通过登陆名查找用户权限集合). <br/> 
     * dubbo服务
     * 
     * @author PJieWin 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthPrivilegeResDTO findPrivilByStaffCode(String pStaffCode,String pStaffClass) throws BusinessException;
    
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
     * findAuthStaffById:(查询用户信息). <br/> 
     * 
     * @author huangxl 
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public AuthStaffResDTO findAuthStaffById(Long staffId) throws BusinessException;
    
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
    
    /**
     * 
     * saveRegister:(用户注册). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveRegister(AuthStaffReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateStaffById:(根据用户id，更新用户登录失败次数及超过次数时的锁定。). <br/> 
     * 返回某段时间内剩余可登录次数
     * 
     * @author huangxl 
     * @param staffCode 登录名
     * @param loginFlag 登录标识1：成功；0：失败
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateStaffById(String staffCode,String loginFlag) throws BusinessException;
}

