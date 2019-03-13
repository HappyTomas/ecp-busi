package com.zengshi.ecp.search.dubbo.search.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;

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
public class ExceptInResultSupport {

    public static SolrQuery exceptInResult(SolrQuery solrQuery, SearchParam searchParam,
            Map<String, String> dfMap,Map<String,List<String>> indexName2MulLanIndexNameMapList) throws SearchException {
        
        if(CollectionUtils.isEmpty(searchParam.getExceptKeywordList())){
            return solrQuery; 
        }

        for (String exceptKeyword : searchParam.getExceptKeywordList()) {
        	
        	String keyword=exceptKeyword;
        	if(searchParam.isIfFuzzyQuery()){
        		keyword=SearchUtils.getFuzzyQueryKeyword(keyword);
        	}
            
            StringBuffer sb = new StringBuffer(EOperator.NOT.getName()+" "+SearchConstants.BRACKETS_LEFT);

            boolean isFirst = true;
            for (String df : dfMap.keySet()) {
                List<String> mulLanIndexNameList=new ArrayList<>();
                mulLanIndexNameList.add(df);
                //多语言字段
                if(indexName2MulLanIndexNameMapList.containsKey(df)){
                    mulLanIndexNameList=indexName2MulLanIndexNameMapList.get(df);
                }

                for(String s:mulLanIndexNameList){
                    if (isFirst) {
                        sb.append(s + SearchConstants.COLON + keyword);
                        isFirst = false;
                    } else {

                        // 配置的默认搜索字段，是指要被搜索的字段。和查询转换模式没关联。这边是用OR。
                        sb.append(" " + EOperator.OR.getName() + " " + s + SearchConstants.COLON
                                + keyword);
                    }
                }
            }

            sb.append(SearchConstants.BRACKETS_RIGHT);
            
            solrQuery.addFilterQuery(sb.toString());

        }

        return solrQuery;

    }

}
