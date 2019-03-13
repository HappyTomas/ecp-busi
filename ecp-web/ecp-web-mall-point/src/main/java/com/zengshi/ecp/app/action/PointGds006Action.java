package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pointmgds006Req;
import com.zengshi.ecp.app.resp.Pointmgds006Resp;
import com.zengshi.ecp.app.resp.gds.PointGoodSearchResultVO;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.busi.search.vo.ExtraRespVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.RangeQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo;
import com.zengshi.ecp.search.dubbo.search.ext.filter.ExtraQueryInfo.QueryCategory;
import com.zengshi.ecp.search.dubbo.search.ext.filter.QueryFilter;
import com.zengshi.ecp.search.dubbo.search.ext.filter.good.GoodCategoriesQueryFilter;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 商品搜索 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午11:47:09 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("pointmgds006")
@Action(bizcode = "pointmgds006", userCheck = false)
@Scope("prototype")
public class PointGds006Action extends AppBaseAction<Pointmgds006Req, Pointmgds006Resp> {

	@Resource
	private IGdsCollectRSV gdsCollectRSV;

	@Resource
	private IGdsCategoryRSV gdsCategoryRSV;

	@Resource
	private IPromRSV promRSV;

	private static final String MODULE = PointGds006Action.class.getName();

	public final static String FIELD_DISCOUNTPRICE = "discountPriceOfLevel";



	@SuppressWarnings("rawtypes")
	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {
		Pointmgds006Req gds006Req = this.request;
		Pointmgds006Resp gds006Resp = this.response;

		SearchParam searchParam = new SearchParam();
		searchParam.setCurrentSiteId(2l);

		searchParam.setPageNo(gds006Req.getPageNumber());
		searchParam.setPageSize(gds006Req.getPageSize());

		// GET 中文解码
		if (StringUtils.isBlank(gds006Req.getKeyword())) {
			searchParam.setKeyword(SearchConstants.STAR);
		} else {
			searchParam.setKeyword(gds006Req.getKeyword());
		}

		List<ExtraFieldQueryField> extraFieldQueryList = new ArrayList<ExtraFieldQueryField>();

		// 根据商品类型菜单过滤(实物商品、虚拟商品)
		if (StringUtils.isNotBlank(gds006Req.getGdsTypeId())) {
			ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
			extraFieldQueryField.setName("gdsTypeId");
			extraFieldQueryField.setValue(gds006Req.getGdsTypeId());
			extraFieldQueryList.add(extraFieldQueryField);
		} else {
			searchParam = this.addFieldFilterSupport(searchParam, gds006Req);
		}

		// 积分商品
		ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
		extraFieldQueryField.setName("ifScoreGds");
		extraFieldQueryField.setValue("1");
		extraFieldQueryList.add(extraFieldQueryField);
		searchParam.setExtraANDFieldQueryList(extraFieldQueryList);

		ExtraRespVO extraRespVO = new ExtraRespVO();
		ExtraQueryInfo extraQueryInfo = new ExtraQueryInfo();
		List<QueryFilter> filterList = new ArrayList<QueryFilter>();
		if (StringUtils.isNotBlank(gds006Req.getCategory())) {
			extraRespVO.setSearchCategory(gds006Req.getCategory());
			List<QueryCategory> queryCategoryList = new ArrayList<QueryCategory>();
			queryCategoryList.add(new QueryCategory(Arrays.asList(gds006Req
					.getCategory().split(SearchConstants.COMMA)),
					ExtraQueryInfo.FIELD_CATEGORIES));
			extraQueryInfo.setQueryCategoryList(queryCategoryList);
			filterList.add(new GoodCategoriesQueryFilter());
		}

		// 查询综合评分和排序下的查询列表
		SearchResult<GoodSearchResult> result = SearchFacade.search(
				GoodSearchResult.class,
				searchParam,
				extraQueryInfo,
				filterList,
				new GdsTranslator(ApplicationContextUtil.getBean(
						"gdsCatalog2SiteRSV", IGdsCatalog2SiteRSV.class)));
		if (result.isSuccess()) {
			PageResponseDTO<GoodSearchResult> pageRespVO = this.renderRespVO(gds006Req, result);
		

			  List<PointGoodSearchResultVO> goodSearchResultVOs = new ArrayList<PointGoodSearchResultVO>();
	            for (GoodSearchResult goodSearchResult : pageRespVO.getResult()) {
	                PointGoodSearchResultVO goodSearchResultVO = new PointGoodSearchResultVO();
	                ObjectCopyUtil.copyObjValue(goodSearchResult, goodSearchResultVO, null, false);
	                goodSearchResultVOs.add(goodSearchResultVO);
	            }

	            gds006Resp.setPageRespVO(goodSearchResultVOs);
		

		}else{

		throw new BusinessException(result.getMessage());
		}
	}

