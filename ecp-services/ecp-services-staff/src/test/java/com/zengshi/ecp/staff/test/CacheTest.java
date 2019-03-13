/** 
 * Project Name:ecp-services-staff-server 
 * File Name:CacheTest.java 
 * Package Name:com.zengshi.ecp.staff.test 
 * Date:2015年10月20日上午10:03:48 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.test;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年10月20日上午10:03:48  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class CacheTest  extends EcpServicesTest{
    @Test
    public void clearScoreConfigCacheTest()
    {
        long count = StaffUtil.clearScoreConfigCache();
        
        LogUtil.warn(CacheTest.class.getName(), "总共删除了："+count+"记录！");
    }
}

