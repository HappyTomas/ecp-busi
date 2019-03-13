package com.zengshi.ecp.order.dubbo.interfaces;

import java.sql.Timestamp;
import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.WOrderCountInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrderWorkBrancRSV {
    
    public void doCollectJob(Map<String, String> extendParams)throws BusinessException;    
    public WOrderCountInfo collectOrder(Long byShopId, Timestamp byCurTime) throws BusinessException;
    public WOrderCountInfo collectOrder(Long byShopId, Timestamp byStartTime, Timestamp byEndTime) throws BusinessException;

}

