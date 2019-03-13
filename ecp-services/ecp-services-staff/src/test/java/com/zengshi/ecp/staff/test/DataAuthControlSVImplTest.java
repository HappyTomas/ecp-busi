package com.zengshi.ecp.staff.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.auth.IRuleOfDataAuth;
import com.zengshi.ecp.server.auth.RuleObject;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class DataAuthControlSVImplTest extends EcpServicesTest {

    @Resource
    private IRuleOfDataAuth ruleOfDataAuth; //店铺管理服务
    
    @Test
    public void testDataAuth(){
    	List<RuleObject> list = ruleOfDataAuth.getRuleObjects("T01", 178L);
    	System.out.println(list.size());
    }
    
    

}

