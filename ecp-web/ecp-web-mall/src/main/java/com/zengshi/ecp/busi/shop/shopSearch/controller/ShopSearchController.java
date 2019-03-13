package com.zengshi.ecp.busi.shop.shopSearch.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.ExtraRespVO;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchPageReqVO;
import com.zengshi.ecp.busi.shop.shopSearch.vo.ShopSearchResult;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping("shopsearch")
public class ShopSearchController extends EcpBaseController {

	@RequestMapping()
	public String search(Model model, ShopSearchPageReqVO vo)
			throws BusinessException {
		String url = CmsCacheUtil.getCmsSiteCache(SiteLocaleUtil.getSite()).getSiteUrl();
		
	    model.addAttribute("contextUrl", url);
		vo.setPageSize(10);
		model.addAttribute("pageSize", "10");
		model.addAttribute("pageNumber", "1");
		ExtraRespVO extraRespVO = new ExtraRespVO();
		extraRespVO.setType("2");
		if(StringUtil.isNotBlank(vo.getKeyword())){
			extraRespVO.setKeyword(vo.getKeyword());
			
		}
		model.addAttribute("extra", extraRespVO);
		SearchResult<ShopSearchResult> searchResult = ShopSearchUtil
				.searchShop(vo);
		if (searchResult.isSuccess()) {
			model.addAttribute("totalPage", searchResult.getTotallyPage());
			model.addAttribute("totalNumber",searchResult.getNumFound());
			
			EcpBasePageRespVO<Map> respVO = ShopSearchUtil.renderRespVO(vo,
					searchResult);
			super.addPageToModel(model, respVO);
		} else {

			throw new BusinessException(searchResult.getMessage());

		}

		return "/shop/shopSearch/shop-search-main";
	}

	@RequestMapping("/list")
	public String searchList(Model model, ShopSearchPageReqVO vo)
			throws BusinessException {
		String url = CmsCacheUtil.getCmsSiteCache(SiteLocaleUtil.getSite()).getSiteUrl();
		
	    model.addAttribute("contextUrl", url);
		vo.setPageSize(10);
		ExtraRespVO extraRespVO = new ExtraRespVO();
		extraRespVO.setType("2");
		if(StringUtil.isNotBlank(vo.getKeyword())){
			extraRespVO.setKeyword(vo.getKeyword());
			
		}
		model.addAttribute("extra", extraRespVO);
		model.addAttribute("pageSize", "10");
		model.addAttribute("pageNumber", vo.getPageNumber());

		SearchResult<ShopSearchResult> searchResult = ShopSearchUtil
				.searchShop(vo);
		if (searchResult.isSuccess()) {
			EcpBasePageRespVO<Map> respVO = ShopSearchUtil.renderRespVO(vo,
					searchResult);
			model.addAttribute("totalPage", searchResult.getTotallyPage());
			model.addAttribute("totalNumber",searchResult.getNumFound());
			super.addPageToModel(model, respVO);
		} else {

			throw new BusinessException(searchResult.getMessage());

		}

		return "/shop/shopSearch/page/shopSearch-page";
	}
	@RequestMapping("/hotlist")
	public String searchHotList(Model model,ShopSearchPageReqVO vo)throws BusinessException{
		vo.setPageSize(8);
		EcpBasePageRespVO<Map> pageRespVO = SearchUtil.shopHotSales(vo, Long.valueOf(vo.getId()));
		model.addAttribute("hotList", pageRespVO);
		model.addAttribute("shopId", vo.getId());
		return "/shop/shopSearch/page/shop-gdslist";
		
	}
	
	@RequestMapping("/newlist")
	public String searchNewList(Model model,ShopSearchPageReqVO vo)throws BusinessException{
		vo.setPageSize(8);
		EcpBasePageRespVO<Map> pageRespVO = SearchUtil.shopNewSales(vo, Long.valueOf(vo.getId()));
		model.addAttribute("hotList", pageRespVO);
		model.addAttribute("shopId", vo.getId());
		return "/shop/shopSearch/page/shop-gdslist";
		
	}
}
