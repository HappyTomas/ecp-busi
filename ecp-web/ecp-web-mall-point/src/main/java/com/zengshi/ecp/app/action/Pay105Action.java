package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pay105Req;
import com.zengshi.ecp.app.resp.Pay105Resp;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 验证付款结果<br>
 * Date:2016年3月5日上午10:33:26  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
@Service("pay105")
@Action(bizcode = "pay105", userCheck = true)
@Scope("prototype")
public class Pay105Action extends AppBaseAction<Pay105Req, Pay105Resp> {
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    private static final String MODULE = Pay105Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        try {
            ROrderDetailsRequest rdor = new ROrderDetailsRequest(); 
            rdor.setOrderId(this.request.getOrderId());
            rdor.setOper(this.request.getCheckCode());
            ROrdCartsChkResponse resp = ordMainRSV.queryOrdOperChk(rdor); 
            this.response.setStatus(resp.isStatus());
            this.response.setMsg(resp.getMsg());
        } catch (Exception e) {
            this.response.setStatus(false);
            this.response.setMsg(e.getMessage());
        }
    }

}
