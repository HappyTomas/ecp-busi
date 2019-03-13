package com.zengshi.ecp.test.client.catg;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.catg.IUnpfCatgRSV;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月25日 下午2:29:20 
* @version 1.0 
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-unpf.xml")
public class UnpfCatgTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IUnpfCatgRSV unpfCatgRSV;
	
	@Test
	public void saveCatgAndProp(){
		CatgReqDTO catgReqDTO=new CatgReqDTO();
    	//catgReqDTO.setAuthId(8L);
		catgReqDTO.setAuthId(44L);
		unpfCatgRSV.saveCatgAndProp(catgReqDTO);
		System.out.println("ok");
	}
}


