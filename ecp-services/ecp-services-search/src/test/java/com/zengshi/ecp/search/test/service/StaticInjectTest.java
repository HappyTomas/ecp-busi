package com.zengshi.ecp.search.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.search.dao.model.SecConfig;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfigSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class StaticInjectTest extends EcpServicesTest {

//    @Resource
    private static ISecConfigSV secConfigSV;

    @Resource
    public void setSecConfigSV(ISecConfigSV secConfigSV) {
        StaticInjectTest.secConfigSV = secConfigSV;
    }

    @Test
    public void testListSecConfig() {

        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        for (SecConfig secConfig : StaticInjectTest.secConfigSV.listSecConfig(secConfigReqDTO)) {
            System.out.println(secConfig);
        }

    }


}
