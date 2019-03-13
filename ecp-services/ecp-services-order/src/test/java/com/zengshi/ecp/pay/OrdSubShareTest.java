package com.zengshi.ecp.pay;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.order.dubbo.dto.OrdSubShareReq;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareResp;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubShareSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class OrdSubShareTest extends EcpServicesTest{

	@Resource
	private IOrdSubShareSV ordSubShareSV;
	
	@Test
	public void testQuery(){
		OrdSubShareReq req = new OrdSubShareReq();
		req.setOrderId("RW17022000057992");
		req.setStatus(OrdConstants.ShareStatus.NODO);
		List<OrdSubShareResp> list = ordSubShareSV.queryOrdSubShareList(req);
		System.out.println(list.size());
	}
}
