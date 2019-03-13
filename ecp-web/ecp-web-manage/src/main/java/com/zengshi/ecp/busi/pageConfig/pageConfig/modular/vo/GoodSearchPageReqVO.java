package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

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
public class GoodSearchPageReqVO extends EcpBasePageReqVO {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 4802843376087735942L;
    
    /**
     * 搜索关键字
     */
    private String keyword;
    
    /**
     * 店铺编码，用户店铺搜索
     */
    private String shopId="";
    
    /**
     * 是否分类Facet
     */
    private boolean categoryPathFacet=false;

    /**
     * 搜索分类
     */
    private String category;

    /**
     * 单个过滤和多个属性过滤都使用属性值组统一处理
     */
    private String propertyGroup;

    /**
     * 在结果中搜
     */
    private String searchmore;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序字段排序
     */
    private String sort;
    
    /**
     * 起点价格
     */
    private String priceStart;
    
    /**
     * 终点价格
     */
    private String priceEnd;
    
    /**
     * 是否返回结果，在只做统计类时用到
     */
    private boolean retResult=true;
    
    //<--------------个性化参数---------------->
    private String showType;
    //<--------------个性化参数---------------->
    
    //组件的入参========start
    private String ifShowTime;//是否展示时间
    
    private String ifShowSale;//是否展示销量
    
    private String ifShowPrice;//是否展示价格
    
    private String showWay;//展示方式
    
    private String firstSort;//默认第一个的排序字段。
    //组件的入参========end
    
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public boolean isCategoryPathFacet() {
        return categoryPathFacet;
    }

    public void setCategoryPathFacet(boolean categoryPathFacet) {
        this.categoryPathFacet = categoryPathFacet;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPropertyGroup() {
        return propertyGroup;
    }

    public void setPropertyGroup(String propertyGroup) {
        this.propertyGroup = propertyGroup;
    }

    public String getSearchmore() {
        return searchmore;
    }

    public void setSearchmore(String searchmore) {
        this.searchmore = searchmore;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(String priceStart) {
        this.priceStart = priceStart;
    }

    public String getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(String priceEnd) {
        this.priceEnd = priceEnd;
    }

    public boolean isRetResult() {
        return retResult;
    }

    public void setRetResult(boolean retResult) {
        this.retResult = retResult;
    }

    public String getIfShowTime() {
        return ifShowTime;
    }

    public void setIfShowTime(String ifShowTime) {
        this.ifShowTime = ifShowTime;
    }

    public String getIfShowSale() {
        return ifShowSale;
    }

    public void setIfShowSale(String ifShowSale) {
        this.ifShowSale = ifShowSale;
    }

    public String getIfShowPrice() {
        return ifShowPrice;
    }

    public void setIfShowPrice(String ifShowPrice) {
        this.ifShowPrice = ifShowPrice;
    }

    public String getShowWay() {
        return showWay;
    }

    public void setShowWay(String showWay) {
        this.showWay = showWay;
    }

    public String getFirstSort() {
        return firstSort;
    }

    public void setFirstSort(String firstSort) {
        this.firstSort = firstSort;
    }
    
}
