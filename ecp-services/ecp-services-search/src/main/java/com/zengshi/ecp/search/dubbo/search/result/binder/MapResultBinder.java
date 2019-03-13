package com.zengshi.ecp.search.dubbo.search.result.binder;

import com.zengshi.ecp.search.dubbo.search.HighlighterParam;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResultBinder extends BaseResultBinder<Map<String,Object>>{
    
    public MapResultBinder(List<String> fieldList) throws ReusltBindingException{
        this.setFieldList(fieldList);
    }
    
    @Override
    public List<String> getFields() {
        return this.getFieldList();
    }

    @Override
    public List<Map<String,Object>> getResults(HighlighterParam highlighterParam, SolrDocumentList solrDocList, boolean ifHighlight,
                                               Map<String, Map<String, List<String>>> highlightMap,
                                               Map<String, String> beanFieldName2IndexName, String mulLanType, List<ELang> langs, Map<String,String> multiLangMap,Map<String, String> hlMap) throws ReusltBindingException {
        
        if (beanFieldName2IndexName == null || beanFieldName2IndexName.size() == 0) {
            throw new ReusltBindingException(
                    "the param map beanFieldName2IndexName null or size==0");
        }

        this.highlighterParam=highlighterParam;
        this.setMulLanType(mulLanType);
        this.setLangs(langs);
        this.setMultiLangMap(multiLangMap);
        this.setHlMap(hlMap);
        this.setSolrDocList(solrDocList);
        this.setHighlightMap(highlightMap);
        this.setBeanFieldName2IndexName(beanFieldName2IndexName);
        this.setIfHighlight(ifHighlight);
        
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(solrDocList.size());

        for (SolrDocument sdoc : solrDocList) {
            result.add(getMap(sdoc));
        }
        return result;
    }
    
    private Map<String, Object> getMap(SolrDocument solrDoc)
            throws ReusltBindingException {
        Map<String, Object> map = new HashMap<String, Object>();

        for (String field : this.getFieldList()) {
            map.put(field, this.getFieldValue(field, solrDoc));
        }

        return map;
    }

}
