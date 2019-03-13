package com.zengshi.ecp.busi.shop.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.ScrollResult;
import com.zengshi.ecp.busi.shop.vo.GdsCategoryVO;
import com.zengshi.ecp.busi.shop.vo.GoodSearchPromVO;
import com.zengshi.ecp.busi.shop.vo.GoodSearchResult;
import com.zengshi.ecp.busi.shop.vo.GoodsPromInfoJsonBean;
import com.zengshi.ecp.busi.shop.vo.PromScollerResult;
import com.zengshi.ecp.busi.shop.vo.PromSkuRespVO;
import com.zengshi.ecp.busi.shop.vo.PromTypeRespVO;
import com.zengshi.ecp.busi.shop.vo.ShopInfoVO;
import com.zengshi.ecp.busi.shop.vo.ShopMainVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.result.FieldFacetResult;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mobile <br>
 * Description: 店铺首页<br>
 * Date:2016年8月18日上午11:23:25  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/shopmain")
@Controller
public class ShopMainController extends EcpBaseController{
    private static final String MODUAL = ShopMainController.class.getName();
    private static String URL = "/shop";
    private static final String APP = "APP";
    private static final String SOLR_COLLECTION_NAME = "promcollection";
    private final static String SUFFIX_IMAGE_SIZE = "_220x220!";
    @Resource
    private IGdsCategoryRSV iGdsCategoryRSV;
    private final static String ISLOGIN_VO_ATTR = "isLogin"; 
    @Resource
    private IShopInfoRSV iShopInfoRSV;
    @Resource
    private IGdsEvalRSV iGdsEvalRSV;
    @Resource
    private IReportGoodPayedRSV iReportGoodPayedRSV;
    @Resource
    private IShopCollectRSV iShopCollectRSV;
    @Resource
    private IPromRSV promRSV;
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    @Resource
    private IPromQueryRSV promQueryRSV;
    @Resource(name = "cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    @Resource(name = "cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    /**
     * 
     * init:(页面入口). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/{shopId}")
    public String init(Model model,@PathVariable("shopId")Long shopId){
        GoodSearchPageReqVO vo=new GoodSearchPageReqVO();
        vo.setShopId(String.valueOf(shopId));
        vo.setPageSize(16);
        vo.setField("sales");
        vo.setSort("desc");
         /* 店铺信息 begin */
         ShopInfoVO shopInfoVO=new ShopInfoVO();
         ShopInfoResDTO shopInfoResDTO=iShopInfoRSV.findShopInfoByShopID(Long.valueOf(vo.getShopId()));
         ObjectCopyUtil.copyObjValue(shopInfoResDTO, shopInfoVO, null, false);
         shopInfoVO.setSmallLogoPathURL(ImageUtil.getImageUrl(shopInfoVO.getLogoPath() + ""));
         shopInfoVO.setLogoPathURL(ImageUtil.getImageUrl(shopInfoVO.getLogoPath() + "_100x100!"));
         model.addAttribute("shopInfoResp", shopInfoVO);
         model.addAttribute("shopId", shopInfoVO.getId());
         /* 店铺信息 end */
         model.addAttribute("searchBoxWidth", "450");
         /* 是否收藏  begin*/
        ShopCollectReqDTO shopCollectReqDTO=new ShopCollectReqDTO();
        shopCollectReqDTO.setShopId(String.valueOf(shopId));
        shopCollectReqDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
       // iShopCollectRSV.deleteShopBySel(shopCollectReqDTO);
        boolean isCollect=false;
        if(shopCollectReqDTO.getStaffId()!=0){
              PageResponseDTO<ShopInfoResDTO> page=iShopCollectRSV.listShopCollect(shopCollectReqDTO);   
                if (page  != null && CollectionUtils.isNotEmpty(page.getResult())) {
                    isCollect=true;
                }
        }
        try {
//            List<GoodSearchResult> hotGoods=SearchUtil.shopHotSales(8,shopId);
            //根据店铺id 获取促销类型
            List<PromTypeRespVO> promTypeList = getPromTypeList(shopId);
            model.addAttribute("promTypeList", promTypeList);
//            model.addAttribute("hotGdsList", hotGoods);
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "获取店铺热卖失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODUAL, "获取店铺热卖失败！", e);
        }
        
        model.addAttribute("isCollect", isCollect);   
        /* 是否收藏  end*/
        model.addAttribute(ISLOGIN_VO_ATTR, StaffLocaleUtil.getStaff().getId() != 0);
        return URL + "/shop-main";
    }
    /**
     * 
     * collectShop:(收藏店铺). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param shopId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/collectShop/{shopId}")
    @ResponseBody
    public EcpBaseResponseVO collectShop(Model model,@PathVariable("shopId")Long shopId) throws BusinessException {
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
         try {
            ShopCollectReqDTO shopCollectReqDTO=new ShopCollectReqDTO();
            shopCollectReqDTO.setShopId(String.valueOf(shopId));
            shopCollectReqDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
   
            iShopCollectRSV.insertShopCollect(shopCollectReqDTO);
            ecpBaseResponseVO.setResultMsg("收藏店铺成功");
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
         } catch (BusinessException e) {
             LogUtil.error(MODUAL, "收藏店铺失败", e);
             ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
             ecpBaseResponseVO.setResultMsg("收藏店铺失败");
         } catch (Exception e) {
             LogUtil.error(MODUAL, "收藏店铺失败", e);
             ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
             ecpBaseResponseVO.setResultMsg("收藏店铺失败");
         }
        
        return ecpBaseResponseVO;
    }
   /**
    * 
    * deleteShop:(取消收藏). <br/> 
    * 
    * @author gxq 
    * @param model
    * @param shopId
    * @return
    * @throws BusinessException 
    * @since JDK 1.6
    */
    @RequestMapping(value = "/deleteShop/{shopId}")
    @ResponseBody
    public EcpBaseResponseVO deleteShop(Model model,@PathVariable("shopId")Long shopId) throws BusinessException {
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
         try {
            ShopCollectReqDTO shopCollectReqDTO=new ShopCollectReqDTO();
            shopCollectReqDTO.setShopId(String.valueOf(shopId));
            shopCollectReqDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
            iShopCollectRSV.deleteShopBySel(shopCollectReqDTO);
            ecpBaseResponseVO.setResultMsg("取消收藏店铺成功");
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
         } catch (BusinessException e) {
             LogUtil.error(MODUAL, "取消收藏店铺失败", e);
             ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
             ecpBaseResponseVO.setResultMsg("取消收藏店铺失败");
         } catch (Exception e) {
             LogUtil.error(MODUAL, "取消收藏店铺失败", e);
             ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
             ecpBaseResponseVO.setResultMsg("取消收藏店铺失败");
         }
        
        return ecpBaseResponseVO;
    }
    /**
     * 
     * scroll:(滚动分页加载). <br/> 
     * 
     * @author gxq 
     * @param vo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/scroll")
    @ResponseBody
    public ScrollResult scroll(GoodSearchPageReqVO vo) throws BusinessException{

        // 初始分页长度设置
        vo.setPageSize(SearchUtil.DEFAULT_PAGESIZE);

        vo.setPageNumber(vo.getPage());

        SearchResult<Map<String,Object>> result = SearchUtil.searchGood(vo);

        if (result.isSuccess()) {
            return SearchUtil.renderRespVO(result);
        }

        throw new BusinessException(result.getMessage());
    }
    /**
     * 
     * searchGoodsList:(查询店铺的商品). <br/> 
     * 
     * @author gxq 
     * @param shopMainVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gridgoodslist")
    public String searchGoodsList(Model model,GoodSearchPageReqVO vo){
        return URL + "/list/shop-gds-list";
    }
    /**
     * 
     * gridSaleList:(促销商品页面). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="gridsalelist")
    public String gridSaleList(Model model,GoodSearchPageReqVO vo){
        model.addAttribute("promTypeCode", vo.getPromTypeCode());
        return URL + "/list/shop-salegds-list";
    }
    /**
     * 
     * getPromTypeList:(根据店铺id获取促销类型). <br/> 
     * 
     * @author gxq 
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    public  List<PromTypeRespVO> getPromTypeList(Long shopId){
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        searchParam.setCollectionName(SOLR_COLLECTION_NAME);
        searchParam.setIfRetDocList(true);
        
        List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
        
        ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
        extraFieldQueryField.setName("shopId");
        extraFieldQueryField.setValue(String.valueOf(shopId));
        extraANDFieldQueryList.add(extraFieldQueryField);
        
        
        ExtraFieldQueryField extraFieldQueryField1=new ExtraFieldQueryField();
        extraFieldQueryField1.setName("siteId");
        extraFieldQueryField1.setValue(SiteLocaleUtil.getSite()+"");//SiteLocaleUtil.getSite()+""
        extraANDFieldQueryList.add(extraFieldQueryField1);
        
        searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        
        List<String> facetFieldList=new ArrayList<String>();
        facetFieldList.add("promTypeCode");
        searchParam.setFieldFacetFieldList(facetFieldList);
        
        searchParam.setFacetLimit(Integer.MAX_VALUE);
        
        searchParam.setPageNo(1);
        searchParam.setPageSize(1);
        //2、调用solr服务
        List<PromTypeRespVO> list=new ArrayList<PromTypeRespVO>();
        SearchResult<PromTypeRespVO> result = SearchFacade.search(PromTypeRespVO.class,
                searchParam, null, null,null); 
        
        if(result!=null){
             Map m=result.getFieldFacetResultMap();
             //当没有分组时 支持代码健壮 不报错null
             if(m!=null){
                 PromTypeDTO promTypeDTO = new PromTypeDTO();
                 FieldFacetResult r= null;
                 if(m.get("promTypeCode")!=null){
                     r=(FieldFacetResult)m.get("promTypeCode");
                 }
                 List totalList=new ArrayList();
                 if(r!=null){
                     totalList=r.getValue();
                 }
                if(CollectionUtils.isNotEmpty(totalList)){
                    for(int i=0;i<totalList.size();i++){
                         PromTypeRespVO t=new PromTypeRespVO();
                         t.setPromTypeCode(((FieldFacetResult.Count)totalList.get(i)).getValue());
                         // 获得促销类型对象
                         promTypeDTO.setPromTypeCode(t.getPromTypeCode());
                         PromTypeResponseDTO p = promQueryRSV.queryPromType(promTypeDTO);
                         t.setNameShort(p.getNameShort());
                         t.setPromTypeName(p.getPromTypeName());
                         list.add(t);
                    }
                }
             }
        }
        return list;
    }
    @RequestMapping(value="/scollsalelist")
    @ResponseBody
    public PromScollerResult scollSaleList(Model model,GoodSearchPageReqVO vo){
        PromScollerResult promScollerResult = new PromScollerResult();
        //店铺编码为空
        if(vo.getShopId()==null){
              throw new BusinessException("店铺编码不能为空");
        }
        
        if(vo.getPageSize()==0){
            vo.setPageSize(10);
        }
        vo.setPageNumber(vo.getPage());
        if(vo.getPageNumber()==0){
            vo.setPageNumber(1);
        }
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        searchParam.setCollectionName(SOLR_COLLECTION_NAME);
        searchParam.setIfRetDocList(true);
        List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
        ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
        extraFieldQueryField.setName("shopId");
        extraFieldQueryField.setValue(vo.getShopId()+"");
        extraANDFieldQueryList.add(extraFieldQueryField);
        ExtraFieldQueryField extraFieldQueryField1=new ExtraFieldQueryField();
        extraFieldQueryField1.setName("siteId");
        extraFieldQueryField1.setValue(SiteLocaleUtil.getSite()+"");//SiteLocaleUtil.getSite()+""
        extraANDFieldQueryList.add(extraFieldQueryField1);
        //非空
        if(StringUtils.isNotBlank(vo.getPromTypeCode())){
             ExtraFieldQueryField extraFieldQueryField2=new ExtraFieldQueryField();
             extraFieldQueryField2.setName("promTypeCode");
             extraFieldQueryField2.setValue(vo.getPromTypeCode());
             extraANDFieldQueryList.add(extraFieldQueryField2);
        }
        searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        searchParam.setPageNo(vo.getPageNumber());
        searchParam.setPageSize(vo.getPageSize());
        
        //2、调用solr服务
        List<PromSkuRespVO> list = new ArrayList<PromSkuRespVO>();
        SearchResult<PromSkuRespVO> result = SearchFacade.search(PromSkuRespVO.class,
                searchParam, null, null,null); 
        if(result!=null){
            if(CollectionUtils.isNotEmpty(result.getResultList())){
                list=result.getResultList();
                 PromTypeDTO promTypeDTO = new PromTypeDTO();
                for(PromSkuRespVO v:list){
                     // 获得促销类型对象
                     promTypeDTO.setPromTypeCode(v.getPromTypeCode());
                     PromTypeResponseDTO p = promQueryRSV.queryPromType(promTypeDTO);
                     v.setNameShort(p.getNameShort());
                     v.setPromTypeName(p.getPromTypeName());
                     v.setSkuId(Long.valueOf(v.getChildId()));
                     v.setPromId(Long.valueOf(v.getParentId()));
                     SearchParam searchParam1 = new SearchParam();
                     searchParam1.setCurrentSiteId(SiteLocaleUtil.getSite());
                     searchParam1.setIfRetDocList(true);
                     searchParam1.setId(v.getGdsId()+"");
                        
                     List<ExtraFieldQueryField> extraANDFieldQueryList1=new ArrayList<ExtraFieldQueryField>();
                     ExtraFieldQueryField extraFieldQueryFieldt=new ExtraFieldQueryField();
                     extraFieldQueryFieldt.setName("firstSkuId");
                     extraFieldQueryFieldt.setValue(v.getSkuId()+"");
                     extraANDFieldQueryList1.add(extraFieldQueryFieldt);
                     searchParam1.setExtraANDFieldQueryList(extraANDFieldQueryList1);
    
    
                     searchParam1.setPageNo(1);
                     searchParam1.setPageSize(10);
                    
                     //排序
                     List<SortField> sortFieldList=new ArrayList<SortField>();
                     SortField s=new SortField("sales", ESort.DESC);
                     sortFieldList.add(s);
                     searchParam1.setSortFieldList(sortFieldList);
                    
                    
                     //2、调用solr服务
                     SearchResult<GoodSearchResult> result1 = SearchFacade.search(GoodSearchResult.class,
                            searchParam1, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                                    IGdsCatalog2SiteRSV.class))); 
                    
                     if(result1!=null){
                        if(CollectionUtils.isNotEmpty(result1.getResultList())){
                            GoodSearchResult r=result1.getResultList().get(0);
                            r.render();//初始化数据
                            v.setPrice(r.getDefaultPrice());
                            v.setPriceYun(MoneyUtil.convertCentToYuan(r.getDefaultPrice()));
                            v.setPromPrice(r.getDiscountPrice());
                            v.setPromPriceYun(MoneyUtil.convertCentToYuan(r.getDiscountPrice()));
                            //如何获得url
                             v.setImageUrl(r.getImageUrl());
                            v.setSales(r.getSales());
                        }
                     }
                     if(v.getPrice()==null){
                         //价格获得
                         GdsSkuInfoReqDTO dto=new GdsSkuInfoReqDTO();
                         dto.setId(v.getSkuId());
                         //库存查询 设置条件
                         //SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.SHOWPRICE};
                         SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.MAINPIC,SkuQueryOption.SHOWPRICE};
                         dto.setSkuQuery(skuQuery);
                         //调用商品接口
                         GdsSkuInfoRespDTO  respDTO=new GdsSkuInfoRespDTO();
                         respDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
                         v.setPrice(respDTO.getRealPrice());
                         v.setPriceYun(MoneyUtil.convertCentToYuan(respDTO.getRealPrice()));
                         v.setPromPrice(v.getPrice());
                         v.setPromPriceYun(MoneyUtil.convertCentToYuan(v.getPrice()));
                         v.setSales(0l);
                         //如何获得url
                         v.setImageUrl(respDTO.getMainPic()==null?ImageUtil.getImageUrl(null):ImageUtil.getImageUrl(respDTO.getMainPic().getMediaUuid()+SUFFIX_IMAGE_SIZE));
                     }
                     PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
                     CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                     promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
                     promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
                     promRuleCheckDTO.setGdsId(v.getGdsId());
                     promRuleCheckDTO.setChannelValue(APP);
                     promRuleCheckDTO.setShopId(Long.parseLong(vo.getShopId()));
                     promRuleCheckDTO.setSkuId(v.getSkuId());
                     promRuleCheckDTO.setBasePrice(v.getPrice());
                     promRuleCheckDTO.setBuyPrice(v.getPromPrice());
                     promRuleCheckDTO.setShopName(" ");
                     promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
                     promRuleCheckDTO.setPromId(v.getPromId());
                     
                    List<PromListRespDTO> promInfoList= promRSV.listPromForSolr(promRuleCheckDTO);
                    
                    if(CollectionUtils.isNotEmpty(promInfoList)){
                        if(promInfoList.get(0)!=null){
                            if(promInfoList.get(0).getPromSkuPriceRespDTO()!=null){
                                BigDecimal value=promInfoList.get(0).getPromSkuPriceRespDTO().getDiscountFinalPrice();
                                v.setPromPrice(value!=null?value.longValue():v.getPrice());
                            }
                        }
                    }
                }
                promScollerResult.setTotal(result.getTotallyPage());
                promScollerResult.setDatas(list);
            }
        }
        return promScollerResult;
    }
    /**
     * 
     * hotSales:(查询热卖商品). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param shopMainVO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/hotsales")
    @ResponseBody
    public List<GoodSearchResult> hotSales(Model model,ShopMainVO shopMainVO) throws BusinessException {
        if(shopMainVO.getShopId() != null){
            List<GoodSearchResult> hotGoods=SearchUtil.shopHotSales(shopMainVO.getCount(), shopMainVO.getShopId());
            return hotGoods;
        }else{
            return null;
        }
    }
    
    /**
     * 
     * queryShopCatgInfo:(获取店铺分类). <br/> 
     * 
     * @author gxq 
     * @param shopMainVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/queryshopcategoryinfo")
    public String queryShopCatgInfo(Model model,ShopMainVO shopMainVO){
        GdsCategoryReqDTO gdsCategoryReqDTO=new GdsCategoryReqDTO();
        gdsCategoryReqDTO.setShopId(shopMainVO.getShopId());
        gdsCategoryReqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_2);

        List<GdsCategoryVO> vos=new ArrayList<GdsCategoryVO>();
        List<GdsCategoryRespDTO> catgs=iGdsCategoryRSV.queryRootCategory(gdsCategoryReqDTO);
        if(CollectionUtils.isNotEmpty(catgs)){
            for(GdsCategoryRespDTO catg :catgs){
                GdsCategoryReqDTO req=new GdsCategoryReqDTO();
                req.setCatgParent(catg.getCatgCode());
                List<GdsCategoryRespDTO> childCatgs=iGdsCategoryRSV.querySubCategory(req);

                GdsCategoryVO vo=new GdsCategoryVO();
                vo.setCatgCode(catg.getCatgCode());
                vo.setCatgName(catg.getCatgName());
                vo.setSortNo(catg.getSortNo());

                // 生成图片路径
                vo.setMediaURL(ImageUtil.getImageUrl(catg.getMediaUuid() + GdsCategoryVO.SUFFIX_IMAGE_SIZE));

                if(CollectionUtils.isNotEmpty(childCatgs)){
                    List<GdsCategoryVO> childVos=new ArrayList<GdsCategoryVO>();
                    for(GdsCategoryRespDTO childCatg:childCatgs){
                        GdsCategoryVO childVo=new GdsCategoryVO();
                        childVo.setCatgCode(childCatg.getCatgCode());
                        childVo.setCatgName(childCatg.getCatgName());
                        childVo.setSortNo(childCatg.getSortNo());
                        // 生成图片路径
                        childVo.setMediaURL(ImageUtil.getImageUrl(childCatg.getMediaUuid() + GdsCategoryVO.SUFFIX_IMAGE_SIZE));
                        childVos.add(childVo);
                    }
                    vo.setChildren(childVos);
                }
                vos.add(vo);
            }
        }
        model.addAttribute("categoryList", vos);
        return URL + "/shopcategory/shop-category";
    }
    
    /**
     * 
     * queryPromInfo:(异步加载获取促销信息列表). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param goodSearchPromVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryPromInfo")
    @ResponseBody
    public GoodsPromInfoJsonBean queryPromInfo(GoodSearchPromVO goodSearchPromVO) {
        GoodsPromInfoJsonBean bean = new GoodsPromInfoJsonBean();
        // 获取促销信息
        PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
        promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
        promRuleCheckDTO.setGdsId(goodSearchPromVO.getGdsId());
        promRuleCheckDTO.setChannelValue(APP);
        promRuleCheckDTO.setShopId(goodSearchPromVO.getShopId());
        promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
        promRuleCheckDTO.setSkuId(goodSearchPromVO.getSkuId());
        promRuleCheckDTO.setBasePrice(goodSearchPromVO.getRealPrice());
        promRuleCheckDTO.setBuyPrice(goodSearchPromVO.getDiscountPrice());
        promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
        
        List<PromListRespDTO> promInfoDTOList = promRSV.listPromForSolr(promRuleCheckDTO);

        String[] promTypes = null;

        if (CollectionUtils.isNotEmpty(promInfoDTOList)) {
            promTypes = new String[promInfoDTOList.size()];
            for (int i = 0; i < promInfoDTOList.size(); i++) {
                //取第一条价格
                if(i==0){
                    if(promInfoDTOList.get(i).getPromSkuPriceRespDTO() != null){
                        bean.setPrice(promInfoDTOList.get(i).getPromSkuPriceRespDTO().getDiscountFinalPrice());
                        bean.setGuidePrice(promInfoDTOList.get(i).getPromSkuPriceRespDTO().getDiscountCaclPrice());
                    }
                }
                if(promInfoDTOList.get(i).getPromInfoDTO() != null){
                    promTypes[i] = promInfoDTOList.get(i).getPromInfoDTO().getPromTypeShow();
                }
            }
        }

        List<String> typeList = new ArrayList<String>();
        if(org.apache.commons.lang.StringUtils.equals(goodSearchPromVO.getIfFree(),SearchConstants.STATUS_VALID)){
            typeList.add("免邮");
        }
        if (promTypes != null) {
            for (String type : promTypes) {
                if (!typeList.contains(type)) {
                    typeList.add(type);
                }
            }
            // 取前三的促销类型
            if (typeList.size() > 3) {
                List<String> greatThan3Type = new ArrayList<String>();
                for (int i = 3; i < typeList.size(); i++) {
                    greatThan3Type.add(typeList.get(i));
                }
                typeList.removeAll(greatThan3Type);
            }

        }
        bean.setPromTypes(typeList);
        return bean;
    }
    public static void main(String args[]){
        String hexstr = "884b2fbc1397c37a4f6fe951aa19679d";  
        System.out.print(Integer.parseInt("0x0b"));
    }
    
    
    /******广告******/
    
