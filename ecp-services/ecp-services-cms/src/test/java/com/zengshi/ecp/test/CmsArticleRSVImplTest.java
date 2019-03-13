package com.zengshi.ecp.test;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.test.EcpServicesTest;


	/**
	 * Title: ECP <br>
	 * Project Name:ecp-services-cms <br>
	 * Description: <br>
	 * Date:2015年8月5日下午10:25:12  <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author huangxm9
	 * @version  
	 * @since JDK 1.6 
	 */  
	public class CmsArticleRSVImplTest extends EcpServicesTest{
	    
	    @Resource
	    private ICmsArticleRSV cmsArticleRSV;
	    
	    /** 
	     * importOldNewToArticleTest:(这里用一句话描述这个方法的作用). <br/> 
	     * TODO(将旧官网数据导入文章表).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author jiangzh  
	     * @throws UnsupportedEncodingException 
	     * @since JDK 1.6 
	     */ 
	    @Test
        public void importOldNewToArticleTest() throws UnsupportedEncodingException {
	        
	        cmsArticleRSV.inportOldWenToArticle();
	        
	    }
	    

	    /** 
	     * saveCmsArticleTest:(测试  新增文章). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
	    public void addCmsArticleTest() {
	            CmsArticleReqDTO dto = new CmsArticleReqDTO();
	            Date date=new Date();
	            for(int i=0;i<2;i++){
	            dto.setArticleTitle("测试的"+i+"");
	            dto.setIstop("0");
	            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
//	            dto.setThisTime(new Timestamp(date.getTime()));
	            dto.setPubTime(new Timestamp(2015-10-1));
	            dto.setLostTime(new Timestamp(date.getTime()));
	            dto.setStaticId("11100");
	            dto.setSiteId(1L);
	            dto.setChannelId(11L);
	            dto.setThumbnail("11");
//	            dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//	            dto.setCreateStaff(dto.getCreateStaff());  
	            cmsArticleRSV.addCmsArticle(dto);
	            }
	    }
	//    
//	    /** 
//	     * updateCmsArticleTest:(测试文章更新). <br/> 
//	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
//	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
//	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
//	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
//	     * 
//	     * @author huangxm9  
//	     * @since JDK 1.6 
//	     */ 
//	    @Test
	    public void updateCmsArticleTest() {
	        CmsArticleReqDTO dto = new CmsArticleReqDTO();
	        Date date=new Date();
	        dto.setId(1L);
	        dto.setArticleTitle("测试的xiugai");
            dto.setIstop("1");
            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
//            dto.setThisTime(new Timestamp(date.getTime()));
            dto.setPubTime(new Timestamp(2015-10-1));
            dto.setLostTime(new Timestamp(date.getTime()));
            dto.setStaticId("11100");
            dto.setSiteId(1L);
            dto.setChannelId(11L);
            dto.setThumbnail("11");
	        cmsArticleRSV.updateCmsArticle(dto);
	    }

	    /** 
	     * queryCmsArticleTest:(测试文章查询). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
	    @Test
	    public void queryCmsArticleTest() {
	        CmsArticleReqDTO dto = new CmsArticleReqDTO();
	        Date date = new Date();
//	        dto.setThisTime(new Timestamp(date.getTime()));
//	        dto.setPubTime(new Timestamp(2015-10-01));
//	        dto.setLostTime(new Timestamp(2015-12-01));
	        dto.setStatus("1");
	        List list = cmsArticleRSV.queryCmsArticleList(dto);
	        return;
	    }
	    
	    /** 
	     * queryCmsArticlePageTest:(测试文章查询). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void queryCmsArticlePageTest() {
//	        CmsArticleReqDTO dto = new CmsArticleReqDTO();
//	        dto.setPageNo(1);
//	        dto.setPageSize(10);
//	        cmsArticleRSV.queryCmsArticlePage(dto);
//	    }
	    
	    /** 
	     * changeStatusCmsArticleTest:(测试文章生效失效). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void changeStatusCmsArticleTest() {
//	        String id = "4003";
//	        String status = "1";
//	        cmsArticleRSV.changeStatusCmsArticle(id, status);
//	    }
	    
	    /** 
	     * changeStatusCmsArticleBatch:(测试文章批量生效失效). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
//	     */ 
//	    @Test
//	    public void changeStatusCmsArticleBatchTest() {
//	        List<String> list = new ArrayList<String>();
//	        list.add("4003");
//	        list.add("4002");
//	        String status = "1";
//	        cmsArticleRSV.changeStatusCmsArticleBatch(list, status);
//	    }
	//    
	    /** 
	     * deleteCmsArticleTest:(测试文章删除). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void deleteCmsArticleTest() {
//	        String id = "4003";
//	        cmsArticleRSV.deleteCmsArticle(id);;
//	    }
	//    
	    /** 
	     * deleteCmsArticle:(测试文章批量删除). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void deleteCmsArticleBatchTest() {
//	        List<String> list = new ArrayList<String>();
//	        list.add("4003");
//	        list.add("4002");
//	        cmsArticleRSV.deleteCmsArticleBatch(list);
//	    }
}
