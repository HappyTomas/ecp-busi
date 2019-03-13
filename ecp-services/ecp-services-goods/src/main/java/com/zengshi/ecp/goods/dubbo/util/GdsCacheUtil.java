package com.zengshi.ecp.goods.dubbo.util;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;

public class GdsCacheUtil {
	
	public static final String MODULE=GdsCacheUtil.class.getName();
	
	/**
	 * 删除指定key的缓存
	 * 
	 * @author linwb3
	 * @param key 
	 * @since JDK 1.6
	 */
	public static final void delCacheItem(final String key){
		try {
			CacheUtil.delItem(key);
		} catch (Exception e) {
			LogUtil.error(GdsCacheUtil.class.getName(),"del gdsInfo cache failed! ",e);
		}
	}
	
	/**
	 * 删除商品缓存
	 * 
	 * @author linwb3
	 * @param gdsId 
	 * @since JDK 1.6
	 */
	public static void delGdsInfoCache(Long gdsId) {
		// 删除商品信息缓存
		try {
			CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.GDS_CACHE_KEY_PREFIX + gdsId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "edit gdsInfo cache failed! please check  Cache Server!", e);
		}
	}
	
	/**
	 * 
	 * 删除商品图片缓存
	 * 
	 * @author linwb3
	 * @param skuId 
	 * @since JDK 1.6
	 */
	public static void delGdsPicCache(Long gdsId) {
		// 删除商品主图缓存
		try {
			CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.GDS_MAINPIC_CACHE_KEY_PREFIX + gdsId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "del gdsInfo main pic cache failed! ! please check  Cache Server!", e);
		}
	}
	
	/**
	 * 
	 * 删除单品信息缓存
	 * 
	 * @author linwb3
	 * @param skuId 
	 * @since JDK 1.6
	 */
	public static void delSkuInfoCache(Long skuId) {
		try {
			CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.SKU_CACHE_KEY_PREFIX + skuId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "delete skuInfo cache failed! please check  Cache Server!", e);
		}
	}
	
	/**
	 * 删除单品图片缓存
	 * 
	 * @author linwb3
	 * @param skuId 
	 * @since JDK 1.6
	 */
	public static void delSkuPicCache(Long skuId) {
		try {
			CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.SKU_MAINPIC_CACHE_KEY_PREFIX + skuId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "delete skuInfo cache failed! please check  Cache Server!", e);
		}
	}
}

