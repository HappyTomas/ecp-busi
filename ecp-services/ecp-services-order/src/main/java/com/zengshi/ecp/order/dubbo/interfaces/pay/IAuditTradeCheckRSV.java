package com.zengshi.ecp.order.dubbo.interfaces.pay;

import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckTotalResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 交易清算报表服务<br>
 * Date:2015年12月31日上午11:13:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IAuditTradeCheckRSV {
    /**
     * 
     * getTAuditTradeQsVOs:易清算报表分页查询. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param tAuditTradeQsVO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<RAuditTradeCheckResponse> getTAuditTradeChecks(RAuditTradeCheckRequest auditTradeCheckRequest)
    		throws BusinessException;
    
    /**
     * 
     * getTAuditTradeChecksTotal:获取对账汇总信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param auditTradeCheckRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public RAuditTradeCheckTotalResponse getTAuditTradeChecksTotal(
            RAuditTradeCheckRequest auditTradeCheckRequest)throws BusinessException;
   
}
