package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord102Req;
import com.zengshi.ecp.app.resp.Ord10201Resp;
import com.zengshi.ecp.app.resp.Ord10202Resp;
import com.zengshi.ecp.app.resp.Ord102Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdCartsResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
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
@Service("ord102")
@Action(bizcode = "ord102", userCheck = true)
@Scope("prototype")
public class Ord102Action extends AppBaseAction<Ord102Req, Ord102Resp> {
    
    @Resource
    private IOrdCartRSV ordCartRSV;
    
    private static final String MODULE = Ord102Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        ROrdCartRequest rOrdCartRequest = new ROrdCartRequest();
        rOrdCartRequest.setStaffId(rOrdCartRequest.getStaff().getId());
        LogUtil.info(MODULE, "Ord102Action:" + JSON.toJSONString(rOrdCartRequest));
        RShowOrdCartsResponse rShowOrdCartsResponse = this.ordCartRSV.queryCart(rOrdCartRequest);

        this.response.setStaffId(rOrdCartRequest.getStaff().getId());
        List<Ord10201Resp> ordCartLists = new ArrayList<Ord10201Resp>();

        if(CollectionUtils.isNotEmpty(rShowOrdCartsResponse.getOrdCartList())){
            for(ROrdCartResponse cart:rShowOrdCartsResponse.getOrdCartList()){
                Ord10201Resp appCartsResp = new Ord10201Resp();
                ObjectCopyUtil.copyObjValue(cart, appCartsResp, "", false);
                Long cartId = cart.getId();
                appCartsResp.setCartId(cartId);


                //购物车列表拼接
                List<Ord10202Resp> appCartItems = new ArrayList<Ord10202Resp>();
                if(CollectionUtils.isNotEmpty(cart.getOrdCartItemList())){
                    for(ROrdCartItemResponse cartItem:cart.getOrdCartItemList()){
                        Ord10202Resp appCartItemResp = new Ord10202Resp();
                        ObjectCopyUtil.copyObjValue(cartItem,appCartItemResp,"",false);
                        appCartItems.add(appCartItemResp);
                    }
                }
                appCartsResp.setOrdCartItemList(appCartItems);
                ordCartLists.add(appCartsResp);
            }
        }

        this.response.setOrdCartList(ordCartLists);
    }
}
