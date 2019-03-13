package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeResDTO;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2016年2月22日下午4:47:37  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface ICustThirdCodeRSV {

    /**
     * 
     * saveCustThirdCode:(保存第三方账号信息). <br/> 
     * 
     * @author wangbh
     * @param custThirdCodeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveCustThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO) throws BusinessException;
    
    
    /**
     * 
     * queryThirdCode:(查询第三方账号). <br/> 
     * 
     * @author wangbh
     * @param custThirdCodeReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustThirdCodeResDTO queryThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO) throws BusinessException;
    
}

