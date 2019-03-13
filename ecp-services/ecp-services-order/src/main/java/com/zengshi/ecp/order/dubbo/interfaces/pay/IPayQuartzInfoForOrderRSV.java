package com.zengshi.ecp.order.dubbo.interfaces.pay;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoResponse;

public interface IPayQuartzInfoForOrderRSV {
    
    /**
     * 
     * queryNotDealPayScoreOrder:获取未处理积分的订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param rPayQuartzInfoRequest
     * @return 
     * @since JDK 1.6
     */
    public List<RPayQuartzInfoResponse> queryNotDealScoreOrder(RPayQuartzInfoRequest rPayQuartzInfoRequest);
    
    /**
     * 
     * queryNotDealPayScoreOrder:获取未处理优惠券的订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param rPayQuartzInfoRequest
     * @return 
     * @since JDK 1.6
     */
    public List<RPayQuartzInfoResponse> queryNotDealCoupOrder(RPayQuartzInfoRequest rPayQuartzInfoRequest);
    
   
    /**
     * 
     * queryNotDealPayScoreOrder:处理积分订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param rPayQuartzInfoRequest
     * @return 
     * @since JDK 1.6
     */
    public void dealScoreOrder(RPayQuartzInfoRequest rPayQuartzInfoRequest);
    
    /**
     * 
     * queryNotDealPayScoreOrder:处理优惠券订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param rPayQuartzInfoRequest
     * @return 
     * @since JDK 1.6
     */
    public void dealCoupOrder(RPayQuartzInfoRequest rPayQuartzInfoRequest);
    
}

