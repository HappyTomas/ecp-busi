package com.zengshi.ecp.busi.shop.shopSearch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchPageReqVO;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchResult;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.RangeQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.paas.utils.StringUtil;

public class ShopSearchUtil {
	
	
    public static SearchResult<ShopSearchResult> searchShop(ShopSearchPageReqVO vo) throws BusinessException {
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        searchParam.setCollectionName("shopcollection");
        searchParam.setKeyword(vo.getKeyword());
        if(StringUtils.isNotBlank(vo.getId())){
            searchParam.setId(vo.getId());
        }
        
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());
        
        searchParam = addFieldSortSupport(searchParam, vo);
        if(StringUtil.isNotBlank(vo.getEvalRate())){
        searchParam = addShopEvalRateFilter(searchParam, vo);
        }
        addSiteIdFilter(searchParam, vo);
        
        SearchResult<ShopSearchResult> searchResult = SearchFacade.search(ShopSearchResult.class, searchParam, null,null,null);
        return searchResult;
    }
    
    
    
    /**
     * 字段处理过滤
     * 
     * @param searchParam
     * @param vo
     * @return
     */
    private static SearchParam addFieldSortSupport(SearchParam searchParam, ShopSearchPageReqVO vo) {
        
        List<SortField> sortFieldList=new ArrayList<SortField>();
        
        // 自定义排序
        if (StringUtils.isNotBlank(vo.getField())) {
            
            // 排序字段校验
            if (StringUtils.equals("saleCount", vo.getField())) {
                ESort eSort = ESort.getAndValidSort(vo.getSort());
                if (null != eSort) {
                sortFieldList.add(new SortField(vo.getField(), eSort));
                }
            }
     
        }
        
        searchParam.setSortFieldList(sortFieldList);

        return searchParam;

    }
    
    
    private static SearchParam addShopEvalRateFilter(SearchParam searchParam, ShopSearchPageReqVO vo) {
        List<RangeQueryField> rangeQueryFieldList = new ArrayList<RangeQueryField>();
        RangeQueryField rangeQueryField = new RangeQueryField();
        rangeQueryField.setName("gdsEvalRate");
        if(vo.getEvalRate().equals("1")){
        rangeQueryField.setStart("100");
        rangeQueryField.setEnd("100");
        }else if(vo.getEvalRate().equals("2")){
        	
            rangeQueryField.setStart("90");
            rangeQueryField.setEnd("100");
//            rangeQueryField.setEnd("79.99");
        }else if(vo.getEvalRate().equals("3")){
        	
            rangeQueryField.setStart("80");
            rangeQueryField.setEnd("100");
//            rangeQueryField.setEnd("29.99");
        	
        }
        rangeQueryFieldList.add(rangeQueryField);
        searchParam.setRangeQueryFieldList(rangeQueryFieldList);
        
       return searchParam;
    }
    
    private static void addSiteIdFilter(SearchParam searchParam, ShopSearchPageReqVO vo){
    	 List<ExtraFieldQueryField> extraANDFieldQueryList = new ArrayList<ExtraFieldQueryField>();
    	 ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
    	 extraFieldQueryField.setName("siteList");
    	 extraFieldQueryField.setValue("1");
    	 extraFieldQueryField.setExcept(false);
    	 extraANDFieldQueryList.add(extraFieldQueryField);
    	 searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
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
            SearchResult<ShopSearchResult> result) throws BusinessException {
        PageResponseDTO<ShopSearchResult> t = new PageResponseDTO<ShopSearchResult>();
        t.setResult(ShopSearchUtil.renderSearchResult(result.getResultList()));
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
    
    
    private static List<ShopSearchResult> renderSearchResult(List<ShopSearchResult> shopSearchResultVOList) {

        if (CollectionUtils.isNotEmpty(shopSearchResultVOList)) {
            for (ShopSearchResult shopSearchResultVO : shopSearchResultVOList) {
            	shopSearchResultVO.render();       
            }

        }

        return shopSearchResultVOList;

    }

//    public  static void main(String args[])throws Exception{
//    	for(int i = 0; i < 10 ;i++){
//    	ShopSearchPageReqVO vo  = new ShopSearchPageReqVO();
//    	vo.setPageSize(10);
//    
//    	SearchResult<ShopSearchResult>  searchResult = ShopSearchUtil.searchShop(vo);
//    	System.out.println(searchResult.getNumFound());
//    	}
//    } 
    
}
