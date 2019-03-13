package com.zengshi.ecp.busi.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.velocity.AiNumberUtil;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.search.vo.ExtraRespVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.ScrollResult;
import com.zengshi.ecp.busi.shop.vo.GoodSearchResult;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.RangeQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.UncertainField;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryCategory;
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
import com.zengshi.paas.utils.ImageUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by HDF on 2016/6/28.
 */
public class SearchUtil {

    public final static int DEFAULT_PAGESIZE=10;
    private final static String SUFFIX_IMAGE_SIZE = "_220x220!";
    public final static String FIELD_DISCOUNTPRICE="discountPriceOfLevel";
    public final static String FIELD_DISCOUNT="discountOfLevel";
    public final static String ROPERTYCODE_DEFAULTPRICE = "priceCode";
    public static final String FIELD_CATEGORYPATH = "categoryPath";
    public static final String FIELD_CATEGORYPATH_SHOP = "shopCategoryPath";
    public final static AiNumberUtil util=new AiNumberUtil();

    private final static List<String> fields=new ArrayList<String>();

    static{
        fields.add("id");
        fields.add("firstSkuId");
        fields.add("promotionType");
        fields.add("mainPic");
        fields.add("imageUrl");
        fields.add("defaultPrice");
        fields.add("discountPrice");
        fields.add("discount");
        fields.add("gdsName");
        fields.add("gdsNameSrc");
        fields.add("gdsSubHead");
        fields.add("gdsSubHeadSrc");
        fields.add("sales");
        fields.add("guidePrice");
        fields.add("uncertainfield_discountInfo");
        fields.add("score");
        fields.add("scoreType");
    }

