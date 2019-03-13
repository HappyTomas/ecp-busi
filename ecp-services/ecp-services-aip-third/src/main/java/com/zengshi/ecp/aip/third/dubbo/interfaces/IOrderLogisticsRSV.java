package com.zengshi.ecp.aip.third.dubbo.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderLogisticsRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * @author l2iu
 *
 */
public interface IOrderLogisticsRSV {
	/**
	 * 查询物流公司接口
	 * @param orderLogisticsReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public OrderLogisticsRespDTO queryLogisticsCompany(OrderLogisticsReqDTO orderLogisticsReqDTO)throws BusinessException;
}
