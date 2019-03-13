/** 
 * Project Name:ecp-services-cms 
 * File Name:CmsInfoRSVImpl.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-6下午3:28:01 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015-8-6下午3:28:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version  
 * @since JDK 1.7
 */
public class CmsInfoRSVImplTest extends EcpServicesTest{
    
    @Resource
    private ICmsInfoRSV iCmsInfoRSV;
    
//    @Test
//    public void saveCmsInfoTest(){
//        CmsInfoReqDTO cmsInfoDTO=new CmsInfoReqDTO();
//        cmsInfoDTO.setInfoTitle("测试一下下");
//        cmsInfoDTO.setStatus("1");
//        cmsInfoDTO.setInfoType("02");
//        cmsInfoDTO.setPubTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//        cmsInfoDTO.setStaticId("1");
//        iCmsInfoRSV.saveCmsInfo(cmsInfoDTO);
//    }
//    
//    @Test
//    public void updateCmsInfoTest(){
//        CmsInfoReqDTO cmsInfoDTO=new CmsInfoReqDTO();
//        cmsInfoDTO.setId((long)4001);
//        cmsInfoDTO.setInfoTitle("测试一下下");
//        cmsInfoDTO.setStatus("1");
//        cmsInfoDTO.setInfoType("02");
//        cmsInfoDTO.setPubTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//        cmsInfoDTO.setStaticId("1");
//        iCmsInfoRSV.updateCmsInfo(cmsInfoDTO);
//    }
//    
//    @Test
//    public void queryCmsInfoTest(){
////        CmsInfoOutDTO cmsInforesponseDTO=iCmsInfoRSV.queryCmsInfo((long)4002);
//        CmsInfoReqDTO cmsInfoDTO=new CmsInfoReqDTO();
//        cmsInfoDTO.setId((long) 4002);
//        CmsInfoRespDTO cmsInfoOutDTO=iCmsInfoRSV.queryCmsInfo(cmsInfoDTO);
//        System.out.println("cmsInforesponseDTO================"+cmsInfoOutDTO);
//    }
//    
//    @Test
//    public void queryCmsInfosTest(){
//        CmsInfoReqDTO infoDTO=new CmsInfoReqDTO();
////        infoDTO.setInfoTitle("测试");
////        infoDTO.setPageSize(2);
////        infoDTO.setPageNo(2);
//        PageResponseDTO<CmsInfoRespDTO> result=PageResponseDTO.buildByBaseInfo(infoDTO, CmsInfoRespDTO.class); 
//        result=iCmsInfoRSV.queryCmsInfoPage(infoDTO);
//        List<CmsInfoRespDTO> list=result.getResult();
//        System.out.println("*******"+result.getCount()+"*******"+result.getPageCount()+"*******"+result.getStartRowIndex()+"***********"+result.getPageNo()+"***********"+result.getEndRowIndex());
////        for (CmsInfoOutDTO outDTO : list) {
////            System.out.println("outDTO=========="+outDTO.getId()+"   "+outDTO.getInfoTitle());
////        }
////        for (int i = result.getStartRowIndex(); i < result.getEndRowIndex(); i++) {
////            System.out.println("ID========="+list.get(i).getId()+"===== INFOTITLE===="+list.get(i).getInfoTitle());
////        }
//        for (CmsInfoRespDTO cmsInfoRespDTO : list) {
//            System.out.println("==============="+cmsInfoRespDTO.toString());
//        }
//    }

  @Test
  public void queryCmsInfosTest(){
      CmsInfoReqDTO infoDTO=new CmsInfoReqDTO();
      Date date=new Date();
      infoDTO.setThisTime(new Timestamp(date.getTime()));
      infoDTO.setPubTime(new Timestamp(2015-10-1));
      infoDTO.setLostTime(new Timestamp(2015-12-1));
//      PageResponseDTO<CmsInfoRespDTO> result=PageResponseDTO.buildByBaseInfo(infoDTO, CmsInfoRespDTO.class); 
      List list = iCmsInfoRSV.queryCmsInfoList(infoDTO);
     return;
  }
}

