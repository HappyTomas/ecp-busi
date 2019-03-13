package com.zengshi.ecp.search.test.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.zengshi.ecp.search.dubbo.search.MulValueExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.util.ELang;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import org.junit.Test;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.result.ClusteringReuslt;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 搜索测试<br>
 * Date:2015年8月14日下午1:03:39 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SearcherTest extends EcpServicesTest {
    
    private SearchParam setKeyWord(SearchParam searchParam){
        
//        searchParam.setKeyword("ipho");
//      searchParam.setKeyword("小");
//      searchParam.setKeyword("中");
//      searchParam.setKeyword("中央");
//      searchParam.setKeyword("hu");
        
        //特征数据测试cloud模式下的suggest功能是否存在问题
        searchParam.setKeyword("中");
        
        return searchParam;
        
    }
    
    @Test
    public void spellcheckTest() {
        SearchParam searchParam = new SearchParam();

        // 前店必须显式指定搜索集合名称
        searchParam.setCurrentSiteId(1l);
        
        searchParam=setKeyWord(searchParam);
        
        SearchResult<CollationReuslt> searchResult = SearchFacade.spellcheck(searchParam,new GdsTranslator(EcpFrameContextHolder.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));

        System.err.println("--------------------------------------");
        if(searchResult.getResultList()!=null){
            for(CollationReuslt s:searchResult.getResultList()){
                System.out.println(s.getCollationQueryString()+":"+s.getNumberOfHits());
            }
        }
        System.err.println("--------------------------------------");
    }
    
    @Test
    public void suggestTest() {
        SearchParam searchParam = new SearchParam();

        // 前店必须显式指定搜索集合名称
        searchParam.setCurrentSiteId(0l);
        
        searchParam=setKeyWord(searchParam);
        
//        SearchResult<String> searchResult = SearchFacade.suggest1(searchParam); 
//        System.err.println("--------------------------------------");
//        if(searchResult.getResultList()!=null){
//            for(String s:searchResult.getResultList()){
//                System.err.println(s);
//            }
//        }
//        System.err.println("--------------------------------------");
        
        SearchResult<CollationReuslt> searchResult = SearchFacade.suggest2(searchParam,new GdsTranslator(EcpFrameContextHolder.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));
        
        System.out.println(searchResult);

    }

    @Test
    public void quggestTest() {
        SearchParam searchParam = new SearchParam();

        // 前店必须显式指定搜索集合名称
        searchParam.setCurrentSiteId(1l);
        
        searchParam=setKeyWord(searchParam);
        
//        SearchResult<String> searchResult = SearchFacade.suggest1(searchParam); 
//        System.err.println("--------------------------------------");
//        if(searchResult.getResultList()!=null){
//            for(String s:searchResult.getResultList()){
//                System.err.println(s);
//            }
//        }
//        System.err.println("--------------------------------------");
        
        SearchResult<String> searchResult = SearchFacade.suggest1(searchParam,new GdsTranslator(EcpFrameContextHolder.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));
        
        System.out.println(searchResult);

    }
    
    @Test
    public void searchTest() {
        SearchParam searchParam = new SearchParam();

        // 前店必须显式指定搜索集合名称
        searchParam.setCurrentSiteId(1l);
        
        //DateFacet
//        List<DateFacetField> dataFacetFieldList=new ArrayList<DateFacetField>();
//        DateFacetField dateFacetField=new DateFacetField();
//        dateFacetField.setField("testdatarange");
//        Date startDate=new Date();
//        int newHours=startDate.getHours()-5;
//        startDate.setHours(newHours);
//        dateFacetField.setStart(startDate);
//        Date endDate=new Date();
//        dateFacetField.setEnd(endDate);
//        dateFacetField.setGap("+1HOUR");
//        dataFacetFieldList.add(dateFacetField);
//        searchParam.setDataFacetFieldList(dataFacetFieldList);
        
        //QueryFacet
//        List<QueryFacetField> queryFacetFieldList=new ArrayList<QueryFacetField>();
//        QueryFacetField queryFacetField=new QueryFacetField();
//        queryFacetField.setField("guidePrice");
//        queryFacetField.setStart(13+"");
//        queryFacetField.setEnd(19+"");
//        queryFacetFieldList.add(queryFacetField);
//        
//        queryFacetField=new QueryFacetField();
//        queryFacetField.setField("guidePrice");
//        queryFacetField.setStart(13+"");
//        queryFacetField.setEnd(33+"");
//        queryFacetFieldList.add(queryFacetField);
//        
//        queryFacetField=new QueryFacetField();
//        queryFacetField.setField("guidePrice");
//        queryFacetField.setStart(1123+"");
//        queryFacetField.setEnd(2122+"");
//        queryFacetFieldList.add(queryFacetField);
//        
//        queryFacetField=new QueryFacetField();
//        queryFacetField.setField("goodMainPic");
//        queryFacetField.setStart(2004+"");
//        queryFacetField.setEnd(2008+"");
//        queryFacetFieldList.add(queryFacetField);
//        searchParam.setQueryFacetFieldList(queryFacetFieldList);
//        
//        queryFacetField=new QueryFacetField();
//        queryFacetField.setField("goodMainPic");
//        queryFacetField.setStart(2004+"");
//        queryFacetFieldList.add(queryFacetField);
//        searchParam.setQueryFacetFieldList(queryFacetFieldList);
        
        //范围查询
        //需要注意的是，范围查询会影响*Facet统计结果。
//        List<RangeQueryField> rangeQueryFieldList=new ArrayList<RangeQueryField>();
//        RangeQueryField rangeQueryField=new RangeQueryField();
//        rangeQueryField.setField("goodMainPic");
//        rangeQueryField.setStart(2004+"");
//        rangeQueryField.setEnd(2023+"");
//        rangeQueryFieldList.add(rangeQueryField);
//        searchParam.setRangeQueryFieldList(rangeQueryFieldList);
        
        //FieldFacet
//        List<String> fieldFacetFieldList=new ArrayList<String>();
//        fieldFacetFieldList.add("topCategoryCode");
//        searchParam.setFieldFacetFieldList(fieldFacetFieldList);
        
//        searchParam.setKeyword("内科学");
        searchParam.setKeyword("*");
        
        //URL重定向测试
//        searchParam.setKeyword("百度");//全匹配
//        searchParam.setKeyword("我是淘宝");//包含匹配
        
        //搜索关键字重定向测试
//        searchParam.setKeyword("小米");//全匹配
//        searchParam.setKeyword("我是韩国");//包含匹配
        

        //字段排序设置
//        List<SortField> sortFieldList=new ArrayList<SortField>();
        //默认需要
//        sortFieldList.add(new SortField("gdsNameBoost", ESort.ASC));
//        sortFieldList.add(new SortField("sales", ESort.ASC));
//        sortFieldList.add(new SortField("gdsNameBoost", ESort.DESC));
//        sortFieldList.add(new SortField("newProductTime", ESort.DESC));
//        searchParam.setSortFieldList(sortFieldList);
//        searchParam.setIfSortByScore(true, 0);
        
        searchParam.setPageNo(1);
        
        //额外查询条件
//        ExtraQueryInfo extraQueryInfo=new ExtraQueryInfo();
//        List<QueryFilter> filterList=new ArrayList<QueryFilter> ();
        
        //分类查询 
//        extraQueryInfo.setCategoryCode("112");
//        filterList.add(new GoodCategoriesQueryFilter());
        
        //属性查询
//        List<QueryProperty> queryPropertyList=new ArrayList<QueryProperty>();
//        
//        QueryProperty queryProperty1=new QueryProperty();
//        queryProperty1.setPropertyCode("2");
//        List<String> propertyValueCodeList1=new ArrayList<String>();
//        propertyValueCodeList1.add("7");
//        propertyValueCodeList1.add("8");
//        queryProperty1.setPropertyValueCodeList(propertyValueCodeList1);
//        queryPropertyList.add(queryProperty1);
//        
//        QueryProperty queryProperty2=new QueryProperty();
//        queryProperty2.setPropertyCode("1");
//        List<String> propertyValueCodeList2=new ArrayList<String>();
//        propertyValueCodeList2.add("1");
//        propertyValueCodeList2.add("2");
//        queryProperty2.setPropertyValueCodeList(propertyValueCodeList2);
//        queryPropertyList.add(queryProperty2);
//        
//        
//        extraQueryInfo.setQueryPropertyList(queryPropertyList);
//        filterList.add(new GoodPropertyQueryFilter());
        
        //在结果中搜
//        List<String> extraKeywordList=new ArrayList<String>();
//        extraKeywordList.add("孙悦");
//        extraKeywordList.add("翟晓川");
//        searchParam.setExtraKeywordList(extraKeywordList);
        
        //在结果中过滤
//        List<String> exceptKeywordList=new ArrayList<String>();
//        exceptKeywordList.add("黄端锋");
//        exceptKeywordList.add("林文彬");
//        searchParam.setExceptKeywordList(exceptKeywordList);
        
        SearchResult<Map<String, Object>> searchResult = SearchFacade.search(searchParam,null,null, new GdsTranslator(EcpFrameContextHolder.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class)));

        System.out.println(searchResult);
        
        if (searchResult.isSuccess()) {
            if(searchResult.isUrlResult()){
                System.out.println("URL重定向结果："+searchResult.getRedirectUrl());
            }else{
                if (searchResult.getResultList() == null || searchResult.getResultList().size() == 0) {
                    System.out.println("查询结果为空！");
                } else {

                    for (Map<String, Object> result : searchResult.getResultList()) {
                        System.out.println(result);
                    }
                }
            }
        } else {
            System.out.println("查询出现异常：" + searchResult.getMessage());
        }
    }
    
    @Test
    public void searchTest2() {
    	SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(1l);
        searchParam.setKeyword("603232");
        searchParam.setIfFuzzyQuery(true);
        searchParam.setPageNo(1);
        searchParam.setPageSize(1000);
        
        SearchResult<Map<String,Object>> result=SearchFacade.search(searchParam,null,null,new GdsTranslator(EcpFrameContextHolder.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));
        System.out.println(result);
    }
    
    @Test
    public void concurrentQueryTest() {

        //静态缓存初始化
        SearchCacheUtils.init();

        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Long startTime=System.currentTimeMillis();
        final AtomicLong atomicLongQTime=new AtomicLong();
        final AtomicLong atomicLongSearchFacade=new AtomicLong();
        int reqs=10;
        for(int i=1;i<=reqs;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Long startTime=System.currentTimeMillis();
                    SearchParam searchParam=new SearchParam();
                    searchParam.setCollectionName("gdscollection");
                    searchParam.setKeyword("国际");
                    searchParam.setPageNo(1);
                    searchParam.setPageSize(20);

                    /*List<MulValueExtraFieldQueryField> mulValueExtraFieldQueryFieldList=new ArrayList<MulValueExtraFieldQueryField>();

                    MulValueExtraFieldQueryField mulValueExtraFieldQueryField=new MulValueExtraFieldQueryField();
                    mulValueExtraFieldQueryField.setName("areas");
                    List<String> values=new ArrayList<String>();
                    values.add("北京");
                    values.add("中国");
                    values.add("法国");
                    mulValueExtraFieldQueryField.setValue(values);
                    mulValueExtraFieldQueryFieldList.add(mulValueExtraFieldQueryField);

                    mulValueExtraFieldQueryField=new MulValueExtraFieldQueryField();
                    mulValueExtraFieldQueryField.setName("source");
                    values=new ArrayList<String>();
                    values.add("信息部");
                    values.add("国际部");
                    mulValueExtraFieldQueryField.setValue(values);
                    mulValueExtraFieldQueryFieldList.add(mulValueExtraFieldQueryField);

                    mulValueExtraFieldQueryField=new MulValueExtraFieldQueryField();
                    mulValueExtraFieldQueryField.setName("ifShow");
                    values=new ArrayList<String>();
                    values.add("1");
                    values.add("3");
                    mulValueExtraFieldQueryField.setValue(values);
                    mulValueExtraFieldQueryFieldList.add(mulValueExtraFieldQueryField);

                    searchParam.setExtraANDMulValueFieldQueryList(mulValueExtraFieldQueryFieldList);*/

                    SearchResult<Map<String,Object>> searchResult=SearchFacade.search(searchParam,null,null,null);
                    System.out.println("共找到："+searchResult.getNumFound()+"条");
                    Long cost=System.currentTimeMillis()-startTime;
                    atomicLongSearchFacade.addAndGet(cost);
                    atomicLongQTime.addAndGet(searchResult.getqTime());
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long cost=System.currentTimeMillis()-startTime;
        Long average=cost/reqs;
        Long searchFacadeAverave=atomicLongSearchFacade.longValue()/reqs;
        Long qTimeAverave=atomicLongQTime.longValue()/reqs;
        System.out.println("===========================================SearchFacade并发耗时："+cost+"，平均每次请求耗时："+average+"==============================================");
        System.out.println("===========================================SearchFacade总共耗时："+atomicLongSearchFacade.longValue()+"，平均每次请求耗时："+searchFacadeAverave+"==============================================");
        System.out.println("===========================================QTime总共耗时："+atomicLongQTime.longValue()+"，平均每次请求耗时："+qTimeAverave+"==============================================");

       // ===========================================SearchFacade并发耗时：59484，平均每次请求耗时：19==============================================
        //===========================================SearchFacade总共耗时：593827，平均每次请求耗时：197==============================================
        //===========================================QTime总共耗时：3773，平均每次请求耗时：1==============================================

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=1;i<=5;i++){
            SearchParam searchParam=new SearchParam();
            searchParam.setCollectionName("gdscollection");
            searchParam.setKeyword("国际");
            searchParam.setPageNo(1);
            searchParam.setPageSize(20);
            SearchResult<Map<String,Object>> searchResult=SearchFacade.search(searchParam,null,null,null);
            System.out.println("共找到："+searchResult.getNumFound()+"条");
        }

    }

    @Test
    public void searchTestMulLan() {
        SearchCacheUtils.clean();
        SearchParam searchParam = new SearchParam();
        searchParam.setCollectionName("gdscollection");
        searchParam.setPageNo(1);
        searchParam.setPageSize(10);

        List<String> kwList=new ArrayList<String>();
        kwList.add("中央气象台发布首个大雾红色预警");//textcn
        kwList.add("Fiscalía colombiana señala que accidente avión del Chapecoense fue un homicidio culposo");//textes
        kwList.add("Ali Bongo : Nous invitons les entreprises chinoises à venir investir massivement dans notre pays le Gabon");//textfr
        kwList.add("Apple будет инвестировать в китайскую ветроэнергетику");//textru
        kwList.add("مقتل 5 من الشرطة اليمنية بانفجار لغم جنوبي البلاد");//textar

        for(String kw:kwList){
            searchParam.setKeyword(kw);
            SearchResult<Map<String,Object>> result=SearchFacade.search(searchParam,null,null,null);
            System.out.println(result);
        }
    }
    
    @Test
    public void searchTest3() {
        SearchCacheUtils.clean();
        SearchParam searchParam = new SearchParam();
        searchParam.setCollectionName("gdscollection");
        //searchParam.setKeyword("习近平");
        searchParam.setKeyword("США продолжат предоставление Афганис");

        //searchParam.setDemoteTimeField("publishDate");

        //searchParam.setBf("dateValueSourceParser(${publishDate})");

        //searchParam.setBoost("recip(ms(NOW/HOUR,${publishDate}),3.16e-9,1,1)");

        searchParam.setPageNo(1);
        searchParam.setPageSize(10);

        //searchParam.setId("201478");
        /*List<String> extra=new ArrayList<>();
        extra.add("毛毛");
        searchParam.setExtraKeywordList(extra);*/

        /*List<SortField> sortFieldList=new ArrayList<>();
        sortFieldList.add(new SortField("publishDate", ESort.ASC));
        sortFieldList.add(new SortField("publishDate", ESort.ASC));
        sortFieldList.add(new SortField("publishDate", ESort.ASC));
        searchParam.setSortFieldList(sortFieldList);
        searchParam.setIfSortByScore(true,sortFieldList.size()+1);*/

        //searchParam.setLang(ELang.english);

        SearchResult<Map<String,Object>> result=SearchFacade.search(searchParam,null,null,null);
        for(Map<String,Object> map:result.getResultList()){
            System.out.println(map.get("id")+"==="+ map.get("gdsName"));
        }
        System.out.println(result);
    }

    @Test
    public void suggestTest3() {
        SearchParam searchParam = new SearchParam();
        searchParam.setCollectionName("gdscollection");
        searchParam.setKeyword("*");
        searchParam.setPageNo(1);
        SearchResult<String> result=SearchFacade.suggest1(searchParam);
        System.out.println(result);
    }

    @Test
    public void clusteringTest() {
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(1l);
        searchParam.setKeyword("*");
        searchParam.setPageNo(1);
        searchParam.setPageSize(1000);
        
        SearchResult<ClusteringReuslt> result=SearchFacade.clustering(searchParam,new GdsTranslator(EcpFrameContextHolder.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));
        System.out.println(result);
    }

}
