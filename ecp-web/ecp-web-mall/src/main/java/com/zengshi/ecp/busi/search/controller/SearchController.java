package com.zengshi.ecp.busi.search.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.util.Hash;
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
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryTreeVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatlog2ShopRSV;
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
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

import net.sf.jasperreports.crosstabs.fill.calculation.ArbitraryRankComparator;

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

    private final static String SHOWTYPE_VO_ATTR = "showType";

    private final static String ISLOGIN_VO_ATTR = "isLogin";
    /**
     * 分类树目录ID前缀.
     */
    public static final String CATLOG_ID_PREFIX = "CATLOG-";

    /**
     * 分类树店铺ID前缀。
     */
    public static final String SHOP_ID_PREFIX = "SHOP-";
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;

    @Resource
    private IPromRSV promRSV;

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
    
    @Resource
    private IShopCollectRSV iShopCollectRSV;

    @Resource
	private ICustInfoRSV iCustInfoRSV;
    @Resource(name="gdsCatlog2ShopRSV")
    private IGdsCatlog2ShopRSV gdsCatlog2ShopRSV;
    @Resource(name = "gdsCatalog2SiteRSV")
    private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;
    @Resource(name = "shopCacheRSV")
    private IShopCacheRSV shopCacheRSV;
    @Resource(name = "gdsCatalogRSV")
    private IGdsCatalogRSV gdsCatalogRSV;
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

            EcpBasePageRespVO<Map> pageRespVO = SearchUtil.renderRespVO(vo, result);
            
            // 是否支持工厂库存.
            if(GdsUtils.isSupportFacStock()){
                model.addAttribute("supportFacStock", GdsUtils.isSupportFacStock());  
            }
            
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
              //搜索页面功能 需要传入用户
            	BaseStaff staff = StaffLocaleUtil.getStaff();
                gdsCategoryReqDTO.setStaff(staff);
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
                    //只有店铺搜索才显示店铺分类facet信息
                    if(StringUtils.isNotBlank(vo.getShopId())){
                        extraRespVO=addShopCatgFacetResult(extraRespVO,result);
                    }
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
            model.addAttribute("senior",vo.isSenior());
            String exam="";//高级搜索的考试培训分类的code 现网为12130
            try {
            	exam=SysCfgUtil.fetchSysCfg("SEARCH_CATEGORY_EXAM").getParaValue();
			} catch (Exception e) {
				// TODO: handle exception
				exam="12130";
			}
            model.addAttribute("examBook",exam);//高级搜索的考试培训分类的code 现网为12130
            
            Map<String, List<Map<String, String>>> rootLevelmap=extraRespVO.getCateInfoMap();
            List<Map<String,List<String>>> firAndSecCategorylist = new ArrayList<Map<String,List<String>>>();
          //遍历rootLevelmap中的值
            if(rootLevelmap!=null){
            	if(CollectionUtils.isNotEmpty(rootLevelmap.values())){
            		for (List<Map<String, String>> oneLevelmaplist : rootLevelmap.values()) {
            			if(CollectionUtils.isNotEmpty(oneLevelmaplist)){
            				for(Map<String, String> onemap : oneLevelmaplist){
            					String rootCatgCode = onemap.get("code");
            					//获取第二级分类;
            					GdsCategoryReqDTO catgSubQuery = new GdsCategoryReqDTO();
            					catgSubQuery.setCatgParent(rootCatgCode);
            					catgSubQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            					List<GdsCategoryRespDTO> secondCategorylist = this.gdsCategoryRSV.querySubCategory(catgSubQuery);
            					//获取当前分类的一级分类的名称
            					String firstName = onemap.get("topCateValue");
            					String firstNum = onemap.get("count");
            					//Map<纸质书_数量_code,List<图书_数量_code>>
            					Map<String,List<String>> firAndSecCategory = new HashMap<String,List<String>>();
            					String firstNameAndNum="";
            					if(result.isSuccess()){
            						firstNameAndNum = firstName+"_"+firstNum+"_"+rootCatgCode;
            					}
            					List<String> secondlist = new ArrayList<String>();
            					if(CollectionUtils.isNotEmpty(secondCategorylist)){
            						for(GdsCategoryRespDTO secondCate:secondCategorylist){
            							vo.setCategory(secondCate.getCatgCode());
            							vo.setRetResult(false);
            							String secondNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
            							String secondNameAndNum = secondCate.getCatgName()+"_"+secondNum+"_"+secondCate.getCatgCode();
            							if(!secondNum.equals("0")){
            								secondlist.add(secondNameAndNum);
            							}
            						}
            					}
            					firAndSecCategory.put(firstNameAndNum, secondlist);
            					firAndSecCategorylist.add(firAndSecCategory);
            					extraRespVO.setFirAndSecCategory(firAndSecCategory);
            					extraRespVO.setFirAndSecCategorylist(firAndSecCategorylist);
            				}
            			}
            		}
            		model.addAttribute(EXTRA_VO_ATTR, extraRespVO);
            	}
            	GdsCategoryReqDTO catePropertyReq = new GdsCategoryReqDTO();
            	catePropertyReq.setCatgLevel((short) 1);
            	catePropertyReq.setStatus(GdsConstants.Commons.STATUS_VALID);
            	catePropertyReq.setSiteId((long) 1);
            	List<GdsCategoryRespDTO> catePropertylist = this.gdsCategoryRSV.queryRootCategory(catePropertyReq);
            	extraRespVO.setProCategorylist(catePropertylist);
            	model.addAttribute(EXTRA_VO_ATTR, extraRespVO);
            }
            
            //平台入口分类 获取对应分类数量
            if(extraRespVO.getSearchType().equals("2")){
            	Map<String, List<Map<String,String>>> cateInfoMap_new = new HashMap<String, List<Map<String,String>>>();
            	String key;
            	Map<String, List<Map<String,String>>> cateInfoMap = extraRespVO.getCateInfoMap();
            	if(cateInfoMap!=null){
            		for (Map.Entry<String, List<Map<String,String>>> entry : cateInfoMap.entrySet()) {
            			List<Map<String,String>> newSecondlist = new ArrayList<Map<String,String>>();
            			key = entry.getKey();
            			List<Map<String,String>> secondlist = entry.getValue();
            			for(Map<String,String> map:secondlist){
            				//Map<String,String> newmap = new HashMap<String,String>();
            				String code = map.get("code");
            				vo.setCategory(code);
            				vo.setRetResult(false);
            				String secondNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
            				map.put("secondNum", secondNum);
            				newSecondlist.add(map);
            			}
            			cateInfoMap_new.put(key, secondlist);
            		}
            		extraRespVO.setCateInfoMap(cateInfoMap_new);
            	}
            	Map<String, Map<String,String>> topCateInfoMap_new = new HashMap<String, Map<String,String>>();
            	//String key_top;
            	Map<String, Map<String,String>> topCateInfoMap = extraRespVO.getTopCateInfoMap();
            	if(topCateInfoMap!=null){
            		for(Map.Entry<String, Map<String,String>> entry_top : topCateInfoMap.entrySet()){
            			String key_top = entry_top.getKey();
            			Map<String,String> top_map = entry_top.getValue();
            			String code = top_map.get("code");
            			vo.setCategory(code);
            			vo.setRetResult(false);
            			String firstNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
            			top_map.put("count", firstNum);
            			topCateInfoMap_new.put(key_top, top_map);
            		}
            	}
            	extraRespVO.setTopCateInfoMap(topCateInfoMap_new);
            	//获取分类路径
            	List<GdsCategoryRespDTO> pathGdsCategoryRespDTO = getCategoryPath(extraRespVO.getSearchCategory());
            	//获取下钻数据
            	List<String> downDataList = getDowncategory(extraRespVO.getParentCateCode());
            	//获取分类属性
            	List<String> proCategorylist = getSubcategory(extraRespVO.getSearchCategory());
            	
            	model.addAttribute("pathList", pathGdsCategoryRespDTO);
            	model.addAttribute("downDataList", downDataList);
            	model.addAttribute("proCategorylist", proCategorylist);
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
        
        // 是否支持工厂库存.
        if(GdsUtils.isSupportFacStock()){
            model.addAttribute("supportFacStock", GdsUtils.isSupportFacStock());  
        }
        
        
        // 分类目录Facet设置
        vo.setCategoryPathFacet(false);
//        vo.setPropertyGroup("[{\"propertyId\":\"priceCode\",\"propertyValueIds\":\"1\"}]");
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
            return "/search/page/search-resultlist";
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
    String keyword,@RequestParam("searchType")
    String searchType) throws BusinessException {

        SearchParam searchParam = new SearchParam();
        searchParam.setKeyword(keyword);
        SearchResult<CollationReuslt> result = null;
        
        //商品suggest
        if(StringUtils.equals(searchType, "1")){
            searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
            result = SearchFacade.suggest2(searchParam,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                    IGdsCatalog2SiteRSV.class)));
        }else if(StringUtils.equals(searchType, "2")){//店铺suggest
            searchParam.setCollectionName("shopcollection");
            result = SearchFacade.suggest2(searchParam);
        }
        
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
		CustInfoResDTO custInfoResDTO = null;
		ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
		if (custInfoReqDTO.getStaff().getId() == 0) {
			promRuleCheckDTO.setCustLevelValue(custInfoReqDTO.getStaff().getStaffLevelCode());
		} else {
			custInfoReqDTO.setId(custInfoReqDTO.getStaff().getId());
			custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
			promRuleCheckDTO.setCustLevelValue(custInfoResDTO.getCustLevelCode());
			promRuleCheckDTO.setAreaValue(custInfoResDTO.getProvinceCode());
			promRuleCheckDTO.setStaffId(custInfoResDTO.getId() + "");
			shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
		}
        
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
    @RequestMapping(value = "/refreshCategory")
    @ResponseBody
