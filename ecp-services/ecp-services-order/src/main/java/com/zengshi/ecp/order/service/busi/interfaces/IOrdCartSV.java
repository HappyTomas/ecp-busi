package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.cart.FastOrdRequest;
import com.zengshi.ecp.order.dubbo.dto.cart.FastPreOrdRequest;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdCartsResponse;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;

/**
 * @author linwei
 *
 */
public interface IOrdCartSV {
	
    /**
     * 
     * addToCart:(加入购物车方法). <br/> 
     * @author linwei 
     * @param info
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public void addToCart(ROrdCartRequest info);
    
    /**
     * 
     * showCart:(展示购物车方法). <br/> 
     * @author linwei 
     * @param info
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public RShowOrdCartsResponse queryCart(ROrdCartRequest info);
    /**
     * 
     * delCart:(删除购物车实例). <br/> 
     * @author linwei 
     * @param ordCartId
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public void deleteCart(Long ordCartId,Long staffId);   
    /**
     * 
     * carToPreOrd:(购物车转预订单方法). <br/> 
     * @author linwei 
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public RPreOrdMainsResponse queryPreOrd(RCrePreOrdsRequest info); 
    /**
     * 
     * updateOrdCartProm:(更新购物车实例促销信息). <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    
    public void updateOrdCartProm(ROrdCartItemRequest info);
    
    /** 
     * calcShip:计算运费. <br/> 
     * @author cbl 
     * @param rOrdCartsCommRequest 
     * @since JDK 1.6 
     */ 
    public Map<Long, Long> calcShip(ROrdCartsCommRequest rOrdCartsCommRequest);
    
    /** 
     * AssembleParamForProm:变更购物车信息 组装促销域入参. <br/> 
     * @author cbl 
     * @param rOrdCartChgRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartChgResponse queryAssembleParamForProm(ROrdCartChgRequest rOrdCartChgRequest);

    /**
     *
     * fastOrdCart:(立即购买校验购物车方法). <br/>
     * @author wangxq
     * @param fastOrdRequest
     * @throws DataAccessException
     * @since JDK 1.6
     */
    public RShowOrdCartsResponse fastOrdCart(FastOrdRequest fastOrdRequest);

    /**
     * queryFastAssembleParamForAll:轻松购 购物车生成预订单之前较验入参. <br/>
     * @author cbl
     * @param fastOrdRequest
     * @return
     * @since JDK 1.6
     */
    public ROrdCartsCommRequest queryFastAssembleParamForAll(FastPreOrdRequest fastOrdRequest);

    /**
     *
     * carToPreOrd:(购物车转预订单方法). <br/>
     * @author linwei
     * @throws DataAccessException
     * @since JDK 1.6
     */
    public RPreOrdMainsResponse queryFastPreOrd(FastPreOrdRequest info);

    /** 
     * AssembleParamForProm:批量删除 组装促销域入参. <br/> 
     * @author cbl 
     * @param rOrdCartChgRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartsCommRequest queryBatchDeleteForProm(RBatchCartChgRequest rBatchCartChgRequest);
    /** 
     * AssembleParamForProm:组装购物车生成预订单之前较验入参. <br/> 
     * @author cbl 
     * @param rOrdCartsCommRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartsCommRequest queryAssembleParamForAll(RCrePreOrdsRequest rCrePreOrdsRequest);
    
    
    /** 
     * deleteAfterOrderCart:生成订单之后删除购物车实例. <br/> 
     * @author cbl 
     * @param rSumbitMainsRequest 
     * @since JDK 1.6 
     */ 
    public void deleteAfterOrderCart(RSumbitMainsRequest rSumbitMainsRequest);
    
    /** 
     * queryBeforOrderCart:生成订单前确认购物车数据. <br/> 
     * @author cbl 
     * @param rSumbitMainsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public boolean queryBeforOrderCart(RSumbitMainsRequest rSumbitMainsRequest);

    /**
     * queryFastBeforOrderCart:生成订单前确认立即购买数据. <br/>
     * @author cbl
     * @param rSumbitMainsRequest
     * @return
     * @since JDK 1.6
     */
    public boolean queryFastBeforOrderCart(RSumbitMainsRequest rSumbitMainsRequest);

}
