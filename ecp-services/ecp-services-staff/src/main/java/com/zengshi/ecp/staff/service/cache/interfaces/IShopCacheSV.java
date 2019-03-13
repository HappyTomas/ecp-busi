package com.zengshi.ecp.staff.service.cache.interfaces;

import java.util.Map;

import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;

public interface IShopCacheSV {
    /**
     * 
     * init:(初始化店铺缓存). <br/> 
     * 
     * @author PJieWin  
     * @since JDK 1.6
     */
    public void init();
    /**
     * 
     * update:(更新店铺缓存记录). <br/> 
     * 
     * @author PJieWin 
     * @param pDto
     * @return 
     * @since JDK 1.6
     */
    public int update(ShopInfoResDTO pDto);
    /**
     * 
     * add:(添加店铺缓存记录). <br/> 
     * 
     * @author PJieWin 
     * @param pDto
     * @return 
     * @since JDK 1.6
     */
    public int add(ShopInfoResDTO pDto);
    
    /**
     * 
     * getCacheShop:(获取店铺列表). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public Map<Long, ShopInfoResDTO> getCacheShop();
    
    
    /**
     * 
     * getCacheShop:(通过企业获取店铺列表). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public Map<Long,ShopInfoResDTO> getCompanyShop(long companyId);
}

