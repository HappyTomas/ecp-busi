package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.OrdExpressLogReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdExpressReqLogRSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdExpressReqLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class OrdExpressReqLogRSVImpl implements IOrdExpressReqLogRSV {

	@Resource
	private IOrdExpressReqLogSV ordExpressReqLogSV;
	@Override
	public void saveOrdExpressLog(OrdExpressLogReq log) throws BusinessException {
		// TODO Auto-generated method stub
		
		ordExpressReqLogSV.saveOrdExpressLog(log);
	}

}
