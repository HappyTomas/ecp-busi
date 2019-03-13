package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctMainResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctSubResDTO;

public class RBackApplyAccResp extends BaseResponseDTO {

    /**OrderAcctMainResDTO
	 * 
	 */
	private static final long serialVersionUID = 4928788957150851140L;
		
	//下单返回的资金账户金额
	private List<OrderAcctSubResDTO> sumUsedCulateAccList;

    public List<OrderAcctSubResDTO> getSumUsedCulateAccList() {
        return sumUsedCulateAccList;
    }

    public void setSumUsedCulateAccList(List<OrderAcctSubResDTO> sumUsedCulateAccList) {
        this.sumUsedCulateAccList = sumUsedCulateAccList;
    }




	

	
	
    




	

    
}

