package com.zengshi.ecp.general.order.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupCheckInfoRespDTO extends BaseResponseDTO{
	
	//优惠券信息
	private CoupCheckBeanRespDTO coupCheckBeanRespDTO;
	//单品集合
	//如果是没有任何限制规则的抵用券则 List<CoupSkuRespDTO> coupSkuRespDTO不赋值
	private List<CoupSkuRespDTO> coupSkuRespDTO;
	
	public CoupCheckBeanRespDTO getCoupCheckBeanRespDTO() {
		return coupCheckBeanRespDTO;
	}
	public void setCoupCheckBeanRespDTO(CoupCheckBeanRespDTO coupCheckBeanRespDTO) {
		this.coupCheckBeanRespDTO = coupCheckBeanRespDTO;
	}
	public List<CoupSkuRespDTO> getCoupSkuRespDTO() {
		return coupSkuRespDTO;
	}
	public void setCoupSkuRespDTO(List<CoupSkuRespDTO> coupSkuRespDTO) {
		this.coupSkuRespDTO = coupSkuRespDTO;
	}
	
	
}

