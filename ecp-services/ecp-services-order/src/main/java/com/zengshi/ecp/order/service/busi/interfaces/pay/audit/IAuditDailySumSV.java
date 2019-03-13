package com.zengshi.ecp.order.service.busi.interfaces.pay.audit;

import com.zengshi.ecp.order.dao.model.AuditDailySum;

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
public interface IAuditDailySumSV {
    /**
     * saveAuditDailySum:保存对账文件. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param log 
     * @since JDK 1.6
     */
    public void saveAuditDailySum(AuditDailySum auditDailySum);
    
    /**
     * 
     * updateAuditDailySum:更新. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param auditDailySum 
     * @since JDK 1.6
     */
    public void updateAuditDailySum(AuditDailySum auditDailySum);
    
    /**
     * 
     * getAuditDailySum:根据主键查询对账总表. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param auditDailySum 
     * @since JDK 1.6
     */
    public AuditDailySum getAuditDailySum(AuditDailySum auditDailySum);
}