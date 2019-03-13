package com.zengshi.ecp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class CmsGdsCategoryRSVImplTest extends EcpServicesTest{

	@Resource
	private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
	
	 @Test
	public void addCmsCategory() {
		CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
		reqDTO.setCatgName("最新测试1");
		reqDTO.setSortNo(2);
		reqDTO.setCatgParent("9002");
		CmsGdsCategoryRespDTO respDTO = cmsGdsCategoryRSV.addCmsGdsCategory(reqDTO);
		return;
	}
    
//	 @Test
    public void deleteCmsCategory() {
		String id = "10000";
		cmsGdsCategoryRSV.deleteCmsGdsCategory(id);
//		CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
//		CmsGdsCategoryRespDTO respDTO = new CmsGdsCategoryRespDTO();
//		reqDTO.setCatgId(id);
//		respDTO = cmsGdsCategoryRSV.queryCmsGdsCategory(reqDTO);
		return;
	}
    
//	 @Test
    public void changeCmsCategoryStatus() {
		cmsGdsCategoryRSV.changeStatusCmsGdsCategory("200", "1");
	}
    
	/** 
     * queryCmsPlaceTest:(测试内容位置查询). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * 
     * @author   
     * @since JDK 1.6 
     */ 
//    @Test
    public void queryCmsCategorySonsTest() {
        CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
        dto.setId("10");
        dto.setStatus("1");
        List<CmsGdsCategoryRespDTO> list = cmsGdsCategoryRSV.queryCmsCategorySons(dto);
        return;
    }
    
//    @Test
    public void queryCmsCategoryInit(){
    	List<CmsGdsCategoryRespDTO> list = cmsGdsCategoryRSV.queryCmsGdsCategoryInit();
    	return;
    }
}
