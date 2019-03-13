package com.zengshi.ecp.general.order.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.PayQuartzInfoResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IPayQuartzInfoRSV {
    
    /**
     * 
     * updateScoreDealFlagToDoing:更新积分定时任务的处理状态到处理中. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PayQuartzInfoResponse updateScoreDealFlagToDoing(PayQuartzInfoRequest request) throws BusinessException;
    
    /**
     * 
     * updateScoreDealFlagToDone:更新积分定时任务的处理状态到已经完成. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PayQuartzInfoResponse updateScoreDealFlagToDone(PayQuartzInfoRequest request) throws BusinessException;
    
    /**
     * 
     * updateCoupDealFlagToDoing:更新优惠券定时任务的处理状态到处理中. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PayQuartzInfoResponse updateCoupDealFlagToDoing(PayQuartzInfoRequest request) throws BusinessException;
    
    /**
     * 
     * updateCoupDealFlagToDone:更新优惠券定时任务的处理状态到已经完成. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PayQuartzInfoResponse updateCoupDealFlagToDone(PayQuartzInfoRequest request) throws BusinessException;
    
    /**
     * 
     * updateDealFlagToDoing:更新定时任务的处理状态到处理中. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PayQuartzInfoResponse updateDealFlagToDoing(PayQuartzInfoRequest request) throws BusinessException;
    
    /**
     * 
     * updateDealFlagToDone:更新定时任务的处理状态到已经完成. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PayQuartzInfoResponse updateDealFlagToDone(PayQuartzInfoRequest request) throws BusinessException;
}

