package com.zengshi.ecp.unpf.dubbo.impl.order;

import com.zengshi.ecp.unpf.dubbo.dto.order.RYouzanOrderReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RYouzanOrderResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.YouzanPushOrdersEntityReq;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IYouzanSynOrdersRSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IYouzanSynOrdersSV;

import javax.annotation.Resource;

/**
 * Created by guojingman on 2017/2/23.
 */
public class YouzanSynOrdersRSVImpl implements IYouzanSynOrdersRSV {
    @Resource
    private IYouzanSynOrdersSV youzanSynOrdersSV;

    @Override
    public void saveOrdersFromYouzan(RYouzanOrderReq orderReq) {
        youzanSynOrdersSV.saveOrdersFromYouzan(orderReq);
    }

    @Override
    public void saveOrUpdateOrdersFromYouzanPush(YouzanPushOrdersEntityReq orderReq) {
        youzanSynOrdersSV.saveOrUpdateOrdersFromYouzanPush(orderReq);
    }
}
