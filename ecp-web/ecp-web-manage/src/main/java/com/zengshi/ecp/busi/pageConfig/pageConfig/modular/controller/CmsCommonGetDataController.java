package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.SearchUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ComponentReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.GoodSearchResult;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ModularShopAdvertiseReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ModularShopCategoryReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ModularShopGdsHotReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ModularShopInfoReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ModularShopInfoRespVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 模塊化取數<br>
 * Date:2016年6月6日下午3:39:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping("/cmscommongetdata")
@Controller
public class CmsCommonGetDataController extends EcpBaseController{
    private String URL = "/pageConfig/pageConfig";
    private static String TRUE = "true";
    private String MODULE = CmsCommonGetDataController.class.getName();
    @Resource
    private IShopInfoRSV iShopInfoRSV;
    @Resource
    private IGdsCategoryRSV iGdsCategoryRSV;
    @Resource
    private IGdsEvalRSV iGdsEvalRSV;
    @Resource
    private IReportGoodPayedRSV iReportGoodPayedRSV;
    @Resource
    private IShopCollectRSV iShopCollectRSV;
    @Resource
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    @Resource
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    @Resource
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    @Resource
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource
    private ICoupRSV coupRSV;
    @Resource(name = "promQueryRSV")
    private IPromQueryRSV promQueryRSV;
   
    @Resource(name = "scoreInfoRSV")
    private IScoreInfoRSV scoreInfoRSV;
    
    /**
     * 
     * hotSales:(获取热销产品). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param count
     * @param shopId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/hotSales")
    @ResponseBody
    public Map<String,Object> hotSales(Model model,ModularShopGdsHotReqVO modularShopGdsHotReqVO) throws BusinessException {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<GoodSearchResult> hotGoods =null;
        if(StringUtil.isBlank(modularShopGdsHotReqVO.getCount())){
            resultMap.put("errMsg", "展示数量未设置！");
        }else if(StringUtil.isEmpty(modularShopGdsHotReqVO.getShopId())){
            resultMap.put("errMsg", "未指定店铺！");
        }else{
            try {
                hotGoods = SearchUtil.shopHotSales(Integer.valueOf(modularShopGdsHotReqVO.getCount()), modularShopGdsHotReqVO.getShopId());
            } catch (Exception e) {
                resultMap.put("errMsg", "查询数据异常！");
            }
        }
        resultMap.put("gdsList", hotGoods);
        return resultMap;
    }
    
    /**
     * 
     * qyrShopInfo:(获取店铺信息。). <br/> 
     * 
     * @author gxq 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/qyrshopinfo")
    public String qyrShopInfo(Model model,ModularShopInfoReqVO modularShopInfoVO){
        /* 店铺信息 begin */
        ModularShopInfoRespVO shopInfoVO=new ModularShopInfoRespVO();
        ShopInfoResDTO shopInfoResDTO = iShopInfoRSV.findShopInfoByShopID(modularShopInfoVO.getShopId());
        ObjectCopyUtil.copyObjValue(shopInfoResDTO, shopInfoVO, null, false);
        
        if(TRUE.equals(modularShopInfoVO.getIfSales())){//是否显示销量
            //获取销量
            RGoodSaleRequest gGoodSaleRequest=new RGoodSaleRequest();
            gGoodSaleRequest.setShopId(modularShopInfoVO.getShopId());
            Long saleNum=iReportGoodPayedRSV.querySumBuyNumByShopId(gGoodSaleRequest);
            shopInfoVO.setSaleNum(saleNum);
        }
        if(TRUE.equals(modularShopInfoVO.getIfGoodsBaby())){//是否显示宝贝
            //获取宝贝数量
            GoodSearchPageReqVO goodSearchPageReqVO=new GoodSearchPageReqVO();
            goodSearchPageReqVO.setShopId(modularShopInfoVO.getShopId()+"");
            SearchResult<GoodSearchResult> searchResult =SearchUtil.searchGood(goodSearchPageReqVO);
            shopInfoVO.setFavNum(searchResult.getNumFound());
        }
        if(TRUE.equals(modularShopInfoVO.getIfRate())){//是否显示好评率
            //获取好评率
            GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
            gdsEvalReqDTO.setShopId(modularShopInfoVO.getShopId());
            shopInfoVO.setEvalRate(iGdsEvalRSV.calCulateShopGoodEvalRate(gdsEvalReqDTO));
        }
        if(TRUE.equals(modularShopInfoVO.getIfCollectShop())){//是否显示收藏店铺
            /* 是否收藏  begin*/
            ShopCollectReqDTO shopCollectReqDTO=new ShopCollectReqDTO();
            shopCollectReqDTO.setShopId(modularShopInfoVO.getShopId()+"");
            shopCollectReqDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
            boolean isCollect=false;
            if(shopCollectReqDTO.getStaffId()!=0){
                PageResponseDTO<ShopInfoResDTO> page=iShopCollectRSV.listShopCollect(shopCollectReqDTO);   
                if (page  != null && CollectionUtils.isNotEmpty(page.getResult())) {
                    isCollect=true;
                }
            }
            model.addAttribute("isCollect", isCollect); 
            /* 是否收藏  end*/
        }
        shopInfoVO.setSmallLogoPathURL(ImageUtil.getImageUrl(shopInfoVO.getLogoPath() + "_48x48!"));
        shopInfoVO.setLogoPathURL(ImageUtil.getImageUrl(shopInfoVO.getLogoPath() + "_100x100!"));
        model.addAttribute("shopInfoResp", shopInfoVO);
        model.addAttribute("showParam", modularShopInfoVO);
      
