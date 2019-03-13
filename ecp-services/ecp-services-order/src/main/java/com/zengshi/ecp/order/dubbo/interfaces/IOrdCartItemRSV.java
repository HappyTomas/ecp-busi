package com.zengshi.ecp.order.dubbo.interfaces;




import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.order.dubbo.dto.RGroupChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdCartItemRSV {
    /**
     * 
     * updateOrdCartItem:更新购物车数量. <br/> 
     * @author linwei 
     * @param info
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public ROrdCartChgResponse updateOrdCartItemAmount(ROrdCartChgRequest rOrdCartChgRequest) throws BusinessException;  
    /**
     * 
     * delOrdCartItemAmount:(删除购物车明细). <br/> 
     * @author linwei 
     * @param info
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ROrdCartChgResponse delOrdCartItem(ROrdCartChgRequest rOrdCartChgRequest) throws BusinessException;     
    /**
     * 
     * updateOrdCartItemProm:(更新购物车明细促销信息). <br/> 
     * @author linwei 
     * @param info
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ROrdCartChgResponse updateOrdCartItemProm(ROrdCartChgRequest rOrdCartChgRequest) throws BusinessException; 
    
    /** 
     * updateGroupCartItemAmount:组合商品修改数量. <br/> 
     * @author cbl 
     * @param rGroupChgRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateGroupAmount(RGroupChgRequest rGroupChgRequest) throws BusinessException; 
    /** 
     * deleteGroup:删除组合商品. <br/> 
     * @author cbl 
     * @param rGroupChgRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteGroup(RGroupChgRequest rGroupChgRequest) throws BusinessException;
    
    /** 
     * updateCartItemAmount:没有促销情况的更新数量. <br/> 
     * @author cbl 
     * @param rOrdCartItemRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateOrdCartItemAmountNoProm(ROrdCartItemRequest  rOrdCartItemRequest) throws BusinessException;

    /**
     * 
     * queryCartItemByItemId:根据staffid和cartitemid查询数据. <br/> 
     * @author lwy 
     * @param preOrdSubresp
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ROrdCartItemResponse queryCartItemByItemId(ROrdCartItemCommRequest rOrdCartItemRequest)throws BusinessException;
}

