/**
 * 
 */
package com.zengshi.ecp.order.service.busi.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dao.mapper.busi.OrdCartItemHISMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdCartItemMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdCartMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdCartItemUalMapper;
import com.zengshi.ecp.order.dao.model.OrdCartItem;
import com.zengshi.ecp.order.dao.model.OrdCartItemCriteria;
import com.zengshi.ecp.order.dao.model.OrdCartItemHIS;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RGroupChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryCartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitSubRequest;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月10日下午2:44:54 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version
 * @since JDK 1.6
 */
public class OrdCartItemSVImpl implements IOrdCartItemSV {

    @Resource
    private OrdCartMapper ordCartMapper;

    @Resource
    private OrdCartItemMapper ordCartItemMapper;
    
    @Resource
    private OrdCartItemHISMapper ordCartItemHISMapper;
    
    @Resource
    private OrdCartItemUalMapper ordCartItemUalMapper;
    
    @Resource
    private IOrdCartSV ordCartSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource(name = "seq_ord_cart_item")
    private Sequence seqOrdCartItem;
    
    private static final String MODULE = OrdCartItemSVImpl.class.getName();

    /**
     * 
     * saveOrdCartItem:(保存购物车明细信息). <br/>
     * 
     * @author linwei
     * @param info
     * @since JDK 1.6
     */
    public void saveOrdCartItem(ROrdCartItemRequest sAddOrdCartItemRequest) {
        OrdCartItem orderCartItem = new OrdCartItem();
        ObjectCopyUtil.copyObjValue(sAddOrdCartItemRequest, orderCartItem, null, false);
        orderCartItem.setId(seqOrdCartItem.nextValue());
        orderCartItem.setAddTime(new Timestamp(System.currentTimeMillis()));
        orderCartItem.setCreateStaff(sAddOrdCartItemRequest.getStaffId());
        orderCartItem.setCreateTime(new Timestamp(System.currentTimeMillis()));
        orderCartItem.setUpdateStaff(sAddOrdCartItemRequest.getStaffId());
        orderCartItem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderCartItem.setSiteId(sAddOrdCartItemRequest.getCurrentSiteId());
        //调用商品接口，重新设置gdsName
        GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsInfoReqDTO.setId(sAddOrdCartItemRequest.getSkuId());
        gdsInfoReqDTO.setGdsId(sAddOrdCartItemRequest.getGdsId());
        gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] {
                GdsOption.SkuQueryOption.BASIC});
        GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                .querySkuInfoByOptions(gdsInfoReqDTO);
        if (!StringUtil.isEmpty(gdsInfoRespDTO)) {
            orderCartItem.setGdsName(gdsInfoRespDTO.getGdsName());
        }
        if(sAddOrdCartItemRequest.getGdsType()==2){  
            orderCartItem.setOrderAmount(1l);
        }
        //特殊处理，由于数字印刷不能参与其他促销并且不能和订单促销相叠加，需要设置特殊的promId
        if(!StringUtil.isBlank(orderCartItem.getPrnFlag())){
            if(orderCartItem.getPrnFlag().equals(OrdConstants.ShopCart.CART_ITEM_PRN_FLAG)){
                orderCartItem.setPromId(-3l);
            }   
        }
        
        ordCartItemMapper.insert(orderCartItem);
    }
     
    /**
     * 
     * queryOrdCartItemCount:(根据买家id查询购物车明细列表总数量). <br/>
     * 
     * @author panjs
     * @param info
     * @since JDK 1.6
     */
    public Long queryOrdCartItemCount(ROrdCartItemRequest sAddOrdCartItemRequest) {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria()
                .andStaffIdEqualTo(sAddOrdCartItemRequest.getStaffId())
                .andSiteIdEqualTo(sAddOrdCartItemRequest.getCurrentSiteId());
        Long cartItemCount = ordCartItemMapper.countByExample(orderCartItemCriteria);
        return cartItemCount;
    }
    
    public boolean queryExistOrdCartItem(ROrdCartItemRequest sAddOrdCartItemRequest) {
        // 1 调用查询存在相同购物车明细的明细id的方法
        OrdCartItem ordCartItem = queryExistOrdCartItemId(sAddOrdCartItemRequest);
        // 2 如果ordCartItemId为0，代表不存在相同的购物车明细，返回false，如果不等于0，代表存在相同购物车明细，返回ture
        if (StringUtil.isEmpty(ordCartItem)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * existOrdCartItemId:(查询存在相同购物车明细的购物车id----条件是同个staffId同个店铺同个购物车类型同个sku). <br/>
     * 
     * @author linwei
     * @param info
     * @return
     * @since JDK 1.6
     */
    public OrdCartItem queryExistOrdCartItemId(ROrdCartItemRequest sAddOrdCartItemRequest) {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        OrdCartItemCriteria.Criteria criteria = orderCartItemCriteria.createCriteria();
        criteria.andStaffIdEqualTo(sAddOrdCartItemRequest.getStaffId())
                .andCartTypeEqualTo(sAddOrdCartItemRequest.getCartType())
                .andShopIdEqualTo(sAddOrdCartItemRequest.getShopId())
                .andSkuIdEqualTo(sAddOrdCartItemRequest.getSkuId())
                .andGroupDetailEqualTo(sAddOrdCartItemRequest.getGroupDetail());
        if(LongUtils.isNotEmpty(sAddOrdCartItemRequest.getPromId())){
        	criteria.andPromIdEqualTo(sAddOrdCartItemRequest.getPromId());
        }
        List<OrdCartItem> ordCartItems = ordCartItemMapper.selectByExample(orderCartItemCriteria);
        if (ordCartItems.size() > 0) {
            OrdCartItem ordCartItem = ordCartItems.get(0);
            return ordCartItem;
        }
        return null;
    }
    /**
     * 
     * 根据购物车实例id查询购物车明细列表（可选）. 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV#showOrdCartItem(java.lang.Long, java.lang.Long)
     */
    @Override
    public List<OrdCartItem> queryOrdCartItem(Long ordCartId, Long staffId) {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(staffId).andCartIdEqualTo(ordCartId);
        List<OrdCartItem> ordCartItems = ordCartItemMapper.selectByExample(orderCartItemCriteria);
        return ordCartItems;
    }
    /**
     * 
     * 更新购物车数量. 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV#updateOrdCartItemAmount(java.lang.Long, java.lang.Long)
     */
    @Override
    public void updateOrdCartItemAmount(ROrdCartItemRequest info)  {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId()).andIdEqualTo(info.getId());
/*        List<OrdCartItem> ordCartItems =ordCartItemMapper.selectByExample(orderCartItemCriteria);
        if(!CollectionUtils.isEmpty(ordCartItems)){
            OrdCartItem ordCartItem = ordCartItems.get(0);
            ordCartItem.setOrderAmount(info.getOrderAmount());
            ordCartItemMapper.updateByPrimaryKey(ordCartItem);          
        }*/
        OrdCartItem ordCartItem = new OrdCartItem();
        ordCartItem.setOrderAmount(info.getOrderAmount());
        ordCartItemMapper.updateByExampleSelective(ordCartItem, orderCartItemCriteria); 
    }
   /**
    * 
    * 删除购物车明细方法. 
    * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV#delOrdCartItemAmount(com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest)
    */
    @Override
    public void deleteOrdCartItem(ROrdCartItemRequest info){
        //1删除明细
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId()).andIdEqualTo(info.getId());
        ordCartItemMapper.deleteByExample(orderCartItemCriteria);
        //2如果是最后一条明细记录，则调用删除购物车实例的方法，以避免存在无明细而有实例的数据，导致数据问题
        if(queryIsLastCartItem(info.getStaffId(),info.getCartId())){
            ordCartSV.deleteCart(info.getCartId(), info.getStaffId());
        }
    }
    public boolean queryIsLastCartItem(Long staffId,Long ordCartId) {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(staffId).andCartIdEqualTo(ordCartId);
        List<OrdCartItem> ordCartItems = ordCartItemMapper.selectByExample(orderCartItemCriteria);
        if(CollectionUtils.isEmpty(ordCartItems)){
            return true;
        }
        return false;
    }
    /**
     * 
     * 更细鼓舞车明细的促销信息. 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV#updateOrdCartItemProm(com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest)
     */
    @Override
    public void updateOrdCartItemProm(ROrdCartItemRequest info) {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId()).andIdEqualTo(info.getId());
