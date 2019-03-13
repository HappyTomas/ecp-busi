package com.zengshi.ecp.busi.seller.prom.goods.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.JsonResultThreadLocal;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.prom.PromConst;
import com.zengshi.ecp.busi.seller.prom.goods.vo.CatgVO;
import com.zengshi.ecp.busi.seller.prom.goods.vo.FileImportReqVO;
import com.zengshi.ecp.busi.seller.prom.goods.vo.FileImportRespVO;
import com.zengshi.ecp.busi.seller.prom.goods.vo.GdsVO;
import com.zengshi.ecp.busi.seller.prom.goods.vo.QueryGdsReqVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.ImprotPromResultDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromImportRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromUtilRSV;
import com.zengshi.ecp.prom.dubbo.util.CheckPageNull;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-20下午7:21:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version  
 * @since JDK 1.7 
 */
@Controller
@RequestMapping(value = "/seller/gdsprom")
public class GdsPromController extends EcpBaseController {

	 private static String MODULE = GdsPromController.class.getName();
	 
	@Resource
	private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

	@Resource
	private IGdsCategoryRSV gdsCategoryRSV;

	@Resource
	private IPromGroupRSV promGroupRSV;

	@Resource
	private IPromQueryRSV promQueryRSV;

	@Resource
	private IPromUtilRSV promUtilRSV;
	
	@Resource
    private IPromSkuRSV promSkuRSV;
	 
    @Resource
	private IShopInfoRSV shopInfoRSV;

    @Resource
    private IPromImportRSV promImportRSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
	/**
	 * 商品选择 按钮 初始化
	 * 
	 * @return
	 * @throws Exception
	 * @author huangjx
	 */
	@RequestMapping("/gdsselect")
	public String gdsSelect(Model model, Long shopId, Long siteId, QueryGdsReqVO queryGdsReqVO) throws Exception {
		model.addAttribute("shopId", shopId);
		model.addAttribute("siteId", siteId);
		/*GdsSkuInfoReqDTO queryDTO = queryGdsReqVO.toBaseInfo(GdsSkuInfoReqDTO.class);

		ObjectCopyUtil.copyObjValue(queryGdsReqVO, queryDTO, "", false);

		if (StringUtil.isEmpty(queryGdsReqVO.getGdsStatus())) {
			// 默认全部为待上架 和 已上架
			List<String> gdsStList = new ArrayList<String>();
			gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
			gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
			queryDTO.setGdsStatusArr(gdsStList);
		}

		SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC, SkuQueryOption.CATG, SkuQueryOption.SHOWPRICE };
		queryDTO.setSkuQuery(skuQuery);
		PageResponseDTO<GdsSkuInfoRespDTO> page = gdsSkuInfoQueryRSV.queryGdsSkuInfoListPage(queryDTO);
		// 转化分类名称
		if (CheckPageNull.checkPageNull(page)) {
			for (GdsSkuInfoRespDTO dto : page.getResult()) {
				// 非空 不需要查询
				if (StringUtil.isEmpty(dto.getMainCatgName())) {

					if (!StringUtil.isEmpty(dto.getMainCatgs())) {
						GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
						reqDTO.setCatgCode(dto.getMainCatgs());
						// 分类id 查询
						GdsCategoryRespDTO respDTO = gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
						if (respDTO != null) {
							dto.setMainCatgName(respDTO.getCatgName());
						}
					}
				}
			}
		}
		model.addAttribute("page", page);*/
		return "/seller/prom/gdsselect/gds-select-grid";
	}
	
	
	/**
	 * 商品选择页面 查询和分页
	 * 
	 * @return
	 * @throws Exception
	 * @author huangjx
	 */
	@RequestMapping(value = "/goodslist")
	public String goodsList(Model model, @ModelAttribute("queryGdsReqVO") QueryGdsReqVO queryGdsReqVO)
			throws BusinessException {

		// 1.封装后场入参对象
		GdsSkuInfoReqDTO queryDTO = queryGdsReqVO.toBaseInfo(GdsSkuInfoReqDTO.class);

		ObjectCopyUtil.copyObjValue(queryGdsReqVO, queryDTO, "", false);

	    if(StringUtil.isEmpty(queryGdsReqVO.getGdsStatus())){
            //默认全部为待上架 和 已上架
            List<String> gdsStList=new ArrayList<String>();
            gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
            gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
            queryDTO.setGdsStatusArr(gdsStList);
        }
	    if(queryGdsReqVO!=null && queryGdsReqVO.getSiteId()!=null){
      	  List<Long> siteIds=new ArrayList<Long>();
      	  siteIds.add(queryGdsReqVO.getSiteId());
      	  queryDTO.setSiteIds(siteIds);
      }
		PageResponseDTO<GdsSkuInfoRespDTO> page = gdsSkuInfoQueryRSV.queryGdsSkuInfoListPage(queryDTO);
		// 转化分类名称
		if (CheckPageNull.checkPageNull(page)) {
			for (GdsSkuInfoRespDTO dto : page.getResult()) {
				// 非空 不需要查询
				if (StringUtil.isEmpty(dto.getMainCatgName())) {

					if (!StringUtil.isEmpty(dto.getMainCatgs())) {
						GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
						reqDTO.setCatgCode(dto.getMainCatgs());
						// 分类id 查询
						GdsCategoryRespDTO respDTO = gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
						if (respDTO != null) {
							dto.setMainCatgName(respDTO.getCatgName());
						}
					}
				}
			}
		}
		model.addAttribute("page", page);
		return "/seller/prom/gdsselect/gds-list";
	}

