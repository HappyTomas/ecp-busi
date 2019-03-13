package com.zengshi.ecp.staff.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IMenuManageRSV;
import com.zengshi.paas.utils.CacheUtil;

public class MenuManageRSVImplTest extends EcpServicesTest {
    
    @Resource
    private IMenuManageRSV menuManageRSV;
    
    @Test
    public void listAuthMenuTest(){
//        List<String> req = new ArrayList<String>();
//        req.add("1000");
//        List<AuthMenuResDTO> res = menuManageRSV.listAuthMenu(req);
//        System.out.println(res.size());
    }
    
    @Test
    public void menuCacheTest(){
        List<AuthMenuResDTO> res = (List<AuthMenuResDTO>) CacheUtil.getItem("SYS_SUB_SYSTEM_1000");
        System.out.println(res.size());
    }
}

