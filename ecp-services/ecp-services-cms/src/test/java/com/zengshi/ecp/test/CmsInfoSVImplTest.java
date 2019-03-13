/** 
 * Project Name:ecp-services-cms 
 * File Name:CmsInfoSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-6下午3:03:42 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015-8-6下午3:03:42  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version  
 * @since JDK 1.7
 */
public class CmsInfoSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsInfoSV iCmsInfoSV;
    
    @Test
    public void saveCmsInfoTest(){
        CmsInfoReqDTO cmsInfoDTO=new CmsInfoReqDTO();
        cmsInfoDTO.setInfoTitle("测试一下下");
        cmsInfoDTO.setStatus("1");
        cmsInfoDTO.setInfoType("02");
        cmsInfoDTO.setPubTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        cmsInfoDTO.setLostTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        cmsInfoDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        cmsInfoDTO.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        cmsInfoDTO.setStaticId("1");
        CmsInfoRespDTO id=iCmsInfoSV.saveCmsInfo(cmsInfoDTO);
        System.out.println("ID==========="+id);
    }
    
    @Test
    public void updateSmsInfoTest(){
        CmsInfoReqDTO cmsInfoDTO=new CmsInfoReqDTO();
        cmsInfoDTO.setId((long)4002);
        cmsInfoDTO.setInfoTitle("测试一下更新");
        cmsInfoDTO.setStatus("1");
        cmsInfoDTO.setInfoType("02");
        cmsInfoDTO.setPubTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        cmsInfoDTO.setStaticId("1");
        iCmsInfoSV.updateCmsInfo(cmsInfoDTO);
        
    }
    
//    @Test
//    public void deleteCmsInfoTest(){
//        long id=(long)4002;
//        iCmsInfoSV.deleteCmsInfo(id);
//    }
    
    @Test
    public void queryCmsinfosTest(){
        CmsInfoReqDTO dto=new CmsInfoReqDTO();
        //dto.setInfoTitle("更新");
        dto.setStartPubTime(DateUtil.getSysDate());
        dto.setEndPubTime(DateUtil.getSysDate());
        List<CmsInfoRespDTO> cmsInfos= iCmsInfoSV.queryCmsInfoList(dto);
        System.out.println("==================="+cmsInfos.size());
    }
    
    @Test
    public void findByPageTest(){
        PageResponseDTO<CmsInfoRespDTO> pageResponseDTO=new PageResponseDTO<>();
        CmsInfoReqDTO cmsInfoDTO=new CmsInfoReqDTO();
        cmsInfoDTO.setPageSize(2);
        cmsInfoDTO.setPageNo(2);
        pageResponseDTO=iCmsInfoSV.queryCmsInfoPage(cmsInfoDTO);
        List<CmsInfoRespDTO> list=pageResponseDTO.getResult();
        for (CmsInfoRespDTO cmsInfoRespDTO : list) {
            System.out.println("==================="+cmsInfoRespDTO.toString());
        }
    }
}

