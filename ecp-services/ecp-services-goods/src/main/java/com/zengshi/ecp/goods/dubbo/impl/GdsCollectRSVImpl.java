package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:商品收藏dubbo服务 <br>
 * Date:2015年9月6日上午9:53:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsCollectRSVImpl extends AbstractRSVImpl implements IGdsCollectRSV{

	@Resource
	private IGdsCollectSV gdsCollectSV;
	
	/**
	 * 添加收藏
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV#addGdsCollect(com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO)
	 */
	@Override
	public Long addGdsCollect(GdsCollectReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getSkuId(), "reqDTO.skuId");
		return gdsCollectSV.addGdsCollect(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByGds(
			GdsCollectReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getGdsId(), "reqDTO.gdsId");
		return gdsCollectSV.queryGdsCollectRespDTOPagingByGds(reqDTO);
	}
	
	@Override
	public long countGdsCollectByShop(GdsCollectReqDTO reqDTO)
	        throws BusinessException {
	    paramNullCheck(reqDTO,true, "reqDTO");
        paramNullCheck(reqDTO.getShopId(), "reqDTO.shopId");
        paramNullCheck(reqDTO.getGdsId(), "reqDTO.gdsId");
        paramNullCheck(reqDTO.getSkuId(), "reqDTO.skuId");
	    return gdsCollectSV.countGdsCollectByShop(reqDTO);
	}
	
	@Override
    public PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByShop(
            GdsCollectReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO,true, "reqDTO");
        paramNullCheck(reqDTO.getShopId(), "reqDTO.shopId");
        return gdsCollectSV.queryGdsCollectRespDTOPagingByShop(reqDTO);
    }
	
	@Override
    public PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByStaff(
            GdsCollectReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO,true, "reqDTO");
        reqDTO.setStaffId(reqDTO.getStaff().getId());
        return gdsCollectSV.queryGdsCollectRespDTOPagingByStaff(reqDTO);
    }

	/**
	 * 删除收藏
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV#addGdsCollect(com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO)
	 */
	@Override
	public int deleteGdsCollectByPK(GdsCollectReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		return gdsCollectSV.deleteGdsCollectByPK(reqDTO);
	}

	/**
	 * 编辑收藏
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV#editGdsCollect(com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO)
	 */
	@Override
	public int editGdsCollect(GdsCollectReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		return gdsCollectSV.editGdsCollect(reqDTO);
	}

	/**
	 * 查询单个收藏
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV#editGdsCollect(com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO)
	 */
	@Override
	public GdsCollectRespDTO queryGdsCollectByPK(GdsCollectReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		return gdsCollectSV.queryGdsCollectByPK(reqDTO);
	}

}

