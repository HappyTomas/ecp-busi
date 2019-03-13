/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsArrmsgRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl 
 * Date:2015-9-21上午10:33:55 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsArrmsgRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsArrmsgSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-21上午10:33:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsArrmsgRSVImpl extends AbstractRSVImpl implements IGdsArrmsgRSV {

	@Resource(name="gdsArrmsgSV")
	private IGdsArrmsgSV gdsArrmsgSV;
	
	/** 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsArrmsgRSV#saveGdsArrmsg(com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO) 
	 */
	@Override
	public void saveGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true,new String[]{"reqDTO"});
		paramNullCheck(reqDTO.getSkuId(),new String[]{"reqDTO.skuId"});
		gdsArrmsgSV.saveGdsArrmsg(reqDTO);
	}

	/** 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsArrmsgRSV#deleteGdsArrmsg(com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO) 
	 */
	@Override
	public void deleteGdsArrmsg(GdsArrmsgReqDTO reqDTO)
			throws BusinessException {
		gdsArrmsgSV.deleteGdsArrmsg(reqDTO);
	}

	/** 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsArrmsgRSV#sendNotice(com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO) 
	 */
	@Override
	public void sendNotice(GdsArrmsgReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		gdsArrmsgSV.sendNotice(reqDTO);
		
	}

	/** 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsArrmsgRSV#queryGdsArrmsg(com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO) 
	 */
	@Override
	public PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsg(
			GdsArrmsgReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		return gdsArrmsgSV.queryGdsArrmsg(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsgFromStaff(
			GdsArrmsgReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		return gdsArrmsgSV.queryGdsArrmsgFromStaff(reqDTO);
	}
	
}

