package com.zengshi.ecp.aip.third.test.service.busi.user;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SellerRespDTO;
import com.zengshi.ecp.aip.third.service.busi.seller.interfaces.ISellerSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class SellerSVImplTest extends EcpServicesTest {

    @Resource
    private ISellerSV defaultSellerSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
    	
    }
    //淘宝调用测试
    
    @Test
    public void testTaobao() throws BusinessException {
    	BaseShopAuthReqDTO baseShopAuthReqDTO=new BaseShopAuthReqDTO();
    	baseShopAuthReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	baseShopAuthReqDTO.setAppkey("1023521935");
    	baseShopAuthReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	baseShopAuthReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	baseShopAuthReqDTO.setAccessToken("620083070eddd78d1d9d7473ccb18e7e3082dcegd46a5122054631247");
    	baseShopAuthReqDTO.setShopId(100L);
    	baseShopAuthReqDTO.setAuthId(12L);
    	
    	SellerRespDTO value=defaultSellerSV.getSellerInfo(baseShopAuthReqDTO);
    	//B 天猫  C 淘宝
        String a=value.getType();
    	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	
    }
}
