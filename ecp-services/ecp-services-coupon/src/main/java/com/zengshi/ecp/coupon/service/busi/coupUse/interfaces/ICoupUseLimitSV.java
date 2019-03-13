package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupUseLimitRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ICoupUseLimitSV extends ICoupRuleSV{

	/**
	 * 
	 * 查询优惠券使用参数规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupUseLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupUseLimitRespDTO> queryCoupUseList(CoupUseLimitReqDTO coupUseLimitReqDTO) throws BusinessException;
	
	/**
	 * checkCoupLimit:优惠券规则校验. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartItemCommRequest
	 * @param coupId
	 * @param custLevelValue
	 * @param source
	 * @return 1:通过 0:不通过  2:多个单品组合
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CoupLimitCheckRespDTO checkCoupLimit(ROrdCartItemCommRequest  ordCartItemCommRequest ,Long coupId,String custLevelValue,String source,String ruleCode)throws BusinessException;
	
	
}

