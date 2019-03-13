package com.zengshi.ecp.order.service.busi.interfaces.pay;

import com.zengshi.ecp.order.dao.model.PayRepeat;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRepeatDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 重复支付<br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayRepeatSV {
    /**
     * 
     * addPayRepeat:添加重复支付记录. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payRepeat 
     * @since JDK 1.6
     */
    public void addPayRepeat(PayRepeatDTO payRepeat);
    
    /**
     * 
     * getPayRepeatByOrderIdPayWayTransNo:根据支付通道编码，支付流水查询重复支付信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param OrderId
     * @param payWay
     * @param transNo
     * @return 
     * @since JDK 1.6
     */
    public PayRepeat getPayRepeatByPayWayTransNo(String payWay,String transNo);
}