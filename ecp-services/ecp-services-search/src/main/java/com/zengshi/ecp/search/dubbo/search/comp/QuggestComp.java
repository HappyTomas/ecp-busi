package com.zengshi.ecp.search.dubbo.search.comp;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.search.dubbo.search.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CommonParams;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.solr.common.params.FacetParams;
import org.castor.core.util.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: QueryFacet方式的Suggestion实现<br>
 * Date:2015年8月17日下午3:29:01 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class QuggestComp extends BaseComp {

    private final static String MODULE = "【搜索引擎】QuggestComp";

    private final static String TEXT = "Quggest异常";

    public final static String FIELD_SUGGEST = "suggest";

    public final static String FIELD_KW = "kw";

    //public final static String FIELD_KWFREQ = "kwfreq";

    @SuppressWarnings("unchecked")
    public static SearchResult<String> suggest1(SearchParam searchParam) {

        LogUtil.info(MODULE, searchParam.toString());

        SearchResult<String> searchResult = new SearchResult<String>();

        // 参数校验
        if (searchParam.getTipCount() <= 0) {
            searchResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_SPELLCHECK_TIPCOUNT_INVALID,
                    new String[] { "" + searchParam.getTipCount() }, LocaleUtil.getLocale()));
            return searchResult;
        }

        // 关键字为空不给建议
        if (StringUtils.isBlank(searchParam.getKeyword())) {
            searchResult.setSuccess();
            return searchResult;
        }

        // 集合名称是否为空
        if (!validateCollectionName(searchParam, searchResult)) {
            return searchResult;
        }

        // 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
        SecConfigRespDTO currSecConfigRespDTO = getAndValidateSecConfig(searchParam,
                searchResult);
        if (currSecConfigRespDTO == null) {
            return searchResult;
        }

        try {
            SolrServer solrServer = SearchUtils.getSolrServer(searchParam.getCollectionName() + SearchConstants.COLLECTION_SUGGEST_SUFFIX,true);

            SolrQuery query = new SolrQuery();
            query.set(CommonParams.QT, ERequestType.QUERY.getHandler());

            //户端强制指定检索语言（如界面提供检索语言参数选择）
            if(CollectionUtils.isNotEmpty(searchParam.getLangs())){
                StringBuffer sb=new StringBuffer();
                for(ELang lang:searchParam.getLangs()){
                    if(org.apache.commons.lang.StringUtils.isNotBlank(sb.toString())) sb.append(" "+ EOperator.OR.getName()+" ");
                    sb.append(SearchConstants.FIELD_LANG +SearchConstants.COLON+lang.getLang());
                }
                query.addFilterQuery(sb.toString());
            }
            
            query.set("shards.tolerant",true);

            String keyword=StringUtil.replaceAll(searchParam.getKeyword()," ","");
            keyword=StringUtil.replaceAll(keyword,"　","");

            StringBuilder sb = new StringBuilder();
            sb.append(FIELD_SUGGEST).append(":").append(keyword).append("*");
            query.setQuery(sb.toString());
            query.setFacet(true);
            query.addFacetField(FIELD_KW);
            query.setFacetMinCount(1);
            query.setFacetLimit(searchParam.getTipCount());
            query.setRows(0);
            //字典排序（不适用目前实现的分词组合suggestion方式）//INDEX ASC
            query.setFacetSort(FacetParams.FACET_SORT_INDEX);
            //query.setFacetSort(FacetParams.FACET_SORT_INDEX_LEGACY);
            //词频排序（分词组合越短正常词频越高，显示得越靠前，这是正常的方式）//COUNT DESC
            //query.setFacetSort(FacetParams.FACET_SORT_COUNT_LEGACY);
            //query.setFacetSort(FacetParams.FACET_SORT_COUNT);
            QueryResponse queryResp = solrServer.query(query, METHOD.POST);
            searchResult = addReuslt1(queryResp, searchResult);

            // 额外响应信息设置
            searchResult = addBaseRespInfo(searchResult, queryResp);

            LogUtil.info(MODULE, MODULE+"请求参数：【"+keyword+"】");

        } catch (BusinessException e) {
            LogUtil.error(MODULE, TEXT, e);

            // 已国际化信息
            searchResult.setMessage(e.getErrorMessage());
            return searchResult;
        } catch (SolrServerException e1) {
            LogUtil.error(MODULE, TEXT, e1);
            searchResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Error.KEY_ERROR_QUERY,
                    new String[] { SearchUtils.getExceptionMessage(e1) }, LocaleUtil.getLocale()));
            return searchResult;
        }

        return searchResult;
    }

    public static SearchResult<String> addReuslt1(QueryResponse queryResp,
            SearchResult<String> searchResult) {
        
        List<FacetField> facets = queryResp.getFacetFields();
        if (facets != null && facets.size() > 0) {
            List<String> suggestionStrList = new ArrayList<String>();
            List<Count> counts = facets.get(0).getValues();
            for (Count count : counts) {
                //去除term 组合数前缀
                suggestionStrList.add(count.getName().substring(1));
            }
            searchResult.setResultList(suggestionStrList);
        }

        return searchResult;
    }

    @SuppressWarnings("unchecked")
    public static SearchResult<CollationReuslt> suggest2(SearchParam searchParam) {

        LogUtil.info(MODULE, searchParam.toString());

        SearchResult<CollationReuslt> searchResult = new SearchResult<CollationReuslt>();

        // 参数校验
        if (searchParam.getTipCount() <= 0) {
            searchResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_SPELLCHECK_TIPCOUNT_INVALID,
                    new String[] { "" + searchParam.getTipCount() }, LocaleUtil.getLocale()));
            return searchResult;
        }

        // 关键字为空不给建议
        if (StringUtils.isBlank(searchParam.getKeyword())) {
            searchResult.setSuccess();
            return searchResult;
        }

        // 集合名称是否为空
        if (!validateCollectionName(searchParam, searchResult)) {
            return searchResult;
        }

        // 获取集合名称所指向的索引配置并验证，若验证不通过也一并返回null
        SecConfigRespDTO currSecConfigRespDTO = getAndValidateSecConfig(searchParam,
                searchResult);
        if (currSecConfigRespDTO == null) {
            return searchResult;
        }

        try {
            SolrServer solrServer = SearchUtils.getSolrServer(searchParam.getCollectionName() + SearchConstants.COLLECTION_SUGGEST_SUFFIX,true);

            SolrQuery query = new SolrQuery();
            query.set(CommonParams.QT, ERequestType.QUERY.getHandler());

            //户端强制指定检索语言（如界面提供检索语言参数选择）
            if(CollectionUtils.isNotEmpty(searchParam.getLangs())){
                StringBuffer sb=new StringBuffer();
                for(ELang lang:searchParam.getLangs()){
                    if(org.apache.commons.lang.StringUtils.isNotBlank(sb.toString())) sb.append(" "+ EOperator.OR.getName()+" ");
                    sb.append(SearchConstants.FIELD_LANG +SearchConstants.COLON+lang.getLang());
                }
                query.addFilterQuery(sb.toString());
            }

            query.set("shards.tolerant",true);

            StringBuilder sb = new StringBuilder();
            sb.append(FIELD_SUGGEST).append(":").append(searchParam.getKeyword()).append("*");
            query.setQuery(sb.toString());
            query.setFacet(true);
            query.addFacetField(FIELD_KW);
            query.setFacetMinCount(1);
            query.setFacetLimit(searchParam.getTipCount());
            query.setRows(0);
            //字典排序（不适用目前实现的分词组合suggestion方式）//INDEX ASC
            query.setFacetSort(FacetParams.FACET_SORT_INDEX);
            //query.setFacetSort(FacetParams.FACET_SORT_INDEX_LEGACY);
            //词频排序（分词组合越短正常词频越高，显示得越靠前，这是正常的方式）//COUNT DESC
            //query.setFacetSort(FacetParams.FACET_SORT_COUNT_LEGACY);
            //query.setFacetSort(FacetParams.FACET_SORT_COUNT);
            QueryResponse queryResp = solrServer.query(query, METHOD.POST);
            searchResult = addReuslt2(queryResp, searchResult);

            // 额外响应信息设置
            searchResult = addBaseRespInfo(searchResult, queryResp);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, TEXT, e);

            // 已国际化信息
            searchResult.setMessage(e.getErrorMessage());
            return searchResult;
        } catch (SolrServerException e1) {
            LogUtil.error(MODULE, TEXT, e1);
            searchResult.setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Error.KEY_ERROR_QUERY,
                    new String[] { SearchUtils.getExceptionMessage(e1) }, LocaleUtil.getLocale()));
            return searchResult;
        }

        return searchResult;
    }

    public static SearchResult<CollationReuslt> addReuslt2(
            QueryResponse queryResp,
            SearchResult<CollationReuslt> searchResult) {

        List<FacetField> facets = queryResp.getFacetFields();
        if (facets != null && facets.size() > 0) {
            List<CollationReuslt> suggestionStrList = new ArrayList<CollationReuslt>();
            CollationReuslt collationReuslt = null;
            List<Count> counts = facets.get(0).getValues();
            for (Count count : counts) {
                //去除term 组合数前缀
                collationReuslt = new CollationReuslt(count.getName().substring(1),count.getCount());
                suggestionStrList.add(collationReuslt);
            }
            searchResult.setResultList(suggestionStrList);
        }

        return searchResult;
    }

}
