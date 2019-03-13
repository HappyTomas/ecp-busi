package com.zengshi.ecp.staff.dubbo.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.ecp.staff.service.cache.interfaces.IShopCacheSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层 会员管理服务接口实现类<br>
 * Date:2015-8-12下午9:46:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ShopCacheRSVImpl implements IShopCacheRSV{

    @Resource
    private IShopCacheSV shopCacheSV;

    @Override
    public Map<Long, ShopInfoResDTO> getCacheShop() throws BusinessException {
        return shopCacheSV.getCacheShop();
    }

    @Override
    public Map<Long, ShopInfoResDTO> getCompanyShop(long companyId) throws BusinessException {
        
        return shopCacheSV.getCompanyShop(companyId);
    }
  

}

