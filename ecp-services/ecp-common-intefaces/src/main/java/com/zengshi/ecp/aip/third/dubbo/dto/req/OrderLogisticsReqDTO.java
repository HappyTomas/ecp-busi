package com.zengshi.ecp.aip.third.dubbo.dto.req;

public class OrderLogisticsReqDTO extends BaseShopAuthReqDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	private String fields;//需返回的字段列表。可选值:LogisticCompany 结构中的所有字段;多个字段间用","逗号隔开. 如:id,code,name,reg_mail_no说明：id：物流公司IDcode：物流公司codename：物流公司名称reg_mail_no：物流公司对应的运单规则 
	private Boolean isRecommended;//是否查询推荐物流公司.可选值:true,false.如果不提供此参数,将会返回所有支持电话联系的物流公司
	private String orderMode = "all"; //推荐物流公司的下单方式.可选值:offline(电话联系/自己联系),online(在线下单),all(即电话联系又在线下单). 此参数仅仅用于is_recommended 为ture时。就是说对于推荐物流公司才可用.如果不选择此参数将会返回推荐物流中支持电话联系的物流公司.
	
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public Boolean getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
	public String getOrderMode() {
		return orderMode;
	}
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}

}