public String refreshCategory(Model model, GoodSearchPageReqVO vo) throws BusinessException {
    	vo.setCategoryPathFacet(false);
        SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);
        if (result.isSuccess()) {
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
                gdsCategoryReqDTO.setIsContainSub(true);
              //搜索页面功能 需要传入用户
            	BaseStaff staff = StaffLocaleUtil.getStaff();
                gdsCategoryReqDTO.setStaff(staff);
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

                    //只有店铺搜索才显示店铺分类facet信息
                    if(StringUtils.isNotBlank(vo.getShopId())){
                        extraRespVO=addShopCatgFacetResult(extraRespVO,result);
                    }
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
//            model.addAttribute(EXTRA_VO_ATTR, extraRespVO);
//            model.addAttribute("category", vo.getCategory());
         // 获取当前选中的一级和二级分类
            String targetCatgCode = vo.getCategory();
            GdsCategoryReqDTO catgQuery = new GdsCategoryReqDTO();
            catgQuery.setCatgCode(targetCatgCode);
            GdsCategoryRespDTO targetCatg = this.gdsCategoryRSV.queryGdsCategoryByPK(catgQuery);
            
            if(null != targetCatg){
            	String catgPath = targetCatg.getCatgPath();
            	String rootCatgCode = exetraRootCatgCode(catgPath);
            	String secondCatgCode = exetraRootCatgCodeForSecond(catgPath);
            	// TODO 
            	GdsCategoryReqDTO catgSubQuery = new GdsCategoryReqDTO();
            	catgSubQuery.setCatgParent(rootCatgCode);
            	catgSubQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            	//获取第二级分类;
            	List<GdsCategoryRespDTO> secondCategorylist = this.gdsCategoryRSV.querySubCategory(catgSubQuery);
            	//获取当前分类的一级分类的名称
            	GdsCategoryReqDTO firstCatgQuery = new GdsCategoryReqDTO();
            	firstCatgQuery.setCatgCode(rootCatgCode);
            	GdsCategoryRespDTO firstCatgNameDTO = this.gdsCategoryRSV.queryGdsCategoryByPK(firstCatgQuery);
            	String firstName = firstCatgNameDTO.getCatgName();
            	//Map<纸质书_数量_code,List<图书_数量_code>>
            	Map<String,List<String>> firAndSecCategory = new HashMap<String,List<String>>();
            	String firstNameAndNum="";
            	if(result.isSuccess()){
            		String allNum=String.valueOf(result.getNumFound());
            		firstNameAndNum = firstName+"_"+allNum+"_"+rootCatgCode;
            	}
            	List<String> secondlist = new ArrayList<String>();
            	for(GdsCategoryRespDTO secondCate:secondCategorylist){
            		if(targetCatgCode.equals(secondCate.getCatgCode()) || targetCatgCode.equals(rootCatgCode)){
            			vo.setCategory(secondCate.getCatgCode());
            			String secondNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
            			String secondNameAndNum = secondCate.getCatgName()+"_"+secondNum+"_"+secondCate.getCatgCode();
            			secondlist.add(secondNameAndNum);
            		}else if(secondCate.getCatgCode().equals(secondCatgCode)){
            			String secondNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
            			String secondNameAndNum = secondCate.getCatgName()+"_"+secondNum+"_"+secondCate.getCatgCode();
            			secondlist.add(secondNameAndNum);
            		};
            	}
            	firAndSecCategory.put(firstNameAndNum, secondlist);
            	extraRespVO.setFirAndSecCategory(firAndSecCategory);
            	//获取分类属性
            	GdsCategoryReqDTO catgProQuery = new GdsCategoryReqDTO();
            	catgProQuery.setCatgParent(vo.getCategory());
            	catgProQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            	List<GdsCategoryRespDTO> proCategorylist = gdsCategoryRSV.querySubCategory(catgProQuery);
            	extraRespVO.setProCategorylist(proCategorylist);
            	
            	GdsCategoryReqDTO currentCatgQuery = new GdsCategoryReqDTO();
            	currentCatgQuery.setCatgCode(vo.getCategory());
            	GdsCategoryRespDTO currentCatgNameDTO = gdsCategoryRSV.queryGdsCategoryByPK(currentCatgQuery);
            	String currentCatgPath = currentCatgNameDTO.getCatgPath();
            	String[] arryCode = currentCatgPath.replace("<", "").split(">");
            	List<GdsCategoryRespDTO> pathCategorylist = new ArrayList<GdsCategoryRespDTO>();
            	if(arryCode!=null){
            		for(int i=0;i<arryCode.length;i++){
            			GdsCategoryReqDTO pathCategoryQuery = new GdsCategoryReqDTO();
            			pathCategoryQuery.setCatgCode(arryCode[i]);
            			pathCategoryQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            			GdsCategoryRespDTO pathCategoryDto = gdsCategoryRSV.queryGdsCategoryByPK(pathCategoryQuery);
            			pathCategorylist.add(pathCategoryDto);
            		}
            	}
            	extraRespVO.setPathCategorylist(pathCategorylist);
            	
            }
            
         

            String tree = JSONObject.toJSONString(extraRespVO);
            return tree;

        }

        throw new BusinessException(result.getMessage());
    }
	/**
     * 
     * 分类树异步获取数据。
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/asyncData/selector/getNodes")
    @ResponseBody
    public String getSelectorNodes(Model model,CategoryVO reqVO,HttpServletResponse response,GoodSearchPageReqVO searchVo)throws Exception{
//        LogUtil.info(MODULE,"获取分类树型节点,参数"+ToStringBuilder.reflectionToString(reqVO));
        List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
        // 判断是否有传分类类型参数
        if(StringUtil.isBlank(reqVO.getCatgType())){
//            LogUtil.warn(MODULE, "catgType为空,返回空数据!");
            return JSONObject.toJSONString(trees);
        }
        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        
        //reqDTO.setIsContainSub(true);
        // 是否需要权限验证。如果入参为FALSE或者false则表示需要进行权限验证
        boolean isNeedAuth = Boolean.FALSE.toString().equalsIgnoreCase(reqVO.getIgnoreDataAuth());
        //List<Long> catlogIdsWithCurrentUser = fetchCatlogIdsByCurrentUser(reqVO.getShopIds());//为卖家中心登入时要传的参数
        List<Long> catlogIdsWithCurrentUser = null;
        List<Long> shopIdsWithCurrentUser = fetchShopIdsByCurrentUser(reqVO.getShopIds());
        
        reqVO.setCatgCode("");
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "", false);
        String id = reqVO.getId();
//        String id = "3041";
        // 节点ID与父节点ID同为空，为目录树加载（即分类树初始化显示顶级目录）
        if(StringUtil.isBlank(reqVO.getId())){
            switch (reqVO.getCatgType()) {
            // 平台分类根节点为目录信息,添加目录树节点数据。
            case GdsConstants.GdsCategory.CATG_TYPE_1:
            	//reqDTO.setCatgCode("3042");
            	reqDTO.setCatgCode(searchVo.getCatgCode());
            	//reqDTO.setCatgLevel((short) 2);
                // 不显示目录
                if(CategoryVO.SHOW_ROOT_NO == reqVO.getShowRoot()){
                    // 目录ID不为空,查询分类信息.
                    List<GdsCategoryRespDTO> catgLst = null;
                    // 查询根分类。
                    // catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                    //包含的分类编码
                    //reqDTO.setIncludeCatgCode(includeCatgCode);
                 // 设置目录ID范围限定条件.
                 //   reqDTO.setCatlogIds(catlogIdsWithCurrentUser);
//                    if(isNeedAuth){
//                    	catgLst = gdsCategoryRSV.queryRootCategoryWithAuth(reqDTO);
//                    }else{
//                    	catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
//                    }
                    String exam="";//高级搜索的考试培训分类的code 现网为12130
                    try {
                    	exam=SysCfgUtil.fetchSysCfg("SEARCH_CATEGORY_EXAM").getParaValue();
        			} catch (Exception e) {
        				exam="12130";
        			}
                    if(searchVo.getCatgCode().equals(exam)){//"2349"
                    	GdsCategoryReqDTO catgQuery = new GdsCategoryReqDTO();
                        catgQuery.setCatgCode(exam);
                        GdsCategoryRespDTO catgDto = this.gdsCategoryRSV.queryGdsCategoryByPK(catgQuery);
                        reqDTO.setCatgLevel(catgDto.getCatgLevel());
                    	catgLst = gdsCategoryRSV.queryExaCategory(reqDTO);
                    }else{
                    	catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                    }
                    convert2TreeList(trees, catgLst,reqDTO);
                }else{
                    List<GdsCatalogRespDTO> catlogs = null;
                    if(null != reqVO.getCatlogId()){
                        catlogs = loadCatalogRespDTO(reqVO);
                    }else if(null != reqVO.getSiteId()){
                        GdsCatalog2SiteReqDTO c2sReqDTO =  new GdsCatalog2SiteReqDTO();
                        c2sReqDTO.setSiteId(reqVO.getSiteId());
                        c2sReqDTO.setCatalogStatus(GdsConstants.Commons.STATUS_VALID);
                        catlogs = gdsCatalog2SiteRSV.queryRelationBySiteId(c2sReqDTO);
                    }
                    if(CollectionUtils.isNotEmpty(catlogs)){
                        for(GdsCatalogRespDTO catlogRespDTO : catlogs){
                        	if(catlogIdsWithCurrentUser.contains(catlogRespDTO.getId())){
	                            CategoryTreeVO vo = buildCatalogNodeItem(catlogRespDTO, null);
	                            trees.add(vo);
                        	}
                        }
                    }
                }
                break;
                // 店铺分类根节点为目录信息。
            case GdsConstants.GdsCategory.CATG_TYPE_2:
                // 不显示店铺.
                if(CategoryVO.SHOW_ROOT_NO == reqVO.getShowRoot()){
                    // 目录ID不为空,查询分类信息.
                    List<GdsCategoryRespDTO> catgLst = null;
                    reqDTO.setShopIdLst(shopIdsWithCurrentUser);
                    // 查询根分类。
                    catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                    convert2TreeList(trees, catgLst,reqDTO);
                }else{
                    // 获取店铺信息.
                    Map<Long, ShopInfoResDTO> map = shopCacheRSV.getCacheShop();
                    if(MapUtils.isNotEmpty(map)){
                        Set<Entry<Long, ShopInfoResDTO>> entrySet = map.entrySet();
                        for (Iterator<Entry<Long, ShopInfoResDTO>> iterator = entrySet.iterator(); iterator
                                .hasNext();) {
                            Entry<Long, ShopInfoResDTO> entry = (Entry<Long, ShopInfoResDTO>) iterator
                                    .next();
                            ShopInfoResDTO dto = entry.getValue();
                            if(null != dto && GdsUtils.isEqualsValid(dto.getShopStatus())){
                                if((null != reqVO.getShopId() && reqVO.getShopId().equals(dto.getId()))
                                		||(null != reqVO.getShopIds() && reqVO.getShopIds().contains(dto.getId()))
                                		){
                                	if(shopIdsWithCurrentUser.contains(dto.getId())){
                                		CategoryTreeVO vo = buildShopNodeItem(dto, null);
                                		trees.add(vo);
                                	}
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
            }
        }else{
            // 目录ID不为空,查询分类信息.
            List<GdsCategoryRespDTO> catgLst = null;
            
            if(id.startsWith(CATLOG_ID_PREFIX)){
                id = id.replace(CATLOG_ID_PREFIX, "");
                reqDTO.setCatlogId(Long.valueOf(id));
                // 查询根分类。
                if(isNeedAuth){
                  catgLst = gdsCategoryRSV.queryRootCategoryWithAuth(reqDTO);
                }else{
                	catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                }
                
            }else if(id.startsWith(SHOP_ID_PREFIX)){
                id = id.replace(SHOP_ID_PREFIX, "");
                reqDTO.setShopId(Long.valueOf(id));
                // 查询根分类。
                catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
            }else{
                // 否则加载子分类。
                reqDTO.setCatgParent(id);
                List<String> includeCatgCode = new ArrayList<String>();
                includeCatgCode.add("1200");
                includeCatgCode.add("1201");
                if(id.equals("1199")){
                	reqDTO.setIncludeCatgCode(includeCatgCode);
                }
                if(isNeedAuth){
                   catgLst = gdsCategoryRSV.querySubCategoryWithAuth(reqDTO);
                }else{
                	catgLst = gdsCategoryRSV.querySubCategory(reqDTO);
                }
                
            }
            convert2TreeList(trees,catgLst,reqDTO);
        }
        String json = JSONObject.toJSONString(trees);
//        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }
private List<Long> fetchCatlogIdsByCurrentUser(List<Long> selectedShopIds){
        
    	List<Long> catlogIds = new ArrayList<Long>();
    	SellerResDTO srd =  SellerLocaleUtil.getSeller();
    	List<ShopInfoResDTO> shopLst = srd.getShoplist();
    	if(CollectionUtils.isNotEmpty(shopLst)){
    		for (Iterator<ShopInfoResDTO> iterator = shopLst.iterator(); iterator.hasNext();) {
				ShopInfoResDTO shopInfoResDTO = iterator.next();
				if(CollectionUtils.isNotEmpty(selectedShopIds)){
    				if(!selectedShopIds.contains(shopInfoResDTO.getId())){
    					continue;
    				}
    			}
				Long shopId = shopInfoResDTO.getId();
				GdsCatlog2ShopReqDTO relationQuery = new GdsCatlog2ShopReqDTO();
		    	relationQuery.setShopId(shopId);
		    	LongListRespDTO longListResp = gdsCatlog2ShopRSV.queryGdsCatlog2ShopRespDTOByShopId(relationQuery);
		    	if(null != longListResp && CollectionUtils.isNotEmpty(longListResp.getLst())){
		    		for(Long l : longListResp.getLst()){
		    			if(!catlogIds.contains(l)){
		    				catlogIds.add(l);
		    			}
		    		}
		    	}
			}
    	}
    	return catlogIds;
    }
	private List<Long> fetchShopIdsByCurrentUser(List<Long> selectedShopIds){
	    List<Long> resultLst = new ArrayList<Long>();
	    List<Long> shopIds = new ArrayList<Long>();
	   	SellerResDTO srd =  SellerLocaleUtil.getSeller();
	   	List<ShopInfoResDTO> shopLst = srd.getShoplist();
	   	if(CollectionUtils.isNotEmpty(shopLst)){
	   		for (Iterator<ShopInfoResDTO> iterator = shopLst.iterator(); iterator.hasNext();) {
					ShopInfoResDTO shopInfoResDTO = iterator.next();
					Long shopId = shopInfoResDTO.getId();
	    			if(!shopIds.contains(shopId)){
	    				shopIds.add(shopId);
	    			}
	    		}
	   	}
	   	
	   	if(CollectionUtils.isNotEmpty(selectedShopIds)){
	   		for(Long sid : selectedShopIds){
	   			if(shopIds.contains(sid)){
	   				resultLst.add(sid);
	   			}
	   		}
	   	}else{
	   		return shopIds;
	   	}
	   	
	   	
	   	return resultLst;
	}
	/*
     * 
     * convert2TreeList:分类信息转换成分类树列表. <br/>
     * 
     * @author liyong7
     * 
     * @param trees
     * 
     * @param catgLst
     * 
     * @since JDK 1.6
     */
    private void convert2TreeList(List<CategoryTreeVO> trees, List<GdsCategoryRespDTO> catgLst,
            GdsCategoryReqDTO categoryReqDTO) {
        if (CollectionUtils.isNotEmpty(catgLst)) {
            for (GdsCategoryRespDTO respDTO : catgLst) {
                CategoryTreeVO vo = new CategoryTreeVO();
                vo.setId(respDTO.getCatgCode());
                vo.setName(respDTO.getCatgName());
                vo.setIsRoot(false);
                if (null != respDTO.getCatlogId()) {
                    vo.setCatlogId(respDTO.getCatlogId().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setpId(CATLOG_ID_PREFIX + respDTO.getCatlogId());
                    } else {
                        vo.setpId(respDTO.getCatgParent());
                    }
                } else if (null != respDTO.getShopId()) {
                    vo.setShopId(respDTO.getShopId().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setpId(SHOP_ID_PREFIX + respDTO.getShopId());
                    } else {
                        vo.setpId(respDTO.getCatgParent());
                    }
                }
                vo.setCatgLevel(respDTO.getCatgLevel());
                //如果指定显示最大节点
                if (categoryReqDTO.getMaxShowNode() != null || !"".equals(categoryReqDTO.getMaxShowNode())) {
                    if (respDTO.getCatgLevel().toString().equals(categoryReqDTO.getMaxShowNode())) {
                        vo.setIsParent(false);
                    }else{
                        vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true
                                : false);
                    }
                } else {
                    vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true
                            : false);
                }
                /*
                 * if(StringUtil.isNotBlank(respDTO.getMediaUuid())){
                 * vo.setIcon(new AiToolUtil().genImageUrl(respDTO.getMediaUuid(), "18x18")); }
                 */
                trees.add(vo);
            }
        }
    }
    /*
     * 
     * 加载出所有有效目录信息.
     * 
     * @author liyong7
     * 
     * @return
     * 
     * @since JDK 1.6
     */
    private List<GdsCatalogRespDTO> loadCatalogRespDTO(CategoryVO reqVO) {
        GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
        reqDTO.setPageSize(Integer.MAX_VALUE);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setId(reqVO.getCatlogId());
        PageResponseDTO<GdsCatalogRespDTO> page = gdsCatalogRSV
                .queryGdsCatalogRespDTOPaging(reqDTO);
        return page.getResult();
    }
    /*
     * 
     * 创建目录树节点.
     * 
     * @author liyong7
     * 
     * @param catlogRespDTO
     * 
     * @return
     * 
     * @since JDK 1.6
     */
    private CategoryTreeVO buildCatalogNodeItem(GdsCatalogRespDTO catlogRespDTO, Boolean isOpen) {
        CategoryTreeVO vo = new CategoryTreeVO();
        vo.setId(CATLOG_ID_PREFIX + catlogRespDTO.getId());
        vo.setName(catlogRespDTO.getCatlogName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        if (null != catlogRespDTO.getId()) {
            vo.setCatlogId(catlogRespDTO.getId().toString());
        }
        vo.setIsRoot(true);
        vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
    }
    /*
     * 
     * 构建店铺节点.
     * 
     * @author liyong7
     * 
     * @param dto
     * 
     * @return
     * 
     * @since JDK 1.6
     */
    private CategoryTreeVO buildShopNodeItem(ShopInfoResDTO dto, Boolean isOpen) {
        CategoryTreeVO vo = new CategoryTreeVO();
        vo.setId(SHOP_ID_PREFIX + dto.getId());
        vo.setName(dto.getShopName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        vo.setIsRoot(true);
        //vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
    }
    
    /**
     * 根据分类族谱提取根分类编码。
     * @param catgPath
     * @return
     */
    private String exetraRootCatgCode(String catgPath) {
		// TODO Auto-generated method stub
		if(StringUtil.isNotBlank(catgPath)){
			catgPath = catgPath.replace("<", "");
			String[] ary = catgPath.split(">");
			return ary[0];
		}
		return null;
	}
    /**
     * 根据分类族谱提取1级分类编码。
     * @param catgPath
     * @return
     */
    private String exetraRootCatgCodeForSecond(String catgPath) {
		// TODO Auto-generated method stub
		if(StringUtil.isNotBlank(catgPath)){
			catgPath = catgPath.replace("<", "");
			String[] ary = catgPath.split(">");
			if(ary.length>1){
				return ary[1];
			}
		}
		return null;
	}
    
    @RequestMapping(value = "/getSameLevelCategory")
    @ResponseBody
public String getSameLevelCategory(GoodSearchPageReqVO vo) throws BusinessException {
    	vo.setCategoryPathFacet(false);
        SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);
        if (result.isSuccess()) {
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
                gdsCategoryReqDTO.setIsContainSub(true);
              //搜索页面功能 需要传入用户
            	BaseStaff staff = StaffLocaleUtil.getStaff();
                gdsCategoryReqDTO.setStaff(staff);
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

                    //只有店铺搜索才显示店铺分类facet信息
                    if(StringUtils.isNotBlank(vo.getShopId())){
                        extraRespVO=addShopCatgFacetResult(extraRespVO,result);
                    }
                }
//                extraRespVO=addPlateCatgFacetResult(extraRespVO,result);

            } else {

                // 指定了查询分类的情况下，虽然查询结果为空，但是分类目录信息任然需要展示
                if (gdsCategoryRespDTO != null) {
                    extraRespVO = this.addCategoryQueryResult(extraRespVO, vo.getCategory(),
                            gdsCategoryRespDTO.getCatgName(), gdsCategoryRespDTO.getCatgParent(),
                            "0",vo.getShopId());
                }

            }

            extraRespVO.setType("1");//搜商品
//            model.addAttribute(EXTRA_VO_ATTR, extraRespVO);
//            model.addAttribute("category", vo.getCategory());
         // 获取当前选中的一级和二级分类
            String targetCatgCode = vo.getCategory();
            GdsCategoryReqDTO catgQuery = new GdsCategoryReqDTO();
            catgQuery.setCatgCode(targetCatgCode);
            GdsCategoryRespDTO targetCatg = this.gdsCategoryRSV.queryGdsCategoryByPK(catgQuery);
            
            if(null != targetCatg){
            	
            	//获取分类属性
            	GdsCategoryReqDTO catgProQuery = new GdsCategoryReqDTO();
            	catgProQuery.setCatgParent(vo.getCategory());
            	catgProQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            	List<GdsCategoryRespDTO> proCategorylist = gdsCategoryRSV.querySubCategory(catgProQuery);
            	extraRespVO.setProCategorylist(proCategorylist);
            	//获取分类路径
            	GdsCategoryReqDTO currentCatgQuery = new GdsCategoryReqDTO();
            	currentCatgQuery.setCatgCode(vo.getCategory());
            	GdsCategoryRespDTO currentCatgNameDTO = gdsCategoryRSV.queryGdsCategoryByPK(currentCatgQuery);
            	String currentCatgPath = currentCatgNameDTO.getCatgPath();
            	String[] arryCode = currentCatgPath.replace("<", "").split(">");
            	List<GdsCategoryRespDTO> pathCategorylist = new ArrayList<GdsCategoryRespDTO>();
            	if(arryCode!=null){
            		for(int i=0;i<arryCode.length-1;i++){
            			GdsCategoryReqDTO pathCategoryQuery = new GdsCategoryReqDTO();
            			pathCategoryQuery.setCatgCode(arryCode[i]);
            			pathCategoryQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            			GdsCategoryRespDTO pathCategoryDto = gdsCategoryRSV.queryGdsCategoryByPK(pathCategoryQuery);
            			pathCategorylist.add(pathCategoryDto);
            		}
            	}
            	extraRespVO.setPathCategorylist(pathCategorylist);
            	String downCode = extraRespVO.getParentCateCode();
            	List<GdsCategoryRespDTO> downCategorylist=new ArrayList<GdsCategoryRespDTO>();
            	if(StringUtil.isNotBlank(downCode)){
            		GdsCategoryReqDTO downCatgQuery = new GdsCategoryReqDTO();
            		downCatgQuery.setCatgParent(downCode);
            		downCatgQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            		downCategorylist = gdsCategoryRSV.querySubCategory(downCatgQuery);
            	}else{
            		GdsCategoryReqDTO downCatgQuery = new GdsCategoryReqDTO();
            		downCatgQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
            		downCatgQuery.setSiteId((long) 1);
            		downCategorylist = gdsCategoryRSV.queryRootCategory(downCatgQuery);
            	}
            	//下钻效果数据
            	List<String> downCatelist = new ArrayList<String>();
            	vo.setRetResult(false);
            	if(CollectionUtils.isNotEmpty(downCategorylist)){
            		for(GdsCategoryRespDTO downCate:downCategorylist){
            			vo.setCategory(downCate.getCatgCode());
            			String downNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
            			if(!downNum.equals("0")){
            				String secondNameAndNum = downCate.getCatgName()+"_"+downNum+"_"+downCate.getCatgCode();
            				downCatelist.add(secondNameAndNum);
            			}
            		}
            	}
            	extraRespVO.setDownCatelist(downCatelist);
            }
            
         

            String tree = JSONObject.toJSONString(extraRespVO);
            return tree;

        }

        throw new BusinessException(result.getMessage());
    }
    
    @RequestMapping(value = "/getCurrentcategory")
    @ResponseBody