    /**
     * qryLeafletList:(查询广告列表). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param model
     * @param placeId
     * @param linkType
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryLeafletList")
    public String qryLeafletList(Model model,ComponentReqVO reqVO,HttpServletRequest request) throws Exception {
        LogUtil.info(MODUAL, "==========reqVO:" + reqVO.toString() + ";");
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<CmsAdvertiseRespDTO> respList = null;
        
        // 1. 入参加工
        if(StringUtil.isNotEmpty(reqVO.getPlaceId())){//内容位置
            //1.1 查询内容位置
            CmsPlaceRespDTO placeRespDto = null;
            placeRespDto = this.qryPlaceByID(reqVO.getPlaceId(), reqVO.getStatus());
            if(placeRespDto != null && StringUtil.isNotEmpty(placeRespDto.getId())){
                if(StringUtil.isNotEmpty(reqVO.getPlaceHeight()) && StringUtil.isNotEmpty(placeRespDto.getPlaceHeight())){
                    reqVO.setPlaceHeight(placeRespDto.getPlaceHeight());
                }
                if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(placeRespDto.getPlaceWidth())){
                    reqVO.setPlaceWidth(placeRespDto.getPlaceWidth());
                }
                //优先取内容位置表中的palceCount
                if(StringUtil.isNotEmpty(reqVO.getPlaceSize()) && StringUtil.isNotEmpty(placeRespDto.getPlaceCount())){
                    reqVO.setPlaceSize(placeRespDto.getPlaceCount());
                }
                
                //1.2 初始化查询参数
                CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
                reqDTO.setPlaceId(reqVO.getPlaceId());
                
                if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
                    reqDTO.setStatus(reqVO.getStatus());
                }else{
                    reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                }
                    
                if (StringUtil.isNotEmpty(reqVO.getPlaceSize())) {//广告数量
                    reqDTO.setPageNo(1);
                    reqDTO.setPageSize(reqVO.getPlaceSize());
                }
                
                if(StringUtil.isNotEmpty(reqVO.getShopId())){//店铺
                    reqDTO.setShopId(reqVO.getShopId());
                }
                String standard = "";// 规格
                if (StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
                    standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
                }
                reqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03);
                // 设置当前时间  查询当前时间有效的广告
                reqDTO.setThisTime(DateUtil.getSysDate());
                //eqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
               
                // 2. 调用广告服务，无分页
                respList = new ArrayList<CmsAdvertiseRespDTO>();
                try{
                    PageResponseDTO<CmsAdvertiseRespDTO> pageInfo = cmsAdvertiseRSV.queryCmsAdvertisePage(reqDTO);
                    if (pageInfo != null) {
                        respList = pageInfo.getResult();
                    }
                    // 3. 返回图片地址及广告链接地址
                    String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
                    if (!CollectionUtils.isEmpty(respList)) {
                        for (CmsAdvertiseRespDTO dto : respList) {
                            // 3.1调文件服务器，返回图片
                            if (StringUtil.isNotBlank(dto.getVfsId())) {
                                dto.setVfsUrl(ParamsTool.getImageUrl(dto.getVfsId(),"375x144!"));
                            }
                            if (StringUtil.isNotBlank(dto.getLinkUrl())) {
                                if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(dto.getLinkType())){//商品
                                    if(this.isNumeric(dto.getLinkUrl())){
                                        dto.setLinkUrl("/gdsdetail/"+dto.getLinkUrl()+"-");
                                    }
                                    dto.setLinkUrl(baseUrl+dto.getLinkUrl());
                                }else if(CmsConstants.LinkType.CMS_LINKTYPE_02.equalsIgnoreCase(dto.getLinkType())){//公告
                                    if(this.isNumeric(dto.getLinkUrl())){
                                        dto.setLinkUrl("/info/infodetail?id="+dto.getLinkUrl());
                                    }
                                    dto.setLinkUrl(baseUrl+dto.getLinkUrl());
                                }else if(CmsConstants.LinkType.CMS_LINKTYPE_03.equalsIgnoreCase(dto.getLinkType())){
                                    //dto.setLinkUrl("/prom/init");
                                    //dto.setLinkUrl(baseUrl+dto.getLinkUrl());
                                    if(this.isNumeric(dto.getLinkUrl())){
                                        CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
                                        pageInfoReqDTO.setId(Long.valueOf(dto.getLinkUrl().trim()));
                                        CmsPageInfoRespDTO cmsPageInfoRespDTO = cmsPageInfoRSV.queryCmsPageInfo(pageInfoReqDTO);
                                        if (cmsPageInfoRespDTO != null && StringUtil
                                                .isNotEmpty(cmsPageInfoRespDTO.getSiteUrl())) {
                                            dto.setLinkUrl(baseUrl+cmsPageInfoRespDTO.getSiteUrl());
                                        } else {
                                            dto.setLinkUrl(baseUrl+"/modularcommon?pageId=" + dto.getLinkUrl());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }catch(Exception err){
                    LogUtil.error(MODUAL, "查询广告出现异常:",err);
                }
            }
        }
        // 4. 返回结果
        model.addAttribute("respList", respList);
        return URL + "/list/shop-main-advertise";
    }
    
    /**
     * 
     * qryPlaceByID:(查询内容位置). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param placeId
     * @param status
     * @return 
     * @since JDK 1.6
     */
    private CmsPlaceRespDTO  qryPlaceByID(Long placeId,String status){
        CmsPlaceRespDTO respDto = null;
        if(StringUtil.isNotEmpty(placeId)){
            if(StringUtil.isBlank(status)){//默认查询有效的广告位置
                status = CmsConstants.ParamStatus.CMS_PARAMSTATUS_1;
            }
            
            CmsPlaceReqDTO reqDto= new CmsPlaceReqDTO();
            reqDto.setId(placeId);
            reqDto.setStatus(status);
            try {
                respDto = cmsPlaceRSV.queryCmsPlace(reqDto);
            } catch (Exception e) {
                LogUtil.error(MODUAL, "广告查询中查询内容位置出错！");
                respDto = null;
            }
            
        }
        
        return respDto;
    }
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private boolean isNumeric(String str){
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
         
        return false; 
     }
}