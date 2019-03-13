package com.zengshi.ecp.staff.service.cache.interfaces;

import java.util.Map;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月31日下午7:32:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface ICompanyCacheSV {
    /**
     * 
     * updateCacheCompany:(修改企业缓存). <br/> 
     * 
     * @author wangbh
     * @param id
     * @throws BusinessException 
     * @since JDK 1.7
     */
   public void updateCacheCompany(Long id) throws BusinessException;
   
   
   /**
    * 
    * getCacheCompany:(获取缓存中的店铺数据,为空，刷新缓存). <br/> 
    * 
    * @author wangbh
    * @throws BusinessException 
    * @since JDK 1.7
    */
   public Map<Long,CompanyInfoResDTO> getCacheCompany() throws BusinessException;
   
   /**
    * 
    * find:(根据企业ID找出企业信息). <br/> 
    * 
    * @author PJieWin 
    * @param pCompanyId
    * @return
    * @throws BusinessException 
    * @since JDK 1.6
    */
   public CompanyInfoResDTO find(Long pCompanyId) throws BusinessException;
    
}

