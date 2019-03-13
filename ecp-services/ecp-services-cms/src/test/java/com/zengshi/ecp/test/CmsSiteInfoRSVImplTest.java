package com.zengshi.ecp.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class CmsSiteInfoRSVImplTest extends EcpServicesTest{

	 @Resource
	    private ICmsSiteInfoRSV cmsSiteInfoRSV;

	    /** 
	     * saveCmsSiteInfoTest:(测试  新增文章). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
	    @Test
	    public void addCmsSiteInfoTest() {
	            CmsSiteInfoReqDTO dto = new CmsSiteInfoReqDTO();
	            for(int i=0;i<2;i++){
	            	dto.setSiteInfoName("caonima"+i);
	            	dto.setSiteId(1L);
	            	dto.setStaticId("1");
	            	dto.setStatus("1");
	            	dto.setSortNo(i+1);
	            cmsSiteInfoRSV.addCmsSiteInfo(dto);
	            }
	    }
	//    
//	    /** 
//	     * updateCmsSiteInfoTest:(测试文章更新). <br/> 
//	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
//	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
//	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
//	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
//	     * 
//	     * @author huangxm9  
//	     * @since JDK 1.6 
//	     */ 
//	    @Test
	    public void updateCmsSiteInfoTest() {
	        CmsSiteInfoReqDTO dto = new CmsSiteInfoReqDTO();
	        Date date=new Date();
	        dto.setId(1L);
	        cmsSiteInfoRSV.updateCmsSiteInfo(dto);
	    }

	    /** 
	     * queryCmsSiteInfoTest:(测试文章查询). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
	    @Test
	    public void queryCmsSiteInfoTest() {
	        CmsSiteInfoReqDTO dto = new CmsSiteInfoReqDTO();
	        Date date = new Date();
//	        dto.setThisTime(new Timestamp(date.getTime()));
//	        dto.setPubTime(new Timestamp(2015-10-01));
//	        dto.setLostTime(new Timestamp(2015-12-01));
	        dto.setStatus("1");
	        List list = cmsSiteInfoRSV.queryCmsSiteInfoList(dto);
	        return;
	    }
	    
	    /** 
	     * queryCmsSiteInfoPageTest:(测试文章查询). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void queryCmsSiteInfoPageTest() {
//	        CmsSiteInfoReqDTO dto = new CmsSiteInfoReqDTO();
//	        dto.setPageNo(1);
//	        dto.setPageSize(10);
//	        cmsSiteInfoRSV.queryCmsSiteInfoPage(dto);
//	    }
	    
	    /** 
	     * changeStatusCmsSiteInfoTest:(测试文章生效失效). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void changeStatusCmsSiteInfoTest() {
//	        String id = "4003";
//	        String status = "1";
//	        cmsSiteInfoRSV.changeStatusCmsSiteInfo(id, status);
//	    }
	    
	    /** 
	     * changeStatusCmsSiteInfoBatch:(测试文章批量生效失效). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
//	     */ 
//	    @Test
//	    public void changeStatusCmsSiteInfoBatchTest() {
//	        List<String> list = new ArrayList<String>();
//	        list.add("4003");
//	        list.add("4002");
//	        String status = "1";
//	        cmsSiteInfoRSV.changeStatusCmsSiteInfoBatch(list, status);
//	    }
	//    
	    /** 
	     * deleteCmsSiteInfoTest:(测试文章删除). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void deleteCmsSiteInfoTest() {
//	        String id = "4003";
//	        cmsSiteInfoRSV.deleteCmsSiteInfo(id);;
//	    }
	//    
	    /** 
	     * deleteCmsSiteInfo:(测试文章批量删除). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author huangxm9  
	     * @since JDK 1.6 
	     */ 
//	    @Test
//	    public void deleteCmsSiteInfoBatchTest() {
//	        List<String> list = new ArrayList<String>();
//	        list.add("4003");
//	        list.add("4002");
//	        cmsSiteInfoRSV.deleteCmsSiteInfoBatch(list);
//	    }
}
