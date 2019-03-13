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

import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
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
public class CmsHotSearchRSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsHotSearchRSV cmsHotSearchRSV;

    /** 
     * saveCmsHotSearchTest:(测试  新增广告). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void saveCmsHotSearchTest() {
        CmsHotSearchReqDTO dto = new CmsHotSearchReqDTO();
        dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        dto.setCreateStaff(dto.getCreateStaff());  
        cmsHotSearchRSV.addCmsHotSearch(dto);
    }
    
    /** 
     * updateCmsHotSearchTest:(测试广告更新). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void updateCmsHotSearchTest() {
        CmsHotSearchReqDTO dto = new CmsHotSearchReqDTO();
        
        dto.setId(4003L);
        dto.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        dto.setUpdateStaff(dto.getCreateStaff());
        
        cmsHotSearchRSV.updateCmsHotSearch(dto);
    }

    /** 
     * queryCmsHotSearchTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryCmsHotSearchTest() {
        CmsHotSearchReqDTO dto = new CmsHotSearchReqDTO();
        dto.setId(4003L);
        cmsHotSearchRSV.queryCmsHotSearch(dto);
    }
    
    /** 
     * queryCmsHotSearchPageTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryCmsHotSearchPageTest() {
        CmsHotSearchReqDTO dto = new CmsHotSearchReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        dto.setStatus("1");
        cmsHotSearchRSV.queryCmsHotSearchPage(dto);
    }
    
    /** 
     * changeStatusCmsHotSearchTest:(测试广告生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void changeStatusCmsHotSearchTest() {
        String id = "4003";
        String status = "1";
        cmsHotSearchRSV.changeStatusCmsHotSearch(id, status);
    }
    
    /** 
     * changeStatusCmsHotSearchBatch:(测试广告批量生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void changeStatusCmsHotSearchBatchTest() {
        List<String> list = new ArrayList<String>();
        list.add("4003");
        list.add("4002");
        String status = "1";
        cmsHotSearchRSV.changeStatusCmsHotSearchBatch(list, status);
    }
    
    /** 
     * deleteCmsHotSearchTest:(测试广告删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void deleteCmsHotSearchTest() {
        String id = "4003";
        cmsHotSearchRSV.deleteCmsHotSearch(id);;
    }
    
    /** 
     * deleteCmsHotSearch:(测试广告批量删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void deleteCmsHotSearchBatchTest() {
        List<String> list = new ArrayList<String>();
        list.add("4003");
        list.add("4002");
        cmsHotSearchRSV.deleteCmsHotSearchBatch(list);
    }

}

