package com.zengshi.ecp.order.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.OrdExpressDetailResp;
import com.zengshi.ecp.order.dubbo.dto.OrderExpressReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderExpressRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrderExpressSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

public class OrderExpressRSVImpl implements IOrderExpressRSV {

	private static final String MODULE = OrderExpressRSVImpl.class.getName();
	@Resource
	private IOrderExpressSV orderExpressSV;
	@Override
	public void dealOrderExpress(String sign, String param) throws Exception {
		// TODO Auto-generated method stub
		//判断运单状态并相应处理
		JSONObject body = JSONObject.parseObject(param);	
		String status =  body.getString("status");
		String comNew =  body.getString("comNew");
		String com =  body.getString("com");
		if(status.equals("about")){			
			if(StringUtil.isBlank(comNew)){
				//需重新订阅，但不进行其他处理，返回成功（目前不做处理）	
				 LogUtil.info(MODULE, "物流公司编码不正确");
		         throw new BusinessException("物流公司编码不正确"); 
			}else{
				//不需重新订阅，但需要将错误的快递公司编码进行修改	（目前不做处理）		
				com = comNew;
			}			
		}
		//继续进行物流数据保存处理
		String lastResult = body.getString("lastResult");
		JSONObject lastResultBody = JSONObject.parseObject(lastResult);	
		String nu = lastResultBody.getString("nu");//运单号
		String data = lastResultBody.getString("data");//需保持的内容数据
		List<OrderExpressReq> expresses = JSONObject.parseArray(data, OrderExpressReq.class);
		orderExpressSV.saveOrderExpress(nu, com, expresses);		
	}
	
	@Override
	public List<OrdExpressDetailResp> queryOrderExpressDetail(String orderId) throws BusinessException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(orderId)){
			 LogUtil.info(MODULE, "订单编号不能为空");
	            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
		}
		try{
			return orderExpressSV.queryOrderExpressDetail(orderId);
		}catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310033);
        }
		
	}

	@Override
	public List<ROrdExpressDetailsResp> queryOrderExpressDetailList(String orderId) throws BusinessException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(orderId)){
			 LogUtil.info(MODULE, "订单编号不能为空");
	            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
		}
		try{
			return orderExpressSV.queryOrderExpressDetailList(orderId);
		}catch (BusinessException be) {
           LogUtil.error(MODULE, "===业务异常===",be);
           throw be;
       } catch (Exception e) {
           LogUtil.error(MODULE, "===系统异常===",e);
           throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310033);
       }
	}
}
