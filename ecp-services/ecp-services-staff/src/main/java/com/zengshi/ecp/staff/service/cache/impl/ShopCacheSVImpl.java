package com.zengshi.ecp.staff.service.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.staff.dao.mapper.busi.CompanyShopIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopInfoMapper;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDX;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDXCriteria;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopInfoCriteria;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.cache.interfaces.IShopCacheSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 店铺信息缓存管理类<br>
 * Date:2015年8月31日下午7:20:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 */
public class ShopCacheSVImpl implements IShopCacheSV{
    
    public static final String MODULE = ShopCacheSVImpl.class.getName();
    
    /**
     * 店铺信息表mapper操作
     */
    @Resource
    private ShopInfoMapper shopInfoMapper;
    
    @Resource
    private CompanyShopIDXMapper companyShopIDXMapper;   
    
    /**
     * 
     * TODO 初始化店铺信息缓存. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IShopCacheSV#init()
     */
    @Override
    public void init()
    {
        LogUtil.info(MODULE, "=========初始化店铺信息缓存   开 始 ==========");
        Map<Long, ShopInfoResDTO> shopMap = new HashMap<Long, ShopInfoResDTO>();
        ShopInfoCriteria criteria = new ShopInfoCriteria();
        criteria.createCriteria();
        List<ShopInfo> resList = null;
        try {
            resList = shopInfoMapper.selectByExample(criteria);
            LogUtil.info(MODULE, "=========找到 ["+resList.size()+"] 条店铺信息==========");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        shopMap.clear();
        for(ShopInfo info : resList)
        {
            ShopInfoResDTO dto = new ShopInfoResDTO();
            ObjectCopyUtil.copyObjValue(info, dto, null, false);
            shopMap.put(dto.getId(), dto);
        }
        CacheUtil.addItem(StaffConstants.shopInfo.SHOP_CACHE_KEY, shopMap);
        LogUtil.info(MODULE, "=========初始化店铺信息缓存   结 束 ==========");
    }

    /**
     * 
     * TODO 更新缓存记录. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IShopCacheSV#update(com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO)
     */
    @Override
    public int update(ShopInfoResDTO pDto) {
        //1.取出店铺信息缓存块
        @SuppressWarnings("unchecked")
        Map<Long, ShopInfoResDTO> shopMap = (HashMap<Long, ShopInfoResDTO>)CacheUtil.getItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
        ShopInfo shopInfo = null;
        try {
            shopInfo = shopInfoMapper.selectByPrimaryKey(pDto.getId());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        if(shopInfo != null)
        {
            ShopInfoResDTO sInfo = new ShopInfoResDTO();
            ObjectCopyUtil.copyObjValue(shopInfo, sInfo, null, false);
            shopMap.put(sInfo.getId(), sInfo);
        }
        else {
            ShopInfoResDTO sInfo = shopMap.get(pDto.getId());
            if(sInfo != null)
            {
                shopMap.remove(pDto.getId());
            }
        }       
        
        CacheUtil.addItem(StaffConstants.shopInfo.SHOP_CACHE_KEY, shopMap);
        return 0;
    }

    /**
     * 
     * TODO 添加缓存记录. 
     * @see com.zengshi.ecp.staff.service.cache.interfaces.IShopCacheSV#add(com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO)
     */
    @Override
    public int add(ShopInfoResDTO pDto) {
        //1.取出店铺信息缓存块
        @SuppressWarnings("unchecked")
        Map<Long, ShopInfoResDTO> shopMap = (HashMap<Long, ShopInfoResDTO>)CacheUtil.getItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
        ShopInfo shopInfo = null;
        try {
            shopInfo = shopInfoMapper.selectByPrimaryKey(pDto.getId());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        if(shopInfo != null)
        {
            ObjectCopyUtil.copyObjValue(shopInfo, pDto, null, false);
            try {
                shopMap.put(pDto.getId(), pDto);
            } catch (Exception e) {
                // TODO: handle exception
                LogUtil.info(MODULE, "=========添加店铺信息缓存记录出现异常： "+pDto.toString()+"============");
                e.printStackTrace();
                throw e;
            }
        }
        CacheUtil.addItem(StaffConstants.shopInfo.SHOP_CACHE_KEY, shopMap);
        return 0;
    }

    @Override
    public Map<Long, ShopInfoResDTO> getCacheShop() {
        Map<Long, ShopInfoResDTO> shopMap  = new HashMap<Long, ShopInfoResDTO>();
        shopMap = (HashMap<Long, ShopInfoResDTO>)CacheUtil.getItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
        if(shopMap==null||shopMap.isEmpty()){
            LogUtil.info(MODULE, "=========为从缓存中取到店铺信息，转数据库中获取==========");
            ShopInfoCriteria criteria = new ShopInfoCriteria();
            List<ShopInfo> resList =null;
            try {
                resList = shopInfoMapper.selectByExample(criteria);
                LogUtil.info(MODULE, "=========找到 ["+resList.size()+"] 条店铺信息==========");
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                throw e;
            }
            shopMap = new HashMap<Long, ShopInfoResDTO>(); 
            for(ShopInfo info : resList)
            {
                ShopInfoResDTO dto = new ShopInfoResDTO();
                ObjectCopyUtil.copyObjValue(info, dto, null, false);
                shopMap.put(dto.getId(), dto);
            }
            CacheUtil.addItem(StaffConstants.shopInfo.SHOP_CACHE_KEY, shopMap);
        }
        return shopMap;
    }

    @Override
    public Map<Long, ShopInfoResDTO> getCompanyShop(long companyId) {
        
        Map<Long, ShopInfoResDTO> shopMap  = new HashMap<Long, ShopInfoResDTO>();
        Map<Long, ShopInfoResDTO> shopMap1  = new HashMap<Long, ShopInfoResDTO>();
        shopMap = (HashMap<Long, ShopInfoResDTO>)CacheUtil.getItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
        if(shopMap!=null&&!shopMap.isEmpty()){
            CompanyShopIDXCriteria c = new CompanyShopIDXCriteria();
            c.createCriteria().andCompanyIdEqualTo(companyId);
            List<CompanyShopIDX> list = new ArrayList<CompanyShopIDX>();
            try {
              list = companyShopIDXMapper.selectByExample(c);
            } catch (Exception e) {
               e.printStackTrace();
            }
            LogUtil.info(MODULE, "=========从缓存中获取企业下属店铺,数据为空返回空的map==========");
            if(list!=null&&!list.isEmpty()){
            for (CompanyShopIDX companyShopIDX : list) {
                if(!StringUtil.isEmpty(shopMap.get(companyShopIDX.getShopId()))){
                shopMap1.put(companyShopIDX.getShopId(),shopMap.get(companyShopIDX.getShopId()));
                }
            }
              }
            
        }else{
            shopMap = getCacheShop();
            CompanyShopIDXCriteria c = new CompanyShopIDXCriteria();
            c.createCriteria().andCompanyIdEqualTo(companyId);
            List<CompanyShopIDX> list = new ArrayList<CompanyShopIDX>();
            try {
              list = companyShopIDXMapper.selectByExample(c);
            } catch (Exception e) {
               e.printStackTrace();
            }
            LogUtil.info(MODULE, "=========从缓存中获取企业下属店铺,数据为空返回空的map==========");
            if(list!=null&&!list.isEmpty()){
            for (CompanyShopIDX companyShopIDX : list) {
                if(!StringUtil.isEmpty(shopMap.get(companyShopIDX.getShopId()))){
                    shopMap1.put(companyShopIDX.getShopId(),shopMap.get(companyShopIDX.getShopId()));
                    }
            }
              }
        }
        
        return shopMap1;
    }
}

