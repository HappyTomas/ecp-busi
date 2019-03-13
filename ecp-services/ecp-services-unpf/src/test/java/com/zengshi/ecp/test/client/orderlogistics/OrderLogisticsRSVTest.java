package com.zengshi.ecp.test.client.orderlogistics;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrderLogisticsRSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-unpf.xml")
public class OrderLogisticsRSVTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IOrderLogisticsRSV orderLogisticsRSV;
	
	@Test
	public void testQueryLogisticsCompany(){
		OrderLogisticsReqDTO orderLogisticsReqDTO = new OrderLogisticsReqDTO();
		orderLogisticsReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
		orderLogisticsReqDTO.setAppkey("1023521935");
		orderLogisticsReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
		orderLogisticsReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
		orderLogisticsReqDTO.setAccessToken("62015307e1f8dea5818e80ec7404941c3dbfb9ZZ7a044c02074082786");

		orderLogisticsReqDTO.setShopId(100L);
		orderLogisticsReqDTO.setAuthId(100L);
		orderLogisticsRSV.queryLogisticsCompany(orderLogisticsReqDTO);
	}
}
