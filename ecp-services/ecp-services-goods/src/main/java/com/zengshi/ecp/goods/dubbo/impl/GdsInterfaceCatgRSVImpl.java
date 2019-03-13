/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsInterfaceCatgRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl 
 * Date:2015-10-27上午10:42:47 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceCatg;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceCatgRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsInterfaceCatgSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-27上午10:42:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsInterfaceCatgRSVImpl extends AbstractRSVImpl implements
		IGdsInterfaceCatgRSV {
	@Resource
	private IGdsInterfaceCatgSV gdsInterfaceCatgSV;

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceCatgRSV#queryCategoryByOriginCatgCode(com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO) 
	 */
	@Override
	public GdsCategoryRespDTO queryCategoryByOriginCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException {
		return gdsInterfaceCatgSV.queryCategoryByOriginCatgCode(reqDTO);
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceCatgRSV#saveGdsInterfaceCatg(com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO) 
	 */
	@Override
	public void saveGdsInterfaceCatg(GdsInterfaceCatgReqDTO reqDTO)
			throws BusinessException {
		gdsInterfaceCatgSV.saveGdsInterfaceCatg(reqDTO);
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceCatgRSV#queryInterfaceCatgByOriginAndOriginCatgCode(com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO) 
	 */
	@Override
	public GdsInterfaceCatgRespDTO queryInterfaceCatgByOriginAndOriginCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException {
		GdsInterfaceCatg record = gdsInterfaceCatgSV.queryInterfaceCatgByOriginAndOriginCatgCode(reqDTO);
		if(null != record){
			GdsInterfaceCatgRespDTO respDTO = new GdsInterfaceCatgRespDTO();
			ObjectCopyUtil.copyObjValue(record, respDTO, null, true);
			return respDTO;
		}
		return null;
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceCatgRSV#queryInterfaceCatgByCatgCode(com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO) 
	 */
	@Override
	public GdsInterfaceCatgRespDTO queryInterfaceCatgByCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException {
		GdsInterfaceCatg record = gdsInterfaceCatgSV.queryInterfaceCatgByCatgCode(reqDTO);
		if(null != record){
			GdsInterfaceCatgRespDTO respDTO = new GdsInterfaceCatgRespDTO();
			ObjectCopyUtil.copyObjValue(record, respDTO, null, true);
			return respDTO;
		}
		return null;
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceCatgRSV#updateInterfaceCatg(com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO) 
	 */
	@Override
	public void updateInterfaceCatg(GdsInterfaceCatgReqDTO reqDTO)
			throws BusinessException {
		gdsInterfaceCatgSV.updateInterfaceCatg(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsInterfaceCatgRespDTO> queryPaging(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException {
		return gdsInterfaceCatgSV.queryPaing(reqDTO);
	}
	
	
	

}

