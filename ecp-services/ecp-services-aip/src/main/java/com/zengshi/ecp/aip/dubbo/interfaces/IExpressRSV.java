package com.zengshi.ecp.aip.dubbo.interfaces;

import com.zengshi.ecp.aip.dubbo.dto.ExpressQueryReq;
import com.zengshi.ecp.aip.dubbo.dto.ExpressQueryResp;
import com.zengshi.ecp.aip.dubbo.dto.ExpressRssReq;
import com.zengshi.ecp.aip.dubbo.dto.ExpressRssResp;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * 快递100接口
 * @author l2iu
 *
 */
public interface IExpressRSV {

	/**
	 * 订阅订单物流信息
	 * @return
	 */
	public ExpressRssResp rss(ExpressRssReq req) throws Exception;
	
	/**
	 * 查询订单物流详情
	 * @param req
	 * @return
	 */
	public ExpressQueryResp queryExpressInfo(ExpressQueryReq req) throws BusinessException;
}
