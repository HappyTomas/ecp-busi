package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.OrdExpressDetailResp;
import com.zengshi.ecp.order.dubbo.dto.OrderExpressReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * @author l2iu
 *
 */
public interface IOrderExpressSV {

	public void saveOrderExpress(String expressNo,String expressCode,List<OrderExpressReq> expresses) throws BusinessException;
	/**
	 * 按发货批次查询订阅物流信息
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public List<ROrdExpressDetailsResp> queryOrderExpressDetailList(String orderId) throws BusinessException;
	
	/**
	 * 查询订单物流订阅信息
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public List<OrdExpressDetailResp>  queryOrderExpressDetail(String orderId) throws BusinessException;
}
