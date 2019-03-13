package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginResultResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月14日上午10:23:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 平台用户登陆密码验证服务类
 */
public interface IAuthLoginRSV {
    /**
     * 
     * checkLogin:(该函数用户验证平台用户登陆结果). <br/> 
     * TODO(账号登陆验证，邮箱登陆验证，手机登陆验证等).<br/> 
     * bubbo层服务
     * 
     * @author PJieWin 
     * @param authStaffInfo
     * @return 
     * @since JDK 1.6
     */
    public LoginResultResDTO checkLogin(String loginpara, String password) throws BusinessException;
    
    /**
     * 
     * insertLoginLog:(插入登陆日志表信息). <br/> 
     * 
     * @author PJieWin 
     * @param loginLogInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int insertLoginLog(LoginAccessLogReqDTO loginAccessLogReqDTO,LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException;

    /**
     * 
     * updateOutLoginLog:(登陆退出日志更新). <br/> 
     * 
     * @author PJieWin 
     * @param loginLogInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateOutLoginLog(LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException;
    
    
    /**
     * 
     * updateOutLoginLog:(企业会员注册). <br/> 
     * 
     * @author wangbh 
     * @param registerCompany
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void registerCompany(CustInfoReqDTO custInfoReqDTO,
			CustAuthstrReqDTO custAuthstrReqDTO)throws BusinessException;

    
    /**
     * 
     * checkPassword:(判断密码是否正确). <br/> 
     * @author huangxl 
     * @param pass 明文密码
     * @param rawPass 数据库里面加过密的密码
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean checkPassword(String pass,String rawPass) throws BusinessException;
}

