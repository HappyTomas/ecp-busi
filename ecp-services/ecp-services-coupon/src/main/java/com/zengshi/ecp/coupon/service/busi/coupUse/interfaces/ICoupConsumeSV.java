package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupConsumeRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;


public interface ICoupConsumeSV extends IGeneralSQLSV{
	
	/**
	 * 优惠券使用明细查询
	 * @author huanghe5
	 * @param coupConsumeReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public PageResponseDTO<CoupConsumeRespDTO> queryCoupConsumePage(CoupConsumeReqDTO coupConsumeReqDTO) throws BusinessException;

}

