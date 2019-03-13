package com.zengshi.ecp.goods.service.busi.impl.external;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV;
import com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsTypeStrategySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.interfaces.stock.IUnpfStockRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:商品统一对外服务（类型库存策略相关）<br>
 * Date:2015年9月29日上午10:37:54 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoExternalSVImpl extends AbstractSVImpl implements
		IGdsInfoExternalSV {

	@Resource
	private IGdsTypeSV gdsTypeSV;
	
	@Resource
	private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;
	
	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;
	
	@Resource
    private IUnpfStockRSV unpfStockRSV;

	/**
	 * 
	 * TODO 获取库存.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#getStockAmount(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO)
	 */
	@Override
	public StockInfoRespDTO getStockAmount(
			StockInfoForGdsReqDTO stockInfoForGdsReqDTO)
			throws BusinessException {
		IGdsTypeStrategySV gdsTypeStrategySV = getGTypeStrategySV(stockInfoForGdsReqDTO
				.getTypeId(),stockInfoForGdsReqDTO.getSkuId());
		return gdsTypeStrategySV.getStockAmount(stockInfoForGdsReqDTO);
	}

	/**
	 * 
	 * TODO 添加库存预占，单个.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#addStockPreOccupy()
	 */
	@Override
	public void addStockPreOccupy(ROrdCartItemCommRequest ordCartItemCommRequest) throws BusinessException {
		IGdsTypeStrategySV gdsTypeStrategySV = getGTypeStrategySV(ordCartItemCommRequest.getGdsType(),ordCartItemCommRequest.getSkuId());
		gdsTypeStrategySV.addStockPreOccupy(ordCartItemCommRequest);
	}

	/**
	 * 
	 * TODO 批量添加库存预占  订单级.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#BatchAddStockPreOccupy()
	 */
	@Override
	public void BatchAddStockPreOccupy(ROrdCartsCommRequest cartsCommRequest) throws BusinessException {
		for (ROrdCartCommRequest cartCommRequest : cartsCommRequest.getOrdCartsCommList()) {
			for (ROrdCartItemCommRequest ordCartItemCommRequest : cartCommRequest.getOrdCartItemCommList()) {
				paramNullCheck(cartsCommRequest.getStaffId(),new String[] {"cartsCommRequest.staffId"});
				paramNullCheck(cartCommRequest.getShopId(),new String[] {"cartCommRequest.shopId"});
				paramNullCheck(ordCartItemCommRequest.getGdsType(),new String[] {"ordCartItemCommRequest.gdsType"});
				//如果是数字印刷版不处理
				if("1".equals(ordCartItemCommRequest.getPrnFlag())){
				    
				    continue ;
				}
				
				ordCartItemCommRequest.setShopId(cartCommRequest.getShopId());
				ordCartItemCommRequest.setStaffId(cartsCommRequest.getStaffId());
				this.addStockPreOccupy(ordCartItemCommRequest);
			}
		}
	}

	/**
	 * 
	 * TODO 取消预占.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#cancleStockPreOccupy()
	 */
	@Override
	public void cancleStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest) throws BusinessException {
		IGdsTypeStrategySV gdsTypeStrategySV = getGTypeStrategySV(cartItemCommRequest.getGdsType(),cartItemCommRequest.getSkuId());
		gdsTypeStrategySV.cancleStockPreOccupy(cartItemCommRequest);
	}
	
	
	

	/**
	 * 
	 * TODO 批量取消预占 订单级.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#batchCancleStockPreOccupy()
	 */
	@Override
	public void batchCancleStockPreOccupy(ROrdCartsCommRequest cartsCommRequest) throws BusinessException {
		paramNullCheck(cartsCommRequest.getStaffId(), new String[]{"cartsCommRequest.staffId"});
		for (ROrdCartCommRequest cartCommRequest : cartsCommRequest
				.getOrdCartsCommList()) {
			paramNullCheck(cartCommRequest, new String[]{"cartCommRequest"});
			paramNullCheck(cartCommRequest.getOrdCartItemCommList(), new String[]{"cartCommRequest.ordCartItemCommList"});
			// 遍历 以明细取消
			for (ROrdCartItemCommRequest cartItemCommRequest : cartCommRequest.getOrdCartItemCommList()) {
				cartItemCommRequest.setStaffId(cartsCommRequest.getStaffId());
				//如果是数字印刷版，不作处理
				if(cartItemCommRequest.getPrnFlag()!=null){
				if(cartItemCommRequest.getPrnFlag().equals("1")){
				    
				  continue ;  
				    
				}
				}
				this.cancleStockPreOccupy(cartItemCommRequest);
			}
		}
	}

	/**
	 * 
	 * TODO 发货 明细级别发货.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#deliverGds()
	 */
	@Override
	public void deliverGds(DeliverySkuStcokReqDTO deliverySkuStcokReqDTO) throws BusinessException {
		IGdsTypeStrategySV gdsTypeStrategySV = getGTypeStrategySV(deliverySkuStcokReqDTO.getGdsTypeId(),deliverySkuStcokReqDTO.getSkuId());
		gdsTypeStrategySV.deliverGds(deliverySkuStcokReqDTO);
	}
	
	/**
	 * 
	 * TODO 商品发货处理接口 订单级发货. 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#deliverOrderGds(com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO)
	 */
	@Override
	public void deliverOrderGds(DeliverySkuStcokMainReqDTO deliverySkuStcokMainReqDTO) throws BusinessException{
		for (DeliverySkuStcokReqDTO deliverySkuStcokReqDTO : deliverySkuStcokMainReqDTO
				.getDeliverySkuStcokDTOs()) {
			deliverySkuStcokReqDTO.setStaffId(deliverySkuStcokMainReqDTO.getStaffId());
			//数字印刷直接不处理
			if("1".equals(deliverySkuStcokReqDTO.getPrnFlag())){
			    continue;
			}
			deliverySkuStcokReqDTO.setDeliverySno(deliverySkuStcokMainReqDTO.getDeliverySno() + deliverySkuStcokReqDTO.getSubOrder());
			this.deliverGds(deliverySkuStcokReqDTO);
		}
	}
	
	/**
	 * 
	 * TODO 确认收货.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#confirmReceipt()
	 */
	@Override
	public void confirmReceipt(AffirmStockDTO affirmStockDTO) throws BusinessException {
		IGdsTypeStrategySV gdsTypeStrategySV = getGTypeStrategySV(affirmStockDTO.getTypeId(),affirmStockDTO.getSkuId());
		gdsTypeStrategySV.confirmReceipt(affirmStockDTO);
	}
	
	/**
	 * 
	 * TODO 确认收货 批量 订单所有明细.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#batchConfirmReceipt()
	 */
	@Override
	public void batchConfirmReceipt(AffirmStockMainDTO affirmStockMainDTO) throws BusinessException {
		for (AffirmStockDTO affirmStockDTO : affirmStockMainDTO.getAffirmStockDTOs()) {
			affirmStockDTO.setStaffId(affirmStockMainDTO.getStaffId());
			affirmStockDTO.setShopId(affirmStockMainDTO.getShopId());
			affirmStockDTO.setCompanyId(affirmStockMainDTO.getCompanyId());
			this.confirmReceipt(affirmStockDTO);
		}
	}
	

	/**
	 * 
	 * TODO 是否有库存.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.isNeedStockAmount#isHaveAmount(java.lang.Long)
	 */
	@Override
	public boolean isNeedStockAmount(Long id) throws BusinessException {
		GdsType type = gdsTypeSV.queryGdsTypeModelByPKFromCache(id);
		if (type == null) {
			throw new BusinessException(
					GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200109);
		}
		return GdsUtils.isEqualsValid(type.getIfNeedstock());
	}

	/**
	 * 
	 * TODO 是否免邮.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV#isGdsTypeFreightFree(java.lang.Long)
	 */
	@Override
	public boolean isGdsTypeFreightFree(Long id) throws BusinessException {
		GdsType type = gdsTypeSV.queryGdsTypeModelByPKFromCache(id);
		if (type == null) {
			throw new BusinessException(
					GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200109);
		}
		return GdsUtils.isEqualsValid(type.getIfFree());
	}

	
	
	@Override
    public boolean isGdsTypeBuyonce(Long id) throws BusinessException {
		GdsType type = gdsTypeSV.queryGdsTypeModelByPKFromCache(id);
        if (type == null) {
            throw new BusinessException(
                    GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200109);
        }
        return GdsUtils.isEqualsValid(type.getIfBuyonce());
    }
	
	
	
	

    @Override
    public boolean isVirtualProduct(Long id) throws BusinessException {
        GdsType type = gdsTypeSV.queryGdsTypeModelByPKFromCache(id);
        if (type == null) {
            throw new BusinessException(
                    GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200109);
        }
        return "VirtualProduct".equalsIgnoreCase(type.getTypeCode());
    }

    /**
	 * 
	 * getPriceStrategySV:(获取价格策略实现SV). <br/>
	 * 
	 * @author linwb3
	 * @param priceTypeCode
	 * @return
	 * @since JDK 1.6
	 */
	private IGdsTypeStrategySV getGTypeStrategySV(Long typeId,Long skuId)
			throws BusinessException {

		GdsTypeReqDTO reqDTO = new GdsTypeReqDTO();
		reqDTO.setId(typeId);
		String typeCode = getGTypeCode(reqDTO,skuId);

		String strategyName = "gType" + typeCode + "StrategySV";
		checkStrategyName(strategyName);
		IGdsTypeStrategySV gdsTypeStrategySV = null;
		try {
			gdsTypeStrategySV = EcpFrameContextHolder.getBean(strategyName,
					IGdsTypeStrategySV.class);
		} catch (Exception e) {
			LogUtil.info(MODULE, "获取不到类型策略服务", e);
			throw new BusinessException(
					GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200110,
					new String[] {});
		}
		return gdsTypeStrategySV;
	}

	/**
	 * 
	 * checkStrategyName:(检查策略名称，如果策略不存在，则抛出异常). <br/>
	 * 
	 * @author linwb3
	 * @param strategyName
	 * @since JDK 1.6
	 */
	private void checkStrategyName(String strategyName)
			throws BusinessException {
		if (StringUtil.isEmpty(strategyName)
				|| "gTypenullStrategySV".equals(strategyName)) {
			throw new BusinessException(
					GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200109);
		}
	}

	/**
	 * 
	 * getPriceTypeCode:(获取价格类型编码). <br/>
	 * 
	 * @author linwb3
	 * @param sku2PriceReqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	private String getGTypeCode(GdsTypeReqDTO gdsTypeReqDTO,long skuId)
			throws BusinessException {
		Long typeId = gdsTypeReqDTO.getId();
		String typeCode = null;
		if (typeId != null) {
			GdsType gdsType = gdsTypeSV.queryGdsTypeByPK(typeId);
			if (gdsType != null) {
				typeCode = gdsType.getTypeCode();
			}
		}
		else if (StringUtil.isNotBlank(gdsTypeReqDTO.getTypeCode())) {
			GdsType gdsType = gdsTypeSV.queryGdsTypeByCode(typeCode,
					GdsConstants.Commons.STATUS_VALID);
			if (gdsType != null) {
				typeCode = gdsType.getTypeCode();
			}
		}else{
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO=new GdsSkuInfoReqDTO();
			gdsSkuInfoReqDTO.setId(skuId);
			GdsSkuInfo skuInfo=gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);
			GdsType gdsType = gdsTypeSV.queryGdsTypeByPK(skuInfo.getGdsTypeId());
			if (gdsType != null) {
				typeCode = gdsType.getTypeCode();
			}
		}
		return typeCode;
	}

}
