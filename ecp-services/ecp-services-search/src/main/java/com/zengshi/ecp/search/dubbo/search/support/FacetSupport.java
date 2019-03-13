package com.zengshi.ecp.search.dubbo.search.support;

import com.zengshi.ecp.search.dubbo.search.DateFacetField;
import com.zengshi.ecp.search.dubbo.search.QueryFacetField;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.comp.BaseComp;
import com.zengshi.ecp.search.dubbo.search.result.FieldFacetResult;
import com.zengshi.ecp.search.dubbo.search.result.QueryFacetResult;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.FacetParams;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月22日下午12:02:23 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class FacetSupport {

    public static SolrQuery support(SolrQuery solrQuery, SearchParam searchParam,
            Map<String, String> facetMap, Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList)
            throws SearchException {

        solrQuery = addFieldFacetSupport(solrQuery, searchParam.getCollectionName(),searchParam.getFieldFacetFieldList(), facetMap,indexName2MulLanIndexNameMapList);
        solrQuery = addDateFacetSupport(solrQuery, searchParam.getDataFacetFieldList(),searchParam.getLangs(), beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
        solrQuery = addQueryFacetSupport(solrQuery, searchParam.getQueryFacetFieldList(),searchParam.getLangs(), beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
        
        if(StringUtils.isNotBlank(solrQuery.get(FacetParams.FACET))&&Boolean.valueOf(solrQuery.get(FacetParams.FACET))){
            solrQuery.setFacetMinCount(1).setFacetLimit(searchParam.getFacetLimit()).setFacetSort(FacetParams.FACET_SORT_COUNT)
            .setFacetMissing(false);
        }

        return solrQuery;

    }

    private static SolrQuery addFieldFacetSupport(SolrQuery solrQuery, String collectionName,List<String> fieldFacetFieldList,
            Map<String, String> facetMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {

        if (CollectionUtils.isEmpty(fieldFacetFieldList)) {
            return solrQuery;
        }

        if (MapUtils.isEmpty(facetMap)) {
            throw new SearchException(ResourceMsgUtil.getMessage(
                    SearchMessageKeyContants.Info.KEY_INFO_FIELDFACET_EMPTY, new String[]{collectionName},
                    LocaleUtil.getLocale()));
        }

        for (String s : fieldFacetFieldList) {
            if (!facetMap.containsKey(s)) {
                throw new SearchException(ResourceMsgUtil.getMessage(
                        SearchMessageKeyContants.Info.KEY_INFO_FIELDFACET_NOTREGISTERD,
                        new String[] { s }, LocaleUtil.getLocale()));
            }

            //TODO 需要考虑多语言字段facet问题，需要考虑多语言字段Facet结果合并的问题
            /*List<String> mulLanIndexNameList=new ArrayList<>();
            mulLanIndexNameList.add(facetMap.get(s));
            //多语言字段
            if(indexName2MulLanIndexNameMapList.containsKey(facetMap.get(s))){
                mulLanIndexNameList=indexName2MulLanIndexNameMapList.get(facetMap.get(s));
            }
            for(String s_:mulLanIndexNameList){
                solrQuery.addFacetField(s_);
            }*/

            solrQuery.addFacetField(facetMap.get(s));
        }

        // SolrJ内部自动设置
        solrQuery.setFacet(true);

        return solrQuery;

    }

    public static Map<String, FieldFacetResult> getFieldFacetResult(QueryResponse queryResp,Map<String, String> indexName2BeanFieldNameCnMap) throws SearchException {

        List<FacetField> facets = queryResp.getFacetFields();
        if (facets != null && facets.size() > 0) {

            Map<String, FieldFacetResult> facetFieldResultMap = new HashMap<String, FieldFacetResult>();
            FieldFacetResult facetFieldResult;
            List<FieldFacetResult.Count> countList;

            for (FacetField facet : facets) {

                countList = new ArrayList<FieldFacetResult.Count>();
                List<Count> counts = facet.getValues();
                for (Count count : counts) {
                    countList.add(new FieldFacetResult.Count(count.getName(), count.getCount()));
                }

                // 暂时不使用key操作符实现，以防后面可能需要根据k-v做扩展
                String field = BaseComp.getSrcField(facet.getName());
                
                if(StringUtils.isEmpty(field)){
                    continue;
                }

                //TODO 需要考虑多语言字段facet问题，需要考虑多语言字段Facet结果合并的问题
                
                facetFieldResult = new FieldFacetResult(field,BaseComp.getSrcFieldCn(facet.getName(), indexName2BeanFieldNameCnMap), countList);

                facetFieldResultMap.put(facetFieldResult.getName(), facetFieldResult);
            }

            return facetFieldResultMap;
        }

        return null;

    }

    private static SolrQuery addDateFacetSupport(SolrQuery solrQuery, List<DateFacetField> dataFacetFieldList,List<ELang> langs,
            Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {
        if (dataFacetFieldList != null && !dataFacetFieldList.isEmpty()) {
            String indexName="";
            for (DateFacetField dateFacetField : dataFacetFieldList) {

                BaseComp.checkSrcField(dateFacetField.getName(), beanFieldName2IndexNameMap);
                
                //多语言字段
                indexName=BaseComp.getIndexField(dateFacetField.getName(), beanFieldName2IndexNameMap);

                //TODO 需要考虑多语言字段facet问题，需要考虑多语言字段Facet结果合并的问题
                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                    throw new SearchException("DateFacetField字段暂不支持多语言字段");
                }

                solrQuery
                        .addDateRangeFacet(indexName,
                                dateFacetField.getStart(), dateFacetField.getEnd(),
                                dateFacetField.getGap());

            }
         
            // SolrJ内部自动设置
            solrQuery.setFacet(true);
        }
        return solrQuery;
    }

    public static void getDateFacetResult() {

        // List<RangeFacet> rangeFacetList=queryResp.getFacetRanges();
        // if(rangeFacetList!=null&&!rangeFacetList.isEmpty()){
        // for(RangeFacet rangeFacet:rangeFacetList){
        // }
        // }

    }

    private static SolrQuery addQueryFacetSupport(SolrQuery solrQuery, List<QueryFacetField> queryFacetFieldList,List<ELang> langs,
            Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {

        if (queryFacetFieldList != null && !queryFacetFieldList.isEmpty()) {
            String indexName="";
            for (QueryFacetField queryFacetField : queryFacetFieldList) {

                BaseComp.checkSrcField(queryFacetField.getName(), beanFieldName2IndexNameMap);
                
                //多语言字段
                indexName=BaseComp.getIndexField(queryFacetField.getName(), beanFieldName2IndexNameMap);

                //TODO 需要考虑多语言字段facet问题，需要考虑多语言字段Facet结果合并的问题
                if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                    throw new SearchException("QueryFacetField字段暂不支持多语言字段");
                }

                solrQuery.addFacetQuery(indexName+ ":[" + queryFacetField.getStart() + " " + SearchConstants.TO + " "
                        + queryFacetField.getEnd() + "]");

            }
         
            // SolrJ内部自动设置
            solrQuery.setFacet(true);
        }
        return solrQuery;
    }

    public static Map<String, QueryFacetResult> getQueryFacetResult(QueryResponse queryResp,Map<String, String> indexName2BeanFieldNameCnMap) throws SearchException {

        Map<String, Integer> facetQuery = queryResp.getFacetQuery();
        if (facetQuery != null && !facetQuery.isEmpty()) {
            Map<String, QueryFacetResult> queryFacetResultMap = new HashMap<String, QueryFacetResult>();
            QueryFacetResult queryFacetResult;
            String key;
            Integer value;
            String field;
            String fieldCn;
            String start;
            String end;

            for (Entry<String, Integer> entry : facetQuery.entrySet()) {

                key = entry.getKey();
                value = entry.getValue();

                String[] arr1 = key.split(SearchConstants.COLON);
                field = BaseComp.getSrcField(arr1[0]);
                
                if(StringUtils.isEmpty(field)){
                    continue;
                }
                
                fieldCn = BaseComp.getSrcFieldCn(arr1[0], indexName2BeanFieldNameCnMap);
                String[] arr2 = arr1[1].split(SearchConstants.TO);
                start = arr2[0].trim().substring(1);
                end = arr2[1].trim();
                end = end.substring(0, end.length() - 1);

                if (queryFacetResultMap.containsKey(field)) {
                    queryFacetResult = queryFacetResultMap.get(field);
                } else {
                    queryFacetResult = new QueryFacetResult(field, fieldCn);
                    queryFacetResultMap.put(field, queryFacetResult);
                }

                //TODO 需要考虑多语言字段facet问题，需要考虑多语言字段Facet结果合并的问题

                queryFacetResult.addCount(new QueryFacetResult.Count(start, end, value));

            }

            return queryFacetResultMap;
        }

        return null;

    }

}
