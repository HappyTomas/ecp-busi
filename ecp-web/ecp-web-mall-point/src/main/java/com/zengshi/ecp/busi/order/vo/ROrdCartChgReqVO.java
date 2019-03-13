/**
 * ROrdCartChgReqVO.java	  V1.0   2015-10-21 下午4:16:53
 *
 * Copyright 2015 AsiaInfo Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;

/**
 * 功能描述：促销相关VO
 *
 * @author  曾海沥(zenghl)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ROrdCartChgReqVO extends EcpBasePageReqVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String source;
	
	private String countryCode;
	
	private String provinceCode;
	
	private String cityCode;
	
	private ROrdCartItemRequest ordCartItem;
	
    private ROrdCartChangeRequest ordCartChg;

	public ROrdCartItemRequest getOrdCartItem() {
		return ordCartItem;
	}

	public void setOrdCartItem(ROrdCartItemRequest ordCartItem) {
		this.ordCartItem = ordCartItem;
	}

	public ROrdCartChangeRequest getOrdCartChg() {
		return ordCartChg;
	}

	public void setOrdCartChg(ROrdCartChangeRequest ordCartChg) {
		this.ordCartChg = ordCartChg;
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

}
