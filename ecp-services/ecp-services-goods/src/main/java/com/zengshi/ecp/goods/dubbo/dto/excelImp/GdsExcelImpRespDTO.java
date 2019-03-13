package com.zengshi.ecp.goods.dubbo.dto.excelImp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsExcelImpRespDTO extends BaseResponseDTO  {

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 2531847719863230389L;

    private Long importId;

    private String busiSrc;

    private Long gdsId;

    private String catgCode;

    private String gdsName;

    private Long shopId;

    private Long gdsType;

    private String gdsTitle;

    private String gdsPrice;

    private String gdsPropStr;

    private Long gdsStock;

    private String gdsDetailDesc;

    private String gdsPackDesc;

    private String failReason;

	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public String getBusiSrc() {
		return busiSrc;
	}

	public void setBusiSrc(String busiSrc) {
		this.busiSrc = busiSrc;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getGdsType() {
		return gdsType;
	}

	public void setGdsType(Long gdsType) {
		this.gdsType = gdsType;
	}

	public String getGdsTitle() {
		return gdsTitle;
	}

	public void setGdsTitle(String gdsTitle) {
		this.gdsTitle = gdsTitle;
	}

	public String getGdsPrice() {
		return gdsPrice;
	}

	public void setGdsPrice(String gdsPrice) {
		this.gdsPrice = gdsPrice;
	}

	public String getGdsPropStr() {
		return gdsPropStr;
	}

	public void setGdsPropStr(String gdsPropStr) {
		this.gdsPropStr = gdsPropStr;
	}

	public Long getGdsStock() {
		return gdsStock;
	}

	public void setGdsStock(Long gdsStock) {
		this.gdsStock = gdsStock;
	}

	public String getGdsDetailDesc() {
		return gdsDetailDesc;
	}

	public void setGdsDetailDesc(String gdsDetailDesc) {
		this.gdsDetailDesc = gdsDetailDesc;
	}

	public String getGdsPackDesc() {
		return gdsPackDesc;
	}

	public void setGdsPackDesc(String gdsPackDesc) {
		this.gdsPackDesc = gdsPackDesc;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	
}

