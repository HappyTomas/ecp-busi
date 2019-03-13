package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupCheckRespDTO extends BaseResponseDTO{
	

	private static final long serialVersionUID = 1L;
	
	//可使用的优惠券信息(店铺)
	private List<CoupCheckBeanRespDTO> coupCheckBean;

	/**
	 * key:CoupCheckBeanRespDTO value:skuID的集合  此List表示此优惠券能在哪些单品上使用
	 * PS:满减规则可能是多个单品单价总和计算的
	 * 如果是没有任何限制规则的抵用券则 List<CoupSkuRespDTO> coupSkuRespDTO不赋值
	 */
	private Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap;
	
	
	public List<CoupCheckBeanRespDTO> getCoupCheckBean() {
		return coupCheckBean;
	}
	public void setCoupCheckBean(List<CoupCheckBeanRespDTO> coupCheckBean) {
		this.coupCheckBean = coupCheckBean;
	}
	public Map<Long, CoupCheckInfoRespDTO> getCoupIdskuIdMap() {
		return coupIdskuIdMap;
	}
	public void setCoupIdskuIdMap(Map<Long, CoupCheckInfoRespDTO> coupIdskuIdMap) {
		this.coupIdskuIdMap = coupIdskuIdMap;
	}
	
}

