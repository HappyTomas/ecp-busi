/** 
 * Project Name:ecp-services-staff-server 
 * File Name:IShopAddrSV.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.interfaces 
 * Date:2015年9月16日下午6:08:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.busi.sso.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoMsgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoReqDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午6:08:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.6 
 * 
 * 导入SSO用户数据
 */
public interface ISsoUserImportSV{
    
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
     * updateStaffInfo:(sso登录时，修改用户信息). <br/> 
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
     * updateAndSaveStaffInfo:(变更用户信息). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    
    public SsoUserInfoMsgResDTO updateAndSaveStaffInfo(SsoUserInfoReqDTO dto)throws BusinessException;
  
    
}

