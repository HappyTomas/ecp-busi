package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;

public class RoleManageSVImplTest extends EcpServicesTest {
    
    @Resource
    private IRoleManageSV roleManageSV;
    
    @Test
    public void testCountAuthMenu(){
        long a = roleManageSV.countAuthRole();
        System.out.println(a);
    }

}

