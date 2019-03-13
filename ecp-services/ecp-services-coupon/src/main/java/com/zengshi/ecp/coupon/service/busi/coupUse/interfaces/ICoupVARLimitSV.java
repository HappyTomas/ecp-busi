package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupVarLimitRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ICoupVARLimitSV extends ICoupRuleSV{
	
	/**
	 * 
	 * 查询优惠券共同使用规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupVarLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupVarLimitRespDTO> queryCoupVARList(CoupVarLimitReqDTO coupUseLimitReqDTO) throws BusinessException;
	
	
}

