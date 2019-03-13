package com.zengshi.ecp.test.client.gdssend;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfShopAuthTopicRSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;


/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 上午9:55:42 
* @version 1.0 
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dubbo/client/dubbo-test-unpf.xml")
public class UnpfShopAuthTopicRSVTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IUnpfShopAuthTopicRSV unpfShopAuthTopicRSV;
	
	@Test
	public void testSave(){
		UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
		unpfShopAuthTopicReqDTO.setShopAuthId(24l);
		unpfShopAuthTopicReqDTO.setPlatType("taobao");
		unpfShopAuthTopicReqDTO.setShopId(3000l);
		unpfShopAuthTopicReqDTO.setTopic("超级算法");
		unpfShopAuthTopicReqDTO.setStatus("1");
		unpfShopAuthTopicReqDTO.setIfValid("合格");
		//unpfShopAuthTopicReqDTO.setNick("授权消息简介");
		unpfShopAuthTopicRSV.saveShopAuthTopic(unpfShopAuthTopicReqDTO);
	}
	@Test
	public void testQuery(){
		UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
		unpfShopAuthTopicReqDTO.setId(3l);
	    UnpfShopAuthTopicRespDTO unpfShopAuthTopicRespDTO =	unpfShopAuthTopicRSV.queryShopAuthTopicById(unpfShopAuthTopicReqDTO);
	
	    System.out.println(unpfShopAuthTopicRespDTO.getShopAuthId());
	    System.out.println(unpfShopAuthTopicRespDTO.getTopic());
	    System.out.println(unpfShopAuthTopicRespDTO.getPlatType());
	}
	
	@Test
	public void testQueryForPage(){
		UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
		unpfShopAuthTopicReqDTO.setPageNo(2);
		unpfShopAuthTopicReqDTO.setPageSize(10);
		/*unpfShopAuthTopicReqDTO.setPlatType("jd");
		unpfShopAuthTopicReqDTO.setStatus("1");
		unpfShopAuthTopicReqDTO.setShopId(1020l);*/
	    PageResponseDTO<UnpfShopAuthTopicRespDTO> unpfShopAuthTopicPage =	unpfShopAuthTopicRSV.queryShopAuthTopicForPage(unpfShopAuthTopicReqDTO);
	   if(unpfShopAuthTopicPage!=null&&!CollectionUtils.isEmpty(unpfShopAuthTopicPage.getResult())){
		   
		   for (UnpfShopAuthTopicRespDTO unpfShopAuthTopicRespDTO : unpfShopAuthTopicPage.getResult()) {
			   System.out.println(unpfShopAuthTopicRespDTO.getId());
			   System.out.println(unpfShopAuthTopicRespDTO.getTopic());
			   System.out.println(unpfShopAuthTopicRespDTO.getPlatType());
		}
	   }
	}
	
	@Test
	public void testUpdate(){
		UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
		unpfShopAuthTopicReqDTO.setId(3l);
		unpfShopAuthTopicReqDTO.setShopId(100l);
		unpfShopAuthTopicReqDTO.setStatus("1");
		unpfShopAuthTopicReqDTO.setTopic("主题是什么");
		unpfShopAuthTopicReqDTO.setIfValid("合格不");
	   unpfShopAuthTopicRSV.updateShopAuthTopicByExample(unpfShopAuthTopicReqDTO);
	}
}


