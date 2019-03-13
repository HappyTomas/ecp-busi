package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;

public interface IUnpfErpOrderSV {
	public void saveErpOrder(UnpfOrdMain unpfOrdMain,List<RUnpfOrdSubReq> subOrders,String nickName) throws BusinessException;
}
