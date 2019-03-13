package com.zengshi.ecp.goods.service.busi.impl.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCusPriceMapper;
import com.zengshi.ecp.goods.dao.model.GdsCusPrice;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCusPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCusPriceRespDTO;
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
public class GdsPriceCusGroupStrategySVImpl extends AbstractSVImpl implements
		IGdsSkuPiceStrategySV {

	@Resource(name = "seq_gds_price")
	private PaasSequence seqGdsCusPrice;

	@Resource
	private GdsCusPriceMapper gdsCusPriceMapper;

	@Autowired(required = false)
	private IGdsPriceSV gdsPriceSV;

	@Override
	public GdsCusPriceRespDTO getPrice(Long id, Map<String, Object> params)
			throws BusinessException {
		GdsCusPrice price = gdsCusPriceMapper.selectByPrimaryKey(id);
		GdsCusPriceRespDTO gdsPriceRespDTO = new GdsCusPriceRespDTO();
		ObjectCopyUtil.copyObjValue(price, gdsPriceRespDTO, null, false);
		return gdsPriceRespDTO;
	}

	@Override
	public Long calculatePrice(Map<String, Object> params)
			throws BusinessException {
		Long skuId = (Long) params.get("skuId");
		Long staffId = (Long) params.get("staffId");
		GdsSku2PriceReqDTO sku2PriceReqDTO = new GdsSku2PriceReqDTO();
		sku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
		sku2PriceReqDTO.setSkuId(skuId);
		sku2PriceReqDTO.setIfPrice(false);
		List<GdsSku2PriceRespDTO> prices = gdsPriceSV
				.queryGdsSkuPriceList(sku2PriceReqDTO);
		List<GdsCusPriceRespDTO> result = new ArrayList<GdsCusPriceRespDTO>();
		if (CollectionUtils.isNotEmpty(prices)) {
			for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : prices) {
				result.add((GdsCusPriceRespDTO) getPrice(
						gdsSku2PriceRespDTO.getPriceId(), null));
			}
		}

		// 通过服务获取客户分组,这里先写死
		String staffGroup = "001";
		
		//判断分组
		if (CollectionUtils.isNotEmpty(result)) {
			for (GdsCusPriceRespDTO gdsCusPriceRespDTO : result) {
				if(staffGroup.equals(gdsCusPriceRespDTO.getPriceTarget())){
					return gdsCusPriceRespDTO.getPrice();
				}
			}
		}
		return 0L;
	}
	
	@Override
	public ROrdCartsChkResponse validatePrice(Map<String, Object> params)
			throws BusinessException {

		ROrdCartsChkResponse resp=new ROrdCartsChkResponse();
		resp.setStatus(true);
		return resp;
	}

	@Override
	public Long savePrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		Long priceId = seqGdsCusPrice.nextValue();
		GdsCusPrice gdsCusPrice = new GdsCusPrice();
		gdsCusPrice.setId(priceId);
		gdsCusPrice.setStatus(GdsConstants.Commons.STATUS_VALID);
		
		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		GdsCusPriceReqDTO gdsCusPriceReqDTO=(GdsCusPriceReqDTO) gdsSku2PriceReqDTO.getPrice();
		ObjectCopyUtil.copyObjValue(gdsCusPriceReqDTO, gdsCusPrice, null, false);
		gdsCusPrice.setPriceTypeId(gdsSku2PriceReqDTO.getPriceId());
		gdsCusPrice.setCreateStaff(baseInfo.getStaff().getId());
		gdsCusPrice.setCreateTime(DateUtil.getSysDate());
		gdsCusPrice.setUpdateStaff(baseInfo.getStaff().getId());
		gdsCusPrice.setUpdateTime(DateUtil.getSysDate());
		gdsCusPriceMapper.insert(gdsCusPrice);
		return priceId;
	}

	@Override
	public void editPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		Long priceId = gdsSku2PriceReqDTO.getPriceId();
		GdsCusPrice gdsPrice = new GdsCusPrice();
		gdsPrice.setId(priceId);
		gdsPrice.setUpdateStaff(baseInfo.getStaff().getId());
		gdsPrice.setUpdateTime(DateUtil.getSysDate());
		ObjectCopyUtil.copyObjValue(baseInfo, gdsPrice, null, true);
		gdsCusPriceMapper.updateByPrimaryKeySelective(gdsPrice);
	}

	@Override
	public void delPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		Long priceId = gdsSku2PriceReqDTO.getPriceId();
		GdsCusPrice gdsPrice = new GdsCusPrice();
		gdsPrice.setId(priceId);
		gdsPrice.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsPrice.setUpdateStaff(baseInfo.getStaff().getId());
		gdsPrice.setUpdateTime(DateUtil.getSysDate());
		gdsCusPriceMapper.updateByPrimaryKeySelective(gdsPrice);
	}

}
