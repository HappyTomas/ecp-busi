package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.OrdExpressLogReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdExpressReqLogSV {

	public void saveOrdExpressLog(OrdExpressLogReq log) throws BusinessException;
}
