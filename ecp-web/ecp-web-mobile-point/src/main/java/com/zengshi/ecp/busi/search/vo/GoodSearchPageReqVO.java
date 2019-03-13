package com.zengshi.ecp.busi.search.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * Created by HDF on 2016/6/28.
 */
public class GoodSearchPageReqVO extends EcpBasePageReqVO {

    /**
     * 搜索关键字
     */
    private String keyword;
    /**
     * 搜索类型。1：商品搜索，2：店铺搜索
     */
    private String searchType;

    /**
     * 单个过滤和多个属性过滤都使用属性值组统一处理
     */
    private String propertyGroup;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序字段排序
     */
    private String sort;

    private int page;
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
     * 在结果中搜
     */
    private String searchmore;

    /**
     * 起点价格
     */
    private String priceStart;
    
    /**
     * 终点价格
     */
    private String priceEnd;
    /**
     * 是否积分商品
     */
    private String ifScoreGds;
    /**
     * 是否返回结果，在只做统计类时用到
     */
    private boolean retResult=true;
    /**
     * 商品类型
     */
    private String gdsTypeId;
    
    private String rangeType;//范围查询类型
    
    //<--------------个性化参数---------------->
    private String showType;
    private String adid;
    //<--------------个性化参数---------------->\
    /**
     * 促销类型编码
     */
    private String promTypeCode;
    
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPropertyGroup() {
        return propertyGroup;
    }

    public void setPropertyGroup(String propertyGroup) {
        this.propertyGroup = propertyGroup;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public String getSearchmore() {
        return searchmore;
    }

    public void setSearchmore(String searchmore) {
        this.searchmore = searchmore;
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

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

	public String getIfScoreGds() {
		return ifScoreGds;
	}

	public void setIfScoreGds(String ifScoreGds) {
		this.ifScoreGds = ifScoreGds;
	}

	public String getGdsTypeId() {
		return gdsTypeId;
	}

	public void setGdsTypeId(String gdsTypeId) {
		this.gdsTypeId = gdsTypeId;
	}

	public String getRangeType() {
		return rangeType;
	}

	public void setRangeType(String rangeType) {
		this.rangeType = rangeType;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}
    
    
}
