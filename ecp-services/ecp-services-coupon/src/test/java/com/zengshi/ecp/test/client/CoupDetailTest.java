package com.zengshi.ecp.test.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.xerces.impl.xs.SchemaSymbols;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCodeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeCountRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.StringUtil;


public class CoupDetailTest extends EcpServicesTest{
	@Resource
    private ICoupDetailRSV coupDetailRSV; 
	
//	@Test
	public void deleteCoupDetail(){
		CoupMeReqDTO coupMeReqDTO = new CoupMeReqDTO();
		coupMeReqDTO.setId(14l);
		coupMeReqDTO.setSiteId(1l);
		coupDetailRSV.deleteCoupDetail(coupMeReqDTO);
		
	}
	
	@Test
	public void queryCoupDetail(){
		
		CoupMeReqDTO coupMeReqDTO = new CoupMeReqDTO();
		coupMeReqDTO.setStaffId(182l);
		coupMeReqDTO.setOpeType("2");
		CoupMeCountRespDTO bean = coupDetailRSV.queryCoupDetail(coupMeReqDTO);
		
		PageResponseDTO<CoupMeRespDTO> beanPage = bean.getBeanPage();
		List<CoupMeRespDTO> beanPageBean =   beanPage.getResult();
		System.out.println(beanPageBean.size());
	}
	
