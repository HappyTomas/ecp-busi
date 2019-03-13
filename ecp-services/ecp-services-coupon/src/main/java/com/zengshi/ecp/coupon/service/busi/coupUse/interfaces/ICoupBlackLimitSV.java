package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBlackLimitRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface ICoupBlackLimitSV extends ICoupRuleSV{
	
	/**
	 * 
	 * queryCoupInfoPage:查询黑名单表. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupBlackLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupBlackLimitRespDTO> queryCoupBlackList(CoupBlackLimitReqDTO coupBlackLimitReqDTO) throws BusinessException;
	

}

