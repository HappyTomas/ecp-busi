package com.zengshi.ecp.busi.shop.shopIndex.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.busi.shop.shopIndex.vo.ShopInfoVO;
import com.zengshi.ecp.busi.shop.shopSearch.controller.ShopSearchUtil;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchPageReqVO;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchResult;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月9日下午2:18:17 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhuqr
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/shopIndex")
public class ShopIndexController extends EcpBaseController {
	private String MODUAL = ShopIndexController.class.getName();
	private String URL = "/shop/shopIndex";
    private final static String ISLOGIN_VO_ATTR = "isLogin"; 
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
	
	  /**
     * 
     * init:(页面入口). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
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
       
       SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);
     
	  
       if (result.isSuccess()) {
    	     /* 搜索列表 begin*/
		     EcpBasePageRespVO<Map> pageRespVO = SearchUtil.renderRespVO(vo, result);
		   	 super.addPageToModel(model, pageRespVO);
		 	 /* 搜索列表 end */
		   	 
		 	 /* 店铺信息 begin */
		 	 ShopInfoVO shopInfoVO=new ShopInfoVO();
		   	 ShopInfoResDTO shopInfoResDTO=iShopInfoRSV.findShopInfoByShopID(Long.valueOf(vo.getShopId()));
		   	 ObjectCopyUtil.copyObjValue(shopInfoResDTO, shopInfoVO, null, false);
		 
		 	 
		 	 //获取好评率
			 GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
		 	 gdsEvalReqDTO.setShopId(Long.valueOf(vo.getShopId()));
		 	 shopInfoVO.setEvalRate(iGdsEvalRSV.calCulateShopGoodEvalRate(gdsEvalReqDTO));
		 	
		 	 //获取宝贝数量
		 	 GoodSearchPageReqVO goodSearchPageReqVO=new GoodSearchPageReqVO();
		     goodSearchPageReqVO.setShopId(vo.getShopId());
		     SearchResult<GoodSearchResult> searchResult =SearchUtil.searchGood(goodSearchPageReqVO);
		     shopInfoVO.setFavNum(searchResult.getNumFound());
		    
		     //获取销售数量
		     RGoodSaleRequest gGoodSaleRequest=new RGoodSaleRequest();
		     gGoodSaleRequest.setShopId(Long.valueOf(vo.getShopId()));
		     // 为保持搜索引擎店铺销售总量与店铺主页销售总量一至,店铺主页销售总量从搜索引擎取值.
		     //Long saleNum=iReportGoodPayedRSV.querySumBuyNumByShopId(gGoodSaleRequest);
		     //shopInfoVO.setSaleNum(saleNum);
		     ShopSearchPageReqVO shopSearchVO = new ShopSearchPageReqVO();
		     shopSearchVO.setId(vo.getShopId());
		     shopSearchVO.setPageSize(1);
		     shopSearchVO.setPageNumber(1);
		     SearchResult<ShopSearchResult> shopSearchResult = ShopSearchUtil
		                .searchShop(shopSearchVO);
		     
		     // 从solr获取店铺信息,设置销售量.
		     if (shopSearchResult.isSuccess() && shopSearchResult.getNumFound() >=0) {
		    	 if(shopSearchResult.getResultList().size()>0){
		    		 Long saleNum= shopSearchResult.getResultList().get(0).getSaleCount();
		    		 shopInfoVO.setSaleNum(saleNum);
		    	 }
		      }else{
		          throw new BusinessException(shopSearchResult.getMessage());
		      }
		     
		     
		     shopInfoVO.setSmallLogoPathURL(ImageUtil.getImageUrl(shopInfoVO.getLogoPath() + "_48x48!"));
		     shopInfoVO.setLogoPathURL(ImageUtil.getImageUrl(shopInfoVO.getLogoPath() + "_100x100!"));
		     model.addAttribute("shopInfoResp", shopInfoVO);
		     model.addAttribute("shopId", shopInfoVO.getId());
		     
		 	 /* 店铺信息 end */
		     model.addAttribute("searchBoxWidth", "450");
		     
		 	 /* 商品分类  begin*/
		 	 GdsCategoryReqDTO gdsCategoryReqDTO=new GdsCategoryReqDTO();
		 	 gdsCategoryReqDTO.setShopId(Long.valueOf(vo.getShopId()));
		     gdsCategoryReqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_2);
		 	 List<GdsCategoryRespDTO> gdsCategorys=iGdsCategoryRSV.queryRootCategory(gdsCategoryReqDTO);
		     if(CollectionUtils.isNotEmpty(gdsCategorys)){
		    	 for(GdsCategoryRespDTO category:gdsCategorys){
		    		 GdsCategoryReqDTO chid=new GdsCategoryReqDTO();
		    		 chid.setCatgParent(category.getCatgCode());
		    		 List<GdsCategoryRespDTO> childs=iGdsCategoryRSV.querySubCategory(chid);
		    		 category.setChildren(childs);
		    	 }
		     }
		     model.addAttribute("category", gdsCategorys);
		     /* 商品分类  end*/
		     
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
		    model.addAttribute("isCollect", isCollect);   
		    /* 是否收藏  end*/
	    	    
		    model.addAttribute(ISLOGIN_VO_ATTR, StaffLocaleUtil.getStaff().getId() != 0);
		 	return URL+"/shop-index";
       }
       throw new BusinessException(result.getMessage());
	}
    /**
     * 
     * page:(分页查询). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page")
    public String page(Model model,GoodSearchPageReqVO vo) throws BusinessException {
        // 分类目录Facet设置
       // vo.setCategoryPathFacet(false);
        SearchResult<GoodSearchResult> result =SearchUtil.searchGood(vo);
        if (result.isSuccess()) {
            EcpBasePageRespVO<Map> pageRespVO = SearchUtil.renderRespVO(vo, result);
        	super.addPageToModel(model, pageRespVO);
        	model.addAttribute(ISLOGIN_VO_ATTR, StaffLocaleUtil.getStaff().getId() != 0);
        	return URL+"/page/shopIndex-page";
        }
        throw new BusinessException(result.getMessage());
      

    }  
    /**
     * 
     * hotSales:(查询热卖商品). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
     * @param count
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/hotSales")
    @ResponseBody
    public List<GoodSearchResult> hotSales(Model model,int count,String shopId) throws BusinessException {
    	if(StringUtil.isNotBlank(shopId)){
    		List<GoodSearchResult> hotGoods=SearchUtil.shopHotSales(count, Long.valueOf(shopId));
    		return hotGoods;
    	}else{
    		return null;
    	}
    }
    /**
     * 
     * collectShop:(收藏店铺). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
     * @param count
     * @return 
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
     * deleteShop:(收藏店铺). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
     * @param count
     * @return 
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
}
