package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord010Req;
import com.zengshi.ecp.app.resp.Ord010Resp;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 取消订单<br>
 * Date:2016年3月5日上午10:31:03 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord010")
@Action(bizcode = "ord010", userCheck = true)
@Scope("prototype")
public class Ord010Action extends AppBaseAction<Ord010Req, Ord010Resp> {

    @Resource
    private IOrdMainRSV ordMainRSV;

    private static final String MODULE = Ord010Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        String orderId = this.request.getOrderId();
        
        if (StringUtil.isBlank(orderId)) {
            LogUtil.error(MODULE, "订单号不能为空");
            throw new BusinessException("orderId不能为空");
        }

        ROrderDetailsRequest rdor = new ROrderDetailsRequest();
        rdor.setOrderId(orderId);
        rdor.setOper("04"); //取消订单验证状态
        // 取消订单来源判断
        rdor.setDelFrom(OrdConstants.DealFrom.FROM_BUYER);
        ROrdCartsChkResponse resp = ordMainRSV.queryOrdOperChk(rdor);
        if (resp!= null && resp.isStatus() == true) {
            ROrderDetailsRequest rdetail = new ROrderDetailsRequest();
            rdetail.setOrderId(orderId);
            rdetail.setStaffId(rdor.getStaff().getId());
            ordMainRSV.removeOrd(rdetail);
        }
    }

}
