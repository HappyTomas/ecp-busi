package com.zengshi.ecp.test.client.cfg;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopCfgRSV;

/**
* @author  lisp: 
* @date 创建时间：2016年11月25日 下午2:29:20 
* @version 1.0 
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-unpf.xml")
public class UnpfShopCfgTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IUnpfShopCfgRSV unpfShopCfgRSV;
	
	@Test
	public void testSave(){
		UnpfShopCfgReqDTO unpfShopCfgReqDTO= new UnpfShopCfgReqDTO();
		unpfShopCfgReqDTO.setShopAuthId(7l);
		unpfShopCfgReqDTO.setShopId(100l);
		unpfShopCfgReqDTO.setPlatType("taobao");
		unpfShopCfgReqDTO.setInputValue("毛绒小熊台灯");
		unpfShopCfgRSV.saveShopCfg(unpfShopCfgReqDTO);
	}
	@Test
	public void testUpdate(){
		UnpfShopCfgReqDTO unpfShopCfgReqDTO= new UnpfShopCfgReqDTO();
		unpfShopCfgReqDTO.setId(4l);
		unpfShopCfgReqDTO.setInputValue("MUG试纸马克杯");
		unpfShopCfgRSV.updateShopCfg(unpfShopCfgReqDTO);
		}
	
	
	@Test
	public void testQueryById(){
		UnpfShopCfgReqDTO unpfShopCfgReqDTO = new UnpfShopCfgReqDTO();
		unpfShopCfgReqDTO.setId(5l);
		System.out.println(unpfShopCfgRSV.queryShopCfgById(unpfShopCfgReqDTO).getInputValue()+";");
	}
	
	@Test
	public void testQueryForPage(){
		UnpfShopCfgReqDTO unpfShopCfgReqDTO = new UnpfShopCfgReqDTO();
		unpfShopCfgReqDTO.setPageSize(10);
		unpfShopCfgReqDTO.setPageNo(1);
		PageResponseDTO<UnpfShopCfgRespDTO> list = unpfShopCfgRSV.queryShopCfgForPage(unpfShopCfgReqDTO);
		for (UnpfShopCfgRespDTO unpfShopCfgRespDTO : list.getResult()) {
			System.out.println(unpfShopCfgRespDTO.getId()+":"+unpfShopCfgRespDTO.getInputValue());
		}
	}
}


