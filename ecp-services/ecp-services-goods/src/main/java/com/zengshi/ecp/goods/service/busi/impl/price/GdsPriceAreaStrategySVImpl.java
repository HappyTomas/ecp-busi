package com.zengshi.ecp.goods.service.busi.impl.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsAreaPriceMapper;
import com.zengshi.ecp.goods.dao.model.GdsAreaPrice;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsAreaPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsAreaPriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsSkuPiceStrategySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品普通价格策略实现<br>
 * Date:2015年8月29日下午5:42:26 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceAreaStrategySVImpl extends AbstractSVImpl implements
		IGdsSkuPiceStrategySV {

	@Resource(name = "seq_gds_price")
	private PaasSequence seqGdsAreaPrice;

	@Resource
	private GdsAreaPriceMapper gdsAreaPriceMapper;
	
	@Autowired(required = false)
	private IGdsPriceSV gdsPriceSV;

	@Override
	public GdsAreaPriceRespDTO getPrice(Long id, Map<String, Object> params)
			throws BusinessException {
		GdsAreaPrice price = gdsAreaPriceMapper.selectByPrimaryKey(id);
		GdsAreaPriceRespDTO GdsAreaPriceRespDTO = new GdsAreaPriceRespDTO();
		ObjectCopyUtil.copyObjValue(price, GdsAreaPriceRespDTO, null, false);
		return GdsAreaPriceRespDTO;
	}
	
	@Override
	public ROrdCartsChkResponse validatePrice(Map<String, Object> params)
			throws BusinessException {

		ROrdCartsChkResponse resp=new ROrdCartsChkResponse();
		resp.setStatus(true);
		return resp;
	}

	@Override
	public Long calculatePrice(Map<String, Object> params)
			throws BusinessException {
		Long skuId = (Long) params.get("skuId");
		GdsSku2PriceReqDTO sku2PriceReqDTO = new GdsSku2PriceReqDTO();
		sku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
		sku2PriceReqDTO.setSkuId(skuId);
		sku2PriceReqDTO.setIfPrice(false);
		List<GdsSku2PriceRespDTO> prices = gdsPriceSV.queryGdsSkuPriceList(sku2PriceReqDTO);
		List<GdsAreaPriceRespDTO> result = new ArrayList<GdsAreaPriceRespDTO>();
		if (CollectionUtils.isNotEmpty(prices)) {
			for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : prices) {
				result.add((GdsAreaPriceRespDTO) getPrice(
						gdsSku2PriceRespDTO.getPriceId(), null));
			}
		}
		//通过服务获取地区编码,这里先写死
		String areaCode = "001";
		
		//判断地区
		if (CollectionUtils.isNotEmpty(result)) {
			for (GdsAreaPriceRespDTO gdsAreaPriceRespDTO : result) {
				if(areaCode.equals(gdsAreaPriceRespDTO.getPriceTarget())){
					return gdsAreaPriceRespDTO.getPrice();
				}
			}
		}
		return 0L;
	}

	@Override
	public Long savePrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		Long priceId = seqGdsAreaPrice.nextValue();
		GdsAreaPrice gdsAreaPrice = new GdsAreaPrice();
		gdsAreaPrice.setId(priceId);
		gdsAreaPrice.setStatus(GdsConstants.Commons.STATUS_VALID);
		
		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		GdsAreaPriceReqDTO GdsAreaPriceReqDTO=(GdsAreaPriceReqDTO) gdsSku2PriceReqDTO.getPrice();
		ObjectCopyUtil.copyObjValue(GdsAreaPriceReqDTO, gdsAreaPrice, null, false);
		gdsAreaPrice.setPriceTypeId(gdsSku2PriceReqDTO.getPriceId());
		gdsAreaPrice.setCreateStaff(baseInfo.getStaff().getId());
		gdsAreaPrice.setCreateTime(DateUtil.getSysDate());
		gdsAreaPrice.setUpdateStaff(baseInfo.getStaff().getId());
		gdsAreaPrice.setUpdateTime(DateUtil.getSysDate());
		gdsAreaPriceMapper.insert(gdsAreaPrice);
		return priceId;
	}

	@Override
	public void editPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		Long priceId = gdsSku2PriceReqDTO.getPriceId();
		GdsAreaPrice GdsAreaPrice = new GdsAreaPrice();
		GdsAreaPrice.setId(priceId);
		GdsAreaPrice.setUpdateStaff(baseInfo.getStaff().getId());
		GdsAreaPrice.setUpdateTime(DateUtil.getSysDate());
		ObjectCopyUtil.copyObjValue(baseInfo, GdsAreaPrice, null, true);
		gdsAreaPriceMapper.updateByPrimaryKeySelective(GdsAreaPrice);
	}

	@Override
	public void delPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		Long priceId = gdsSku2PriceReqDTO.getPriceId();
		GdsAreaPrice GdsAreaPrice = new GdsAreaPrice();
		GdsAreaPrice.setId(priceId);
		GdsAreaPrice.setStatus(GdsConstants.Commons.STATUS_INVALID);
		GdsAreaPrice.setUpdateStaff(baseInfo.getStaff().getId());
		GdsAreaPrice.setUpdateTime(DateUtil.getSysDate());
		gdsAreaPriceMapper.updateByPrimaryKeySelective(GdsAreaPrice);
	}

}
