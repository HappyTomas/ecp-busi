/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月5日下午10:25:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsFloorGdsRSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsFloorGdsRSV cmsFloorGdsRSV;


    
    /** 
     * updateCmsSiteTest:(测试广告更新). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void deleteFloorGdsForPromTest() {
        CmsFloorGdsReqDTO reqDto = new CmsFloorGdsReqDTO();
        reqDto.setPromId(3494l);
        reqDto.setGdsId(43203l);
        List<Long> gdsids = new ArrayList<Long>();
        gdsids.add(37887l);
        gdsids.add(37903l);
        gdsids.add(43203l);
        reqDto.setGdsIds(gdsids);
        this.cmsFloorGdsRSV.deleteCmsFloorGdsForProm(reqDto);
    }

}

