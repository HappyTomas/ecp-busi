package com.zengshi.ecp.search.dubbo.search.comp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.util.NamedList;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.ClusteringReuslt;
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
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: 通用自动聚合/聚类接口<br>
 * Date:2016年1月13日下午12:45:51  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class ClusteringComp extends BaseComp {

    private final static String MODULE = "【搜索引擎】ClusteringComp";

    private final static String TEXT = "Clustering异常";

    @SuppressWarnings("unchecked")
    public static SearchResult<ClusteringReuslt> clustering(SearchParam searchParam) {

        LogUtil.info(MODULE, searchParam.toString());

        SearchResult<ClusteringReuslt> searchResult = new SearchResult<ClusteringReuslt>();

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
            SolrServer solrServer = SearchUtils.getSolrServer(searchParam.getCollectionName(),true);

            SolrQuery query = new SolrQuery();
            query.set(CommonParams.QT, ERequestType.CLUSTERING.getHandler());

            if (!searchParam.isIfRetDocList()) {
                query.setRows(0);
            } else {
                query.setStart((int) ((searchParam.getPageNo() - 1) * searchParam.getPageSize()))
                        .setRows(searchParam.getPageSize());
            }

            //如果集群中某一个分片的节点都挂了，为了让集群可以正常提供查询服务，需要添加下面代码
            //表示不使用分布式搜索，如果设置为true则会导致返回结果为空
            //query.set("distrib",false);
            //query.setDistrib(false);

            //只从存活的shards获取数据,没有此参数，如果集群内有挂掉的shard，将显示：
            //no servers hosting shard
            //需要注意值是Boolean而不是字符串
            query.set("shards.tolerant",true);

            QueryResponse queryResp = solrServer.query(query, METHOD.POST);
            searchResult = addReuslt(queryResp.getResponse(), searchResult);

            // 额外响应信息设置
            searchResult = addBaseRespInfo(searchResult, queryResp);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, TEXT, e);

            // 已国际化信息
            searchResult.setMessage(SearchUtils.getExceptionMessage(e));
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static SearchResult<ClusteringReuslt> addReuslt(NamedList<Object> nameList,
            SearchResult<ClusteringReuslt> searchResult) {

        List<NamedList> clusters = (List<NamedList>) nameList.get("clusters");

        if (CollectionUtils.isNotEmpty(clusters)) {
            List<ClusteringReuslt> resultList = new ArrayList<ClusteringReuslt>();
            for (NamedList nl : clusters) {

                resultList.add(new ClusteringReuslt((List<String>) nl.get("labels"), String.valueOf(nl
                        .get("score")), (List<String>) nl.get("docs")));
            }
            searchResult.setResultList(resultList);
        }

        return searchResult;

    }
}
