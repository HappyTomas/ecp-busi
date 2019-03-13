package com.zengshi.ecp.goods.dubbo.util;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-8下午2:17:14  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCatalogCacheUtil {
	
	private static final String MODULE = GdsCategoryCacheUtil.class.getName();
	
	
	public static final void cacheCataLog(GdsCatalogRespDTO respDTO) throws BusinessException{
		if(respDTO ==null){
            return;
        }
        LogUtil.info(MODULE, "===============ID为{"+respDTO.getId()+"}的目录放入缓存开始====");
		if(null != respDTO && null != respDTO.getId()){
			String json = JSONObject.toJSONString(respDTO);
			CacheUtil.addItem(buildKey(respDTO.getId()), json);
		}
		LogUtil.info(MODULE, "===============ID为{"+respDTO.getId()+"}的目录放入缓存结束====");
	}
	
	
	 public static final void removeCatalog(Long id){
	    	LogUtil.info(MODULE, "=======开始根据ID为["+id+"]从缓存中移除目录...");
	    	CacheUtil.delItem(buildKey(id));
	    	LogUtil.error(MODULE, "=======结束根据ID为["+id+"]从缓存中移除目录...");
	    }
	
	
	public static long clearGdsCatalogCache(){
        LogUtil.info(MODULE, "========开始清理商品目录缓存...");
        List<Object> keys = CacheUtil.keys(getCatalogCacheKey()+"*");
        if (keys == null || keys.size() == 0) {
            return 0;
        } else {
            LogUtil.info(MODULE, "========缓存大小：" + keys.size());
            for (Object obj : keys) {
                String key = (String) obj;
                LogUtil.info(MODULE, "======清空key" + key);
                CacheUtil.delItem(key);
            }
            LogUtil.info(MODULE, "========清空结束");
            return keys.size();
        }
    }
	/**
	 * 
	 * clearGdsCatalog2SiteCache:清空站点与目录关联关系缓存. <br/> 
	 * 
	 * @author liyong7
	 * @return 
	 * @since JDK 1.6
	 */
	public static long clearGdsCatalog2SiteCache(){
		LogUtil.info(MODULE, "========开始清理商品目录与站点关联关系缓存...");
        List<Object> keys = CacheUtil.keys(GdsConstants.CacheKey.CACHE_KEY_CATALOG2SITE_BY_SITEID+"*");
        if (keys == null || keys.size() == 0) {
            return 0;
        } else {
            LogUtil.info(MODULE, "========缓存大小：" + keys.size());
            for (Object obj : keys) {
                String key = (String) obj;
                LogUtil.info(MODULE, "======清空key" + key);
                CacheUtil.delItem(key);
            }
            LogUtil.info(MODULE, "========清空结束");
            return keys.size();
        }
	}
	
	
	/**
	 * 
	 * clearGdsDefaultCatalogCache:清理默认目录缓存. <br/> 
	 * 
	 * @author liyong7
	 * @return 
	 * @since JDK 1.6
	 */
	public static long clearGdsDefaultCatalogCache(){
        LogUtil.info(MODULE, "========开始清理商品默认目录缓存...");
        CacheUtil.delItem(GdsConstants.CacheKey.CACHE_KEY_GDS_DEFAULT_CATALOG);
        LogUtil.info(MODULE, "========清理默认目录缓存结束");
        return 1L;
    }
	
	
	 public static GdsCatalogRespDTO getCatalogByFromCacheById(Long id){
		 GdsCatalogRespDTO respDTO = null;
	     	try {
	     		LogUtil.info(MODULE, "============开始从缓存获取ID为["+id+"]目录信息...");
	 			if(null != id){
	 				String json = String.valueOf(CacheUtil.getItem(buildKey(id)));
	 				if(!"null".equals(json) && StringUtil.isNotBlank(json)){
	 					respDTO = JSONObject.parseObject(json, GdsCatalogRespDTO.class);
	 				}
	 			}
	 			LogUtil.info(MODULE, "============从缓存获取ID为["+id+"]的目录信息结束!");
	 		} catch (Exception e) {
	 			LogUtil.error(MODULE, "===========从缓存获取ID为{"+id+"}的目录信息遇到异常!",e);
	 		}
	     	return respDTO;
	     }
	 
	 /**
	  * 
	  * getGdsDefaultCatalogFromCache:从缓存获取默认目录信息. <br/> 
	  * 
	  * @author liyong7
	  * @return 
	  * @since JDK 1.6
	  */
	 public static GdsCatalogRespDTO getGdsDefaultCatalogFromCache(){
		    GdsCatalogRespDTO respDTO = null;
	     	try {
	     		LogUtil.info(MODULE, "============开始从缓存获取默认目录信息...");
 				String json = String.valueOf(CacheUtil.getItem(getDefaultCatalogCacheKey()));
 				if(!"null".equals(json) && StringUtil.isNotBlank(json)){
 				   respDTO = JSONObject.parseObject(json, GdsCatalogRespDTO.class);
 				}
	 			LogUtil.info(MODULE, "============从缓存获取默认目录信息结束!");
	 		} catch (Exception e) {
	 			LogUtil.error(MODULE, "===========从缓存获取默认目录信息遇到异常!",e);
	 		}
	     	return respDTO;
	    }
	
	 /**
	  * 
	  * cacheDefaultCatalog:缓存默认目录. <br/> 
	  * 
	  * @author liyong7
	  * @param respDTO 
	  * @since JDK 1.6
	  */
	 public static void cacheDefaultCatalog (GdsCatalogRespDTO respDTO){
		 if(respDTO ==null){
	            return;
	        }
	        LogUtil.info(MODULE, "===============ID为{"+respDTO.getId()+"}的目录放入缓存...");
			String json = JSONObject.toJSONString(respDTO);
			CacheUtil.addItem(getDefaultCatalogCacheKey(), json);
	 }
	 
	 /**
	  * 
	  * removeDefaultCatalogFromCache:从缓存移除默认目录. <br/> 
	  * 
	  * @author liyong7 
	  * @since JDK 1.6
	  */
	 public static void removeDefaultCatalogFromCache (){
	        LogUtil.info(MODULE, "===============开始从缓存清除默认商品目录");
			CacheUtil.delItem(getDefaultCatalogCacheKey());
			LogUtil.info(MODULE, "===============结束从缓存清除默认商品目录");
	 }
	
	/**
	 * 
	 * getGlobalCacheKey:获取一般目录缓存键. <br/> 
	 * 
	 * @author liyong7
	 * @return 
	 * @since JDK 1.6
	 */
	public static final String getCatalogCacheKey(){
		return GdsConstants.CacheKey.CACHE_KEY_GDS_CATALOG;
	}
	/**
	 * 
	 * getDefaultCatalogCacheKey:创建默认目录缓存键. <br/> 
	 * 
	 * @author liyong7
	 * @return 
	 * @since JDK 1.6
	 */
	public static final String getDefaultCatalogCacheKey(){
		return GdsConstants.CacheKey.CACHE_KEY_GDS_DEFAULT_CATALOG;
	}
	/**
	 * 
	 * buildKey:创建一般目录缓存键. <br/> 
	 * 
	 * @author liyong7
	 * @param catgCode
	 * @return 
	 * @since JDK 1.6
	 */
	public static final String buildKey(Long catgCode){
		return getCatalogCacheKey()+catgCode;
	}
	
	public static final String buildDefaultCatalogKey(Long catgCode){
		return getDefaultCatalogCacheKey()+catgCode;
	}
	
	public static final class GdsCatalog2Shop{
		
		public static void cacheCatalogIds(Long shopId, LongListRespDTO respDTO){
			if(null != shopId && null != respDTO){
				String json = JSONObject.toJSONString(respDTO);
				CacheUtil.addItem(GdsCatalog2Shop.buildCacheKey(shopId), json);
			}
		}
		
		public static LongListRespDTO getCatalogIdsByShopIdFromCache(Long shopId){
			if(null == shopId){
				LogUtil.warn(MODULE, "===========传入店铺ID为空,不能从缓存获取与店铺关联的目录关联关系");
				return null;
			}
			LongListRespDTO respDTO = null;
     		LogUtil.info(MODULE, String.format("=============开始从缓存获取店铺ID为%d的目录关联关系", shopId));
			String json = String.valueOf(CacheUtil.getItem(buildCacheKey(shopId)));
			if(!"null".equals(json) && StringUtil.isNotBlank(json)){
			   respDTO = JSONObject.parseObject(json, LongListRespDTO.class);
			}
 			LogUtil.info(MODULE, String.format("=============结束从缓存获取店铺ID为%d的目录关联关系", shopId));
	     	return respDTO;
		}
		
		public static void removeRelationFromCacheByShopId(Long shopId){
			if(null == shopId){
				LogUtil.warn(MODULE, "===========传入店铺ID为空,不执行从缓存移除店铺与目录关联关系");
				return;
			}
     		LogUtil.info(MODULE, String.format("=============开始从缓存移除店铺ID为%d的目录关联关系", shopId));
			CacheUtil.delItem(buildCacheKey(shopId));
 			LogUtil.info(MODULE, String.format("=============结束从缓存移除店铺ID为%d的目录关联关系", shopId));
		}
		
		public static long clearAllGdsCatlog2ShopFromCache(){
			LogUtil.info(MODULE, "========开始清理店铺与目录关联关系缓存与站点关联关系缓存...");
	        List<Object> keys = CacheUtil.keys(GdsConstants.CacheKey.CACHE_KEY_CATLOG2SHOP_BY_SHOP+"*");
	        if (keys == null || keys.size() == 0) {
	            return 0;
	        } else {
	            LogUtil.info(MODULE, "========缓存大小：" + keys.size());
	            for (Object obj : keys) {
	                String key = (String) obj;
	                LogUtil.info(MODULE, "======清空key" + key);
	                CacheUtil.delItem(key);
	            }
	            LogUtil.info(MODULE, "========清空结束");
	            return keys.size();
	        }
		}
		
		public static String buildCacheKey(Long shopId){
			return GdsConstants.CacheKey.CACHE_KEY_CATLOG2SHOP_BY_SHOP+shopId;
		}
	}
	
public static final class GdsCatalog2Site{
		public static void cacheCatalogIds(Long siteId, LongListRespDTO respDTO){
			if(null != siteId && null != respDTO){
				String json = JSONObject.toJSONString(respDTO);
				CacheUtil.addItem(GdsCatalog2Site.buildCacheKey(siteId), json);
			}
		}
		
		public static LongListRespDTO getCatalogIdsBySiteIdFromCache(Long siteId){
			if(null == siteId){
				LogUtil.warn(MODULE, "===========传入站点ID为空,不能从缓存获取与站点关联的目录关联关系");
				return null;
			}
			LongListRespDTO respDTO = null;
     		LogUtil.info(MODULE, String.format("=============开始从缓存获取站点ID为%d的目录关联关系", siteId));
			String json = String.valueOf(CacheUtil.getItem(buildCacheKey(siteId)));
			if(!"null".equals(json) && StringUtil.isNotBlank(json)){
			   respDTO = JSONObject.parseObject(json, LongListRespDTO.class);
			}
 			LogUtil.info(MODULE, String.format("=============结束从缓存获取站点ID为%d的目录关联关系", siteId));
	     	return respDTO;
		}
		
		public static void removeRelationFromCacheBySiteId(Long siteId){
			if(null == siteId){
				LogUtil.warn(MODULE, "===========传入店铺ID为空,不执行从缓存移除站点与目录关联关系");
				return;
			}
     		LogUtil.info(MODULE, String.format("=============开始从缓存移除站点ID为%d的目录关联关系", siteId));
			CacheUtil.delItem(buildCacheKey(siteId));
 			LogUtil.info(MODULE, String.format("=============结束从缓存移除站点ID为%d的目录关联关系", siteId));
		}
		
		public static long clearAllGdsCatlog2ShopFromCache(){
			LogUtil.info(MODULE, "========开始清理站点与目录关联关系缓存与站点关联关系缓存...");
	        List<Object> keys = CacheUtil.keys(GdsConstants.CacheKey.CACHE_KEY_CATALOG2SITE_BY_SITEID+"*");
	        if (keys == null || keys.size() == 0) {
	            return 0;
	        } else {
	            LogUtil.info(MODULE, "========缓存大小：" + keys.size());
	            for (Object obj : keys) {
	                String key = (String) obj;
	                LogUtil.info(MODULE, "======清空key" + key);
	                CacheUtil.delItem(key);
	            }
	            LogUtil.info(MODULE, "========清空结束");
	            return keys.size();
	        }
		}
		
		public static String buildCacheKey(Long siteId){
			return GdsConstants.CacheKey.CACHE_KEY_CATALOG2SITE_BY_SITEID+siteId;
		}
	}
	
}
