package com.zengshi.ecp.unpf.service.busi.order.impl;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfErpOrderSV;

/**
 * 用于扩展，核心不做任何处理
 * @author l2iu
 *
 */
public class UnpfErpOrderSVImpl implements IUnpfErpOrderSV{

	@Override
	public void saveErpOrder(UnpfOrdMain unpfOrdMain,List<RUnpfOrdSubReq> subOrders,String nickName) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


}
