package com.zengshi.ecp.order.dubbo.interfaces;


import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdCartsResponse;
import com.zengshi.ecp.order.dubbo.dto.cart.FastOrdRequest;
import com.zengshi.ecp.order.dubbo.dto.cart.FastPreOrdRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdCartRSV {
    /**
     * 
     * addToCart:加入购物车. <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void addToCart(ROrdCartRequest info) throws BusinessException;
    /**
     * 
     * showCart:(展示购物车方法). <br/> 
     * @author linwei 
     * @param info
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public RShowOrdCartsResponse queryCart(ROrdCartRequest info) throws BusinessException;

    /**
     *
     * fastOrdCart:(展示购物车方法). <br/>
     * @author wangxq
     * @param info
     * @throws BusinessException
     * @since JDK 1.6
     */
    public RShowOrdCartsResponse fastOrdCart(FastOrdRequest info) throws BusinessException;

    /**
     *
     * carToPreOrd:(购物车转预订单方法). <br/>
     * @author linwei
     * @throws BusinessException
     * @since JDK 1.6
     */
    public RPreOrdMainsResponse createPreOrd(RCrePreOrdsRequest info) throws BusinessException;

    /**
     *
     * carToPreOrd:(购物车转预订单方法). <br/>
     * @author linwei
     * @throws BusinessException
     * @since JDK 1.6
     */
    public RPreOrdMainsResponse createFastPreOrd(FastPreOrdRequest info) throws BusinessException;
    /**
     * 
     * updateOrdCartProm:(更新购物车促销实例). <br/> 
     * @author linwei 
     * @throws BusinessException 
     * @since JDK 1.6
     */    
    public ROrdCartChgResponse updateOrdCartProm(ROrdCartChgRequest rOrdCartChgRequest) throws BusinessException;  
    
    
    /** 
     * createOrderChk:去结算校验，提交订单检验. <br/> 
   
     * @author cbl 
     * @param rOrdCartsChkRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse createOrderChk(ROrdCartsCommRequest rOrdCartsCommRequest) throws BusinessException;
    
    /** 
     * querySumAmountByCartId:买家查询商品数量. <br/> 
     * @author cbl 
     * @param cartId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public Long querySumAmountByStaffId(RQueryCartRequest rQueryCartRequest) throws BusinessException;
    
    /** 
     * assembleParamForProm:组装促销信息入参. <br/> 
     * @author cbl 
     * @param rOrdCartChgRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartChgResponse assembleParamForProm(ROrdCartChgRequest rOrdCartChgRequest) throws BusinessException;
    
    /** 
     * deleteBatchCartItems:批量删除购物车明细. <br/> 
     * @author cbl 
     * @param rOrdCartChgRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartsCommRequest deleteBatchCartItems(RBatchCartChgRequest rBatchCartChgRequest) throws BusinessException;
}

