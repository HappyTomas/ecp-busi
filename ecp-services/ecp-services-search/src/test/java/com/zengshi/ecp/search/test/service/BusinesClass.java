package com.zengshi.ecp.search.test.service;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dao.model.SecConfig;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfigSV;

public class BusinesClass {
    
    private static ISecConfigSV secConfigSV;

    @Resource
    public void setSecConfigSV(ISecConfigSV secConfigSV) {
        BusinesClass.secConfigSV = secConfigSV;
    }

    public static void testListSecConfig() {

        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        for (SecConfig secConfig : secConfigSV.listSecConfig(secConfigReqDTO)) {
            System.out.println(secConfig);
        }

    }

}

