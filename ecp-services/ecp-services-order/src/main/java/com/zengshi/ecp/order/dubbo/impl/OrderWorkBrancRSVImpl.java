package com.zengshi.ecp.order.dubbo.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.WOrderCountInfo;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderWorkBrancRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.workplat.interfaces.IOrderMainInfoSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2016年5月20日下午2:35:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 统计订单统计情况
 */
public class OrderWorkBrancRSVImpl implements IOrderWorkBrancRSV{
    
    public static final String MODULE = OrderWorkBrancRSVImpl.class.getName();
    
    @Resource
    private IOrderMainInfoSV ordermaininfoSV;
    
    @Override
    public WOrderCountInfo collectOrder(Long byShopId, Timestamp byCurTime) throws BusinessException{
        
        WOrderCountInfo orderCountInfo = null;
        
        //1.判断系统数据来源标识  1.行为分析系统 2.本系统
        BaseSysCfgRespDTO config = SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE");
        
        switch (config.getParaValue()) {
        case "1":
            orderCountInfo = collectOrderFromXW(byShopId, byCurTime);
            orderCountInfo.setDataSource("1");
            break;
        case "2":
            orderCountInfo = collectOrderFromDB(byShopId, byCurTime);
            orderCountInfo.setDataSource("2");
            break;
        default:
            LogUtil.error(MODULE, "系统参数SYS_ADMIN_ITEM_SOURCE配置的值不合法，请重新配置");
            break;
        }
        
        return orderCountInfo;
    }

    @Override
    public WOrderCountInfo collectOrder(Long byShopId, Timestamp byStartTime, Timestamp byEndTime) throws BusinessException{
        WOrderCountInfo orderCountInfo = null;
        
        //1.判断系统数据来源标识  1.行为分析系统 2.本系统
        BaseSysCfgRespDTO config = SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE");
        switch (config.getParaValue()) {
        case "1":
            orderCountInfo = collectOrderFromXW(byShopId, byStartTime, byEndTime);
            orderCountInfo.setDataSource("1");
            break;
        case "2":
            orderCountInfo = collectOrderFromDB(byShopId, byStartTime, byEndTime);
            orderCountInfo.setDataSource("2");
            break;
        default:
            LogUtil.error(MODULE, "系统参数SYS_ADMIN_ITEM_SOURCE配置的值不合法，请重新配置");
            break;
        }
        
        return orderCountInfo;
    }

    private WOrderCountInfo collectOrderFromDB(Long byShopId, Timestamp byCurTime) {
        
        WOrderCountInfo orderCountInfo = new WOrderCountInfo();
        
        //1.取当天第一秒钟
        Timestamp byStartTime = DateUtil.getTheDayFirstSecond(byCurTime);
        //2.取当天最后一秒钟
        Timestamp byEndTime = DateUtil.getTheDayLastSecond(byCurTime);
        
        orderCountInfo = ordermaininfoSV.queryOrderFromDB(byShopId, byStartTime, byEndTime);
        
        return orderCountInfo;
    }

    private WOrderCountInfo collectOrderFromDB(Long byShopId, Timestamp byStartTime,
            Timestamp byEndTime) {
        WOrderCountInfo orderCountInfo = new WOrderCountInfo();
        
        /*方案1，拆分时间，若byEndTime是当天，则当前的数据取数据库，则不是，则取统计表
         * 
        //拆分时间 byStartTime -- 昨天第一秒钟 -- 昨天 -- 昨天最后一秒 -- 今天第一秒 -- 今天 --今天最后一秒 -- byEndTime
        //1.0如果byEndTime与当前时间是同一天
        if (endTimeInToday(byEndTime)) {
              //则查当天第一秒钟 -- byEndTime
               * 
              //将byEndTime换成昨天最后一秒
              byEndTime = DateUtil.getTheDayLastSecond(DateUtil.getOffsetDaysTime(byEndTime, -1));
        }
        //查byStartTime - byEndTime
        //查统计表
        */
        //累计
        
        //方案2.不拆分时间，全部取数据库
        orderCountInfo = ordermaininfoSV.queryOrderFromDB(byShopId, DateUtil.getTheDayFirstSecond(byStartTime), DateUtil.getTheDayLastSecond(byEndTime));
                
        return orderCountInfo;
    }

    private WOrderCountInfo collectOrderFromXW(Long byShopId, Timestamp byCurTime) {
        WOrderCountInfo orderCountInfo = new WOrderCountInfo();
        
        //1.取当天第一秒钟
        Timestamp byStartTime = DateUtil.getTheDayFirstSecond(byCurTime);
        //2.取当天最后一秒钟
        Timestamp byEndTime = DateUtil.getTheDayLastSecond(byCurTime);
        
        orderCountInfo = ordermaininfoSV.queryOrderFromXW(byShopId, byStartTime, byEndTime);
        
        return orderCountInfo;
     }

    private WOrderCountInfo collectOrderFromXW(Long byShopId, Timestamp byStartTime,
            Timestamp byEndTime) {
        
        WOrderCountInfo orderCountInfo = new WOrderCountInfo();
        
        orderCountInfo = ordermaininfoSV.queryOrderFromXW(byShopId, DateUtil.getTheDayFirstSecond(byStartTime), DateUtil.getTheDayLastSecond(byEndTime));

        return orderCountInfo;
    }

    @Override
    public void doCollectJob(Map<String, String> extendParams) throws BusinessException {
    }


}

