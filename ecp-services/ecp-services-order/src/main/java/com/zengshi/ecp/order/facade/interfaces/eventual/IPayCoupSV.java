package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.distribute.tx.common.TransactionListener;

public interface IPayCoupSV extends TransactionListener {
	/**
     * 
     * dealCoup:处理优惠券，不使用一致性事务的时候可以使用这个方法. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param paySuccInfo 
     * @since JDK 1.6
     */
	public void dealCoup(PaySuccInfo paySuccInfo);

}

