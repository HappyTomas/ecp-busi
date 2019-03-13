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

import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月5日下午10:25:12 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
public class CmsRecommendRSVImplTest extends EcpServicesTest{

    @Resource
    private ICmsRecommendRSV cmsRecommendRSV;

    /**
     * saveCmsExpertRecommendTest:(测试 新增广告). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    // @Test
    // public void saveCmsExpertRecommendTest() {
    //
    // for(int i=18;i<26;i++){
    // CmsExpertRecommendReqDTO dto = new CmsExpertRecommendReqDTO();
    //// dto.setExpertRecommendTitle("测试的"+i);
    //// dto.setLinkType("01");
    //// dto.setLinkName("一号店");
    //// dto.setLinkUrl("http://localhost:8080/k");
    //// dto.setBmpName("广告名称大家好才是真的好");
    //// dto.setBmpUrl("http://localhost:8080/p");
    //// dto.setVfsId("1");
    //// dto.setSortNo(1);
    //// dto.setSubSystem(CmsConstants.PlatType.CMS_PLATTYPE_PLAT);
    //// dto.setShopId(1L);
    //// dto.setRemark("测试的"+i);
    //// dto.setPlaceId(1L);
    // dto.setRecommendType("01");
    // dto.setAuthorName("xiaomo"+i);
    // dto.setAuthorImage("C:/Users/ASUS/Pictures/LifeFrame");
    // dto.setAuthorIntroduction("haoa,hao aho hao"+i);
    // dto.setRecommendGdsId(1008609212L);
    // dto.setOtherProduction("daomubiji");
    // dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
    // dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
    // dto.setCreateStaff(dto.getCreateStaff());
    // cmsExpertRecommendRSV.addCmsExpertRecommend(dto);
    // }
    //
    // }
    //
    /**
     * updateCmsExpertRecommendTest:(测试广告更新). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    @Test
    public void updateCmsExpertRecommendTest() {
        CmsRecommendReqDTO dto = new CmsRecommendReqDTO();

        dto.setId(1L);
        dto.setSortNo(6);
        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        dto.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        dto.setUpdateStaff(dto.getCreateStaff());

        cmsRecommendRSV.updateCmsRecommend(dto);
    }

    /**
     * queryCmsExpertRecommendTest:(测试广告查询). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    @Test
    public void queryCmsRecommendTest() {
        CmsRecommendReqDTO dto = new CmsRecommendReqDTO();
        dto.setId(1L);
        cmsRecommendRSV.queryCmsRecommend(dto);
    }

    /**
     * queryCmsExpertRecommendPageTest:(测试广告查询). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    @Test
    public void queryCmsRecommendPageTest() {
        CmsRecommendReqDTO dto = new CmsRecommendReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        cmsRecommendRSV.queryCmsRecommendPage(dto);
    }

    /**
     * changeStatusCmsExpertRecommendTest:(测试广告生效失效). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    @Test
    public void changeStatusCmsRecommendTest() {
        String id = "3";
        String status = "1";
        cmsRecommendRSV.changeStatusCmsRecommend(id, status);
    }

    /**
     * changeStatusCmsExpertRecommendBatch:(测试广告批量生效失效). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    @Test
    public void changeStatusCmsRecommendBatchTest() {
        List<String> list = new ArrayList<String>();
        list.add("3");
        list.add("2");
        String status = "1";
        cmsRecommendRSV.changeStatusCmsRecommendBatch(list, status);
    }

    /**
     * deleteCmsExpertRecommendTest:(测试广告删除). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    @Test
    public void deleteCmsRecommendTest() {
        String id = "3";
        cmsRecommendRSV.deleteCmsRecommend(id);
    }

    /**
     * deleteCmsExpertRecommend:(测试广告批量删除). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @since JDK 1.6
     */
    @Test
    public void deleteCmsRecommendBatchTest() {
        List<String> list = new ArrayList<String>();
        list.add("3");
        list.add("2");
        cmsRecommendRSV.deleteCmsRecommendBatch(list);
    }

}
