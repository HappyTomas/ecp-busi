package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord110Req;
import com.zengshi.ecp.app.resp.Ord110Resp;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 确认收货<br>
 * Date:2110年3月5日上午10:29:20 <br>
 * Copyright (c) 2110 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord110")
@Action(bizcode = "ord110", userCheck = true)
@Scope("prototype")
public class Ord110Action extends AppBaseAction<Ord110Req, Ord110Resp> {

    private static final String MODULE = Ord110Action.class.getName();
    @Resource
    private IOrdReceiptRSV ordReceiptRSV;
    
    @Resource 
    private IOrdMainRSV ordMainRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        
        ROrderDetailsRequest rdor = new ROrderDetailsRequest();
        rdor.setOrderId(this.request.getOrderId());
        rdor.setOper("03");// 确认收货
        resp = ordMainRSV.queryOrdOperChk(rdor);
        if (resp.isStatus() == true) {
            ROrdReceiptRequest rdto = new ROrdReceiptRequest();
            rdto.setOrderId(this.request.getOrderId());
            rdto.setStaffId(rdto.getStaff().getId());
            ordReceiptRSV.orderReceipt(rdto);
        } else {
            throw new BusinessException(resp.getMsg());
        }
    }
}
