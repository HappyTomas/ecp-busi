package com.zengshi.ecp.aip.third.service.busi.order.logistics.impl;

import java.util.List;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.Company;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderLogisticsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.order.logistics.interfaces.IOrderLogisticsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.LogisticsCompaniesGetRequest;
import com.taobao.api.response.LogisticsCompaniesGetResponse;

public class TaobaoOrderLogisticsSVImpl implements IOrderLogisticsSV {
	
	public static final String MODULE = TaobaoOrderLogisticsSVImpl.class.getName();
	//属性返回字段设置
	private String fields;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	@Override
	public OrderLogisticsRespDTO queryLogisticsCompany(OrderLogisticsReqDTO orderLogisticsReqDTO)
			throws BusinessException {
		// TODO Auto-generated method stub
		TaobaoClient client = new DefaultTaobaoClient(
				orderLogisticsReqDTO.getServerUrl(), orderLogisticsReqDTO.getAppkey(),
				orderLogisticsReqDTO.getAppscret());
		
		LogisticsCompaniesGetRequest req = new LogisticsCompaniesGetRequest();		
		req.setFields("id,code,name,reg_mail_no");
		if(StringUtil.isNotEmpty(orderLogisticsReqDTO.getIsRecommended())&&orderLogisticsReqDTO.getIsRecommended().booleanValue()==true){
			req.setIsRecommended(orderLogisticsReqDTO.getIsRecommended());
			req.setOrderMode(orderLogisticsReqDTO.getOrderMode());
		}		
		OrderLogisticsRespDTO resp = new OrderLogisticsRespDTO();
		try {
			LogisticsCompaniesGetResponse rsp = client.execute(req);
			if(rsp.isSuccess()){
				JSONObject body = JSONObject.parseObject(rsp.getBody());
				String response = body.getString("logistics_companies_get_response");
				String logistics_companies = JSONObject.parseObject(response).getString("logistics_companies");
				JSONArray companyArray = JSONObject.parseObject(logistics_companies).getJSONArray("logistics_company");
				List<Company> companies = JSONObject.parseArray(companyArray.toString(),Company.class);
				resp.setCompanies(companies);
			}else{
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
