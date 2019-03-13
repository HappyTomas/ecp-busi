package com.zengshi.ecp.unpf.test.service.busi.service.token;

import javax.annotation.Resource;
import org.junit.Test;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.service.busi.token.interfaces.IUnpfTokenSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class TokenSVImplTest extends EcpServicesTest {

    @Resource
    private IUnpfTokenSV unpfTokenSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
    	
    }
    //淘宝调用测试
    
    @Test
    public void testTaobao() throws BusinessException { 
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO=new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(6L);
    	unpfShopAuthReqDTO.setAuthCode("11");
    	unpfTokenSV.createToken(unpfShopAuthReqDTO);
    	System.out.println("ok");
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	
    }
}
