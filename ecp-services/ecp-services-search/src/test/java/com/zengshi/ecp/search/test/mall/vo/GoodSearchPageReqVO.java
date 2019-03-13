package com.zengshi.ecp.search.test.mall.vo;

import java.util.List;

import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryCategory;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月21日上午9:23:24  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class GoodSearchPageReqVO extends _EcpBasePageReqVO {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 4802843376087735942L;
    
    private String id;
    
    private String keyword;

    private List<QueryCategory> queryCategoryList;
    
    private boolean categoryOrRelation=true;

    //private String propertyGroup;

    private String searchmore;
    
    private String exceptKeyword;
    
    /**
     * 商品类型id串，多个商品类型id用或操作
     */
    private String gdsTypeIds;
    
    /**
     * 地区串
     */
    private String areas;
    
    /**
     * 来源串
     */
    private String sources;
    
    /**
     * 日期时间跨度标识
     */
    private String dateTimeRangeFlag;

    /**
     * 排序字段
     */
    private String sortFields;
    
    /**
     * 排序值
     */
    private String sortFieldsSort;
    
    /**
     * 是否Facet
     */
    private boolean ifFacet=false;
    
    /**
     * Facet字段
     */
    private String facetFields;
    
    /**
     * 1：网格，2：列表
     */
    private String showType="1";
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<QueryCategory> getQueryCategoryList() {
        return queryCategoryList;
    }

    public void setQueryCategoryList(List<QueryCategory> queryCategoryList) {
        this.queryCategoryList = queryCategoryList;
    }

    public boolean isCategoryOrRelation() {
        return categoryOrRelation;
    }

    public void setCategoryOrRelation(boolean categoryOrRelation) {
        this.categoryOrRelation = categoryOrRelation;
    }

    public String getSearchmore() {
        return searchmore;
    }

    public void setSearchmore(String searchmore) {
        this.searchmore = searchmore;
    }
    
    public String getExceptKeyword() {
        return exceptKeyword;
    }

    public void setExceptKeyword(String exceptKeyword) {
        this.exceptKeyword = exceptKeyword;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public boolean isIfFacet() {
        return ifFacet;
    }

    public void setIfFacet(boolean ifFacet) {
        this.ifFacet = ifFacet;
    }
    
    public String getFacetFields() {
        return facetFields;
    }

    public void setFacetFields(String facetFields) {
        this.facetFields = facetFields;
    }

    public String getGdsTypeIds() {
        return gdsTypeIds;
    }

    public void setGdsTypeIds(String gdsTypeIds) {
        this.gdsTypeIds = gdsTypeIds;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getDateTimeRangeFlag() {
        return dateTimeRangeFlag;
    }

    public void setDateTimeRangeFlag(String dateTimeRangeFlag) {
        this.dateTimeRangeFlag = dateTimeRangeFlag;
    }

    public String getSortFields() {
        return sortFields;
    }

    public void setSortFields(String sortFields) {
        this.sortFields = sortFields;
    }

    public String getSortFieldsSort() {
        return sortFieldsSort;
    }

    public void setSortFieldsSort(String sortFieldsSort) {
        this.sortFieldsSort = sortFieldsSort;
    }
    
}
