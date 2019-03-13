package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord003Req;
import com.zengshi.ecp.app.resp.Ord003Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 全选购物车明细<br>
 * Date:2016年3月5日上午10:26:56 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 *
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord003")
@Action(bizcode = "ord003", userCheck = true)
@Scope("prototype")
public class Ord003Action extends AppBaseAction<Ord003Req, Ord003Resp> {

    @Resource
    private IOrdCartItemRSV ordCartItemRSV;

    private static final String MODULE = Ord003Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        ROrdCartChgRequest r = new ROrdCartChgRequest();
        r.setrOrdCartItemRequest(this.request.getOrdCartItem());
        r.setSource(CommonConstants.SOURCE.SOURCE_APP);
        ordCartItemRSV.delOrdCartItem(r);
    }


}
