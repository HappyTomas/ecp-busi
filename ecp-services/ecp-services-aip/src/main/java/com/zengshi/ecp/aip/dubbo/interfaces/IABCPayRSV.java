package com.zengshi.ecp.aip.dubbo.interfaces;

import com.zengshi.ecp.aip.dubbo.dto.ABCPayQueryOrderRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayQueryOrderResponse;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayRefundRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayRefundResponse;
import com.zengshi.ecp.aip.dubbo.dto.ABCPaySettleRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPaySettleResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IABCPayRSV {
    
    /**
     * 
     * querySettle:查询农行对账信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @return 
     * @since JDK 1.6
     */
    public ABCPaySettleResponse querySettle(ABCPaySettleRequest request)
            throws BusinessException;
    
    /**
     * 
     * refund:退款. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq 
     * @since JDK 1.6
     */
    public ABCPayRefundResponse refund(ABCPayRefundRequest request)
            throws BusinessException;
    
    /**
     * 
     * QueryOrder:交易查询. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq 
     * @since JDK 1.6
     */
    public ABCPayQueryOrderResponse queryOrder(ABCPayQueryOrderRequest request)
            throws BusinessException;
}

