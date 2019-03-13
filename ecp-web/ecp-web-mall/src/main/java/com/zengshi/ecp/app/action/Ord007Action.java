package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord007Req;
import com.zengshi.ecp.app.resp.Ord007Resp;
import com.zengshi.ecp.order.dubbo.dto.RBatchCartChgRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 批量删除购物车明细<br>
 * Date:2016年3月5日上午10:29:38 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord007")
@Action(bizcode = "ord007", userCheck = true)
@Scope("prototype")
public class Ord007Action extends AppBaseAction<Ord007Req, Ord007Resp> {

    @Resource
    private IOrdCartRSV ordCartRSV;
    
    private static final String MODULE = Ord007Action.class.getName();
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        RBatchCartChgRequest params = new RBatchCartChgRequest();
        params.setrOrdCartItemRequests(this.request.getOrdCartItems());
        ordCartRSV.deleteBatchCartItems(params);
        this.response.setSuccess(true);
        this.response.setMessage("删除成功");
    }

}
