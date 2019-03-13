package com.zengshi.ecp.unpf.test.service.busi.service.catg;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.unpf.service.busi.catg.interfaces.IUnpfCatgSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CatgSVImplTest extends EcpServicesTest {

    @Resource
    private IUnpfCatgSV unpfCatgSV;
  //分类保存测试
    @Test
    public void testCatg() throws BusinessException { 
    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setAuthId(8L);
    	unpfCatgSV.saveCatg(catgReqDTO);
    	System.out.println("ok");
    }
    //分类 属性 属性值 测试
    @Test
    public void testCatgAnpProp() throws BusinessException { 
    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setAuthId(8L);
    	unpfCatgSV.saveCatgAndProp(catgReqDTO);
    	System.out.println("ok");
    } 
}
