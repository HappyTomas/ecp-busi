package com.zengshi.ecp.order.service.busi.interfaces.pay;

import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfReqLogDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付相关求情报文日志<br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayIntfReqLogSV {
    
    /**
     * 
     * addPayIntfReqLog:支付请求日志保存. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param info 
     * @since JDK 1.6
     */
    public void addPayIntfReqLog(PayIntfReqLogDTO info);
    
    /**
     * 
     * addPayZYDigitalLog:保存泽元数字教材授权日志. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param info 
     * @since JDK 1.6
     */
    public void addPayZYDigitalLog(PayIntfReqLogDTO info);
    
    /**
     * 
     * addPayZYExaminationLog:保存泽元考试网授权日志. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param info 
     * @since JDK 1.6
     */
    public void addPayZYExaminationLog(PayIntfReqLogDTO info);
}