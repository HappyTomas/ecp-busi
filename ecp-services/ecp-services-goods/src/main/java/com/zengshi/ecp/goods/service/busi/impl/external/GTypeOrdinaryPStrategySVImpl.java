package com.zengshi.ecp.goods.service.busi.impl.external;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsTypeStrategySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

public class GTypeOrdinaryPStrategySVImpl extends AbstractSVImpl implements
		IGdsTypeStrategySV {

	@Resource
	private IGdsStockSV gdsStockSV;

	@Resource
	private IGdsTypeSV gdsTypeSV;

	@Resource
	private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;
	/**
	 * 获取库存 TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsTypeStrategySV#getStockAmount(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO)
	 */
	@Override
	public StockInfoRespDTO getStockAmount(StockInfoForGdsReqDTO stockInfoForGdsReqDTO) throws BusinessException {
		StockInfoRespDTO stockInfoDTO = null;
		GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
		gdsSkuInfoReqDTO.setId(stockInfoForGdsReqDTO.getSkuId());
		// 查询类型信息
		GdsSkuInfo skuinfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);
		GdsTypeRespDTO type = null;
		if (skuinfo != null) {
			type = gdsTypeSV.queryGdsTypeByPKFromCache(skuinfo.getGdsTypeId());
		}
		// 如果需要库存,则查询库存
		if (type != null && GdsUtils.isEqualsValid(type.getIfNeedstock())) {
			try {
				stockInfoDTO = gdsStockSV.queryStockInfoByGds(stockInfoForGdsReqDTO);
			} catch (Exception e) {
				LogUtil.error(MODULE, "查询库存信息异常", e);
			}
		}
		// 如果不需要库存,则不查询库存,直接以充足展示
		else if (type != null && GdsUtils.isEqualsInvalidOrNULL(type.getIfNeedstock())) {
			stockInfoDTO = new StockInfoRespDTO();
			ObjectCopyUtil.copyObjValue(stockInfoForGdsReqDTO, stockInfoDTO, null, false);
			Long count = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_HARDTOGET_THRESHOLD).getParaValue()) + 1L;
			stockInfoDTO.setAvailCount(count);
		} else {
			return null;
		}
		return stockInfoDTO;
	}

	@Override
	public void addStockPreOccupy(ROrdCartItemCommRequest ordCartItemCommRequest) throws BusinessException {
		GdsTypeRespDTO type = null;
		if (ordCartItemCommRequest != null) {
			type = gdsTypeSV.queryGdsTypeByPKFromCache(ordCartItemCommRequest.getGdsType());
		}
		if (type != null && GdsUtils.isEqualsValid(type.getIfNeedstock())) {
			gdsStockSV.addStockPreOccupy(ordCartItemCommRequest);
		}
	}

	@Override
	public void cancleStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest) throws BusinessException {
		GdsTypeRespDTO type = null;
		if (cartItemCommRequest != null) {
			type = gdsTypeSV.queryGdsTypeByPKFromCache(cartItemCommRequest.getGdsType());
		}
		if (type != null && GdsUtils.isEqualsValid(type.getIfNeedstock())) {
			gdsStockSV.deleteSubStockPreOccupy(cartItemCommRequest);
		}
	}

	@Override
	public void deliverGds(DeliverySkuStcokReqDTO deliverySkuStcokReqDTO) throws BusinessException {
		GdsTypeRespDTO type = null;
		if (deliverySkuStcokReqDTO != null) {
			type = gdsTypeSV.queryGdsTypeByPKFromCache(deliverySkuStcokReqDTO.getGdsTypeId());
		}
		if (type != null && GdsUtils.isEqualsValid(type.getIfNeedstock())) {
			gdsStockSV.updateDeliverySkuStcokForSub(deliverySkuStcokReqDTO);
		}
	}

	@Override
	public void confirmReceipt(AffirmStockDTO affirmStockDTO) throws BusinessException {
		GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
		gdsSkuInfoReqDTO.setId(affirmStockDTO.getSkuId());
		// 查询类型信息
		GdsSkuInfo skuinfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);
		GdsTypeRespDTO type = null;
		if (skuinfo != null) {
			type = gdsTypeSV.queryGdsTypeByPKFromCache(skuinfo.getGdsTypeId());
		}
		if (type != null && GdsUtils.isEqualsValid(type.getIfNeedstock())) {
			gdsStockSV.updateAffirmReceiptStockForSub(affirmStockDTO);
		}
	}
}
