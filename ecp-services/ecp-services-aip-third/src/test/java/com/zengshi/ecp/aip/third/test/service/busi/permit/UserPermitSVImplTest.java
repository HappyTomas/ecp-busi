package com.zengshi.ecp.aip.third.test.service.busi.permit;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicListRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.aip.third.service.busi.permit.interfaces.IUserPermitSV;
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
public class UserPermitSVImplTest extends EcpServicesTest {

    @Resource
    private IUserPermitSV defaultUserPermitSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
    	
    }
    //淘宝调用测试
    
    @Test
    public void testTaobaoCreateMsgPermit() throws BusinessException {
    	ShopTopicReqDTO shopTopicReqDTO=new ShopTopicReqDTO();
    	shopTopicReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	shopTopicReqDTO.setAppkey("1023521935");
    	
    	shopTopicReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	
    	shopTopicReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	
    	//gdsSendReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	shopTopicReqDTO.setAuthCode("wsAvvKeb4gipOEVE1bh7tte74934");
    	shopTopicReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	shopTopicReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");
    	
    	shopTopicReqDTO.setAccessToken("6100715e84a693abf059a3e18917edfa36a91aa6fcb6d4e2076226623");
    	shopTopicReqDTO.setShopId(100L);
    	shopTopicReqDTO.setTopic("taobao_item_ItemStockChanged");
    	shopTopicReqDTO.setNick("sandbox_b_16");
    	String value=defaultUserPermitSV.createPermit(shopTopicReqDTO);
    	System.out.println(value);
    }
    @Test
    public void testTaobaoCancelMsgPermit() throws BusinessException {
    	ShopTopicReqDTO shopTopicReqDTO=new ShopTopicReqDTO();
    	shopTopicReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	shopTopicReqDTO.setAppkey("1023521935");
    	
    	shopTopicReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	
    	shopTopicReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	
    	//gdsSendReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	shopTopicReqDTO.setAuthCode("wsAvvKeb4gipOEVE1bh7tte74934");
    	shopTopicReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	shopTopicReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");
    	
    	shopTopicReqDTO.setAccessToken("6100715e84a693abf059a3e18917edfa36a91aa6fcb6d4e2076226623");
    	shopTopicReqDTO.setShopId(100L);
    	shopTopicReqDTO.setTopic("taobao_item_ItemStockChanged");
    	shopTopicReqDTO.setNick("sandbox_b_16");
    	String value=defaultUserPermitSV.cancelPermit(shopTopicReqDTO);
    	System.out.println(value);
    }
    @Test
    public void testTaobaoUserMsgPermitList() throws BusinessException {
    	ShopTopicReqDTO shopTopicReqDTO=new ShopTopicReqDTO();
    	shopTopicReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	shopTopicReqDTO.setAppkey("1023521935");
    	
    	shopTopicReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	
    	shopTopicReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	
    	//gdsSendReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	shopTopicReqDTO.setAuthCode("wsAvvKeb4gipOEVE1bh7tte74934");
    	shopTopicReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	shopTopicReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");
    	
    	shopTopicReqDTO.setAccessToken("620083070eddd78d1d9d7473ccb18e7e3082dcegd46a5122054631247");
    	shopTopicReqDTO.setShopId(100L);
    	//shopTopicReqDTO.setTopic("taobao_item_ItemStockChanged");
    	shopTopicReqDTO.setNick("sandbox_c_10");
    	ShopTopicListRespDTO value=defaultUserPermitSV.userPermitList(shopTopicReqDTO);
    	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	
    }
}
