/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseAreaAdminUtilTest.java 
 * Package Name:test.ecp.sys 
 * Date:2015-8-25下午6:23:47 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.server.test.EcpServicesTest;

import net.sf.json.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-25下午6:23:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseAreaAdminUtilTest extends EcpServicesTest {
    

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseAreaAdminUtil#fetchCountryInfos()}.
     */
    @Test
    public void testFetchCountryInfos() {
        long begin = Calendar.getInstance().getTime().getTime();
        List<BaseAreaAdminRespDTO> dtos = BaseAreaAdminUtil.fetchCountryInfos();
        System.out.println("===========fetchtime:"+(Calendar.getInstance().getTime().getTime() - begin));
    }
    
    @Test
    public void testCycle(){
        for(int i =0 ; i<10 ; i++){
            testFetchCountryInfos();
        }
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseAreaAdminUtil#fetchChildAreaInfos(java.lang.String)}.
     */
    @Test
    public void testFetchChildAreaInfos() {
        
        List<BaseAreaAdminRespDTO> dtos = BaseAreaAdminUtil.fetchChildAreaInfos("156");
        for(BaseAreaAdminRespDTO dto : dtos){
            System.out.println("=============" + JSONObject.fromObject(dto).toString());
        }
        
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseAreaAdminUtil#fetchAreaInfo(java.lang.String)}.
     */
    @Test
    public void testFetchAreaInfo() {
        BaseAreaAdminRespDTO dto = BaseAreaAdminUtil.fetchAreaInfo("350922");

            System.out.println("=============" + JSONObject.fromObject(dto).toString());

    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseAreaAdminUtil#fetchAreaName(java.lang.String)}.
     */
    @Test
    public void testFetchAreaName() {
        String areaCode = "350922";
        String areaName = BaseAreaAdminUtil.fetchAreaName(areaCode);
        System.out.println("=============areaCode:" + areaCode+";areaName:" + areaName);
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseAreaAdminUtil#clearCacheArea()}.
     */
    @Test
    public void testClearCacheArea() {
        BaseAreaAdminUtil.clearCache();
    }
    
    

}

