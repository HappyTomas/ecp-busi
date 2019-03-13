package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.CartItemPriceInfo;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPriceRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:商品对外价格服务 <br>
 * Date:2015年9月28日上午11:13:37 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceRSVImpl extends AbstractRSVImpl implements IGdsPriceRSV {

	@Autowired(required = false)
	private IGdsPriceSV gdsPriceSV;

	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	/**
	 * 
	 * TODO 获取所有价格类型.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPriceRSV#queryAllPriceType(com.zengshi.ecp.server.front.dto.BaseInfo)
	 */
	@Override
	public List<GdsPriceTypeRespDTO> queryAllPriceType(BaseInfo info)
			throws BusinessException {
		return gdsPriceSV.queryAllPriceType();
	}

	/**
	 * 
	 * TODO 计算购物车商品价格.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPriceRSV#caculate(com.zengshi.ecp.general.order.dto.ROrdCartChkRequest)
	 */
	public Map<Long, CartItemPriceInfo> caculate(ROrdCartsCommRequest reqDtos) {
		paramNullCheck(reqDtos, "reqDtos");
		paramNullCheck(reqDtos.getStaffId(), "reqDtos.staffId");
		return gdsPriceSV.caculatePrice(reqDtos);
	}

	/**
	 * 
	 * TODO 计算购物车商品价格.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPriceRSV#caculate(com.zengshi.ecp.general.order.dto.ROrdCartChkRequest)
	 */
	@Override
	public Long getDeaultPrice(GdsSkuInfoReqDTO skuInfoReqDTO)
			throws BusinessException {
		paramNullCheck(skuInfoReqDTO, "skuInfoReqDTO");
		paramNullCheck(skuInfoReqDTO.getGdsId(), "skuInfoReqDTO.gdsId");
		paramNullCheck(skuInfoReqDTO.getId(), "skuInfoReqDTO.id");
		return gdsPriceSV.getDeaultPrice(skuInfoReqDTO);
	}

    @Override
    public GdsPriceCalRespDTO caculate(GdsPriceCalReqDTO reqDto) throws BusinessException {
        paramNullCheck(reqDto, "reqDto");
        paramNullCheck(reqDto.getStaff(), "reqDtos.staff");
        paramNullCheck(reqDto.getGdsId(), "reqDtos.gdsId");
        paramNullCheck(reqDto.getSkuId(), "reqDtos.skuId");
        paramNullCheck(reqDto.getAmount(), "reqDtos.amount");
        return gdsPriceSV.caculatePrice(reqDto);
    }

}
