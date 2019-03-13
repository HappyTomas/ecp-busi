package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.distribute.tx.common.TransactionListener;

public interface IOrdDeliverExpressSV extends TransactionListener{

	/**
	 * 处理订单发货时快递100物流订阅
	 * @author l2iu
	 * @param rConfirmDeliveRequest
	 * @since JDK 1.6 
	 */
    public void dealMethod(RConfirmDeliveRequest rConfirmDeliveRequest);
}
