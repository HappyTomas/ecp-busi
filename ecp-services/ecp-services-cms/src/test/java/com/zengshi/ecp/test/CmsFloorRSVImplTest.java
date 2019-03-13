/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
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
public class CmsFloorRSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsFloorRSV cmsFloorRSV;


    
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
    public void queryFloorInfoDetailTest() {
        CmsFloorInfoDetailReqDTO reqDto = new CmsFloorInfoDetailReqDTO();
        CmsFloorInfoDetailRespDTO respDto = null;
        
        //楼层id 必填
        reqDto.setFloorId(12000l);
        
        //设置选项
        /*FloorQueryOption[] options = new FloorQueryOption[] {
                FloorQueryOption.TAB,
                FloorQueryOption.ADVERTISE,
                FloorQueryOption.COUPON,
                FloorQueryOption.LABEL,
                FloorQueryOption.GDS
                };
        reqDto.setFloorOptions(options);
        */        
        //设置各信息个数
        reqDto.setTabSize(3);
        
        //查询
        respDto = this.cmsFloorRSV.queryFloorInfoDetail(reqDto);
        reqDto = null;
    }

}

