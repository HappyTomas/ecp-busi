package com.zengshi.ecp.test.client.gdssend;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfSendLogRSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yammer.metrics.stats.UniformSample;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午4:15:16 
* @version 1.0 
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dubbo/client/dubbo-test-unpf.xml")
public class UnpfSendLogRSVITest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IUnpfSendLogRSV unpfSendLogRSV;
	
	
	@Test
	public void testSave(){
		UnpfSendLogReqDTO unpfSendLogReqDTO = new UnpfSendLogReqDTO();
		unpfSendLogReqDTO.setShopAuthId(65l);
		unpfSendLogReqDTO.setPlatType("taobao");
		unpfSendLogReqDTO.setShopId(3000l);
		unpfSendLogReqDTO.setAction("1");
		unpfSendLogRSV.saveGdsSendLog(unpfSendLogReqDTO);
	}
	

	@Test
	public void testQuery(){
		UnpfSendLogReqDTO unpfSendLogReqDTO = new UnpfSendLogReqDTO();
		unpfSendLogReqDTO.setId(2l);
		UnpfSendLogRespDTO unpfSendLogRespDTO = unpfSendLogRSV.queryGdsSendLogById(unpfSendLogReqDTO);
		System.out.println(unpfSendLogRespDTO.getPlatType());
		System.out.println(unpfSendLogRespDTO.getAction());
		System.out.println(unpfSendLogRespDTO.getShopAuthId());
		System.out.println(unpfSendLogRespDTO.getShopId());
	}
	
	

	@Test
	public void testQueryForPage(){
		UnpfSendLogReqDTO unpfSendLogReqDTO = new UnpfSendLogReqDTO();
		unpfSendLogReqDTO.setPageSize(5);
		unpfSendLogReqDTO.setPageNo(2);
		PageResponseDTO<UnpfSendLogRespDTO> unpfSendLogPage = unpfSendLogRSV.queryGdsSendLogForPage(unpfSendLogReqDTO);
		if(unpfSendLogPage!=null&&!CollectionUtils.isEmpty(unpfSendLogPage.getResult())){
			for (UnpfSendLogRespDTO unpfSendLogRespDTO : unpfSendLogPage.getResult()) {
				/*System.out.println(unpfSendLogRespDTO.getPlatType());
				System.out.println(unpfSendLogRespDTO.getAction());
				System.out.println(unpfSendLogRespDTO.getShopAuthId());
				System.out.println(unpfSendLogRespDTO.getShopId());*/
				System.out.println(unpfSendLogRespDTO.getId());

			}
		}
	}
	
}


