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

import com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
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
public class CmsListRSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsListRSV cmsListRSV;

    /** 
     * saveCmsListTest:(测试  新增广告). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
//    @Test
//    public void saveCmsListTest() {
//        
//        for(int i=18;i<26;i++){
//            CmsListReqDTO dto = new CmsListReqDTO();
//            dto.setListClass("01");
//            dto.setGdsId(1008609212L);
//            
//            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
//            dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//            dto.setCreateStaff(dto.getCreateStaff());  
//            cmsListRSV.addCmsList(dto);
//        }
//        
//    }
//    
    /** 
     * updateCmsListTest:(测试广告更新). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
//     */ 
//    @Test
//    public void updateCmsListTest() {
//        CmsListReqDTO dto = new CmsListReqDTO();
//        
//        dto.setId(2L);
//       
//        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
//        dto.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//        dto.setUpdateStaff(dto.getCreateStaff());
//        
//        cmsListRSV.updateCmsList(dto);
//    }

    /** 
     * queryCmsListTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryCmsListTest() {
        CmsListReqDTO dto = new CmsListReqDTO();
        dto.setId(3L);
        cmsListRSV.queryCmsList(dto);
    }
    
    /** 
     * queryCmsListPageTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryCmsListPageTest() {
        CmsListReqDTO dto = new CmsListReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        cmsListRSV.queryCmsListPage(dto);
    }
    
    /** 
     * changeStatusCmsListTest:(测试广告生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void changeStatusCmsListTest() {
        String id = "6";
        String status = "1";
        cmsListRSV.changeStatusCmsList(id, status);
    }
    
    /** 
     * changeStatusCmsListBatch:(测试广告批量生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void changeStatusCmsListBatchTest() {
        List<String> list = new ArrayList<String>();
        list.add("5");
        list.add("6");
        String status = "1";
        cmsListRSV.changeStatusCmsListBatch(list, status);
    }
    
    /** 
     * deleteCmsListTest:(测试广告删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
//    @Test
//    public void deleteCmsListTest() {
//        String id = "4003";
//        cmsListRSV.deleteCmsList(id);;
//    }
//    
//    /** 
//     * deleteCmsList:(测试广告批量删除). <br/> 
//     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
//     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
//     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
//     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
//     * 
//     * @author jiangzh  
//     * @since JDK 1.6 
//     */ 
//    @Test
//    public void deleteCmsListBatchTest() {
//        List<String> list = new ArrayList<String>();
//        list.add("4003");
//        list.add("4002");
//        cmsListRSV.deleteCmsListBatch(list);
//    }

}
