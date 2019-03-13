package com.zengshi.ecp.staff.service.common.loginlog.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoResDTO;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015-11-9下午2:39:41  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 登陆日志表操作服务类
 */
public interface ILoginLogSV {
    public int insert(LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException;
    public int update(LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException;
    public LoginLogInfoResDTO find(Long staffId) throws BusinessException;

}

