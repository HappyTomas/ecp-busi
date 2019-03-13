/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsTypeRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl 
 * Date:2015年8月27日下午5:49:32 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsLogRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.IGdsLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午5:49:32 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsLogRSVImpl extends AbstractRSVImpl implements IGdsLogRSV {

	@Resource(name = "gdsLogSV")
	private IGdsLogSV gdsLogSV;

	

	@Override
	public List<GdsLogRespDTO> queryGdsLogRespDTO(GdsLogReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
//		paramNullCheck(id, true);
//		paramNullCheck(id.getId(), false, "reqDTO.id");
		paramNullCheck(dto, false);
		try {
			return gdsLogSV.queryGdsLogRespDTO(dto);
		} catch (Exception e) {
			LogUtil.info(MODULE, "查询gdsloglist");
			
		}
		return null;
	}

}
