package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IPrivlgManageSV;

public class PrivlgManageSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPrivlgManageSV privlgManageSV;
    
    @Test
    public void saveAuthPrivilegeTest(){
        for (int i = 0; i < 20; i++) {
            AuthPrivilege  ap = new AuthPrivilege();
            ap.setRoleAdmin("privgl_"+i);
            ap.setStatus("1");
            ap.setSysCode("1000");
            ap.setPrivilegeType("10");
            privlgManageSV.saveAuthPrivilege(ap);
        }
        
        
    }

}

