/**
 * 
 */
package com.zengshi.ecp.order.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.CartItemPriceInfo;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPriceRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dao.mapper.busi.OrdCartHISMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdCartItemMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdCartMapper;
import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartItemsRequest;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartsRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdItemRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdCartsResponse;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitSubRequest;
import com.zengshi.ecp.order.dubbo.dto.cart.FastOrdRequest;
import com.zengshi.ecp.order.dubbo.dto.cart.FastPreOrdRequest;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV;
import com.zengshi.ecp.order.service.busi.interfaces.IThingLockSV;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
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
public class OrdCartSVImpl implements IOrdCartSV {

    @Resource
    private OrdCartMapper ordCartMapper;

    @Resource
    private OrdCartItemMapper ordCartItemMapper;

    @Resource
    private OrdCartHISMapper ordCartHISMapper;

    @Resource
    private IOrdCartItemSV ordCartItemSV;

    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource
    private IGdsStockRSV gdsStockRSV;

    @Resource
    private IGdsPriceRSV gdsPriceRSV;

    @Resource
    private IPromRSV promRSV;

    @Resource
    private IAcctManageRSV acctManageRSV;

    @Resource
    private ICustAddrRSV custAddrRSV;

    @Resource
    private IGdsShiptemRSV gdsShiptemRSV;

    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;

    @Resource
    private ICustInfoRSV  custInfoRSV;
    @Resource
    private IShopManageRSV shopManageRSV;
    
    @Resource
    private IPromRSV proRSV;
    @Resource    
    private IGdsCategoryRSV gdsCategoryRSV; 

    @Resource(name = "seq_ord_cart")
    private Sequence seqOrdCart;

    @Resource
    private ICoupDetailRSV iCoupDetailRSV;

    @Resource
    private IThingLockSV thingLockSV;

    private static final String MODULE = OrdCartSVImpl.class.getName();

    /**
     * 
     * 加入购物车方法（可选）.
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV#addToCart(ROrdCartRequest)
     */

    public void addToCart(ROrdCartRequest info) {

        ThingLock thingLock = new ThingLock();
        thingLock.setId(Long.toString(info.getStaff().getId())+info.getShopId());
        thingLock.setType(CommonConstants.LockType.LOCK_TYPE_ADD_CART);
        this.thingLockSV.addThingLock(thingLock);

        // 1 判断是否符合购物车实例合并规则，如果符合，则调用创建购物车方法
        Long ordCartId = queryExistOrdCartId(info);
        if (ordCartId != 0) {
            info.setId(ordCartId);
            updateOrdCart(info);
        } else {
            createOrdCart(info);
        }
        this.thingLockSV.removeThingLock(thingLock);
    }

