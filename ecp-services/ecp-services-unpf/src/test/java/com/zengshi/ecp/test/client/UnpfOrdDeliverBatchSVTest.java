package com.zengshi.ecp.test.client;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrderShipRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dubbo/client/dubbo-test-unpf.xml")
public class UnpfOrdDeliverBatchSVTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IOrderShipRSV orderShipRSV;
	
	@Test
	public void testDeliverGoods(){
		OrderShipReqDTO orderShipReq = new OrderShipReqDTO();		
		orderShipReq.setAuthId(45L);
		orderShipReq.setAppkey("23521935");    	
		orderShipReq.setAppscret("bedf75375de5da394607a610715e572a");    	
		orderShipReq.setServerUrl("https://eco.taobao.com/router/rest");
		orderShipReq.setAccessToken("62021072e189dbceg470c7d398ed700fe0ffd4e4f2dd5e91125014918");
		
		orderShipReq.setShopId(35L);
		orderShipReq.setTid(2886838681759866L);//交易单号
		orderShipReq.setPlatType(AipThirdConstants.Commons.TAOBAO);
		orderShipReq.setOutSid("1235");//运单号
		orderShipReq.setCompanyCode("Sa11111111");//物流公司code
		try{
			orderShipRSV.ship(orderShipReq);
		}catch(BusinessException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
