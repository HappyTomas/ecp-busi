package com.zengshi.ecp.order.service.busi.interfaces;


import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdx;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderIdx;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月11日上午11:27:44  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdMainShopIdxSV extends IGeneralSQLSV {
    
    /** 
     * saveOrdMainShopIdx:(这里用一句话描述这个方法的作用). <br/> 
     * @author cbl 
     * @param ordMainShopIdx 
     * @since JDK 1.6 
     */ 
    public void saveOrdMainShopIdx(OrdMainShopIdx ordMainShopIdx);

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
     * queryOrderByShopIdPage:待发货，已发货订单查询. <br/> 
     * @author cbl 
     * @param rDelyOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<SOrderIdx> queryOrderByShopIdPage(RQueryOrderRequest rQueryOrderRequest);
    
    /** 
     * updateOrderTwoStatusByOrderId:根据订单号更新订单二级信息. <br/> 
     * @author cbl 
     * @param ordMain 
     * @since JDK 1.6 
     */ 
    public void updateOrderTwoStatusByOrderId(OrdMain ordMain);

    /**
     * queryOrderCount:统计订单各种状态数量. <br/>
     * @author cbl
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public Long queryOrderCountByShop(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
}
