package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdMainStaffIdx;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderIdx;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IOrdMainStaffIdxSV extends IGeneralSQLSV{
    /** 
     * saveOrdDeliveryDetails:保存数据. <br/> 
     * @author cbl 
     * @param ordMainStaffIdx 
     * @since JDK 1.6 
     */ 
    public void saveOrdMainStaffIdx(OrdMainStaffIdx ordMainStaffIdx);

    
    /** 
     * updateOrderStatus:根据订单号更新订单状态. <br/> 
     * @author cbl 
     * @param sOrderStatusInfo 
     * @since JDK 1.6 
     */ 
    public void updateOrderStatus(SBaseAndStatusInfo sOrderStatusInfo);
    
    /** 
     * updateOrderStatus:根据主订单号更新主订单商户订单号，同时更新索引表商户订单号. <br/> 
     * @author panjs 
     * @param status 
     * @since JDK 1.6 
     */ 
    public void updateOrderPayTranNo(ROrdPayRelReq rOrdPayRelReq);
    
    /** 
     * queryOrderByStaffIdPage:买家订单查询. <br/> 
     * @author cbl 
     * @param rCustomerOrdRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<SOrderIdx> queryOrderByStaffIdPage(RQueryOrderRequest rQueryOrderRequest);
    
    
    /** 
     * queryOrderCount:统计订单各种状态数量. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCountResponse queryOrderCountByStaff(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    
    /** 
     * updateOrderTwoStatusByOrderId:根据订单号更新订单二级信息. <br/> 
     * @author cbl 
     * @param ordMain 
     * @since JDK 1.6 
     */ 
    public void updateOrderTwoStatusByOrderId(OrdMain ordMain);

    /**
     * 条件可以包含订单状态列表查询
     * @param rQueryOrderRequest
     * @return
     */
    public PageResponseDTO<SOrderIdx> queryOrderSelectiveStatus(RQueryOrderRequest rQueryOrderRequest);
}
