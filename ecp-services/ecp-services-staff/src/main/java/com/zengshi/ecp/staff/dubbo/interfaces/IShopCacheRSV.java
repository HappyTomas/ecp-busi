package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.Map;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;

public interface IShopCacheRSV {

    /**
     * 
     * getCacheShop:(获取店铺列表缓存). <br/> 
     * 
     * @author wangbh
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
   public Map<Long, ShopInfoResDTO> getCacheShop() throws BusinessException;
   
      /**
       * 
       * getCompanyShop:(通过companyId获取下属店铺). <br/> 
       * 
       * @author wangbh
       * @param companyId
       * @return
       * @throws BusinessException 
       * @since JDK 1.7
       */
   public Map<Long, ShopInfoResDTO> getCompanyShop(long companyId) throws BusinessException;
   
}
