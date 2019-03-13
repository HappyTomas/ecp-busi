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
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
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
public class CmsAdvertiseRSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsAdvertiseRSV cmsAdvertiseRSV;

    /** 
     * saveCmsAdvertiseTest:(测试  新增广告). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
//    @Test
//    public void saveCmsAdvertiseTest() {
//            CmsAdvertiseReqDTO dto = new CmsAdvertiseReqDTO();
//            dto.setAdvertiseTitle("测试的");
//            dto.setLinkType("01");
//            dto.setLinkName("一号店");
//            dto.setLinkUrl("http://localhost:8080/k");
//            dto.setVfsName("广告名称大家好才是真的好");
//            dto.setVfsId("1");
//            dto.setSortNo(1);
//            dto.setSubSystem(CmsConstants.PlatType.CMS_PLATTYPE_PLAT);
//            dto.setShopId(1L);
//            dto.setRemark("测试的");
//            dto.setPlaceId(1L);
//            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
//            dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//            dto.setCreateStaff(dto.getCreateStaff());  
//            cmsAdvertiseRSV.addCmsAdvertise(dto);
//        
//    }
//    
//    /** 
//     * updateCmsAdvertiseTest:(测试广告更新). <br/> 
//     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
//     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
//     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
//     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
//     * 
//     * @author jiangzh  
//     * @since JDK 1.6 
//     */ 
//    @Test
//    public void updateCmsAdvertiseTest() {
//        CmsAdvertiseReqDTO dto = new CmsAdvertiseReqDTO();
//        
//        dto.setId(1003L);
//        dto.setAdvertiseTitle("测试的03-测试修改");
//        dto.setLinkType("01");
//        dto.setLinkName("一号店");
//        dto.setLinkUrl("http://localhost:8080/k");
//        dto.setVfsName("广告名称大家好才是真的好");
//        dto.setVfsId("1");
//        dto.setSortNo(1);
//        dto.setSubSystem(CmsConstants.PlatType.CMS_PLATTYPE_PLAT);
//        dto.setShopId(1L);
//        dto.setRemark("测试的03-测试修改");
//        dto.setPlaceId(1L);
//        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
//        dto.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//        dto.setUpdateStaff(dto.getCreateStaff());
//        
//        cmsAdvertiseRSV.updateCmsAdvertise(dto);
//    }

    /** 
     * queryCmsAdvertiseTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    @Test
    public void queryCmsAdvertiseTest() {
        CmsAdvertiseReqDTO dto = new CmsAdvertiseReqDTO();
        Date date = new Date();
        dto.setThisTime(new Timestamp(date.getTime()));
        dto.setPubTime(new Timestamp(2015-10-01));
        dto.setLostTime(new Timestamp(2015-12-01));
        dto.setStatus("1");
        List list = cmsAdvertiseRSV.queryCmsAdvertiseList(dto);
        return;
    }
    
    /** 
     * queryCmsAdvertisePageTest:(测试广告查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
//    @Test
//    public void queryCmsAdvertisePageTest() {
//        CmsAdvertiseReqDTO dto = new CmsAdvertiseReqDTO();
//        dto.setPageNo(1);
//        dto.setPageSize(10);
//        cmsAdvertiseRSV.queryCmsAdvertisePage(dto);
//    }
    
    /** 
     * changeStatusCmsAdvertiseTest:(测试广告生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
//    @Test
//    public void changeStatusCmsAdvertiseTest() {
//        String id = "4003";
//        String status = "1";
//        cmsAdvertiseRSV.changeStatusCmsAdvertise(id, status);
//    }
    
    /** 
     * changeStatusCmsAdvertiseBatch:(测试广告批量生效失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
//     */ 
//    @Test
//    public void changeStatusCmsAdvertiseBatchTest() {
//        List<String> list = new ArrayList<String>();
//        list.add("4003");
//        list.add("4002");
//        String status = "1";
//        cmsAdvertiseRSV.changeStatusCmsAdvertiseBatch(list, status);
//    }
//    
    /** 
     * deleteCmsAdvertiseTest:(测试广告删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
//    @Test
//    public void deleteCmsAdvertiseTest() {
//        String id = "4003";
//        cmsAdvertiseRSV.deleteCmsAdvertise(id);;
//    }
//    
    /** 
     * deleteCmsAdvertise:(测试广告批量删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
//    @Test
//    public void deleteCmsAdvertiseBatchTest() {
//        List<String> list = new ArrayList<String>();
//        list.add("4003");
//        list.add("4002");
//        cmsAdvertiseRSV.deleteCmsAdvertiseBatch(list);
//    }

}

