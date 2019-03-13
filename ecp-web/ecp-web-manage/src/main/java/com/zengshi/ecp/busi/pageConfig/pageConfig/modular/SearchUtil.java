package com.zengshi.ecp.busi.pageConfig.pageConfig.modular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.GoodSearchResult;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.RangeQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryCategory;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryProperty;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.good.GoodCategoriesQueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.good.GoodPropertyQueryFilter;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SearchUtil {
    
    public static final String FIELD_CATEGORYPATH = "categoryPath";
    
    public final static String FIELD_DISCOUNTPRICE="discountPriceOfLevel";
    
    public final static String FIELD_DISCOUNT="discountOfLevel";

    public final static String ROPERTYCODE_DEFAULTPRICE = "priceCode";
    
    /**
     * 平台热卖商品榜单（不分页）
     * @param count 获取热卖商品榜单商品数
     * @return
     */
    public static List<GoodSearchResult> platformHotSales(int count){
        return shopHotSales(count,-1);
    }
    
    /**
     * 店铺热卖商品榜单（不分页）
     * @param count 获取热卖商品榜单商品数
     * @return
     */
    public static List<GoodSearchResult> shopHotSales(int count,long shopId){
        
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        
        searchParam.setPageNo(1);
        searchParam.setPageSize(count);
        
        if(shopId!=-1){
            List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(shopId+"");
            extraANDFieldQueryList.add(extraFieldQueryField);
            searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        }
        
        List<SortField> sortFieldList=new ArrayList<SortField>();
        sortFieldList.add(new SortField("sales", ESort.DESC));
        searchParam.setSortFieldList(sortFieldList);
        
        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class))); 
        
        return renderSearchResult(result.getResultList());
        
    }
    
    /**
     * 平台热卖商品（分页）
     * @param vo 分页请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static EcpBasePageRespVO<Map> platformHotSales(EcpBasePageReqVO vo){
        return shopHotSales(vo,-1);
    }
    
    /**
     * 店铺热卖商品（分页）
     * @param vo 分页请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static EcpBasePageRespVO<Map> shopHotSales(EcpBasePageReqVO vo,long shopId){
        
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());
        
        if(shopId!=-1){
            List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(shopId+"");
            extraANDFieldQueryList.add(extraFieldQueryField);
            searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        }
        
        List<SortField> sortFieldList=new ArrayList<SortField>();
        sortFieldList.add(new SortField("sales", ESort.DESC));
        searchParam.setSortFieldList(sortFieldList);
        
        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class))); 
        
        return renderRespVO(vo, result);
    }
    
    
    /**
     * 店铺新上架商品（分页）
     * @param vo 分页请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static EcpBasePageRespVO<Map> shopNewSales(EcpBasePageReqVO vo,long shopId){
        
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());
        
        if(shopId!=-1){
            List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(shopId+"");
            extraANDFieldQueryList.add(extraFieldQueryField);
            searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        }
        
        List<SortField> sortFieldList=new ArrayList<SortField>();
        sortFieldList.add(new SortField("updateTime", ESort.DESC));
        searchParam.setSortFieldList(sortFieldList);
        
        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class))); 
        
        return renderRespVO(vo, result);
    }
    
    
    
    /**
     * 执行搜索请求
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public static SearchResult<GoodSearchResult> searchGood(GoodSearchPageReqVO vo) throws BusinessException {
        
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());

        searchParam.setKeyword(vo.getKeyword());
        
        searchParam.setIfRetDocList(vo.isRetResult());

        // 分类搜索
        ExtraQueryInfo extraQueryInfo = new ExtraQueryInfo();
        List<QueryFilter> filterList = new ArrayList<QueryFilter>();
        if (StringUtils.isNotBlank(vo.getCategory())) {
            // 不管分类是否在管理平台变更了，都进行搜索
            List<QueryCategory> queryCategoryList=new ArrayList<QueryCategory>();
            queryCategoryList.add(new QueryCategory(Arrays.asList(vo.getCategory().split(SearchConstants.COMMA)), ExtraQueryInfo.FIELD_CATEGORIES));
            extraQueryInfo.setQueryCategoryList(queryCategoryList);
            filterList.add(new GoodCategoriesQueryFilter());
        }

        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());
        
        if(StringUtils.isNotBlank(vo.getShopId())){
            List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(vo.getShopId());
            extraANDFieldQueryList.add(extraFieldQueryField);
            searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        }
        
        // 属性过滤
        // 可能为空
        extraQueryInfo = addPropertyFilterSupport(searchParam, extraQueryInfo, vo);
        if (CollectionUtils.isNotEmpty(extraQueryInfo.getQueryPropertyList())) {
            filterList.add(new GoodPropertyQueryFilter());
        }

        searchParam = addFieldSortSupport(searchParam, vo);

        // Facet参数设置
        if(vo.isCategoryPathFacet()){
            List<String> queryFacetFieldList = new ArrayList<String>();
            queryFacetFieldList.add(FIELD_CATEGORYPATH);
            searchParam.setFieldFacetFieldList(queryFacetFieldList);
        }
        
        // 在结果中搜
        // 可能为空
        if (StringUtils.isNotBlank(vo.getSearchmore())) {
            List<String> extraKeywordList = new ArrayList<String>();
            extraKeywordList.add(vo.getSearchmore());
            searchParam.setExtraKeywordList(extraKeywordList);
        }

        // 查询综合评分和排序下的查询列表
        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, extraQueryInfo, filterList,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class))); 
        return result;
    }
    
    /**
     * 字段处理过滤
     * 
     * @param searchParam
     * @param vo
     * @return
     */
    private static SearchParam addFieldSortSupport(SearchParam searchParam, GoodSearchPageReqVO vo) {
        
        List<SortField> sortFieldList=new ArrayList<SortField>();
        
        // 自定义排序
        if (StringUtils.isNotBlank(vo.getField())) {
            
            // 排序字段校验
            if (StringUtils.equals("sales", vo.getField())
                    || StringUtils.equals("discountPrice", vo.getField())
                    || StringUtils.equals("newProductTime", vo.getField())) {
                ESort eSort = ESort.getAndValidSort(vo.getSort());
                if (null != eSort) {
                    if(StringUtils.equals("discountPrice", vo.getField())){
                        sortFieldList.add(new SortField(FIELD_DISCOUNTPRICE+StaffLocaleUtil.getStaff().getStaffLevelCode(), eSort));
                    }else{
                        sortFieldList.add(new SortField(vo.getField(), eSort));
                    }
                }
            }
            
            //自定义排序，无需加入评分排序和相关度排序

        }else{
            
            //无自定义排序，则第一排序字段为商品类型，目的是纸质书拍前面
            sortFieldList.add(new SortField("gdsTypeId", ESort.ASC));
            
            if(!StringUtils.isBlank(searchParam.getKeyword())&&!StringUtils.equals(SearchConstants.STAR, searchParam.getKeyword())){
                //无自定义排序，需要加入评分排序和相关度排序
//                searchParam.setIfSortByScore(true, 1);
                sortFieldList.add(new SortField("gdsNameBoost", ESort.ASC));
            }
            
            //默认按出版日期降序
            sortFieldList.add(new SortField("newProductTime", ESort.DESC));
        }
        
        searchParam.setSortFieldList(sortFieldList);

        return searchParam;

    }
    
    /**
     * 属性过滤
     * 
     * @param extraQueryInfo
     * @param propertyGroup
     * @return
     */
    private static ExtraQueryInfo addPropertyFilterSupport(SearchParam searchParam,
            ExtraQueryInfo extraQueryInfo, GoodSearchPageReqVO vo) {

        if (StringUtils.isBlank(vo.getPropertyGroup())) {
            return extraQueryInfo;
        }

        JSONArray jsonArray = JSONArray.parseArray(vo.getPropertyGroup());
        int length = jsonArray.size();

        if (length > 0) {

            List<QueryProperty> queryPropertyList = new ArrayList<QueryProperty>();
            QueryProperty queryProperty = null;
            List<String> propertyValueCodeList = null;
            String propertyValueIdArr[] = null;
            for (int i = 0; i < length; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                // 价格范围查询，范围值不由页面传值
                if (StringUtils.equals(jsonObj.getString("propertyId"), ROPERTYCODE_DEFAULTPRICE)) {

                    propertyValueIdArr = jsonObj.getString("propertyValueIds").split(
                            SearchConstants.COMMA);
                    if (propertyValueIdArr != null && propertyValueIdArr.length > 0) {
                        String type = propertyValueIdArr[0];
                        String priceStart = "";
                        String priceEnd = "";

                        if (StringUtils.equals("1", type)) {
                            priceStart = "0";
                            priceEnd = "8900";
                        } else if (StringUtils.equals("2", type)) {
                            priceStart = "9000";
                            priceEnd = "19900";
                        } else if (StringUtils.equals("3", type)) {
                            priceStart = "20000";
                            priceEnd = "29900";
                        } else if (StringUtils.equals("4", type)) {
                            priceStart = "30000";
                            priceEnd = "39900";
                        } else if (StringUtils.equals("5", type)) {
                            priceStart = "40000";
                            priceEnd = "*";
                        }

                        if (StringUtils.isNotBlank(priceStart)
                                && StringUtils.isNotBlank(priceEnd)) {

                            // 价格范围查询
                            List<RangeQueryField> rangeQueryFieldList = new ArrayList<RangeQueryField>();
                            RangeQueryField rangeQueryField = new RangeQueryField();
                            rangeQueryField.setName(FIELD_DISCOUNTPRICE+StaffLocaleUtil.getStaff().getStaffLevelCode());
                            rangeQueryField.setStart(priceStart);
                            rangeQueryField.setEnd(priceEnd);
                            rangeQueryFieldList.add(rangeQueryField);
                            searchParam.setRangeQueryFieldList(rangeQueryFieldList);
                        }

                    }

                } else {
                    queryProperty = new QueryProperty();
                    queryProperty.setPropertyCode(jsonObj.getString("propertyId"));

                    propertyValueCodeList = new ArrayList<String>();

                    propertyValueIdArr = jsonObj.getString("propertyValueIds").split(
                            SearchConstants.COMMA);
                    if (propertyValueIdArr != null && propertyValueIdArr.length > 0) {
                        for (String propertyValueId : propertyValueIdArr) {
                            propertyValueCodeList.add(propertyValueId);
                        }
                    }

                    queryProperty.setPropertyValueCodeList(propertyValueCodeList);
                    queryPropertyList.add(queryProperty);
                }

            }

            extraQueryInfo.setQueryPropertyList(queryPropertyList);

        }

        // 自定义范围查询
        // 以范围属性查询优先
        if (CollectionUtils.isEmpty(searchParam.getRangeQueryFieldList())) {
            if (StringUtils.isNotBlank(vo.getPriceStart())
                    && StringUtils.isNotBlank(vo.getPriceEnd())) {
                List<RangeQueryField> rangeQueryFieldList = new ArrayList<RangeQueryField>();
                RangeQueryField rangeQueryField = new RangeQueryField();
                rangeQueryField.setName(FIELD_DISCOUNTPRICE+StaffLocaleUtil.getStaff().getStaffLevelCode());
                try {
                    long start = Long.parseLong(vo.getPriceStart()) * 100;
                    long end = Long.parseLong(vo.getPriceEnd()) * 100;
                    rangeQueryField.setStart(start + "");
                    rangeQueryField.setEnd(end + "");
                    rangeQueryFieldList.add(rangeQueryField);
                    searchParam.setRangeQueryFieldList(rangeQueryFieldList);
                } catch (Exception e) {
                    // TODO 查询范围超出或格式异常
                }

            }
        }

        return extraQueryInfo;

    }
    
    /**
     * 分页数据封装
     * 
     * @param vo
     * @param result
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static EcpBasePageRespVO<Map> renderRespVO(EcpBasePageReqVO vo,
            SearchResult<GoodSearchResult> result) throws BusinessException {
        PageResponseDTO<GoodSearchResult> t = new PageResponseDTO<GoodSearchResult>();
        t.setResult(SearchUtil.renderSearchResult(result.getResultList()));
        t.setPageNo(vo.getPageNumber());
        t.setPageSize(vo.getPageSize());
        t.setCount(result.getNumFound());
        t.setPageCount(result.getTotallyPage());
        EcpBasePageRespVO<Map> respVO = null;
        try {
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        } catch (Exception e) {
            throw new BusinessException("EcpBasePageRespVO.buildByPageResponseDTO(t)执行异常！");
        }
        return respVO;
    }
    
    private static List<GoodSearchResult> renderSearchResult(List<GoodSearchResult> goodSearchResultVOList) {

        if (CollectionUtils.isNotEmpty(goodSearchResultVOList)) {
            for (GoodSearchResult goodSearchResultVO : goodSearchResultVOList) {
                goodSearchResultVO.render();

                List<String> typeList = new ArrayList<String>();
                goodSearchResultVO.setPromotionType(typeList);
            }

        }

        return goodSearchResultVOList;

    }

    
    /**
     * 分类收藏商品
     * @param vo 分页请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static EcpBasePageRespVO<Map> catgHotCollect(GoodSearchPageReqVO vo){
        
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());
        ExtraQueryInfo extraQueryInfo = new ExtraQueryInfo();
        List<QueryFilter> filterList = new ArrayList<QueryFilter>();
        if (StringUtils.isNotBlank(vo.getCategory())) {

            // 分类过滤
            // 可能为空
            List<QueryCategory> queryCategoryList=new ArrayList<QueryCategory>();
            queryCategoryList.add(new QueryCategory(Arrays.asList(vo.getCategory().split(SearchConstants.COMMA)), ExtraQueryInfo.FIELD_CATEGORIES));
            extraQueryInfo.setQueryCategoryList(queryCategoryList);
            filterList.add(new GoodCategoriesQueryFilter());

        }
        
        List<SortField> sortFieldList=new ArrayList<SortField>();
        sortFieldList.add(new SortField("collectCount", ESort.DESC));
        searchParam.setSortFieldList(sortFieldList);
        
        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class))); 
        
        return renderRespVO(vo, result);
    }
    
}

