package com.zengshi.ecp.unpf.dubbo.interfaces.order;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfShopExpressReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressResp;

import java.util.List;

public interface IUnpfShopExpressRSV {
	/**
	 * 同步平台物流公司信息
	 * @author l2iu
	 * @param req
	 */
	public void dealShopExpress(OrderLogisticsReqDTO req) throws BusinessException;
	
	public List<RUnpfShopExpressResp> queryShopExpressResp(RUnpfShopExpressReq req) throws BusinessException;

	void insert(UnpfShopExpressReqDTO expressDTO);
}
