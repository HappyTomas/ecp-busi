package com.zengshi.ecp.search.dubbo.search.support;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月22日上午11:57:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class HighlightSupport {

    public static SolrQuery addHighlightSupport(SearchParam searchParam,SolrQuery solrQuery,
                                                SecConfigRespDTO currSecConfigRespDTO, Set<String> fieldSet,Map<String,List<String>> indexName2MulLanIndexNameMapList) {

        if (StringUtils.equals(SearchConstants.STATUS_1, currSecConfigRespDTO.getConfigQueryIfHl())) {

            solrQuery.setHighlight(true);
            solrQuery.setHighlightSimplePre(currSecConfigRespDTO.getConfigQueryHlPre())
                    .setHighlightSimplePost(currSecConfigRespDTO.getConfigQueryHlPost())
                    .setHighlightSnippets(1).setHighlightFragsize(Integer.MAX_VALUE);

            if(searchParam.getHighlighterParam()!=null){
                solrQuery.set("hl.useFastVectorHighlighter",searchParam.getHighlighterParam().isUseFastVectorHighlighter());
                solrQuery.setHighlightFragsize(searchParam.getHighlighterParam().getFragsize());
                solrQuery.setHighlightSnippets(searchParam.getHighlighterParam().getSnippets());
                solrQuery.set("hl.boundaryScanner",searchParam.getHighlighterParam().getBoundaryScanner().getName());
                solrQuery.set("hl.bs.type",searchParam.getHighlighterParam().getBsType().getName());
                solrQuery.set("hl.bs.maxScan",searchParam.getHighlighterParam().getBsMaxScan());
                solrQuery.set("hl.bs.chars",searchParam.getHighlighterParam().getBsChars());
                solrQuery.set("hl.bs.language",searchParam.getHighlighterParam().getBsLanguage());
                solrQuery.set("hl.bs.country",searchParam.getHighlighterParam().getBsCountry());
            }

            for (String s : fieldSet) {
                List<String> mulLanIndexNameList=new ArrayList<>();
                mulLanIndexNameList.add(s);
                //多语言字段
                if(indexName2MulLanIndexNameMapList.containsKey(s)){
                    mulLanIndexNameList=indexName2MulLanIndexNameMapList.get(s);
                }

                for(String s_:mulLanIndexNameList){
                    solrQuery.addHighlightField(s_);
                }
            }

        }

        return solrQuery;

    }

}
