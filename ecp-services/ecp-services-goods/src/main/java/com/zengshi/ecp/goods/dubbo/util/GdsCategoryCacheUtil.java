package com.zengshi.ecp.goods.dubbo.util;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 商品分类缓存工具类.<br>
 * Date:2015-11-17下午8:07:03 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
@Service("gdsCategoryCacheUtil")
public class GdsCategoryCacheUtil {

    private static final String MODULE = GdsCategoryCacheUtil.class.getName();

    public static final void cacheCategory(GdsCategoryRespDTO respDTO) throws BusinessException {
        if (respDTO == null) {
            return;
        }
        LogUtil.info(MODULE, "===============分类ID为{" + respDTO.getCatgCode() + "}的分类放入缓存...");
        try {
            if (null != respDTO && StringUtil.isNotBlank(respDTO.getCatgCode())) {
                // CacheUtil.hsetItem(getGlobalCacheKey(), respDTO.getCatgCode(), json);
                CacheUtil.addItem(buildKey(respDTO.getCatgCode()), respDTO);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "==========catgCode为{" + respDTO.getCatgCode() + "}的分类放入缓存遇到异常!", e);
        }
    }

    public static final void removeCategory(String catgCode) {
        LogUtil.info(MODULE, "=======开始根据分类编码[" + catgCode + "]从缓存中移除分类...");
        try {
            if (StringUtil.isNotBlank(catgCode)) {
                // CacheUtil.hdelItem(getGlobalCacheKey(), catgCode);
                CacheUtil.delItem(buildKey(catgCode));
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "从缓存中移除分类编码为[" + catgCode + "]的分类遇到异常!");
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200316, new String[] { catgCode });
        } finally {
            LogUtil.info(MODULE, "=======根据分类编码[" + catgCode + "]从缓存中移除分类结束!");
        }

    }

    public static long clearGdsCategoryCache() {
        LogUtil.info(MODULE, "========开始清理商品分类缓存...");
        List<Object> keys = CacheUtil.keys(getCacheKey() + "*");
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
        // Map<String, String> map = CacheUtil.getHashMap(getGlobalCacheKey());
        // long itemCount = MapUtils.isEmpty(map) ? 0L : map.size();
    }

    /**
     * 
     * getCategoryByCatgCodeFromCache:根据分类编码从缓存中获取分类信息. <br/>
     * <font color="red">该方法仅从缓存中加载分类信息,如果加载不到则不会再从数据库中进行查询,调用者请注意该特性.</font>
     * 
     * @author liyong7
     * @param catgCode
     * @return
     * @since JDK 1.6
     * @deprecated 该方法允许使用,使用时请注意查看说明
     */
    public static GdsCategoryRespDTO getCategoryByCatgCodeFromCache(String catgCode) {

        GdsCategoryRespDTO respDTO = null;
        try {
            LogUtil.info(MODULE, "============开始从缓存获取分类编码为[" + catgCode + "]分类信息...");
            if (StringUtil.isNotBlank(catgCode)) {

//                Long data2 = System.currentTimeMillis();
                respDTO = (GdsCategoryRespDTO) CacheUtil.getItem(buildKey(catgCode));
//                Long time = System.currentTimeMillis()-data2;
//                LogUtil.error("", "查询商品分类缓存耗时" + time + "ms");
            }
            LogUtil.info(MODULE, "============从缓存获取分类编码为[" + catgCode + "]分类信息结束!");
        } catch (Exception e) {
            LogUtil.error(MODULE, "===========从缓存获取分类编码为{" + catgCode + "}的分类遇到异常!", e);
        }
        return respDTO;
    }

    public static final String getCacheKey() {
        return GdsConstants.CacheKey.CACHE_KEY_GDS_CATEGORY_;
    }

    /**
     * 
     * getGlobalCacheKey:获取分类全局缓存key. <br/>
     * 
     * @author liyong7
     * @return
     * @since JDK 1.6
     */
    public static final String getGlobalCacheKey() {
        return GdsConstants.CacheKey.GLOBAL_CACHE_KEY_GDS_CATEGORY;
    }

    private static final String buildKey(String catgCode) {
        return getCacheKey() + catgCode;
    }

}
