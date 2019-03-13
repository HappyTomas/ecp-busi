package com.zengshi.ecp.search.test.mall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryCategory;
import com.zengshi.ecp.search.test.mall.util.WebSearchUtil;
import com.zengshi.ecp.search.test.mall.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.search.test.mall.vo.GoodSearchPageRespVO;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class ControllerTest extends EcpServicesTest {
    
    @SuppressWarnings("rawtypes")
    @Test
    public void searchTest() {
        GoodSearchPageReqVO vo=new GoodSearchPageReqVO();
        vo.setPageNumber(1);
        vo.setPageSize(16);
        vo.setKeyword("*");
        
//        vo.setSearchmore("俄罗斯");
//        vo.setExceptKeyword("1");
        
//        vo.setId("1008");
        
//        List<QueryCategory> queryCategoryList=new ArrayList<QueryCategory>();
//        
//        List<String> categoryCodeList=new ArrayList<String>();
//        categoryCodeList.add("NOT_1083");
//        categoryCodeList.add("NOT_1084");
//        categoryCodeList.add("NOT_1085");
//        QueryCategory queryCategory=new QueryCategory(categoryCodeList, ExtraQueryInfo.FIELD_CATEGORIES);
//        queryCategoryList.add(queryCategory);
//        
//        categoryCodeList=new ArrayList<String>();
//        categoryCodeList.add("NOT_123");
//        categoryCodeList.add("NOT_w45");
////        categoryCodeList.add("1083");
//        queryCategory=new QueryCategory(categoryCodeList, ExtraQueryInfo.FIELD_CATEGORIES);
//        queryCategoryList.add(queryCategory);
//        
//        vo.setQueryCategoryList(queryCategoryList);
        
        //类型
//        vo.setGdsTypeIds("1,NOT_10,NOT_2");
        
        //地区
//        vo.setAreas("中国,日本");
        
        //来源
//        vo.setSources("1233377");
        
        //一个月内
//        vo.setDateTimeRangeFlag("1");
        
        //统计，相关人物或相关地区
//        vo.setIfFacet(true);
//        vo.setFacetFields("keyword");
        
        GoodSearchPageRespVO<Map> pageRespVO=WebSearchUtil.search(vo);
        System.out.println(pageRespVO);
    }

}

