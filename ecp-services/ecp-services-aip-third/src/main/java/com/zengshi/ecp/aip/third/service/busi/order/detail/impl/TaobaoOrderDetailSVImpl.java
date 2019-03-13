package com.zengshi.ecp.aip.third.service.busi.order.detail.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SubOrder;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.aip.third.service.busi.order.detail.interfaces.IOrderDetailSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradeGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradeGetResponse;

public class TaobaoOrderDetailSVImpl implements IOrderDetailSV{
    
    public static final String MODULE = TaobaoOrderDetailSVImpl.class.getName();

    @Resource
  	private ITokenSV defaultTokenSV;
    
	//属性返回字段设置
	private String fields;
	
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
    /**
     * 
     * querySimpleOrderDetail:订单简单详情 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    @Override
    public OrderRespDTO querySimpleOrderDetail(OrderReqDTO orderReqDTO)throws BusinessException{
		TaobaoClient client = new DefaultTaobaoClient(
				orderReqDTO.getServerUrl(), orderReqDTO.getAppkey(),
				orderReqDTO.getAppscret());

		TradeGetRequest req = new TradeGetRequest();
		
		req.setFields(fields);
	 
		req.setTid(Long.valueOf(orderReqDTO.getOrderId()));
		
		OrderRespDTO resp = new OrderRespDTO();
		try {
			// 验证参数是否正确
			req.check();			
			String token=defaultTokenSV.fetchShopToken(orderReqDTO.findBaseShopAuth());			
			// 调用服务
			TradeGetResponse rsp = client.execute(req, token);
			
			/*String objId=MongoUtil.insert(AipThirdConstants.Commons.MONGO_DB_ORDER, rsp.getBody());
			resp.setParamId(objId);
			*/
			// 返回结果解析
			if (rsp.isSuccess()) { 
				JSONObject body = JSONObject.parseObject(rsp.getBody());
				String response = body.getString("trade_get_response");
				String trade = JSONObject.parseObject(response).getString("trade");				
				resp=JSONObject.parseObject(trade,OrderRespDTO.class);
				String orders = JSONObject.parseObject(trade).getString("orders");
				JSONArray subOrders = JSONObject.parseObject(orders).getJSONArray("order");
				List<SubOrder> orderList = JSONObject.parseArray(subOrders.toJSONString(), SubOrder.class);
				resp.setSubOrders(orderList);
				resp.setOrderId(orderReqDTO.getOrderId());
				resp.setPlatType(orderReqDTO.getPlatType());
				resp.setShopId(orderReqDTO.getShopId());
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}

		} catch (ApiException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		}

		return resp;
	
	
    }

    /**
     * 
     * queryOrderDetail:订单详情 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    @Override
    public OrderRespDTO queryOrderDetail(OrderReqDTO orderReqDTO)throws BusinessException{
    	TaobaoClient client = new DefaultTaobaoClient(
				orderReqDTO.getServerUrl(), orderReqDTO.getAppkey(),
				orderReqDTO.getAppscret());

		TradeFullinfoGetRequest req = new TradeFullinfoGetRequest();
		req.setFields(fields);	 
		req.setTid(Long.valueOf(orderReqDTO.getOrderId()));		
		OrderRespDTO resp = new OrderRespDTO();
		try {
			// 验证参数是否正确
			req.check();			
			String token=defaultTokenSV.fetchShopToken(orderReqDTO.findBaseShopAuth());			
			// 调用服务
			TradeFullinfoGetResponse rsp = client.execute(req, token);
			// 返回结果解析
			if (rsp.isSuccess()) { 
				JSONObject body = JSONObject.parseObject(rsp.getBody());
				String response = body.getString("trade_fullinfo_get_response");
				String trade = JSONObject.parseObject(response).getString("trade");				
				resp=JSONObject.parseObject(trade,OrderRespDTO.class);
				String orders = JSONObject.parseObject(trade).getString("orders");
				com.alibaba.fastjson.JSONArray subOrders = JSONObject.parseObject(orders).getJSONArray("order");
				List<SubOrder> orderList = JSONObject.parseArray(subOrders.toJSONString(), SubOrder.class);
				resp.setSubOrders(orderList);
				resp.setOrderId(orderReqDTO.getOrderId());
				resp.setPlatType(orderReqDTO.getPlatType());
				resp.setShopId(orderReqDTO.getShopId());
				resp.setRespParam(rsp.getBody());
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}

		} catch (ApiException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		}

		return resp;
	
    }
}

