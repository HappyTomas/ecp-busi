package com.zengshi.ecp.aip.third.test.service.busi.token;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
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
public class TokenSVImplTest extends EcpServicesTest {

    @Resource
    private ITokenSV defaultTokenSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
    	
    }
    //淘宝调用测试
    
    /*{"taobao_user_nick":"sandbox_b_16","re_expires_in":15552000,"expires_in":12960000,"expire_time":1491546898910,"r1_expires_in":12960000,"w2_valid":1478588698910,"w2_expires_in":1800,"taobao_user_id":"2076226623","w1_expires_in":12960000,"r1_valid":1491546898910,"r2_valid":1478846098910,"w1_valid":1491546898910,"r2_expires_in":259200,"token_type":"Bearer","refresh_token":"6200b23261b868173d7da5baf1d8eccegaa579f325c80d22076226623","refresh_token_valid_time":1494138898910,"access_token":"6200623c3399a35d58e68225533178bdfc31708275c7f772076226623"}*/
    @Test
    public void testTaobao() throws BusinessException {
    	BaseShopAuthReqDTO baseShopAuthReqDTO=new BaseShopAuthReqDTO();
    	baseShopAuthReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	baseShopAuthReqDTO.setAppkey("1023521935");
    	
    	baseShopAuthReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	
    	baseShopAuthReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	
    	//gdsSendReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	baseShopAuthReqDTO.setAuthCode("3T6XnM1726cItPVsEPla4DuM5506");
    	baseShopAuthReqDTO.setTokenUrl(/*"https://oauth.tbsandbox.com/token"*/"https://oauth.tbsandbox.com/authorize");
    	
    	baseShopAuthReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");
    	
    	baseShopAuthReqDTO.setAccessToken("62015307e1f8dea5818e80ec7404941c3dbfb9ZZ7a044c02074082786");
    	baseShopAuthReqDTO.setRefreshToken("6200d30551b9eae882f134e145a240433ebfc0ZZe8235122074082786");
    	baseShopAuthReqDTO.setShopId(100L);
    	//TokenRespDTO value=defaultTokenSV.fetchToken(baseShopAuthReqDTO);
    	
    	String value=defaultTokenSV.refreshToken(baseShopAuthReqDTO);
    	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	
    }
}
