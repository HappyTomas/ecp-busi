package com.zengshi.ecp.im.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.im.dubbo.dto.CustInfoImResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelReqDTO;
import com.zengshi.ecp.im.service.busi.interfaces.IOfuserSV;
import com.zengshi.ecp.im.service.util.ImUtil;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class TestOfuser extends EcpServicesTest{
	
	@Resource
	private IOfuserSV orsv;
	
	@Test
    public void testSave() {
		try {
			/*1、调用工具类，创建openfire用户*/
			//ImUtil.addAccountManager("hxl_test_03", "123456", new HashMap<String,String>());
			ImOfuserRelReqDTO req = new ImOfuserRelReqDTO();
			req.setStaffCode("hxl_test_13");
			req.setOfStaffCode("hxl_test_13");
			req.setStaffId(569L);
			orsv.saveOfuser(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
    public void testSel() {
		try {
			CustInfoImResDTO res = orsv.findCustByOfuser("hxl_test_13");
			System.out.println(res.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

