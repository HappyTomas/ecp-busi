package com.zengshi.ecp.goods.dubbo.dto.excelImp;

import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ExcelImportGdsModelDTO extends BaseInfo{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6228721668301854310L;

	private String gdsName;
	
	private String catgCode;
	
	private Long shopId;
	
	private Long gdsId;
	
	private Long gdsType;
	
	private String gdsTitle;
	
	private Long gdsPrice;
	
	private Long gdsStock;
	
	private Map<String,String> propInfos = 	 new HashMap<String, String>();
	
	private String gdsDetailDesc;
	
	private String gdsPackDesc;
	
	
	//记录存储整个对象的单元区域
	private int firstRowIdx;
	
	private int lastRowIdx;
	//导入批次号
	private Long importId;

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
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



	public Map<String, String> getPropInfos() {
		return propInfos;
	}

	public void setPropInfos(Map<String, String> propInfos) {
		this.propInfos = propInfos;
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

	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public int getFirstRowIdx() {
		return firstRowIdx;
	}

	public void setFirstRowIdx(int firstRowIdx) {
		this.firstRowIdx = firstRowIdx;
	}

	public int getLastRowIdx() {
		return lastRowIdx;
	}

	public void setLastRowIdx(int lastRowIdx) {
		this.lastRowIdx = lastRowIdx;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getGdsPrice() {
		return gdsPrice;
	}

	public void setGdsPrice(Long gdsPrice) {
		this.gdsPrice = gdsPrice;
	}

	public Long getGdsStock() {
		return gdsStock;
	}

	public void setGdsStock(Long gdsStock) {
		this.gdsStock = gdsStock;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}


}
