package com.zengshi.ecp.staff.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopInfoMapper;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.cache.impl.ShopCacheSVImpl;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.PaasContextHolder;

public class CacheShopInfoTest extends EcpServicesTest{
    
    @Resource
    ShopCacheSVImpl cacheShop ;

    @Test
    public void testCache()
    {
//        ShopInfoMapper = PaasContextHolder.getContext().getBean("shopInfoMapper", ShopInfoMapper.class);
//        cacheShop.setShopInfoMapper(ShopInfoMapper);
        cacheShop.init();
        
        @SuppressWarnings("unchecked")
        Map<Long, ShopInfoResDTO> map = (HashMap<Long, ShopInfoResDTO>)CacheUtil.getItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
        System.out.println("=======该缓存有："+map.size()+"条记录======");

        ShopInfoResDTO sDto = new ShopInfoResDTO();
        sDto.setId(24L);
        sDto.setShopFullName("测试333333");
        sDto.setShopName("测试3333");
        
        cacheShop.update(sDto);
        @SuppressWarnings("unchecked")
        Map<Long, ShopInfoResDTO> map2 = (HashMap<Long, ShopInfoResDTO>)CacheUtil.getItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
        System.out.println("======="+map2.get(24L).toString());
    }
}

