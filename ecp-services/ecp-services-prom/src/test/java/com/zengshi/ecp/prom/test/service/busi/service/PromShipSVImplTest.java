package com.zengshi.ecp.prom.test.service.busi.service;

import javax.annotation.Resource;
import org.junit.Test;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipRespDTO;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromShipSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromShipSVImplTest extends EcpServicesTest {

    @Resource
    private IPromShipSV promShipSV;
    
    
    @Test
    public void testNUll() throws BusinessException {
        PromShipRespDTO promShipRespDTO=promShipSV.qryPromShip(null);
    }
    @Test
    public void test() throws BusinessException {
        PromShipDTO promShipDTO=new PromShipDTO();
        PromShipRespDTO promShipRespDTO=promShipSV.qryPromShip(promShipDTO);
    }
 
    @Test
    public void testSuccess() throws BusinessException {
        PromShipDTO promShipDTO=new PromShipDTO();
       // promShipDTO.setCityCode("11");
       // promShipDTO.setProvinceCode("22");
       // promShipDTO.setCountryCode("33");
        promShipDTO.setMoney(22222222222200l);
        promShipDTO.setShopId(69l);
        PromShipRespDTO promShipRespDTO=promShipSV.qryPromShip(promShipDTO);
        System.out.println(promShipRespDTO);
    }
}
