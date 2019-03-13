package com.zengshi.ecp.search.dubbo.search.result.binder;

import com.zengshi.ecp.search.dubbo.search.HighlighterParam;
import com.zengshi.ecp.search.dubbo.search.comp.BaseComp;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.List;
import java.util.Map;

public abstract class BaseResultBinder<T> {

    HighlighterParam highlighterParam;

    //多语言类型：1记录多语言，2字段多语言
    String mulLanType;

    List<ELang> langs;
    
    Map<String,String> multiLangMap;
    
    List<String> fieldList=null;
    
    SolrDocumentList solrDocList=null;

    Map<String, String> beanFieldName2IndexName = null;

    Map<String, Map<String, List<String>>> highlightMap = null;

    Map<String, String> hlMap=null;

    boolean ifHighlight=true;

    public abstract List<String> getFields() throws ReusltBindingException;
    
    public abstract List<T> getResults(HighlighterParam highlighterParam,SolrDocumentList solrDocList, boolean ifHighlight,
                                       Map<String, Map<String, List<String>>> highlightMap,
                                       Map<String, String> beanFieldName2IndexName, String mulLanType, List<ELang> langs, Map<String,String> multiLangMap,Map<String, String> hlMap) throws ReusltBindingException;
    
    public Object getFieldValue(String field, SolrDocument solrDocument)
            throws ReusltBindingException {

        Object fieldValue = null;
        
        //多语言字段
        //1、多语言字段智能返回所在语言下的字段值
        //2、含通配符字段需要在字段列表中声明输出多个

        String indexNameSrc=BaseComp.getIndexField(field, this.getBeanFieldName2IndexName());

        String indexName=indexNameSrc;

        //记录多语言
        if(StringUtils.equals(mulLanType,SearchConstants.STATUS_1)){

            //记录多语言返回字段中的字段类型部分应该按照语言字段（lang）指定的返回，目的是支持如果是检索全部（*）应该就是返回所有语言的数据了。
            String rowLangFieldType= (String) solrDocument.getFieldValue(SearchConstants.FIELD_LANG_FIELD_TYPE);

            if(StringUtils.isNotBlank(rowLangFieldType)&&BaseComp.isMultiLangField(this.getMultiLangMap(), field)){
                String segs[]=indexName.split(SearchConstants.UNDERLINE);
                indexName=indexName.replace(SearchConstants.UNDERLINE+segs[1]+SearchConstants.UNDERLINE,
                        SearchConstants.UNDERLINE+rowLangFieldType+SearchConstants.UNDERLINE);
            }

        }else if(StringUtils.equals(mulLanType,SearchConstants.STATUS_2)){//字段多语言

            //TODO 如果是检索全部（*）应该就是返回所有语言的数据了，对于字段多语言如何返回呢？应该是需要遍历搜索引擎支持的所有语言类型判断返回。
            //目前先只支持当前检索语言的记录
            /*if((this.getLang()!=null)&&BaseComp.isMultiLangField(this.getMultiLangMap(), field)){
                String segs[]=indexName.split(SearchConstants.UNDERLINE);
                indexName=indexName.replace(SearchConstants.UNDERLINE+segs[1]+SearchConstants.UNDERLINE,
                        SearchConstants.UNDERLINE+this.getLang().getLang()+SearchConstants.UNDERLINE);
            }*/
        }

        if(StringUtils.isBlank(indexName)){
            return null;
        }

        //设置需要高亮，且高亮字段配置不为空，且字段在高亮字段范围内（防止正常字段被截取破坏）
        if (this.isIfHighlight()&&this.getHighlightMap() != null&&this.getHlMap().containsKey(indexNameSrc)) {
            Map<String, List<String>> map = this.getHighlightMap().get(solrDocument
                    .getFieldValue(SearchConstants.ID));
            if (map != null) {
                List<String> list = map.get(indexName);
                if (list != null && list.size() > 0) {

                    // 获取并设置高亮的字段
                    fieldValue = list.get(0);
                }
            }

        }

        if (fieldValue == null) {
            fieldValue = solrDocument.getFieldValue(indexName);

            //高亮时，内容截取，且字段在高亮字段范围内（防止正常字段被截取破坏）
            if(this.isIfHighlight()&&highlighterParam!=null&&this.getHlMap().containsKey(indexNameSrc)){
                if(fieldValue instanceof String){
                    String s=(String)fieldValue;
                    if(s.length()>highlighterParam.getFragsize()){
                        fieldValue=s.substring(0,highlighterParam.getFragsize());
                    }
                }
            }
        }

        return fieldValue;
    }

    public List<ELang> getLangs() {
        return langs;
    }

    public void setLangs(List<ELang> langs) {
        this.langs = langs;
    }

    public String getMulLanType() {
        return mulLanType;
    }

    public void setMulLanType(String mulLanType) {
        this.mulLanType = mulLanType;
    }

    public Map<String, String> getMultiLangMap() {
        return multiLangMap;
    }

    public Map<String, String> getHlMap() {
        return hlMap;
    }

    public void setHlMap(Map<String, String> hlMap) {
        this.hlMap = hlMap;
    }

    public void setMultiLangMap(Map<String, String> multiLangMap) {
        this.multiLangMap = multiLangMap;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

    public SolrDocumentList getSolrDocList() {
        return solrDocList;
    }

    public void setSolrDocList(SolrDocumentList solrDocList) {
        this.solrDocList = solrDocList;
    }

    public Map<String, String> getBeanFieldName2IndexName() {
        return beanFieldName2IndexName;
    }

    public void setBeanFieldName2IndexName(Map<String, String> beanFieldName2IndexName) {
        this.beanFieldName2IndexName = beanFieldName2IndexName;
    }

    public Map<String, Map<String, List<String>>> getHighlightMap() {
        return highlightMap;
    }

    public void setHighlightMap(Map<String, Map<String, List<String>>> highlightMap) {
        this.highlightMap = highlightMap;
    }

    public boolean isIfHighlight() {
        return ifHighlight;
    }

    public void setIfHighlight(boolean ifHighlight) {
        this.ifHighlight = ifHighlight;
    }

    public enum EBinderType{
        MAP("map"),
        BEAN("bean");
        private String binderType;
        EBinderType(String binderType){
            this.binderType=binderType;
        }
        public String getBinderType() {
            return binderType;
        }
    }
}

