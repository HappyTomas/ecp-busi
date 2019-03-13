package com.zengshi.ecp.test.client.gdssend;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendRSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
* @author  lisp: 
* @date 创建时间：2016年11月22日 下午2:17:18 
* @version 1.0 
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dubbo/client/dubbo-test-unpf.xml")
public class UnpfGdsUnsendRSVTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IUnpfGdsUnsendRSV unpfGdsUnsendRSV;
	
	@Test
	public void testsave(){
		UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO = new UnpfGdsUnsendReqDTO();
		unpfGdsUnsendReqDTO.setShopId(3000l);
		unpfGdsUnsendReqDTO.setGdsId(9877l);
		unpfGdsUnsendReqDTO.setStatus("0");
		unpfGdsUnsendRSV.saveGdsUnsend(unpfGdsUnsendReqDTO);
	}
	
	@Test
	public void testdelete(){
		UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO = new UnpfGdsUnsendReqDTO();
		unpfGdsUnsendReqDTO.setId(12l);
		unpfGdsUnsendRSV.deleteGdsUnsendById(unpfGdsUnsendReqDTO);
	}
	
	@Test
	public void testqueryById(){
		UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO = new UnpfGdsUnsendReqDTO();
		unpfGdsUnsendReqDTO.setId(2l);
		UnpfGdsUnsendRespDTO unpfGdsUnsendRespDTO = unpfGdsUnsendRSV.queryGdsUnsendById(unpfGdsUnsendReqDTO);
		System.out.println(unpfGdsUnsendRespDTO.getStatus());
		System.out.println(unpfGdsUnsendRespDTO.getGdsId());
		System.out.println(unpfGdsUnsendRespDTO.getShopId());
	}
	
	
	@Test
	public void testqueryForPage(){
		UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO = new UnpfGdsUnsendReqDTO();
		unpfGdsUnsendReqDTO.setPageSize(8);
		unpfGdsUnsendReqDTO.setPageNo(2);
		PageResponseDTO<UnpfGdsUnsendRespDTO> page = unpfGdsUnsendRSV.queryGdsUnsendForPage(unpfGdsUnsendReqDTO);
		if(page!=null&&!CollectionUtils.isEmpty(page.getResult())){
			for (UnpfGdsUnsendRespDTO unpfGdsUnsendRespDTO : page.getResult()) {
				System.out.println(unpfGdsUnsendRespDTO.getId());
				/*System.out.println(unpfGdsUnsendRespDTO.getStatus());
				System.out.println(unpfGdsUnsendRespDTO.getGdsId());
				System.out.println(unpfGdsUnsendRespDTO.getShopId());*/
			}
		}
		
	}
}


