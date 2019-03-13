package com.zengshi.ecp.busi.search.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月14日下午5:05:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version @param <T>
 * @since JDK 1.6
 */
public class ExtraRespVO implements Serializable {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = -2004460624500515441L;
    
    /**
     * 1：通过关键字查询，2：通过分类查询，3：分类+关键字
     */
    private String searchType;
    
    /**
     * 1、搜索商品 2、搜店店铺
     */
    private String type;
    
    private String searchCategory="";
    
    private String parentCateCode;
    
    private String parentCateName;
    
    private String keyword;
    
    //顶级分类、二级分类映射和Facet信息
    private Map<String, List<Map<String,String>>> cateInfoMap;
    
    //顶级分类名称有序列表，便于页面展示处理
    private List<String> topCateNameList;
    
    //顶级分类名称、分类信息映射Map
    private Map<String,Map<String,String>> topCateInfoMap;
    
    //默认一级分类和二级分类
    private Map<String,List<String>> firAndSecCategory;
    
    //默认一级分类和二级分类list
    private List<Map<String,List<String>>> firAndSecCategorylist;
    
    //分类属性（当前分类的孩子）
    private List<GdsCategoryRespDTO> proCategorylist;
    
    //高级搜索结果分类路径显示
    private List<GdsCategoryRespDTO> pathCategorylist;
    
    //高级搜索下钻数据
    private List<String> downCatelist;

    /*====================店铺分类相关start======================*/

    public List<String> getDownCatelist() {
		return downCatelist;
	}

	public void setDownCatelist(List<String> downCatelist) {
		this.downCatelist = downCatelist;
	}

	public List<GdsCategoryRespDTO> getPathCategorylist() {
		return pathCategorylist;
	}

	public void setPathCategorylist(List<GdsCategoryRespDTO> pathCategorylist) {
		this.pathCategorylist = pathCategorylist;
	}

	public List<GdsCategoryRespDTO> getProCategorylist() {
		return proCategorylist;
	}

	public void setProCategorylist(List<GdsCategoryRespDTO> proCategorylist) {
		this.proCategorylist = proCategorylist;
	}

	private String shopParentCateCode;

    private String shopParentCateName;

    //顶级分类、二级分类映射和Facet信息
    private Map<String, List<Map<String,String>>> shopCateInfoMap;

    //顶级分类名称有序列表，便于页面展示处理
    private List<String> shopTopCateNameList;

    //顶级分类名称、分类信息映射Map
    private Map<String,Map<String,String>> shopTopCateInfoMap;

    /*====================店铺分类相关  end======================*/

    private List<GdsPropRespDTO> propList;

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentCateCode() {
        return parentCateCode;
    }

    public void setParentCateCode(String parentCateCode) {
        this.parentCateCode = parentCateCode;
    }

    public String getParentCateName() {
        return parentCateName;
    }

    public void setParentCateName(String parentCateName) {
        this.parentCateName = parentCateName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Map<String, List<Map<String, String>>> getCateInfoMap() {
        return cateInfoMap;
    }

    public void setCateInfoMap(Map<String, List<Map<String, String>>> cateInfoMap) {
        this.cateInfoMap = cateInfoMap;
    }

    public List<String> getTopCateNameList() {
        return topCateNameList;
    }

    public void setTopCateNameList(List<String> topCateNameList) {
        this.topCateNameList = topCateNameList;
    }

    public Map<String, Map<String, String>> getTopCateInfoMap() {
        return topCateInfoMap;
    }

    public void setTopCateInfoMap(Map<String, Map<String, String>> topCateInfoMap) {
        this.topCateInfoMap = topCateInfoMap;
    }

    public List<GdsPropRespDTO> getPropList() {
        return propList;
    }

    public void setPropList(List<GdsPropRespDTO> propList) {
        this.propList = propList;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public List<String> getShopTopCateNameList() {
        return shopTopCateNameList;
    }

    public void setShopTopCateNameList(List<String> shopTopCateNameList) {
        this.shopTopCateNameList = shopTopCateNameList;
    }

    public Map<String, List<Map<String, String>>> getShopCateInfoMap() {
        return shopCateInfoMap;
    }

    public void setShopCateInfoMap(Map<String, List<Map<String, String>>> shopCateInfoMap) {
        this.shopCateInfoMap = shopCateInfoMap;
    }

    public Map<String, Map<String, String>> getShopTopCateInfoMap() {
        return shopTopCateInfoMap;
    }

    public void setShopTopCateInfoMap(Map<String, Map<String, String>> shopTopCateInfoMap) {
        this.shopTopCateInfoMap = shopTopCateInfoMap;
    }

    public String getShopParentCateName() {
        return shopParentCateName;
    }

    public void setShopParentCateName(String shopParentCateName) {
        this.shopParentCateName = shopParentCateName;
    }

    public String getShopParentCateCode() {
        return shopParentCateCode;
    }

    public void setShopParentCateCode(String shopParentCateCode) {
        this.shopParentCateCode = shopParentCateCode;
    }

	public Map<String,List<String>> getFirAndSecCategory() {
		return firAndSecCategory;
	}

	public void setFirAndSecCategory(Map<String,List<String>> firAndSecCategory) {
		this.firAndSecCategory = firAndSecCategory;
	}

	public List<Map<String, List<String>>> getFirAndSecCategorylist() {
		return firAndSecCategorylist;
	}

	public void setFirAndSecCategorylist(List<Map<String, List<String>>> firAndSecCategorylist) {
		this.firAndSecCategorylist = firAndSecCategorylist;
	}



}
