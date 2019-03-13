package com.zengshi.ecp.busi.unpf.vo;

import java.io.Serializable;

public class UnpfShopCfgInfoVO implements Serializable{
	
	   private static final long serialVersionUID = -1L;
	   
	   private String provinceCode;//省份
	   
	   private String cityCode;//城市
	   
	   private String templateId;//模板Id
	   
	   private String shipType="1";//1买家 2卖家
	   
	   private String freight_by_buyer="freight_details";//freight_details 运费  postage 运费模板
	   
	   private String post;//平邮
	   
	   private String express;//快递
	   
	   private String ems;//ems
	   
	   private String refund;//退款比例

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getFreight_by_buyer() {
		return freight_by_buyer;
	}

	public void setFreight_by_buyer(String freight_by_buyer) {
		this.freight_by_buyer = freight_by_buyer;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getEms() {
		return ems;
	}

	public void setEms(String ems) {
		this.ems = ems;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}
	   
}
