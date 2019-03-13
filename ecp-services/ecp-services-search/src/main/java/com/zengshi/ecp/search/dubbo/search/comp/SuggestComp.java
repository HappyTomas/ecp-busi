package com.zengshi.ecp.search.dubbo.search.comp;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.params.CommonParams;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.util.ERequestType;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: Suggestion接口<br>
 * Date:2015年8月17日下午3:29:01 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SuggestComp extends BaseComp {

    private final static String MODULE = "【搜索引擎】SuggestComp";
    
    private final static String TEXT="Suggest异常";

    @SuppressWarnings("unchecked")
    public static SearchResult<String> suggest1(SearchParam searchParam) {

        LogUtil.info(MODULE, searchParam.toString());
        
        SearchResult<String> searchResult = new SearchResult<String>();
        
        //参数校验
        if(searchParam.getTipCount()<=0){
            searchResult
            .setMessage(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_SPELLCHECK_TIPCOUNT_INVALID, new String[]{""+searchParam.getTipCount()},
                    LocaleUtil.getLocale()));
            return searchResult;
        }
        
        //关键字为空不给建议
        if(StringUtils.isBlank(searchParam.getKeyword())){
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
            SolrServer solrServer = SearchUtils.getSolrServer(searchParam.getCollectionName(),true);

            SolrQuery query = new SolrQuery();
            query.set(CommonParams.QT, ERequestType.SUGGEST.getHandler());

            query.set("spellcheck.q", searchParam.getKeyword());

            //如果集群中某一个分片的节点都挂了，为了让集群可以正常提供查询服务，需要添加下面代码
            //表示不使用分布式搜索，如果设置为true则会导致返回结果为空
            //query.set("distrib",false);
            //query.setDistrib(false);

            //只从存活的shards获取数据,没有此参数，如果集群内有挂掉的shard，将显示：
            //no servers hosting shard
            //需要注意值是Boolean而不是字符串
            query.set("shards.tolerant",true);

            query.set("spellcheck", "true");
            query.set("spellcheck.build", "true");
            query.set("spellcheck.count", searchParam.getTipCount());
            query.set("spellcheck.extendedResults", "false");

            QueryResponse queryResp = solrServer.query(query, METHOD.POST);
            searchResult = addReuslt1(queryResp.getSpellCheckResponse(), searchResult);

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
                    SearchMessageKeyContants.Error.KEY_ERROR_QUERY, new String[]{SearchUtils.getExceptionMessage(e1)},
                    LocaleUtil.getLocale()));
            return searchResult;
        }

        return searchResult;
    }

    public static SearchResult<String> addReuslt1(SpellCheckResponse spellCheckResponse,
            SearchResult<String> searchResult) {

        if (spellCheckResponse != null) {

            List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();

            if (suggestionList != null && !suggestionList.isEmpty()) {

                List<String> suggestionStrList = new ArrayList<String>();

                for (Suggestion suggestion : suggestionList) {
                    suggestionStrList.addAll(suggestion.getAlternatives());
                }

                searchResult.setResultList(suggestionStrList);

            }

        }

        return searchResult;
    }

    @SuppressWarnings("unchecked")
    public static SearchResult<CollationReuslt> suggest2(SearchParam searchParam) {

        LogUtil.info(MODULE, searchParam.toString());

        SearchResult<CollationReuslt> searchResult = new SearchResult<CollationReuslt>();
        
        if(StringUtils.isBlank(searchParam.getKeyword())){
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
            SolrServer solrServer = SearchUtils.getSolrServer(searchParam.getCollectionName(),true);

            SolrQuery query = new SolrQuery();
            query.set(CommonParams.QT, ERequestType.SUGGEST.getHandler());

            query.set("spellcheck.q", searchParam.getKeyword());

            // true返回结果为空
            query.set("distrib", "false");

            //只从存活的shards获取数据,没有此参数，如果集群内有挂掉的shard，将显示：
            //no servers hosting shard
            //需要注意值是Boolean而不是字符串
            query.set("shards.tolerant",true);

            query.set("spellcheck", "true");
            query.set("spellcheck.build", "true");
            query.set("spellcheck.count", searchParam.getTipCount());
            query.set("spellcheck.extendedResults", "false");

            QueryResponse queryResp = solrServer.query(query, METHOD.POST);
            searchResult = addReuslt2(queryResp.getSpellCheckResponse(), searchResult);

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
                    SearchMessageKeyContants.Error.KEY_ERROR_QUERY, new String[]{SearchUtils.getExceptionMessage(e1)},
                    LocaleUtil.getLocale()));
            return searchResult;
        }

        return searchResult;
    }

    public static SearchResult<CollationReuslt> addReuslt2(
            SpellCheckResponse spellCheckResponse,
            SearchResult<CollationReuslt> searchResult) {

        if (spellCheckResponse != null) {

            List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();

            if (suggestionList != null && !suggestionList.isEmpty()) {

                List<CollationReuslt> suggestionStrList = new ArrayList<CollationReuslt>();
                CollationReuslt collationReuslt = null;

                for (Suggestion suggestion : suggestionList) {
                    for (String s : suggestion.getAlternatives()) {
                        collationReuslt = new CollationReuslt(s, 123);
                        suggestionStrList.add(collationReuslt);
                    }
                }

                searchResult.setResultList(suggestionStrList);

            }

        }

        return searchResult;
    }

}
