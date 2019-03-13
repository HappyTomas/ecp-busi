package com.zengshi.ecp.order.service.busi.interfaces.pay.audit;

import com.zengshi.ecp.order.dao.model.AuditTradeCheck;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckTotalResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

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
public interface IAuditTradeCheckSV {
    
    /**
     * 
     * saveAuditTradeCheck:保存对账订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param auditTradeCheck 
     * @since JDK 1.6
     */
    public void saveAuditTradeCheck(AuditTradeCheck auditTradeCheck);
    
    /**
     * 
     * getTAuditTradeChecks:分页获取交易清算信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param auditTradeCheckRequest
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<RAuditTradeCheckResponse> getTAuditTradeChecks(RAuditTradeCheckRequest auditTradeCheckRequest);
    
    /**
     * 
     * getTAuditTradeChecksTotal:获取汇总信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param auditTradeCheckRequest
     * @return 
     * @since JDK 1.6
     */
    public RAuditTradeCheckTotalResponse getTAuditTradeChecksTotal(RAuditTradeCheckRequest auditTradeCheckRequest);
    
    
}