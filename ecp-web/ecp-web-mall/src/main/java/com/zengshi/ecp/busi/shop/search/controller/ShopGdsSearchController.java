package com.zengshi.ecp.busi.shop.search.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.CategoryClickAjaxRespVO;
import com.zengshi.ecp.busi.search.vo.ExtraRespVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchPromVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.busi.search.vo.GoodsPromInfoJsonBean;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.FieldFacetResult.Count;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
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
import com.zengshi.paas.utils.ImageUtil;
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
@RequestMapping(value = "/shopgdssearch")
public class ShopGdsSearchController extends EcpBaseController {

    private final static String EXTRA_VO_ATTR = "extra";

    private final static String SHOWTYPE_VO_ATTR = "showType";

    private final static String ISLOGIN_VO_ATTR = "isLogin";

    private final static String SHOPID_VO_ATTR = "shopId";

    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;

    @Resource
    private IPromRSV promRSV;

    @Resource
    private IShopCollectRSV iShopCollectRSV;

    // @Resource
    // private ISecHotKeywordRSV secHotKeywordRSV;

    @Resource
    private ICmsHotSearchRSV cmsHotSearchRSV;

    @Resource
    private IGdsCollectRSV gdsCollectRSV;

    @Resource(name = "cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;

    @Resource(name = "reportGoodPayedRSV")
    private IReportGoodPayedRSV reportGoodPayedRSV;

    @Resource(name = "gdsEvalRSV")
    private IGdsEvalRSV gdsEvalRSV;

    @Resource(name = "shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;

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
    public String search(Model model, GoodSearchPageReqVO vo) throws BusinessException {

        // 初始分页长度设置
        vo.setPageSize(16);

        // 分类目录Facet设置
        vo.setCategoryPathFacet(true);

        SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);

        if (result.isSuccess()) {
            
            // 是否支持工厂库存.
            if(GdsUtils.isSupportFacStock()){
                model.addAttribute("supportFacStock", GdsUtils.isSupportFacStock());  
            }

            EcpBasePageRespVO<Map> pageRespVO = SearchUtil.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);

            ExtraRespVO extraRespVO = new ExtraRespVO();
            if (StringUtils.isNotBlank(vo.getCategory())) {
                extraRespVO.setSearchCategory(vo.getCategory());
            }

            extraRespVO.setSearchType("1");
            if (StringUtils.isBlank(vo.getKeyword())) {
                extraRespVO.setKeyword("");
            } else {
                extraRespVO.setKeyword(vo.getKeyword());
            }

            // 是否指定了查询分类
            GdsCategoryRespDTO gdsCategoryRespDTO = null;
            if (StringUtils.isNotBlank(vo.getCategory())) {

                GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
                gdsCategoryReqDTO.setCatgCode(vo.getCategory());
                gdsCategoryReqDTO.setCatgType(SearchUtil.getCatgType(vo.getShopId()));
                gdsCategoryRespDTO = this.gdsCategoryRSV.queryGdsCategoryByPK(gdsCategoryReqDTO);

                // 分类已被删除
                if (gdsCategoryRespDTO == null) {
                    // TODO 暂时为分类不存在搜索全部
                    // throw new BusinessException("分类不存在或已被删除：" + vo.getCategory());
                } else {
                    extraRespVO.setSearchType("2");
                }

            }

            if (result.getResultList().size() > 0) {

                // 指定了查询分类
                if (gdsCategoryRespDTO != null) {
                    extraRespVO = this.addCategoryQueryResult(extraRespVO, vo.getCategory(),
                            gdsCategoryRespDTO.getCatgName(), gdsCategoryRespDTO.getCatgParent(),
                            result.getNumFound() + "",vo.getShopId());
                } else {
                    extraRespVO=addPlateCatgFacetResult(extraRespVO,result);
                    extraRespVO=addShopCatgFacetResult(extraRespVO,result);
                }

            } else {

                // 指定了查询分类的情况下，虽然查询结果为空，但是分类目录信息任然需要展示
                if (gdsCategoryRespDTO != null) {
                    extraRespVO = this.addCategoryQueryResult(extraRespVO, vo.getCategory(),
                            gdsCategoryRespDTO.getCatgName(), gdsCategoryRespDTO.getCatgParent(),
                            "0",vo.getShopId());
                }

            }

            extraRespVO.setType("1");//搜商品
            model.addAttribute(EXTRA_VO_ATTR, extraRespVO);
            model.addAttribute("category", vo.getCategory());
            // 默认以网格方式展示列表
            model.addAttribute(SHOWTYPE_VO_ATTR, "1");

            // 获取当前登录用户
            // BaseStaff staff = new BaseInfo().getStaff();
            model.addAttribute(ISLOGIN_VO_ATTR, StaffLocaleUtil.getStaff().getId() != 0);

            if(StringUtils.isNotBlank(vo.getShopId())){
                model.addAttribute("shopId", vo.getShopId());

                Long shopId=Long.parseLong(vo.getShopId());
                // 店铺名称
                ShopInfoResDTO shopInfoResDTO=new ShopInfoResDTO();
                shopInfoResDTO=this.shopInfoRSV.findShopInfoByShopID(shopId);
                model.addAttribute("shopName", shopInfoResDTO.getShopName());
                model.addAttribute("shopFullName", shopInfoResDTO.getShopFullName());
                model.addAttribute("linkPerson", shopInfoResDTO.getLinkPerson());
                model.addAttribute("linkMobilephone", shopInfoResDTO.getLinkMobilephone());
                model.addAttribute("shopImageUrl",ImageUtil.getImageUrl(shopInfoResDTO.getLogoPath() + "_100x100!"));
                // 销量
                RGoodSaleRequest rgoodSaleRequest=new RGoodSaleRequest();
                rgoodSaleRequest.setShopId(shopId);
                model.addAttribute("sales",this.reportGoodPayedRSV.querySumBuyNumByShopId(rgoodSaleRequest));
                // 好评率
                GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
                gdsEvalReqDTO.setShopId(shopId);
                model.addAttribute("goodEvalRate",this.gdsEvalRSV.calCulateShopGoodEvalRate(gdsEvalReqDTO));

                GoodSearchPageReqVO v=new GoodSearchPageReqVO();
                v.setShopId(vo.getShopId());
                v.setRetResult(false);
                result =SearchUtil.searchGood(v);
                model.addAttribute("gdsNum",result.getNumFound());

                /* 是否收藏  begin*/
                ShopCollectReqDTO shopCollectReqDTO=new ShopCollectReqDTO();
                shopCollectReqDTO.setShopId(String.valueOf(shopId));
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
            // 增加广告ID。
            if(StringUtil.isNotBlank(vo.getAdid())){
                model.addAttribute("adid",vo.getAdid());
            }
            return "/search/search-result";

        }

        throw new BusinessException(result.getMessage());

    }

    private ExtraRespVO addPlateCatgFacetResult(ExtraRespVO extraRespVO,SearchResult<GoodSearchResult> result){

        extraRespVO.setParentCateCode("");
        extraRespVO.setParentCateName("平台分类");

        // 查询Main分类Facet列表
        List<Map<String, String>> facetCateInfoList = new ArrayList<Map<String, String>>();
        Map<String,Long> topcateCountMap=new HashMap<String,Long>();
        if (result.getFieldFacetResultMap().containsKey(SearchUtil.FIELD_CATEGORYPATH)) {
            if (CollectionUtils.isNotEmpty(result.getFieldFacetResultMap()
                    .get(SearchUtil.FIELD_CATEGORYPATH).getValue())) {

                List<Count> counts = result.getFieldFacetResultMap()
                        .get(SearchUtil.FIELD_CATEGORYPATH).getValue();
                Map<String, String> fieldInfoMap;
                String temp[];
                for (Count count : counts) {
                    fieldInfoMap = new HashMap<String, String>();
                    temp = count.getValue().split(SearchConstants.SEPERATOR);
                    fieldInfoMap.put("topCateCode", temp[0]);
                    fieldInfoMap.put("topCateValue", temp[1]);
                    fieldInfoMap.put("code", temp[2]);
                    fieldInfoMap.put("value", temp[3]);
                    fieldInfoMap.put("count", count.getCount() + "");
                    facetCateInfoList.add(fieldInfoMap);

                    if(topcateCountMap.containsKey(temp[0])){
                        topcateCountMap.put(temp[0], topcateCountMap.get(temp[0])+count.getCount());
                    }else{
                        topcateCountMap.put(temp[0],count.getCount());
                    }
                }

            }
        }

        // key为顶级分类名称
        Map<String, List<Map<String, String>>> cateInfoMap = new HashMap<String, List<Map<String, String>>>();
        List<String> topCateValueList = new ArrayList<String>();
        Map<String, String> topCateCodeMap = new HashMap<String, String>();
        String firstTopCate="";

        // 根据Facet分类查询其顶级分类，并进行归类
        for (Map<String, String> map : facetCateInfoList) {

            String topCateValue = map.get("topCateValue");

            //二级分类等于主分类的，count数设置为总数
            if(StringUtils.equals(map.get("code"), map.get("topCateCode"))){
                map.put("count", topcateCountMap.get(map.get("code"))+"");
            }

            if(StringUtils.isBlank(firstTopCate)){
                firstTopCate=map.get("code");
            }

            if (cateInfoMap.containsKey(topCateValue)) {
                cateInfoMap.get(topCateValue).add(map);
            } else {
                List<Map<String, String>> cateList = new ArrayList<Map<String, String>>();
                cateList.add(map);
                cateInfoMap.put(topCateValue, cateList);

                topCateValueList.add(topCateValue);
                topCateCodeMap.put(map.get("topCateCode"),map.get("topCateCode"));
            }

            // // 查询顶级分类
            // GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
            // gdsCategoryReqDTO.setCatgCode(map.get("code"));
            // gdsCategoryRespDTO = this.gdsCategoryRSV
            // .queryRootCategoryByPK(gdsCategoryReqDTO);
            // if (gdsCategoryRespDTO != null) {
            // if (cateInfoMap.containsKey(gdsCategoryRespDTO.getCatgName())) {
            // cateInfoMap.get(gdsCategoryRespDTO.getCatgName()).add(map);
            // } else {
            // List<Map<String, String>> cateList = new ArrayList<Map<String,
            // String>>();
            // cateList.add(map);
            // cateInfoMap.put(gdsCategoryRespDTO.getCatgName(), cateList);
            //
            // topCateList.add(gdsCategoryRespDTO.getCatgName());
            // }
            // } else {
            //
            // // 当前分类刚好是顶级分类，把当前分类当成顶级分类展示
            //
            // }

        }

        //二级分类等于主分类的，提前展示
        for(Entry<String, List<Map<String, String>>> entry:cateInfoMap.entrySet()){
            int pos=-1;
            for(int i=0;i<entry.getValue().size();i++){
                if(topCateCodeMap.containsKey(entry.getValue().get(i).get("code"))){
                    pos=i;
                    break;
                }
            }
            if(pos!=-1){
                Map<String,String> map=entry.getValue().get(pos);
                entry.getValue().set(pos, entry.getValue().get(0));
                entry.getValue().set(0,map);
            }
        }

        extraRespVO.setCateInfoMap(cateInfoMap);
        extraRespVO.setTopCateNameList(topCateValueList);

        // 查询第一个Top分类的属性和属性值列表
        if (StringUtils.isNotBlank(firstTopCate)) {
            GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
            reqDTO.setCatgCode(firstTopCate);
            GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
                    .querySearchProps(reqDTO);
            if (gdsCatg2PropRelationRespDTO != null) {
                List<GdsPropRespDTO> gdsPropRespDTOList = gdsCatg2PropRelationRespDTO
                        .getSearchProps();
                if (CollectionUtils.isNotEmpty(gdsPropRespDTOList)) {
                    extraRespVO.setPropList(gdsPropRespDTOList);
                }
            }
        }

        return extraRespVO;

    }

    private ExtraRespVO addShopCatgFacetResult(ExtraRespVO extraRespVO,SearchResult<GoodSearchResult> result){

        extraRespVO.setShopParentCateCode("");
        extraRespVO.setShopParentCateName("店铺分类");

        // 查询Main分类Facet列表
        List<Map<String, String>> facetCateInfoList = new ArrayList<Map<String, String>>();
        Map<String,Long> topcateCountMap=new HashMap<String,Long>();
        if (result.getFieldFacetResultMap().containsKey(SearchUtil.FIELD_CATEGORYPATH_SHOP)) {
            if (CollectionUtils.isNotEmpty(result.getFieldFacetResultMap()
                    .get(SearchUtil.FIELD_CATEGORYPATH_SHOP).getValue())) {

                List<Count> counts = result.getFieldFacetResultMap()
                        .get(SearchUtil.FIELD_CATEGORYPATH_SHOP).getValue();
                Map<String, String> fieldInfoMap;
                String temp[];
                for (Count count : counts) {
                    fieldInfoMap = new HashMap<String, String>();
                    temp = count.getValue().split(SearchConstants.SEPERATOR);
                    fieldInfoMap.put("topCateCode", temp[0]);
                    fieldInfoMap.put("topCateValue", temp[1]);
                    fieldInfoMap.put("code", temp[2]);
                    fieldInfoMap.put("value", temp[3]);
                    fieldInfoMap.put("count", count.getCount() + "");
                    facetCateInfoList.add(fieldInfoMap);

                    if(topcateCountMap.containsKey(temp[0])){
                        topcateCountMap.put(temp[0], topcateCountMap.get(temp[0])+count.getCount());
                    }else{
                        topcateCountMap.put(temp[0],count.getCount());
                    }
                }

            }
        }

        // key为顶级分类名称
        Map<String, List<Map<String, String>>> cateInfoMap = new HashMap<String, List<Map<String, String>>>();
        List<String> topCateValueList = new ArrayList<String>();
        Map<String, String> topCateCodeMap = new HashMap<String, String>();
        String firstTopCate="";

        // 根据Facet分类查询其顶级分类，并进行归类
        for (Map<String, String> map : facetCateInfoList) {

            String topCateValue = map.get("topCateValue");

            //二级分类等于主分类的，count数设置为总数
            if(StringUtils.equals(map.get("code"), map.get("topCateCode"))){
                map.put("count", topcateCountMap.get(map.get("code"))+"");
            }

            if(StringUtils.isBlank(firstTopCate)){
                firstTopCate=map.get("code");
            }

            if (cateInfoMap.containsKey(topCateValue)) {
                cateInfoMap.get(topCateValue).add(map);
            } else {
                List<Map<String, String>> cateList = new ArrayList<Map<String, String>>();
                cateList.add(map);
                cateInfoMap.put(topCateValue, cateList);

                topCateValueList.add(topCateValue);
                topCateCodeMap.put(map.get("topCateCode"),map.get("topCateCode"));
            }

        }

        //二级分类等于主分类的，提前展示
        for(Entry<String, List<Map<String, String>>> entry:cateInfoMap.entrySet()){
            int pos=-1;
            for(int i=0;i<entry.getValue().size();i++){
                if(topCateCodeMap.containsKey(entry.getValue().get(i).get("code"))){
                    pos=i;
                    break;
                }
            }
            if(pos!=-1){
                Map<String,String> map=entry.getValue().get(pos);
                entry.getValue().set(pos, entry.getValue().get(0));
                entry.getValue().set(0,map);
            }
        }

        extraRespVO.setShopCateInfoMap(cateInfoMap);
        extraRespVO.setShopTopCateNameList(topCateValueList);

        return extraRespVO;

    }

    /**
     * 单个分类查询
     *
     * @param extraRespVO
     * @param currCategoryCode
     * @param currCategoryName
     * @return
     */
    private ExtraRespVO addCategoryQueryResult(ExtraRespVO extraRespVO, String currCategoryCode,
                                               String currCategoryName, String parentCateCode, String hits,String shopId) {

        String catgType=SearchUtil.getCatgType(shopId);

        GdsCategoryReqDTO gdsCategoryReqDTO = null;
        List<GdsCategoryRespDTO> childrenCateList = null;
        if (StringUtils.isNotBlank(parentCateCode)) {

            // 查询当前分类的父分类
            gdsCategoryReqDTO = new GdsCategoryReqDTO();
            gdsCategoryReqDTO.setCatgCode(parentCateCode);
            gdsCategoryReqDTO.setCatgType(catgType);
            GdsCategoryRespDTO gdsCategoryRespDTO = this.gdsCategoryRSV
                    .queryGdsCategoryByPK(gdsCategoryReqDTO);

            // 出现异常，父分类已被删除
            if (gdsCategoryRespDTO == null) {
                throw new BusinessException("分类不存在或已被删除：" + parentCateCode);
            } else {
                extraRespVO.setParentCateCode(gdsCategoryRespDTO.getCatgCode());
                extraRespVO.setParentCateName(gdsCategoryRespDTO.getCatgName());
            }

            gdsCategoryReqDTO = new GdsCategoryReqDTO();
            gdsCategoryReqDTO.setCatgParent(parentCateCode);
            gdsCategoryReqDTO.setCatgType(catgType);
            childrenCateList = this.gdsCategoryRSV.querySubCategory(gdsCategoryReqDTO);

        } else {

            // 没有父分类(当前分类已经是一级分类)，则设置当前分类为父分类
            extraRespVO.setParentCateCode("");

            gdsCategoryReqDTO = new GdsCategoryReqDTO();
            gdsCategoryReqDTO.setCatgType(catgType);
            if(GdsConstants.GdsCategory.CATG_TYPE_1.equals(catgType)){
                gdsCategoryReqDTO.setCatlogId(1l);
                extraRespVO.setParentCateName("平台分类");
            }else{
                gdsCategoryReqDTO.setShopId(Long.parseLong(shopId));
                extraRespVO.setParentCateName("店铺分类");
            }
            childrenCateList = this.gdsCategoryRSV.queryRootCategory(gdsCategoryReqDTO);
        }

        // 查询当前分类的其它兄弟分类
        List<String> topCateNameList = new ArrayList<String>();
        topCateNameList.add(currCategoryName);
        Map<String, Map<String, String>> topCateInfoMap = new HashMap<String, Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", currCategoryCode);
        map.put("count", hits);// 只能统计出当前分类的查询结果个数
        topCateInfoMap.put(currCategoryName, map);

        gdsCategoryReqDTO = new GdsCategoryReqDTO();
        gdsCategoryReqDTO.setCatgCode(parentCateCode);
        if (CollectionUtils.isNotEmpty(childrenCateList)) {
            for (GdsCategoryRespDTO childrenCate : childrenCateList) {
                if (!StringUtils.equals(currCategoryCode, childrenCate.getCatgCode())) {
                    topCateNameList.add(childrenCate.getCatgName());
                    map = new HashMap<String, String>();
                    map.put("code", childrenCate.getCatgCode());
                    topCateInfoMap.put(childrenCate.getCatgName(), map);
                }
            }
        }

        extraRespVO.setTopCateNameList(topCateNameList);
        extraRespVO.setTopCateInfoMap(topCateInfoMap);

        // 查询当前分类子分类列表
        gdsCategoryReqDTO = new GdsCategoryReqDTO();
        gdsCategoryReqDTO.setCatgParent(currCategoryCode);
        gdsCategoryReqDTO.setCatgType(catgType);
        childrenCateList = this.gdsCategoryRSV.querySubCategory(gdsCategoryReqDTO);

        if (CollectionUtils.isNotEmpty(childrenCateList)) {

            // key为顶级分类名称
            Map<String, List<Map<String, String>>> cateInfoMap = new HashMap<String, List<Map<String, String>>>();
            List<Map<String, String>> cateList = new ArrayList<Map<String, String>>();
            for (GdsCategoryRespDTO childrenCate : childrenCateList) {

                // 查询Main分类Facet列表
                Map<String, String> fieldInfoMap = new HashMap<String, String>();
                fieldInfoMap.put("code", childrenCate.getCatgCode());
                fieldInfoMap.put("value", childrenCate.getCatgName());
                cateList.add(fieldInfoMap);

            }

            cateInfoMap.put(currCategoryName, cateList);
            extraRespVO.setCateInfoMap(cateInfoMap);

        }

        // 查询分类的属性和属性值列表
        GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
        reqDTO.setCatgCode(currCategoryCode);
        GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
                .querySearchProps(reqDTO);
        if (gdsCatg2PropRelationRespDTO != null) {
            List<GdsPropRespDTO> gdsPropRespDTOList = gdsCatg2PropRelationRespDTO.getSearchProps();
            if (CollectionUtils.isNotEmpty(gdsPropRespDTOList)) {
                extraRespVO.setPropList(gdsPropRespDTOList);
            }
        }

        return extraRespVO;

    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page")
    public String page(Model model, GoodSearchPageReqVO vo) throws BusinessException {

        // 分类目录Facet设置
        vo.setCategoryPathFacet(false);

        SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);

        if (result.isSuccess()) {
            EcpBasePageRespVO<Map> pageRespVO = SearchUtil.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);

            // 异步刷新列表，需要showType参数
            model.addAttribute(SHOWTYPE_VO_ATTR, vo.getShowType());

            // 获取当前登录用户
            // BaseStaff staff = new BaseInfo().getStaff();
            model.addAttribute(ISLOGIN_VO_ATTR, StaffLocaleUtil.getStaff().getId() != 0);
            model.addAttribute("category", vo.getCategory());
            // 增加广告ID。
            if(StringUtil.isNotBlank(vo.getAdid())){
                model.addAttribute("adid",vo.getAdid());
            }
            return "/shop/search/page/search-resultlist";
        }

        throw new BusinessException(result.getMessage());

    }

