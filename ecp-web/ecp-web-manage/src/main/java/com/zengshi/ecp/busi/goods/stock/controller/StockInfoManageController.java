package com.zengshi.ecp.busi.goods.stock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.goods.stock.vo.StockInfo;
import com.zengshi.ecp.busi.goods.stock.vo.StockJsonBean;
import com.zengshi.ecp.busi.goods.stock.vo.StockOptInfo;
import com.zengshi.ecp.busi.goods.util.GdsParamsTool;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: 库存管理 <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-8-26下午8:36:37 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/goods/stockinfo")
public class StockInfoManageController extends EcpBaseController {
	@Resource(name = "gdsStockRSV")
	private IGdsStockRSV gdsStockRSV;

	@Resource(name = "gdsInfoQueryRSV")
	private IGdsInfoQueryRSV gdsInfoQueryRSV;

	private static final String MODULE = StockInfoManageController.class.getName();

	/**
	 * 
	 * pageStockInit:(库存管理页面初始化). <br/>
	 * 
	 * @author zjh
	 * @param stockInfo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/pageStockInit")
	public String pageStockInit(Model model,StockInfo stockInfo) {
		model.addAttribute("stockInfo",stockInfo);
		return "/goods/stock/stock-grid";
	}

	/**
	 * 
	 * optStock:(初始化库存操作页面). <br/>
	 * 
	 * @author zjh
	 * @param stockInfo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/optStock")
	public String optStock(StockInfo stockInfo) {
		LogUtil.debug(MODULE, "请求参数为：" + stockInfo.toString());
		return "/goods/stock/stockPop/opt-stock";
	}

	/**
	 * 
	 * listStock:(返回库存列表). <br/>
	 * 
	 * @author zjh
	 * @param model
	 * @param stockInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/listStock")
	@ResponseBody
	public Model listStock(Model model, StockInfo stockInfo) throws Exception {
		LogUtil.debug(MODULE, "请求参数为：" + stockInfo.toString());

		// /后场服务所需要的DTO；
		StockInfoReqDTO stockInfoDTO = stockInfo.toBaseInfo(StockInfoReqDTO.class);
		stockInfoDTO.setShopId(stockInfo.getShopId());
		// stockInfoDTO.setShopId(1234L);
		if (stockInfo.getTypeId() != null) {
			stockInfoDTO.setTypeId(stockInfo.getTypeId());
		}
		if (stockInfo.getCatgCode() != null && !"".equals(stockInfo.getCatgCode())) {
			stockInfoDTO.setCatgCode(stockInfo.getCatgCode());
		}

		// if (stockInfo.getSkuId() != null) {
		//
		// stockInfoDTO.setSkuId(stockInfo.getSkuId());
		// }
		if (stockInfo.getRepType() != null && !"".equals(stockInfo.getRepType())) {

			stockInfoDTO.setRepType(stockInfo.getRepType());
		}

		if (StringUtil.isNotBlank(stockInfo.getStockStatus())) {
			if (stockInfo.getStockStatus().equals("00")) {
				stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_LACK);
			} else if (stockInfo.getStockStatus().equals("01")) {
				stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_HARDTOGET);
			} else if (stockInfo.getStockStatus().equals("02")) {
				stockInfoDTO.setStockStatus(GdsConstants.GdsStock.STOCK_STATUS_ENOUGH);
			}
		}

		stockInfoDTO.setGdsName(stockInfo.getGdsName());
		stockInfoDTO.setIsbn(stockInfo.getIsbn());
		PageResponseDTO<StockInfoPageRespDTO> t = gdsStockRSV.queryStockPageInfo(stockInfoDTO);

		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		if (t.getResult() == null) {
			t.setResult(new ArrayList<StockInfoPageRespDTO>());
		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
		return super.addPageToModel(model, GdsParamsTool.batchGdsAndOrdDetailUrl(respVO));
	}

	/**
	 * 
	 * saveOptStockInfo:(保存库存变更信息). <br/>
	 * 
	 * @author zjh
	 * @param stockOptInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/saveOptStockInfo")
	public String saveOptStockInfo(@Valid StockOptInfo stockOptInfo) throws Exception {
		LogUtil.debug(MODULE, "请求参数为：" + stockOptInfo.toString());

		StockInfoReqDTO stockInfoDTO = new StockInfoReqDTO();

		stockInfoDTO.setStaffId(stockOptInfo.getStaff().getId());
		stockInfoDTO.setShopId(stockOptInfo.getShopId());
		stockInfoDTO.setId(stockOptInfo.getStockId());
		stockInfoDTO.setTurnType(stockOptInfo.getTurnType());
		stockInfoDTO.setGdsId(stockOptInfo.getGdsId());
		stockInfoDTO.setTurnCount(stockOptInfo.getTurnCount());

		gdsStockRSV.updateStockInfo(stockInfoDTO);
		return "/goods/stock/stock-grid";

	}

	@RequestMapping(value = "/queryGdsByCatgCode")
	@ResponseBody
	public StockJsonBean queryGdsByCatgCode(HttpServletResponse response, @RequestParam("catgCode") String catgCode, @RequestParam("shopId") Long shopId) throws Exception {
		GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
		gdsInfoReqDTO.setPlatCatgs(catgCode);
		gdsInfoReqDTO.setShopId(shopId);
		StockJsonBean jsonBean = new StockJsonBean();
		try {

			List<GdsInfoRespDTO> gdsInfoRespDTOs = gdsInfoQueryRSV.queryGdsInfoList(gdsInfoReqDTO);
			if (gdsInfoRespDTOs == null || gdsInfoRespDTOs.size() == 0) {

				jsonBean.setFlag("false");
				jsonBean.setMsg("分类id:" + catgCode + "没有商品数据！");
			} else {

				jsonBean.setObject(gdsInfoRespDTOs);
				jsonBean.setFlag("true");

			}
		} catch (Exception e) {
			jsonBean.setFlag("false");
			jsonBean.setMsg("商品列表查询失败！");

		}

		return jsonBean;

	}
}