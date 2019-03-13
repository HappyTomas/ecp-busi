package com.zengshi.ecp.aip.third.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrdDetialRSV;
import com.zengshi.ecp.aip.third.service.busi.order.detail.interfaces.IOrderDetailSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

public class OrdDetailRSVImpl implements IOrdDetialRSV {

	@Resource
	private IOrderDetailSV defaultOrderDetailSV;
	
	@Override
	public OrderRespDTO querySimpleOrderDetail(OrderReqDTO orderReqDTO) throws BusinessException {
		// TODO Auto-generated method stub		
		if(StringUtil.isEmpty(orderReqDTO)){
			String value="AIPTHIRD.100017";
			throw new BusinessException(value);
		}
		if(StringUtil.isBlank(orderReqDTO.getOrderId())){
			String value="AIPTHIRD.100018";
			throw new BusinessException(value);
		}
		OrderRespDTO resp = new OrderRespDTO();
		try{
			resp = defaultOrderDetailSV.querySimpleOrderDetail(orderReqDTO);
		}catch(Exception e){
			String value = "AIPTHIRD.ERRORS.100001";
			throw new BusinessException(value);
		}
		return resp;
	}

	@Override
	public OrderRespDTO queryOrderDetail(OrderReqDTO orderReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		
		if(StringUtil.isEmpty(orderReqDTO)){
			String value="AIPTHIRD.100017";
			throw new BusinessException(value);
		}
		if(StringUtil.isBlank(orderReqDTO.getOrderId())){
			String value="AIPTHIRD.100018";
			throw new BusinessException(value);
		}
		OrderRespDTO resp = new OrderRespDTO();
		try{
			resp = defaultOrderDetailSV.queryOrderDetail(orderReqDTO);
		}catch(Exception e){
			String value = "AIPTHIRD.ERRORS.100001";
			throw new BusinessException(value);
		}
		return resp;
	}

}
