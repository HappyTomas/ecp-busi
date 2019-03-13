package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层管理人员登录SV<br>
 * Date:2015-8-11下午4:05:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IManagerLoginRSV {

    /**
     * 
     * managerLogin:(dubbo层管理人员登录SV). <br/> 
     * map.put(resultCode,1)
     * map.put(resultInfo,"成功")
     * map.put(resultObj,AuthStaffDTO)
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
     * 更新：登录成功次数、最后成功登录时间、变更最后更新时间
     * @author huangxl 
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int loginSuccess(Long staffId) throws BusinessException;
}

