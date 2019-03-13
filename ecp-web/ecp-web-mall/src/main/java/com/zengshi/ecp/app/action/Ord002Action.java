package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord002Req;
import com.zengshi.ecp.app.resp.Ord00201Resp;
import com.zengshi.ecp.app.resp.Ord00202Resp;
import com.zengshi.ecp.app.resp.Ord00203Resp;
import com.zengshi.ecp.app.resp.Ord00204Resp;
import com.zengshi.ecp.app.resp.Ord00205Resp;
import com.zengshi.ecp.app.resp.Ord002Resp;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdCartsResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 购物车列表查询<br>
 * Date:2016年3月5日上午10:27:54 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord002")
@Action(bizcode = "ord002", userCheck = true)
@Scope("prototype")
public class Ord002Action extends AppBaseAction<Ord002Req, Ord002Resp> {
    
    @Resource
    private IOrdCartRSV ordCartRSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
    
    private static final String MODULE = Ord002Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        ROrdCartRequest rOrdCartRequest = new ROrdCartRequest();
        rOrdCartRequest.setStaffId(rOrdCartRequest.getStaff().getId());
        rOrdCartRequest.setSource(CommonConstants.SOURCE.SOURCE_APP);
        rOrdCartRequest.setPromSubmitType(this.request.getPromSubmitType());
        LogUtil.info(MODULE, "Ord002Action:" + JSON.toJSONString(rOrdCartRequest));
        RShowOrdCartsResponse rShowOrdCartsResponse = this.ordCartRSV.queryCart(rOrdCartRequest);

        this.response.setStaffId(rOrdCartRequest.getStaff().getId());
        List<Ord00201Resp> ordCartLists = new ArrayList<Ord00201Resp>();

