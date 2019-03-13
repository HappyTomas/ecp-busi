package com.zengshi.ecp.im.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.service.common.interfaces.IStaffHotlineSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class TestHotline extends EcpServicesTest{
	
	@Resource
	private IStaffHotlineSV hotlineSV;
	
	@Test
    public void testSave() {
		try {
			/*SessionReqDTO dto = new SessionReqDTO();
			dto.setUserCode("user_wangbh");
			dto.setIssueType("0");
			SessionResDTO sessionResDTO = hotlineSV.getSession(dto);*/
			ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
			dto.setStaffId(314l);
			dto.setShopId(100L);
			dto.setModuleType("1");
			hotlineSV.addHotlineStaff(dto);
			//System.out.println(sessionResDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}

