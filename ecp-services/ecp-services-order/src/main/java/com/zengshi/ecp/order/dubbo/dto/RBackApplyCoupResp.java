package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdBackNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdNumRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackApplyCoupResp extends BaseResponseDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4928788957150851140L;
	
	private List<OrdNumRespDTO> backApllyCoupList;

    public List<OrdNumRespDTO> getBackApllyCoupList() {
        return backApllyCoupList;
    }

    public void setBackApllyCoupList(List<OrdNumRespDTO> backApllyCoupList) {
        this.backApllyCoupList = backApllyCoupList;
    }    
}

