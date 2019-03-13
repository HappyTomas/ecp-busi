package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品查询Dubbo服务实现<br>
 * Date:2015年8月30日下午4:53:02 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfoQueryRSVImpl extends AbstractRSVImpl implements
		IGdsSkuInfoQueryRSV {

	@Resource
	private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

	@Override
	public GdsSkuInfoRespDTO queryGdsSkuInfoResp(
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsSkuInfoReqDTO, "gdsSkuInfoReqDTO");
		paramNullCheck(gdsSkuInfoReqDTO.getId(), "gdsSkuInfoReqDTO.id");
		return gdsSkuInfoQuerySV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
	}

	@Override
	public GdsSkuInfoRespDTO querySkuInfoByOptions(
			GdsSkuInfoReqDTO skuInfoReqDTO) throws BusinessException {
		paramNullCheck(skuInfoReqDTO, "skuInfoReqDTO");
		paramNullCheck(skuInfoReqDTO.getId(), "skuInfoReqDTO.id");
		return gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoReqDTO,
				skuInfoReqDTO.getSkuQuery());
	}

	@Override
	public List<GdsSkuInfoRespDTO> queryGdsSkuInfoList(
			GdsSkuInfoReqDTO skuInfoReqDTO) throws BusinessException {
		paramNullCheck(skuInfoReqDTO, "skuInfoReqDTO");
		paramNullCheck(skuInfoReqDTO.getShopId(), "skuInfoReqDTO.shopId");
		return gdsSkuInfoQuerySV.queryGdsSkuInfoListResp(skuInfoReqDTO,
				skuInfoReqDTO.getSkuQuery());
	}

	@Override
	public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoListPage(
			GdsSkuInfoReqDTO skuInfoReqDTO) throws BusinessException {
		paramNullCheck(skuInfoReqDTO, "skuInfoReqDTO");
//		paramNullCheck(skuInfoReqDTO.getShopId(), "skuInfoReqDTO.shopId");
		return gdsSkuInfoQuerySV.queryGdsSkuInfoPageListResp(skuInfoReqDTO,
				skuInfoReqDTO.getSkuQuery());
	}

	@Override
	public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoPaging(
			GdsSku2PropPropIdxReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		if(ArrayUtils.isEmpty(reqDTO.getOptions())){
			return gdsSkuInfoQuerySV.queryGdsSkuInfoPagingByProp(reqDTO,SkuQueryOption.BASIC,SkuQueryOption.PROP, SkuQueryOption.MAINPIC,SkuQueryOption.CAlDISCOUNT);
		}else{
			return gdsSkuInfoQuerySV.queryGdsSkuInfoPagingByProp(reqDTO, reqDTO.getOptions());
		}
		
	}

	@Override
	public Boolean isBelongToCategory(GdsGds2CatgReqDTO reqDto)
			throws BusinessException {
		paramNullCheck(reqDto, true, "reqDto");
		return gdsSkuInfoQuerySV.isBelongToCategory(reqDto);
	}
	
	@Override
	public PageResponseDTO<GdsSkuInfoRespDTO> querySkuInfoListPageALLDB(GdsSkuInfoReqDTO gdsInfoReqDTO){
		paramNullCheck(gdsInfoReqDTO, true, "gdsInfoReqDTO");
		return gdsSkuInfoQuerySV.querySkuInfoListPageALLDB(gdsInfoReqDTO);
	}
	

}
