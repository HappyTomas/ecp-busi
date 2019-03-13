package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.model.OrdCartItem;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RGroupChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitSubRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;


/**
 * @author linwei
 *
 */
public interface IOrdCartItemSV {
	
    /**
     * 
     * saveOrdCartItem:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei 
     * @param info
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public void saveOrdCartItem(ROrdCartItemRequest sAddOrdCartItemRequest);   
    
    /**
     * 
     * queryOrdCartItemCount:(根据买家id查询购物车明细列表总数量). <br/>
     * 
     * @author panjs
     * @param info
     * @since JDK 1.6
     */
    public Long queryOrdCartItemCount(ROrdCartItemRequest sAddOrdCartItemRequest);
    
    /**
     * 
     * existOrdCartItem:(查询存在相同购物车明细的购物车). <br/> 
     * @author linwei 
     * @param info
     * @throws DataAccessException 
     * @since JDK 1.6
     */    
    public boolean queryExistOrdCartItem(ROrdCartItemRequest sAddOrdCartItemRequest);
    /**
     * 
     * existOrdCartItemId:(查询存在相同购物车明细的购物车id----条件是同个staffId同个店铺同个购物车类型同个sku). <br/> 
     * @author linwei 
     * @param info
     * @return 
     * @since JDK 1.6
     */
    public OrdCartItem queryExistOrdCartItemId(ROrdCartItemRequest sAddOrdCartItemRequest);    
    /**
     * 
     * showOrdCartItem:(查询购物车明细的方法). <br/> 
     * @author linwei 
     * @param info
     * @return 
     * @since JDK 1.6
     */
    public List<OrdCartItem> queryOrdCartItem(Long ordCartId,Long staffId); 
    
    /**
     * 
     * updateOrdCartItemAmount:(更新购物车明细数量). <br/> 
     * @author linwei 
     * @param ordCartItem
     * @param staffId
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public void updateOrdCartItemAmount(ROrdCartItemRequest info); 
    
    /**
     * 
     * updateOrdCartItemProm:(更新购物车促销id). <br/> 
     * @author linwei 
     * @param ordCartItem
     * @param staffId
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public void updateOrdCartItemProm(ROrdCartItemRequest info);     
    
    /**
     * 
     * delOrdCartItem:(删除购物车明细). <br/> 
     * @author linwei 
     * @param info
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    public void deleteOrdCartItem(ROrdCartItemRequest info); 
    
    /** 
     * deleteAfterOrderCartItem:生成订单之后删除购物车明细实例. <br/> 
     * @author cbl 
     * @param rSumbitSubRequest 
     * @since JDK 1.6 
     */ 
    public void deleteAfterOrderCartItem(RSumbitSubRequest rSumbitSubRequest,String source);
    
    /** 
     * isLastCartItem:是否最后一条记录. <br/> 
     * @author cbl 
     * @param staffId
     * @param ordCartId
     * @return 
     * @since JDK 1.6 
     */ 
    public boolean queryIsLastCartItem(Long staffId,Long ordCartId);
    
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
     * querySumAmountByCartId:根据购物车ID查询商品数量. <br/> 
     * @author cbl 
     * @param cartId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public Long querySumAmountByStaffId(RQueryCartRequest rQueryCartRequest) throws BusinessException;
    
    /** 
     * deleteCartItemsBatch:批量删除明细. <br/> 
     * @author cbl 
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCartItemsBatch(RBatchCartChgRequest rBatchCartChgRequest) throws BusinessException;
    
    /** 
     * queryCartItemByItemId:根据cartItemId查询购物车明细记录. <br/> 
     * @author cbl 
     * @param cartItemId
     * @param staffId
     * @return 
     * @since JDK 1.6 
     */ 
    public OrdCartItem queryCartItemByItemId(Long cartItemId,Long staffId);
    
    /** 
     * queryCartItemByKey:根据主键查询购物车明细记录. <br/> 
     * @author cbl 
     * @param cartItemId
     * @param staffId
     * @return 
     * @since JDK 1.6 
     */ 
    public OrdCartItem queryCartItemByKey(Long cartItemId);
}
