package com.zengshi.ecp.staff.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.auth.attribute.FilterRule;
import com.zengshi.ecp.server.auth.attribute.IRuleOfDataFilter;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class DataFilterControlRSVImplTest extends EcpServicesTest {
	
	@Resource
	private IRuleOfDataFilter ruleOfDataFilter;
	
	@Test
	public void getRule(){
		System.out.println("\n\n\n=====  DataFilterControlRSVImplTest 1  ======");
		List<FilterRule> list = ruleOfDataFilter.getRules("DA1012", 178L);
		
		System.out.println("\n\n\n=====  DataFilterControlRSVImplTest 2  ======");
		
		System.out.println(list.size());
		
		System.out.println("\n\n\n=====  DataFilterControlRSVImplTest 3  ======");
	}

}

