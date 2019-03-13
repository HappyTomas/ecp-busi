package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import java.util.List;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfShopExpress;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressResp;

public interface IUnpfShopExpressSV {
	/**
	 * 同步平台物流公司信息
	 * @author l2iu
	 * @param req
	 */
	public void dealShopExpress(OrderLogisticsReqDTO req) throws BusinessException;
	
	
	/**
	 * 根据店铺编号和平台类别获取物流公司列表
	 * @param req
	 * @return
	 * @throws BusinessException
	 */
	public List<RUnpfShopExpressResp> queryShopExpressResp(RUnpfShopExpressReq req) throws BusinessException;
	
	/**
	 * 根据店铺编号和平台类别和物流公司编号获取物流公司列表
	 * @param req
	 * @return
	 * @throws BusinessException
	 */
	public RUnpfShopExpressResp getShopExpressResp(RUnpfShopExpressReq req) throws BusinessException;

	void insert(UnpfShopExpress express);
}
