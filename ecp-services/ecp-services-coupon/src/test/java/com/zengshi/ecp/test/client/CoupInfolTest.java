package com.zengshi.ecp.test.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-coupon.xml")
public class CoupInfolTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
    private ICoupRSV coupRSV;
	
	/**
     * queryCoupInfoByCoupCode 优惠券信息查询 
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
		CoupInfoRespDTO coupInfoRespDTO = coupRSV.queryCoupInfoByCoupCode(coupInfoReqDTO);
		if(StringUtil.isNotEmpty(coupInfoRespDTO)){
			if(StringUtil.isNotBlank(coupInfoRespDTO.getCoupCode())){
				System.out.println(coupInfoRespDTO.getCoupName());
			}else{
				System.out.println("nll");
			}
			System.out.println("coupInfoRespDTO  nll");
		}
		System.out.println("coupInfoRespDTO  nll");
	}
	
	/**
     * 优惠券信息查询 分页
     * 
     * @param CoupInfoReqDTO
     * @return
     * @throws BusinessException
     * @author huanghe5
     */
//	@Test
	public void queryCoupInfoPage() {
		CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
		coupInfoReqDTO.setShopId(Long.valueOf(1111));
		PageResponseDTO<CoupInfoRespDTO> pageBeans;
		try {
			pageBeans = coupRSV.queryCoupInfoPage(coupInfoReqDTO);
			List<CoupInfoRespDTO> beans = pageBeans.getResult();
			if(!CollectionUtils.isEmpty(beans)){
				for (CoupInfoRespDTO coupInfoRespDTO : beans) {
					System.out.println(coupInfoRespDTO.getId()+":"+coupInfoRespDTO.getCoupName());
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void updateTest(){
		
		CoupReqDTO coupReqDTO= new CoupReqDTO();
		
		coupRSV.saveEditCoup(coupReqDTO);
		
	}
	
//	@Test
	public void saveTest(){
		
		CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
		coupInfoReqDTO.setSiteId(11l);
		
	}
	
//	@Test
	public void ifInvalidCoup(){
		CoupReqDTO coupReqDTO = new CoupReqDTO();
		List<CoupInfoReqDTO> coupInfoReqDTOs = new ArrayList<CoupInfoReqDTO>();
		CoupInfoReqDTO bean = new CoupInfoReqDTO();
		bean.setId(28l);
		coupInfoReqDTOs.add(bean);
		coupReqDTO.setCoupInfoReqDTOs(coupInfoReqDTOs);
		coupRSV.validCoup(coupReqDTO);
	}

	
}

