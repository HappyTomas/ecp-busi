package com.zengshi.ecp.staff.test;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dao.model.AuthMenu;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Menu;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IMenuManageSV;


public class MenuManageSVImplTest extends EcpServicesTest {
    
    @Resource
    private IMenuManageSV menuManageSV;
    
    @Test
    public void saveAuthMenuTest() {
        for(int i=0; i<20; i++){
            AuthMenu am = new AuthMenu();
            am.setCreateTime(new Timestamp(System.currentTimeMillis()));
            am.setCreateStaff(0L);
            am.setMenuTitle("tile_"+i);
            am.setStatus("1");
            am.setMenuUrl("/url/menu/tile_"+i);
            am.setSysCode("1000");
            menuManageSV.saveAuthMenu(am);
        }
    }
    @Test
    public void savePrivilegeMenuRelTest() {
        for(long i=0; i<21; i++){
            AuthPrivilege2Menu ap2m = new AuthPrivilege2Menu();
            ap2m.setSysCode("1000");
            ap2m.setMenuId(i);
            ap2m.setPrivilegeId(i);
            menuManageSV.savePrivilegeMenuRel(ap2m);
        }
    }
    

}

