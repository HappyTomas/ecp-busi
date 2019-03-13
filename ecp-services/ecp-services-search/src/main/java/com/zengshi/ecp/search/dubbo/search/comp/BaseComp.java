package com.zengshi.ecp.search.dubbo.search.comp;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.search.GrammarParam;
import com.zengshi.ecp.search.dubbo.search.RecommendParam;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.GrammarResult;
import com.zengshi.ecp.search.dubbo.search.result.RecommendResult;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.util.*;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年9月1日下午4:58:28  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class BaseComp {
    
    private final static String MODULE = "【搜索引擎】BaseComp";
    
    /**
     * 集合名称是否为空
     * @param searchParam
     * @param searchResult
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean validateCollectionName(SearchParam searchParam,SearchResult searchResult){
        
        // 集合名称是否为空
        if (StringUtils.isEmpty(searchParam.getCollectionName())) {
            searchResult
                    .setMessage(ResourceMsgUtil.getMessage(
                            SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NULL, null,
                            LocaleUtil.getLocale()));
            return false;
        }
        
        return true;
        
    }

    /**
     * 集合名称是否为空
     * @param recommendParam
     * @param recommendResult
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean validateCollectionName(RecommendParam recommendParam, RecommendResult recommendResult){

        // 集合名称是否为空
        if (StringUtils.isEmpty(recommendParam.getCollectionName())) {
            recommendResult
                    .setMessage(ResourceMsgUtil.getMessage(
                            SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NULL, null,
                            LocaleUtil.getLocale()));
            return false;
        }

        return true;

    }

    /**
     * 集合名称是否为空
     * @param grammarParam
     * @param grammarResult
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean validateCollectionName(GrammarParam grammarParam, GrammarResult grammarResult){

        // 集合名称是否为空
        if (StringUtils.isEmpty(grammarParam.getCollectionName())) {
            grammarResult
                    .setMessage(ResourceMsgUtil.getMessage(
                            SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NULL, null,
                            LocaleUtil.getLocale()));
            return false;
        }

        return true;

    }
    
    /**
     * 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
     * @param searchParam
     * @param searchResult
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SecConfigRespDTO getAndValidateSecConfig(SearchParam searchParam,SearchResult searchResult){
        
        // 缓存配置读取
        Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap;
        try {
            cacheSecConfigRespDTOMap = SearchCacheUtils.getSecConfigReqDTOMap();
        } catch (SearchException e2) {
            LogUtil.error(MODULE, SearchUtils.getExceptionMessage(e2));
            searchResult.setMessage(SearchUtils.getExceptionMessage(e2));
            return null;
        }
        
        SecConfigRespDTO currSecConfigRespDTO = null;
        
        if (cacheSecConfigRespDTOMap != null
                && cacheSecConfigRespDTOMap.containsKey(searchParam.getCollectionName())) {
            currSecConfigRespDTO = cacheSecConfigRespDTOMap.get(searchParam.getCollectionName());
        } else {

            // 是否在索引配置库中存在此记录
            searchResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTREGISTERD, new String[]{searchParam.getCollectionName()},
                    LocaleUtil.getLocale()));
            return null;
        }

        // 是否已经激活
//        if (StringUtils.equals(currSecConfigRespDTO.getConfigIfActive(), SearchConstants.STATUS_0)) {
//            searchResult.setMessage(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_COLLECTION_NOTACTIVED, new String[]{searchParam.getCollectionName()},
//                    LocaleUtil.getLocale()));
//            return null;
//        }

        // 是否已经建立集合
//        if (StringUtils
//                .equals(currSecConfigRespDTO.getCollectionStatus(), SearchConstants.STATUS_0)) {
//            searchResult.setMessage(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_COLLECTION_NOTCREATED, new String[]{searchParam.getCollectionName()},
//                    LocaleUtil.getLocale()));
//            return null;
//        }

        // 是否已经建立索引
//        if (StringUtils.equals(currSecConfigRespDTO.getIndexStatus(), SearchConstants.STATUS_0)) {
//            searchResult.setMessage(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_COLLECTION_NOTINDEXD, new String[]{searchParam.getCollectionName()},
//                    LocaleUtil.getLocale()));
//            return null;
//        }
        
        return currSecConfigRespDTO;
        
    }

    /**
     * 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
     * @param recommendParam
     * @param recommendResult
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SecConfigRespDTO getAndValidateSecConfig(RecommendParam recommendParam,RecommendResult recommendResult){

        // 缓存配置读取
        Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap;
        try {
            cacheSecConfigRespDTOMap = SearchCacheUtils.getSecConfigReqDTOMap();
        } catch (SearchException e2) {
            LogUtil.error(MODULE, SearchUtils.getExceptionMessage(e2));
            recommendResult.setMessage(SearchUtils.getExceptionMessage(e2));
            return null;
        }

        SecConfigRespDTO currSecConfigRespDTO = null;

        if (cacheSecConfigRespDTOMap != null
                && cacheSecConfigRespDTOMap.containsKey(recommendParam.getCollectionName())) {
            currSecConfigRespDTO = cacheSecConfigRespDTOMap.get(recommendParam.getCollectionName());
        } else {

            // 是否在索引配置库中存在此记录
            recommendResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTREGISTERD, new String[]{recommendParam.getCollectionName()},
                    LocaleUtil.getLocale()));
            return null;
        }

        return currSecConfigRespDTO;

    }

    /**
     * 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
     * @param grammarParam
     * @param grammarResult
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SecConfigRespDTO getAndValidateSecConfig(GrammarParam grammarParam,GrammarResult grammarResult){

        // 缓存配置读取
        Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap;
        try {
            cacheSecConfigRespDTOMap = SearchCacheUtils.getSecConfigReqDTOMap();
        } catch (SearchException e2) {
            LogUtil.error(MODULE, SearchUtils.getExceptionMessage(e2));
            grammarResult.setMessage(SearchUtils.getExceptionMessage(e2));
            return null;
        }

        SecConfigRespDTO currSecConfigRespDTO = null;

        if (cacheSecConfigRespDTOMap != null
                && cacheSecConfigRespDTOMap.containsKey(grammarParam.getCollectionName())) {
            currSecConfigRespDTO = cacheSecConfigRespDTOMap.get(grammarParam.getCollectionName());
        } else {

            // 是否在索引配置库中存在此记录
            grammarResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTREGISTERD, new String[]{grammarParam.getCollectionName()},
                    LocaleUtil.getLocale()));
            return null;
        }

        return currSecConfigRespDTO;

    }
    
    /**
     * 额外响应信息设置
     * @param searchResult
     * @param queryResp
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SearchResult addBaseRespInfo(SearchResult searchResult,QueryResponse queryResp){
        
        searchResult.setqTime(queryResp.getQTime());
        LogUtil.info(MODULE, "QTime：【"+queryResp.getQTime()+"ms】");
        searchResult.setRequestUrl(queryResp.getRequestUrl());
        searchResult.setStatus(queryResp.getStatus());
        
        return searchResult;
        
    }

    /**
     * 额外响应信息设置
     * @param recommendResult
     * @param queryResp
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static RecommendResult addBaseRespInfo(RecommendResult recommendResult,QueryResponse queryResp){

        recommendResult.setqTime(queryResp.getQTime());
        LogUtil.info(MODULE, "HTTP查询请求-响应耗时：【"+queryResp.getQTime()+"ms】");
        recommendResult.setRequestUrl(queryResp.getRequestUrl());
        recommendResult.setStatus(queryResp.getStatus());

        return recommendResult;

    }
    
    /**
     * 操作字段校验，判断操作字段是否在索引字段配置中
     * @param srcField
     * @param srcFieldName2IndexNameMap
     * @throws SearchException
     */
    public static void checkSrcField(String srcField,Map<String, String> srcFieldName2IndexNameMap) throws SearchException{
        
        if(!srcFieldName2IndexNameMap.containsKey(srcField)){
            
            //通配符字段类型确认
            for(String key:srcFieldName2IndexNameMap.keySet()){
                if(PatternMatcher.match(key, srcField)){
                    return;
                }
            }
            throw new SearchException(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_FIELD_NOTREGISTERD, new String[]{srcField}, LocaleUtil.getLocale()));
        }
        
    }
    
    public static boolean isMultiLangField(Map<String, String> multiLangMap,String srcField){
        
        if(multiLangMap.containsKey(srcField)){
            return true;
        }
        
        //通配符字段类型确认
        for(String key:multiLangMap.keySet()){
            if(PatternMatcher.match(key, srcField)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取操作字段对应的索引字段名
     * @param srcField
     * @param srcFieldName2IndexNameMap
     * @throws SearchException
     */
    public static String getIndexField(String srcField,Map<String, String> srcFieldName2IndexNameMap){
        
        if(srcFieldName2IndexNameMap.containsKey(srcField)){
            return srcFieldName2IndexNameMap.get(srcField);
        }else{
            //通配符字段类型确认
            for(String key:srcFieldName2IndexNameMap.keySet()){
                if(PatternMatcher.match(key, srcField)){
                    
                    //通配符替换
                    return srcFieldName2IndexNameMap.get(key).replace(key+SearchConstants.UNDERLINE, srcField+SearchConstants.UNDERLINE);
                }
            }
//            throw new SearchException(ResourceMsgUtil.getMessage(
//                    SearchMessageKeyContants.Info.KEY_FIELD_NOTREGISTERD, new String[]{field}, LocaleUtil.getLocale()));
        }
        
        //返回的字段在索引配置中不存在，不应该报错
        return null;
    }
    
    /**
     * 获取索引字段对应的操作字段名
     * @param indexField 索引字段名
     * @throws SearchException
     */
    public static String getSrcField(String indexField) throws SearchException{
        
        return indexField.split(SearchConstants.UNDERLINE)[0];
    }
    
    /**
     * 获取索引字段对应的操作字段中文名
     * @param indexField 索引字段名
     * @param indexName2SrcFieldNameCnMap
     * @throws SearchException
     */
    public static String getSrcFieldCn(String indexField,Map<String, String> indexName2SrcFieldNameCnMap) throws SearchException{
        
        if(indexName2SrcFieldNameCnMap.containsKey(indexField)){
            return indexName2SrcFieldNameCnMap.get(indexField);
        }else{
            //通配符字段类型确认
            for(String key:indexName2SrcFieldNameCnMap.keySet()){
                if(PatternMatcher.match(key, indexField)){
                    return indexName2SrcFieldNameCnMap.get(key);
                }
            }
        }
        return "";
    }

    /**
     * 字符串语种分析
     * @param string 字符串
     * @return
     */
    public static List<ELang> langAnalysis(String string){
        List<ELang> langs= LangUtils.checkLang(string);

        //默认语言设置（*或无法检测到语言环境）
        //检索全部（*）的情况下会导致显示结果不全
        /*if(CollectionUtils.isEmpty(langs)){
            langs=new ArrayList<>();
            //默认是中文语种（中文语种兼容中文和英文）
            langs.add(ELang.chinese);
        }*/

        return langs;
    }

}

