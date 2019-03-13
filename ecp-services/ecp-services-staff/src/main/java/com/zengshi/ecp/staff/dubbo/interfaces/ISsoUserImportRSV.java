package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.Map;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoMsgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层  sso数据同步接口<br>
 * Date:2015年8月14日下午5:04:46  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.6
 */
public interface ISsoUserImportRSV {
    
   
    
    /**
     * 
     * saveStaffInfo:(sso登录时，数据同步接口). <br/> 
     * @author wangbh
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    
    public SsoUserInfoMsgResDTO saveStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException;
    
    /**
     * 
     * updateStaffInfo:(sso修改用户信息接口). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public SsoUserInfoMsgResDTO updateStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException;
    
    
    /**
     * 
     * changeStaffInfo:(不信任泽元接口). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    
    public SsoUserInfoMsgResDTO changeStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException;
    
    
}

