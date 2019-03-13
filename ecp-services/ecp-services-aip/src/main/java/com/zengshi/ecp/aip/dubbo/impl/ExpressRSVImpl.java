package com.zengshi.ecp.aip.dubbo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.zengshi.ecp.aip.dubbo.dto.ExpressQueryReq;
import com.zengshi.ecp.aip.dubbo.dto.ExpressQueryResp;
import com.zengshi.ecp.aip.dubbo.dto.ExpressRssReq;
import com.zengshi.ecp.aip.dubbo.dto.ExpressRssResp;
import com.zengshi.ecp.aip.dubbo.interfaces.IExpressRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.alibaba.fastjson.JSONObject;

public class ExpressRSVImpl implements IExpressRSV{

	@Override
	public ExpressRssResp rss(ExpressRssReq req) throws Exception{
		// TODO Auto-generated method stub
		
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("schema", "json");
		param.put("param", req.getJsonParam());
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);
		//"http://www.kuaidi100.com/poll"
		final PostMethod  method = new PostMethod (req.getRequestUrl());
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		method.setRequestBody(assembleRequestParams(param));
		String result = "";
		ExpressRssResp resp = null;
		try {
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), "UTF-8");					
			resp = JSONObject.parseObject(result, ExpressRssResp.class);	
			resp.setReqParam(req.getJsonParam());//原始入参，用于业务代码日志保存
			resp.setRespParam(result);//原始出参，用于业务代码日志保存
		} catch (Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		return resp;
	}

	@Override
	public ExpressQueryResp queryExpressInfo(ExpressQueryReq req) throws BusinessException{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 组装http请求参数
	 * 
	 * @param params
	 * @param menthod
	 * @return
	 */
	private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}

		return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
	}

}
