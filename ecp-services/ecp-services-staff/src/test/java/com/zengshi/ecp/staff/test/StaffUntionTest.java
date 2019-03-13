package com.zengshi.ecp.staff.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.service.busi.union.interfaces.IStaffUnionSV;

public class StaffUntionTest extends EcpServicesTest{

	@Resource
	private IStaffUnionSV staffUnionSV;
	
	@Test
	public void testSelScore() {
		OrderBackMainReqDTO<OrderBackSubReqDTO> req = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
		OrderBackSubReqDTO sub = new OrderBackSubReqDTO();
		req.setOrderId("RW15110700001543");
		req.setStaffId(72L);
		List<OrderBackSubReqDTO> list = new ArrayList<OrderBackSubReqDTO>();
		sub.setOrderId("RW15110700001543");
		sub.setSubOrderId("SRW15110700000735");
		sub.setStaffId(72L);
		sub.setScale(700000L);
		sub.setLastFlag(true);
		list.add(sub);
		req.setList(list);
		long score = staffUnionSV.selTotalScoreByBackSubOrder(req);
		System.out.println(score);
	}
	
	@Test
	public void testFrozenScore() {
		OrderBackMainReqDTO<OrderBackSubReqDTO> req = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
		OrderBackSubReqDTO sub = new OrderBackSubReqDTO();
		req.setOrderId("RW15110700001543");
		req.setStaffId(72L);
		List<OrderBackSubReqDTO> list = new ArrayList<OrderBackSubReqDTO>();
		sub.setOrderId("RW15110700001543");
		sub.setSubOrderId("SRW15110700000735");
		sub.setStaffId(72L);
		sub.setScale(700000L);
		list.add(sub);
		req.setList(list);
		long score = staffUnionSV.saveScoreFrozenForOrderBack(req);
		System.out.println(score);
	}
	
	@Test
	public void testBackScore() {
		OrderBackMainReqDTO<OrderBackSubReqDTO> req = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
		OrderBackSubReqDTO sub = new OrderBackSubReqDTO();
		req.setOrderId("RW15110700001543");
		req.setStaffId(72L);
		List<OrderBackSubReqDTO> list = new ArrayList<OrderBackSubReqDTO>();
		sub.setOrderId("RW15110700001543");
		sub.setSubOrderId("SRW15110700000735");
		sub.setStaffId(72L);
		sub.setScale(700000L);
		sub.setLastFlag(true);
		list.add(sub);
		req.setList(list);
		OrderBackSubReqDTO acct = new OrderBackSubReqDTO();
		acct.setOrderId("RW15110700001543");
		staffUnionSV.saveScoreAcctForOrderBack(req, acct);
	}
}

