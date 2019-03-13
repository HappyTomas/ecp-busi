package com.zengshi.ecp.test.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCallBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupConsumeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeCountRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdNumBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdBackNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupBlackLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupConsumeSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupDetailSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

public class CoupUseTest extends EcpServicesTest{

	@Resource
    private ICoupBlackLimitSV coupBlackLimitSV;
	
	@Resource
    private ICoupDetailSV coupDetailSV;
	
	@Resource
    private ICoupConsumeSV coupConsumeSV;
	
	@Resource
    private ICoupInfoSV coupInfoSV;
	
//	@Test
	public void coupBlackLimitSaveTest(){
		CoupReqDTO coupReqDTO = new CoupReqDTO();
		
		CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
		coupInfoReqDTO.setId(Long.valueOf(111));
		
		List<CoupBlackLimitReqDTO> coupBlackLimitReqDTOs = new ArrayList<CoupBlackLimitReqDTO>();
		CoupBlackLimitReqDTO bean1 = new CoupBlackLimitReqDTO();
		bean1.setCategoryType("1");
		bean1.setGdsId(Long.valueOf(21));
		bean1.setStatus("1");
		
		CoupBlackLimitReqDTO bean2 = new CoupBlackLimitReqDTO();
		bean2.setCategoryType("0");
		bean2.setSkuId(Long.valueOf(21));
		bean2.setStatus("1");
		
		CoupBlackLimitReqDTO bean3 = new CoupBlackLimitReqDTO();
		bean3.setCategoryType("1");
		bean3.setGdsId(Long.valueOf(121));
		bean3.setStatus("1");
		
		coupBlackLimitReqDTOs.add(bean1);
		coupBlackLimitReqDTOs.add(bean2);
		coupBlackLimitReqDTOs.add(bean3);
		
		coupReqDTO.setCoupInfoReqDTO(coupInfoReqDTO);
		coupReqDTO.setCoupBlackLimitReqDTOs(coupBlackLimitReqDTOs);
		coupBlackLimitSV.save(coupReqDTO);
		
	}
	
//	@Test
	public void deleteStatus(){
		CoupReqDTO coupReqDTO = new CoupReqDTO();
		
		CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
		coupInfoReqDTO.setId(Long.valueOf(112));
		
		List<CoupBlackLimitReqDTO> coupBlackLimitReqDTOs = new ArrayList<CoupBlackLimitReqDTO>();
		CoupBlackLimitReqDTO bean1 = new CoupBlackLimitReqDTO();
		bean1.setCategoryType("1");
		bean1.setGdsId(Long.valueOf(21));
		bean1.setStatus("1");
		
		coupBlackLimitReqDTOs.add(bean1);
		coupReqDTO.setCoupInfoReqDTO(coupInfoReqDTO);
		coupReqDTO.setCoupBlackLimitReqDTOs(coupBlackLimitReqDTOs);
		coupBlackLimitSV.deleteStatus(coupReqDTO);
	}
	
