package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Gds021Req;
import com.zengshi.ecp.app.req.Gds023Req;
import com.zengshi.ecp.app.req.Gds024Req;
import com.zengshi.ecp.app.resp.gds.GoodSearchResultVO;
import com.zengshi.ecp.app.resp.gds.ShopSearchResultVO;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.search.dubbo.search.*;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ShopSearchUtil {

    private static final String MODULE = ShopSearchUtil.class.getName();

    public static final String APP = "2";

    public static List<GoodSearchResult> renderGoodSearchResult(List<GoodSearchResult> goodSearchResultVOList) {

        if (CollectionUtils.isNotEmpty(goodSearchResultVOList)) {
            for (GoodSearchResult goodSearchResultVO : goodSearchResultVOList) {
                goodSearchResultVO.render();

                List<String> typeList = new ArrayList<String>();
                goodSearchResultVO.setPromotionType(typeList);

                try {
                    // 获取促销信息
                    PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
                    CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                    promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
                    promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
                    promRuleCheckDTO.setGdsId(Long.parseLong(goodSearchResultVO.getId()));
                    promRuleCheckDTO.setChannelValue(APP);
                    promRuleCheckDTO.setShopId(Long.parseLong(goodSearchResultVO.getShopId()));
                    promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
                    promRuleCheckDTO.setSkuId(goodSearchResultVO.getFirstSkuId());
                    promRuleCheckDTO.setBasePrice(goodSearchResultVO.getDefaultPrice());
                    promRuleCheckDTO.setBuyPrice(goodSearchResultVO.getDiscountPrice());
                    promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
                    promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
                    // 获取促销价格
                    IPromRSV promRSV=ApplicationContextUtil.getBean("promRSV",
                            IPromRSV.class);
                    List<PromListRespDTO> promInfoDTOList = promRSV.listPromForSolr(promRuleCheckDTO);
                    List<String> promotionType=new ArrayList<String>();
                    if (CollectionUtils.isNotEmpty(promInfoDTOList)) {
                        PromListRespDTO prom = promInfoDTOList.get(0);
                        if (prom.getPromSkuPriceRespDTO() != null) {
                            BigDecimal price = prom.getPromSkuPriceRespDTO().getDiscountFinalPrice();
                            if (price != null && price.longValue() != 0) {
                                goodSearchResultVO.setDiscountPrice(price.longValue());
                            }
                        }
                        for (PromListRespDTO promListRespDTO : promInfoDTOList) {
                            if(promListRespDTO.getPromInfoDTO() !=null && org.apache.commons.lang3.StringUtils.isNoneBlank(promListRespDTO.getPromInfoDTO().getPromTypeName())){
                                promotionType.add(promListRespDTO.getPromInfoDTO().getPromTypeShow());
                            }
                        }
                        goodSearchResultVO.setPromotionType(promotionType);
                    }
                } catch (Exception e) {
                    LogUtil.error(ShopSearchUtil.class.getName(), "cal prom price failed", e);
                }
            }
        }

        return goodSearchResultVOList;

    }

    /**
     * 店铺热卖商品（分页）
     * @param vo 分页请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SearchResult<GoodSearchResult> shopHotSales(Gds023Req vo){

        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());

        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());

        if(StringUtils.isNotBlank(vo.getId())){
            List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(vo.getId());
            extraANDFieldQueryList.add(extraFieldQueryField);
            searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        }

        List<SortField> sortFieldList=new ArrayList<SortField>();
        sortFieldList.add(new SortField("sales", ESort.DESC));
        searchParam.setSortFieldList(sortFieldList);

        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class)));

        return result;
    }

    /**
     * 店铺新上架商品（分页）
     * @param vo 分页请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SearchResult<GoodSearchResult> shopNewSales(Gds024Req vo){

        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());

        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());

        if(StringUtils.isNotBlank(vo.getId())){
            List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(vo.getId());
            extraANDFieldQueryList.add(extraFieldQueryField);
            searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        }

        List<SortField> sortFieldList=new ArrayList<SortField>();
        sortFieldList.add(new SortField("updateTime", ESort.DESC));
        searchParam.setSortFieldList(sortFieldList);

        SearchResult<GoodSearchResult> result = SearchFacade.search(GoodSearchResult.class,
                searchParam, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                        IGdsCatalog2SiteRSV.class)));

        return result;
    }
	
    public static SearchResult<ShopSearchResultVO> searchShop(Gds021Req vo) throws BusinessException {
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        searchParam.setCollectionName("shopcollection");
        searchParam.setKeyword(vo.getKeyword());
        
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());
        
        searchParam = addFieldSortSupport(searchParam, vo);
        if(StringUtil.isNotBlank(vo.getEvalRate())){
        searchParam = addShopEvalRateFilter(searchParam, vo);
        }
        addSiteIdFilter(searchParam, vo);
        
        SearchResult<ShopSearchResultVO> searchResult = SearchFacade.search(ShopSearchResultVO.class, searchParam, null,null,null);
        return searchResult;
    }
    
    
    
    /**
     * 字段处理过滤
     * 
     * @param searchParam
     * @param vo
     * @return
     */
    private static SearchParam addFieldSortSupport(SearchParam searchParam, Gds021Req vo) {
        
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
    
    
    private static SearchParam addShopEvalRateFilter(SearchParam searchParam, Gds021Req vo) {
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
    
    private static void addSiteIdFilter(SearchParam searchParam, Gds021Req vo){
    	 List<ExtraFieldQueryField> extraANDFieldQueryList = new ArrayList<ExtraFieldQueryField>();
    	 ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
    	 extraFieldQueryField.setName("siteList");
    	 extraFieldQueryField.setValue("1");
    	 extraFieldQueryField.setExcept(false);
    	 extraANDFieldQueryList.add(extraFieldQueryField);
    	 searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
    }
    
    public static List<ShopSearchResultVO> renderShopSearchResult(List<ShopSearchResultVO> shopSearchResultVOList) {

        if (CollectionUtils.isNotEmpty(shopSearchResultVOList)) {
            for (ShopSearchResultVO shopSearchResultVO : shopSearchResultVOList) {
                shopSearchResultVO=render(shopSearchResultVO);
            }

        }

        return shopSearchResultVOList;

    }

    private static ShopSearchResultVO render(ShopSearchResultVO shopSearchResultVO) {

        shopSearchResultVO.setLogoUrl(ImageUtil.getImageUrl(shopSearchResultVO.getLogoPath() + ShopSearchResultVO.SUFFIX_IMAGE_SIZE));

        //店铺下热销商品查询
        Gds023Req gds023Req=new Gds023Req();
        gds023Req.setId(shopSearchResultVO.getId());
        gds023Req.setPageNumber(1);
        gds023Req.setPageSize(4);
        SearchResult<GoodSearchResult> searchResult = ShopSearchUtil.shopHotSales(gds023Req);

        if (searchResult.isSuccess()) {

            searchResult.setResultList(ShopSearchUtil.renderGoodSearchResult(searchResult.getResultList()));
            List<GoodSearchResultVO> goodSearchResultVOs = new ArrayList<GoodSearchResultVO>();
            for (GoodSearchResult goodSearchResult : searchResult.getResultList()) {
                GoodSearchResultVO goodSearchResultVO = new GoodSearchResultVO();
                ObjectCopyUtil.copyObjValue(goodSearchResult, goodSearchResultVO, null, false);
                goodSearchResultVOs.add(goodSearchResultVO);
            }
            shopSearchResultVO.setGdsCount(searchResult.getNumFound());
            shopSearchResultVO.setGoodList(goodSearchResultVOs);
        }else{
            LogUtil.error(MODULE, "获取店铺：" + shopSearchResultVO.getId() + "下热销商品失败！"+searchResult.getMessage());
        }

        return shopSearchResultVO;

    }

}
