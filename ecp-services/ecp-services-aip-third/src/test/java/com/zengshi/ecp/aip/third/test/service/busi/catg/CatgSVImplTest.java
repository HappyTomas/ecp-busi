package com.zengshi.ecp.aip.third.test.service.busi.catg;

import java.io.IOException;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.dubbo.common.json.JSON;

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
    private ICatgSV defaultCatgSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException { 
    	
    }
    //淘宝调用测试分类
    @Test
    public void testTaobao() throws BusinessException {
    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("1023521935");
    	
    	catgReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	
    	catgReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	
    	//catgReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	catgReqDTO.setAuthCode("TnRZ84ImOhqoNW2YqYzrLFUa4789");
    	catgReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	catgReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");
    	
    	catgReqDTO.setAccessToken("62015307e1f8dea5818e80ec7404941c3dbfb9ZZ7a044c02074082786");
    	
    	//catgReqDTO.setOutCatgCode("");
    /*	catgReqDTO.setOutParentCatgCode("50011992");
    	*/
    	catgReqDTO.setShopId(100L);
    	CatgsRespDTO value=defaultCatgSV.fetch(catgReqDTO);
    	System.out.println(value);
    }
    
    //淘宝调用测试分类
    @Test
    public void testTaobaoPrd() throws BusinessException {
    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("23521935");
    	
    	catgReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	
    	catgReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	
/*    	//catgReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	catgReqDTO.setAuthCode("TnRZ84ImOhqoNW2YqYzrLFUa4789");
    	catgReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	catgReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");*/
    	
    	catgReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	
    	//catgReqDTO.setOutCatgCode("");
    /*	catgReqDTO.setOutParentCatgCode("50011992");
    	*/
    	catgReqDTO.setShopId(100L);
    	catgReqDTO.setAuthId(8L);
    	CatgsRespDTO value=defaultCatgSV.fetch(catgReqDTO);
    	
    	if(value!=null){
    		
    		for(CatgRespDTO c:value.getCatgs()){
    			try {
					System.out.println(JSON.json(c));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	System.out.println(value);
    }
    //淘宝调用测试分类和属性
    @Test
    public void testTaobaoCatgAndPropPrd() throws BusinessException {
    	CatgReqDTO catgReqDTO=new CatgReqDTO();
    	catgReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	catgReqDTO.setAppkey("23521935");
    	
    	catgReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	
    	catgReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	
/*    	//catgReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	catgReqDTO.setAuthCode("TnRZ84ImOhqoNW2YqYzrLFUa4789");
    	catgReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	catgReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");*/
    	
    	catgReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	
    	catgReqDTO.setShopId(100L);
    	//catgReqDTO.setOutCatgCode("");
 /*   	catgReqDTO.setOutParentCatgCode("50011992");*/
    	CatgsRespDTO value=defaultCatgSV.fetchCatgAndProp(catgReqDTO);
    	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
		CatgReqDTO catgReqDTO=new CatgReqDTO();
		catgReqDTO.setPlatType(AipThirdConstants.Commons.YOUZAN);
		catgReqDTO.setAppkey("8a4602a057fc8f8485");
//		String APP_ID = "8a4602a057fc8f8485";
//		String APP_SECRET = "5b56c136787e54e077f5d780fd0ff076";
		catgReqDTO.setAppscret("5b56c136787e54e077f5d780fd0ff076");

		catgReqDTO.setServerUrl("https://open.koudaitong.com/api/entry");

/*    	//catgReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	catgReqDTO.setAuthCode("TnRZ84ImOhqoNW2YqYzrLFUa4789");
    	catgReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	catgReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");*/

		catgReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");

		catgReqDTO.setShopId(100L);
		//catgReqDTO.setOutCatgCode("");
 /*   	catgReqDTO.setOutParentCatgCode("50011992");*/
		CatgsRespDTO value=defaultCatgSV.fetchCatgAndProp(catgReqDTO);
		System.out.println("1111:"+ com.alibaba.fastjson.JSON.toJSONString(value));
    }
}
