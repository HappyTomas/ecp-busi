package com.zengshi.ecp.aip.third.dubbo.dto.req;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class ProductThirdReqDTO extends BaseShopAuthReqDTO{

	private static final long serialVersionUID = 1L;
	
	//分类代码
	private String outCatgCode;
		
	//品牌id
	private String brandId;
	
	//产品规则xml
	private String productXml;
		
	//产品编码
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductXml() {
		return productXml;
	}

	public void setProductXml(String productXml) {
		this.productXml = productXml;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getOutCatgCode() {
		return outCatgCode;
	}

	public void setOutCatgCode(String outCatgCode) {
		this.outCatgCode = outCatgCode;
	}
 
}

