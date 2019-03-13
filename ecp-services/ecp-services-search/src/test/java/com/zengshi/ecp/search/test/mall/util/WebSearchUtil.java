package com.zengshi.ecp.search.test.mall.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.search.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.good.GoodCategoriesQueryFilter;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.EOperator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.test.mall.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.search.test.mall.vo.GoodSearchPageRespVO;
import com.zengshi.ecp.search.test.mall.vo.GoodSearchResult;
import com.zengshi.ecp.search.test.mall.vo._EcpBasePageRespVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;

public class WebSearchUtil {

    private final static String FIELD_TAG = "tag";
    
    private final static String FIELD_KEYWORD = "keyword";

    private final static String FIELD_AREA = "area";

    private final static String FIELD_PEOPLE = "people";

    private final static String FIELD_SOURCE = "source";

    private final static String FIELD_GDSTYPEID = "gdsTypeId";
    
    private final static String FIELD_GDSTYPEIDANDNAME = "gdsTypeIdAndName";
    
    private final static String FIELD_WRITEDATE="writeDate";
    
    private final static String FIELD_TOPCATEGORYCODE="topCategoryCode";

    @SuppressWarnings("rawtypes")
    public static GoodSearchPageRespVO<Map> search(GoodSearchPageReqVO vo) throws BusinessException {

        SearchParam searchParam = new SearchParam();

        // 搜索站点设置
        searchParam.setCurrentSiteId(1l);

        // 分页参数设置
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());

        // 查询关键字设置
        if (StringUtils.isBlank(vo.getKeyword())) {
            searchParam.setKeyword(SearchConstants.STAR);
        } else {
            searchParam.setKeyword(vo.getKeyword());
        }
        
        searchParam.setId(vo.getId());

        // 分类过滤
        ExtraQueryInfo extraQueryInfo = null;
        List<QueryFilter> filterList = new ArrayList<QueryFilter>();
        if (CollectionUtils.isNotEmpty(vo.getQueryCategoryList())){
            extraQueryInfo = new ExtraQueryInfo();
            extraQueryInfo.setQueryCategoryList(vo.getQueryCategoryList());
            filterList.add(new GoodCategoriesQueryFilter());
        }

        // 在结果中搜
        if (StringUtils.isNotBlank(vo.getSearchmore())) {
            List<String> extraKeywordList = new ArrayList<String>();
            extraKeywordList.add(vo.getSearchmore());
            searchParam.setExtraKeywordList(extraKeywordList);
        }
        
        // 在结果中过滤
        if(StringUtils.isNotBlank(vo.getExceptKeyword())){
            searchParam.andExcept(vo.getExceptKeyword());
        }

        // 字段排序或字段字段单值过滤
        searchParam = addFieldQSupport(searchParam, vo);

        // Facet字段设置,无论是一次查询或多次查询都只进行统一的一次Facet
        if (vo.isIfFacet() && StringUtils.isNotBlank(vo.getFacetFields())) {
            String[] fields = vo.getFacetFields().split(SearchConstants.COMMA);
            if (fields != null && fields.length > 0) {
                List<String> queryFacetFieldList = new ArrayList<String>();
                for (String field : fields) {
                    if (StringUtils.equals(field, FIELD_KEYWORD)
                            ||StringUtils.equals(field, FIELD_AREA)
                            || StringUtils.equals(field, FIELD_PEOPLE)|| StringUtils.equals(field, FIELD_SOURCE)
                                    || StringUtils.equals(field, FIELD_GDSTYPEIDANDNAME)|| StringUtils.equals(field, FIELD_TOPCATEGORYCODE)
                                    || StringUtils.equals(field, FIELD_TAG)) {
                        queryFacetFieldList.add(field);
                        searchParam.setFieldFacetFieldList(queryFacetFieldList);
                    }
                }
            }
        }

