package com.zengshi.ecp.unpf.dubbo.interfaces.order;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * @author l2iu
 *
 */
public interface IUnpfOrdDetialRSV {
	
	/**
	 * 根据交易订单号获取交易详情并保存（调用获取单笔交易的部分信息接口,性能高）
	 * @param req
	 * @throws BusinessException
	 */
	public void dealSimpleUnpfOrderMain(OrderReqDTO req) throws BusinessException;
	
	/**
	 * 根据交易订单号获取交易详情并保存（调用获取单笔交易的详细信息接口）
	 * @param req
	 * @throws BusinessException
	 */
	public void dealUnpfOrderMain(OrderReqDTO req) throws BusinessException;
}
