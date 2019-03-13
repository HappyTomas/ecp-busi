package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatlog2ShopRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatlog2ShopSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016-4-5上午9:34:58  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCatlog2ShopRSVImpl extends AbstractRSVImpl implements
		IGdsCatlog2ShopRSV {
	
	@Resource
	private IGdsCatlog2ShopSV gdsCatlog2ShopSV;
	

	@Override
	public void saveGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getShopId(),reqDTO.getCatlogId()}, 
				   new String[]{"reqDTO.shopId","reqDTO.catlogId"});
		gdsCatlog2ShopSV.saveGdsCatlog2Shop(reqDTO);
		GdsUtils.sendShopIndexMsg(reqDTO.getShopId());
	}
	
	@Override
	public void batchSaveGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getShopId(),reqDTO.getCatlogIds()}, 
				   new String[]{"reqDTO.shopId","reqDTO.catlogIds"});
		gdsCatlog2ShopSV.batchSaveGdsCatlog2Shop(reqDTO);
		GdsUtils.sendShopIndexMsg(reqDTO.getShopId());
	}



	@Override
	public void deleteGdsCatlog2ShopByPK(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getShopId(),reqDTO.getCatlogId()}, 
				   new String[]{"reqDTO.shopId","reqDTO.catlogId"});
		gdsCatlog2ShopSV.deleteGdsCatlog2ShopByPK(reqDTO);
		GdsUtils.sendShopIndexMsg(reqDTO.getShopId());
	}

	@Override
	public PageResponseDTO<GdsCatlog2ShopRespDTO> queryRelationByShopId(
			GdsCatlog2ShopReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getShopId()}, 
				   new String[]{"reqDTO.shopId"});
		reqDTO.setCatlogId(null);
		return gdsCatlog2ShopSV.queryRelation(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsCatlog2ShopRespDTO> queryRelationByCatlogId(
			GdsCatlog2ShopReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatlogId()}, 
				   new String[]{"reqDTO.catlogId"});
		reqDTO.setShopId(null);
		return gdsCatlog2ShopSV.queryRelation(reqDTO);
	}

	@Override
	public void deleteRelationByShopId(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getShopId()}, 
				   new String[]{"reqDTO.shopId"});
		reqDTO.setCatlogId(null);
		gdsCatlog2ShopSV.deleteGdsCatlog2Shop(reqDTO);
		GdsUtils.sendShopIndexMsg(reqDTO.getShopId());
	}

	@Override
	public void deleteRelationByCatlogId(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatlogId()}, 
				   new String[]{"reqDTO.catlogId"});
		reqDTO.setShopId(null);
		gdsCatlog2ShopSV.deleteGdsCatlog2Shop(reqDTO);
	}

	@Override
	public LongListRespDTO queryGdsCatlog2ShopRespDTOByShopId(
			GdsCatlog2ShopReqDTO reqDTO) throws BusinessException {
		return gdsCatlog2ShopSV.queryRelationedGdsCatlogIdByShopId(reqDTO);
	}
	
	

}

