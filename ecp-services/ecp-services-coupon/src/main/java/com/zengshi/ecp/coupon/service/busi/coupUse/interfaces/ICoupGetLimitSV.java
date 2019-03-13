package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupGetLimitRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ICoupGetLimitSV extends ICoupRuleSV{

	/**
	 * 
	 * 查询领取权限规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupGetLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupGetLimitRespDTO> queryCoupGetList(CoupGetLimitReqDTO coupGetLimitReqDTO) throws BusinessException;
	
}

