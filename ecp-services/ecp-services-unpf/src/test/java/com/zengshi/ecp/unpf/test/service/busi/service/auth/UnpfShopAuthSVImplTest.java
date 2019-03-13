package com.zengshi.ecp.unpf.test.service.busi.service.auth;

import javax.annotation.Resource;
import org.junit.Test;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class UnpfShopAuthSVImplTest extends EcpServicesTest {

    @Resource
    private IUnpfShopAuthSV unpfShopAuthSV;
    
    @Test
    public void testById() throws BusinessException { 
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO=new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(6L);
    	UnpfShopAuthRespDTO resp=unpfShopAuthSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	System.out.println("ok");
    }
}
