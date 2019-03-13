package com.zengshi.ecp.search.dubbo.search;

import com.zengshi.ecp.search.dubbo.search.comp.RecommendComp;
import com.zengshi.ecp.search.dubbo.search.result.RecommendResult;
import com.zengshi.paas.utils.LogUtil;
import org.apache.commons.collections.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by HDF on 2016/9/22.
 */
public class RecommendFacade {

    private final static String MODULE = "【搜索引擎】RecommendFacade";

    /*---------------------------------CF推荐 start-------------------------------------*/

    /**
     * userBasedCF推荐
     * @param recommendParam
     * @param <T>
     * @return
     */
    public static<T> RecommendResult<T> userBasedCFRecommend(RecommendParam recommendParam){
        return null;
    }

    /**
     * userBasedCF推荐
     * @param recommendParam
     * @return
     */
    public static RecommendResult<Map<String,Object>> userBasedCFRecommend2(RecommendParam recommendParam){
        return null;
    }

    /**
     * itemBasedCF推荐
     * @param recommendParam
     * @param <T>
     * @return
     */
    public static<T> RecommendResult<T> itemBasedCFRecommend(RecommendParam recommendParam){
        return null;
    }

    /**
     * itemBasedCF推荐
     * @param recommendParam
     * @return
     */
    public static RecommendResult<Map<String,Object>> itemBasedCFRecommend2(RecommendParam recommendParam){
        return null;
    }

    /*---------------------------------CF推荐 end-------------------------------------*/




    /*---------------------------------CB推荐 start-------------------------------------*/

    /**
     * 基于用户画像的CB推荐
     * @param recommendParam
     * @param currentSiteId
     * @param translator
     * @param <T>
     * @return
     */
    public static<T> RecommendResult<T> personasBasedCBRecommend(RecommendParam recommendParam,long currentSiteId,ITranslator translator){
        return null;
    }

    /**
     * 基于用户画像的CB推荐
     * @param recommendParam
     * @param currentSiteId
     * @param translator
     * @return
     */
    public static RecommendResult<Map<String,Object>> personasBasedCBRecommend2(RecommendParam recommendParam,long currentSiteId,ITranslator translator){
        return null;
    }

    /**
     * 基于用户画像的CB推荐
     * @param recommendParam
     * @param <T>
     * @return
     */
    public static<T> RecommendResult<T> personasBasedCBRecommend(RecommendParam recommendParam){
        return null;
    }

    /**
     * 基于用户画像的CB推荐
     * @param recommendParam
     * @return
     */
    public static RecommendResult<Map<String,Object>> personasBasedCBRecommend2(RecommendParam recommendParam){
        return null;
    }

    /**
     * 基于物品的CB推荐
     * @param recommendParam
     * @param currentSiteId
     * @param translator
     * @param <T>
     * @return
     */
    public static<T> RecommendResult<T> itemBasedCBRecommend(RecommendParam recommendParam,long currentSiteId,ITranslator translator){
        long startTime=System.currentTimeMillis();
        if(recommendParam.getBinderType()==null){
            RecommendResult<T> recommendResult = new RecommendResult<T>();
            recommendResult.setMessage("请指定返回结果数据绑定类型");
            return recommendResult;
        }
        try {
            if(translator!=null){
                recommendParam.setCollectionName(SearchFacade.doTransfer(currentSiteId,translator));
            }
        } catch (SearchException e) {
            RecommendResult<T> recommendResult = new RecommendResult<T>();
            recommendResult.setMessage(e.getMessage());
            return recommendResult;
        }
        RecommendResult<T> recommendResult=RecommendComp.itemBasedCBRecommend(recommendParam);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "基于item的CB推荐耗时：【"+(endTime-startTime)+"ms】");
        return recommendResult;
    }

    /**
     * 基于物品的CB推荐
     * @param recommendParam
     * @param <T>
     * @return
     */
    public static<T> RecommendResult<T> itemBasedCBRecommend(RecommendParam recommendParam){
        long startTime=System.currentTimeMillis();
        if(recommendParam.getBinderType()==null){
            RecommendResult<T> recommendResult = new RecommendResult<T>();
            recommendResult.setMessage("请指定返回结果数据绑定类型");
            return recommendResult;
        }
        RecommendResult<T> recommendResult=RecommendComp.itemBasedCBRecommend(recommendParam);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "基于item的CB推荐耗时：【"+(endTime-startTime)+"ms】");
        return recommendResult;
    }

    /**
     * 基于物品的CB推荐
     * @param recommendParam
     * @param currentSiteId
     * @param translator
     * @return
     */
    public static RecommendResult<Map<String,Object>> itemBasedCBRecommend2(RecommendParam recommendParam, long currentSiteId, ITranslator translator){
        long startTime=System.currentTimeMillis();
        if(recommendParam.getBinderType()==null){
            RecommendResult<Map<String,Object>> recommendResult = new RecommendResult<Map<String,Object>>();
            recommendResult.setMessage("请指定返回结果数据绑定类型");
            return recommendResult;
        }
        try {
            if(translator!=null){
                recommendParam.setCollectionName(SearchFacade.doTransfer(currentSiteId,translator));
            }
        } catch (SearchException e) {
            RecommendResult<Map<String,Object>> recommendResult = new RecommendResult<Map<String,Object>>();
            recommendResult.setMessage(e.getMessage());
            return recommendResult;
        }
        RecommendResult<Map<String,Object>> recommendResult=RecommendComp.itemBasedCBRecommend(recommendParam);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "基于item的CB推荐耗时：【"+(endTime-startTime)+"ms】");
        return recommendResult;
    }

    /**
     * 基于物品的CB推荐
     * @param recommendParam
     * @return
     */
    public static RecommendResult<Map<String,Object>> itemBasedCBRecommend2(RecommendParam recommendParam){
        long startTime=System.currentTimeMillis();
        if(recommendParam.getBinderType()==null){
            RecommendResult<Map<String,Object>> recommendResult = new RecommendResult<Map<String,Object>>();
            recommendResult.setMessage("请指定返回结果数据绑定类型");
            return recommendResult;
        }
        RecommendResult<Map<String,Object>> recommendResult=RecommendComp.itemBasedCBRecommend(recommendParam);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "基于item的CB推荐耗时：【"+(endTime-startTime)+"ms】");
        return recommendResult;
    }


    /*---------------------------------CB推荐 end-------------------------------------*/

}
