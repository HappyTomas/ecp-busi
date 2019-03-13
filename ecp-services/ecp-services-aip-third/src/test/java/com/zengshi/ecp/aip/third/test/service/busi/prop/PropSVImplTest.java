package com.zengshi.ecp.aip.third.test.service.busi.prop;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.PropReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.aip.third.service.busi.prop.interfaces.IPropSV;
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
public class PropSVImplTest extends EcpServicesTest {

    @Resource
    private IPropSV defaultPropSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException { 
    	
    }
    //淘宝调用测试分类
    @Test
    public void testTaobao() throws BusinessException {
    	PropReqDTO propReqDTO=new PropReqDTO();
    	propReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	propReqDTO.setAppkey("1023521935");
    	
    	propReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	
    	propReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	
    	//catgReqDTO.setAuthCode("wTkdIR6QCfhEmeWnObHjWdp44777");
    	propReqDTO.setAuthCode("TnRZ84ImOhqoNW2YqYzrLFUa4789");
    	propReqDTO.setAuthUrl("https://oauth.tbsandbox.com/token");
    	propReqDTO.setRedirectUri("http://shoptest1.pmph.com:19419/ecp-web-mall");
    	
    	propReqDTO.setAccessToken("6200623c3399a35d58e68225533178bdfc31708275c7f772076226623");
    	
    	propReqDTO.setOutCatgCode("50011992");
    	
    	PropsRespDTO value=defaultPropSV.fetch(propReqDTO);
    	System.out.println(value);
    }
    //淘宝调用测试分类和属性
    @Test
    public void testTaobaoPropValue() throws BusinessException { 
    	PropReqDTO propReqDTO=new PropReqDTO();
    	propReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	propReqDTO.setAppkey("23521935");
    	propReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	propReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	propReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	
    	propReqDTO.setOutCatgCode("50005715");
    	
    	//propReqDTO.setOutCatgCode("44343");
    	PropsRespDTO value=defaultPropSV.fetchPropValue(propReqDTO);
    	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	
    }
}
