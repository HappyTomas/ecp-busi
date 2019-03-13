package com.zengshi.ecp.aip.third.dubbo.dto.req;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class GdsThirdReqDTO extends ProductThirdReqDTO{

	private static final long serialVersionUID = 1L;
	
	//商品 xml
	private String gdsXml;

	public String getGdsXml() {
		return gdsXml;
	}

	public void setGdsXml(String gdsXml) {
		this.gdsXml = gdsXml;
	}
		 
}