	/**
	 * 促销黑名单列表
	 * 
	 * @param model
	 * @param promId
	 * @param gdsBlackList
	 * @return
	 * @throws Exception
	 * @author lisp
	 */
	@RequestMapping("/gdsblackList")
	public String gdsBlackList(Model model, @RequestParam("promId") String promId,
			@RequestParam("skuIds") String[] skuIds) throws Exception {

		List<GdsVO> gdsList = new ArrayList<GdsVO>();
		model.addAttribute("gdsList", gdsList);

		// 非空 需要查询促销商品列表(编辑 初始化进入非空，重新编辑商品列表也是非空)
		// 为空 表示新增
		if (!StringUtil.isEmpty(promId) && skuIds.length == 0) {
			PromSkuLimitDTO ipromSkuLimitDTO = new PromSkuLimitDTO();
			ipromSkuLimitDTO.setPromId(new Long(promId));
			ipromSkuLimitDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
			List<PromSkuLimitDTO> skuList = promQueryRSV.listPromotionSkuLimit(ipromSkuLimitDTO);

			if (!CollectionUtils.isEmpty(skuList)) {

				GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
				GdsSkuInfoRespDTO respDTO = new GdsSkuInfoRespDTO();

				for (PromSkuLimitDTO promSkuLimitDTO : skuList) {

					GdsVO gdsVO = new GdsVO();
					dto.setId(promSkuLimitDTO.getSkuId().longValue());
					gdsVO.setSkuId(promSkuLimitDTO.getSkuId().toString());
					respDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
					if (respDTO != null) {
						ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
					}
					gdsList.add(gdsVO);

				}
			}
		} else {
			// 新增
			if (!StringUtil.isEmpty(skuIds) && skuIds.length > 0) {

				// 页面传入参数 组织
				List<String> gdss = java.util.Arrays.asList(skuIds);
				// 去重 过滤
				Set set = new HashSet(gdss);
				gdss = new ArrayList<String>(set);
				if (!CollectionUtils.isEmpty(gdss)) {

					GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
					GdsSkuInfoRespDTO respDTO = new GdsSkuInfoRespDTO();
					// 设置列表值
					for (String skuId : gdss) {

						if (!StringUtil.isEmpty(skuId)) {
							// 单品信息
							dto.setId(new Long(skuId));
							respDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
							GdsVO gdsVO = new GdsVO();
							if (respDTO != null) {
								ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
								gdsVO.setSkuId(respDTO.getId().toString());
							}

							// 计算库存量 待实现
							/*
							 * if(!CollectionUtils.isEmpty(respDTO.getStocks()))
							 * { for(StockInfoPageDTO
							 * stock:respDTO.getStocks()){
							 * //gdsVO.setPromCnt(stock.) }
							 * 
							 * }
							 */
							gdsList.add(gdsVO);
						}

					}

				}
			}
		}
		return "/seller/prom/gdsselect/prom-blackgds-table";
	}