public String getCurrentcategory(GoodSearchPageReqVO vo) throws BusinessException {
    	GdsCategoryReqDTO catgProQuery = new GdsCategoryReqDTO();
    	catgProQuery.setCatgParent(vo.getCategory());
    	catgProQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
    	List<GdsCategoryRespDTO> proCategorylist_temp = gdsCategoryRSV.querySubCategory(catgProQuery);
    	List<String> proCategorylist = new ArrayList<String>();
    	if(CollectionUtils.isNotEmpty(proCategorylist_temp)){
    		for(int i=0; i<proCategorylist_temp.size();i++){
    			vo.setCategory(proCategorylist_temp.get(i).getCatgCode());
    			String downNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
    			if(!downNum.equals("0")){
    				//proCategorylist.remove(i);
    				String subNameNumCode = proCategorylist_temp.get(i).getCatgName()+"_"+downNum+"_"+proCategorylist_temp.get(i).getCatgCode();
    				proCategorylist.add(subNameNumCode);
    			}
    		}
    	}
    	String proCategorylistStr = JSONObject.toJSONString(proCategorylist);
    	return proCategorylistStr;
    }
    
    @RequestMapping(value = "/getsubcategory")
    @ResponseBody
public String getsubcategory(GoodSearchPageReqVO vo) throws BusinessException {
    	//获取分类路径
    	GdsCategoryReqDTO subCatgQuery = new GdsCategoryReqDTO();
    	subCatgQuery.setCatgCode(vo.getCategory());
    	GdsCategoryRespDTO subCatgNameDTO = gdsCategoryRSV.queryGdsCategoryByPK(subCatgQuery);
    	String firstNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
    	String str = subCatgNameDTO.getCatgName()+"_"+firstNum+"_"+subCatgNameDTO.getCatgCode();
    	//下钻效果数据
    	String downCode = subCatgNameDTO.getCatgParent();
//    	GdsCategoryReqDTO parentQuery = new GdsCategoryReqDTO();
//    	parentQuery.setCatgCode(downCode);
//    	parentQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
//    	GdsCategoryRespDTO pathCategoryDto = gdsCategoryRSV.queryGdsCategoryByPK(parentQuery);
    	
    	GdsCategoryReqDTO downCatgQuery = new GdsCategoryReqDTO();
    	List<GdsCategoryRespDTO> downCategorylist=new ArrayList<GdsCategoryRespDTO>();
    	if(StringUtil.isNotBlank(downCode)){
    		
    		downCatgQuery.setCatgParent(downCode);
    		downCatgQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
    		downCategorylist = gdsCategoryRSV.querySubCategory(downCatgQuery);
    	}else{
    		downCatgQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
    		downCatgQuery.setSiteId((long) 1);
    		downCategorylist = gdsCategoryRSV.queryRootCategory(downCatgQuery);
    	}
    	List<String> downCatelist = new ArrayList<String>();
    	if(CollectionUtils.isNotEmpty(downCategorylist)){
    		for(GdsCategoryRespDTO downCate:downCategorylist){
    			vo.setCategory(downCate.getCatgCode());
    			String downNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
    			if(!downNum.equals("0")){
    				String secondNameAndNum = downCate.getCatgName()+"_"+downNum+"_"+downCate.getCatgCode();
    				downCatelist.add(secondNameAndNum);
    			}
    		}
    	}
    	Map<String,List<String>> map = new HashMap<String,List<String>>();
    	map.put(str, downCatelist);
    	return JSONObject.toJSONString(map);
    }
    
    @RequestMapping(value = "/getParentcategory")
    @ResponseBody
    public GdsCategoryRespDTO getParentcategory(GoodSearchPageReqVO vo) throws BusinessException {
    	GdsCategoryReqDTO parentCatgQuery = new GdsCategoryReqDTO();
    	parentCatgQuery.setCatgCode(vo.getCategory());
    	GdsCategoryRespDTO parentCodeDto_temp= gdsCategoryRSV.queryGdsCategoryByPK(parentCatgQuery);
    	parentCatgQuery.setCatgCode(parentCodeDto_temp.getCatgParent());
    	GdsCategoryRespDTO parentCodeDto= gdsCategoryRSV.queryGdsCategoryByPK(parentCatgQuery);
    	return parentCodeDto;
    }
    /**
     * 获取分类属性（获取当前节点的孩子）
     */
    private List<String> getSubcategory(String code) throws BusinessException {
    	GdsCategoryReqDTO catgProQuery = new GdsCategoryReqDTO();
    	catgProQuery.setCatgParent(code);
    	catgProQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
    	//List<GdsCategoryRespDTO> proCategorylist = gdsCategoryRSV.querySubCategory(catgProQuery);
    	List<GdsCategoryRespDTO> proCategorylist_temp = gdsCategoryRSV.querySubCategory(catgProQuery);
    	List<String> proCategorylist = new ArrayList<String>();
    	if(CollectionUtils.isNotEmpty(proCategorylist_temp)){
    		for(int i=0; i<proCategorylist_temp.size();i++){
    			GoodSearchPageReqVO vo = new GoodSearchPageReqVO();
    			vo.setRetResult(false);
    			vo.setCategory(proCategorylist_temp.get(i).getCatgCode());
    			String downNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
    			if(!downNum.equals("0")){
    				String subNameNumCode = proCategorylist_temp.get(i).getCatgName()+"_"+downNum+"_"+proCategorylist_temp.get(i).getCatgCode();
    				proCategorylist.add(subNameNumCode);
    			}
    		}
    	}
    	return proCategorylist;
    }
    /**
     * 获取分类全路径
     */
    private List<GdsCategoryRespDTO> getCategoryPath(String code) throws BusinessException {
    	GdsCategoryReqDTO currentCatgQuery = new GdsCategoryReqDTO();
    	currentCatgQuery.setCatgCode(code);
    	GdsCategoryRespDTO currentCatgNameDTO = gdsCategoryRSV.queryGdsCategoryByPK(currentCatgQuery);
    	String currentCatgPath = currentCatgNameDTO.getCatgPath();
    	String[] arryCode = currentCatgPath.replace("<", "").split(">");
    	List<GdsCategoryRespDTO> pathCategorylist = new ArrayList<GdsCategoryRespDTO>();
    	if(arryCode!=null){
    		for(int i=0;i<arryCode.length-1;i++){
    			GdsCategoryReqDTO pathCategoryQuery = new GdsCategoryReqDTO();
    			pathCategoryQuery.setCatgCode(arryCode[i]);
    			pathCategoryQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
    			GdsCategoryRespDTO pathCategoryDto = gdsCategoryRSV.queryGdsCategoryByPK(pathCategoryQuery);
    			pathCategorylist.add(pathCategoryDto);
    		}
    	}
    	return pathCategorylist;
    }
    /**
     * 获取分类下钻数据
     */
    private List<String> getDowncategory(String parentCode) throws BusinessException {
    	List<GdsCategoryRespDTO> downCategorylist=new ArrayList<GdsCategoryRespDTO>();
    	if(StringUtil.isNotBlank(parentCode)){
    		GdsCategoryReqDTO downCatgQuery = new GdsCategoryReqDTO();
    		downCatgQuery.setCatgParent(parentCode);
    		downCatgQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
    		downCategorylist = gdsCategoryRSV.querySubCategory(downCatgQuery);
    	}else{
    		GdsCategoryReqDTO downCatgQuery = new GdsCategoryReqDTO();
    		downCatgQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
    		downCatgQuery.setSiteId((long) 1);
    		downCategorylist = gdsCategoryRSV.queryRootCategory(downCatgQuery);
    	}
    	//下钻效果数据
    	GoodSearchPageReqVO vo = new GoodSearchPageReqVO();
    	vo.setRetResult(false);
    	List<String> downCatelist = new ArrayList<String>();
    	if(CollectionUtils.isNotEmpty(downCategorylist)){
    		for(GdsCategoryRespDTO downCate:downCategorylist){
    			vo.setCategory(downCate.getCatgCode());
    			String downNum =String.valueOf(SearchUtil.searchGood(vo).getNumFound());
    			if(!downNum.equals("0")){
    				String secondNameAndNum = downCate.getCatgName()+"_"+downNum+"_"+downCate.getCatgCode();
    				downCatelist.add(secondNameAndNum);
    			}
    		}
    	}
    	return downCatelist;
    }
    
    /**
     * 获取分类属性和属性值
     */
    @RequestMapping(value = "/getGdsProp")
    @ResponseBody
    private List<GdsPropRespDTO> getGdsProp(GoodSearchPageReqVO vo) throws BusinessException {
    	GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
        reqDTO.setCatgCode(vo.getCategory());
        GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
                .querySearchProps(reqDTO);
        if (gdsCatg2PropRelationRespDTO != null) {
            List<GdsPropRespDTO> gdsPropRespDTOList = gdsCatg2PropRelationRespDTO
                    .getSearchProps();
            if (CollectionUtils.isNotEmpty(gdsPropRespDTOList)) {
            	return gdsPropRespDTOList;
            }else{
            	return null;
            }
        }else{
        	return null;
        }
    }
}
