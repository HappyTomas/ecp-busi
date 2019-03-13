package com.zengshi.ecp.coupon.dubbo.dto.resp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

public class CoupMeCountRespDTO extends BaseResponseDTO {

	// 我的优惠券 分页参数
	PageResponseDTO<CoupMeRespDTO> beanPage;
	// 我的优惠券总数量
	Long coupCount;
	//1:可使用 2:已使用 0:已过期
	Long coupCount_0;
	Long coupCount_1;
	Long coupCount_2;
	
	
	
	public Long getCoupCount_0() {
		return coupCount_0;
	}

	public void setCoupCount_0(Long coupCount_0) {
		this.coupCount_0 = coupCount_0;
	}

	public Long getCoupCount_1() {
		return coupCount_1;
	}

	public void setCoupCount_1(Long coupCount_1) {
		this.coupCount_1 = coupCount_1;
	}

	public Long getCoupCount_2() {
		return coupCount_2;
	}

	public void setCoupCount_2(Long coupCount_2) {
		this.coupCount_2 = coupCount_2;
	}

	public PageResponseDTO<CoupMeRespDTO> getBeanPage() {
		return beanPage;
	}

	public void setBeanPage(PageResponseDTO<CoupMeRespDTO> beanPage) {
		this.beanPage = beanPage;
	}

	public Long getCoupCount() {
		return coupCount;
	}

	public void setCoupCount(Long coupCount) {
		this.coupCount = coupCount;
	}

}