        // 查询综合评分和排序下的查询列表
        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, extraQueryInfo, filterList,new GdsTranslator(EcpFrameContextHolder.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class)));
        if (result.isSuccess()) {
            
            System.out.println(result);
            
            if (result.isSuccess()) {
                if(result.isUrlResult()){
                    System.out.println("URL重定向结果："+result.getRedirectUrl());
                }else{
                    if (result.getResultList() == null || result.getResultList().size() == 0) {
                        System.out.println("查询结果为空！");
                    } else {

                        for (GoodSearchResult r : result.getResultList()) {
                            System.out.println(r);
                        }
                    }
                }
            } else {
                System.out.println("查询出现异常：" + result.getMessage());
            }
            
            
            
            _EcpBasePageRespVO<Map> ecpPageRespVO = renderRespVO(vo, result);
            GoodSearchPageRespVO<Map> goodPageRespVO = new GoodSearchPageRespVO<Map>();
            goodPageRespVO.setList(ecpPageRespVO.getList());
            goodPageRespVO.setPageNumber(ecpPageRespVO.getPageNumber());
            goodPageRespVO.setPageSize(ecpPageRespVO.getPageSize());
            goodPageRespVO.setTotalPage(ecpPageRespVO.getTotalPage());
            goodPageRespVO.setTotalRow(ecpPageRespVO.getTotalRow());

            // Facet结果解析
            if (vo.isIfFacet() && StringUtils.isNotBlank(vo.getFacetFields())) {
                String[] fields = vo.getFacetFields().split(SearchConstants.COMMA);
                if (fields != null && fields.length > 0) {
                    for (String field : fields) {
                        if (result.getFieldFacetResultMap().containsKey(field)) {
                            if (CollectionUtils.isNotEmpty(result.getFieldFacetResultMap()
                                    .get(field).getValue())) {

                                // 相关新闻Facet
                                if (StringUtils.equals(field, FIELD_KEYWORD)) {
                                    goodPageRespVO.setRelatedNewsCountList(result
                                            .getFieldFacetResultMap().get(field).getValue());
                                } else if (StringUtils.equals(field, FIELD_TAG)) {// “搜索“航母”的人还关注了”Facet
                                    goodPageRespVO.setRelatedKeywordsCountList(result
                                            .getFieldFacetResultMap().get(field).getValue());
                                } else if (StringUtils.equals(field, FIELD_AREA)) {// 地区Facet
                                    goodPageRespVO.setAreaCountList(result
                                            .getFieldFacetResultMap().get(field).getValue());
                                } else if (StringUtils.equals(field, FIELD_PEOPLE)) {// 人物Facet
                                    goodPageRespVO.setPeopleCountList(result
                                            .getFieldFacetResultMap().get(field).getValue());
                                }else if (StringUtils.equals(field, FIELD_SOURCE)) {// 来源Facet
                                    goodPageRespVO.setSourceCountList(result
                                            .getFieldFacetResultMap().get(field).getValue());
                                }else if (StringUtils.equals(field, FIELD_GDSTYPEIDANDNAME)) {// 类型Facet
                                    goodPageRespVO.setGdsTypeIdAndNameCountList(result
                                            .getFieldFacetResultMap().get(field).getValue());
                                }else if (StringUtils.equals(field, FIELD_TOPCATEGORYCODE)) {// 顶级分类编码Facet
                                    goodPageRespVO.setTopCategoryCodeCountList(result
                                            .getFieldFacetResultMap().get(field).getValue());
                                }
                            }
                        }
                    }
                }
            }

            return goodPageRespVO;
        }

        throw new BusinessException(result.getMessage());

    }

    @SuppressWarnings("deprecation")
    private static SearchParam addFieldQSupport(SearchParam searchParam, GoodSearchPageReqVO vo) {
        
        List<ExtraORFieldQueryGroup> extraORFieldQueryGroupList=new ArrayList<ExtraORFieldQueryGroup>();
        
        // 商品类型字段过滤
        if (StringUtils.isNotBlank(vo.getGdsTypeIds())) {
            String[] gdsTypeIdArr = vo.getGdsTypeIds().split(SearchConstants.COMMA);
            if (gdsTypeIdArr != null && gdsTypeIdArr.length > 0) {
                List<QueryField> extraORFieldQueryList = new ArrayList<QueryField>();
                for (int i = 0; i < gdsTypeIdArr.length; i++) {
                    ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
                    extraFieldQueryField.setName(FIELD_GDSTYPEID);
                    
                    if(gdsTypeIdArr[i].startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                        extraFieldQueryField.setValue(gdsTypeIdArr[i].substring(4));
                        extraFieldQueryField.setExcept(true);
                    }else{
                        extraFieldQueryField.setValue(gdsTypeIdArr[i]);
                    }
                    
                    extraORFieldQueryList.add(extraFieldQueryField);
                }
                extraORFieldQueryGroupList.add(new ExtraORFieldQueryGroup(extraORFieldQueryList));
            }
        }
        
        // 商品地区字段过滤
        if (StringUtils.isNotBlank(vo.getAreas())) {
            String[] areaArr = vo.getAreas().split(SearchConstants.COMMA);
            if (areaArr != null && areaArr.length > 0) {
                List<QueryField> extraORFieldQueryList = new ArrayList<QueryField>();
                for (int i = 0; i < areaArr.length; i++) {
                    ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
                    extraFieldQueryField.setName(FIELD_AREA);
                    
                    if(areaArr[i].startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                        extraFieldQueryField.setValue(areaArr[i].substring(4));
                        extraFieldQueryField.setExcept(true);
                    }else{
                        extraFieldQueryField.setValue(areaArr[i]);
                    }
                    
                    extraORFieldQueryList.add(extraFieldQueryField);
                }
                extraORFieldQueryGroupList.add(new ExtraORFieldQueryGroup(extraORFieldQueryList));
            }
        }
        
        // 商品来源字段过滤
        if (StringUtils.isNotBlank(vo.getSources())) {
            String[] sourceArr = vo.getSources().split(SearchConstants.COMMA);
            if (sourceArr != null && sourceArr.length > 0) {
                List<QueryField> extraORFieldQueryList = new ArrayList<QueryField>();
                for (int i = 0; i < sourceArr.length; i++) {
                    ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
                    extraFieldQueryField.setName(FIELD_SOURCE);
                    
                    if(sourceArr[i].startsWith(EOperator.NOT.getName()+SearchConstants.UNDERLINE)){
                        extraFieldQueryField.setValue(sourceArr[i].substring(4));
                        extraFieldQueryField.setExcept(true);
                    }else{
                        extraFieldQueryField.setValue(sourceArr[i]);
                    }
                    
                    extraORFieldQueryList.add(extraFieldQueryField);
                }
                extraORFieldQueryGroupList.add(new ExtraORFieldQueryGroup(extraORFieldQueryList));
            }
        }
        
        searchParam.setExtraORFieldQueryGroupList(extraORFieldQueryGroupList);
        
        // 时间段过滤
        if(StringUtils.isNotBlank(vo.getDateTimeRangeFlag())){
            String start = "";
            String end = "";
            Timestamp now =DateUtil.getSysDate();
            
            String nowStr = SearchUtils.format(now);
            
            if(StringUtils.equals(vo.getDateTimeRangeFlag(),"1")){//当天
                end = nowStr;
                now.setHours(0);
                now.setMinutes(0);
                now.setSeconds(0);
                now.setNanos(0);
                start = SearchUtils.format(now);
            }else if(StringUtils.equals(vo.getDateTimeRangeFlag(),"2")){//一周
                end = nowStr;
                now.setDate(now.getDate()-7);
                start = SearchUtils.format(now);
            }else if(StringUtils.equals(vo.getDateTimeRangeFlag(),"3")){//一个月
                end = nowStr;
                now.setMonth(now.getMonth()-1);
                start = SearchUtils.format(now);
            }else if(StringUtils.equals(vo.getDateTimeRangeFlag(),"4")){//三个月
                end = nowStr;
                now.setMonth(now.getMonth()-3);
                start = SearchUtils.format(now);
            }else if(StringUtils.equals(vo.getDateTimeRangeFlag(),"5")){//一年
                end =nowStr;
                now.setYear(now.getYear()-1);
                start = SearchUtils.format(now);
            }
            if(StringUtils.isNotBlank(start)&&StringUtils.isNotBlank(end)){
                List<RangeQueryField> rangeQueryFieldList = new ArrayList<RangeQueryField>();
                RangeQueryField rangeQueryField = new RangeQueryField();
                rangeQueryField.setName(FIELD_WRITEDATE);
                rangeQueryField.setStart(start);
                rangeQueryField.setEnd(end);
                rangeQueryFieldList.add(rangeQueryField);
                searchParam.setRangeQueryFieldList(rangeQueryFieldList);
            }
        }
        
        List<SortField> sortFieldList = new ArrayList<SortField>();
        boolean ifSort = false;

        if (StringUtils.isNotBlank(vo.getSortFields())) {
            String[] fieldsArr = vo.getSortFields().split(SearchConstants.COMMA);
            String[] fieldValuesArr = vo.getSortFieldsSort().split(SearchConstants.COMMA);

            if (fieldsArr != null && fieldValuesArr != null
                    && fieldsArr.length == fieldValuesArr.length) {
                List<ExtraFieldQueryField> extraFieldQueryList = new ArrayList<ExtraFieldQueryField>();
                for (int i = 0; i < fieldValuesArr.length; i++) {
                    String field = fieldsArr[i];
                    String fieldValue = fieldValuesArr[i];
                    
                    // 字段校验
                    ESort eSort = ESort.getAndValidSort(fieldValue);

                    // 排序
                    if (null != eSort) {
                        ifSort = true;
                        sortFieldList.add(new SortField(field, eSort));
                    } else {// 非属性字段过滤

                        ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
                        extraFieldQueryField.setName(field);
                        extraFieldQueryField.setValue(fieldValue);
                        extraFieldQueryList.add(extraFieldQueryField);
                    }
                }
                searchParam.setExtraANDFieldQueryList(extraFieldQueryList);
            }
        } else {
            if (!ifSort) {

                if (!StringUtils.equals(SearchConstants.STAR, searchParam.getKeyword())) {
                    // 无自定义排序，需要加入评分排序和相关度排序
                    searchParam.setIfSortByScore(true);
                    sortFieldList.add(new SortField("gdsNameBoost", ESort.ASC));
                }

                // 默认按更新日期降序
//                sortFieldList.add(new SortField(FIELD_WRITEDATE, ESort.DESC));
            }
        }

        searchParam.setSortFieldList(sortFieldList);

        return searchParam;

    }

    @SuppressWarnings("rawtypes")
    private static _EcpBasePageRespVO<Map> renderRespVO(GoodSearchPageReqVO vo,
            SearchResult<GoodSearchResult> result) throws BusinessException {
        PageResponseDTO<GoodSearchResult> t = new PageResponseDTO<GoodSearchResult>();
        t.setResult(renderSearchResult(result.getResultList()));
        t.setPageNo(vo.getPageNumber());
        t.setPageSize(vo.getPageSize());
        t.setCount(result.getNumFound());
        t.setPageCount(result.getTotallyPage());
        _EcpBasePageRespVO<Map> respVO = null;
        try {
            respVO = _EcpBasePageRespVO.buildByPageResponseDTO(t);
        } catch (Exception e) {
            throw new BusinessException("EcpBasePageRespVO.buildByPageResponseDTO(t)执行异常！");
        }
        return respVO;
    }

    private static List<GoodSearchResult> renderSearchResult(
            List<GoodSearchResult> goodSearchResultVOList) {
        if (CollectionUtils.isNotEmpty(goodSearchResultVOList)) {
            for (GoodSearchResult goodSearchResultVO : goodSearchResultVOList) {
                goodSearchResultVO.render();
            }
        }
        return goodSearchResultVOList;
    }
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Timestamp now =DateUtil.getSysDate();
        System.out.println(now); 
        
        now.setYear(now.getYear()-1);
        System.out.println(now);
        
        now.setMonth(now.getMonth()-12);
        System.out.println(now);
        
        now.setMonth(now.getMonth()-1);
        System.out.println(now);
        
        System.out.println(now.getDate());
        now.setDate(now.getDate()-10);
        System.out.println(now);
        
        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);
        now.setNanos(0);
        now.setDate(now.getDate()+1);
        System.out.println(now);
    }

}
