package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.impl.AuthStaffRSVImpl;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyCheckSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustCheckSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;


public class AuthStaffSVImplTest extends EcpServicesTest{

   
	@Resource(name="registerSV")
    private IRegisterSV registerSV;
	
	@Resource
    private ICustCheckSV custCheckSV;
	
	@Resource
	private ICompanyCheckSV companyCheckSV;
	
    @Resource
    private IAuthStaffRSV testRSV ;
    
    @Test
    public void testSaveInfo() {
        AuthStaffReqDTO info = new AuthStaffReqDTO();
    	try {
    	    info.setStaffCode("wangbh");
    	    info.setStaffPasswd("123");
    	    info.setSerialNumber("13960729412");
    	    info.setStaffClass("20");
    	    info.setCreateFrom("00");
    	    registerSV.saveAuthStaff(info);
          
		} catch (Exception e) {
		        e.printStackTrace();
		}
       
      
    }
    @Test
    public void testfindPrivilByStaffCode()
    {
        System.out.println("=========  测试用户权限集合  开始 ==========");
        

        System.out.println("=========  测试用户权限集合  开始 2==========");
        AuthPrivilegeResDTO result = testRSV.findPrivilByStaffCode("pengjie","10");
        
        if(result!=null)
            System.out.println("========="+result.toString());
        
        System.out.println("=========  测试用户权限集合  结束 ==========");
    }
}

