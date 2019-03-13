package com.zengshi.ecp.unpf.dubbo.interfaces.gdssend;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendRespDTO;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月17日 上午9:08:23 
* @version 1.0 
* Copyright (c) 2016 AI 
*  */
public interface IUnpfGdsSendRSV {

	/**
	* 推送 
	* 
	* @author huangjx
	* @param GdsSendReqDTO
	* @return 
	* @throws 
	*/
	public GdsSendRespDTO send(GdsSendReqDTO gdsSendReqDTO) throws BusinessException;
	
	}


