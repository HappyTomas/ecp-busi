package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;

public class AuthRelManageSVImplTest extends EcpServicesTest {
    
    @Resource
    private IAuthRelManageSV authRelManageSV;
    
    @Test
    public void testDeleteStaffGroupRel(){
        AuthStaff2Group delete = new AuthStaff2Group(); 
        authRelManageSV.deleteStaffGroupRel(delete);
    }
}

