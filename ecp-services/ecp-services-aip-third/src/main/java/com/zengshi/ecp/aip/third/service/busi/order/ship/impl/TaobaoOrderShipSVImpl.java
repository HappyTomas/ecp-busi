package com.zengshi.ecp.aip.third.service.busi.order.ship.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.plexus.util.StringUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.aip.third.service.busi.order.ship.interfaces.IOrderShipSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.response.LogisticsOfflineSendResponse;

public class TaobaoOrderShipSVImpl implements IOrderShipSV{
    public static final String MODULE = TaobaoOrderShipSVImpl.class.getName();
    
    @Resource
  	private ITokenSV defaultTokenSV;
    
	@Override
	public Map<String,Object> ship(OrderShipReqDTO req) throws BusinessException {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		TaobaoClient client = new DefaultTaobaoClient(
				req.getServerUrl(), req.getAppkey(),
				req.getAppscret());
		LogisticsOfflineSendRequest offlineSendReq = new LogisticsOfflineSendRequest();
		offlineSendReq.setTid(req.getTid());//必填
		offlineSendReq.setCompanyCode(req.getCompanyCode());//必填
		offlineSendReq.setOutSid(req.getOutSid());//必填
		
		offlineSendReq.setSubTid(req.getSubTid());
		if(StringUtils.isNotBlank(req.getSubTid())){
			offlineSendReq.setIsSplit(1L);	//拆单
		}else{
			offlineSendReq.setIsSplit(req.getIsSplit());	
		}
		offlineSendReq.setSellerIp(req.getSellerIp());		
		offlineSendReq.setSenderId(req.getSenderId());
		try {			
			offlineSendReq.check();			
			String token=defaultTokenSV.fetchShopToken(req.findBaseShopAuth());	
			LogisticsOfflineSendResponse rsp  = client.execute(offlineSendReq, token);
			//LogUtil.warn(MODULE,"Offine line aip body:" + rsp.getBody());
			if(rsp.isSuccess()) { 
				map.put("result", true);
			}else{				
				LogUtil.error(MODULE, rsp.getBody());
				map.put("result", false);	
				JSONObject body = JSONObject.parseObject(rsp.getBody());
				String response = body.getString("error_response");
				body = JSONObject.parseObject(response);
				String errMsg = body.getString("sub_msg");
				if(StringUtil.isBlank(errMsg)){
					errMsg = body.getString("msg");
				}
				map.put("msg", errMsg);
				LogUtil.warn(MODULE,"Offine line error:" + errMsg);
			}
			return map;
		} catch (ApiException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		}
	}

}
