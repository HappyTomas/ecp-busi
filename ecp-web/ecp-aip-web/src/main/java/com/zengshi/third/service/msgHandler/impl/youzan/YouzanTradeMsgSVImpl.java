package com.zengshi.third.service.msgHandler.impl.youzan;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdDetialRSV;
import com.zengshi.model.TopMsgReq;
import com.zengshi.model.YouzanMsgPushEntity;
import com.zengshi.third.service.msgHandler.interfaces.IThirdMsgHandlerSV;

public class YouzanTradeMsgSVImpl implements IThirdMsgHandlerSV {
	//有赞
	
	@Resource
	private IUnpfOrdDetialRSV unpfOrdDetialRSV;
	
	@Override
	public void doAction(TopMsgReq req)throws BusinessException{
		YouzanMsgPushEntity r=(YouzanMsgPushEntity) req;
		OrderReqDTO ordReq=new OrderReqDTO();
		ordReq.setShopId(r.getShopId());
		ordReq.setPlatType(r.getPlatType());
		ordReq.setOrderId(r.getId());
		//dubbbo内部组织店铺对应的授权信息
		unpfOrdDetialRSV.dealUnpfOrderMain(ordReq);
	
	}
}
