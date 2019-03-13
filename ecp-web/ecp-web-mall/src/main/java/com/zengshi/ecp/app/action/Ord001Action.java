package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord00101Req;
import com.zengshi.ecp.app.req.Ord001Req;
import com.zengshi.ecp.app.resp.Ord001Resp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 加入购物车服务<br>
 * Date:2016年3月5日上午10:27:37 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord001")
@Action(bizcode = "ord001", userCheck = true)
@Scope("prototype")
public class Ord001Action extends AppBaseAction<Ord001Req, Ord001Resp> {

    @Resource
    private IOrdCartRSV ordCartRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private IOrdSubRSV ordSubRSV;

    private static final String MODULE = Ord001Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        ROrdCartRequest rOrdCartRequest = new ROrdCartRequest();
        rOrdCartRequest.setStaffId(rOrdCartRequest.getStaff().getId());
        rOrdCartRequest.setCartType(this.request.getCartType());
        rOrdCartRequest.setShopId(this.request.getShopId());
        rOrdCartRequest.setPromId(this.request.getPromId());
        String shopName = "";
        if (rOrdCartRequest.getShopId() != null) {
            ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(this.request.getShopId());
            if (shop != null) {
                shopName = shop.getShopName();
            }
        }
        rOrdCartRequest.setShopName(shopName);
        List<Ord00101Req> ord00101Reqs = this.request.getOrd00101Reqs();
        
        
        
        List<ROrdCartItemRequest> itemList = new ArrayList<ROrdCartItemRequest>();
        for (Ord00101Req gdsItem : ord00101Reqs) {

            ROrdSubStaffIdxReq dto = new ROrdSubStaffIdxReq();
            dto.setSkuId(gdsItem.getSkuId());
            dto.setStaffId(rOrdCartRequest.getStaffId());
            if("2".equals(gdsItem.getGdsType()+"")){  
                List<ROrdSubStaffIdxResp> resps = ordSubRSV.queryOrderSubByStaffIdAndSkuid(dto);
                if(resps != null){
                    throw new BusinessException("抱歉，您不能重复购买虚拟商品！");
                }
            }
                
            ROrdCartItemRequest item = new ROrdCartItemRequest();
            item.setCartType(StringUtil.isEmpty(this.request.getCartType()) ? "01" : this.request.getCartType());
            item.setShopId(this.request.getShopId());
            item.setShopName(shopName);
            item.setStaffId(rOrdCartRequest.getStaff().getId());
            item.setGdsId(gdsItem.getGdsId());
            item.setGdsName(gdsItem.getGdsName());
            item.setSkuId(gdsItem.getSkuId());
            item.setSkuInfo(gdsItem.getSkuInfo());
            item.setGroupType(StringUtil.isEmpty(gdsItem.getGroupType()) ? "0" : gdsItem.getGroupType());
            item.setGroupDetail(gdsItem.getGroupDetail());
            item.setGdsType(gdsItem.getGdsType());
            item.setOrderAmount(gdsItem.getOrderAmount());
            item.setCategoryCode(gdsItem.getCategoryCode());
            item.setScoreTypeId(gdsItem.getScoreTypeId());
            item.setPrnFlag(StringUtil.isEmpty(gdsItem.getPrnFlag()) ? "0" : gdsItem.getPrnFlag());
            item.setPromId(gdsItem.getPromId());

            itemList.add(item);
        }
        rOrdCartRequest.setOrdCartItemList(itemList);
        LogUtil.info(MODULE, "Ord001Action:" + JSON.toJSONString(rOrdCartRequest));
        this.ordCartRSV.addToCart(rOrdCartRequest);
    }

}
