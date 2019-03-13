/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
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
public class CmsSiteRSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsSiteRSV cmsSiteRSV;

    /** 
     * saveCmsSiteTest:(测试  新增广告). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void saveCmsSiteTest() {
        CmsSiteReqDTO dto = new CmsSiteReqDTO();
        dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        dto.setCreateStaff(dto.getCreateStaff());  
        cmsSiteRSV.addCmsSite(dto);
    }
    
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
    public void updateCmsSiteTest() {
        CmsSiteReqDTO dto = new CmsSiteReqDTO();
        
        dto.setId(4003L);
        dto.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        dto.setUpdateStaff(dto.getCreateStaff());
        
        cmsSiteRSV.updateCmsSite(dto);
    }

    /** 
     * queryCmsSiteTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryCmsSiteTest() {
        CmsSiteReqDTO dto = new CmsSiteReqDTO();
        dto.setId(4003L);
        cmsSiteRSV.queryCmsSite(dto);
    }
    
    /** 
     * queryCmsSitePageTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryCmsSitePageTest() {
        CmsSiteReqDTO dto = new CmsSiteReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        cmsSiteRSV.queryCmsSitePage(dto);
    }
    
    /** 
     * changeStatusCmsSiteTest:(测试广告生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void changeStatusCmsSiteTest() {
        String id = "4003";
        String status = "1";
        cmsSiteRSV.changeStatusCmsSite(id, status);
    }
    
    /** 
     * changeStatusCmsSiteBatch:(测试广告批量生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void changeStatusCmsSiteBatchTest() {
        List<String> list = new ArrayList<String>();
        list.add("4003");
        list.add("4002");
        String status = "1";
        cmsSiteRSV.changeStatusCmsSiteBatch(list, status);
    }
    
    /** 
     * deleteCmsSiteTest:(测试广告删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void deleteCmsSiteTest() {
        String id = "4003";
        cmsSiteRSV.deleteCmsSite(id);;
    }
    
    /** 
     * deleteCmsSite:(测试广告批量删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void deleteCmsSiteBatchTest() {
        List<String> list = new ArrayList<String>();
        list.add("4003");
        list.add("4002");
        cmsSiteRSV.deleteCmsSiteBatch(list);
    }

}

