package com.zengshi.ecp.search.dubbo.search;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.search.util.ELang;
import org.apache.solr.client.solrj.SolrQuery;

public class QueryBuilderResp {
    
    private SolrQuery solrQuery;
    
    private Map<String, String> flMap ;

    private Map<String, String> hlMap ;

    private Map<String, String> facetMap ;

    private Map<String, String> dfMap ;
    
    private Map<String,String> multiLangMap;

    private List<ELang> langs;

    private Map<String,List<String>> indexName2MulLanIndexNameMapList;

    public SolrQuery getSolrQuery() {
        return solrQuery;
    }

    public void setSolrQuery(SolrQuery solrQuery) {
        this.solrQuery = solrQuery;
    }

    public Map<String, String> getFlMap() {
        return flMap;
    }

    public void setFlMap(Map<String, String> flMap) {
        this.flMap = flMap;
    }

    public Map<String, String> getHlMap() {
        return hlMap;
    }

    public void setHlMap(Map<String, String> hlMap) {
        this.hlMap = hlMap;
    }

    public Map<String, String> getFacetMap() {
        return facetMap;
    }

    public void setFacetMap(Map<String, String> facetMap) {
        this.facetMap = facetMap;
    }

    public Map<String, String> getDfMap() {
        return dfMap;
    }

    public void setDfMap(Map<String, String> dfMap) {
        this.dfMap = dfMap;
    }

    public Map<String, String> getMultiLangMap() {
        return multiLangMap;
    }

    public void setMultiLangMap(Map<String, String> multiLangMap) {
        this.multiLangMap = multiLangMap;
    }

    public List<ELang> getLangs() {
        return langs;
    }

    public void setLangs(List<ELang> langs) {
        this.langs = langs;
    }

    public Map<String, List<String>> getIndexName2MulLanIndexNameMapList() {
        return indexName2MulLanIndexNameMapList;
    }

    public void setIndexName2MulLanIndexNameMapList(Map<String, List<String>> indexName2MulLanIndexNameMapList) {
        this.indexName2MulLanIndexNameMapList = indexName2MulLanIndexNameMapList;
    }
}

