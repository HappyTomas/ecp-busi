package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupShopLimitRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface ICoupShopLimitSV extends ICoupRuleSV{
	
	/**
	 * 
	 * 查询店铺规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupShopLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupShopLimitRespDTO> queryCoupShopList(CoupShopLimitReqDTO coupShopLimitReqDTO,String typeLimit) throws BusinessException;
	
}

