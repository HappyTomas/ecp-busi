package com.zengshi.ecp.im.test;

import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyResDTO;
import com.zengshi.ecp.im.service.busi.interfaces.ISatisfyEvaluateSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import org.junit.Test;

import javax.annotation.Resource;

public class SatisfyEvaluateTest extends EcpServicesTest{
	
	@Resource
	private ISatisfyEvaluateSV satisfyEvaluateSV;
	
	@Test
    public void testAddSatisfyEvaluate() {

        CustServSatisfyReqDTO reqDTO = new CustServSatisfyReqDTO();
        reqDTO.setCsaCode("hello");
        reqDTO.setOfStaffCode("world");
        reqDTO.setSatisfyType("3");
        reqDTO.setSessionId("good333");
        reqDTO.setShopId(100L);
        CustServSatisfyResDTO resDTO = satisfyEvaluateSV.addSatisfyEvaluate(reqDTO);
        System.out.println(reqDTO);
    }
	

}

