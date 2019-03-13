package com.zengshi.ecp.pay;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dubbo.dto.pay.PayShopCfgRequest;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayShopCfgSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class PayShopCfgSVImplTest extends EcpServicesTest{

    @Resource
    private IPayShopCfgSV payShopCfgSV;
    
    @Test
    public void testGetCfgByShopId() {
        List<PayShopCfg> cfgList = payShopCfgSV.getCfgByShopId(100L);
        System.out.println("---------------------------------"+cfgList);
    }
    
    @Test
    public void testAddCfg() {
        PayShopCfgRequest r = new PayShopCfgRequest();
        r.setShopId(35L);
        r.setPayWay("9002");
        r.setKeyName("5631d5efabece4e4fa4ab6e2");
        r.setCerPassword("aipay123456");
        r.setUseFlag(PayStatus.PAY_WAY_OPEN);
        r.setCreateStaff("weijq");
        r.setMercCode("111111");
        payShopCfgSV.addCfg(r);
    }
    
    @Test
    public void testEditCfg() {
        PayShopCfgRequest r = new PayShopCfgRequest();
        r.setShopId(100L);
        r.setPayWay("9012");
        r.setUseFlag(PayStatus.PAY_WAY_OPEN);
        r.setCreateStaff("weijq");
        r.setMercCode("2222");
        r.setPayWayName("沃支付");
        r.setUpdateStaff("weijq");
        payShopCfgSV.editCfg(r);
    }
}
