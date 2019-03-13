package com.zengshi.ecp.staff.service.common.loginlog.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015-11-9下午2:39:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 登陆操作日志表服务类
 */
public interface ILoginAccessLogSV {
    public int insert(LoginAccessLogReqDTO loginAccessLogReqDTO) throws BusinessException;
    public LoginAccessLogResDTO find(Long staffId) throws BusinessException;

}

