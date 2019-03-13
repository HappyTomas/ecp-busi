package com.zengshi.ecp.busi.search.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.search.vo.ExtraRespVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.RangeQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryCategory;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.good.GoodCategoriesQueryFilter;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月9日下午2:18:17 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends EcpBaseController {

    private final static String EXTRA_VO_ATTR = "extra";

    /**
     * 分页数据封装
     * 
     * @param vo
     * @param result
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private EcpBasePageRespVO<Map> renderRespVO(EcpBasePageReqVO vo,
            SearchResult<GoodSearchResult> result) throws BusinessException {
        PageResponseDTO<GoodSearchResult> t = new PageResponseDTO<GoodSearchResult>();
        t.setResult(this.renderSearchResult(result.getResultList()));
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

    /**
     * 执行搜索请求，统一搜索入口，操作字段由页面传入，但是后台需做字段校验。
     * 
     * @param model
     * @param vo
     * @param keyword
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping()
    public String search(Model model, GoodSearchPageReqVO vo,@RequestParam(value = "nav_type", required = false) String nav_type) throws BusinessException {
        
        model.addAttribute("nav_type", nav_type);

        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(2l);

        // 初始分页长度设置
        vo.setPageSize(16);

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
            searchParam = this.addFieldFilterSupport(searchParam, vo);
        }
        
        //积分商品
        ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
        extraFieldQueryField.setName("ifScoreGds");
        extraFieldQueryField.setValue("1");
        extraFieldQueryList.add(extraFieldQueryField);
        searchParam.setExtraANDFieldQueryList(extraFieldQueryList); 

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
        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam,extraQueryInfo,filterList,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class)));
        if (result.isSuccess()) {
            EcpBasePageRespVO<Map> pageRespVO = this.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);

            if (StringUtils.isBlank(vo.getKeyword())) {
                extraRespVO.setKeyword("");
            } else {
                extraRespVO.setKeyword(vo.getKeyword());
            }
            model.addAttribute(EXTRA_VO_ATTR, extraRespVO);
            // 增加广告ID。
            if(StringUtil.isNotBlank(vo.getAdid())){
                model.addAttribute("adid",vo.getAdid());
            }

            return "/search/search-result";

        }

        throw new BusinessException(result.getMessage());

    }

    private SearchResult<GoodSearchResult> doPage(GoodSearchPageReqVO vo) throws BusinessException {

        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(2l);
        
        searchParam.setKeyword(vo.getKeyword());
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());

        searchParam = this.addFieldFilterSupport(searchParam, vo);
        
        //积分商品
        List<ExtraFieldQueryField> extraFieldQueryList=new ArrayList<ExtraFieldQueryField>();
        ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
        extraFieldQueryField.setName("ifScoreGds");
        extraFieldQueryField.setValue("1");
        extraFieldQueryList.add(extraFieldQueryField);
        searchParam.setExtraANDFieldQueryList(extraFieldQueryList); 

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

        return SearchFacade.search(GoodSearchResult.class, searchParam, extraQueryInfo, filterList,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));

    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page")
    public String page(Model model, GoodSearchPageReqVO vo) throws BusinessException {

        SearchResult<GoodSearchResult> result = this.doPage(vo);
        if (result.isSuccess()) {
            EcpBasePageRespVO<Map> pageRespVO = this.renderRespVO(vo, result);
            // 增加广告ID。
            if(StringUtil.isNotBlank(vo.getAdid())){
                model.addAttribute("adid",vo.getAdid());
            }
            super.addPageToModel(model, pageRespVO);
            return "/search/page/search-resultlist";
        }

        throw new BusinessException(result.getMessage());

    }

    private SearchParam addFieldFilterSupport(SearchParam searchParam, GoodSearchPageReqVO vo) {
        
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

    private List<GoodSearchResult> renderSearchResult(List<GoodSearchResult> goodSearchResultVOList) {

        if (CollectionUtils.isNotEmpty(goodSearchResultVOList)) {
            for (GoodSearchResult goodSearchResultVO : goodSearchResultVOList) {
                goodSearchResultVO.render();
            }
        }

        return goodSearchResultVOList;

    }
    /**
     * 
     * suggest:(autoComplement). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param keyword
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/suggest")
    @ResponseBody
    public List<CollationReuslt> suggest(@RequestParam("keyword")
    String keyword) throws BusinessException {

        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        searchParam.setKeyword(keyword);
        SearchResult<CollationReuslt> result = SearchFacade.suggest2(searchParam,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));

        if (result.isSuccess()) {

            // 没有Suggest返回emptyList
            return result.getResultList();
        }

        throw new BusinessException(result.getMessage());

    }
}
