package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCatgLimitRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ICoupCatgLimitSV extends ICoupRuleSV{

	/**
	 * 
	 * 查询品类限制表.  
	 * 
	 * @author huanghe5 
	 * @param CoupCatgLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupCatgLimitRespDTO> queryCoupCatgList(CoupCatgLimitReqDTO coupCatgLimitReqDTO) throws BusinessException;
	
}