        if(CollectionUtils.isNotEmpty(rShowOrdCartsResponse.getOrdCartList())){
            for(ROrdCartResponse cart:rShowOrdCartsResponse.getOrdCartList()){
                Ord00201Resp appCartsResp = new Ord00201Resp();
                ObjectCopyUtil.copyObjValue(cart, appCartsResp, "", false);
                Long cartId = cart.getId();
                appCartsResp.setCartId(cartId);

                //订单促销信息
                Map<Long, CartPromDTO> cartPromDTOMap = null;
                if(rShowOrdCartsResponse.getCartPromRespDTO() != null){
                    cartPromDTOMap = rShowOrdCartsResponse.getCartPromRespDTO().getCartPromDTOMap();
                }
                CartPromDTO cartPromDTO = null;
                if(cartPromDTOMap != null){
                    cartPromDTO = cartPromDTOMap.get(cartId);
                }
                        
                if(cartPromDTO != null){
                    //赠品信息
                    appCartsResp.setGiftInfo(getGiftText(cartPromDTO));
                    // 各种促销信息（列表展示）
                    appCartsResp.setPromInfoDTOList(getPromInfoList(cartPromDTO));

                    //是否满足促销条件
                    appCartsResp.setIfFulfillProm(cartPromDTO.isIfFulfillProm());
                    if(cartPromDTO.isIfFulfillProm()){
                        appCartsResp.setFulfilMsg((cartPromDTO.getPromTypeMsgResponseDTO()!=null)?cartPromDTO.getPromTypeMsgResponseDTO().getFulfilMsg():"");
                    }else{
                        appCartsResp.setNoFulfilMsg(cartPromDTO.getPromTypeMsgResponseDTO() != null ? cartPromDTO.getPromTypeMsgResponseDTO().getNoFulfilMsg() : "");
                    }
                    //购物车促销ID
                    if(cartPromDTO.getPromInfoDTO() != null){
                        appCartsResp.setPromId(cartPromDTO.getPromInfoDTO().getId());
                    } 
                    //促销金额相关
                    appCartsResp.setDiscountAmount(cartPromDTO.getDiscountAmount().longValue());
                    appCartsResp.setDiscountMoney(cartPromDTO.getDiscountMoney().longValue()+cartPromDTO.getDiscountPriceMoney().longValue());
                    appCartsResp.setDiscountPrice(cartPromDTO.getDiscountPrice().longValue());
                }

                //购物车列表拼接
                List<Ord00202Resp> appCartItems = new ArrayList<Ord00202Resp>();
                if(CollectionUtils.isNotEmpty(cart.getOrdCartItemList())){
                    for(ROrdCartItemResponse cartItem:cart.getOrdCartItemList()){
                        Ord00202Resp appCartItemResp = new Ord00202Resp();
                        ObjectCopyUtil.copyObjValue(cartItem,appCartItemResp,"",false);
                        LongReqDTO reqDTO=new LongReqDTO(cartItem.getGdsType());
                        boolean isVirtual=gdsInfoExternalRSV.isVirtualProduct(reqDTO);
                        appCartItemResp.setVirtual(isVirtual);
                        Map<Long, CartPromItemDTO> cartPromItemDTOMap = null;
                        if(rShowOrdCartsResponse.getCartPromRespDTO() != null){
                            cartPromItemDTOMap = rShowOrdCartsResponse.getCartPromRespDTO().getCartPromItemDTOMap();
                        }
                        CartPromItemDTO cartPromItemDTO = null;
                        if(cartPromItemDTOMap != null){
                            cartPromItemDTO = cartPromItemDTOMap.get(cartItem.getId());
                        }
                        if(cartPromItemDTO != null){
                            //赠品信息
                            // 各种促销信息（列表展示）
                            CartPromDTO parentProm = new CartPromDTO();
                            ObjectCopyUtil.copyObjValue(cartPromItemDTO,parentProm,"",false);
                            appCartItemResp.setPromInfoDTOList(getPromInfoList(parentProm));
                            //促销Id
                            if(cartPromItemDTO != null){
                                appCartItemResp.setOrdPromId(cartPromItemDTO.getOrdPromId());
                                if(cartPromItemDTO.getPromInfoDTO() != null){
                                    appCartItemResp.setPromId(cartPromItemDTO.getPromInfoDTO().getId());
                                }
//                              没促销（包含不满足促销活动）  
//                              原价  basePrice
//                              单价  buyPrice
//                              商品总价    buyPrice*orderAmount
//                                                      
//                              有促销 
//                              原价  discountCaclPrice
//                              单价  discountPrice
//                              商品总价    discountCaclPrice*discountAmount - （discountPriceMoney+discountMoney）
                                
//                                APP的价格
//                                没促销（包含不满足促销活动）  
//                                原价  basePrice
//                                单价  buyPrice
//                                商品总价    buyPrice*orderAmount
    //
//                                满足促销
//                                原价  discountCaclPrice
//                                单价  discountPrice
//                                商品总价    discountCaclPrice*discountAmount - discountMoney

                                

                                //是否满足促销条件
                                appCartItemResp.setIfFulfillProm(cartPromItemDTO.isIfFulfillProm());
                                if(cartPromItemDTO.isIfFulfillProm()){
                                    appCartItemResp.setFulfilMsg(cartPromItemDTO.getPromTypeMsgResponseDTO() != null ? cartPromItemDTO.getPromTypeMsgResponseDTO().getFulfilMsg() : "");
                                    
                                    //促销金额相关
                                    appCartItemResp.setDiscountCaclPrice(cartPromItemDTO.getDiscountCaclPrice().longValue());
                                    appCartItemResp.setDiscountAmount(cartPromItemDTO.getDiscountAmount().longValue());
                                    appCartItemResp.setDiscountMoney(cartPromItemDTO.getDiscountMoney().longValue()+cartPromItemDTO.getDiscountPriceMoney().longValue());
                                    appCartItemResp.setDiscountPrice(cartPromItemDTO.getDiscountFinalPrice().longValue());
                                }else{
                                    appCartItemResp.setNoFulfilMsg(cartPromItemDTO.getPromTypeMsgResponseDTO() != null ? cartPromItemDTO.getPromTypeMsgResponseDTO().getNoFulfilMsg() : "");
                                }
                        }
                        }
                        appCartItems.add(appCartItemResp);
                    }
                }
                appCartsResp.setOrdCartItemList(appCartItems);
                
                //组合商品购物车拼接
                List<Ord00205Resp> appGroupItems = new ArrayList<Ord00205Resp>();
                if(CollectionUtils.isNotEmpty(cart.getGroupLists())){
                    for(List<ROrdCartItemResponse> group:cart.getGroupLists()){
                        
                        List<Ord00202Resp> appGroupItemsResp = new ArrayList<Ord00202Resp>();
                        if(CollectionUtils.isNotEmpty(group)){
                            for(ROrdCartItemResponse appGroupItem: group){
                                Ord00202Resp appGroupItemResp = new Ord00202Resp();
                                ObjectCopyUtil.copyObjValue(appGroupItem,appGroupItemResp,"",false);
                                appGroupItemResp.setGdsStatus(appGroupItem.isGdsStatus());
                                LongReqDTO reqDTO=new LongReqDTO(appGroupItem.getGdsType());
                                boolean isVirtual=gdsInfoExternalRSV.isVirtualProduct(reqDTO);
                                appGroupItemResp.setVirtual(isVirtual);
                                if(rShowOrdCartsResponse.getCartPromRespDTO() != null){
                                    Map<Long, CartPromItemDTO> cartPromItemDTOMap = rShowOrdCartsResponse.getCartPromRespDTO().getCartPromItemDTOMap();
                                    CartPromItemDTO cartPromItemDTO = cartPromItemDTOMap.get(appGroupItem.getId());
                                    //赠品信息
                                    // 各种促销信息（列表展示）
                                    CartPromDTO parentProm = new CartPromDTO();
                                    ObjectCopyUtil.copyObjValue(cartPromItemDTO,parentProm,"",false);
                                    appGroupItemResp.setPromInfoDTOList(getPromInfoList(parentProm));
                                    //促销Id
                                    if(cartPromItemDTO != null){
                                        appGroupItemResp.setOrdPromId(cartPromItemDTO.getOrdPromId());
                                        if(cartPromItemDTO.getPromInfoDTO() != null){
                                            appGroupItemResp.setPromId(cartPromItemDTO.getPromInfoDTO().getId());
                                        }


                                        //是否满足促销条件
                                        appGroupItemResp.setIfFulfillProm(cartPromItemDTO.isIfFulfillProm());
                                        if(cartPromItemDTO.isIfFulfillProm()){
                                            //促销金额相关
                                            appGroupItemResp.setDiscountCaclPrice(cartPromItemDTO.getDiscountCaclPrice().longValue());
                                            appGroupItemResp.setDiscountAmount(cartPromItemDTO.getDiscountAmount().longValue());
                                            appGroupItemResp.setDiscountMoney(cartPromItemDTO.getDiscountMoney().longValue()+cartPromItemDTO.getDiscountPriceMoney().longValue());
                                            appGroupItemResp.setDiscountPrice(cartPromItemDTO.getDiscountFinalPrice().longValue());
                                            appGroupItemResp.setFulfilMsg(cartPromItemDTO.getPromTypeMsgResponseDTO() != null ? cartPromItemDTO.getPromTypeMsgResponseDTO().getFulfilMsg() : "");
                                        }else{
                                            appGroupItemResp.setNoFulfilMsg(cartPromItemDTO.getPromTypeMsgResponseDTO() != null ? cartPromItemDTO.getPromTypeMsgResponseDTO().getNoFulfilMsg() : "");
                                        }
                                    }
                                }

                                appGroupItemsResp.add(appGroupItemResp);
                            }
                        }
                        Ord00205Resp ord00205Resp = new Ord00205Resp();
                        ord00205Resp.setGroupSkus(appGroupItemsResp);
                        
                        appGroupItems.add(ord00205Resp);
                    }
                }
                Ord00204Resp ord00204Resp = new Ord00204Resp();
                ord00204Resp.setGroupLists(appGroupItems);
                appCartsResp.setGroupLists(ord00204Resp);
                ordCartLists.add(appCartsResp);
            }
        }

