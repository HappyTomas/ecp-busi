package com.zengshi.ecp.order.service.busi.interfaces.pay;

import java.util.List;

import com.zengshi.ecp.order.dao.model.PayQuartzInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoResponse;



/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayQuartzInfoSV {
    
    /**
     * 
     * addScoreInfo:添加积分任务. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payQuartzInfo 
     * @since JDK 1.6
     */
    public void addScoreInfo(RPayQuartzInfoRequest payQuartzInfo);
    
    /**
     * 
     * addCoupInfo:添加优惠券任务. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payQuartzInfo 
     * @since JDK 1.6
     */
    public void addCoupInfo(RPayQuartzInfoRequest payQuartzInfo);
    
    
    /**
     * 
     * updateDealFlag:修改任务状态从OldStatus变成newStatus. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payQuartzInfo 
     * @since JDK 1.6
     */
    public int updateDealFlag(RPayQuartzInfoRequest payQuartzInfo,String OldStatus,String newStatus);
    
    /**
     * 
     * deletePayQuartzInfo:删除完成的任务. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param id 
     * @since JDK 1.6
     */
    public void deletePayQuartzInfo(long id);
    
    /**
     * 
     * getScoreBeanByOrderId:根据订单编码，类型，状态获取定时任务实体. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payQuartzInfo
     * @return 
     * @since JDK 1.6
     */
    public List<PayQuartzInfo> getBeanByOrderIdTaskTypeDealFlag(RPayQuartzInfoRequest payQuartzInfo);
    
    /**
     * 
     * getBeanByOrderIdTaskTypeDealFlag:根据子订单编码，类型，状态获取定时任务实体. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payQuartzInfo
     * @return 
     * @since JDK 1.6
     */
    public PayQuartzInfo getBeanBySubOrderIdTaskTypeDealFlag(RPayQuartzInfoRequest payQuartzInfo);
    
    /**
     * 
     * addErrorTimes:错误次数加1. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param id
     * @return 
     * @since JDK 1.6
     */
    public void addErrorTimes(Long id);
    
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
    
}