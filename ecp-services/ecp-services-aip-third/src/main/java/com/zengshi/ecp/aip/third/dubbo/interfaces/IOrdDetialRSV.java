package com.zengshi.ecp.aip.third.dubbo.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * @author l2iu
 *
 */
public interface IOrdDetialRSV {
	/**
     * 
     * querySimpleOrderDetail:订单简单详情 <br/> 
     * @author l2iu
	 * @param orderReqDTO
	 * @return
	 * @throws BusinessException
	 */
	 public OrderRespDTO querySimpleOrderDetail(OrderReqDTO orderReqDTO) throws BusinessException;
	 	 
	/**
     * 
     * queryOrderDetail:订单详情 <br/> 
     * @author l2iu
	 * @param orderReqDTO
     * @return 
     * @since JDK 1.7
     */
	 public OrderRespDTO queryOrderDetail(OrderReqDTO orderReqDTO)throws BusinessException;
}
