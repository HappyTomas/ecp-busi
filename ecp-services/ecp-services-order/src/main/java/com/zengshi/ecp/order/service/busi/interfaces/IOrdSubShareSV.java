package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdSubShare;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareReq;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareResp;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdSubShareSV {

	/**
     * 
     * saveOrdSubShare:(这里用一句话描述这个方法的作用). <br/> 
     * @author liuwy 
     * @param ordSubShare 
     * @since JDK 1.6
     * @throws BusinessException
     */
	public void saveOrdSubShare(OrdSubShare ordSubShare) throws  BusinessException;
	
	/**
	 * 修改分享订单数据状态
     * @author liuwy 
     * @param req 
     * @since JDK 1.6
	 * @throws BusinessException
	 */
	public void updateOrdSubShareStatus(OrdSubShareReq req) throws  BusinessException;
	
	/**
	 * 根据订单编号查询分享订单数据
     * @author liuwy 
     * @param req 
     * @since JDK 1.6
	 * @throws BusinessException
	 */
	public List<OrdSubShareResp> queryOrdSubShareList(OrdSubShareReq req) throws  BusinessException;
}
