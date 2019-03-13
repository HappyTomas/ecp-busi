package com.zengshi.ecp.staff.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAdminManageSV;

public class AdminManageSVImplTest extends EcpServicesTest {
    
    @Resource
    private IAdminManageSV adminManageSV;
    
    @Test
    public void testSaveAuthStaffAdmin(){
        
        long start = System.currentTimeMillis();
        long i=1;
        while(i<30){
            AuthStaffAdminReqDTO save = new AuthStaffAdminReqDTO();
            save.setStaffCode("test_admin_"+i);
            save.setAliasName("aliasName_"+i);
            save.setStaffName("realName_"+i);
            save.setStaffPasswd("123456");
            save.setCreateFrom(StaffConstants.authStaff.CREATE_FROM_MANAGER);
            adminManageSV.saveAuthStaffAdmin(save);
            i++;
            System.out.println(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("开始："+new Date(start) +"\n\n\n耗时：" + (end-start));
        
    }
}