        this.response.setOrdCartList(ordCartLists);
    }

    /**
     * 赠品信息抽取
     * @param cartPromDTO
     * @return
     */
    private String getGiftText(CartPromDTO cartPromDTO){

        String giftInfo = "";
        String $itemSplitStr = "、";

        if(CollectionUtils.isNotEmpty(cartPromDTO.getPromGiftDTOList())){
            for(PromGiftDTO giftDTO : cartPromDTO.getPromGiftDTOList()){
                giftInfo = giftInfo + $itemSplitStr + giftDTO.getGiftName() + " X " + giftDTO.getEveryTimeCnt();
            }
        }
        return giftInfo;
    }

    //促销列表反正供展示
    private List<Ord00203Resp> getPromInfoList(CartPromDTO cartPromDTO){
        List<Ord00203Resp> promInfoDTOList = new ArrayList<Ord00203Resp>();
        if(CollectionUtils.isNotEmpty(cartPromDTO.getPromInfoDTOList())){
            for(PromInfoDTO promDTO : cartPromDTO.getPromInfoDTOList()){
                Ord00203Resp promInfo = new Ord00203Resp();
                ObjectCopyUtil.copyObjValue(promDTO,  promInfo, "", false);
                promInfoDTOList.add(promInfo);
            }
        }
        return promInfoDTOList;
    }

}