    private static List<Map<String,Object>> renderSearchResult(List<Map<String,Object>> list) {

        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String,Object> map : list) {

                // 根据会员等级获取对应的折扣价和折扣率
                String staffLevelCode = StaffLocaleUtil.getStaff().getStaffLevelCode();
                String priceFieldName = SearchUtil.FIELD_DISCOUNTPRICE + staffLevelCode;
                String discountFieldName = SearchUtil.FIELD_DISCOUNT + staffLevelCode;

                String uncertainfield_discountInfo="";
                if(map.containsKey("uncertainfield_discountInfo")){
                    uncertainfield_discountInfo=String.valueOf(map.get("uncertainfield_discountInfo"));
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(uncertainfield_discountInfo)) {
                    List<UncertainField> uncertainFieldList = JSONArray.parseArray(uncertainfield_discountInfo, UncertainField.class);
                    if (CollectionUtils.isNotEmpty(uncertainFieldList)) {
                        for (UncertainField ucertainField : uncertainFieldList) {
                            if (org.apache.commons.lang3.StringUtils.equals(ucertainField.getName(), priceFieldName)) {
                                if (CollectionUtils.isNotEmpty(ucertainField.getValue())) {
                                    map.put("discountPrice",ucertainField.getValue().get(0));
                                }
                            } else if (org.apache.commons.lang3.StringUtils.equals(ucertainField.getName(), discountFieldName)) {
                                if (CollectionUtils.isNotEmpty(ucertainField.getValue())) {
                                    map.put("discount",ucertainField.getValue().get(0));
                                }
                            }
                        }
                    }
                }

                long discountPrice=0;
                /*if(map.containsKey("discountPrice")){
                    discountPrice=Long.parseLong(String.valueOf(map.get("discountPrice")));
                }*/
                if (discountPrice == 0) {
                    map.put("discountPrice",map.get("defaultPrice"));
                }
                map.put("defaultPriceOfYun",util.showMoneyByTwoDecimal(String.valueOf(map.get("defaultPrice"))));
                map.put("discountPriceOfYun",util.showMoneyByTwoDecimal(String.valueOf(map.get("discountPrice"))));

                // 生成图片路径
                map.put("imageUrl",ImageUtil.getImageUrl(map.get("mainPic") + SUFFIX_IMAGE_SIZE));

            }

        }

        return list;

    }

    public static EcpBasePageRespVO<Map<String,Object>> renderRespVO(EcpBasePageReqVO reqvo,
                                                      SearchResult<Map<String,Object>> result) throws BusinessException {

        EcpBasePageRespVO<Map<String,Object>> vo = new EcpBasePageRespVO<Map<String,Object>>();
        vo.setPageNumber(reqvo.getPageNumber());
        vo.setPageSize(reqvo.getPageSize());
        vo.setTotalPage(result.getTotallyPage());
        vo.setTotalRow(result.getNumFound());
        vo.setList(SearchUtil.renderSearchResult(result.getResultList()));

        return vo;
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
    public static EcpBasePageRespVO<Map> renderRespVOs(EcpBasePageReqVO vo,
            SearchResult<GoodSearchResult> result) throws BusinessException {
        PageResponseDTO<GoodSearchResult> t = new PageResponseDTO<GoodSearchResult>();
        t.setResult(SearchUtil.renderSearchResults(result.getResultList()));
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
    private static List<GoodSearchResult> renderSearchResults(List<GoodSearchResult> goodSearchResultVOList) {

        if (CollectionUtils.isNotEmpty(goodSearchResultVOList)) {
            for (GoodSearchResult goodSearchResultVO : goodSearchResultVOList) {
                goodSearchResultVO.render();

                List<String> typeList = new ArrayList<String>();
                goodSearchResultVO.setPromotionType(typeList);
            }

        }

        return goodSearchResultVOList;

    }
    public static ScrollResult renderRespVO(SearchResult<Map<String,Object>> result) throws BusinessException {

        ScrollResult scrollResult=new ScrollResult();
        scrollResult.setTotal(result.getTotallyPage());
        scrollResult.setDatas(SearchUtil.renderSearchResult(result.getResultList()));

        return scrollResult;
    }

    public static SearchResult<Map<String,Object>> searchGood(GoodSearchPageReqVO vo) throws BusinessException {

        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());

        searchParam.setKeyword(vo.getKeyword());

        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());

        List<ExtraFieldQueryField> extraFieldQueryList=new ArrayList<ExtraFieldQueryField>();

        // 根据商品类型菜单过滤(实物商品、虚拟商品)
        if(StringUtils.isNotBlank(vo.getGdsTypeId())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("gdsTypeId");
            extraFieldQueryField.setValue(vo.getGdsTypeId());
            extraFieldQueryList.add(extraFieldQueryField);
        }else{
            searchParam = addFieldFilterSupport(searchParam, vo);
          
        }
        
        //积分商品
        ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
        extraFieldQueryField.setName("ifScoreGds");
        extraFieldQueryField.setValue("1");
        extraFieldQueryList.add(extraFieldQueryField);
        searchParam.setExtraANDFieldQueryList(extraFieldQueryList); 
        // 分类搜索
        ExtraRespVO extraRespVO = new ExtraRespVO();
        ExtraQueryInfo extraQueryInfo = new ExtraQueryInfo();
        List<QueryFilter> filterList = new ArrayList<QueryFilter>();
        if (StringUtils.isNotBlank(vo.getCategory())) {
            extraRespVO.setSearchCategory(vo.getCategory());
            List<QueryCategory> queryCategoryList=new ArrayList<QueryCategory>();
            queryCategoryList.add(new QueryCategory(Arrays.asList(vo.getCategory().split(SearchConstants.COMMA)), ExtraQueryInfo.FIELD_CATEGORIES));
            extraQueryInfo.setQueryCategoryList(queryCategoryList);
            filterList.add(new GoodCategoriesQueryFilter());
        }
        // 查询综合评分和排序下的查询列表
        SearchResult<Map<String,Object>> result = SearchFacade.search(fields,searchParam, extraQueryInfo, filterList,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class)));
        return result;
    }

    private static SearchParam addFieldFilterSupport(SearchParam searchParam, GoodSearchPageReqVO vo) {
        
        List<SortField> sortFieldList=new ArrayList<SortField>();
        
        // 可能为空
        if (StringUtils.isNotBlank(vo.getField())) {

            // 排序字段校验
            if (StringUtils.equals("score", vo.getField())
                    || StringUtils.equals("updateTime", vo.getField())
                    || StringUtils.equals("gdsTypeId", vo.getField())) {

                // 积分字段才支持范围查询
                if (StringUtils.isNotBlank(vo.getRangeType())
                        && StringUtils.equals(vo.getField(), "score")) {
                    List<RangeQueryField> rangeQueryFieldList = new ArrayList<RangeQueryField>();
                    RangeQueryField rangeQueryField = new RangeQueryField();
                    rangeQueryField.setName("score");

                    String start = "";
                    String end = "";
                    if (StringUtils.equals("1", vo.getRangeType())) {
                        start = "0";
                        end = "4999";
                    } else if (StringUtils.equals("2", vo.getRangeType())) {
                        start = "5000";
                        end = "9999";
                    } else if (StringUtils.equals("3", vo.getRangeType())) {
                        start = "10000";
                        end = "14999";
                    } else if (StringUtils.equals("4", vo.getRangeType())) {
                        start = "15000";
                        end = "*";
                    }

                    if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
                        rangeQueryField.setStart(start);
                        rangeQueryField.setEnd(end);
                        rangeQueryFieldList.add(rangeQueryField);
                        searchParam.setRangeQueryFieldList(rangeQueryFieldList);
                    }

                }
                
                ESort eSort = ESort.getAndValidSort(vo.getSort());
                if (null != eSort) {
                    sortFieldList.add(new SortField(vo.getField(), eSort));
                }

            }
            
            //自定义排序，无需加入评分排序和相关度排序
            
        }else{
            
            if(!StringUtils.isBlank(searchParam.getKeyword())&&!StringUtils.equals(SearchConstants.STAR, searchParam.getKeyword())){

                //无自定义排序，需要加入评分排序和相关度排序
                searchParam.setIfSortByScore(true);
                sortFieldList.add(new SortField("gdsNameBoost", ESort.ASC));
            }
            
            //默认按更新日期降序
            sortFieldList.add(new SortField("updateTime", ESort.DESC));
        }
        
        searchParam.setSortFieldList(sortFieldList);

        return searchParam;
    }

    private static ExtraQueryInfo addPropertyFilterSupport(SearchParam searchParam,
                                                           ExtraQueryInfo extraQueryInfo, GoodSearchPageReqVO vo) {

        if (StringUtils.isBlank(vo.getPropertyGroup())) {
            return extraQueryInfo;
        }

        JSONArray jsonArray = JSONArray.parseArray(vo.getPropertyGroup());
        int length = jsonArray.size();

        if (length > 0) {

            List<ExtraQueryInfo.QueryProperty> queryPropertyList = new ArrayList<ExtraQueryInfo.QueryProperty>();
            ExtraQueryInfo.QueryProperty queryProperty ;
            List<String> propertyValueCodeList;
            String propertyValueIdArr[] ;
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
                            rangeQueryField.setName(FIELD_DISCOUNTPRICE+ StaffLocaleUtil.getStaff().getStaffLevelCode());
                            rangeQueryField.setStart(priceStart);
                            rangeQueryField.setEnd(priceEnd);
                            rangeQueryFieldList.add(rangeQueryField);
                            searchParam.setRangeQueryFieldList(rangeQueryFieldList);
                        }

                    }

                } else {
                    queryProperty = new ExtraQueryInfo.QueryProperty();
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

        return extraQueryInfo;

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
        
        return renderGdsSearchResult(result.getResultList());
        
    }
    
    private static List<GoodSearchResult> renderGdsSearchResult(List<GoodSearchResult> goodSearchResultVOList) {

        if (CollectionUtils.isNotEmpty(goodSearchResultVOList)) {
            for (GoodSearchResult goodSearchResultVO : goodSearchResultVOList) {
                goodSearchResultVO.render();

                List<String> typeList = new ArrayList<String>();
                goodSearchResultVO.setPromotionType(typeList);
            }

        }

        return goodSearchResultVOList;

    }
    private static SearchParam addFieldSortSupport(SearchParam searchParam, GoodSearchPageReqVO vo) {

        List<SortField> sortFieldList=new ArrayList<SortField>();

        // 自定义排序
        if (StringUtils.isNotBlank(vo.getField())) {

            // 排序字段校验
            if (StringUtils.equals("sales", vo.getField())
                    || StringUtils.equals("discountPrice", vo.getField()) 
                    || StringUtils.equals("updateTime", vo.getField())){
                ESort eSort = ESort.getAndValidSort(vo.getSort());
                if (null != eSort) {
                    if(StringUtils.equals("discountPrice", vo.getField())){
                        sortFieldList.add(new SortField(FIELD_DISCOUNTPRICE+StaffLocaleUtil.getStaff().getStaffLevelCode(), eSort));
                    }else if(StringUtils.equals("sales", vo.getField())){
                        sortFieldList.add(new SortField(vo.getField(), eSort));
                    }else{
                        sortFieldList.add(new SortField(vo.getField(), eSort));
                    }
                }
            }

            //自定义排序，无需加入评分排序和相关度排序

        }else{

            if(!StringUtils.isBlank(searchParam.getKeyword())&&!StringUtils.equals(SearchConstants.STAR, searchParam.getKeyword())){
                //无自定义排序，需要加入评分排序和相关度排序
//                searchParam.setIfSortByScore(true, 1);
                sortFieldList.add(new SortField("gdsNameBoost", ESort.ASC));
            }

        }

        searchParam.setSortFieldList(sortFieldList);

        return searchParam;

    }
    /**
     * 执行搜索请求
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public static SearchResult<GoodSearchResult> searchGoods(GoodSearchPageReqVO vo) throws BusinessException {
        
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
            queryFacetFieldList.add(FIELD_CATEGORYPATH_SHOP);
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
}