	@SuppressWarnings({ "rawtypes" })
	private PageResponseDTO<GoodSearchResult> renderRespVO(Pointmgds006Req gds006Req,
			SearchResult<GoodSearchResult> result) throws BusinessException {
		PageResponseDTO<GoodSearchResult> t = new PageResponseDTO<GoodSearchResult>();
		t.setResult(this.renderSearchResult(result.getResultList()));
		t.setPageNo(gds006Req.getPageNumber());
		t.setPageSize(gds006Req.getPageSize());
		t.setCount(result.getNumFound());
		t.setPageCount(result.getTotallyPage());

		return t;
	}

	public static final String APP = "2";

	private List<GoodSearchResult> renderSearchResult(
			List<GoodSearchResult> goodSearchResultVOList) {

		if (CollectionUtils.isNotEmpty(goodSearchResultVOList)) {
			for (GoodSearchResult goodSearchResultVO : goodSearchResultVOList) {
				goodSearchResultVO.render();

			}
		}

		return goodSearchResultVOList;

	}
	

	private SearchParam addFieldFilterSupport(SearchParam searchParam,
			Pointmgds006Req vo) {

		List<SortField> sortFieldList = new ArrayList<SortField>();

		// 可能为空
		if (StringUtils.isNotBlank(vo.getField())) {

			// 排序字段校验
			if (StringUtils.equals("score", vo.getField())
					|| StringUtils.equals("updateTime", vo.getField())
					|| StringUtils.equals("gdsTypeId", vo.getField())) {

				// 积分字段才支持范围查询
				if (StringUtils.isNotBlank(vo.getRangeType())
						&& StringUtils.equals(vo.getField(), "score")) {
					List<RangeQueryField> rangeQueryFieldList = new ArrayList<RangeQueryField>();
					RangeQueryField rangeQueryField = new RangeQueryField();
					rangeQueryField.setName("score");

					String start = "";
					String end = "";
					if (StringUtils.equals("1", vo.getRangeType())) {
						start = "0";
						end = "4999";
					} else if (StringUtils.equals("2", vo.getRangeType())) {
						start = "5000";
						end = "9999";
					} else if (StringUtils.equals("3", vo.getRangeType())) {
						start = "10000";
						end = "14999";
					} else if (StringUtils.equals("4", vo.getRangeType())) {
						start = "15000";
						end = "*";
					}

					if (StringUtils.isNotBlank(start)
							&& StringUtils.isNotBlank(end)) {
						rangeQueryField.setStart(start);
						rangeQueryField.setEnd(end);
						rangeQueryFieldList.add(rangeQueryField);
						searchParam.setRangeQueryFieldList(rangeQueryFieldList);
					}

				}

				ESort eSort = ESort.getAndValidSort(vo.getSort());
				if (null != eSort) {
					sortFieldList.add(new SortField(vo.getField(), eSort));
				}

			}

			// 自定义排序，无需加入评分排序和相关度排序

		} else {

			if (!StringUtils.isBlank(searchParam.getKeyword())
					&& !StringUtils.equals(SearchConstants.STAR,
							searchParam.getKeyword())) {

				// 无自定义排序，需要加入评分排序和相关度排序
				searchParam.setIfSortByScore(true);
				sortFieldList.add(new SortField("gdsNameBoost", ESort.ASC));
			}

			// 默认按更新日期降序
			sortFieldList.add(new SortField("updateTime", ESort.DESC));
		}

		searchParam.setSortFieldList(sortFieldList);

		return searchParam;
	}
}