    @RequestMapping(value = "/collect")
    @ResponseBody
    public Map<String, String> collect(@RequestParam("skuId")
                                               String skuId) throws BusinessException {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isBlank(skuId)) {
            map.put("flag", "0");
            map.put("message", "系统错误，单品主键为空");
        }
        GdsCollectReqDTO gdsCollectReqDTO = new GdsCollectReqDTO();
        try {
            Long _skuId = Long.parseLong(skuId);
            gdsCollectReqDTO.setSkuId(_skuId);

            this.gdsCollectRSV.addGdsCollect(gdsCollectReqDTO);
            map.put("flag", "1");
            map.put("message", "收藏成功");
        } catch (BusinessException e) {
            if (StringUtils.equals(e.getErrorCode(),
                    GdsErrorConstants.GdsCollect.ERROR_GOODS_COLLECT_251000)) {
                map.put("flag", "2");
                map.put("message", "已收藏该商品");
                return map;
            }
            throw e;
        } catch (NumberFormatException e) {
            map.put("flag", "0");
            map.put("message", "系统错误，单品主键不是Long类型");
        }

        return map;
    }

    @RequestMapping(value = "/hotkeyword")
    @ResponseBody
    public List<String> hotkeyword() throws BusinessException {
        List<String> hotkeywords = new ArrayList<String>();

        CmsHotSearchReqDTO cmsHotSearchReqDTO = new CmsHotSearchReqDTO();
        cmsHotSearchReqDTO.setPageNo(1);
        cmsHotSearchReqDTO.setPageSize(5);
        cmsHotSearchReqDTO.setSiteId(SiteLocaleUtil.getSite());
        cmsHotSearchReqDTO.setStatus(SearchConstants.STATUS_1);
        PageResponseDTO<CmsHotSearchRespDTO> pageResp = this.cmsHotSearchRSV
                .queryCmsHotSearchPage(cmsHotSearchReqDTO);
        if (CollectionUtils.isNotEmpty(pageResp.getResult())) {
            for (CmsHotSearchRespDTO cmsHotSearchRespDTO : pageResp.getResult()) {
                hotkeywords.add(cmsHotSearchRespDTO.getHotSearchName());
            }
        }

        // SecHotKeywordReqDTO secHotKeywordReqDTO = new SecHotKeywordReqDTO();
        // secHotKeywordReqDTO.setPageNo(1);
        // secHotKeywordReqDTO.setPageSize(5);
        // PageResponseDTO<SecHotKeywordRespDTO>
        // pageResp=this.secHotKeywordRSV.querySecHotKeywordPage(secHotKeywordReqDTO);
        // if(CollectionUtils.isNotEmpty(pageResp.getResult())){
        // for(SecHotKeywordRespDTO secHotKeywordRespDTO:pageResp.getResult()){
        // hotkeywords.add(secHotKeywordRespDTO.getKeyword());
        // }
        // }

        return hotkeywords;
    }

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

    @RequestMapping(value = "/category")
    @ResponseBody
    public CategoryClickAjaxRespVO<GoodSearchResult> category(GoodSearchPageReqVO vo)
            throws BusinessException {

        CategoryClickAjaxRespVO<GoodSearchResult> categoryClickAjaxRespVO = new CategoryClickAjaxRespVO<GoodSearchResult>();

        GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
        reqDTO.setCatgCode(vo.getCategory());
        GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
                .querySearchProps(reqDTO);
        if (gdsCatg2PropRelationRespDTO != null) {
            List<GdsPropRespDTO> gdsPropRespDTOList = gdsCatg2PropRelationRespDTO.getSearchProps();
            if (CollectionUtils.isNotEmpty(gdsPropRespDTOList)) {

                // 属性和属性值列表可为空
                categoryClickAjaxRespVO.setPropList(gdsPropRespDTOList);

            }
        }

        return categoryClickAjaxRespVO;

    }

    private static String WEB = "1";

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
        promRuleCheckDTO.setChannelValue(WEB);
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
        // 获取用户对商品的收藏信息
        GdsCollectReqDTO collectReqDTO = new GdsCollectReqDTO();
        collectReqDTO.setGdsId(goodSearchPromVO.getGdsId());
        collectReqDTO.setStaffId(collectReqDTO.getStaff().getId());
        collectReqDTO.setPageNo(0);
        collectReqDTO.setPageSize(1);
        PageResponseDTO<GdsCollectRespDTO> pageResponseDTO = gdsCollectRSV
                .queryGdsCollectRespDTOPagingByStaff(collectReqDTO);

        if(goodSearchPromVO.getIsLogin()){
            if ( pageResponseDTO.getCount() > 0) {
                bean.setIfHavFav("1");
            } else {
                bean.setIfHavFav("0");
            }
        }else{
            bean.setIfHavFav("0");
        }
        bean.setPromTypes(typeList);
        return bean;
    }
}
