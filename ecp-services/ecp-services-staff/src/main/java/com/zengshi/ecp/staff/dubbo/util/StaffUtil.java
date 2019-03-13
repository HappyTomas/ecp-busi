package com.zengshi.ecp.staff.dubbo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyCacheRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IMenuManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCacheRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年9月2日上午9:36:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version
 * @since JDK 1.7
 */
@Service("staffUtil")
public class StaffUtil {
    private static final String MODULE = StaffUtil.class.getName();
    private static ICompanyCacheRSV companyCacheRSV;
    private static IShopCacheRSV shopCacheRSV;
    private static IMenuManageRSV authMenuRSV;
    private static ICustInfoRSV custInfoRSV;
    private static IScoreCacheRSV scoreCacheRSV;
    
    @Resource(name = "custInfoRSV")
    public void setCustInfoRSV(ICustInfoRSV custInfoRSV) {
        StaffUtil.custInfoRSV = custInfoRSV;
    }

    @Resource(name = "authMenuRSV")
    public void setMenuManageRSV(IMenuManageRSV menuManageRSV) {
        StaffUtil.authMenuRSV = menuManageRSV;
    }

    @Resource(name = "shopCacheRSV")
    public void setShopCacheRSV(IShopCacheRSV shopCacheRSV) {
        StaffUtil.shopCacheRSV = shopCacheRSV;
    }

    @Resource(name = "companyCacheRSV")
    public void setCompanyCacheRSV(ICompanyCacheRSV companyCacheRSV) {
        StaffUtil.companyCacheRSV = companyCacheRSV;
    }
    @Resource(name = "scoreCacheRSV")
    public void setScoreCacheRSV(IScoreCacheRSV scoreCacheRSV) {
        StaffUtil.scoreCacheRSV = scoreCacheRSV;
    }

    /**
     * 
     * getComapnyCache:(获取公司缓存). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public static Map<Long, CompanyInfoResDTO> getComapnyCache() {
        Map<Long, CompanyInfoResDTO> map = new HashMap<Long, CompanyInfoResDTO>();
        try {
            map = companyCacheRSV.getCacheCompany();
        } catch (Exception e) {
           LogUtil.error(MODULE, "获取企业缓存异常", e);
        }

        return map;
    }
    
    /**
     * 
     * getShopCache:(获取店铺缓存). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public static Map<Long, ShopInfoResDTO> getShopCache() {
        Map<Long, ShopInfoResDTO> map = new HashMap<Long, ShopInfoResDTO>();
        try {
            map = shopCacheRSV.getCacheShop();
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取店铺信息异常", e);
        }

        return map;
    }
    
    /**
     * 
     * clearShopCache:(清空店铺缓存). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public static long clearShopCache(){
      Map<Long, ShopInfoResDTO> shopMap = (HashMap<Long, ShopInfoResDTO>)CacheUtil.getItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
      if ((shopMap == null) || (shopMap.size() == 0)) {
        return 0L;
      }
      LogUtil.info(MODULE, "========开始清理缓存；缓存数量：" + shopMap.size());
      CacheUtil.delItem(StaffConstants.shopInfo.SHOP_CACHE_KEY);
      LogUtil.info(MODULE, "========清理缓存结束；");
      return shopMap.size();
    }

    /**
     * 
     * clearCompanyCache:(清空企业缓存). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public static long clearCompanyCache(){
      Map<Long,CompanyInfoResDTO> map = (HashMap<Long, CompanyInfoResDTO>) CacheUtil.getItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY);
      if ((map == null) || (map.size() == 0)) {
        return 0L;
      }
      LogUtil.info(MODULE, "========开始清理缓存；缓存数量：" + map.size());
      CacheUtil.delItem(StaffConstants.companyInfo.COMPANY_CACHE_KEY);
      LogUtil.info(MODULE, "========清理缓存结束；");
      return map.size();
    }
    
    /**
     * 
     * clearScoreConfigCache:(清空积分规则缓存). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */
    public static long clearScoreConfigCache(){
      LogUtil.info(MODULE, "========开始清理积分配置缓存；");
      long count = scoreCacheRSV.sizeScoreCache();
      CacheUtil.delItem(StaffConstants.Score.SCORE_ACTION_CACHE_KEY);
      CacheUtil.delItem(StaffConstants.Score.SCORE_TYPE_CACHE_KEY);
      CacheUtil.delItem(StaffConstants.Score.SCORE_FUN_CACHE_KEY);
      CacheUtil.delItem(StaffConstants.Score.SCORE_PARA_CACHE_KEY);
      CacheUtil.delItem(StaffConstants.Score.SCORE_CACLFUN_CACHE_KEY);

      LogUtil.info(MODULE, "========清理缓存结束；");
      return count;
    }
    
