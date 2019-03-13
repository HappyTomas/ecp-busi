package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RBackApplyOrdReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrderBackRSV {

	public ROrdReturnRefundResp calCulateBackInfo(RBackApplyOrdReq resp)throws BusinessException;
	
	public ROrdReturnRefundResp calCulateBackInfo (ROrderBackReq req)throws BusinessException;
	
	public boolean modifyBackMoney(ROrdReturnRefundReq req)throws BusinessException;
}
