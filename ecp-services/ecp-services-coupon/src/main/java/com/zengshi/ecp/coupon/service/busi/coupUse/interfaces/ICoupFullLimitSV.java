package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.general.order.dto.CoupSkuRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface ICoupFullLimitSV extends ICoupRuleSV{
	
	/**
	 * 
	 * 查询满减限制表
	 * 
	 * @author huanghe5 
	 * @param CoupFullLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupFullLimitRespDTO> queryCoupFullList(CoupFullLimitReqDTO coupFullLimitReqDTO) throws BusinessException;
	
	/**
	 * checkCoupLimit:下单时校验优惠券限制条件. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartCommRequest
	 * @param coupId
	 * @return 1:通过 0:不通过  2:多个单品组合
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public boolean checkCoupFullLimit(List<CoupSkuRespDTO> coupSkus,Long coupId,Long coupValue) throws BusinessException;
	
}

