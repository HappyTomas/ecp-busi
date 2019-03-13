package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.interfaces.IOrderWorkPlatRSV;
import com.zengshi.ecp.order.service.busi.workplat.interfaces.IOrderWorkPlatSV;

public class OrderWorkPlatRSVImpl implements IOrderWorkPlatRSV {

    @Resource
    private IOrderWorkPlatSV orderWorkPlatSV;
    
    @Override
    public long catchPlatDeliverCount() {
        return orderWorkPlatSV.catchPlatDeliverCount();
    }

    @Override
    public long catchPlatOfflineCheckCount() {
        return orderWorkPlatSV.catchPlatOfflineCheckCount();
    }

    @Override
    public long catchPlatBackGdsOrderCount() {
        return orderWorkPlatSV.catchPlatBackGdsOrderCount();
    }

    @Override
    public long catchPlatBackMoneyOrderCount() {
        return orderWorkPlatSV.catchPlatBackMoneyOrderCount();
    }

}

