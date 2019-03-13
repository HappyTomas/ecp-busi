package com.zengshi.ecp.pay;

import javax.annotation.Resource;

import org.drools.compiler.compiler.BoundIdentifiers;
import org.junit.Test;

import com.zengshi.ecp.order.dubbo.dto.WOrderCountInfo;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderWorkBrancRSV;
import com.zengshi.ecp.order.service.busi.workplat.interfaces.IOrderMainInfoSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;

public class OrderWorkBrancImplTEST extends EcpServicesTest{

    @Resource
    private IOrderWorkBrancRSV orderWorkBrancRSV;
    
    @Resource
    private IOrderMainInfoSV ordermaininfoSV;
    
    @Test
    public void testCollectJob()
    {
        WOrderCountInfo countInfo = orderWorkBrancRSV.collectOrder(100L, DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -5));
        
        LogUtil.info("", countInfo.getAllOrderCount().toString());
    }
    @Test
    public void testHttpClient()
    {
        WOrderCountInfo countInfo = ordermaininfoSV.queryOrderFromXW(100L, DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -15), DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -5));
    }
}

