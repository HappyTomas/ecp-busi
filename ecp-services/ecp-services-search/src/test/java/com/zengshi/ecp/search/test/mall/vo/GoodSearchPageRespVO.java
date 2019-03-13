package com.zengshi.ecp.search.test.mall.vo;

import java.util.List;

import com.zengshi.ecp.search.dubbo.search.result.FieldFacetResult.Count;

public class GoodSearchPageRespVO<T> extends _EcpBasePageRespVO<T> {

private static final long serialVersionUID = -2586464623855137683L;
    
    /**
     * 相关新闻Facet结果
     */
    private List<Count> relatedNewsCountList=null;
    
    /**
     * 地区Facet结果
     */
    private List<Count> areaCountList=null;
    
    /**
     * 人物Facet结果
     */
    private List<Count> peopleCountList=null;
    
    /**
     * 来源Facet结果
     */
    private List<Count> sourceCountList=null;
    
    /**
     * 类型Facet结果
     */
    private List<Count> gdsTypeIdAndNameCountList=null;
    
    /**
     * 顶级分类编码Facet结果
     */
    private List<Count> topCategoryCodeCountList=null;
    
    /**
     * “搜索“航母”的人还关注了”Facet结果
     */
    private List<Count> relatedKeywordsCountList=null;

    public List<Count> getRelatedNewsCountList() {
        return relatedNewsCountList;
    }

    public void setRelatedNewsCountList(List<Count> relatedNewsCountList) {
        this.relatedNewsCountList = relatedNewsCountList;
    }

    public List<Count> getAreaCountList() {
        return areaCountList;
    }

    public void setAreaCountList(List<Count> areaCountList) {
        this.areaCountList = areaCountList;
    }

    public List<Count> getPeopleCountList() {
        return peopleCountList;
    }

    public void setPeopleCountList(List<Count> peopleCountList) {
        this.peopleCountList = peopleCountList;
    }

    public List<Count> getSourceCountList() {
        return sourceCountList;
    }

    public void setSourceCountList(List<Count> sourceCountList) {
        this.sourceCountList = sourceCountList;
    }

    public List<Count> getGdsTypeIdAndNameCountList() {
        return gdsTypeIdAndNameCountList;
    }

    public void setGdsTypeIdAndNameCountList(List<Count> gdsTypeIdAndNameCountList) {
        this.gdsTypeIdAndNameCountList = gdsTypeIdAndNameCountList;
    }

    public List<Count> getTopCategoryCodeCountList() {
        return topCategoryCodeCountList;
    }

    public void setTopCategoryCodeCountList(List<Count> topCategoryCodeCountList) {
        this.topCategoryCodeCountList = topCategoryCodeCountList;
    }

    public List<Count> getRelatedKeywordsCountList() {
        return relatedKeywordsCountList;
    }

    public void setRelatedKeywordsCountList(List<Count> relatedKeywordsCountList) {
        this.relatedKeywordsCountList = relatedKeywordsCountList;
    }

}

