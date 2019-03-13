package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRWRSV;
import com.zengshi.ecp.staff.service.busi.union.interfaces.IStaffUnionRWSV;

public class StaffUnionRWRSVImpl implements IStaffUnionRWRSV{
	
	@Resource(name="staffUnionCoreRWSV")
	private IStaffUnionRWSV staffUnionRWSV;

	@Override
	public long selTotalScoreByBackOrderRW(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
		return staffUnionRWSV.selTotalScoreByBackOrderRW(req);
	}

	@Override
	public boolean saveScoreAcctForOrderBackRW(OrderBackMainReqDTO<OrderBackSubReqDTO> req, OrderBackSubReqDTO orderReq)
			throws BusinessException {
		return staffUnionRWSV.saveScoreAcctForOrderBackRW(req, orderReq);
	}

	@Override
	public Long saveScoreFrozenForOrderBackRW(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
		return staffUnionRWSV.saveScoreFrozenForOrderBackRW(req);
	}

	@Override
	public Long saveScoreFrozenModifyForOrderBackRW(OrderBackMainReqDTO<OrderBackSubReqDTO> req)
			throws BusinessException {
		return staffUnionRWSV.saveScoreFrozenModifyForOrderBackRW(req);
	}

	@Override
	public long selTotalScoreByBackOrderAllRW(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
		return staffUnionRWSV.selTotalScoreByBackOrderAllRW(req);
	}

}