    /**
     * 
     * createOrdCart:(新建购物车方法). <br/>
     * 
     * @author linwei
     * @since JDK 1.6
     */
    private void createOrdCart(ROrdCartRequest info) {
        ROrdCartItemRequest cartItemRequest = new ROrdCartItemRequest();
        cartItemRequest.setStaffId(info.getStaffId());
        cartItemRequest.setCurrentSiteId(info.getCurrentSiteId());
        Long cartItemCount = this.ordCartItemSV.queryOrdCartItemCount(cartItemRequest);
        //对于mysql返回null的处理
        if(LongUtils.isEmpty(cartItemCount)){
        	cartItemCount=0L;
        }
        //判断购物车商品明细数量是否大于99
        if(cartItemCount.longValue()>=99){
            LogUtil.info(MODULE, info.getStaffId() + ":购物车宝贝总数已满99件");
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350013);
        }else{
         // 1 存入购物车主表T_ORD_CART
            Long ordCartId = saveOrdCart(info);
            // 2 存入购物车明细表T_ORD_CART_ITEM
            List<ROrdCartItemRequest> ordCartItemList = info.getOrdCartItemList();
            for (ROrdCartItemRequest sAddOrdCartItemRequest : ordCartItemList) {
                if (StringUtil.isEmpty(sAddOrdCartItemRequest)) {
                    throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351002);
                }
                sAddOrdCartItemRequest.setCartId(ordCartId); 
                this.ordCartItemSV.saveOrdCartItem(sAddOrdCartItemRequest);
            } 
        }
        
    }

    /**
     * 
     * saveOrdCart:(保存购物车实例主表). <br/>
     * 
     * @author linwei
     * @param info
     * @since JDK 1.6
     */
    private Long saveOrdCart(ROrdCartRequest info) {
        Long ordCartId = seqOrdCart.nextValue();
        OrdCart ordCart = new OrdCart();
        ordCart.setId(ordCartId);
        ordCart.setCartType(info.getCartType());
        ordCart.setShopId(info.getShopId());
        ordCart.setShopName(info.getShopName());
        ordCart.setStaffId(info.getStaffId());
        ordCart.setCreateStaff(info.getStaffId());
        ordCart.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ordCart.setUpdateStaff(info.getStaffId());
        ordCart.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        ordCart.setSiteId(info.getCurrentSiteId());
        this.ordCartMapper.insert(ordCart);
        return ordCartId;
    }

    /**
     * 
     * updateOrdCart:(更新购物车方法). <br/>
     * 
     * @author linwei
     * @since JDK 1.6
     */
    private void updateOrdCart(ROrdCartRequest info) {
        List<ROrdCartItemRequest> ordCartItemList = info.getOrdCartItemList();
        for (ROrdCartItemRequest sAddOrdCartItemRequest : ordCartItemList) {
            if (StringUtil.isEmpty(sAddOrdCartItemRequest)) {
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351002);
            }
            sAddOrdCartItemRequest.setCartId(info.getId());
            // 1 调用查询是否存在相同购物车明细的购物城明细id的方法
            OrdCartItem ordCartItem = this.ordCartItemSV.queryExistOrdCartItemId(sAddOrdCartItemRequest);
            if (!StringUtil.isEmpty(ordCartItem)) {
                sAddOrdCartItemRequest.setId(ordCartItem.getId());
                sAddOrdCartItemRequest.setOrderAmount(
                        ordCartItem.getOrderAmount() + sAddOrdCartItemRequest.getOrderAmount());
                //如果是虚拟书，不进行合并
                LongReqDTO longReqDTO=new LongReqDTO();
                longReqDTO.setId(ordCartItem.getGdsType());
                if(gdsInfoExternalRSV.isGdsTypeBuyMore(longReqDTO)){  
                    this.ordCartItemSV.updateOrdCartItemAmount(sAddOrdCartItemRequest); 
                }else{

                }
            } else {
                Long cartItemCount = this.ordCartItemSV.queryOrdCartItemCount(sAddOrdCartItemRequest);
                //对于mysql返回null的处理
                if(LongUtils.isEmpty(cartItemCount)){
                	cartItemCount=0L;
                }
                //判断购物车商品明细数量是否大于99
                if(cartItemCount.longValue()>=99){
                    LogUtil.info(MODULE, sAddOrdCartItemRequest.getStaffId()+":购物车宝贝总数已满99件");
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350013);
                }else{
                    this.ordCartItemSV.saveOrdCartItem(sAddOrdCartItemRequest);
                }
                
            }
        }

    }

    /**
     * 
     * existOrdCartId:(查询存在相同购物车实例的购物车id----条件是同个操作者同个店铺同种购物车类型). <br/>
     * 
     * @author linwei
     * @param info
     * @return
     * @since JDK 1.6
     */
    private Long queryExistOrdCartId(ROrdCartRequest info) {
        OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
        orderCartCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId())
                .andCartTypeEqualTo(info.getCartType()).andShopIdEqualTo(info.getShopId()).andSiteIdEqualTo(info.getCurrentSiteId());
        List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria);
        if (CollectionUtils.isNotEmpty(ordCarts)) {
            OrdCart ordCart = ordCarts.get(0);
            return ordCart.getId();
        }
        return 0l;
    }

    /**
     * 
     * 展示购物车的方法.
     * 
     * @throws
     * 
     *             @see
     *             com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV#queryCart(ROrdCartRequest info)
     */
    @Override
    public RShowOrdCartsResponse queryCart(ROrdCartRequest info) {
        // 1 从购物车主表中获取登录者的购物车主表信息
        OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
        orderCartCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId())
                .andSiteIdEqualTo(info.getCurrentSiteId());
        List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria);
        // 获取买家的默认收货地址
        /*
         * CustAddrResDTO custAddrResDTO=custAddrRSV.findDefaultAddr(info.getStaffId());
         * if(StringUtil.isEmpty(custAddrResDTO)){ throw new
         * BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001); } String
         * provinceCode=custAddrResDTO.getProvince();
         */
        // 展示的购物车实例列表
        List<ROrdCartResponse> rShowOrdCartList = new ArrayList<ROrdCartResponse>();
        // 返回的出参，给前店的
        RShowOrdCartsResponse rShowOrdCartsResponse = new RShowOrdCartsResponse();
        
        List<ROrdCartItemCommRequest> isNoGdsStatus = new ArrayList<ROrdCartItemCommRequest>();
        
        // 2 循环购物车主表，查询出购物车明细列表信息
        if (!CollectionUtils.isEmpty(ordCarts)) {
            // 封装公有入参
            ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
            //rOrdCartsCommRequest.setPromSubmitType(PromConstants.PromTypeMsg.CART_SUBMIT_TYPE);
            if(StringUtil.isNotEmpty(info.getPromSubmitType())){
                rOrdCartsCommRequest.setPromSubmitType(info.getPromSubmitType());
            }else{
                rOrdCartsCommRequest.setPromSubmitType(PromConstants.PromTypeMsg.CART_SUBMIT_TYPE);	
            }
            // rOrdCartsCommRequest.setCurrentSiteId(1l);
            rOrdCartsCommRequest.setStaffId(info.getStaffId());
            List<ROrdCartCommRequest> ordCartsCommList = new ArrayList<ROrdCartCommRequest>();
            for (OrdCart ordCart : ordCarts) {
                
                // 展示的每个购物车实例下的明细列表
                List<ROrdCartItemResponse> rShowOrdCartItemList = new ArrayList<ROrdCartItemResponse>();
                // 展示的购物车实例
                ROrdCartResponse rShowOrdCartResponse = new ROrdCartResponse();
                ObjectCopyUtil.copyObjValue(ordCart, rShowOrdCartResponse, null, false);
                ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
                ObjectCopyUtil.copyObjValue(ordCart, rOrdCartCommRequest, null, false);
                List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
                Long ordCartId = ordCart.getId();
                List<OrdCartItem> ordCartItems = ordCartItemSV.queryOrdCartItem(ordCartId,
                        info.getStaffId());
                if (CollectionUtils.isEmpty(ordCartItems)) {
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
                }
                // 循环购物车明细列表，封装上价格等给页面展示的属性
                for (OrdCartItem ordCartItem : ordCartItems) {
                    ROrdCartItemResponse rShowOrdCartItemResponse = new ROrdCartItemResponse();
                    ObjectCopyUtil.copyObjValue(ordCartItem, rShowOrdCartItemResponse, null, false);
                    String skuInfo="";
                    if(!StringUtil.isBlank(rShowOrdCartItemResponse.getSkuInfo())){
                        skuInfo=rShowOrdCartItemResponse.getSkuInfo();
                    }
                    //增加对商品属性展示的优化，加上商品的2级分类
                    GdsCategoryReqDTO categoryDTO=new GdsCategoryReqDTO();
                    categoryDTO.setCatgCode(ordCartItem.getCategoryCode());
                    List<GdsCategoryRespDTO> categoryRespList=gdsCategoryRSV.queryCategoryTraceUpon(categoryDTO);
                    if(CollectionUtils.isNotEmpty(categoryRespList)){
                        if(categoryRespList.size()>1){
                            GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                            GdsCategoryRespDTO categoryRes2=categoryRespList.get(1);    
                            //rShowOrdCartItemResponse.setSkuInfo(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName()+"    "+skuInfo);   
                            rShowOrdCartItemResponse.setGdsCateName(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName());
                        }else{
                            GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                            //rShowOrdCartItemResponse.setSkuInfo(categoryRes1.getCatgName()+"    "+skuInfo);   
                            rShowOrdCartItemResponse.setGdsCateName(categoryRes1.getCatgName());
                        }
                    }
                    ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                    ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
                    ordCartItemCommList.add(rOrdCartItemCommRequest);
                    
                    // 补充页面展示的购物车明细信息----调用商品信息的接口，获取商品的图片，URL
                    GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
                    gdsInfoReqDTO.setId(ordCartItem.getSkuId());
                    gdsInfoReqDTO.setGdsId(ordCartItem.getGdsId());
                    gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] {
                            GdsOption.SkuQueryOption.BASIC, GdsOption.SkuQueryOption.MAINPIC });
                    GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                            .querySkuInfoByOptions(gdsInfoReqDTO);
                    if (StringUtil.isEmpty(gdsInfoRespDTO)) {
                        throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
                    }
                    rShowOrdCartItemResponse.setGdsStatus(GdsUtils.isOnShelves(gdsInfoRespDTO.getGdsStatus()));
                    
                    
                    
                    if (!StringUtil.isEmpty(gdsInfoRespDTO.getMainPic())) {
                        rShowOrdCartItemResponse.setPicUrl(gdsInfoRespDTO.getMainPic().getURL());
                        rShowOrdCartItemResponse.setPicId(gdsInfoRespDTO.getMainPic().getMediaUuid());
                    }
                    rShowOrdCartItemResponse.setGdsUrl(gdsInfoRespDTO.getUrl());
                    // 库存，调用商品查询库存接口
                    StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
                    stockInfoForGdsDTO.setShopId(ordCartItem.getShopId());
                    stockInfoForGdsDTO.setGdsId(ordCartItem.getGdsId());
                    stockInfoForGdsDTO.setSkuId(ordCartItem.getSkuId());
                    stockInfoForGdsDTO.setTypeId(ordCartItem.getGdsType());
                    StockInfoRespDTO stockInfoDTO = gdsInfoExternalRSV
                            .getStockAmount(stockInfoForGdsDTO);

                    Long availCount = 0l;
                    if (!StringUtil.isEmpty(stockInfoDTO)) {
                        availCount = stockInfoDTO.getAvailCount();
                    }
                    rShowOrdCartItemResponse.setStockAvailCount(availCount);
                    rShowOrdCartItemList.add(rShowOrdCartItemResponse);
                    //如果是下架商品不调促销域
                    if(!rShowOrdCartItemResponse.isGdsStatus()){
                        isNoGdsStatus.add(rOrdCartItemCommRequest);
                    }
                    
                }
                rOrdCartCommRequest.setOrdCartItemCommList(ordCartItemCommList);

                ordCartsCommList.add(rOrdCartCommRequest);
                rShowOrdCartResponse.setOrdCartItemList(rShowOrdCartItemList);
                rShowOrdCartList.add(rShowOrdCartResponse);
            }
            rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
            rShowOrdCartsResponse.setStaffId(info.getStaffId());
            rShowOrdCartsResponse.setOrdCartList(rShowOrdCartList);
            rOrdCartsCommRequest.setSource(info.getSource());

            // 根据封装的参数，进行运费和价格的计算
            Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
            // 进行一次出参的循环，把对应的运费和价格封装进去
            for (ROrdCartResponse rResultOrdCartResponse : rShowOrdCartsResponse.getOrdCartList()) {
                for (ROrdCartItemResponse rShowOrdCartItemResponse : rResultOrdCartResponse
                        .getOrdCartItemList()) {
                    Long skuId = rShowOrdCartItemResponse.getSkuId();
                    CartItemPriceInfo cartItemPriceInfo = priceMap.get(skuId);
                    if (!StringUtil.isEmpty(cartItemPriceInfo)) {
                        rShowOrdCartItemResponse.setBasePrice(cartItemPriceInfo.getBasePrice());
                        rShowOrdCartItemResponse.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                        rShowOrdCartItemResponse.setScore(cartItemPriceInfo.getScore() == null ? 0l
                                : cartItemPriceInfo.getScore());
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(isNoGdsStatus)){
                for(ROrdCartItemCommRequest rOrdCartItemCommRequest: isNoGdsStatus){
                    for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
                        rOrdCartCommRequest.getOrdCartItemCommList().remove(rOrdCartItemCommRequest);
                    }
                }
                List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
                for(ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()){
                    if(CollectionUtils.isEmpty(rOrdCartCommRequest.getOrdCartItemCommList())){
                        rOrdCartCommRequests.add(rOrdCartCommRequest);
                    }
                }
                for(ROrdCartCommRequest rOrdCartCommRequest:rOrdCartCommRequests){
                    rOrdCartsCommRequest.getOrdCartsCommList().remove(rOrdCartCommRequest);
                }
            }
            
            // 封装促销相关信息
            for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
                
                CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                custInfoReqDTO.setId(info.getStaff().getId());
                CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
                ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
                shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
                shopStaffGroupReqDTO.setShopId(rOrdCartCommRequest.getShopId());
                // 客户组id
                String custGroupValue = null;
                if (custInfoResDTO != null && custInfoResDTO.getId() != null
                        && custInfoResDTO.getId() != 0) {
                    custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
                }
                // 客户基本信息
                rOrdCartCommRequest.setCustGroupValue(custGroupValue);
                rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
                rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
                //web  渠道
                rOrdCartCommRequest.setChannelValue(info.getSource()); 
                
                for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                    rOrdCartItemCommRequest.setBasePrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBasePrice());
                    rOrdCartItemCommRequest.setBuyPrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBuyPrice());
                    rOrdCartItemCommRequest.setCurrentSiteId(info.getCurrentSiteId());
                }
            }
            LogUtil.info(MODULE, "input:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
            rOrdCartsCommRequest.setCurrentSiteId(info.getCurrentSiteId());
            LogUtil.info(MODULE, "促销入参:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
            if(CollectionUtils.isNotEmpty(rOrdCartsCommRequest.getOrdCartsCommList())){
                CartPromRespDTO cprd = this.promRSV.initCartPromMap(rOrdCartsCommRequest);
                rShowOrdCartsResponse.setCartPromRespDTO(cprd);
                if (cprd != null) {
                    LogUtil.info(MODULE, "out:" + JSON.toJSONString(cprd).toString());
                    LogUtil.info(MODULE, "促销出参:" + JSON.toJSONString(cprd).toString());
                } else {
                    LogUtil.info(MODULE, "---------------------------------NULL");
                }
            }
            
/*            
            //计算运费
            for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest
                    .getOrdCartsCommList()) {
                Long payMoney = 0l;
                // 订单级别优惠
                Map<Long, CartPromDTO> ordMap = null;
                // 子订单级别优惠
                Map<Long, CartPromItemDTO> ordSubMap = null;
                if (rShowOrdCartsResponse.getCartPromRespDTO() != null) {
                    ordMap = rShowOrdCartsResponse.getCartPromRespDTO().getCartPromDTOMap();
                    ordSubMap = rShowOrdCartsResponse.getCartPromRespDTO().getCartPromItemDTOMap();
                }
                for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest
                        .getOrdCartItemCommList()) {
                    // 商品金额 -计算运费
                    payMoney += rOrdCartItemCommRequest.getBuyPrice()
                            * rOrdCartItemCommRequest.getOrderAmount();

                    //计算运费
                    rOrdCartItemCommRequest.setOrderMoney(rOrdCartItemCommRequest.getBuyPrice()
                            * rOrdCartItemCommRequest.getOrderAmount());

                    if (ordSubMap != null) {
                        CartPromItemDTO itemProm = ordSubMap.get(rOrdCartItemCommRequest.getId());

                        if (itemProm != null) {
                            Long discountMoney = itemProm.getDiscountMoney() == null ? 0l
                                    : itemProm.getDiscountMoney().longValue();
                            Long discountPriceMoney = itemProm.getDiscountPriceMoney() == null ? 0l
                                    : itemProm.getDiscountPriceMoney().longValue();

                            rOrdCartItemCommRequest
                                    .setOrderMoney(rOrdCartItemCommRequest.getOrderMoney()
                                            - (discountMoney + discountPriceMoney));
                            // 优惠金额
                            payMoney -= (discountMoney + discountPriceMoney);
                        }

                    }
                }
                if (ordMap != null) {
                    CartPromDTO cartProm = ordMap.get(rOrdCartCommRequest.getId());
                    if (cartProm != null) {
                        Long discountMoney = cartProm.getDiscountMoney() == null ? 0l
                                : cartProm.getDiscountMoney().longValue();
                        Long discountPriceMoney = cartProm.getDiscountPriceMoney() == null ? 0l
                                : cartProm.getDiscountPriceMoney().longValue();
                        payMoney -= (discountMoney + discountPriceMoney);
                    }
                }
                rOrdCartCommRequest.setPayMoney(payMoney);
            }
            rOrdCartsCommRequest.setCurrentSiteId(info.getCurrentSiteId());
            LogUtil.info(MODULE,
                    "ship input:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
            // 根据封装的参数，进行运费计算
            Map<Long, Long> shipExpenseMap = gdsShiptemRSV
                    .calcShipExpenseByCarts(rOrdCartsCommRequest);
            for (ROrdCartResponse rResultOrdCartResponse : rShowOrdCartsResponse.getOrdCartList()) {
                Long shipExpense = shipExpenseMap.get(rResultOrdCartResponse.getShopId());
                rResultOrdCartResponse.setExpressFee(shipExpense);
            }*/

        }
//        LogUtil.info(MODULE, "展示购物车:" + JSON.toJSONString(rShowOrdCartsResponse).toString());
        //处理组合商品
        if(CollectionUtils.isNotEmpty(rShowOrdCartsResponse.getOrdCartList()))
            delGroupQueryCart(rShowOrdCartsResponse);
        LogUtil.info(MODULE, "展示购物车:" + JSON.toJSONString(rShowOrdCartsResponse).toString());
        return rShowOrdCartsResponse;
    }

    /** 
     * delGroup:处理组合商品. <br/> 
     * @author cbl 
     * @param rShowOrdCartsResponse 
     * @since JDK 1.6 
     */ 
    private void delGroupQueryCart(RShowOrdCartsResponse rShowOrdCartsResponse){
        for (ROrdCartResponse rResultOrdCartResponse : rShowOrdCartsResponse.getOrdCartList()) {
            
            Map<String, Map<Long, ROrdCartItemResponse>> cartMap = null;
            List<List<ROrdCartItemResponse>> groupLists = null;
            List<ROrdCartItemResponse> ros = new ArrayList<ROrdCartItemResponse>();
            for(ROrdCartItemResponse rOrdCartItem:rResultOrdCartResponse.getOrdCartItemList()){
                if(rOrdCartItem.getGroupType().equals("0")){
                    continue;
                } else if(rOrdCartItem.getGroupType().equals("1")){
                    if(cartMap == null){
                        cartMap = new HashMap<String, Map<Long, ROrdCartItemResponse>>();
                    }
                    if(StringUtils.isNotBlank(rOrdCartItem.getGroupDetail())&&cartMap.get(""+rOrdCartItem.getPromId()) == null){
                        Map<Long, ROrdCartItemResponse> cartItemMap = new HashMap<Long, ROrdCartItemResponse>();
                        cartMap.put(""+rOrdCartItem.getPromId(), cartItemMap);
                    }
                    
                    cartMap.get(""+rOrdCartItem.getPromId()).put(rOrdCartItem.getId(), rOrdCartItem);
                    ros.add(rOrdCartItem);
                } else {
                   
                }
            }
            for(ROrdCartItemResponse rOrdCartItem:ros){
                rResultOrdCartResponse.getOrdCartItemList().remove(rOrdCartItem);
            }
            
            if(cartMap != null){
//                rResultOrdCartResponse.setGroupMaps(cartMap);
                
                groupLists = new ArrayList<List<ROrdCartItemResponse>>();
                Iterator<Entry<String, Map<Long, ROrdCartItemResponse>>> iter = cartMap.entrySet().iterator(); 
                while(iter.hasNext()) {
                    Entry<String, Map<Long, ROrdCartItemResponse>> entry = iter.next();
//                    list.add(entry.getValue());
                    List<ROrdCartItemResponse> mapValuesList = new ArrayList<ROrdCartItemResponse>(((Map<Long,ROrdCartItemResponse>) entry.getValue()).values());
                    groupLists.add(mapValuesList);
                }
                rResultOrdCartResponse.setGroupLists(groupLists);
            }
        }
    }
    
    /**
     * 
     * 删除购物车实例.
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV//delCart(java.lang.Long)
     */
    @Override
    public void deleteCart(Long ordCartId, Long srtaffId) {
        OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
        orderCartCriteria.createCriteria().andStaffIdEqualTo(srtaffId).andIdEqualTo(ordCartId);
        ordCartMapper.deleteByExample(orderCartCriteria);
    }

    /**
     * 
     * 更新购物车实例促销信息.
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV#updateOrdCartProm(ROrdCartItemRequest)
     */
    @Override
    public void updateOrdCartProm(ROrdCartItemRequest info) {
        OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
        orderCartCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId())
                .andIdEqualTo(info.getId());
        /*
         * List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria); if
         * (CollectionUtils.isEmpty(ordCarts)) { OrdCart ordCart = ordCarts.get(0);
         * ordCart.setPromId(info.getPromId()); ordCartMapper.updateByPrimaryKeySelective(ordCart);
         * }
         */
        OrdCart ordCart = new OrdCart();
        ordCart.setPromId(info.getPromId());
        ordCartMapper.updateByExampleSelective(ordCart, orderCartCriteria);
    }

    /**
     * 
     * 购物车去结算生成预订单.
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV//queryPreOrd(com.zengshi.ecp.order.dubbo.dto.ROrdCartsRequest)
     */
    @Override
    public RPreOrdMainsResponse queryPreOrd(RCrePreOrdsRequest info) {

        LogUtil.info(MODULE, "生成预订单入参:" + JSON.toJSONString(info).toString());
        RPreOrdMainsResponse rPreOrdsResponse = new RPreOrdMainsResponse();
        List<RPreOrdMainResponse> preOrdMainList = new ArrayList<RPreOrdMainResponse>();
        List<RCrePreOrdRequest> ordCartList = info.getCarList();
        Map<String,Map<String,Long>> disCountMoneyMaps = new HashMap<String, Map<String,Long>>();
        // 封装公有入参
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        rOrdCartsCommRequest.setPromSubmitType(PromConstants.PromTypeMsg.ORDER_SUBMIT_TYPE);
        rOrdCartsCommRequest.setStaffId(info.getStaffId());
        rOrdCartsCommRequest.setSource(info.getSource());
        List<ROrdCartCommRequest> ordCartsCommList = new ArrayList<ROrdCartCommRequest>();
        // 循环购物车实例，生成预订单主单
        for (RCrePreOrdRequest rCrePreOrdRequest : ordCartList) {
            RPreOrdMainResponse rPreOrdMainResponse = new RPreOrdMainResponse();
            // 初始化是否全部虚拟商品的flag,初始化为1
            String virtualFlag="1";
            // 初始化是否全部都是数字教材/电子书/数字图书馆消费卡的flag,初始化为1
            String platCatgsFlag="1";
            List<RPreOrdSubResponse> preOrdSubList = new ArrayList<RPreOrdSubResponse>();
            /*
             * //预订单总数量 Long orderAmount=0l; //预订单总金额 Long orderMoney=0l;
             */
            Long cartId = rCrePreOrdRequest.getCartId();
            OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
            orderCartCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId())
                    .andIdEqualTo(cartId);
            List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria);
            if (CollectionUtils.isEmpty(ordCarts)) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352001);
            }
            OrdCart ordCart = ordCarts.get(0);
            ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
            ObjectCopyUtil.copyObjValue(ordCart, rOrdCartCommRequest, null, false);
            rOrdCartCommRequest.setSource(info.getSource());
            rOrdCartCommRequest.setCurrentSiteId(info.getCurrentSiteId());
            List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
            rPreOrdMainResponse.setShopId(ordCart.getShopId());
            rPreOrdMainResponse.setShopName(ordCart.getShopName());
            rPreOrdMainResponse.setCartId(rCrePreOrdRequest.getCartId());
            /*
             * rPreOrdMainResponse.setPromId(ordCart.getPromId());
             * rPreOrdMainResponse.setPromName(ordCart.getPromName());
             */

            // 循环购物车实例里的购物车明细，生成预订单明细信息
            for (RCrePreOrdItemRequest rCrePreOrdItemRequest : rCrePreOrdRequest
                    .getCartItemIdList()) {
                RPreOrdSubResponse rPreOrdSubResponse = new RPreOrdSubResponse();
                Long cartItemId = rCrePreOrdItemRequest.getCartItemId();
                OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
                orderCartItemCriteria.createCriteria().andStaffIdEqualTo(info.getStaffId())
                        .andIdEqualTo(cartItemId);
                List<OrdCartItem> ordCartItems = ordCartItemMapper
                        .selectByExample(orderCartItemCriteria);
                if (CollectionUtils.isEmpty(ordCartItems)) {
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
                }
                OrdCartItem ordCartItem = ordCartItems.get(0);
                ObjectCopyUtil.copyObjValue(ordCartItem, rPreOrdSubResponse, null, false);
                String skuInfo="";
                if(!StringUtil.isBlank(rPreOrdSubResponse.getSkuInfo())){
                    skuInfo=rPreOrdSubResponse.getSkuInfo();
                }
                //增加对商品属性展示的优化，加上商品的2级分类
                GdsCategoryReqDTO categoryDTO=new GdsCategoryReqDTO();
                categoryDTO.setCatgCode(ordCartItem.getCategoryCode());
                List<GdsCategoryRespDTO> categoryRespList=gdsCategoryRSV.queryCategoryTraceUpon(categoryDTO);
                if(CollectionUtils.isNotEmpty(categoryRespList)){
                    if(categoryRespList.size()>1){
                        GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                        GdsCategoryRespDTO categoryRes2=categoryRespList.get(1);    
                        //rPreOrdSubResponse.setSkuInfo(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName()+"    "+skuInfo);   
                        rPreOrdSubResponse.setGdsCateName(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName());
                    }else{
                        GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                        //rPreOrdSubResponse.setSkuInfo(categoryRes1.getCatgName()+"    "+skuInfo);  
                        rPreOrdSubResponse.setGdsCateName(categoryRes1.getCatgName());
                    }
                }
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
                
                // 促销域使用
                rOrdCartItemCommRequest.setPromId(rCrePreOrdItemRequest.getPromId());

                // 只要有一个是非虚拟商品，赋值最终虚拟标示为0
                if(ordCartItem.getGdsType() !=2 || LongUtils.isEmpty(ordCartItem.getGdsType())){                   
                    virtualFlag="0";
                }
                GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                gdsSkuInfoReqDTO.setId(ordCartItem.getSkuId());
                GdsSkuInfoRespDTO gdsSkuInfo = gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
                //只要有一个是非充值卡/数字教材/电子书
                if(ordCartItem.getGdsType() != 3 && !StringUtils.contains(gdsSkuInfo.getPlatCatgs(),"<1200>")&& !StringUtils.contains(gdsSkuInfo.getPlatCatgs(),"<1201>")){
                    platCatgsFlag="0";
                } 
                // 补充页面展示的购物车明细信息----调用商品信息的接口，获取商品的图片，URL
                GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
                gdsInfoReqDTO.setId(ordCartItem.getSkuId());
                gdsInfoReqDTO.setGdsId(ordCartItem.getGdsId());
                gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] {
                        GdsOption.SkuQueryOption.BASIC, GdsOption.SkuQueryOption.MAINPIC });
                GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                        .querySkuInfoByOptions(gdsInfoReqDTO);
                if (StringUtil.isEmpty(gdsInfoRespDTO)) {
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
                }
                //优惠券相关
