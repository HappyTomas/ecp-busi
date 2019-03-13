package com.zengshi.ecp.order.service.busi.workplat.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.WOrderCountInfo;


public interface IOrderMainInfoSV {

    public WOrderCountInfo queryOrderFromDB(Long byShopId, Timestamp byStartTime,
            Timestamp byEndTime);
    public WOrderCountInfo queryOrderFromXW(Long byShopId, Timestamp byStartTime,
            Timestamp byEndTime);
}