        return URL+"/modular/loading/list/shophomepage/shop-info-template";
    }
    
    
    /**
     * 
     * gdsGrid:(店铺内商品搜索列表). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param goodSearchPageReqVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/gdsgrid")
    public String gdsGrid(Model model,GoodSearchPageReqVO goodSearchPageReqVO){
        GoodSearchPageReqVO vo=new GoodSearchPageReqVO();
        vo.setShopId(goodSearchPageReqVO.getShopId()+"");
        vo.setPageSize(16);
        if(StringUtil.isNotBlank(goodSearchPageReqVO.getFirstSort())){
            vo.setField(goodSearchPageReqVO.getFirstSort());
        }else{
            vo.setField("sales");
        }
        vo.setSort("desc");
        SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);
        if (result.isSuccess()) {
              /* 搜索列表 begin*/
              EcpBasePageRespVO<Map> pageRespVO = SearchUtil.renderRespVO(vo, result);
              super.addPageToModel(model, pageRespVO);
             
        }
        //回传是否显示的一些参数。
        model.addAttribute("showParam", goodSearchPageReqVO);
        model.addAttribute("isLogin", StaffLocaleUtil.getStaff().getId() != 0);
        return URL+"/modular/loading/list/shophomepage/shop-innerindex-list-template";
    }
    
    /**
     * 
     * page:(店铺内搜索商品列表分页查询). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param vo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/page")
    public String page(Model model,GoodSearchPageReqVO vo) throws BusinessException {
        // 分类目录Facet设置
       // vo.setCategoryPathFacet(false);
        SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);
        if (result.isSuccess()) {
            EcpBasePageRespVO<Map> pageRespVO = SearchUtil.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);
            model.addAttribute("isLogin", StaffLocaleUtil.getStaff().getId() != 0);
            return URL+"/modular/loading/list/shophomepage/shop-innerindex-gds-grid";
        }
        throw new BusinessException(result.getMessage());
    }  
    /**
     * 
     * qryCatgList:(查询获取商品分类). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryCatgList")
    public String qryCatgList(Model model,ModularShopCategoryReqVO reqVO) throws Exception {
        /* 商品分类  begin*/
        GdsCategoryReqDTO gdsCategoryReqDTO=new GdsCategoryReqDTO();
        gdsCategoryReqDTO.setShopId(reqVO.getShopId());
        gdsCategoryReqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_2);
        List<GdsCategoryRespDTO> gdsCategorys = new ArrayList<GdsCategoryRespDTO>();
        try {
            gdsCategorys=iGdsCategoryRSV.queryRootCategory(gdsCategoryReqDTO);
            if(CollectionUtils.isNotEmpty(gdsCategorys)){
                for(GdsCategoryRespDTO category:gdsCategorys){
                    GdsCategoryReqDTO chid=new GdsCategoryReqDTO();
                    chid.setCatgParent(category.getCatgCode());
                    List<GdsCategoryRespDTO> childs=iGdsCategoryRSV.querySubCategory(chid);
                    category.setChildren(childs);
                }
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取分类失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取分类失败！", e);
        }
        model.addAttribute("category", gdsCategorys);
        model.addAttribute("showParam", reqVO);
        return URL+"/modular/loading/list/shophomepage/shop-category-template";
    }
    
   
    
    /**
     * 
     * qryLeafletList:(根据内容位置id（placeId） 获取广告信息。). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @param request
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryLeafletList")
    @ResponseBody
    public Map<String,Object> qryLeafletList(Model model,ModularShopAdvertiseReqVO reqVO,HttpServletRequest request) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<CmsAdvertiseRespDTO> respList = null;
        
        // 1. 入参加工
        if(StringUtil.isBlank(reqVO.getPlaceId())){//内容位置
            LogUtil.error(MODULE, "内容位置参数未传！");
            resultMap.put("errMsg", "内容位置未选择！");
        }else{
            //1.1 查询内容位置
            CmsPlaceRespDTO placeRespDto = null;
            try {
                placeRespDto = this.qryPlaceByID(Long.valueOf(reqVO.getPlaceId()), reqVO.getStatus());
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询内容位置出现异常:",err);
                resultMap.put("errMsg", "查询内容位置出现异常！");
                return resultMap;
            }
            if(placeRespDto == null || StringUtil.isEmpty(placeRespDto.getId())){
                LogUtil.error(MODULE, "内容位置不存在！");
                resultMap.put("errMsg", "该内容位置不存在！");
            }else{
                //1.2 初始化查询参数
                CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
                reqDTO.setPlaceId(Long.valueOf(reqVO.getPlaceId()));
                if(reqVO.getShopId() != null){
                    reqDTO.setShopId(reqVO.getShopId());
                }
                if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
                    reqDTO.setStatus(reqVO.getStatus());
                }else{
                    reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                }
                
                reqDTO.setPageNo(1);
                if (StringUtil.isNotBlank(reqVO.getShowAmount())) {//广告数量
                    reqDTO.setPageSize(Integer.valueOf(reqVO.getShowAmount()));
                }else{
                    reqDTO.setPageSize(1);
                }
                
                String standard = "";// 规格
                if (StringUtil.isNotBlank(reqVO.getPlaceWidth())) {
                    standard = reqVO.getPlaceWidth() + "x";
                    if(StringUtil.isNotBlank(reqVO.getPlaceHeight())){
                        standard +=  reqVO.getPlaceHeight();
                    }
                    standard +="!";
                }
                
                reqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
                // 设置当前时间  查询当前时间有效的广告
                reqDTO.setThisTime(DateUtil.getSysDate());
                //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
               
                // 2. 调用广告服务，无分页
                respList = new ArrayList<CmsAdvertiseRespDTO>();
                PageResponseDTO<CmsAdvertiseRespDTO> pageInfo = null;
                try{
                    pageInfo = cmsAdvertiseRSV.queryCmsAdvertisePage(reqDTO);
                }catch(Exception err){
                    LogUtil.error(MODULE, "查询广告出现异常:",err);
                    resultMap.put("errMsg", "查询广告出现异常！");
                    return resultMap;
                }
                if (pageInfo == null || CollectionUtils.isEmpty(pageInfo.getResult())) {
                    LogUtil.warn(MODULE, "该内容位置无有效广告！");
                    resultMap.put("errMsg", "无有效广告！");
                }else{
                    // 3. 返回图片地址及广告链接地址
                    respList = pageInfo.getResult();
                    for (CmsAdvertiseRespDTO dto : respList) {
                        // 3.1调文件服务器，返回图片
                        if (StringUtil.isNotBlank(dto.getVfsId())) {
                            try {
                                dto.setVfsUrl(new AiToolUtil().genImageUrl(dto.getVfsId(),standard));
                            } catch (Exception err) {
                                LogUtil.error(MODULE, "查询图片服务器出现异常:",err);
                            }
                        }
                        if (StringUtil.isNotBlank(dto.getLinkUrl())) {
                            if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(dto.getLinkType())){//商品
                                if(this.isNumeric(dto.getLinkUrl())){
                                    dto.setLinkUrl("/gdsdetail/"+dto.getLinkUrl()+"-?adid="+dto.getId());
                                }
                            }else if(CmsConstants.LinkType.CMS_LINKTYPE_02.equalsIgnoreCase(dto.getLinkType())){//公告
                                if(this.isNumeric(dto.getLinkUrl())){
                                    dto.setLinkUrl("/info/infodetail?adid="+dto.getId()+"&id="+dto.getLinkUrl());
                                }
                            }else if(CmsConstants.LinkType.CMS_LINKTYPE_03.equalsIgnoreCase(dto.getLinkType())){
                                if(this.isNumeric(dto.getLinkUrl())){
                                    CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
                                    pageInfoReqDTO.setId(Long.valueOf(dto.getLinkUrl().trim()));
                                    try {
                                        CmsPageInfoRespDTO cmsPageInfoRespDTO = cmsPageInfoRSV.queryCmsPageInfo(pageInfoReqDTO);
                                        if (cmsPageInfoRespDTO != null && StringUtil
                                                .isNotEmpty(cmsPageInfoRespDTO.getSiteUrl())) {
                                            dto.setLinkUrl(cmsPageInfoRespDTO.getSiteUrl()+"?adid="+dto.getId());
                                        } else {
                                            dto.setLinkUrl("/modularcommon?adid="+dto.getId()+"&pageId=" + dto.getLinkUrl());
                                        }
                                    } catch (Exception err) {
                                        LogUtil.error(MODULE, "配置页面出现异常:",err);
                                    }
                                }
                            }else if(CmsConstants.LinkType.CMS_LINKTYPE_04.equalsIgnoreCase(dto.getLinkType())){
                            	if(this.isNumeric(dto.getLinkUrl())){
                                    dto.setLinkUrl("?adid="+dto.getId());
                                }
                            }
                        }
                    }
                }
                
            }
        }
        // 4. 返回结果
        resultMap.put("respList", respList);
        return resultMap;
    }
    
    /**
     * 
     * 功能描述：获取商品促销信息
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月11日 上午9:39:01</p>
     *
     * @param model
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/getPromGdsInfo")
    @ResponseBody
    public JSONObject getPromGdsInfo(Model model,Long goodsId,Long promId,String standard,Long siteId,String staffId) throws Exception{
    	GdsInfoReqDTO req = new GdsInfoReqDTO();
		if (CmsGoodsDetailUtil.isNumeric(staffId)) {
    		req.setStaffId(Long.valueOf(staffId));
    	}
    	//req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    	req.setId(goodsId);
    	GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                GdsQueryOption.BASIC, GdsQueryOption.MAINPIC 
                };
        SkuQueryOption[] skuOptions = new SkuQueryOption[] { 
                SkuQueryOption.BASIC,SkuQueryOption.CAlDISCOUNT
                };
        req.setGdsQueryOptions(gdsOptions);
        req.setSkuQuerys(skuOptions);
    	GdsInfoDetailRespDTO respDTO=  CmsGoodsDetailUtil.getGdsDetailByGdsId(req);
    	CmsGoodsDetailUtil.extendGdsInfo(respDTO, null, false, true, standard);
    	CmsGoodsDetailUtil.getGdsPromPrice(respDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, promId);
    	if(null != respDTO){
    	    String url=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, String.valueOf(siteId))+respDTO.getUrl();
            respDTO.setUrl(url);
        }
    	JSONObject obj = new JSONObject();
    	obj.put("respDTO", respDTO);
    	return obj;
    }
    /**
     * 
     * getCouponInfo:(获取优惠券信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param coupIds
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/getcouponinfo")
    @ResponseBody
    public Map<String,Object> getCouponInfo (String coupIds){
        LogUtil.info(MODULE, "进入优惠券信息查询，coupIds="+(coupIds==null?coupIds:""));
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Long> coupIdList = new ArrayList<Long>();
        List<CoupInfoRespDTO> coupInfoDTOList= null;//优惠券数据
        //转化成Long数组
        if(StringUtil.isNotBlank(coupIds)){
            String[] coupIdStrs = coupIds.split(",");
            if(coupIdStrs!=null && coupIdStrs.length >0){
                for(String coupIdstr : coupIdStrs){
                    if(StringUtil.isNotBlank(coupIdstr) && isNumeric(coupIdstr)){
                        coupIdList.add(Long.parseLong(coupIdstr));
                    }
                }
            }
        }
        
        resultMap.put("resultFlag", "ok");
        if(CollectionUtils.isNotEmpty(coupIdList)){
            try {
                coupInfoDTOList = coupRSV.queryCoupInfoList(coupIdList);
                if(CollectionUtils.isNotEmpty(coupInfoDTOList)){
                    resultMap.put("coupons", coupInfoDTOList);
                }
            } catch (Exception e) {
                resultMap.put("resultFlag", "exception");
                LogUtil.error(MODULE, "获取优惠券信息异常", e);
            }
        }
        return resultMap;
    }
    @RequestMapping(value = "/qryseckillforwap")
    @ResponseBody
    public Map<String,Object> qrySecKillWap(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "开始查询秒杀数据");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        PromDTO promReqDto = new PromDTO();
        if(StringUtil.isEmpty(reqVO.getSiteId())){
            reqVO.setSiteId(promReqDto.getCurrentSiteId());
        }
        String standard = "160x160!";// 规格
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())) {
            standard = reqVO.getPlaceWidth() + "x";
            if(StringUtil.isNotEmpty(reqVO.getPlaceHeight())){
                standard +=  reqVO.getPlaceHeight();
            }
            standard +="!";
        }
        if(StringUtil.isEmpty(reqVO.getSiteId())){
            LogUtil.error(MODULE, "站点信息为空");
            resultMap.put("resultFlag", "error");
            resultMap.put("msg", "站点信息为空");
            return resultMap;
        }
        promReqDto.setSiteId(reqVO.getSiteId());
        if(StringUtil.isNotEmpty(reqVO.getPageSize())){
            promReqDto.setPageSize(reqVO.getPageSize()); 
        }
        
        List<KillGdsInfoDTO> resultList = null;
        List<KillGdsInfoDTO> qryList = null;
        try {
            qryList = promQueryRSV.killPromGdsinfoList(promReqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询秒杀数据异常！", e);
            resultMap.put("resultFlag", "exception");
            resultMap.put("msg", "查询秒杀数据异常");
            return resultMap;
        }
        LogUtil.info(MODULE, "查询秒杀数据结束！");
        if(CollectionUtils.isNotEmpty(qryList)){
            resultList = new ArrayList<KillGdsInfoDTO>();
            for(KillGdsInfoDTO gdsInfo : qryList){
                if(null == gdsInfo || StringUtil.isEmpty(gdsInfo.getGdsId())){
                    continue;
                }
                gdsInfo.setURL(ParamsTool.getImageUrl(gdsInfo.getMediaUuid(), standard));
                resultList.add(gdsInfo);
            }
        }
        if(CollectionUtils.isEmpty(resultList)){
            LogUtil.info(MODULE, "秒杀数据为空");
            resultMap.put("resultFlag", "empty");
            resultMap.put("msg", "秒杀数据为空");
            return resultMap;
        }
        
        LogUtil.info(MODULE, "秒杀数据为:"+resultList);
        resultMap.put("gdsList", resultList);
        resultMap.put("resultFlag", "ok");
        resultMap.put("msg", "成功");
        return resultMap;
    }
     /**
     * 
     * getPoint:(获取用户积分). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/getpoint")
    @ResponseBody
    public Map<String,Object> getPoint(){
        LogUtil.info(MODULE, "进入用户积分信息查询!");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        
        ScoreSourceReqDTO scoreReqDto = new ScoreSourceReqDTO();
        BaseStaff staffInfo = scoreReqDto.getStaff();
        if(null == staffInfo || StringUtil.isEmpty(staffInfo.getId()) || 0 >= staffInfo.getId()){
            LogUtil.info(MODULE, "查询用户积分用户未登录!"); 
            resultMap.put("resultFlag", "noLogo");
            return resultMap;
        }
        Long staffId = staffInfo.getId();
        LogUtil.info(MODULE, "查询用户积分用户为："+staffId); 
        
        ScoreInfoResDTO scoreInfo = null;
        try {
            scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(staffId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询用户积分异常!");
            resultMap.put("resultFlag", "exception");
            return resultMap;
        }
        resultMap.put("resultFlag", "ok");
        if(null != scoreInfo && StringUtil.isNotEmpty(scoreInfo.getScoreBalance())){
            resultMap.put("point", scoreInfo.getScoreBalance());
            LogUtil.info(MODULE, "查询用户积分结束，积分为："+scoreInfo.getScoreBalance()); 
        }else{
            resultMap.put("point", 0);
            LogUtil.info(MODULE, "查询用户积分结束，积分信息为空！"); 
        }
        return resultMap;
    }
    
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * 
     * @author gxq 
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
    
    /**
     * 
     * qryPlaceByID:(查询内容位置). <br/> 
     * 
     * @author gxq 
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
                LogUtil.error(MODULE, "广告查询中查询内容位置出错！");
                respDto = null;
            }
            
        }
        
        return respDto;
    }
}

