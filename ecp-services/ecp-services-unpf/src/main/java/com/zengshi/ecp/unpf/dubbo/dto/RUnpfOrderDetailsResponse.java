package com.zengshi.ecp.unpf.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdDeliveryBatchResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;

public class RUnpfOrderDetailsResponse {
	
	private RUnpfOrdMainResp unpfOrdMainResp;
	
	private List<RUnpfOrdDeliveryBatchResp> unpfOrdDeliveryBatchResp;

	public RUnpfOrdMainResp getUnpfOrdMainResp() {
		return unpfOrdMainResp;
	}

	public void setUnpfOrdMainResp(RUnpfOrdMainResp unpfOrdMainResp) {
		this.unpfOrdMainResp = unpfOrdMainResp;
	}

	public List<RUnpfOrdDeliveryBatchResp> getUnpfOrdDeliveryBatchResp() {
		return unpfOrdDeliveryBatchResp;
	}

	public void setUnpfOrdDeliveryBatchResp(List<RUnpfOrdDeliveryBatchResp> unpfOrdDeliveryBatchResp) {
		this.unpfOrdDeliveryBatchResp = unpfOrdDeliveryBatchResp;
	}


   
	
	
    
}