    /**
     * 
     * getMenuByStaff:(根据权限获取菜单). <br/> 
     * 
     * @author wangbh
     * @param reqDto privList
     * @return 
     * @since JDK 1.7
     */
    public static List<AuthMenuResDTO> getMenuByStaff(AuthManageReqDTO reqDto){
        
        LogUtil.info(MODULE, "========开始获取菜单：" + reqDto.getPrivList());
        List<AuthMenuResDTO> list = new ArrayList<AuthMenuResDTO>();
        try {
            list = authMenuRSV.listAuthMenuByPrivList(reqDto);
        LogUtil.info(MODULE, "========结束获取菜单：" + reqDto.getPrivList());
        } catch (Exception e) {
           LogUtil.error(MODULE, "获取菜单异常", e);
        }
     
        return list;
    }
    
    public static long clearMenuCache(){
        Long i = new Long(0);
        try {
            i = authMenuRSV.clearMenuCache();
        } catch (Exception e) {
            LogUtil.error(MODULE, "刷新菜单缓存异常", e);
        }
     
       return i;
        
    }
    
    /**
     * 
     * getCustInfo:(获取用户信息). <br/> 
     * 
     * @author wangbh
     * @param req
     * @return 
     * @since JDK 1.7
     */
    public static CustInfoResDTO getCustInfo(CustInfoReqDTO req){
        CustInfoResDTO dto = new CustInfoResDTO();
        try {
            
            dto = custInfoRSV.getCustInfoById(req);
            
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取客户信息异常", e);
        }
        return dto;
    }
    
    /**
     * 
     * getSellerShopList:(得到卖家店铺). <br/> 
     * 
     * @author linby
     * @return 
     * @since JDK 1.7
     */
    public static List<ShopInfoResDTO> getSellerShopList(SellerReqDTO reqDTO){
    	List<ShopInfoResDTO> list = new ArrayList<ShopInfoResDTO>();
    	if(StaffConstants.custInfo.SHOP_FLAG_NO.equals(reqDTO.getShopFlag())){//非卖家
    		return list;
    	}
    	if(StaffConstants.custInfo.CUST_TYPE_C.equals(reqDTO.getCustType())){//custType:20
    		long staffId = reqDTO.getStaff().getId();
    		CustInfoReqDTO cust = new CustInfoReqDTO();
    		cust.setId(staffId);
    		//得到用户信息  店铺id
    		CustInfoResDTO custResDTO = custInfoRSV.getCustInfoById(cust);
    		if(custResDTO.getShopId()==null){
    			return list;
    		}
    		Map<Long,ShopInfoResDTO> cacheShop = shopCacheRSV.getCacheShop();
    		for (Long key : cacheShop.keySet()) {
    			if(key.equals(custResDTO.getShopId())){
    				list.add(cacheShop.get(key));
    				break;
    			}
			}
    		
    	}else if(StaffConstants.custInfo.CUST_TYPE_ADMIN.equals(reqDTO.getCustType())){//custType:30
    		Map<Long,ShopInfoResDTO> map = shopCacheRSV.getCompanyShop(reqDTO.getCompanyId());
    		for (ShopInfoResDTO shopInfoResDTO : map.values()) {
    			list.add(shopInfoResDTO);
			}
    	}
    	
    	return list;
    }

    
}
