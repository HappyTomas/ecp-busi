package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IUnpfTmOrdMainSV {

	public void saveUnpfTmOrdMain(OrderRespDTO orderDetial) throws BusinessException;;
	
	public void updateUnpfTmOrdMain(OrderRespDTO orderDetial) throws BusinessException;;
	
}
