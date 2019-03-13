package com.zengshi.ecp.unpf.dubbo.impl.order;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdDetialRSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdMainSV;

public class UnpfOrdDetialRSVImpl implements IUnpfOrdDetialRSV{
	
	@Resource
	private IUnpfOrdMainSV unpfOrdMainSV;
	
	@Override
	public void dealSimpleUnpfOrderMain(OrderReqDTO req) throws BusinessException {
		// TODO Auto-generated method stub		
		unpfOrdMainSV.saveSimpleUnpfOrdMain(req);
	}

	@Override
	public void dealUnpfOrderMain(OrderReqDTO req) throws BusinessException {
		// TODO Auto-generated method stub
		unpfOrdMainSV.saveUnpfOrdMain(req);		
	}

}
