package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.stock.GdsStockReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.stock.IUnpfStockRSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品类型策略统一抽象接口实现<br>
 * Date:2015年10月3日上午2:33:01 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoExternalRSVImpl extends AbstractRSVImpl implements
		IGdsInfoExternalRSV {

	@Resource
	private IGdsInfoExternalSV gdsInfoExternalSV;
	
	@Resource
	private IGdsStockSV gdsStockSV;
	@Resource
	private IGdsTypeSV gdsTypeSV;

	/**
	 * 
	 * TODO 获取单品库存. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#getStockAmount(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO)
	 */
	@Override
	public StockInfoRespDTO getStockAmount(
			StockInfoForGdsReqDTO stockInfoForGdsReqDTO)
			throws BusinessException {
		paramNullCheck(stockInfoForGdsReqDTO, "stockInfoForGdsReqDTO");
		paramNullCheck(stockInfoForGdsReqDTO.getSkuId(),
				"stockInfoForGdsReqDTO.skuId");
		return gdsInfoExternalSV.getStockAmount(stockInfoForGdsReqDTO);
	}

	/**
	 * 
	 * TODO 新增预占 明细级别. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#addStockPreOccupy(com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest)
	 */
	@Override
	public void addStockPreOccupy(ROrdCartItemCommRequest ordCartItemCommRequest)
			throws BusinessException {
		gdsInfoExternalSV.addStockPreOccupy(ordCartItemCommRequest);
	}

	/**
	 * 
	 * TODO 新增预占  订单级别. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#BatchAddStockPreOccupy(com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest)
	 */
	@Override
	public void BatchAddStockPreOccupy(ROrdCartsCommRequest cartsCommRequest)
			throws BusinessException {
		gdsInfoExternalSV.BatchAddStockPreOccupy(cartsCommRequest);
	}

	/**
	 * 
	 * TODO 取消预占 明细级别. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#cancleStockPreOccupy(com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest)
	 */
	@Override
	public void cancleStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest)
			throws BusinessException {
		gdsInfoExternalSV.cancleStockPreOccupy(cartItemCommRequest);
	}

	/**
	 * 
	 * TODO 取消预占 订单级别. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#batchCancleStockPreOccupy(com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest)
	 */
	@Override
	public void batchCancleStockPreOccupy(ROrdCartsCommRequest cartsCommRequest)
			throws BusinessException {
		gdsInfoExternalSV.batchCancleStockPreOccupy(cartsCommRequest);
	}

	/**
	 * 
	 * TODO 预占确认（发货） 明细级别. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#deliverGds(com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO)
	 */
	@Override
	public void deliverGds(DeliverySkuStcokReqDTO deliverySkuStcokReqDTO)
			throws BusinessException {
		gdsInfoExternalSV.deliverGds(deliverySkuStcokReqDTO);
	}

	/**
	 * 
	 * TODO 预占确认（发货） 订单级别. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#deliverOrderGds(com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO)
	 */
	@Override
	public void deliverOrderGds(
			DeliverySkuStcokMainReqDTO deliverySkuStcokMainReqDTO)
			throws BusinessException {
		gdsInfoExternalSV.deliverOrderGds(deliverySkuStcokMainReqDTO);
	}

	/**
	 * 
	 * TODO 确认收货，明细级别. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#confirmReceipt(com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO)
	 */
	@Override
	public void confirmReceipt(AffirmStockDTO affirmStockDTO)
			throws BusinessException {
		gdsInfoExternalSV.confirmReceipt(affirmStockDTO);
	}

	/**
	 * 
	 * TODO 确认收货 订单级. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#batchConfirmReceipt(com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO)
	 */
	@Override
	public void batchConfirmReceipt(AffirmStockMainDTO affirmStockMainDTO)
			throws BusinessException {
		gdsInfoExternalSV.batchConfirmReceipt(affirmStockMainDTO);
	}

	/**
	 * 
	 * TODO 是否需要库存. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#isNeedStockAmount(com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO)
	 */
	@Override
	public boolean isNeedStockAmount(LongReqDTO id) throws BusinessException {
		return gdsInfoExternalSV.isNeedStockAmount(id.getId());
	}

	/**
	 * 
	 * TODO 该商品类型是否免邮. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV#isGdsTypeFreightFree(com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO)
	 */
	@Override
	public boolean isGdsTypeFreightFree(LongReqDTO id) throws BusinessException {
		return gdsInfoExternalSV.isGdsTypeFreightFree(id.getId());
	}

    @Override
    public boolean isGdsTypeBuyMore(LongReqDTO id) throws BusinessException {
        return gdsInfoExternalSV.isGdsTypeBuyonce(id.getId());
    }

    @Override
    public boolean isVirtualProduct(LongReqDTO id) throws BusinessException {
        return gdsInfoExternalSV.isVirtualProduct(id.getId());
    }
	
	

}