/*                rOrdCartItemCommRequest.setDiscountPrice(gdsInfoRespDTO.getDiscountPrice() * rPreOrdSubResponse.getOrderAmount());
                rOrdCartItemCommRequest.setDiscountMoney(gdsInfoRespDTO.getRealPrice() * rPreOrdSubResponse.getOrderAmount());*/
                ordCartItemCommList.add(rOrdCartItemCommRequest);//这行代码移到下面有没有关系
                //优惠券相关

                if (!StringUtil.isEmpty(gdsInfoRespDTO.getMainPic())) {
                    rPreOrdSubResponse.setPicUrl(gdsInfoRespDTO.getMainPic().getURL());
                    rPreOrdSubResponse.setPicId(gdsInfoRespDTO.getMainPic().getMediaUuid());
                }
                rPreOrdSubResponse.setGdsUrl(gdsInfoRespDTO.getUrl());
                // 库存，调用商品查询库存接口
                StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
                stockInfoForGdsDTO.setShopId(ordCartItem.getShopId());
                stockInfoForGdsDTO.setGdsId(ordCartItem.getGdsId());
                stockInfoForGdsDTO.setSkuId(ordCartItem.getSkuId());
                StockInfoRespDTO stockInfoDTO = gdsStockRSV.queryStockInfoByGds(stockInfoForGdsDTO);
                Long availCount = 0l;
                if (!StringUtil.isEmpty(stockInfoDTO)) {
                    availCount = stockInfoDTO.getAvailCount();
                }
                rPreOrdSubResponse.setStockAvailCount(availCount);
                rPreOrdSubResponse.setCartItemId(rCrePreOrdItemRequest.getCartItemId());
                preOrdSubList.add(rPreOrdSubResponse);
            }
            rOrdCartCommRequest.setOrdCartItemCommList(ordCartItemCommList);

            // 促销域使用
            rOrdCartCommRequest.setPromId(rCrePreOrdRequest.getPromId());
            ordCartsCommList.add(rOrdCartCommRequest);
            rPreOrdMainResponse.setPreOrdSubList(preOrdSubList);
            // 初始化是否全部虚拟商品的flag
            rPreOrdMainResponse.setIsVirtual(virtualFlag);
            //初始化是否全部都是数字教材/电子书/数字图书馆消费卡的flag
            rPreOrdMainResponse.setIsPlatCatgs(platCatgsFlag);
            preOrdMainList.add(rPreOrdMainResponse);
        }
        rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
        rPreOrdsResponse.setStaffId(info.getStaffId());
        rPreOrdsResponse.setPreOrdMainList(preOrdMainList);
        // 根据封装的参数，进行运费和价格的计算
        // Map<Long, Long> shipExpenseMap =
        // gdsShiptemRSV.calcShipExpenseByCarts(rOrdCartsCommRequest);
        Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
        // 进行一次出参的循环，把对应的运费和价格封装进去
        for (RPreOrdMainResponse rReslutPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {
            // Long resultshopId = rReslutPreOrdMainResponse.getShopId();
            // Long shipExpense = shipExpenseMap.get(resultshopId);
            // rReslutPreOrdMainResponse.setRealExpressFee(shipExpense);
            Long orderAmount = 0l;
            Long orderMoney = 0l;
            Long basicMoney = 0l;
            for (RPreOrdSubResponse rResultPreOrdSubResponse : rReslutPreOrdMainResponse
                    .getPreOrdSubList()) {
                Long skuId = rResultPreOrdSubResponse.getSkuId();
                CartItemPriceInfo cartItemPriceInfo = priceMap.get(skuId);
                orderAmount = orderAmount + rResultPreOrdSubResponse.getOrderAmount();
                if (!StringUtil.isEmpty(cartItemPriceInfo)) {
                    rResultPreOrdSubResponse.setBasePrice(cartItemPriceInfo.getBasePrice());
                    rResultPreOrdSubResponse.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                    rResultPreOrdSubResponse.setScore(cartItemPriceInfo.getScore() == null ? 0l
                            : cartItemPriceInfo.getScore());
                    rResultPreOrdSubResponse.setOrderScore(rResultPreOrdSubResponse.getScore()
                            * rResultPreOrdSubResponse.getOrderAmount());
                    rResultPreOrdSubResponse.setOrderMoney(rResultPreOrdSubResponse.getOrderAmount()
                            * cartItemPriceInfo.getBuyPrice());
                    orderMoney = orderMoney + rResultPreOrdSubResponse.getOrderAmount()
                            * cartItemPriceInfo.getBuyPrice();
                    basicMoney = basicMoney + rResultPreOrdSubResponse.getOrderAmount()
                            * cartItemPriceInfo.getBasePrice();
                }

            }
            rReslutPreOrdMainResponse.setOrderAmount(orderAmount);
            rReslutPreOrdMainResponse.setOrderMoney(orderMoney);
            rReslutPreOrdMainResponse.setBasicMoney(basicMoney);
        }

        for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
            
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(info.getStaff().getId());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            shopStaffGroupReqDTO.setShopId(rOrdCartCommRequest.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            rOrdCartCommRequest.setCustGroupValue(custGroupValue);
            rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
            rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
            //web 渠道
            rOrdCartCommRequest.setChannelValue(info.getSource()); 
            
            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                rOrdCartItemCommRequest.setBasePrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBasePrice());
                rOrdCartItemCommRequest.setBuyPrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBuyPrice());
                rOrdCartItemCommRequest.setCurrentSiteId(info.getCurrentSiteId());
            }
        }
        rOrdCartsCommRequest.setCurrentSiteId(info.getCurrentSiteId());
        LogUtil.info(MODULE, "促销入参:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
        CartPromRespDTO cprd = this.promRSV.initCartPromMap(rOrdCartsCommRequest);
        rPreOrdsResponse.setCartPromRespDTO(cprd);
        if (cprd != null) {
            LogUtil.info(MODULE, "促销出参:" + JSON.toJSONString(cprd).toString());
        } else {
            LogUtil.info(MODULE, "---------------------------------NULL");
        }

        Map<Long,Long> standardMoney = null;
        for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
            Long payMoney = 0l;
            // 订单级别优惠
            Map<Long, CartPromDTO> ordMap = null;
            // 子订单级别优惠
            Map<Long, CartPromItemDTO> ordSubMap = null;
            if (rPreOrdsResponse.getCartPromRespDTO() != null) {
                ordMap = rPreOrdsResponse.getCartPromRespDTO().getCartPromDTOMap();
                ordSubMap = rPreOrdsResponse.getCartPromRespDTO().getCartPromItemDTOMap();
            }
            
            Map<String,Long> baseDisCountMoneyMap = new HashMap<String,Long>(); 
            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest
                    .getOrdCartItemCommList()) {
                // 商品金额
                Long baseDiscountPrice = rOrdCartItemCommRequest.getBuyPrice();
                Long baseDiscountMoney = baseDiscountPrice * rOrdCartItemCommRequest.getOrderAmount();
                Long coupDiscountPrice = baseDiscountPrice, coupDiscountMoney = baseDiscountMoney;
               
                

                rOrdCartItemCommRequest.setOrderMoney(rOrdCartItemCommRequest.getBuyPrice()
                        * rOrdCartItemCommRequest.getOrderAmount());
                if (ordSubMap != null) {
                    CartPromItemDTO itemProm = ordSubMap.get(rOrdCartItemCommRequest.getId());

                    if (itemProm != null) {
                        
//                        Long discountMoney = itemProm.getDiscountMoney() == null ? 0l
//                                : itemProm.getDiscountMoney().longValue();
//
//                        Long discountPrice = itemProm.getDiscountPrice() == null ? 0l
//                                : itemProm.getDiscountPrice().longValue();

                        // 优惠金额
//                      payMoney -= (discountMoney + discountPriceMoney);
                        baseDiscountMoney = (itemProm.getDiscountFinalPrice().longValue()*rOrdCartItemCommRequest.getOrderAmount().longValue() -itemProm.getDiscountPriceMoney().longValue());
                        baseDisCountMoneyMap.put(""+rOrdCartItemCommRequest.getSkuId(), baseDiscountMoney);
//                        coupDiscountPrice = baseDiscountPrice - discountPrice;
                        coupDiscountPrice = itemProm.getDiscountFinalPrice().longValue();
                        //优惠券
//                        coupDiscountMoney = coupDiscountPrice * rOrdCartItemCommRequest.getOrderAmount() - discountMoney;
                        coupDiscountMoney = baseDiscountMoney;

//                        Long discountPriceMoney = itemProm.getDiscountPriceMoney() == null ? 0l
//                                : itemProm.getDiscountPriceMoney().longValue();
                    
//                        rOrdCartItemCommRequest
//                                .setOrderMoney(rOrdCartItemCommRequest.getOrderMoney()
//                                        - (discountMoney + discountPriceMoney));
                        rOrdCartItemCommRequest
                                .setOrderMoney(itemProm.getDiscountFinalPrice().longValue()*rOrdCartItemCommRequest.getOrderAmount().longValue());
                        //是否满足促销条件
                        rOrdCartItemCommRequest.setIfFulfillProm(itemProm.isIfFulfillProm());
                    }
                }
                payMoney += baseDiscountMoney;
                //经过促销之后优惠券
                //优惠券
                rOrdCartItemCommRequest.setDiscountMoney(coupDiscountMoney);
                //优惠券
                rOrdCartItemCommRequest.setDiscountPrice(coupDiscountPrice);
            }
            disCountMoneyMaps.put(""+rOrdCartCommRequest.getShopId(), baseDisCountMoneyMap);

            if (ordMap != null) {
                CartPromDTO cartProm = ordMap.get(rOrdCartCommRequest.getId());
                if (cartProm != null) {
                    Long discountMoney = cartProm.getDiscountMoney() == null ? 0l
                            : cartProm.getDiscountMoney().longValue();
                    Long discountPriceMoney = cartProm.getDiscountPriceMoney() == null ? 0l
                            : cartProm.getDiscountPriceMoney().longValue();
                    payMoney -= (discountMoney + discountPriceMoney);
                    //是否满足促销条件
                    rOrdCartCommRequest.setIfFulfillProm(cartProm.isIfFulfillProm());
                }
            }
            rOrdCartCommRequest.setPayMoney(payMoney);

            TransactionContentReqDTO transactionContentReqDTO = new TransactionContentReqDTO();
            transactionContentReqDTO.setStaffId(info.getStaff().getId());
            transactionContentReqDTO.setShopId(rOrdCartCommRequest.getShopId());
            transactionContentReqDTO.setOrderMoney(payMoney);
            LogUtil.info(MODULE,
                    "acct input:" + JSON.toJSONString(transactionContentReqDTO).toString());
            List<AcctInfoResDTO> acctInfoResDTOList = acctManageRSV
                    .queryAcctWithOrderByStaff(transactionContentReqDTO);
            for (RPreOrdMainResponse rReslutPreOrdMainResponse : rPreOrdsResponse
                    .getPreOrdMainList()) {
                if (rReslutPreOrdMainResponse.getShopId().equals(rOrdCartCommRequest.getShopId())) {
                    rReslutPreOrdMainResponse.setOrdAcctInfoList(acctInfoResDTOList);
                    break;
                }
            }

        }
        
        //封装预子订单baseDiscountMoney
        for (RPreOrdMainResponse rReslutPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {          
            for(int i=0;i<rReslutPreOrdMainResponse.getPreOrdSubList().size();i++){
                Long skuId = rReslutPreOrdMainResponse.getPreOrdSubList().get(i).getSkuId();
                Long baseDiscountMoney = disCountMoneyMaps.get(""+rReslutPreOrdMainResponse.getShopId()).get(""+skuId);
                rReslutPreOrdMainResponse.getPreOrdSubList().get(i).setBaseDiscountMoney(baseDiscountMoney);
            }
        }
        
        rOrdCartsCommRequest.setCurrentSiteId(info.getCurrentSiteId());
        LogUtil.info(MODULE, "ship input:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
        // 根据封装的参数，进行运费计算

        Map<Long, Long> shipExpenseMap = gdsShiptemRSV.calcShipExpenseByCarts(rOrdCartsCommRequest);
        for (RPreOrdMainResponse rReslutPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {
            Long shipExpense = shipExpenseMap.get(rReslutPreOrdMainResponse.getShopId());
            rReslutPreOrdMainResponse.setRealExpressFee(shipExpense);
            
            
            //基准价格改造---start
            for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
                if(rReslutPreOrdMainResponse.getShopId().equals(rOrdCartCommRequest.getShopId())){
                    Long standardMonery = 0l;
                    // 子订单级别优惠
                    Map<Long, CartPromItemDTO> ordSubMap = null;
                    if (rPreOrdsResponse.getCartPromRespDTO() != null) {
                        ordSubMap = rPreOrdsResponse.getCartPromRespDTO().getCartPromItemDTOMap();
                    }
                    for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                        if (ordSubMap != null) {
                            CartPromItemDTO itemProm = ordSubMap.get(rOrdCartItemCommRequest.getId());
                            if (itemProm != null) {
                                Long standMoney = itemProm.getDiscountCaclPrice() == null ? 0l : itemProm.getDiscountCaclPrice().longValue();
                                standardMonery += standMoney * rOrdCartItemCommRequest.getOrderAmount();
                            } else {
                                //默认商品基础价格
                                standardMonery += rOrdCartItemCommRequest.getBasePrice()  * rOrdCartItemCommRequest.getOrderAmount();
                            }
                        } else {
                            //默认商品基础价格
                            standardMonery += rOrdCartItemCommRequest.getBasePrice() * rOrdCartItemCommRequest.getOrderAmount();
                        }
                    }
                    rReslutPreOrdMainResponse.setOrderMoney(standardMonery);
                    break;
                } else {
                    continue;
                }
                
            }
            //基准价格改造---end
        }

        //处理组合商品
        delGroupCreatePre(rPreOrdsResponse);

        //优惠券信息
        CoupOrdCheckRespDTO coupOrdCheckRespDTO = iCoupDetailRSV.queryOrdCheckCoup(rOrdCartsCommRequest);
        rPreOrdsResponse.setCoupOrdCheckRespDTO(coupOrdCheckRespDTO);
        if(StringUtil.isNotEmpty(coupOrdCheckRespDTO)&&StringUtil.isNotEmpty(coupOrdCheckRespDTO.getCoupOrdSkuMap())) {
            for (RPreOrdMainResponse rPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {
                rPreOrdMainResponse.setCoupCheckBeanRespDTOs(coupOrdCheckRespDTO.getCoupOrdSkuMap().get(rPreOrdMainResponse.getCartId()));
            }
        }
        LogUtil.info(MODULE, "========优惠券"+JSON.toJSONString(coupOrdCheckRespDTO)+"===========");
        
        

        LogUtil.info(MODULE, "生成预订单出参:" + JSON.toJSONString(rPreOrdsResponse).toString());
        return rPreOrdsResponse;
    }
    
    /** 
     * delGroupCreatePre:组合商品处理. <br/> 
     * @author cbl 
     * @param rPreOrdsResponse 
     * @since JDK 1.6 
     */ 
    private void delGroupCreatePre(RPreOrdMainsResponse rPreOrdsResponse){
        for (RPreOrdMainResponse rPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {
            
            Map<String, Map<Long, RPreOrdSubResponse>> cartMap = null;
            List<List<RPreOrdSubResponse>> groupLists = null;
            List<RPreOrdSubResponse> ros = new ArrayList<RPreOrdSubResponse>();
            for(RPreOrdSubResponse rPreOrdSubResponse:rPreOrdMainResponse.getPreOrdSubList()){
                if(rPreOrdSubResponse.getGroupType().equals("0")){
                    continue;
                } else if(rPreOrdSubResponse.getGroupType().equals("1")) {
                    if(cartMap == null){
                        cartMap = new HashMap<String, Map<Long, RPreOrdSubResponse>>();
                    }
                    if(cartMap.get(rPreOrdSubResponse.getGroupDetail()) == null){
                        Map<Long, RPreOrdSubResponse> cartItemMap = new HashMap<Long, RPreOrdSubResponse>();
                        cartMap.put(rPreOrdSubResponse.getGroupDetail(), cartItemMap);
                    }
                    
                    cartMap.get(rPreOrdSubResponse.getGroupDetail()).put(rPreOrdSubResponse.getCartItemId(), rPreOrdSubResponse);
                    ros.add(rPreOrdSubResponse);
                } else {
                    
                }
                
                
            }
            for(RPreOrdSubResponse rPreOrdSubResponse:ros){
                rPreOrdMainResponse.getPreOrdSubList().remove(rPreOrdSubResponse);
            }
            
            if(cartMap != null){
                
                groupLists = new ArrayList<List<RPreOrdSubResponse>>();
                Iterator<Entry<String, Map<Long, RPreOrdSubResponse>>> iter = cartMap.entrySet().iterator(); 
                while(iter.hasNext()) {
                    Entry<String, Map<Long, RPreOrdSubResponse>> entry = iter.next();
                    List<RPreOrdSubResponse> mapValuesList = new ArrayList<RPreOrdSubResponse>(((Map<Long,RPreOrdSubResponse>) entry.getValue()).values());
                    groupLists.add(mapValuesList);
                }
                rPreOrdMainResponse.setGroupLists(groupLists);
            }
        }
    }

    @Override
    public Map<Long, Long> calcShip(ROrdCartsCommRequest rOrdCartsCommRequest) {
        Map<Long, Long> shipMap = null;
        try {
            shipMap = gdsShiptemRSV.calcShipExpenseByCarts(rOrdCartsCommRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shipMap;
    }

    @Override
    public ROrdCartChgResponse queryAssembleParamForProm(ROrdCartChgRequest rOrdCartChgRequest) {
        LogUtil.info(MODULE,
                "========input===========" + JSON.toJSONString(rOrdCartChgRequest).toString());

        ROrdCartChgResponse roc = new ROrdCartChgResponse();

        ROrdCartChangeRequest rOrdCartChangeRequest = rOrdCartChgRequest.getrOrdCartChangeRequest();
        if (rOrdCartChangeRequest == null) {
            LogUtil.info(MODULE, "========组装促销域入参为空===========");
            return null;
        }
        // 补齐购物信息
        OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
        orderCartCriteria.createCriteria()
                .andStaffIdEqualTo(rOrdCartChangeRequest.getStaff().getId())
                .andIdEqualTo(rOrdCartChangeRequest.getrOrdCartCommRequest().getId());
        List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria);
        if (CollectionUtils.isEmpty(ordCarts)) {
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352001);
        }
        OrdCart ordCart = ordCarts.get(0);
        ObjectCopyUtil.copyObjValue(ordCart, rOrdCartChangeRequest.getrOrdCartCommRequest(), null,
                false);

        
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        custInfoReqDTO.setId(rOrdCartChgRequest.getStaff().getId());
        CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
        ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
        shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
        shopStaffGroupReqDTO.setShopId(rOrdCartChangeRequest.getrOrdCartCommRequest().getShopId());
        // 客户组id
        String custGroupValue = null;
        if (custInfoResDTO != null && custInfoResDTO.getId() != null
                && custInfoResDTO.getId() != 0) {
            custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
        }
        // 客户基本信息
        rOrdCartChangeRequest.getrOrdCartCommRequest().setCustGroupValue(custGroupValue);
        rOrdCartChangeRequest.getrOrdCartCommRequest().setCustLevelValue(custInfoResDTO.getCustLevelCode());
        rOrdCartChangeRequest.getrOrdCartCommRequest().setAreaValue(custInfoResDTO.getProvinceCode());
        //web  渠道
        rOrdCartChangeRequest.getrOrdCartCommRequest().setChannelValue(rOrdCartChgRequest.getSource()); 
        
        
        // 补齐购物车明细信息
        for (ROrdCartItemCommRequest rOrdCartItemCommRequest:rOrdCartChangeRequest.getrOrdCartCommRequest().getOrdCartItemCommList()) {
            OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
            orderCartItemCriteria.createCriteria()
                    .andStaffIdEqualTo(rOrdCartChangeRequest.getStaff().getId())
                    .andIdEqualTo(rOrdCartItemCommRequest.getId());

            List<OrdCartItem> ordCartItems = ordCartItemMapper
                    .selectByExample(orderCartItemCriteria);
            if (CollectionUtils.isEmpty(ordCartItems)) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
            }
            OrdCartItem ordCartItem = ordCartItems.get(0);
            ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
            
            

        }

        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        rOrdCartsCommRequest.setStaffId(rOrdCartChangeRequest.getStaff().getId());
        rOrdCartsCommRequest.setSource(rOrdCartChgRequest.getSource());
        rOrdCartsCommRequest.setCountryCode(rOrdCartChgRequest.getCountryCode());
        rOrdCartsCommRequest.setProvinceCode(rOrdCartChgRequest.getProvinceCode());
        rOrdCartsCommRequest.setCityCode(rOrdCartChgRequest.getCityCode());

        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
        rOrdCartCommRequests.add(rOrdCartChangeRequest.getrOrdCartCommRequest());
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        // 明细价格重新计算
        Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
        for (ROrdCartCommRequest rOrdCartCommRequest :rOrdCartsCommRequest.getOrdCartsCommList()) {
            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                CartItemPriceInfo cartItemPriceInfo = priceMap.get(rOrdCartItemCommRequest.getSkuId());
                if (cartItemPriceInfo != null) {
                    rOrdCartItemCommRequest.setBasePrice(cartItemPriceInfo.getBasePrice());
                    rOrdCartItemCommRequest.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                    rOrdCartItemCommRequest.setScore(cartItemPriceInfo.getScore() == null ? 0l : cartItemPriceInfo.getScore());
                    rOrdCartItemCommRequest.setOrderSubScore(rOrdCartItemCommRequest.getOrderAmount() * rOrdCartItemCommRequest.getScore());
                } else {
                    LogUtil.info(MODULE, "未找到[" + rOrdCartItemCommRequest.getSkuId() + "]该商品的信息");
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
                }
                
            }
        }

        // 补齐变更的购物车明细信息
        if (rOrdCartChangeRequest.getrOrdCartItemCommRequest() != null) {
            for (int i = 0; i < rOrdCartChangeRequest.getrOrdCartCommRequest()
                    .getOrdCartItemCommList().size(); i++) {
                ROrdCartItemCommRequest rOrdCartItemCommRequest = rOrdCartChangeRequest
                        .getrOrdCartCommRequest().getOrdCartItemCommList().get(i);
                if (rOrdCartChangeRequest.getrOrdCartItemCommRequest().getId()
                        .equals(rOrdCartItemCommRequest.getId())) {
                    rOrdCartChangeRequest.setrOrdCartItemCommRequest(rOrdCartItemCommRequest);
                }
            }
        }
        roc.setrOrdCartChangeRequest(rOrdCartChangeRequest);
        LogUtil.info(MODULE, "========out===========" + JSON.toJSONString(roc).toString());
        return roc;
    }

    /**
     *
     * 立即购买校验参数的方法.
     *
     * @throws
     *
     * @see com.zengshi.ecp.order.service.interfaces.IOrdCartSV#fastOrdCart(FastOrdRequest fastOrdRequest)
     */
    @Override
    public RShowOrdCartsResponse fastOrdCart(FastOrdRequest fastOrdRequest) {
        // 1 从购物车主表中获取登录者的购物车主表信息

        OrdCart ordCart = new OrdCart();
        ObjectCopyUtil.copyObjValue(fastOrdRequest,ordCart,"",false);
        // 获取买家的默认收货地址
        // 展示的购物车实例列表
        List<ROrdCartResponse> rShowOrdCartList = new ArrayList<ROrdCartResponse>();
        // 返回的出参，给前店的
        RShowOrdCartsResponse rShowOrdCartsResponse = new RShowOrdCartsResponse();

        List<ROrdCartItemCommRequest> isNoGdsStatus = new ArrayList<ROrdCartItemCommRequest>();

        // 2 循环购物车主表，查询出购物车明细列表信息
        // 封装公有入参
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        rOrdCartsCommRequest.setPromSubmitType(PromConstants.PromTypeMsg.CART_SUBMIT_TYPE);
        rOrdCartsCommRequest.setStaffId(fastOrdRequest.getStaff().getId());
        List<ROrdCartCommRequest> ordCartsCommList = new ArrayList<ROrdCartCommRequest>();

        // 展示的每个购物车实例下的明细列表
        List<ROrdCartItemResponse> rShowOrdCartItemList = new ArrayList<ROrdCartItemResponse>();
        // 展示的购物车实例
        ROrdCartResponse rShowOrdCartResponse = new ROrdCartResponse();
        ObjectCopyUtil.copyObjValue(ordCart, rShowOrdCartResponse, null, false);
        ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
        ObjectCopyUtil.copyObjValue(ordCart, rOrdCartCommRequest, null, false);
        List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
        List<OrdCartItem> ordCartItems = new ArrayList<>();
        if(CollectionUtils.isEmpty(fastOrdRequest.getOrdCartItemList())){
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
        }else{

            for(ROrdCartItemRequest rOrdCartItemRequest: fastOrdRequest.getOrdCartItemList()) {
                OrdCartItem ordCartItem = new OrdCartItem();
                ObjectCopyUtil.copyObjValue(rOrdCartItemRequest,ordCartItem,"",false);
                ordCartItems.add(ordCartItem);
            }
        }

        // 循环购物车明细列表，封装上价格等给页面展示的属性
        for (OrdCartItem ordCartItem : ordCartItems) {
            ROrdCartItemResponse rShowOrdCartItemResponse = new ROrdCartItemResponse();
            ObjectCopyUtil.copyObjValue(ordCartItem, rShowOrdCartItemResponse, null, false);
            String skuInfo="";
            if(!StringUtil.isBlank(rShowOrdCartItemResponse.getSkuInfo())){
                skuInfo=rShowOrdCartItemResponse.getSkuInfo();
            }
            //增加对商品属性展示的优化，加上商品的2级分类
            GdsCategoryReqDTO categoryDTO=new GdsCategoryReqDTO();
            categoryDTO.setCatgCode(ordCartItem.getCategoryCode());
            List<GdsCategoryRespDTO> categoryRespList=gdsCategoryRSV.queryCategoryTraceUpon(categoryDTO);
            if(CollectionUtils.isNotEmpty(categoryRespList)){
                if(categoryRespList.size()>1){
                    GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                    GdsCategoryRespDTO categoryRes2=categoryRespList.get(1);
                    //rShowOrdCartItemResponse.setSkuInfo(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName()+"    "+skuInfo);
                    rShowOrdCartItemResponse.setGdsCateName(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName());
                }else{
                    GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                    //rShowOrdCartItemResponse.setSkuInfo(categoryRes1.getCatgName()+"    "+skuInfo);
                    rShowOrdCartItemResponse.setGdsCateName(categoryRes1.getCatgName());
                }
            }
            ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
            ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
            ordCartItemCommList.add(rOrdCartItemCommRequest);

            // 补充页面展示的购物车明细信息----调用商品信息的接口，获取商品的图片，URL
            GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
            gdsInfoReqDTO.setId(ordCartItem.getSkuId());
            gdsInfoReqDTO.setGdsId(ordCartItem.getGdsId());
            gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] {
                    GdsOption.SkuQueryOption.BASIC, GdsOption.SkuQueryOption.MAINPIC });
            GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                    .querySkuInfoByOptions(gdsInfoReqDTO);
            if (StringUtil.isEmpty(gdsInfoRespDTO)) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
            }
            rShowOrdCartItemResponse.setGdsStatus(GdsUtils.isOnShelves(gdsInfoRespDTO.getGdsStatus()));



            if (!StringUtil.isEmpty(gdsInfoRespDTO.getMainPic())) {
                rShowOrdCartItemResponse.setPicUrl(gdsInfoRespDTO.getMainPic().getURL());
                rShowOrdCartItemResponse.setPicId(gdsInfoRespDTO.getMainPic().getMediaUuid());
            }
            rShowOrdCartItemResponse.setGdsUrl(gdsInfoRespDTO.getUrl());
            // 库存，调用商品查询库存接口
            StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
            stockInfoForGdsDTO.setShopId(ordCartItem.getShopId());
            stockInfoForGdsDTO.setGdsId(ordCartItem.getGdsId());
            stockInfoForGdsDTO.setSkuId(ordCartItem.getSkuId());
            stockInfoForGdsDTO.setTypeId(ordCartItem.getGdsType());
            StockInfoRespDTO stockInfoDTO = gdsInfoExternalRSV
                    .getStockAmount(stockInfoForGdsDTO);

            Long availCount = 0l;
            if (!StringUtil.isEmpty(stockInfoDTO)) {
                availCount = stockInfoDTO.getAvailCount();
            }
            rShowOrdCartItemResponse.setStockAvailCount(availCount);
            rShowOrdCartItemList.add(rShowOrdCartItemResponse);
            //如果是下架商品不调促销域
            if(!rShowOrdCartItemResponse.isGdsStatus()){
                isNoGdsStatus.add(rOrdCartItemCommRequest);
            }

        }
        rOrdCartCommRequest.setOrdCartItemCommList(ordCartItemCommList);

        ordCartsCommList.add(rOrdCartCommRequest);
        rShowOrdCartResponse.setOrdCartItemList(rShowOrdCartItemList);
        rShowOrdCartList.add(rShowOrdCartResponse);
        rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
        rShowOrdCartsResponse.setStaffId(fastOrdRequest.getStaff().getId());
        rShowOrdCartsResponse.setOrdCartList(rShowOrdCartList);
        rOrdCartsCommRequest.setSource("1");

        // 根据封装的参数，进行运费和价格的计算
        Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
        // 进行一次出参的循环，把对应的运费和价格封装进去
        for (ROrdCartResponse rResultOrdCartResponse : rShowOrdCartsResponse.getOrdCartList()) {
            for (ROrdCartItemResponse rShowOrdCartItemResponse : rResultOrdCartResponse
                    .getOrdCartItemList()) {
                Long skuId = rShowOrdCartItemResponse.getSkuId();
                CartItemPriceInfo cartItemPriceInfo = priceMap.get(skuId);
                if (!StringUtil.isEmpty(cartItemPriceInfo)) {
                    rShowOrdCartItemResponse.setBasePrice(cartItemPriceInfo.getBasePrice());
                    rShowOrdCartItemResponse.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                    rShowOrdCartItemResponse.setScore(cartItemPriceInfo.getScore() == null ? 0l
                            : cartItemPriceInfo.getScore());
                }
            }
        }
        if(CollectionUtils.isNotEmpty(isNoGdsStatus)){
            for(ROrdCartItemCommRequest rOrdCartItemCommRequest: isNoGdsStatus){
                for (ROrdCartCommRequest commRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
                    commRequest.getOrdCartItemCommList().remove(rOrdCartItemCommRequest);
                }
            }
            List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
            for(ROrdCartCommRequest commRequest : rOrdCartsCommRequest.getOrdCartsCommList()){
                if(CollectionUtils.isEmpty(commRequest.getOrdCartItemCommList())){
                    rOrdCartCommRequests.add(commRequest);
                }
            }
            for(ROrdCartCommRequest commRequest:rOrdCartCommRequests){
                rOrdCartsCommRequest.getOrdCartsCommList().remove(commRequest);
            }
        }

        // 封装促销相关信息
        for (ROrdCartCommRequest commRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {

            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(fastOrdRequest.getStaff().getId());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            shopStaffGroupReqDTO.setShopId(commRequest.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            commRequest.setCustGroupValue(custGroupValue);
            commRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
            commRequest.setAreaValue(custInfoResDTO.getProvinceCode());
            //web  渠道
            commRequest.setChannelValue("1");

            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : commRequest.getOrdCartItemCommList()) {
                rOrdCartItemCommRequest.setBasePrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBasePrice());
                rOrdCartItemCommRequest.setBuyPrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBuyPrice());
                rOrdCartItemCommRequest.setCurrentSiteId(fastOrdRequest.getCurrentSiteId());
            }
        }
        LogUtil.info(MODULE, "input:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
        rOrdCartsCommRequest.setCurrentSiteId(fastOrdRequest.getCurrentSiteId());
        LogUtil.info(MODULE, "促销入参:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
        if(CollectionUtils.isNotEmpty(rOrdCartsCommRequest.getOrdCartsCommList())){
            CartPromRespDTO cprd = this.promRSV.initCartPromMap(rOrdCartsCommRequest);
            rShowOrdCartsResponse.setCartPromRespDTO(cprd);
            if (cprd != null) {
                LogUtil.info(MODULE, "out:" + JSON.toJSONString(cprd).toString());
                LogUtil.info(MODULE, "促销出参:" + JSON.toJSONString(cprd).toString());
            } else {
                LogUtil.info(MODULE, "---------------------------------NULL");
            }
        }

        //处理组合商品
        if(CollectionUtils.isNotEmpty(rShowOrdCartsResponse.getOrdCartList()))
            delGroupQueryCart(rShowOrdCartsResponse);
        LogUtil.info(MODULE, "展示购物车:" + JSON.toJSONString(rShowOrdCartsResponse).toString());
        return rShowOrdCartsResponse;
    }


    @Override
    public ROrdCartsCommRequest queryFastAssembleParamForAll(FastPreOrdRequest fastPreOrdRequest) {
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();

        /* 从数据库表和入参补齐信息--start */
        for (FastOrdRequest fastOrdRequest : fastPreOrdRequest.getCarList()) {
            ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();

            // 补齐购物车信息
            ObjectCopyUtil.copyObjValue(fastOrdRequest, rOrdCartCommRequest, null, false);
            rOrdCartCommRequest.setPromId(fastOrdRequest.getPromId());

            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(fastOrdRequest.getStaff().getId());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            shopStaffGroupReqDTO.setShopId(fastOrdRequest.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            rOrdCartCommRequest.setCustGroupValue(custGroupValue);
            rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
            rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
            //web  渠道
            rOrdCartCommRequest.setChannelValue(fastOrdRequest.getSource());

            // 补齐购物车明细信息
            List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
            if (CollectionUtils.isEmpty(fastOrdRequest.getOrdCartItemList())) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
            }
            for (ROrdCartItemRequest rOrdCartItemRequest : fastOrdRequest
                    .getOrdCartItemList()) {
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                rOrdCartItemCommRequest.setId(rOrdCartItemRequest.getId());

                ObjectCopyUtil.copyObjValue(rOrdCartItemRequest, rOrdCartItemCommRequest, null, false);
                rOrdCartItemCommRequest.setPromId(rOrdCartItemRequest.getPromId() == null ? -1 : rOrdCartItemRequest.getPromId());

                rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
            }
            // 每个店铺的明细列表
            rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);

            // 每个购物车信息
            rOrdCartCommRequests.add(rOrdCartCommRequest);
        }
        // 该买家的购物车信息列表
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        rOrdCartsCommRequest.setStaffId(fastPreOrdRequest.getStaff().getId());
        /* 从数据库表和入参补齐信息--end */

        /* 从各个域返回数据补齐信息--start */
        // 明细价格获取商品域
        Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
        for (ROrdCartCommRequest rOrdCartCommRequest :rOrdCartsCommRequest.getOrdCartsCommList()) {
            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                CartItemPriceInfo cartItemPriceInfo = priceMap.get(rOrdCartItemCommRequest.getSkuId());
                if (cartItemPriceInfo != null) {
                    rOrdCartItemCommRequest.setBasePrice(cartItemPriceInfo.getBasePrice());
                    rOrdCartItemCommRequest.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                    rOrdCartItemCommRequest.setScore(cartItemPriceInfo.getScore() == null ? 0l : cartItemPriceInfo.getScore());
                    rOrdCartItemCommRequest.setOrderSubScore(rOrdCartItemCommRequest.getOrderAmount() * rOrdCartItemCommRequest.getScore());
                } else {
                    LogUtil.info(MODULE, "未找到[" + rOrdCartItemCommRequest.getSkuId() + "]该商品的信息");
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
                }

            }
        }
        /* 从各个域返回数据补齐信息--end */
        return rOrdCartsCommRequest;

    }

    @Override
    public RPreOrdMainsResponse queryFastPreOrd(FastPreOrdRequest info) {

        LogUtil.info(MODULE, "生成预订单入参:" + JSON.toJSONString(info).toString());
        RPreOrdMainsResponse rPreOrdsResponse = new RPreOrdMainsResponse();
        List<RPreOrdMainResponse> preOrdMainList = new ArrayList<RPreOrdMainResponse>();

        // 封装公有入参
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        rOrdCartsCommRequest.setPromSubmitType(PromConstants.PromTypeMsg.ORDER_SUBMIT_TYPE);
        rOrdCartsCommRequest.setStaffId(info.getStaffId());
        // 优惠券使用 来源
        rOrdCartsCommRequest.setSource(info.getSource());
        List<ROrdCartCommRequest> ordCartsCommList = new ArrayList<ROrdCartCommRequest>();
        // 循环购物车实例，生成预订单主单
        if (CollectionUtils.isEmpty(info.getCarList())) {
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352001);
        }
        for (FastOrdRequest fastOrdRequest : info.getCarList()) {
            // 初始化是否全部虚拟商品的flag,初始化为1
            String virtualFlag="1";

            RPreOrdMainResponse rPreOrdMainResponse = new RPreOrdMainResponse();
            List<RPreOrdSubResponse> preOrdSubList = new ArrayList<RPreOrdSubResponse>();
            /*
             * //预订单总数量 Long orderAmount=0l; //预订单总金额 Long orderMoney=0l;
             */
            ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
            ObjectCopyUtil.copyObjValue(fastOrdRequest, rOrdCartCommRequest, null, false);
            rOrdCartCommRequest.setCurrentSiteId(info.getCurrentSiteId());
            List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
            rPreOrdMainResponse.setShopId(fastOrdRequest.getShopId());
            rPreOrdMainResponse.setShopName(fastOrdRequest.getShopName());
            /*
             * rPreOrdMainResponse.setPromId(ordCart.getPromId());
             * rPreOrdMainResponse.setPromName(ordCart.getPromName());
             */

            // 循环购物车实例里的购物车明细，生成预订单明细信息
            for (ROrdCartItemRequest rOrdCartItemRequest : fastOrdRequest
                    .getOrdCartItemList()) {
                RPreOrdSubResponse rPreOrdSubResponse = new RPreOrdSubResponse();
                ObjectCopyUtil.copyObjValue(rOrdCartItemRequest, rPreOrdSubResponse, null, false);
                String skuInfo="";
                if(!StringUtil.isBlank(rPreOrdSubResponse.getSkuInfo())){
                    skuInfo=rPreOrdSubResponse.getSkuInfo();
                }
                rPreOrdSubResponse.setBaseDiscountMoney(rOrdCartItemRequest.getDiscountMoney());
                //增加对商品属性展示的优化，加上商品的2级分类
                GdsCategoryReqDTO categoryDTO=new GdsCategoryReqDTO();
                categoryDTO.setCatgCode(rOrdCartItemRequest.getCategoryCode());
                List<GdsCategoryRespDTO> categoryRespList=gdsCategoryRSV.queryCategoryTraceUpon(categoryDTO);
                if(CollectionUtils.isNotEmpty(categoryRespList)){
                    if(categoryRespList.size()>1){
                        GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                        GdsCategoryRespDTO categoryRes2=categoryRespList.get(1);
                        //rPreOrdSubResponse.setSkuInfo(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName()+"    "+skuInfo);
                        rPreOrdSubResponse.setGdsCateName(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName());
                    }else{
                        GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                        //rPreOrdSubResponse.setSkuInfo(categoryRes1.getCatgName()+"    "+skuInfo);
                        rPreOrdSubResponse.setGdsCateName(categoryRes1.getCatgName());
                    }
                }
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                ObjectCopyUtil.copyObjValue(rOrdCartItemRequest, rOrdCartItemCommRequest, null, false);

                // 促销域使用
                rOrdCartItemCommRequest.setPromId(rOrdCartItemRequest.getPromId());

                // 只要有一个是非虚拟商品，赋值最终虚拟标示为0
                if(rOrdCartItemRequest.getGdsType() !=2 || LongUtils.isEmpty(rOrdCartItemRequest.getGdsType())){
                    virtualFlag="0";
                }


                // 补充页面展示的购物车明细信息----调用商品信息的接口，获取商品的图片，URL
                GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
                gdsInfoReqDTO.setId(rOrdCartItemRequest.getSkuId());
                gdsInfoReqDTO.setGdsId(rOrdCartItemRequest.getGdsId());
                gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] {
                        GdsOption.SkuQueryOption.BASIC, GdsOption.SkuQueryOption.MAINPIC, GdsOption.SkuQueryOption.CAlDISCOUNT });
                GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                        .querySkuInfoByOptions(gdsInfoReqDTO);
                if (StringUtil.isEmpty(gdsInfoRespDTO)) {
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
                }
                //优惠券相关
                ordCartItemCommList.add(rOrdCartItemCommRequest);//这行代码移到下面有没有关系
                //优惠券相关

                if (!StringUtil.isEmpty(gdsInfoRespDTO.getMainPic())) {
                    rPreOrdSubResponse.setPicUrl(gdsInfoRespDTO.getMainPic().getURL());
                    rPreOrdSubResponse.setPicId(gdsInfoRespDTO.getMainPic().getMediaUuid());
                }
                rPreOrdSubResponse.setGdsUrl(gdsInfoRespDTO.getUrl());
                // 库存，调用商品查询库存接口
                StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
                stockInfoForGdsDTO.setShopId(rOrdCartItemRequest.getShopId());
                stockInfoForGdsDTO.setGdsId(rOrdCartItemRequest.getGdsId());
                stockInfoForGdsDTO.setSkuId(rOrdCartItemRequest.getSkuId());
                StockInfoRespDTO stockInfoDTO = gdsStockRSV.queryStockInfoByGds(stockInfoForGdsDTO);
                Long availCount = 0l;
                if (!StringUtil.isEmpty(stockInfoDTO)) {
                    availCount = stockInfoDTO.getAvailCount();
                }
                rPreOrdSubResponse.setStockAvailCount(availCount);
                preOrdSubList.add(rPreOrdSubResponse);
            }
            rOrdCartCommRequest.setOrdCartItemCommList(ordCartItemCommList);

            // 促销域使用
            rOrdCartCommRequest.setPromId(fastOrdRequest.getPromId());
            ordCartsCommList.add(rOrdCartCommRequest);
            rPreOrdMainResponse.setPreOrdSubList(preOrdSubList);
            // 初始化是否全部虚拟商品的flag
            rPreOrdMainResponse.setIsVirtual(virtualFlag);

            preOrdMainList.add(rPreOrdMainResponse);
        }
        rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
        rPreOrdsResponse.setStaffId(info.getStaffId());
        rPreOrdsResponse.setPreOrdMainList(preOrdMainList);
        // 根据封装的参数，进行运费和价格的计算
        // Map<Long, Long> shipExpenseMap =
        // gdsShiptemRSV.calcShipExpenseByCarts(rOrdCartsCommRequest);
        Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
        // 进行一次出参的循环，把对应的运费和价格封装进去
        for (RPreOrdMainResponse rReslutPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {
            // Long resultshopId = rReslutPreOrdMainResponse.getShopId();
            // Long shipExpense = shipExpenseMap.get(resultshopId);
            // rReslutPreOrdMainResponse.setRealExpressFee(shipExpense);
            Long orderAmount = 0l;
            Long orderMoney = 0l;
            Long basicMoney = 0l;
            for (RPreOrdSubResponse rResultPreOrdSubResponse : rReslutPreOrdMainResponse
                    .getPreOrdSubList()) {
                Long skuId = rResultPreOrdSubResponse.getSkuId();
                CartItemPriceInfo cartItemPriceInfo = priceMap.get(skuId);
                orderAmount = orderAmount + rResultPreOrdSubResponse.getOrderAmount();
                if (!StringUtil.isEmpty(cartItemPriceInfo)) {
                    rResultPreOrdSubResponse.setBasePrice(cartItemPriceInfo.getBasePrice());
                    rResultPreOrdSubResponse.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                    rResultPreOrdSubResponse.setScore(cartItemPriceInfo.getScore() == null ? 0l
                            : cartItemPriceInfo.getScore());
                    rResultPreOrdSubResponse.setOrderScore(rResultPreOrdSubResponse.getScore()
                            * rResultPreOrdSubResponse.getOrderAmount());
                    rResultPreOrdSubResponse.setOrderMoney(rResultPreOrdSubResponse.getOrderAmount()
                            * cartItemPriceInfo.getBuyPrice());
                    orderMoney = orderMoney + rResultPreOrdSubResponse.getOrderAmount()
                            * cartItemPriceInfo.getBuyPrice();
                    basicMoney = basicMoney + rResultPreOrdSubResponse.getOrderAmount()
                            * cartItemPriceInfo.getBasePrice();
                }

            }
            rReslutPreOrdMainResponse.setOrderAmount(orderAmount);
            rReslutPreOrdMainResponse.setOrderMoney(orderMoney);
            rReslutPreOrdMainResponse.setBasicMoney(basicMoney);
        }

        for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {

            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(info.getStaff().getId());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            shopStaffGroupReqDTO.setShopId(rOrdCartCommRequest.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            rOrdCartCommRequest.setCustGroupValue(custGroupValue);
            rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
            rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
            //web 渠道
            rOrdCartCommRequest.setChannelValue(info.getSource());

            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                rOrdCartItemCommRequest.setBasePrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBasePrice());
                rOrdCartItemCommRequest.setBuyPrice(priceMap.get(rOrdCartItemCommRequest.getSkuId()).getBuyPrice());
                rOrdCartItemCommRequest.setCurrentSiteId(info.getCurrentSiteId());
            }
        }
        rOrdCartsCommRequest.setCurrentSiteId(info.getCurrentSiteId());
        LogUtil.info(MODULE, "促销入参:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
        CartPromRespDTO cprd = this.promRSV.initCartPromMap(rOrdCartsCommRequest);
        rPreOrdsResponse.setCartPromRespDTO(cprd);
        if (cprd != null) {
            LogUtil.info(MODULE, "促销出参:" + JSON.toJSONString(cprd).toString());
        } else {
            LogUtil.info(MODULE, "---------------------------------NULL");
        }

        Map<Long,Long> standardMoney = null;
        for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
            Long payMoney = 0l;
            // 订单级别优惠
            Map<Long, CartPromDTO> ordMap = null;
            // 子订单级别优惠
            Map<Long, CartPromItemDTO> ordSubMap = null;
            if (rPreOrdsResponse.getCartPromRespDTO() != null) {
                ordMap = rPreOrdsResponse.getCartPromRespDTO().getCartPromDTOMap();
                ordSubMap = rPreOrdsResponse.getCartPromRespDTO().getCartPromItemDTOMap();
            }
            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest
                    .getOrdCartItemCommList()) {
                // 商品金额
                Long baseDiscountPrice = rOrdCartItemCommRequest.getBuyPrice();
                Long baseDiscountMoney = baseDiscountPrice * rOrdCartItemCommRequest.getOrderAmount();
                Long coupDiscountPrice = baseDiscountPrice, coupDiscountMoney = baseDiscountMoney;



                rOrdCartItemCommRequest.setOrderMoney(rOrdCartItemCommRequest.getBuyPrice()
                        * rOrdCartItemCommRequest.getOrderAmount());
                if (ordSubMap != null) {
                    CartPromItemDTO itemProm = ordSubMap.get(rOrdCartItemCommRequest.getId());

                    if (itemProm != null) {

                        // 优惠金额
                        baseDiscountMoney = (itemProm.getDiscountFinalPrice().longValue()*rOrdCartItemCommRequest.getOrderAmount().longValue() -itemProm.getDiscountPriceMoney().longValue());

                        coupDiscountPrice = itemProm.getDiscountFinalPrice().longValue();
                        //优惠券
                        coupDiscountMoney = baseDiscountMoney;

                        rOrdCartItemCommRequest
                                .setOrderMoney(itemProm.getDiscountFinalPrice().longValue()*rOrdCartItemCommRequest.getOrderAmount().longValue());
                    }
                }
                payMoney += baseDiscountMoney;
                //经过促销之后优惠券
                //优惠券
                rOrdCartItemCommRequest.setDiscountMoney(coupDiscountMoney);
                //优惠券
                rOrdCartItemCommRequest.setDiscountPrice(coupDiscountPrice);
            }


            if (ordMap != null) {
                CartPromDTO cartProm = ordMap.get(rOrdCartCommRequest.getId());
                if (cartProm != null) {
                    Long discountMoney = cartProm.getDiscountMoney() == null ? 0l
                            : cartProm.getDiscountMoney().longValue();
                    Long discountPriceMoney = cartProm.getDiscountPriceMoney() == null ? 0l
                            : cartProm.getDiscountPriceMoney().longValue();
                    payMoney -= (discountMoney + discountPriceMoney);
                }
            }
            rOrdCartCommRequest.setPayMoney(payMoney);

            TransactionContentReqDTO transactionContentReqDTO = new TransactionContentReqDTO();
            transactionContentReqDTO.setStaffId(info.getStaff().getId());
            transactionContentReqDTO.setShopId(rOrdCartCommRequest.getShopId());
            transactionContentReqDTO.setOrderMoney(payMoney);
            LogUtil.info(MODULE,
                    "acct input:" + JSON.toJSONString(transactionContentReqDTO).toString());
            List<AcctInfoResDTO> acctInfoResDTOList = acctManageRSV
                    .queryAcctWithOrderByStaff(transactionContentReqDTO);
            for (RPreOrdMainResponse rReslutPreOrdMainResponse : rPreOrdsResponse
                    .getPreOrdMainList()) {
                if (rReslutPreOrdMainResponse.getShopId().equals(rOrdCartCommRequest.getShopId())) {
                    rReslutPreOrdMainResponse.setOrdAcctInfoList(acctInfoResDTOList);
                    break;
                }
            }

        }
        rOrdCartsCommRequest.setCurrentSiteId(info.getCurrentSiteId());
        LogUtil.info(MODULE, "ship input:" + JSON.toJSONString(rOrdCartsCommRequest).toString());
        // 根据封装的参数，进行运费计算
        Map<Long, Long> shipExpenseMap = gdsShiptemRSV.calcShipExpenseByCarts(rOrdCartsCommRequest);
        for (RPreOrdMainResponse rReslutPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {
            Long shipExpense = shipExpenseMap.get(rReslutPreOrdMainResponse.getShopId());
            rReslutPreOrdMainResponse.setRealExpressFee(shipExpense);


            //基准价格改造---start
            for (ROrdCartCommRequest rOrdCartCommRequest : rOrdCartsCommRequest.getOrdCartsCommList()) {
                if(rReslutPreOrdMainResponse.getShopId().equals(rOrdCartCommRequest.getShopId())){
                    Long standardMonery = 0l;
                    // 子订单级别优惠
                    Map<Long, CartPromItemDTO> ordSubMap = null;
                    if (rPreOrdsResponse.getCartPromRespDTO() != null) {
                        ordSubMap = rPreOrdsResponse.getCartPromRespDTO().getCartPromItemDTOMap();
                    }
                    for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                        if (ordSubMap != null) {
                            CartPromItemDTO itemProm = ordSubMap.get(rOrdCartItemCommRequest.getId());
                            if (itemProm != null) {
                                Long standMoney = itemProm.getDiscountCaclPrice() == null ? 0l : itemProm.getDiscountCaclPrice().longValue();
                                standardMonery += standMoney * rOrdCartItemCommRequest.getOrderAmount();
                            } else {
                                //默认商品基础价格
                                standardMonery += rOrdCartItemCommRequest.getBasePrice()  * rOrdCartItemCommRequest.getOrderAmount();
                            }
                        } else {
                            //默认商品基础价格
                            standardMonery += rOrdCartItemCommRequest.getBasePrice() * rOrdCartItemCommRequest.getOrderAmount();
                        }
                    }
                    rReslutPreOrdMainResponse.setOrderMoney(standardMonery);
                    break;
                } else {
                    continue;
                }

            }
            //基准价格改造---end
        }
        //处理组合商品
        delGroupCreatePre(rPreOrdsResponse);

        //优惠券信息
        CoupOrdCheckRespDTO coupOrdCheckRespDTO = iCoupDetailRSV.queryOrdCheckCoup(rOrdCartsCommRequest);
        rPreOrdsResponse.setCoupOrdCheckRespDTO(coupOrdCheckRespDTO);
        if(StringUtil.isNotEmpty(coupOrdCheckRespDTO)&&StringUtil.isNotEmpty(coupOrdCheckRespDTO.getCoupOrdSkuMap())) {
            for (RPreOrdMainResponse rPreOrdMainResponse : rPreOrdsResponse.getPreOrdMainList()) {
                rPreOrdMainResponse.setCoupCheckBeanRespDTOs(coupOrdCheckRespDTO.getCoupOrdSkuMap().get(rPreOrdMainResponse.getCartId()));
            }
        }
        LogUtil.info(MODULE, "========优惠券"+JSON.toJSONString(coupOrdCheckRespDTO)+"===========");



        LogUtil.info(MODULE, "生成预订单出参:" + JSON.toJSONString(rPreOrdsResponse).toString());
        return rPreOrdsResponse;

    }

    @Override
    public ROrdCartsCommRequest queryAssembleParamForAll(RCrePreOrdsRequest rCrePreOrdsRequest)
            throws BusinessException {
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
        rOrdCartsCommRequest.setSource(rCrePreOrdsRequest.getSource());

        /* 从数据库表和入参补齐信息--start */
        for (RCrePreOrdRequest rCrePreOrdRequest : rCrePreOrdsRequest.getCarList()) {
            ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
            rOrdCartCommRequest.setId(rCrePreOrdRequest.getCartId());
            rOrdCartCommRequest.setSource(rCrePreOrdsRequest.getSource());
            // 补齐购物车信息
            OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
            orderCartCriteria.createCriteria()
                    .andStaffIdEqualTo(rCrePreOrdRequest.getStaff().getId())
                    .andIdEqualTo(rCrePreOrdRequest.getCartId());
            List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria);
            if (CollectionUtils.isEmpty(ordCarts)) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352001);
            }
            OrdCart ordCart = ordCarts.get(0);
            ObjectCopyUtil.copyObjValue(ordCart, rOrdCartCommRequest, null, false);
            rOrdCartCommRequest.setPromId(rCrePreOrdRequest.getPromId());
            
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(rCrePreOrdRequest.getStaff().getId());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            shopStaffGroupReqDTO.setShopId(ordCart.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            rOrdCartCommRequest.setCustGroupValue(custGroupValue);
            rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
            rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
            //web  渠道
            rOrdCartCommRequest.setChannelValue(rCrePreOrdsRequest.getSource());  

            // 补齐购物车明细信息
            List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
            for (RCrePreOrdItemRequest rCrePreOrdItemRequest : rCrePreOrdRequest
                    .getCartItemIdList()) {
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                rOrdCartItemCommRequest.setId(rCrePreOrdItemRequest.getCartItemId());

                OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
                orderCartItemCriteria.createCriteria()
                        .andStaffIdEqualTo(rCrePreOrdItemRequest.getStaff().getId())
                        .andIdEqualTo(rCrePreOrdItemRequest.getCartItemId());

                List<OrdCartItem> ordCartItems = ordCartItemMapper
                        .selectByExample(orderCartItemCriteria);
                if (CollectionUtils.isEmpty(ordCartItems)) {
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
                }
                OrdCartItem ordCartItem = ordCartItems.get(0);
                ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
                rOrdCartItemCommRequest.setPromId(rCrePreOrdItemRequest.getPromId() == null ? -1 : rCrePreOrdItemRequest.getPromId());

                rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
            }
            // 每个店铺的明细列表
            rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);

            // 每个购物车信息
            rOrdCartCommRequests.add(rOrdCartCommRequest);
        }
        // 该买家的购物车信息列表
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        rOrdCartsCommRequest.setStaffId(rCrePreOrdsRequest.getStaff().getId());
        /* 从数据库表和入参补齐信息--end */

        /* 从各个域返回数据补齐信息--start */
        // 明细价格获取商品域
        Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
        for (ROrdCartCommRequest rOrdCartCommRequest :rOrdCartsCommRequest.getOrdCartsCommList()) {
            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                CartItemPriceInfo cartItemPriceInfo = priceMap.get(rOrdCartItemCommRequest.getSkuId());
                if (cartItemPriceInfo != null) {
                    rOrdCartItemCommRequest.setBasePrice(cartItemPriceInfo.getBasePrice());
                    rOrdCartItemCommRequest.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                    rOrdCartItemCommRequest.setScore(cartItemPriceInfo.getScore() == null ? 0l : cartItemPriceInfo.getScore());
                    rOrdCartItemCommRequest.setOrderSubScore(rOrdCartItemCommRequest.getOrderAmount() * rOrdCartItemCommRequest.getScore());
                } else {
                    LogUtil.info(MODULE, "未找到[" + rOrdCartItemCommRequest.getSkuId() + "]该商品的信息");
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
                }
                
            }
        }
        /* 从各个域返回数据补齐信息--end */
        return rOrdCartsCommRequest;
    }

    @Override
    public void deleteAfterOrderCart(RSumbitMainsRequest rSumbitMainsRequest) {
        
        List<RSumbitMainRequest> rSumbitMainRequests = rSumbitMainsRequest.getSumbitMainList();
        for (RSumbitMainRequest sumbitMainRequest : rSumbitMainRequests) {
            for (RSumbitSubRequest rSumbitSubRequest : sumbitMainRequest.getPreOrdSubList()) {
                this.ordCartItemSV.deleteAfterOrderCartItem(rSumbitSubRequest,rSumbitMainsRequest.getSource());
            }
            if (!(this.ordCartItemSV.queryIsLastCartItem(sumbitMainRequest.getStaffId(),
                    sumbitMainRequest.getCartId()))) {
                continue;
            }

            OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
            orderCartCriteria.createCriteria().andStaffIdEqualTo(sumbitMainRequest.getStaffId())
                    .andIdEqualTo(sumbitMainRequest.getCartId());
            List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria);
            if (CollectionUtils.isEmpty(ordCarts)) {
                LogUtil.info(MODULE, "未找到[" + sumbitMainRequest.getCartId() + "]该明细的信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352001);
            }
            OrdCart ordCart = ordCarts.get(0);
            OrdCartHIS ordCartHIS = new OrdCartHIS();
            ObjectCopyUtil.copyObjValue(ordCart, ordCartHIS, null, false);
            this.ordCartHISMapper.insert(ordCartHIS);
            this.ordCartMapper.deleteByExample(orderCartCriteria);
        }
    }

    @Override
    public ROrdCartsCommRequest queryBatchDeleteForProm(RBatchCartChgRequest rBatchCartChgRequest) {
        
        LogUtil.info(MODULE, "补齐入参:"+JSON.toJSONString(rBatchCartChgRequest));
        Map<Long, ROrdCartCommRequest> cartMap = new HashMap<Long, ROrdCartCommRequest>();

        for (RBatchCartsRequest rBatchCartsRequest : rBatchCartChgRequest.getrBatchCartsRequests()) {
            ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
            rOrdCartCommRequest.setId(rBatchCartsRequest.getCartId());

            // 补齐购物车信息
            OrdCartCriteria orderCartCriteria = new OrdCartCriteria();
            orderCartCriteria.createCriteria()
                    .andStaffIdEqualTo(rBatchCartChgRequest.getStaff().getId())
                    .andIdEqualTo(rBatchCartsRequest.getCartId());
            List<OrdCart> ordCarts = ordCartMapper.selectByExample(orderCartCriteria);
            if (CollectionUtils.isEmpty(ordCarts)) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352001);
            }
            OrdCart ordCart = ordCarts.get(0);
            ObjectCopyUtil.copyObjValue(ordCart, rOrdCartCommRequest, null, false);
            rOrdCartCommRequest.setPromId(rBatchCartsRequest.getPromId());
            
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(rBatchCartChgRequest.getStaff().getId());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            shopStaffGroupReqDTO.setShopId(ordCart.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            rOrdCartCommRequest.setCustGroupValue(custGroupValue);
            rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
            rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
            //web  渠道
            rOrdCartCommRequest.setChannelValue(rBatchCartChgRequest.getSource());  
            
            List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
            // 每个店铺的明细列表
            rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
            
            cartMap.put(rBatchCartsRequest.getCartId(), rOrdCartCommRequest);
        }

        // 补齐购物车明细信息
        
        for (RBatchCartItemsRequest rBatchCartItemsRequest : rBatchCartChgRequest.getrBatchCartItemsRequests()) {
            ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
            rOrdCartItemCommRequest.setId(rBatchCartItemsRequest.getCartItemId());

            OrdCartItemCriteria orderCartItemCriteria = new OrdCartItemCriteria();
            orderCartItemCriteria.createCriteria()
                    .andStaffIdEqualTo(rBatchCartChgRequest.getStaff().getId())
                    .andIdEqualTo(rBatchCartItemsRequest.getCartItemId());

            List<OrdCartItem> ordCartItems = ordCartItemMapper.selectByExample(orderCartItemCriteria);
            if (CollectionUtils.isEmpty(ordCartItems)) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
            }
            OrdCartItem ordCartItem = ordCartItems.get(0);
            ObjectCopyUtil.copyObjValue(ordCartItem, rOrdCartItemCommRequest, null, false);
            rOrdCartItemCommRequest.setPromId(rBatchCartItemsRequest.getPromId() == null ? -1
                    : rBatchCartItemsRequest.getPromId());

            ROrdCartCommRequest rOrdCartCommRequest = cartMap.get(rBatchCartItemsRequest.getCartId());
            if(rOrdCartCommRequest == null){
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352001);
            }
            rOrdCartCommRequest.getOrdCartItemCommList().add(rOrdCartItemCommRequest);
        }
        
        
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        rOrdCartsCommRequest.setStaffId(rBatchCartChgRequest.getStaff().getId());
        ObjectCopyUtil.copyObjValue(rBatchCartChgRequest, rOrdCartsCommRequest, null, false);
        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>(cartMap.values());
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        
        // 明细价格获取商品域
        Map<Long, CartItemPriceInfo> priceMap = gdsPriceRSV.caculate(rOrdCartsCommRequest);
        for (ROrdCartCommRequest rOrdCartCommRequest :rOrdCartsCommRequest.getOrdCartsCommList()) {
            for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
                CartItemPriceInfo cartItemPriceInfo = priceMap.get(rOrdCartItemCommRequest.getSkuId());
                if (cartItemPriceInfo != null) {
                    rOrdCartItemCommRequest.setBasePrice(cartItemPriceInfo.getBasePrice());
                    rOrdCartItemCommRequest.setBuyPrice(cartItemPriceInfo.getBuyPrice());
                    rOrdCartItemCommRequest.setScore(cartItemPriceInfo.getScore() == null ? 0l : cartItemPriceInfo.getScore());
                    rOrdCartItemCommRequest.setOrderSubScore(rOrdCartItemCommRequest.getOrderAmount() * rOrdCartItemCommRequest.getScore());
                } else {
                    LogUtil.info(MODULE, "未找到[" + rOrdCartItemCommRequest.getSkuId() + "]该商品的信息");
                    throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352002);
                }
                
            }
        }
        LogUtil.info(MODULE, "补齐出参:"+JSON.toJSONString(rOrdCartsCommRequest));
        return rOrdCartsCommRequest;
    }

    @Override
    public boolean queryBeforOrderCart(RSumbitMainsRequest rSumbitMainsRequest) {
        List<RSumbitMainRequest> rSumbitMainRequests = rSumbitMainsRequest.getSumbitMainList();
        for (RSumbitMainRequest sumbitMainRequest : rSumbitMainRequests) {
            for (RSumbitSubRequest rSumbitSubRequest : sumbitMainRequest.getPreOrdSubList()) {
                OrdCartItem ordCartItem = this.ordCartItemSV.queryCartItemByItemId(rSumbitSubRequest.getCartItemId(), 
                        rSumbitMainsRequest.getStaff().getId());
                if(ordCartItem  == null){
                    return false;
                }
                //如果数量不相等
                if(!ordCartItem.getOrderAmount().equals(rSumbitSubRequest.getOrderAmount())){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean queryFastBeforOrderCart(RSumbitMainsRequest rSumbitMainsRequest) {
        String key = rSumbitMainsRequest.getSourceKey();
        if(StringUtil.isBlank(key)){
            return false;
        }else{
            FastPreOrdRequest fastPreOrdRequest = (FastPreOrdRequest) CacheUtil.getItem(OrdConstants.Common.ORDER_FAST_KEY_PREFIX + key);
            if(fastPreOrdRequest==null||fastPreOrdRequest.getCarList()==null||fastPreOrdRequest.getCarList().size()==0){
                return false;
            }else{
                List<FastOrdRequest> fastOrdRequests = fastPreOrdRequest.getCarList();
                for (FastOrdRequest fastOrdRequest : fastOrdRequests) {
                    for (ROrdCartItemRequest rOrdCartItemRequest : fastOrdRequest.getOrdCartItemList()) {
    
                        if(rOrdCartItemRequest  == null){
                            return false;
                        }
                        //如果数量不相等
                        if(!rOrdCartItemRequest.getOrderAmount().equals(rSumbitMainsRequest.getSumbitMainList().get(0).getPreOrdSubList().get(0).getOrderAmount())){
                            return false;
                        }
                    }
                }
                return true;
            }
        }

    }
}
