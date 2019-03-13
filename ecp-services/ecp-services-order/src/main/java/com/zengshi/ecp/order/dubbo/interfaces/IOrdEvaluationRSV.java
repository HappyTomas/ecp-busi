package com.zengshi.ecp.order.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluatedRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

public interface IOrdEvaluationRSV {

    
    /** 
     * queryEvaluationWait:待评价子订单查询. <br/> 
     * @author cbl 
     * @param rOrdEvaluationRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrdEvaluationResponse> queryEvaluationWait(ROrdEvaluationRequest rOrdEvaluationRequest);
    
    /** 
     * updateEvaluated:已评价子订单回调. <br/> 
     * @author cbl 
     * @param rOrdEvaluatedRequests 
     * @since JDK 1.6 
     */ 
    public void updateEvaluated(List<ROrdEvaluatedRequest> rOrdEvaluatedRequests);
    
    /** 
     * queryEvaluationWaitCount:待评价子订单的数量. <br/> 
     * @author cbl 
     * @param rOrdEvaluationRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryEvaluationWaitCount(ROrdEvaluationRequest rOrdEvaluationRequest);
}

