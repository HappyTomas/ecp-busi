package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdSubStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdSubStaffIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

import java.util.List;

public interface IOrdSubStaffIdxSV extends IGeneralSQLSV{
    /** 
     * saveOrdSubStaffIdx:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei
     * @param OrdSubStaffIdx 
     * @since JDK 1.6 
     */ 
    public void saveOrdSubStaffIdx(OrdSubStaffIdx ordSubStaffIdx);
    
    /** 
     * queryEvaluationWait:查询待评价子订单. <br/> 
     * @author cbl 
     * @param rOrdEvaluationRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrdEvaluationResponse> querySubForEvaluationWait(ROrdEvaluationRequest rOrdEvaluationRequest);
    
    
    /** 
     * updateEvalFlag:更新已评价标识. <br/> 
     * @author cbl 
     * @param rOrdEvaluatedRequest 
     * @since JDK 1.6 
     */ 
    public void updateEvalFlag(ROrdEvaluatedRequest rOrdEvaluatedRequest);
    
    
    /** 
     * updateOrderStatus:更新订单状态. <br/> 
     * @author sky 
     * @param sOrderStatusInfo 
     * @since JDK 1.6 
     */ 
    public void updateOrderStatus(SBaseAndStatusInfo sOrderStatusInfo);
    
    
    /** 
     * queryOrderSubByStaffId:根据买家id查询子订单号. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<SOrderSubIdx> queryOrderSubByStaffId(RQueryOrderRequest rQueryOrderRequest);

    /**
     * queryOrdSubStaffIdx:根据staffid查询表记录. <br/>
     * @author cbl
     * @param rOrdSubStaffIdxReq
     * @return
     * @since JDK 1.6
     */
    public List<ROrdSubStaffIdxResp> queryOrdSubStaffIdx(ROrdSubStaffIdxReq rOrdSubStaffIdxReq);
}

