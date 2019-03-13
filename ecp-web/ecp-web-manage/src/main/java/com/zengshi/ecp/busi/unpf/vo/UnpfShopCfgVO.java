package com.zengshi.ecp.busi.unpf.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class UnpfShopCfgVO extends EcpBasePageReqVO{
	private static final long serialVersionUID = -1L;
	
	private Long id;
	
	private Long shopAuthId;
	
	private Long shopId;
	
	private String platType;
	
	private String cityCode;
	
	private String provinceCode;
	
	private String inputValue;
	
	private UnpfShopCfgInfoVO unpfShopCfgInfoVO;
	
	
	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UnpfShopCfgInfoVO getUnpfShopCfgInfoVO() {
		return unpfShopCfgInfoVO;
	}

	public void setUnpfShopCfgInfoVO(UnpfShopCfgInfoVO unpfShopCfgInfoVO) {
		this.unpfShopCfgInfoVO = unpfShopCfgInfoVO;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}


}
