package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.model.GdsGuessYL;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessHomePageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsGuessYLSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 猜你喜欢dubbo服务<br>
 * Date:2015年9月6日上午10:51:41  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsGuessYLRSVImpl extends AbstractRSVImpl implements IGdsGuessYLRSV{

	@Resource
	private IGdsGuessYLSV gdsGuessYLSV;
	
	/**
	 * 添加猜你喜欢
	 * 其中商品编码,单品编码，单品名称必传
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV#saveGdsGuessYL(com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO)
	 */
	@Override
	public void saveGdsGuessYL(GdsGuessYLReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getGdsId(), "reqDTO.gdsId");
		GdsGuessYL gdsGuessYL=new GdsGuessYL();
		ObjectCopyUtil.copyObjValue(reqDTO, gdsGuessYL, null, false);
		gdsGuessYL.setCreateStaff(reqDTO.getStaff().getId());
		gdsGuessYL.setCreateTime(DateUtil.getSysDate());
		gdsGuessYL.setUpdateStaff(reqDTO.getStaff().getId());
		gdsGuessYL.setUpdateTime(DateUtil.getSysDate());
		gdsGuessYLSV.saveGdsGuessYL(gdsGuessYL);
	}
	
	@Override
	public Integer editGdsGuessYL(GdsGuessYLReqDTO reqDTO){
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		GdsGuessYL gdsGuessYL=new GdsGuessYL();
		ObjectCopyUtil.copyObjValue(reqDTO, gdsGuessYL, null, false);
		gdsGuessYL.setUpdateStaff(reqDTO.getStaff().getId());
		gdsGuessYL.setUpdateTime(DateUtil.getSysDate());
		return gdsGuessYLSV.editGdsGuessYL(gdsGuessYL);
	}

	/**
	 * 判断猜你记录是否存在
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV#queryExist(com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO)
	 */
	@Override
	public Boolean queryExist(GdsGuessYLReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getGdsId(), "reqDTO.gdsId");
		
		return gdsGuessYLSV.queryExist(reqDTO.getCatgCode(), reqDTO.getGdsId(), reqDTO.getSkuId(), GdsConstants.Commons.STATUS_VALID);
	}

	/**
	 * 删除猜你喜欢
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV#deleteGdsGuessYL(com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO)
	 */
	@Override
	public Integer deleteGdsGuessYL(GdsGuessYLReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		
		return gdsGuessYLSV.deleteGdsGuessYL(reqDTO.getId(), reqDTO.getStaff().getId());
	}

	
	/**
	 * 批量删除猜你喜欢
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV#deleteGdsGuessYL(com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO)
	 */
	@Override
	public Integer executeBatchDeleteGdsGuessYL(GdsGuessYLReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getIds(), "reqDTO.ids");
		return gdsGuessYLSV.executeBatchDeleteGdsGuessYL(reqDTO.getIds(), reqDTO.getStaff().getId());
	}

	/**
	 * 分页查询猜你喜欢信息
	 * 入参可选，分类编码，单品名称
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV#queryGdsGuessYLRespDTOPaging(com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO)
	 */
	@Override
	public PageResponseDTO<GdsGuessYLRespDTO> queryGdsGuessYLRespDTOPaging(
			GdsGuessYLReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		return gdsGuessYLSV.queryGdsGuessYLRespDTOPaging(reqDTO);
	}

	/**
	 * 查询单个猜你喜欢信息
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV#queryGdsGuessYLByPK(com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO)
	 */
	@Override
	public GdsGuessYLRespDTO queryGdsGuessYLByPK(GdsGuessYLReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		return gdsGuessYLSV.queryGdsGuessYLByPK(reqDTO.getId());
	}

    @Override
    public GdsGuessHomePageRespDTO queryGdsGuessForHomePage(GdsGuessYLReqDTO dto)
            throws BusinessException {
        return gdsGuessYLSV.queryGdsGuessForHomePage(dto);
    }

}

