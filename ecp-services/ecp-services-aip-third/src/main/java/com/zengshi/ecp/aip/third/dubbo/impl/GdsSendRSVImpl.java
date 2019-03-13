package com.zengshi.ecp.aip.third.dubbo.impl;

import java.util.HashMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IGdsSendRSV;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;
/**
 * 
 * send:商品推送 <br/> 
 * @author huangjx
 * @return 
 * @since JDK 1.7
 */
public class GdsSendRSVImpl extends AipAbstractRSVImpl implements IGdsSendRSV {
	
	@Resource
	private IGdsSendSV defaultGdsSendSV;
	
    @Override
    public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO) throws BusinessException { 
    	return defaultGdsSendSV.send(gdsSendReqDTO);
    }
 
}

