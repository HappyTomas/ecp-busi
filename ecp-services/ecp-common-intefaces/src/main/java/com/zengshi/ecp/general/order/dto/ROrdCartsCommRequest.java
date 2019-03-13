package com.zengshi.ecp.general.order.dto;


import java.util.List;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdCartsCommRequest extends BaseInfo {
    //买家id
    private Long staffId;
      
    private String source;
    
    private String countryCode;
       
    private String provinceCode;
        
    private String cityCode;

    private String promSubmitType;

    //购物车实例列
    private List<ROrdCartCommRequest> ordCartsCommList;
    
    /**
	 * 全部的coupId 包含平台级的和店铺级总和
	 * key:coupId (优惠券小类ID)
	 * value:skuID的集合  此List表示此优惠券能在哪些单品上使用
	 * PS:满减规则可能是多个单品单价总和计算的
	 * 如果是没有任何限制规则的抵用券则 List<CoupSkuRespDTO> coupSkuRespDTO不赋值
	 */
	private Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap;
	
	//可使用的优惠券信息(平台)
	private List<CoupCheckBeanRespDTO> coupPlatfBean;

    private static final long serialVersionUID = 1L;


    
    public Map<Long, CoupCheckInfoRespDTO> getCoupIdskuIdMap() {
		return coupIdskuIdMap;
	}


	public void setCoupIdskuIdMap(Map<Long, CoupCheckInfoRespDTO> coupIdskuIdMap) {
		this.coupIdskuIdMap = coupIdskuIdMap;
	}


	public List<CoupCheckBeanRespDTO> getCoupPlatfBean() {
		return coupPlatfBean;
	}


	public void setCoupPlatfBean(List<CoupCheckBeanRespDTO> coupPlatfBean) {
		this.coupPlatfBean = coupPlatfBean;
	}


	public Long getStaffId() {
        return staffId;
    }


    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }



    public List<ROrdCartCommRequest> getOrdCartsCommList() {
        return ordCartsCommList;
    }


    public void setOrdCartsCommList(List<ROrdCartCommRequest> ordCartsCommList) {
        this.ordCartsCommList = ordCartsCommList;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public String getCountryCode() {
        return countryCode;
    }


    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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


    public String getPromSubmitType() {
        return promSubmitType;
    }

    public void setPromSubmitType(String promSubmitType) {
        this.promSubmitType = promSubmitType;
    }
}