	/**
	 * 促销已选择黑名单分类列表
	 * 
	 * @param model
	 * @param promId
	 * @param catgBlackList
	 * @return
	 * @throws Exception
	 * @author lisp
	 */
	@RequestMapping("/catgblackList")
	public String catgBlackList(Model model, @RequestParam("promId") String promId,
			@RequestParam("catgIds") String[] catgIds) throws Exception {

		List<CatgVO> catgList = new ArrayList<CatgVO>();
		model.addAttribute("catgList", catgList);

		// 为空 表示新增
		if (!StringUtil.isEmpty(promId) && catgIds.length == 0) {
			PromSkuLimitDTO ipromSkuLimitDTO = new PromSkuLimitDTO();
			ipromSkuLimitDTO.setPromId(new Long(promId));
			ipromSkuLimitDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);// 分类
			List<PromSkuLimitDTO> skuList = promQueryRSV.listPromotionSkuLimit(ipromSkuLimitDTO);

			if (!CollectionUtils.isEmpty(skuList)) {

				for (PromSkuLimitDTO promSkuLimitDTO : skuList) {

					CatgVO catgVO = new CatgVO();
					GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
					reqDTO.setCatgCode(promSkuLimitDTO.getCatgCode());
					GdsCategoryRespDTO respDTO = gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);

					catgVO.setCatgCode(promSkuLimitDTO.getCatgCode());

					if (respDTO != null) {
						catgVO.setCatgName(respDTO.getCatgName());
						catgVO.setStatus(respDTO.getStatus());
					}
					catgList.add(catgVO);
				}
			}
		} else {
			// 新增
			if (!StringUtil.isEmpty(catgIds) && catgIds.length > 0) {

				// 页面传入参数 组织
				List<String> catgs = java.util.Arrays.asList(catgIds);
				// 去重 过滤
				Set set = new HashSet(catgs);
				catgs = new ArrayList<String>(set);
				if (!CollectionUtils.isEmpty(catgs)) {
					// 设置列表值
					for (String catgCode : catgs) {
						if (!StringUtil.isEmpty(catgCode)) {
							// 分类信息信息
							CatgVO catgVO = new CatgVO();
							catgVO.setCatgCode(catgCode.split(PromConst.SPILT_HX)[0]);
							catgVO.setCatgName(catgCode.split(PromConst.SPILT_HX)[1]);
							catgList.add(catgVO);
						}
					}
				}
			}
		}
		return "/seller/prom/category/open/prom-blackcatg-table";
	}

	/**
	 * 促销已选择黑名单分类列表
	 * 
	 * @param model
	 * @param promId skuIds
	 * @param gdsList
	 * @return
	 * @throws Exception
	 * @author lisp
	 */
	@RequestMapping("/gdsList")
	public String gdsList(Model model, @RequestParam("promId") String promId, @RequestParam("skuIds") String[] skuIds,@RequestParam("promTypeCode") String promTypeCode)
			throws Exception {

		List<GdsVO> gdsList = new ArrayList<GdsVO>();
		model.addAttribute("gdsList", gdsList);
		model.addAttribute("moreShowButton", "0");
        //判断是否为秒杀促销，展示商品秒杀价配置
        if ("1000000019".equals(promTypeCode)) {
            model.addAttribute("seckillflag", true);
        }else{
            model.addAttribute("seckillflag", false);
        }
		// 非空 需要查询促销商品列表(编辑 初始化进入非空，重新编辑商品列表也是非空)
		// 为空 表示新增
		if (!StringUtil.isEmpty(promId) && skuIds.length == 0) {

			model.addAttribute("moreShowButton", "1");

			PromSkuDTO ipromSkuDTO = new PromSkuDTO();
			ipromSkuDTO.setPromId(new Long(promId));
			ipromSkuDTO.setJoinType(PromConst.PromSku.JOIN_TYPE_2);
			ipromSkuDTO.setPageSize(5);
			PageResponseDTO<PromSkuRespDTO> page = promQueryRSV.pagePromotionSku(ipromSkuDTO);
			List<PromSkuRespDTO> skuList = null;
			if (CheckPageNull.checkPageNull(page)) {
				skuList = page.getResult();
			}

			if (!CollectionUtils.isEmpty(skuList)) {

				GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
				GdsSkuInfoRespDTO respDTO = new GdsSkuInfoRespDTO();

				for (PromSkuRespDTO promSkuDTO : skuList) {

					GdsVO gdsVO = new GdsVO();
					PromStockLimitDTO stocklimit = null;
					dto.setId(promSkuDTO.getSkuId().longValue());
					// 库存查询 设置条件
					SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC, SkuQueryOption.STOCK,
							SkuQueryOption.SHOWPRICE };
					dto.setSkuQuery(skuQuery);
					// 调用商品接口
					respDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
					stocklimit = promUtilRSV.queryPromStockLimit(promId, String.valueOf(promSkuDTO.getSkuId()));
					if (stocklimit != null) {
						gdsVO.setBuyCnt(stocklimit.getBuyCnt());
						model.addAttribute("showBuyFlage", true);
					}
					if (respDTO != null) {
						ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
						//调用商品接口,验证是否需要库存
						LongReqDTO longReqDTO = new LongReqDTO();
						longReqDTO.setId(new Long(gdsVO.getGdsTypeId()));
						gdsVO.setIsNeedStock(gdsInfoExternalRSV.isNeedStockAmount(longReqDTO));
						gdsVO.setPromCnt(promSkuDTO.getPromCnt());
						gdsVO.setPriceType(promSkuDTO.getPriceType());
						gdsVO.setPriceValue(promSkuDTO.getPriceValue());
						gdsVO.setSkuId(respDTO.getId().toString());
						// 默认0
						if (StringUtil.isEmpty(gdsVO.getStockCnt())) {
							gdsVO.setStockCnt(new Long(0));
						}
						// 计算总库存量
						gdsVO.setStockCnt(respDTO.calcAvalidStocks());
						// 计算总库存量
					}

					gdsList.add(gdsVO);
				}
			}
		} else {
			// 新增
			if (!StringUtil.isEmpty(skuIds) && skuIds.length > 0) {

				// 页面传入参数 组织
				List<String> gdss = java.util.Arrays.asList(skuIds);
				// 去重 过滤
				Set set = new HashSet(gdss);
				gdss = new ArrayList<String>(set);
				if (!CollectionUtils.isEmpty(gdss)) {
					// 设置列表值

					GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
					GdsSkuInfoRespDTO respDTO = new GdsSkuInfoRespDTO();

					for (String skuId : gdss) {
						if (!StringUtil.isEmpty(skuId)) {
							// 单品信息
							dto.setId(new Long(skuId));
							// 库存查询 设置条件
							SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC, SkuQueryOption.STOCK,
									SkuQueryOption.SHOWPRICE };
							dto.setSkuQuery(skuQuery);
							// 调用商品接口
							respDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
							GdsVO gdsVO = new GdsVO();
							if (respDTO != null) {
								ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
								//调用商品接口,验证是否需要库存
								LongReqDTO longReqDTO = new LongReqDTO();
								longReqDTO.setId(new Long(gdsVO.getGdsTypeId()));
								gdsVO.setIsNeedStock(gdsInfoExternalRSV.isNeedStockAmount(longReqDTO));
								gdsVO.setSkuId(respDTO.getId().toString());
								// 默认0
								if (StringUtil.isEmpty(gdsVO.getStockCnt())) {
									gdsVO.setStockCnt(new Long(0));
								}
								// 计算总库存量
								gdsVO.setStockCnt(respDTO.calcAvalidStocks());

								// 计算总库存量

							}
							gdsList.add(gdsVO);
						}
					}

				}
			}
		}
		return "/seller/prom/gdsselect/gds-table";
	}

	
	
	
	
	 /**
     * 
     * manyGds:(跳转到商品导入页面). <br/> 
     * 
     * @author lisp 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/manyGds")
    public String manyGds(Model model,String promId,String doAction, String promTypeCode, QueryGdsReqVO queryGdsReqVO){
        model.addAttribute("promId", promId);
        model.addAttribute("doAction", doAction);
        model.addAttribute("promTypeCode", promTypeCode);
        return "/seller/prom/gdsgrid/more-gds-grid";
    }
	
	
    
    /**
     * 导入商品选择列表 
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/gridlist")
    public String gridlist(Model model,  String promTypeCode, QueryGdsReqVO queryGdsReqVO) throws Exception {
        
        
        model.addAttribute("promTypeCode", promTypeCode);
        PromSkuDTO queryDTO=queryGdsReqVO.toBaseInfo(PromSkuDTO.class);
        
        ObjectCopyUtil.copyObjValue(queryGdsReqVO, queryDTO, "", false);
        
        queryDTO.setJoinType(PromConst.PromSku.JOIN_TYPE_2);
        PageResponseDTO<PromSkuRespDTO> gdsPage=promSkuRSV.querySkuPromPage(queryDTO);
        //转化分类名称
        if(CheckPageNull.checkPageNull(gdsPage)){
            for(PromSkuRespDTO dto:gdsPage.getResult()){
                
                ShopInfoResDTO shopDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                if(shopDTO !=null){
                    dto.setShopName(shopDTO.getShopName()); 
                }
                //非空 不需要查询
                GdsSkuInfoReqDTO reqdto=new GdsSkuInfoReqDTO();
                reqdto.setId(dto.getSkuId().longValue());
                GdsSkuInfoRespDTO  respDTO=new GdsSkuInfoRespDTO();
                respDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(reqdto);
                if(respDTO!=null){
                    dto.setCatgCode(respDTO.getMainCatgs());
                    dto.setMainCatgs(respDTO.getMainCatgs());
                    dto.setMainCatgName(respDTO.getMainCatgName());
                    dto.setGdsName(respDTO.getGdsName());
                    dto.setGdsTypeId(respDTO.getGdsTypeId());
                }
             
            }
        }
        
        model.addAttribute("gdsPage",gdsPage );
        return "/seller/prom/gdsgrid/more-gds-list";
    }
    
	
	/**
	 * 黑名单商品选择 按钮 弹出页面
	 * 
	 * @return
	 * @throws Exception
	 * @author lisp
	 */
	@RequestMapping("/gdsblackselect")
	public String gdsBlackSelect(Model model, Long shopId, Long siteId, QueryGdsReqVO queryGdsReqVO) throws Exception {
		model.addAttribute("shopId", shopId);
		model.addAttribute("siteId", siteId);
		return "/seller/prom/gdsselect/gds-select-grid";
	}

	
	/**
	 * 分类选择 按钮 弹出页面
	 * 
	 * @return
	 * @throws Exception
	 * @author lisp
	 */
	@RequestMapping("/catgList")
	public String catgList(Model model, @RequestParam("promId") String promId,@RequestParam("catgIds") String[] catgIds) throws Exception {

		List<CatgVO> catgList = new ArrayList<CatgVO>();
		model.addAttribute("catgList", catgList);

		// 为空 表示新增
		if (!StringUtil.isEmpty(promId) && catgIds.length == 0) {
			PromSkuDTO ipromSkuDTO = new PromSkuDTO();
			ipromSkuDTO.setPromId(new Long(promId));
			ipromSkuDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);// 分类
			List<PromSkuDTO> skuList = promQueryRSV.listPromotionSku(ipromSkuDTO);

			if (!CollectionUtils.isEmpty(skuList)) {

				for (PromSkuDTO promSkuDTO : skuList) {

					CatgVO catgVO = new CatgVO();
					GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
					reqDTO.setCatgCode(promSkuDTO.getCatgCode());
					GdsCategoryRespDTO respDTO = gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);

					catgVO.setCatgCode(promSkuDTO.getCatgCode());

					if (respDTO != null) {
						catgVO.setCatgName(respDTO.getCatgName());
						catgVO.setStatus(respDTO.getStatus());
					}
					catgList.add(catgVO);
				}
			}
		} else {
			// 新增
			if (!StringUtil.isEmpty(catgIds) && catgIds.length > 0) {

				// 页面传入参数 组织
				List<String> catgs = java.util.Arrays.asList(catgIds);
				// 去重 过滤
				Set set = new HashSet(catgs);
				catgs = new ArrayList<String>(set);
				if (!CollectionUtils.isEmpty(catgs)) {
					// 设置列表值
					for (String catgCode : catgs) {
						if (!StringUtil.isEmpty(catgCode)) {
							// 分类信息信息
							CatgVO catgVO = new CatgVO();
							catgVO.setCatgCode(catgCode.split(PromConst.SPILT_HX)[0]);
							catgVO.setCatgName(catgCode.split(PromConst.SPILT_HX)[1]);
							catgList.add(catgVO);
						}
					}
				}
			}
		}
		return "/seller/prom/category/open/prom-catg-table";
	}
	
	/** 
     * valid:(批量删除). <br/> 
     * @author lisp 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/batchDel")
    @ResponseBody
    public EcpBaseResponseVO batchDel(@RequestParam("skuIds")String[] skuIds,@RequestParam("promId")String promId){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            if(skuIds==null){
                throw new BusinessException("web.prom.400032");
            }
            if(StringUtil.isEmpty(promId)){
                throw new BusinessException("web.prom.400033");
            }
            List<PromSkuDTO> list=new ArrayList<PromSkuDTO>();
            for (int i=0;i<skuIds.length;i++){
                PromSkuDTO promSkuDTO=new PromSkuDTO();
                promSkuDTO.setSkuId(Long.valueOf(skuIds[i]));
                promSkuDTO.setPromId(Long.valueOf(promId));
                promSkuDTO.setUpdateStaff(promSkuDTO.getStaff().getId());
                //promSkuDTO.setIfValid(PromConst.PromSku.IF_VALID_0);
                list.add(promSkuDTO);
            }
            promSkuRSV.updateBatch(list);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        return vo;
    }
	
	  /** 
     * valid:(批量insert). <br/> 
     * @author lisp 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/insertGds")
    @ResponseBody
    public EcpBaseResponseVO insertGds(@RequestParam("skuIds")String[] skuIds,@RequestParam("promId")String promId){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            if(skuIds==null){
                throw new BusinessException("web.prom.400032");
            }
            if(StringUtil.isEmpty(promId)){
                throw new BusinessException("web.prom.400033");
            }
            List<PromSkuDTO> list=new ArrayList<PromSkuDTO>();
            
            GdsSkuInfoReqDTO dto=new GdsSkuInfoReqDTO();
            GdsSkuInfoRespDTO  respDTO=new GdsSkuInfoRespDTO();
            
            for (int i=0;i<skuIds.length;i++){
                PromSkuDTO promSkuDTO=new PromSkuDTO();
                promSkuDTO.setSkuId(Long.valueOf(skuIds[i]));
                promSkuDTO.setPromId(Long.valueOf(promId));
                promSkuDTO.setSiteId(promSkuDTO.getCurrentSiteId());
                //单品信息
                dto.setId(new Long(skuIds[i]));
                respDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
                if(respDTO!=null){
                    promSkuDTO.setGdsId(respDTO.getGdsId());
                }
                list.add(promSkuDTO);
            }
            promSkuRSV.insertBatch(list);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        return vo;
    }
	
    /**
     * 
     * importGds:(跳转到商品导入页面 初始化). <br/> 
     * 
     * @author lisp 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/importGds")
    public String importGds(Model model,String promId, String promTypeCode,QueryGdsReqVO queryGdsReqVO){
        model.addAttribute("promId", promId);
        model.addAttribute("promTypeCode", promTypeCode);
        return "/seller/prom/importdata/prom-importdata";
    }
    
    /**
     * 
     * importGds:(跳转到商品导入页面). <br/> 
     * 
     * @author lisp 
     * @return 
     * @since JDK 1.7
     *///String promId,
    @RequestMapping(value="/importGdsList")
    public String importGdsList(Model model,String promId, String promTypeCode,QueryGdsReqVO queryGdsReqVO){
        model.addAttribute("promId", promId);
        model.addAttribute("promTypeCode", promTypeCode);
        PromSkuDTO queryDTO=queryGdsReqVO.toBaseInfo(PromSkuDTO.class);
        
        ObjectCopyUtil.copyObjValue(queryGdsReqVO, queryDTO, "", false);
        
        if(!StringUtil.isEmpty(queryDTO.getGdsId()) && queryDTO.getGdsId()==0){
            queryDTO.setGdsId(null);
        }
        
        if(!StringUtil.isEmpty(queryDTO.getSkuId()) && queryDTO.getSkuId()==0){
            queryDTO.setSkuId(null);
        }
        
        queryDTO.setJoinType(PromConst.PromSku.JOIN_TYPE_2);
        PageResponseDTO<PromSkuRespDTO> importPage=promSkuRSV.querySkuPromPage(queryDTO);
        //PageResponseDTO<PromImportRespDTO> page=promImportRSV.queryPromImportForPage(queryDTO);
        //转化分类名称
        if(CheckPageNull.checkPageNull(importPage)){
            for(PromSkuRespDTO dto:importPage.getResult()){
                
                ShopInfoResDTO shopDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                if(shopDTO !=null){
                    dto.setShopName(shopDTO.getShopName()); 
                }
                //非空 不需要查询
                GdsSkuInfoReqDTO reqdto=new GdsSkuInfoReqDTO();
                reqdto.setId(dto.getSkuId().longValue());
                GdsSkuInfoRespDTO  respDTO=new GdsSkuInfoRespDTO();
                respDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(reqdto);
                if(respDTO!=null){
                    dto.setCatgCode(respDTO.getMainCatgs());
                    dto.setMainCatgs(respDTO.getMainCatgs());
                    dto.setMainCatgName(respDTO.getMainCatgName());
                    dto.setGdsName(respDTO.getGdsName());
                    dto.setGdsTypeId(respDTO.getGdsTypeId());
                }
             
            }
        }
        model.addAttribute("importPage", importPage);
        return "/seller/prom/importdata/import-list";
    }
    /** 
     * valid:(删除). <br/> 
     * @author lisp 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/updateCnt")
    @ResponseBody
    public EcpBaseResponseVO updateCnt(@RequestParam("skuId")String skuId,@RequestParam("promId")String promId,@RequestParam("promCnt")String promCnt){
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            long val=0;
            try
            {
               val=Long.parseLong(promCnt);
               if(val<0){
                   throw new BusinessException("web.prom.400034");
               }
            }
            catch(Exception e)
            {
                throw new BusinessException("web.prom.400034");
            }
            PromSkuDTO promSkuDTO=new PromSkuDTO();
            //promSkuDTO.setId(Long.valueOf(id));
            promSkuDTO.setSkuId(Long.valueOf(skuId));
            promSkuDTO.setPromId(Long.valueOf(promId));
            promSkuDTO.setUpdateStaff(promSkuDTO.getStaff().getId());
            promSkuDTO.setPromCnt(Long.valueOf(val));
            promSkuRSV.updatePromSkuCnt(promSkuDTO);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        return vo;
    }
    
    /** 
     * valid:(删除). <br/> 
     * @author huangjx 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/del")
    @ResponseBody
    public EcpBaseResponseVO delete(@RequestParam("id")String id,@RequestParam("promId")String promId){
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            if(StringUtil.isBlank(id)){
                throw new BusinessException("web.prom.400031");
            }
           /* PromImportReqDTO promImportReqDTO=new PromImportReqDTO();
            
            promImportReqDTO.setId(Long.valueOf(id));
            promImportRSV.deleteById(promImportReqDTO);*/
            PromSkuDTO promSkuDTO=new PromSkuDTO();
            promSkuDTO.setId(Long.valueOf(id));
            promSkuDTO.setPromId(Long.valueOf(promId));
            promSkuDTO.setUpdateStaff(promSkuDTO.getStaff().getId());
            promSkuDTO.setIfValid(PromConst.PromSku.IF_VALID_0);
            promSkuRSV.update(promSkuDTO);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        return vo;
    }
    
    
    /**
     * @param excel
     * @param promId
     * @param model
     * @param request
     * @param response
     * @return
     * @author huangjx
     */
    @RequestMapping(value="/importdata")
    @ResponseBody
    public EcpBaseResponseVO importData(Model model,FileImportReqVO vo){
    
        EcpBaseResponseVO ebResVO = new EcpBaseResponseVO();
        
        PromImportReqDTO aiReqDto = new PromImportReqDTO();
        
        ObjectCopyUtil.copyObjValue(vo, aiReqDto, null, true);
        
        double excueTime;
        
        ImprotPromResultDTO importResultDTO = new ImprotPromResultDTO();
        try {
            long start = System.currentTimeMillis();
            importResultDTO = promImportRSV.saveByFileFJ(aiReqDto);
//            promImportRSV.saveByFile(aiReqDto);

            
            //轮询是否上传 完成
            String isOver = promImportRSV.checkOver(vo.getFileId());
//            while(true){
//                isOver=promImportRSV.checkOver(vo.getFileId());
//                if("0".equals(isOver)){
//                    Thread.sleep(3*1000);
//                }else{
//                    break;
//                }
//            } 
            long end = System.currentTimeMillis();
            excueTime = (end - start)/1000.00;
            LogUtil.debug(MODULE, "批量导入商品数据执行时间："+(excueTime)+"'s");
            
            if("2".equals(isOver)){
                ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                ebResVO.setResultMsg("请检查excel文件是否填写正确。");
            }
            if("1".equals(isOver))
            {
                ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
                ebResVO.setResultMsg("数据总数："+importResultDTO.getTotalCount()+", 成功总数："+importResultDTO.getSuccessCount()+", 失败总数："+importResultDTO.getErrorCount()+", 重复总数："+importResultDTO.getRepeatCount()+", 耗时："+excueTime+"'s");
            }
        } catch (BusinessException e) {
            ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ebResVO.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getErrorMessage(), e);
        } catch (Exception e) {
            ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ebResVO.setResultMsg(e.getMessage());
            LogUtil.error(MODULE, e.getMessage(), e);
        }
        return ebResVO;
    }
    /**
     * 
     * uploadfile:(上传商品文件). <br/> 
     * 
     * @author huangjx 
     * @param excel
     * @param model
     * @param request
     * @param response
     * @return 
     * @since JDK 1.7
     */

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	@NativeJson(true)
	public Map<String,Object> uploadFile(@RequestParam(value = "excelFile", required = false) MultipartFile excel,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		JsonResultThreadLocal.set(false);
	    Map<String, Object> ret = new HashMap<>(); 
        FileImportRespVO respVO = new FileImportRespVO();
        if(excel==null){
            LogUtil.info(MODULE, "促销商品导入文件不存在");
            throw new BusinessException("web.prom.400029");
        }
        
		String fileId = "";
		String oriFileName = excel.getOriginalFilename();
		String[] fileNamea = oriFileName.split("\\.");
		String fileName = fileNamea[0] + "_" + UUID.randomUUID();
		String fileExtName = fileNamea[1];
		 
		try {
			fileId = FileUtil.saveFile(excel.getBytes(), fileName, fileExtName);
			ret.put("fileId", fileId);
			ret.put("fileName", fileName);
			ret.put("fileExtName", fileExtName);
			ret.put("success", true);

		} catch (IOException e) {
			   LogUtil.error(MODULE, "文件保存失败",e);
	           //throw new BusinessException("web.prom.400030");
			   ret.put("success", false);
		}

		return ret;

	}
}
