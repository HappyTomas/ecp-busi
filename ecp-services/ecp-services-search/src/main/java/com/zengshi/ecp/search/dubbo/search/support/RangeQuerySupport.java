package com.zengshi.ecp.search.dubbo.search.support;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.search.RecommendParam;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrQuery;

import com.zengshi.ecp.search.dubbo.search.RangeQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.comp.BaseComp;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月25日上午11:28:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class RangeQuerySupport {

    public static SolrQuery addRangeQuerySupport(SolrQuery solrQuery, RecommendParam recommendParam,
                                                 Map<String, String> beanFieldName2IndexNameMap, Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {
        return addRangeQuerySupport(solrQuery,recommendParam.getRangeQueryFieldList(),recommendParam.getLangs(),beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
    }

    public static SolrQuery addRangeQuerySupport(SolrQuery solrQuery, SearchParam searchParam,
                                                 Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {
        return addRangeQuerySupport(solrQuery,searchParam.getRangeQueryFieldList(),searchParam.getLangs(),beanFieldName2IndexNameMap,multiLangMap,indexName2MulLanIndexNameMapList);
    }

    public static SolrQuery addRangeQuerySupport(SolrQuery solrQuery, List<RangeQueryField> rangeQueryFieldList,List<ELang> langs,
            Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {

        if (CollectionUtils.isEmpty(rangeQueryFieldList)) {
            return solrQuery;
        }

        StringBuffer sb = new StringBuffer();

        boolean first=true;
        String indexName="";
        for (RangeQueryField rangeQueryField : rangeQueryFieldList) {

            BaseComp.checkSrcField(rangeQueryField.getName(), beanFieldName2IndexNameMap);
            
            //多语言字段
            indexName=BaseComp.getIndexField(rangeQueryField.getName(), beanFieldName2IndexNameMap);

            if(indexName2MulLanIndexNameMapList.containsKey(indexName)){
                throw new SearchException("RangeQueryField字段不支持多语言字段");
            }
            
            if(first){
                first=false;
                sb.append(indexName+ ":["
                        + rangeQueryField.getStart() + " " + SearchConstants.TO + " "
                        + rangeQueryField.getEnd() + "]");
            }else{
             
                // 精确查询
                sb.append(" " + EOperator.AND.getName() + " "+indexName + ":["
                        + rangeQueryField.getStart() + " " + SearchConstants.TO + " "
                        + rangeQueryField.getEnd() + "]");
            }

        }
        
        solrQuery.addFilterQuery(sb.toString());

        return solrQuery;

    }

}
