package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord103Req;
import com.zengshi.ecp.app.resp.Ord103Resp;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 增减商品数量<br>
 * Date:2016年3月5日上午10:29:20 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord103")
@Action(bizcode = "ord103", userCheck = true)
@Scope("prototype")
public class Ord103Action extends AppBaseAction<Ord103Req, Ord103Resp> {

    private static final String MODULE = Ord103Action.class.getName();

    @Resource
    private IOrdCartItemRSV ordCartItemRSV;

    @Resource
    private IPromRSV promRSV;

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        ROrdCartItemCommRequest r = new ROrdCartItemCommRequest();
        this.request.getOrdCartItem().setStaffId(r.getStaff().getId());
        
        ordCartItemRSV.updateOrdCartItemAmountNoProm(this.request.getOrdCartItem());
    }

}
