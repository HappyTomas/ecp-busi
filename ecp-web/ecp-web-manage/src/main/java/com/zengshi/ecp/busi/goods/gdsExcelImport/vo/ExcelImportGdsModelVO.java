package com.zengshi.ecp.busi.goods.gdsExcelImport.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 导入数据中间记录model
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016年3月23日下午2:13:43  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
public class ExcelImportGdsModelVO implements Serializable{


	private static final long serialVersionUID = -6228721668301854310L;

	private String gdsName;
	
	private String catgCode;
	
	private String ShopId;
	
	private Long gdsType;
	
	private String gdsTitle;
	
	private Long price;
	
	private Long stockCount;
	
	private Map<String,String> propInfos = 	 new HashMap<String, String>();
	
	private String gdsDetailDesc;
	
	private String gdsPackDesc;
	//记录存储整个对象的单元区域
	private int firstRowIdx;
	
	private int lastRowIdx;

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

	public String getShopId() {
		return ShopId;
	}

	public void setShopId(String shopId) {
		ShopId = shopId;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Map<String, String> getPropInfos() {
		return propInfos;
	}

	public void setPropInfos(Map<String, String> propInfos) {
		this.propInfos = propInfos;
	}

	public Long getStockCount() {
		return stockCount;
	}

	public void setStockCount(Long stockCount) {
		this.stockCount = stockCount;
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


}
