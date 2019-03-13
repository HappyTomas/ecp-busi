package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupOrdCheckRespDTO extends BaseResponseDTO{
	
	private static final long serialVersionUID = 1L;
	
	//退货申请ID
	private Long applyId;
	/**
	 * 全部的coupId 包含平台级的和店铺级总和
	 * key:coupId (优惠券小类ID)
	 * value:skuID的集合  此List表示此优惠券能在哪些单品上使用
	 * PS:满减规则可能是多个单品单价总和计算的
	 * 如果是没有任何限制规则的抵用券则 List<CoupSkuRespDTO> coupSkuRespDTO不赋值
	 */
	private Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap;
	/**
	 * key: Long cartId; 
	 * Value: List<CoupCheckBeanRespDTO> 这个是记录店铺能使用的优惠券信息
	 */
	private Map<Long,List<CoupCheckBeanRespDTO>> coupOrdSkuMap;
	
	//可使用的优惠券信息(平台)
	private List<CoupCheckBeanRespDTO> coupPlatfBean;
	

	public Map<Long, CoupCheckInfoRespDTO> getCoupIdskuIdMap() {
		return coupIdskuIdMap;
	}

	public void setCoupIdskuIdMap(Map<Long, CoupCheckInfoRespDTO> coupIdskuIdMap) {
		this.coupIdskuIdMap = coupIdskuIdMap;
	}

	public Map<Long, List<CoupCheckBeanRespDTO>> getCoupOrdSkuMap() {
		return coupOrdSkuMap;
	}

	public void setCoupOrdSkuMap(Map<Long, List<CoupCheckBeanRespDTO>> coupOrdSkuMap) {
		this.coupOrdSkuMap = coupOrdSkuMap;
	}

	public List<CoupCheckBeanRespDTO> getCoupPlatfBean() {
		return coupPlatfBean;
	}

	public void setCoupPlatfBean(List<CoupCheckBeanRespDTO> coupPlatfBean) {
		this.coupPlatfBean = coupPlatfBean;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	@Override
	public String toString() {
		return "CoupOrdCheckRespDTO [coupIdskuIdMap=" + coupIdskuIdMap
				+ ", coupOrdSkuMap=" + coupOrdSkuMap + ", coupPlatfBean="
				+ coupPlatfBean + "]";
	}
	
	
}

