package com.zengshi.ecp.aip.third.dubbo.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.YouzanOrderReq;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrdersResp;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IYouzanOrderRSV;
import com.zengshi.ecp.aip.third.service.busi.order.youzan.IYouzanOrderSV;

import javax.annotation.Resource;

/**
 * Created by guojingman on 2017/2/22.
 */
public class YouzanOrderRSVImpl implements IYouzanOrderRSV {
    @Resource
    private IYouzanOrderSV orderSV;

    @Override
    public YouzanOrdersResp fetchOrdersFromYouzan(YouzanOrderReq orderReq) {

        return orderSV.fetchOrdersFromYouzan(orderReq);
    }
}
