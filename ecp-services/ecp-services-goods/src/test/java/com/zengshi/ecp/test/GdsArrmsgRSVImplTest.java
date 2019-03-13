/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCategorySVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015年8月13日下午5:09:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsArrmsg;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsArrmsgRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月13日下午5:09:04  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsArrmsgRSVImplTest extends EcpServicesTest{
    @Resource(name="gdsArrmsgRSV")
	private IGdsArrmsgRSV gdsArrmsgRSV;
    
    @Test
    public void testSave(){
    	GdsArrmsg record = new GdsArrmsg();
    	record.setShopId(111L);
    	record.setGdsId(12L);
    	record.setMobile("11111111111");
    	record.setStaffId(0L);
    	
    	GdsArrmsgReqDTO dto = GdsUtils.doObjConvert(record, GdsArrmsgReqDTO.class, null, null);
    	gdsArrmsgRSV.saveGdsArrmsg(dto);
    }
    @Test
    public void testPagingQuery(){
    	
    	GdsArrmsgReqDTO reqDTO = new GdsArrmsgReqDTO();
    	reqDTO.setPageNo(1);
    	reqDTO.setPageSize(Integer.MAX_VALUE);
    	
    	PageResponseDTO<GdsArrmsgRespDTO> page =  gdsArrmsgRSV.queryGdsArrmsg(reqDTO);
    	System.out.println(page.getCount());
    			
    }
    
    
    
}

