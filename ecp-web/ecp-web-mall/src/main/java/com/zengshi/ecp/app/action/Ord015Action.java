package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord015Req;
import com.zengshi.ecp.app.resp.Ord015Resp;
import com.zengshi.ecp.order.dubbo.dto.RGroupChgRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 更新组合商品数量<br>
 * Date:2016年3月5日上午10:29:20 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord015")
@Action(bizcode = "ord015", userCheck = true)
@Scope("prototype")
public class Ord015Action extends AppBaseAction<Ord015Req, Ord015Resp> {

    private static final String MODULE = Ord015Action.class.getName();
    @Resource
    private IOrdCartItemRSV ordCartItemRSV;

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        RGroupChgRequest r = new RGroupChgRequest();
        r.setrOrdCartItemRequests(this.request.getOrdCartItems());
        r.setSource(CommonConstants.SOURCE.SOURCE_APP);
        ordCartItemRSV.updateGroupAmount(r);
    }
    

}
