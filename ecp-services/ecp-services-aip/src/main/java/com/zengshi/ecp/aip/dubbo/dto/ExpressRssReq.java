package com.zengshi.ecp.aip.dubbo.dto;

import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.util.Hash;

public class ExpressRssReq extends BaseInfo{

	private String orderId;
	private Long shopId;
	private String company;//必填  订阅的快递公司的编码，一律用小写字母
	private String number;//必填    订阅的快递单号
	private String key;//必填  授权码，签合同后发放
	private String callbackurl;//必填   回调接口的地址
	private String requestUrl;//必填 
	
	private String from;//非必填  出发地城市，省-市-区，
	private String to;//非必填	目的地城市，省-市-区
	private String salt;//非必填  签名用随机字符串（可选）
	private String resultv2;//添加此字段，值设为1，则表示开通行政区域解析功能（仅对开通签收状态服务用户有效），见章2.3.1《回调请求》
	private String autoCom; //添加此字段且将此值设为1，则表示开始智能判断单号所属公司的功能，开启后，company字段可为空,即只传运单号（number字段），我方收到后会根据单号判断出其所属的快递公司（即company字段）。建议只有在无法知道单号对应的快递公司（即company的值）的情况下才开启此功能；
	private String interCom;  //添加此字段,值设为1，则表示开启国际版。
	private String departureCountry;//非必填,默认为中国
	private String departureCom;//非必填
	private String destinationCountry;//非必填,默认为中国
	private String destinationCom;//非必填
	
	
	private String jsonParam = "";
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCallbackurl() {
		return callbackurl;
	}
	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getResultv2() {
		return resultv2;
	}
	public void setResultv2(String resultv2) {
		this.resultv2 = resultv2;
	}
	public String getAutoCom() {
		return autoCom;
	}
	public void setAutoCom(String autoCom) {
		this.autoCom = autoCom;
	}
	public String getInterCom() {
		return interCom;
	}
	public void setInterCom(String interCom) {
		this.interCom = interCom;
	}
	public String getDepartureCountry() {
		return departureCountry;
	}
	public void setDepartureCountry(String departureCountry) {
		this.departureCountry = departureCountry;
	}
	public String getDepartureCom() {
		return departureCom;
	}
	public void setDepartureCom(String departureCom) {
		this.departureCom = departureCom;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	public String getDestinationCom() {
		return destinationCom;
	}
	public void setDestinationCom(String destinationCom) {
		this.destinationCom = destinationCom;
	}
	
	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	
	/**
	 * 转成快递100接口规定入参Json
	 * @return
	 */
	public String getJsonParam(){
		if(StringUtil.isBlank(jsonParam)){		
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("company", this.company);
			jsonMap.put("number", this.number);
			jsonMap.put("key", this.key);
			if(StringUtil.isNotBlank(this.from)){
				jsonMap.put("from", this.from);
			}
			if(StringUtil.isNotBlank(this.to)){
				jsonMap.put("to", this.to);
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("callbackurl", this.callbackurl);
			if(StringUtil.isNotBlank(this.salt)){
				params.put("salt", this.salt);
			}
			if(StringUtil.isNotBlank(this.resultv2)){
				params.put("resultv2", this.resultv2);
			}
			if(StringUtil.isNotBlank(this.autoCom)){
				params.put("autoCom", this.autoCom);
			}
			if(StringUtil.isNotBlank(this.interCom)){
				params.put("interCom", this.interCom);
			}
			if(StringUtil.isNotBlank(this.departureCountry)){
				params.put("departureCountry", this.departureCountry);
			}
			if(StringUtil.isNotBlank(this.departureCom)){
				params.put("departureCom", this.departureCom);
			}
			if(StringUtil.isNotBlank(this.interCom)){
				params.put("destinationCountry", this.destinationCountry);
			}
			if(StringUtil.isNotBlank(this.interCom)){
				params.put("destinationCom", this.destinationCom);
			}
			jsonMap.put("parameters", params);
			jsonParam = JSONObject.toJSONString(jsonMap);			
		}
		return jsonParam;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
}
