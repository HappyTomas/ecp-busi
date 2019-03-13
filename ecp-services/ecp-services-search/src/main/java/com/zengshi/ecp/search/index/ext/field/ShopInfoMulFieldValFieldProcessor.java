package com.zengshi.ecp.search.index.ext.field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatlog2ShopRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.index.ext.MulFieldValFieldProcessor;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;

public class ShopInfoMulFieldValFieldProcessor extends
		MulFieldValFieldProcessor {

	private static final String MODULE = ShopInfoMulFieldValFieldProcessor.class
			.getName();

	@Resource
	private IShopInfoRSV shopInfoRSV;
	@Resource
	private IGdsEvalRSV gdsEvalRSV;
	@Resource
	private IReportGoodPayedRSV reportGoodPayedRSV;
	@Resource
	private IGdsCatlog2ShopRSV gdsCatlog2ShopRSV;
	@Resource
	private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;

	@Override
	public Map<String, Object> process(SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO,
									   SecFieldRespDTO curSecFieldRespDTO,
									   List<SecFieldRespDTO> secFieldRespDTOList,
									   Map<String, Object> fieldValueMap) throws Exception {

		this.nullCheck(
				fieldValueMap.get(secObjectRespDTO.getObjectUniquefieldName()),
				"主键编码");
		Long id = this.parseLong(
				fieldValueMap.get(secObjectRespDTO.getObjectUniquefieldName()),
				"主键编码");
		Map<String, Object> map = new HashMap<String, Object>();
		// 店铺销量

		// 店铺好评率
		GdsEvalReqDTO gdsEvalReqDTO = new GdsEvalReqDTO();
		gdsEvalReqDTO.setShopId(id);
		Double rate = gdsEvalRSV.calCulateShopGoodEvalRate(gdsEvalReqDTO);

//		BigDecimal bd = new BigDecimal(rate);
//		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

		map.put("gdsEvalRate",rate);

		// map.put("shopType", value)
		if (fieldValueMap.containsKey("shopDealType")) {
			map.put("shopDealType", fieldValueMap.get("shopDealType"));
		}
		if (fieldValueMap.containsKey("logoPath")) {
			map.put("logoPath", fieldValueMap.get("logoPath"));
		}

		// 获取店铺售卖商品总数
		RGoodSaleRequest goodSaleRequest = new RGoodSaleRequest();
		goodSaleRequest.setShopId(id);
		try {
			Long gdsSaleCount = reportGoodPayedRSV
					.querySumBuyNumByShopId(goodSaleRequest);
			map.put("saleCount", gdsSaleCount);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询店铺商品售卖总数失败！");
			map.put("saleCount", 0);
		}
		// 获取店铺目录关系
		GdsCatlog2ShopReqDTO gdsCatlog2ShopReqDTO = new GdsCatlog2ShopReqDTO();
		gdsCatlog2ShopReqDTO.setShopId(id);
		try {
			List<Long> siteId = new ArrayList<Long>();
			LongListRespDTO longListRespDTO = gdsCatlog2ShopRSV
					.queryGdsCatlog2ShopRespDTOByShopId(gdsCatlog2ShopReqDTO);
			if (longListRespDTO != null && longListRespDTO.getLst().size() > 0) {
				for (Long catLogId : longListRespDTO.getLst()) {
					GdsCatalog2SiteReqDTO catalog2SiteReqDTO = new GdsCatalog2SiteReqDTO();
					catalog2SiteReqDTO.setCatlogId(catLogId);
					List<GdsCatalog2SiteRespDTO> catalog2SiteRespDTOs = gdsCatalog2SiteRSV
							.querySiteListByCataLogId(catalog2SiteReqDTO);
					
					for(GdsCatalog2SiteRespDTO catalog2SiteRespDTO : catalog2SiteRespDTOs){
						
						siteId.add(catalog2SiteRespDTO.getSiteId());
						
					}
					

				}

			}
			
			map.put("siteList", siteId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询店铺目录列表失败！");
			map.put("siteList", new ArrayList<Long>());
		}
		return map;

	}

}
