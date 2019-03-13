package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.OrdExpressLogReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdExpressReqLogRSV {
	public void saveOrdExpressLog(OrdExpressLogReq log) throws BusinessException;
}
