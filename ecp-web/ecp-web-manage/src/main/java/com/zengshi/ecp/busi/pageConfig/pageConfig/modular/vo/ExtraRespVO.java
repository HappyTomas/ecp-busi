package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    
    private String searchCategory;
    
    private String parentCateCode;
    
    private String parentCateName;
    
    private String keyword;
    
    //顶级分类、二级分类映射和Facet信息
    private Map<String, List<Map<String,String>>> cateInfoMap;
    
    //顶级分类名称有序列表，便于页面展示处理
    private List<String> topCateNameList;
    
    //顶级分类名称、分类信息映射Map
    private Map<String,Map<String,String>> topCateInfoMap;

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
    
}