	@Test
	public void queryCoupDetailPage(){
		CoupMeReqDTO coupMeReqDTO = new CoupMeReqDTO();
		coupMeReqDTO.setStaffId(180L);
		
		coupMeReqDTO.setPageSize(3);
		PageResponseDTO<CoupMeRespDTO> bean = coupDetailSV.queryCoupDetailPage(coupMeReqDTO);
		List<CoupMeRespDTO> beanList = bean.getResult();
		for (CoupMeRespDTO coupMeRespDTO : beanList) {
			try {
				int i = CoupUtil.daysBetween(coupMeRespDTO.getActiveTime(),DateUtil.getSysDate());
				System.out.println(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
//		Long l = coupDetailSV.queryCoupDetailCount(coupMeReqDTO);
//		int i= bean.getPageSize();
//		System.out.println(i);
	}
	
	
	/**
     * 优惠券信息查询 
     * 
     * @param CoupInfoReqDTO
     * @return
     * @throws BusinessException
     * @author lisp
     */
	@Test
	public void queryCoupInfoByCoupCode(){
		CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();//E341BN9EKA
		coupInfoReqDTO.setCoupCode("826Y710DW2");
		CoupInfoRespDTO coupInfoRespDTO = coupInfoSV.queryCoupInfoByCoupCode(coupInfoReqDTO);
		if(StringUtil.isNotEmpty(coupInfoRespDTO)){
			if(StringUtil.isNotBlank(coupInfoRespDTO.getCoupCode())){
				System.out.println(coupInfoRespDTO.getCoupName());
			}else{
				System.out.println("nll");
			}
			System.out.println("coupInfoRespDTO  nll");
		}
	}
//	@Test
	public void queryCoupConsumePage(){
		CoupConsumeReqDTO coupConsumeReqDTO = new CoupConsumeReqDTO();
		coupConsumeReqDTO.setShopId(69l);
		coupConsumeReqDTO.setOrderId("RW16011300005636");
		coupConsumeReqDTO.setPageSize(3);
		PageResponseDTO<CoupConsumeRespDTO> bean = coupConsumeSV.queryCoupConsumePage(coupConsumeReqDTO);
		if(!CollectionUtils.isEmpty(bean.getResult())){
			System.out.println(bean.getResult().get(0).toString());
		}else{
			System.out.println("ssssssssssssqqq");
		}
		
	}
	
//	@Test
	public void saveCoupGain(){
		CoupCallBackReqDTO coupCallBackReqDTO = new CoupCallBackReqDTO();
		coupCallBackReqDTO.setStaffId(180l);
		//优惠券领取的数量
		coupCallBackReqDTO.setCoupSum(5);
		coupCallBackReqDTO.setShopId(69l);
		coupCallBackReqDTO.setOrderId("RW16011000005537");
		coupCallBackReqDTO.setCustLevel("03");
		coupCallBackReqDTO.setCoupSource(CouponConstants.CoupDetail.COUP_SOURCE_20);
		String s="";
//		for(Long i=180l;i<=183;i++){
			coupCallBackReqDTO.setCoupId(230l);
			s= coupDetailSV.saveCoupGain(coupCallBackReqDTO);
			
//		}
		System.out.println( s );
	}
//	@Test
	public void queryCoupDetailList(){
		CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
		coupDetailReqDTO.setStaffId(180l);
		coupDetailReqDTO.setShopId(35l);
		coupDetailReqDTO.setSortValue(CouponConstants.CoupDetail.sort_0);
		coupDetailReqDTO.setKey("CREATE_TIME");
		Long s = 3l;
		coupDetailReqDTO.setPageNo(1);
		coupDetailReqDTO.setPageSize(s.intValue());
		List<CoupDetailRespDTO> ss = coupDetailSV.queryCoupDetailList(coupDetailReqDTO);
		
		List<CoupDetailRespDTO> ss2 = new ArrayList<CoupDetailRespDTO>();
		int i = 3;
		int j =0;
		for (CoupDetailRespDTO coupDetailRespDTO : ss) {
			ss2.add(coupDetailRespDTO);
			j+=1;
			if(j==i){
				break;
			}
		}
		System.out.println(ss2.size());
	}
//	@Test
	public void queryStaffCoup(){
		CoupOrdBackReqDTO coupOrdBackReqDTO = new CoupOrdBackReqDTO();
		coupOrdBackReqDTO.setOrderId("RW15112600002089");
		coupOrdBackReqDTO.setStaffId(180l);
		CoupOrdNumBackRespDTO coupOrdBackResp = coupDetailSV.queryStaffCoup(coupOrdBackReqDTO);
		List<OrdNumRespDTO> coupNumBeans = coupOrdBackResp.getCoupNumBeans();
		for (OrdNumRespDTO ordNumRespDTO : coupNumBeans) {
			System.out.println(ordNumRespDTO.getCoupId());
			System.out.println(ordNumRespDTO.getCoupBackNum());
		}
		System.out.println(coupOrdBackResp.getStaffId());
	}
//	@Test
	public void deductionCoup(){
		coupDetailSV.editDeductionCoup("RW15112600002089", 180l);
	}
//	@Test
	public void blockCoup(){
		CoupOrdBackRespDTO coupOrdBackRespDTO = new CoupOrdBackRespDTO();
		coupOrdBackRespDTO.setOrderId("RW16010700005476");
		coupOrdBackRespDTO.setStaffId(180l);
		List<OrdBackNumRespDTO> coupNumBeans = new ArrayList<OrdBackNumRespDTO>();
		OrdBackNumRespDTO  ordBackNumRespDTO= new OrdBackNumRespDTO();
		ordBackNumRespDTO.setCoupId(200l);
		ordBackNumRespDTO.setCoupNum(3l);
		coupNumBeans.add(ordBackNumRespDTO);
		coupOrdBackRespDTO.setCoupNumBeans(coupNumBeans);
		CoupCheckBeanRespDTO bean= coupDetailSV.editBlockCoup(coupOrdBackRespDTO);
		List<CoupDetailRespDTO> coupDetails = bean.getCoupDetails();
	}
//	@Test
	public void loseBlackCoup(){
		CoupOrdBackRespDTO coupOrdBackRespDTO = new CoupOrdBackRespDTO();
		coupOrdBackRespDTO.setOrderId("RW15122800105407");
		coupOrdBackRespDTO.setStaffId(180l);
		List<OrdBackNumRespDTO> coupNumBeans = new ArrayList<OrdBackNumRespDTO>();
		OrdBackNumRespDTO  ordBackNumRespDTO= new OrdBackNumRespDTO();
		ordBackNumRespDTO.setCoupId(146l);
		ordBackNumRespDTO.setCoupNum(6l);
		coupNumBeans.add(ordBackNumRespDTO);
		coupOrdBackRespDTO.setCoupNumBeans(coupNumBeans);
		coupDetailSV.editLoseBlackCoup(coupOrdBackRespDTO);
	}
//	@Test
	public void restorePayCoup(){
		CoupCheckBeanRespDTO coupCheckBeanRespDTO = new CoupCheckBeanRespDTO();
		List<CoupDetailRespDTO> coupDetails = new ArrayList<CoupDetailRespDTO>();
		CoupDetailRespDTO bean1 = new CoupDetailRespDTO();
		bean1.setId(489l);
		bean1.setStaffId(180l);
		bean1.setCoupValue(6500l);
		coupDetails.add(bean1);
		CoupDetailRespDTO bean2 = new CoupDetailRespDTO();
		bean2.setId(482l);
		bean2.setStaffId(180l);
		bean2.setCoupValue(6000l);
		coupDetails.add(bean2);
//		 Collections.sort(coupDetails); 
		CoupDetailRespDTO bean3 = new CoupDetailRespDTO();
		bean3.setId(486l);
		bean3.setStaffId(180l);
		bean3.setCoupValue(9000l);
		coupDetails.add(bean3);
        
		coupDetails = sortList(coupDetails,"coupValue",false);
		
         for(int i=0;i<coupDetails.size();i++)  
         {  
        	 CoupDetailRespDTO temp = coupDetails.get(i);  
             System.out.println(temp.getCoupValue());  
         }  
		
//		coupCheckBeanRespDTO.setCoupDetails(coupDetails);
//		coupDetailSV.editRestorePayCoup(coupCheckBeanRespDTO);
	}
	
	public static List sortList(List list, String propertyName, boolean isAsc) { 
        //借助commons-collections包的ComparatorUtils    
        //BeanComparator，ComparableComparator和ComparatorChain都是实现了Comparator这个接口    
        @SuppressWarnings("rawtypes")
		Comparator mycmp = ComparableComparator.getInstance();       
        mycmp = ComparatorUtils.nullLowComparator(mycmp);  //允许null 
        if(isAsc){ 
        mycmp = ComparatorUtils.reversedComparator(mycmp); //逆序       
        } 
        Comparator cmp = new BeanComparator(propertyName, mycmp);    
        Collections.sort(list, cmp);   
        return list; 
	}
	
//	@Test
	public void queryCoupInfoList(){
		List<Long> coupInfoReqBeans = new ArrayList<Long>();
		coupInfoReqBeans.add(131l);
		coupInfoReqBeans.add(142l);
		coupInfoReqBeans.add(223l);
		List<CoupInfoRespDTO> beans = coupInfoSV.queryCoupInfoList(coupInfoReqBeans);
		for (CoupInfoRespDTO coupInfoRespDTO : beans) {
			System.out.println(coupInfoRespDTO.toString());
		}
	}
}

