package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord104Req;
import com.zengshi.ecp.app.resp.Ord104Resp;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 删除、批量删除购物车明细<br>
 * Date:2016年3月5日上午10:29:38 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord104")
@Action(bizcode = "ord104", userCheck = true)
@Scope("prototype")
public class Ord104Action extends AppBaseAction<Ord104Req, Ord104Resp> {

    @Resource
    private IOrdCartRSV ordCartRSV;
    
    private static final String MODULE = Ord104Action.class.getName();
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        RBatchCartChgRequest params = new RBatchCartChgRequest();
        params.setrOrdCartItemRequests(this.request.getOrdCartItems());
        ordCartRSV.deleteBatchCartItems(params);
        this.response.setSuccess(true);
        this.response.setMessage("删除成功");
    }

}
