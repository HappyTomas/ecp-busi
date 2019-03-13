/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV;
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
public class CmsItemPropPreRSVTest extends EcpServicesTest{
    
    @Resource
    private ICmsItemPropPreRSV cmsItemPropPreRSV;
    
    /** 
     * queryItemPropPreRespDTOList:(模块编辑时，回填值查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryItemPropPreRespDTOList() {
        CmsItemPropPreReqDTO dto = new CmsItemPropPreReqDTO();
        dto.setItemId(10000L);
        dto.setStatus("0");
        List<CmsItemPropPreRespDTO> itemPropPreRespDTOList = cmsItemPropPreRSV.queryCmsItemPropValuePreList(dto);
        return;
    }

}

