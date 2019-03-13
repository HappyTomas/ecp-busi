package com.zengshi.ecp.aip.third.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderLogisticsRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrderLogisticsRSV;
import com.zengshi.ecp.aip.third.service.busi.order.logistics.interfaces.IOrderLogisticsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * @author l2iu
 *
 */
public class OrderLogisticsRSVImpl implements IOrderLogisticsRSV {

	@Resource
	private IOrderLogisticsSV defaultOrderLogisticsSV;
	
	@Override
	public OrderLogisticsRespDTO queryLogisticsCompany(OrderLogisticsReqDTO orderLogisticsReqDTO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return defaultOrderLogisticsSV.queryLogisticsCompany(orderLogisticsReqDTO);
	}

}
