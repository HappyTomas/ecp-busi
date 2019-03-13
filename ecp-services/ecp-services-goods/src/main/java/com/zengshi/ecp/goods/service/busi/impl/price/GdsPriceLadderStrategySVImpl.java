package com.zengshi.ecp.goods.service.busi.impl.price;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsPriceLadderMapper;
import com.zengshi.ecp.goods.dao.model.GdsPriceLadder;
import com.zengshi.ecp.goods.dao.model.GdsPriceLadderCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceLadderReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceLadderRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsSkuPiceStrategySV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品阶梯价接口<br>
 * Date:2015年8月29日下午5:42:15 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceLadderStrategySVImpl extends GeneralSQLSVImpl implements
		IGdsSkuPiceStrategySV {

	@Resource(name = "seq_gds_price")
	private PaasSequence seqGdsPriceLadder;

	@Resource
	private GdsPriceLadderMapper gdsPriceLadderMapper;
	
	@Autowired(required = false)
	private IGdsPriceSV gdsPriceSV;

	@Override
	public GdsPriceInfoResp getPrice(Long id, Map<String, Object> params)
			throws BusinessException {
		GdsPriceLadder price = gdsPriceLadderMapper.selectByPrimaryKey(id);
		GdsPriceLadderRespDTO gdsPriceRespDTO = new GdsPriceLadderRespDTO();
		ObjectCopyUtil.copyObjValue(price, gdsPriceRespDTO, null, false);
		return gdsPriceRespDTO;
	}

	@Override
	public Long calculatePrice(Map<String, Object> params)
			throws BusinessException {

		Long amount= (Long) params.get("amount");
		if(null == amount){
			amount = 0L;
		}
		Long gdsId= (Long) params.get("gdsId");
		
		GdsSku2PriceReqDTO sku2PriceReqDTO=new GdsSku2PriceReqDTO();
		sku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
		sku2PriceReqDTO.setGdsId(gdsId);
		sku2PriceReqDTO.setIfPrice(false);
		List<GdsSku2PriceRespDTO> prices=gdsPriceSV.queryGdsSkuPriceList(sku2PriceReqDTO);
		List<GdsPriceLadderRespDTO> ladderPrice=new ArrayList<GdsPriceLadderRespDTO>();
		if(CollectionUtils.isNotEmpty(prices)){
			for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : prices) {
				ladderPrice.add((GdsPriceLadderRespDTO) getPrice(gdsSku2PriceRespDTO.getPriceId(), null));
			}
		}
		Collections.sort(ladderPrice);
		long price=0L;
		int index=0;
		for (GdsPriceLadderRespDTO gdsPriceLadderRespDTO : ladderPrice) {
			long startAmount=gdsPriceLadderRespDTO.getStartAmount();
			//如果小于购买量，价格计算按照最先区间价格
			if(index == 0){
				if(amount<startAmount){
					price=gdsPriceLadderRespDTO.getPrice();
				}
			}
			if(amount>=startAmount){
				price=gdsPriceLadderRespDTO.getPrice();
			}else{
				break;
			}
		}
		return price;
	}
	
	
	@Override
	public ROrdCartsChkResponse validatePrice(Map<String, Object> params)
			throws BusinessException {

		ROrdCartsChkResponse resp=new ROrdCartsChkResponse();
		resp.setStatus(true);
		Long amount= (Long) params.get("amount");
		Long gdsId= (Long) params.get("gdsId");
		GdsSku2PriceReqDTO sku2PriceReqDTO=new GdsSku2PriceReqDTO();
		sku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
		sku2PriceReqDTO.setGdsId(gdsId);
		sku2PriceReqDTO.setIfPrice(false);
		List<GdsSku2PriceRespDTO> prices=gdsPriceSV.queryGdsSkuPriceList(sku2PriceReqDTO);
		List<GdsPriceLadderRespDTO> ladderPrice=new ArrayList<GdsPriceLadderRespDTO>();
		if(CollectionUtils.isNotEmpty(prices)){
			for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : prices) {
				ladderPrice.add((GdsPriceLadderRespDTO) getPrice(gdsSku2PriceRespDTO.getPriceId(), null));
			}
		}
		Collections.sort(ladderPrice);
		int index=0;
		for (GdsPriceLadderRespDTO gdsPriceLadderRespDTO : ladderPrice) {
			long startAmount=gdsPriceLadderRespDTO.getStartAmount();
			//如果小于购买量，价格计算按照最先区间价格
			if(index == 0){
				if(amount<startAmount){
					resp.setMsg("购买量不能小于起购量，请检查阶梯价商品数量！");
					resp.setStatus(false);
				}
			}
		}
		return resp;
	}
	

	@Override
	public Long savePrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		GdsPriceLadderReqDTO gdsPriceLadderReqDTO=(GdsPriceLadderReqDTO) gdsSku2PriceReqDTO.getPrice();
		
		Long ladderId = seqGdsPriceLadder.nextValue();
		GdsPriceLadder gdsPriceLadder = new GdsPriceLadder();
		if (gdsPriceLadderReqDTO.getPrice() == null) {
			throw new BusinessException(
					GdsErrorConstants.GdsPrice.ERROR_GOODS_PRICE_210704);
		} else if (gdsPriceLadderReqDTO.getStartAmount() == null) {
			throw new BusinessException(
					GdsErrorConstants.GdsPrice.ERROR_GOODS_PRICE_210705);
		}
		gdsPriceLadder.setId(ladderId);
		gdsPriceLadder.setStatus(GdsConstants.Commons.STATUS_VALID);
		gdsPriceLadder.setPriceTypeId(gdsSku2PriceReqDTO.getPriceId());
		gdsPriceLadder.setCreateStaff(baseInfo.getStaff().getId());
		gdsPriceLadder.setCreateTime(DateUtil.getSysDate());
		gdsPriceLadder.setUpdateStaff(baseInfo.getStaff().getId());
		gdsPriceLadder.setUpdateTime(DateUtil.getSysDate());
		ObjectCopyUtil.copyObjValue(gdsPriceLadderReqDTO, gdsPriceLadder, null,
				false);
		gdsPriceLadderMapper.insert(gdsPriceLadder);
		return ladderId;
	}

	@Override
	public void editPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		GdsPriceLadderReqDTO gdsPriceLadderReqDTO = (GdsPriceLadderReqDTO) baseInfo;

		GdsPriceLadderCriteria criteria = new GdsPriceLadderCriteria();
		criteria.createCriteria().andIdEqualTo(gdsPriceLadderReqDTO.getId());

		GdsPriceLadder gdsPriceLadder = new GdsPriceLadder();
		gdsPriceLadder.setId(gdsPriceLadderReqDTO.getId());
		ObjectCopyUtil.copyObjValue(baseInfo, gdsPriceLadder, null, true);
		gdsPriceLadderMapper.updateByPrimaryKeySelective(gdsPriceLadder);

	}

	@Override
	public void delPrice(BaseInfo baseInfo, Map<String, Object> params)
			throws BusinessException {
		GdsSku2PriceReqDTO gdsPriceLadderReqDTO = (GdsSku2PriceReqDTO) baseInfo;
		GdsPriceLadder gdsPriceLadder = new GdsPriceLadder();
		gdsPriceLadder.setId(gdsPriceLadderReqDTO.getPriceId());
		gdsPriceLadder.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsPriceLadderMapper.updateByPrimaryKeySelective(gdsPriceLadder);
	}

}
