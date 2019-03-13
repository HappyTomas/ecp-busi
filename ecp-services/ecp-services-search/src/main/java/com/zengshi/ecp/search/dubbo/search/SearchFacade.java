package com.zengshi.ecp.search.dubbo.search;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.search.comp.*;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.result.ClusteringReuslt;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.GrammarResult;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.result.binder.BaseResultBinder.EBinderType;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 提供给前店的搜索门面<br>
 * Date:2015年8月17日下午4:00:24 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
@Service
public class SearchFacade {
    
    private final static String MODULE = "【搜索引擎】SearchFacade";
    
    public static String doTransfer(Long currentSiteId,ITranslator translator) throws SearchException {
        Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap = SearchCacheUtils
                .getSecConfigReqDTOMap();
        long startTime=System.currentTimeMillis();
        SecConfigRespDTO secConfigRespDTO=translator.translate(currentSiteId, cacheSecConfigRespDTOMap);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "查询doTransfer耗时：【"+(endTime-startTime)+"ms】");
        
        if(secConfigRespDTO==null){
            throw new SearchException("search translator translate failure.return null.");
        }
        
        return secConfigRespDTO.getConfigCollectionName();
    }

    /**
     * 搜索，数据以pojo返回
     * 
     * @param clazz
     *            pojo class实例
     * @param searchParam
     *            查询参数对象
     * @param extraQueryInfo
     *            附加搜索条件
     * @param filterList
     *            查询过滤器对象
     * @param translator
     *            查询转换器
     * @return
     */
    public static <T> SearchResult<T> search(Class<T> clazz, SearchParam searchParam,
            ExtraQueryInfo extraQueryInfo, List<QueryFilter> filterList,ITranslator translator) {
        long startTime=System.currentTimeMillis();
        try {
            if(translator!=null){
                searchParam.setCollectionName(doTransfer(searchParam.getCurrentSiteId(),translator));
            }
            searchParam.setKeyword(keywordProcess(searchParam.getKeyword()));
            searchParam.setClazz(clazz);
        } catch (SearchException e) {
            SearchResult<T> searchResult = new SearchResult<T>();
            searchResult.setMessage(e.getMessage());
            return searchResult;
        }
        SearchResult<T> result=QueryComp.search(EBinderType.BEAN,searchParam, extraQueryInfo, filterList);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "查询条件拼装-HTTP请求响应-返回结果拼装总耗时：【"+(endTime-startTime)+"ms】");
        return result;
    }
    
    /**
     * 搜索，数据以pojo返回
     * 
     * @param clazz
     *            pojo class实例
     * @param searchParam
     *            查询参数对象
     * @param translator
     *            查询转换器
     * @return
     */
    public static <T> SearchResult<T> search(Class<T> clazz, SearchParam searchParam,ITranslator translator) {
        return search(clazz, searchParam, null, null,translator);
    }
    
    /**
     * 搜索，数据以pojo返回
     * 
     * @param clazz
     *            pojo class实例
     * @param searchParam
     *            查询参数对象
     * @param extraQueryInfo
     *            附加搜索条件
     * @param filterList
     *            查询过滤器对象
     * @return
     */
    public static <T> SearchResult<T> search(Class<T> clazz, SearchParam searchParam,
            ExtraQueryInfo extraQueryInfo, List<QueryFilter> filterList) {
        return search(clazz, searchParam, extraQueryInfo, filterList, null);
    }

    /**
     * 生成检索文法
     *
     * @param grammarParam
     *            查询参数对象
     * @param translator
     *            查询转换器
     * @return
     */
    public static GrammarResult grammar(GrammarParam grammarParam, ITranslator translator) {
        long startTime=System.currentTimeMillis();
        try {
            if(translator!=null){
                grammarParam.setCollectionName(doTransfer(grammarParam.getCurrentSiteId(),translator));
            }
        } catch (SearchException e) {
            GrammarResult grammarResult=new GrammarResult();
            grammarResult.setMessage(e.getMessage());
            return grammarResult;
        }
        GrammarResult result= GrammarComp.grammar(grammarParam);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "生成检索文法总耗时：【"+(endTime-startTime)+"ms】");
        return result;
    }

    /**
     * 生成检索文法
     *
     * @param grammarParam
     *            查询参数对象
     * @return
     */
    public static GrammarResult grammar(GrammarParam grammarParam) {
        return grammar(grammarParam,null);
    }

    /**
     * 搜索，数据以map返回
     * 
     * @param fieldList
     *            返回字段列表
     * @param searchParam
     *            查询参数
     * @param extraQueryInfo
     *            附加搜索条件
     * @param filterList
     *            过滤器对象
     * @param translator
     *            查询转换器           
     * @return
     */
    public static SearchResult<Map<String,Object>> search(List<String> fieldList, SearchParam searchParam,
            ExtraQueryInfo extraQueryInfo, List<QueryFilter> filterList,ITranslator translator) {
        long startTime=System.currentTimeMillis();
        try {
            if(translator!=null){
                searchParam.setCollectionName(doTransfer(searchParam.getCurrentSiteId(),translator));
            }
            searchParam.setKeyword(keywordProcess(searchParam.getKeyword()));
            searchParam.setFieldList(fieldList);
        } catch (SearchException e) {
            SearchResult<Map<String,Object>> searchResult = new SearchResult<Map<String,Object>>();
            searchResult.setMessage(e.getMessage());
            return searchResult;
        }
        SearchResult<Map<String,Object>> result=QueryComp.search(EBinderType.MAP,searchParam, extraQueryInfo, filterList);
        long endTime=System.currentTimeMillis();
        LogUtil.info(MODULE, "查询条件拼装-HTTP请求响应-返回结果拼装总耗时：【"+(endTime-startTime)+"ms】");
        return result;
    }
    
    /**
     * 搜索，数据以map返回
     * 
     * @param searchParam
     *            查询参数
     * @param translator
     *            查询转换器           
     * @return
     */
    public static SearchResult<Map<String,Object>> search(SearchParam searchParam,
                                                          ExtraQueryInfo extraQueryInfo, List<QueryFilter> filterList,ITranslator translator) {
        return search(new ArrayList<String>(), searchParam, extraQueryInfo, filterList,translator);
    }
    
    /**
     * 搜索，数据以map返回
     * 
     * @param fieldList
     *            返回字段列表
     * @param searchParam
     *            查询参数
     * @param translator
     *            查询转换器           
     * @return
     */
    public static SearchResult<Map<String,Object>> search(List<String> fieldList, SearchParam searchParam,ITranslator translator) {
        return search(fieldList, searchParam, null, null,translator);
    }
    
    /**
     * 搜索，数据以map返回
     * 
     * @param fieldList
     *            返回字段列表
     * @param searchParam
     *            查询参数
     * @param extraQueryInfo
     *            附加搜索条件
     * @param filterList
     *            过滤器对象
     * @return
     */
    public static SearchResult<Map<String,Object>> search(List<String> fieldList, SearchParam searchParam,
            ExtraQueryInfo extraQueryInfo, List<QueryFilter> filterList) {
        return search(fieldList, searchParam, extraQueryInfo, filterList, null);
    }

    /**
     * 拼写检查
     * 
     * @param searchParam
     *            查询参数
     * @param translator
     *            查询转换器           
     * @return
     */
    public static SearchResult<CollationReuslt> spellcheck(SearchParam searchParam,ITranslator translator) {
        try {
            if(translator!=null){
                searchParam.setCollectionName(doTransfer(searchParam.getCurrentSiteId(),translator));
            }
            searchParam.setKeyword(keywordProcess(searchParam.getKeyword()));
        } catch (SearchException e) {
            SearchResult<CollationReuslt> searchResult = new SearchResult<CollationReuslt>();
            searchResult.setMessage(e.getMessage());
            return searchResult;
        }
        return SpellCheckComp.spellcheck(searchParam);
    }
    
    /**
     * 拼写检查
     * 
     * @param searchParam
     *            查询参数
     * @return
     */
    public static SearchResult<CollationReuslt> spellcheck(SearchParam searchParam) {
        return spellcheck(searchParam, null);
    }

    /**
     * 搜索建议，提供统计信息
     * 
     * @param searchParam
     *            查询参数
     * @param translator
     *            查询转换器           
     * @return
     */
    public static SearchResult<CollationReuslt> suggest2(SearchParam searchParam,ITranslator translator) {
        try {
            if(translator!=null){
                searchParam.setCollectionName(doTransfer(searchParam.getCurrentSiteId(),translator));
            }
            searchParam.setKeyword(keywordProcess(searchParam.getKeyword()));
        } catch (SearchException e) {
            SearchResult<CollationReuslt> searchResult = new SearchResult<CollationReuslt>();
            searchResult.setMessage(e.getMessage());
            return searchResult;
        }
        return QuggestComp.suggest2(searchParam);
    }
    
    /**
     * 搜索建议，提供统计信息
     * 
     * @param searchParam
     *            查询参数
     * @return
     */
    public static SearchResult<CollationReuslt> suggest2(SearchParam searchParam) {
        return suggest2(searchParam, null);
    }

    /**
     * 搜索建议，不提供统计信息
     * 
     * @param searchParam
     *            查询参数
     * @param translator
     *            查询转换器           
     * @return
     */
    public static SearchResult<String> suggest1(SearchParam searchParam,ITranslator translator) {
        try {
            if(translator!=null){
                searchParam.setCollectionName(doTransfer(searchParam.getCurrentSiteId(),translator));
            }
            searchParam.setKeyword(keywordProcess(searchParam.getKeyword()));
        } catch (SearchException e) {
            SearchResult<String> searchResult = new SearchResult<String>();
            searchResult.setMessage(e.getMessage());
            return searchResult;
        }
        return QuggestComp.suggest1(searchParam);
    }
    
    /**
     * 搜索建议，不提供统计信息
     * 
     * @param searchParam
     *            查询参数
     * @return
     */
    public static SearchResult<String> suggest1(SearchParam searchParam) {
        return suggest1(searchParam, null);
    }
    
    /**
     * 自动聚类
     * 
     * @param searchParam
     * @return
     */
    public static SearchResult<ClusteringReuslt> clustering(SearchParam searchParam,ITranslator translator) {
        try {
            if(translator!=null){
                searchParam.setCollectionName(doTransfer(searchParam.getCurrentSiteId(),translator));
            }
            searchParam.setKeyword(keywordProcess(searchParam.getKeyword()));
        } catch (SearchException e) {
            SearchResult<ClusteringReuslt> searchResult = new SearchResult<ClusteringReuslt>();
            searchResult.setMessage(e.getMessage());
            return searchResult;
        }
        return ClusteringComp.clustering(searchParam);
    }
    
    public static SearchResult<ClusteringReuslt> clustering(SearchParam searchParam) {
        return clustering(searchParam, null);
    }

    /**
     * 检索关键字根据空格split，做AND检索
     * @param keyword
     * @return
     */
    private static String keywordAnd(String keyword){
        if(StringUtils.isBlank(keyword)||!keyword.contains(" ")){
            return keyword;
        }

        String arr[]=keyword.split(" ");
        String result="";

        for(String s:arr){
            if(StringUtils.isNotBlank(s)){
                if(StringUtils.isNotBlank(result)){
                    result+=" "+ EOperator.AND.getName()+" ";
                }
                result+=s;
            }
        }

        return result;

    }
    
    public static String keywordProcess(String s) {
        if(StringUtil.isNotBlank(s)){
            //搜索所有，不被转义
            if(StringUtils.equals(s, SearchConstants.STAR)){
                return s;
            }

            //Deprecated Invoke@20170214:不处理空格的转义，空格的处理是split后AND处理
            //return ClientUtils.escapeQueryChars(s);

            String result="";

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // These characters are part of the query syntax and must be escaped
                if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
                        || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
                        || c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
                        //|| Character.isWhitespace(c)
                        ) {
                    sb.append('\\');
                }
                sb.append(c);
            }
            result= sb.toString();

            //检索关键字根据空格split，做AND检索
            return keywordAnd(result);

        }else{
            return "";
        }
      }

    /**
     * 纯粹的关键字转义
     * @param s
     * @return
     */
    public static String escapeQueryChars(String s) {
        if(StringUtil.isNotBlank(s)){
            //搜索所有，不被转义
            if(StringUtils.equals(s, SearchConstants.STAR)){
                return s;
            }
            return ClientUtils.escapeQueryChars(s);
        }else{
            return "";
        }
    }

}
