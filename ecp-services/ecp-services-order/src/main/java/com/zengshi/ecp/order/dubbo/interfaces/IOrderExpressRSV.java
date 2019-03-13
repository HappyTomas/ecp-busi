package com.zengshi.ecp.order.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.OrdExpressDetailResp;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrderExpressRSV {

	/**
	 *  处理快递100订阅回掉
	 * @param sign  签名字符串
	 * @param param  快递100响应参数
	 */
	public void dealOrderExpress(String sign,String param) throws Exception;
	
	/**
	 * 查询订单物流信息
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public List<OrdExpressDetailResp> queryOrderExpressDetail(String orderId) throws BusinessException;
	
	/**
	 * 按发货批次查询订阅物流信息
	 * @param orderId
	 * @return
	 * @throws BusinessException
	 */
	public List<ROrdExpressDetailsResp> queryOrderExpressDetailList(String orderId) throws BusinessException;
}
