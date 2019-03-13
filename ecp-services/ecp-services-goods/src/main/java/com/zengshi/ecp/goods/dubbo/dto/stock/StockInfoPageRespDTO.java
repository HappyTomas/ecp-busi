package com.zengshi.ecp.goods.dubbo.dto.stock;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class StockInfoPageRespDTO extends BaseResponseDTO {

	private Long id;

	private Long repCode;

	private Long catgCode;

	private Long typeId;
	//商品类型名称
	private String typeName;

	private Long gdsId;

	private String gdsName;

	private Long skuId;

	private Long shopId;

	private Long companyId;

	private Long realCount;

	private Long preOccupyCount;

	private Long availCount;

	private Long sendCount;

	private Long warningCount;

	private String repType;
	// 单品价格
	private Long defPrice;
	// 单品属性串
	private String propStr;
	// 单品状态
	private String skuStatus;
	// 产品编号.
	private String productNo;
	// 工厂库存
	private Long facStock;

	public String getPropStr() {
		return propStr;
	}

	public void setPropStr(String propStr) {
		this.propStr = propStr;
	}

	private static final long serialVersionUID = 1L;

	public Long getDefPrice() {
		return defPrice;
	}

	public void setDefPrice(Long defPrice) {
		this.defPrice = defPrice;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRepCode() {
		return repCode;
	}

	public void setRepCode(Long repCode) {
		this.repCode = repCode;
	}

	public Long getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(Long catgCode) {
		this.catgCode = catgCode;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getRealCount() {
		return realCount;
	}

	public void setRealCount(Long realCount) {
		this.realCount = realCount;
	}

	public Long getPreOccupyCount() {
		return preOccupyCount;
	}

	public void setPreOccupyCount(Long preOccupyCount) {
		this.preOccupyCount = preOccupyCount;
	}

	public Long getAvailCount() {
		return availCount;
	}

	public void setAvailCount(Long availCount) {
		this.availCount = availCount;
	}

	public Long getSendCount() {
		return sendCount;
	}

	public void setSendCount(Long sendCount) {
		this.sendCount = sendCount;
	}

	public Long getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(Long warningCount) {
		this.warningCount = warningCount;
	}

	public String getRepType() {
		return repType;
	}

	public void setRepType(String repType) {
		this.repType = repType;
	}

	public String getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(String skuStatus) {
		this.skuStatus = skuStatus;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Long getFacStock() {
        return facStock;
    }

    public void setFacStock(Long facStock) {
        this.facStock = facStock;
    }

	
}
