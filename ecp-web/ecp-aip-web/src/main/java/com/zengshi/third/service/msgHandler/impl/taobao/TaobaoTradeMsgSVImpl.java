package com.zengshi.third.service.msgHandler.impl.taobao;

import javax.annotation.Resource;

import jodd.util.StringUtil;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdDetialRSV;
import com.zengshi.model.TaobaoOrderReq;
import com.zengshi.model.TopMsgReq;
import com.zengshi.third.service.msgHandler.interfaces.IThirdMsgHandlerSV;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TaobaoTradeMsgSVImpl implements IThirdMsgHandlerSV {
	//淘宝 & tmall 
	@Resource
	private IUnpfOrdDetialRSV unpfOrdDetialRSV;
	
	@Override
	public void doAction(TopMsgReq req)throws BusinessException{
		TaobaoOrderReq r=(TaobaoOrderReq) req;
		OrderReqDTO ordReq=new OrderReqDTO();
		ordReq.setShopId(r.getShopId());
		ordReq.setPlatType(r.getPlatType());
		ordReq.setAuthId(req.getShopAuthId());
		
		//解析content json
	/*	{
		    "buyer_nick":"helloworld",
		    "oid":"987654321",
		    "payment":"100.00",
		    "seller_nick":"天猫超市",
		    "status":"TRADE_CREATE",
		    "tid":"123456789",
		    "type":"fixed"
		}*/
		
		if(StringUtil.isEmpty(r.getContent())){
			throw new BusinessException("消息content为空");
		}
		JSONObject jsob=JSON.parseObject(r.getContent());
		ordReq.setOrderId(jsob.getString("tid"));
		//dubbbo内部组织店铺对应的授权信息
		unpfOrdDetialRSV.dealUnpfOrderMain(ordReq);
	}
}
