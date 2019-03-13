package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.LoginResultResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.alibaba.fastjson.JSON;

public class AuthLoginRSVImplTest extends EcpServicesTest {
	@Resource
    private IAuthLoginRSV authLoginRSV;
	
	@Test
	public void checkLogin(){
		String loginpara = "cp613";
		String password = "1234561";
		
		LoginResultResDTO dto = null;
		try {
			dto = authLoginRSV.checkLogin(loginpara, password);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			System.out.println("catch!");
			e.printStackTrace();
		}
		System.out.println(dto==null?"dto":JSON.toJSONString(dto));
	}
	
}
