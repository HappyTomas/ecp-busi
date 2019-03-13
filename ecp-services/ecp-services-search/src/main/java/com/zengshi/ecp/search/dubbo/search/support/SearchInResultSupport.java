package com.zengshi.ecp.search.dubbo.search.support;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.comp.BaseComp;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
public class SearchInResultSupport {

    public static List<ELang> mergeSearchInResultLang(List<ELang> mainSearchLangs,SearchParam searchParam){
        if(CollectionUtils.isEmpty(searchParam.getExtraKeywordList())){
            return mainSearchLangs;
        }

        if(mainSearchLangs==null){
            mainSearchLangs=new ArrayList<ELang>();
        }

        for (String extranKeyword : searchParam.getExtraKeywordList()) {
            //多语言检索支持
            //语言环境
            List<ELang> langs=BaseComp.langAnalysis(extranKeyword);

            for(ELang lang:langs){
                if(!mainSearchLangs.contains(lang)){
                    mainSearchLangs.add(lang);
                }
            }
        }

        return mainSearchLangs;
    }

    public static SolrQuery searchInResult(SolrQuery solrQuery, SearchParam searchParam) throws SearchException {
        
        if(CollectionUtils.isEmpty(searchParam.getExtraKeywordList())){
            return solrQuery; 
        }

        for (String extranKeyword : searchParam.getExtraKeywordList()) {

            //检索关键字转义
        	String keyword= SearchFacade.keywordProcess(extranKeyword);
        	if(searchParam.isIfFuzzyQuery()){
        		keyword=SearchUtils.getFuzzyQueryKeyword(keyword);
        	}

            //modify by huangdf@izengshi.com @ 20161228 fix : 结果中搜（次关键字实现同edismax qf）
            //结果中搜支持高亮
            if(StringUtils.isBlank(solrQuery.get(CommonParams.Q))||SearchConstants.STAR.equals(solrQuery.get(CommonParams.Q))){
                solrQuery.setQuery(keyword);
            }else{
                solrQuery.setQuery(solrQuery.get(CommonParams.Q)+" "+EOperator.AND.getName()+" "+keyword);
            }

            /*StringBuffer sb = new StringBuffer(SearchConstants.BRACKETS_LEFT);

            boolean isFirst = true;
            for (String df : dfMap.keySet()) {
                String boostStr="";
                if(StringUtils.isNotBlank(dfMap.get(df))){
                    boostStr="^"+dfMap.get(df);
                }
                if (isFirst) {
                    sb.append(df + SearchConstants.COLON + keyword+boostStr);
                    isFirst = false;
                } else {

                    // 配置的默认搜索字段，是指要被搜索的字段。和查询转换模式没关联。这边是用OR。
                    sb.append(" " + EOperator.OR.getName() + " " + df + SearchConstants.COLON
                            + keyword+boostStr);
                }
            }

            sb.append(SearchConstants.BRACKETS_RIGHT);
            
            solrQuery.addFilterQuery(sb.toString());*/

        }

        return solrQuery;

    }

}
