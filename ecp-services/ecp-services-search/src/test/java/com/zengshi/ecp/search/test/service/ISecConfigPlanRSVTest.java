package com.zengshi.ecp.search.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class ISecConfigPlanRSVTest extends EcpServicesTest{
    
    @Resource
    private ISecConfigPlanRSV secConfigPlanRSV;
    
    @Test
    public void create(){
        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setId(11l);
        String result=secConfigPlanRSV.createCollection(secConfigReqDTO);
        
        System.out.println("-------------------------------------------------");
        System.out.println(result);
        System.out.println("-------------------------------------------------");
    }
    
    @Test
    public void delete(){
        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setId(11l);
        String result=secConfigPlanRSV.deleteCollection(secConfigReqDTO);
        
        System.out.println("-------------------------------------------------");
        System.out.println(result);
        System.out.println("-------------------------------------------------");
    }

}

