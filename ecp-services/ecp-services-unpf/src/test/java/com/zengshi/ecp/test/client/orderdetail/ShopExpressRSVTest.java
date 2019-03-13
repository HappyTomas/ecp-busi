package com.zengshi.ecp.test.client.orderdetail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfShopExpressRSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-unpf.xml")
public class ShopExpressRSVTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IUnpfShopExpressRSV unpfShopExpressRSV;
	@Test
	public void testShopExpressRSV(){
		OrderLogisticsReqDTO req = new OrderLogisticsReqDTO();

		req.setPlatType(AipThirdConstants.Commons.TAOBAO);
	
		req.setAppkey("1023521935");
		req.setAppscret("sandbox75de5da394607a610715e572a");
		req.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
		req.setAccessToken("62015307e1f8dea5818e80ec7404941c3dbfb9ZZ7a044c02074082786");

		req.setShopId(100L);
		req.setAuthId(7L);
		unpfShopExpressRSV.dealShopExpress(req);
	}
}
