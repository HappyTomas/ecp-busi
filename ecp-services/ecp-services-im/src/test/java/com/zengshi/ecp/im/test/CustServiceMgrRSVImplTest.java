package com.zengshi.ecp.im.test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.PriorityQueue;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.zengshi.ecp.im.dao.model.ImCustInfo;
import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.CacheUtil;

public class CustServiceMgrRSVImplTest extends EcpServicesTest{
	
	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;
	
	@Test
	public void testCleanCache(){
		custServiceMgrRSV.cleanImCache();
	}
	
	@Test
	public void testFindCustCodes(){
		String csaCode = "csa_lm";
		List<String> custCodes = custServiceMgrRSV.findCustCodes(csaCode);
		System.out.println("==========买家会员编号start============");
		if(CollectionUtils.isNotEmpty(custCodes)){
			for(String custCode: custCodes){
				System.out.println(custCode);
			}
		}
		System.out.println("==========买家会员编号end============");
	}
	
	@Test
	public void testIsCustJoinIn(){
		String ofStaffCode = "user_dongyt2";
		ImCustReqDTO custReqDTO = new ImCustReqDTO();
		custReqDTO.setOfStaffCode(ofStaffCode);
		boolean isCustJoinIn = custServiceMgrRSV.isCustJoinIn(custReqDTO);
		System.out.println("==========用户【"+ ofStaffCode +"】是否已经接入客服============"+isCustJoinIn);
	}
	
	@Test
	public void testStaffLogin(){
		ImStaffHotlineReqDTO reqDTO = new ImStaffHotlineReqDTO();
		String csaCode = "csa_lm";
		reqDTO.setCsaCode(csaCode);
		custServiceMgrRSV.staffLogin(reqDTO);
	}
	
	@Test
	public void testGetStaffHotline(){
		ImCustReqDTO reqDTO = new ImCustReqDTO();
		String ofStaffCode = "user_dongyt4";
		reqDTO.setOfStaffCode(ofStaffCode);
		reqDTO.setShopId(100L);
		reqDTO.setBusinessType(0L);
		reqDTO.setCustLevel("1");
		ImStaffHotlineResDTO resDTO = custServiceMgrRSV.getStaffHotline(reqDTO);
		System.out.println("============waitCount====="+ resDTO.getWaitCount());
	}
	
	@Test
	public void testRollInRollOut(){
		ImCustReqDTO reqDTO = new ImCustReqDTO();
		String ofStaffCode = "user_dongyt4";
		String srcCsaCode = "csa_admin";
		String destCsaCode = "csa_lm";
		reqDTO.setOfStaffCode(ofStaffCode);
		reqDTO.setSrcCsaCode(srcCsaCode);
		reqDTO.setDestCsaCode(destCsaCode);
		custServiceMgrRSV.rollInRollOut(reqDTO);
	}
	
	public static void main(String[] args) throws Exception {
		/*Integer count = (Integer)CacheUtil.hgetItem("ECP.IM.STAFF.CURR_LINE.COUNT35", "csa_lm");
		Integer max = (Integer)CacheUtil.hgetItem("ECP.IM.STAFF.MAX.SERVICE.COUNT35", "csa_lm");
		System.out.println("count="+count);
		System.out.println("max="+max);*/
		PriorityQueue<ImCustInfo> custQueue = (PriorityQueue<ImCustInfo>)CacheUtil.hgetItem("ECP.IM.CUST.QUEUE", "1094");
		System.out.println("======================begin======================");
		for(ImCustInfo custInfo: custQueue){
			System.out.println(BeanUtils.describe(custInfo));
		}
		System.out.println("======================end======================");
	}

}