/*        List<OrdCartItem> ordCartItems =ordCartItemMapper.selectByExample(orderCartItemCriteria);
        if(CollectionUtils.isEmpty(ordCartItems)){
            OrdCartItem ordCartItem = ordCartItems.get(0);
            ordCartItem.setPromId(info.getPromId());
            ordCartItemMapper.updateByPrimaryKeySelective(ordCartItem);            
        } */   
        OrdCartItem ordCartItem = new OrdCartItem();
        ordCartItem.setPromId(info.getPromId());
        ordCartItemMapper.updateByExampleSelective(ordCartItem, orderCartItemCriteria);
    }

    @Override
    public void deleteAfterOrderCartItem(RSumbitSubRequest rSumbitSubRequest,String source) {
        
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(rSumbitSubRequest.getStaffId())
                                              .andIdEqualTo(rSumbitSubRequest.getCartItemId());
        List<OrdCartItem> ordCartItems = ordCartItemMapper.selectByExample(orderCartItemCriteria);
        if(CollectionUtils.isEmpty(ordCartItems)){
            LogUtil.info(MODULE, "未找到[" + rSumbitSubRequest.getCartItemId() + "]该明细的信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
        }
        OrdCartItem ordCartItem = ordCartItems.get(0);
        OrdCartItemHIS  ordCartItemHIS = new OrdCartItemHIS();
        ObjectCopyUtil.copyObjValue(ordCartItem, ordCartItemHIS, null, false);
        ordCartItemHIS.setOrderId(rSumbitSubRequest.getOrderId());
        ordCartItemHIS.setOrderSubId(rSumbitSubRequest.getOrderSubId());
        ordCartItemHIS.setSource(source);
        this.ordCartItemHISMapper.insert(ordCartItemHIS);
        
        this.ordCartItemMapper.deleteByExample(orderCartItemCriteria);
    }

    @Override
    public void updateGroupAmount(RGroupChgRequest rGroupChgRequest) throws BusinessException {
        for(ROrdCartItemRequest rOrdCartItemRequest: rGroupChgRequest.getrOrdCartItemRequests()){
            rOrdCartItemRequest.setStaffId(rGroupChgRequest.getStaff().getId());
            updateOrdCartItemAmount(rOrdCartItemRequest);
        }
    }

    @Override
    public void deleteGroup(RGroupChgRequest rGroupChgRequest) throws BusinessException {
        for(ROrdCartItemRequest rOrdCartItemRequest: rGroupChgRequest.getrOrdCartItemRequests()){
            rOrdCartItemRequest.setStaffId(rGroupChgRequest.getStaff().getId());
            deleteOrdCartItem(rOrdCartItemRequest);
        }
    }

    @Override
    public Long querySumAmountByStaffId(RQueryCartRequest rQueryCartRequest) throws BusinessException {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(rQueryCartRequest.getStaffId())
                                              .andSiteIdEqualTo(rQueryCartRequest.getCurrentSiteId());
        Long count=this.ordCartItemUalMapper.countSumAmountByExample(orderCartItemCriteria);
        if(LongUtils.isNotEmpty(count)){
        	return count;
        }else{
        	return 0L; 
        }
    }

    @Override
    public void deleteCartItemsBatch(RBatchCartChgRequest rBatchCartChgRequest)
            throws BusinessException {
        for(ROrdCartItemRequest rOrdCartItemRequest: rBatchCartChgRequest.getrOrdCartItemRequests()){
            rOrdCartItemRequest.setStaffId(rBatchCartChgRequest.getStaff().getId());
            deleteOrdCartItem(rOrdCartItemRequest);
        }
    }

    @Override
    public OrdCartItem queryCartItemByItemId(Long cartItemId, Long staffId) {
        OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
        orderCartItemCriteria.createCriteria().andStaffIdEqualTo(staffId).andIdEqualTo(cartItemId);
        List<OrdCartItem> ordCartItems = ordCartItemMapper.selectByExample(orderCartItemCriteria);
        if(CollectionUtils.isEmpty(ordCartItems)){
            return null;
        }
        return ordCartItems.get(0);
    }

    @Override
    public OrdCartItem queryCartItemByKey(Long cartItemId) {
        OrdCartItem ordCartItem = ordCartItemMapper.selectByPrimaryKey(cartItemId);
        return ordCartItem;
    }
}