	   @Test
	    public void editCoupStatus(){
	       
	        coupDetailRSV.editCoupStatus();

	   }
	   @Test
		public void blockCoup(){
		   CoupOrdBackRespDTO coupOrdBackRespDTO = new CoupOrdBackRespDTO();
		   coupOrdBackRespDTO.setapplyId(6600313l);
		   coupOrdBackRespDTO.setOrderId("RW16092306602629");
		   coupOrdBackRespDTO.setStaffId(3026l);
		   coupDetailRSV.blockCoup(coupOrdBackRespDTO);
	   }
	
	   
	   @Test
		public void  queryOrdCheckCoupByCode(){
		   ROrdCartCommRequest ordCartCommRequest = new ROrdCartCommRequest();
		   ordCartCommRequest.setCoupCode("939M7U1UWW");// RXTU1RUCPS黑名单优惠券    2TYPAVPG09白名单  939M7U1UWW 全都有
		   ROrdCartItemCommRequest ordCartItemCommRequest = new ROrdCartItemCommRequest();
		   ordCartItemCommRequest.setDiscountMoney(3100l);
		   ordCartItemCommRequest.setGdsId(37895l);
		   ROrdCartItemCommRequest ordCartItemCommRequest1 = new ROrdCartItemCommRequest();
		   ordCartItemCommRequest1.setGdsId(37894l);
		   ordCartItemCommRequest1.setDiscountMoney(4000l);
		   ROrdCartItemCommRequest ordCartItemCommRequest2 = new ROrdCartItemCommRequest();
		   ordCartItemCommRequest2.setGdsId(46533l);
		   ordCartItemCommRequest2.setDiscountMoney(4500l);
		   ROrdCartItemCommRequest ordCartItemCommRequest3 = new ROrdCartItemCommRequest();
		   ordCartItemCommRequest3.setGdsId(37778l);
		   ordCartItemCommRequest3.setDiscountMoney(4000l);
		   List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
		   //ordCartItemCommList.add(ordCartItemCommRequest1);
		   ordCartItemCommList.add(ordCartItemCommRequest);
		   ordCartItemCommList.add(ordCartItemCommRequest2);
		   //ordCartItemCommList.add(ordCartItemCommRequest3);
		   ordCartCommRequest.setOrdCartItemCommList(ordCartItemCommList);
		   ordCartCommRequest.setShopId(70l);
		   ordCartCommRequest.setSource("1");
		   CoupCodeRespDTO coupCodeRespDTO = coupDetailRSV.queryOrdCheckCoupByCode(ordCartCommRequest);
		   System.out.println(coupCodeRespDTO.getCoupCode());		
		   System.out.println(coupCodeRespDTO.getCoupName());
		   System.out.println(coupCodeRespDTO.getCoupTypeName());
		   System.out.println(coupCodeRespDTO.getIfCanUse());
		   
	   }
	   
	   
	   @Test
	   public void  checkOrder(){
		   ROrdCartsCommRequest ordCartsCommRequest = new ROrdCartsCommRequest();

		   ROrdCartCommRequest ordCartCommRequest = new ROrdCartCommRequest();
		   ROrdCartCommRequest ordCartCommRequest1 = new ROrdCartCommRequest();

		   ordCartCommRequest.setCoupCode("43KX101Q5Q");// RXTU1RUCPS黑名单优惠券    2TYPAVPG09白名单  43KX101Q5Q 全都有
		   ordCartCommRequest1.setCoupCode("2TYPAVPG09");
		   ROrdCartItemCommRequest ordCartItemCommRequest = new ROrdCartItemCommRequest();
		   
		   ordCartItemCommRequest.setDiscountMoney(3100l);
		   ordCartItemCommRequest.setGdsId(37895l);
		   
		   ROrdCartItemCommRequest ordCartItemCommRequest1 = new ROrdCartItemCommRequest();
		   ordCartItemCommRequest1.setGdsId(37895l);
		   ordCartItemCommRequest1.setDiscountMoney(4000l);
		   
		   ROrdCartItemCommRequest ordCartItemCommRequest2 = new ROrdCartItemCommRequest();
		   ordCartItemCommRequest2.setGdsId(46533l);
		   ordCartItemCommRequest2.setDiscountMoney(4500l);
		   
		   ROrdCartItemCommRequest ordCartItemCommRequest3 = new ROrdCartItemCommRequest();
		   ordCartItemCommRequest3.setGdsId(42731l);
		   ordCartItemCommRequest3.setDiscountMoney(4000l);
		   
		   List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
		   List<ROrdCartItemCommRequest> ordCartItemCommList1 = new ArrayList<ROrdCartItemCommRequest>();

		   ordCartItemCommList.add(ordCartItemCommRequest);
		   ordCartItemCommList.add(ordCartItemCommRequest2);
		   
		   ordCartItemCommList1.add(ordCartItemCommRequest1);
		   ordCartItemCommList1.add(ordCartItemCommRequest3);
		   
		   ordCartCommRequest.setOrdCartItemCommList(ordCartItemCommList);
		   ordCartCommRequest1.setOrdCartItemCommList(ordCartItemCommList1);
		   
		   ordCartCommRequest.setShopId(70l);
		   ordCartCommRequest.setSource("1");
		   ordCartCommRequest1.setShopId(70l);
		   ordCartCommRequest1.setSource("1");
		   
		   List<ROrdCartCommRequest> ordCartCommList = new ArrayList<ROrdCartCommRequest>();
		   ordCartCommList.add(ordCartCommRequest);
		   ordCartCommList.add(ordCartCommRequest1);
		   
		   Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap = new HashMap<Long,CoupCheckInfoRespDTO>();
		   CoupCheckInfoRespDTO coupCheckInfoRespDTO = new CoupCheckInfoRespDTO();
		   coupCheckInfoRespDTO.setCoupCheckBeanRespDTO(null);
		   coupIdskuIdMap.put(22l, coupCheckInfoRespDTO);

		   ordCartsCommRequest.setOrdCartsCommList(ordCartCommList);
		   ordCartsCommRequest.setStaffId(70l);
		   ordCartsCommRequest.setCoupIdskuIdMap(coupIdskuIdMap);
		   
		   List<CoupCheckBeanRespDTO> coupCheckshopBean = new ArrayList<CoupCheckBeanRespDTO>();
		   CoupCheckBeanRespDTO coupCheckBeanRespDTO = new CoupCheckBeanRespDTO();
		   coupCheckBeanRespDTO.setCoupId(24523l);
		   coupCheckshopBean.add(coupCheckBeanRespDTO);
		   
		   ordCartCommRequest.setCoupCheckBean(coupCheckshopBean);
		   ordCartCommRequest1.setCoupCheckBean(coupCheckshopBean);
		   
		   ROrdCartsChkResponse ordCartsChkResponse = coupDetailRSV.checkOrder(ordCartsCommRequest);
		   System.out.println(ordCartsChkResponse);
	   }
	   
	   
	   @Test
	   public void  cehek(){
		   List<CoupCheckBeanRespDTO> coupCheckshopBean = new ArrayList<CoupCheckBeanRespDTO>();
		   if(!CollectionUtils.isEmpty(coupCheckshopBean)&&coupCheckshopBean.size()!=0){
			   System.out.println("ok");
		   }else{
			   System.out.println("failue");
		   }
	   }
	   
}

