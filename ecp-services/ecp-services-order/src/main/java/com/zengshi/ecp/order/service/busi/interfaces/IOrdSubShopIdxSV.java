package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdSubShopIdx;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

public interface IOrdSubShopIdxSV {
    /** 
     * saveOrdSubShopIdx:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei
     * @param OrdSubShopIdx 
     * @since JDK 1.6 
     */ 
    public void saveOrdSubShopIdx(OrdSubShopIdx ordSubShopIdx);
    
    
    /** 
     * updateOrderStatus:更新订单状态. <br/> 
     * @author sky 
     * @param sOrderStatusInfo 
     * @since JDK 1.6 
     */ 
    public void updateOrderStatus(SBaseAndStatusInfo sOrderStatusInfo);
    

    /**
     * 分页查下全部索引值，只返回orderId和索引id值
     * @param rGoodSaleRequest
     * @return
     */
    public PageResponseDTO<RGoodSaleResponse> queryOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest);
    
    /**
     * 分页查下全部索引值，只返回orderId和索引id值
     * @param rGoodSaleRequest
     * @return
     */
    public PageResponseDTO<RGoodSaleResponse> queryOrdSubShopIdx1(RGoodSaleRequest rGoodSaleRequest);
    

    /** 
     * updateOrderStatus:更新退款金额. <br/> 
     * @author sky 
     * @param sOrderStatusInfo 
     * @since JDK 1.6 
     */ 
    public void updateSubOrderBackInfo(String subOrderId,Long backMoney,Long backNum);
}

