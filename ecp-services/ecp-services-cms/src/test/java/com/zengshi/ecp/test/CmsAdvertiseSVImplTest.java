/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-7-31下午5:17:01 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV;
import com.zengshi.ecp.server.test.EcpServicesTest;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月5日下午10:25:22  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsAdvertiseSVImplTest extends EcpServicesTest{

    /**
     * Test method for {@link com.zengshi.ecp.CmsAdvertiseSVImpl.service.common.impl.DemoInfoSVImpl#saveInfo(com.zengshi.ecp.demo.dao.model.DemoInfo)}.
     */
    @Resource
    private ICmsAdvertiseSV cmsAdvertiseSV;
    
    @Test
    public void testSaveInfo() {
    	CmsAdvertiseReqDTO dto = new CmsAdvertiseReqDTO();
    	dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
    	dto.setAdvertiseTitle("测试说明");
    	dto.setStatus("1");
        cmsAdvertiseSV.addCmsAdvertise(dto);
    }

}

