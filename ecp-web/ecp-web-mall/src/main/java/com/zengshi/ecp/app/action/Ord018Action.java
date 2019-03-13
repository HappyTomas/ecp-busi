package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord017Req;
import com.zengshi.ecp.app.req.Ord018Req;
import com.zengshi.ecp.app.resp.Ord017Resp;
import com.zengshi.ecp.app.resp.Ord018Resp;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryCartRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 查询购物车商品数量<br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linw
 * @version
 * @since JDK 1.6
 */
@Service("ord018")
@Action(bizcode = "ord018", userCheck = true)
@Scope("prototype")
public class Ord018Action extends AppBaseAction<Ord018Req, Ord018Resp> {

    private static final String MODULE = Ord018Action.class.getName();
    
    @Resource
    private IOrdCartRSV ordCartRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
    	RQueryCartRequest rQueryCartRequest=new RQueryCartRequest();
    	rQueryCartRequest.setStaffId(rQueryCartRequest.getStaff().getId());
    	Long num=ordCartRSV.querySumAmountByStaffId(rQueryCartRequest);
    	if(LongUtils.isEmpty(num)){
    		num=0L;
    	}
       this.response.setOrdCartItemNum(num);
    }
}
