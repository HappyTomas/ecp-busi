package com.zengshi.ecp.unpf.dubbo.impl.gdssend;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendHisReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendHisRSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGdsUnsendHisSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:11:18 
* @version 1.0 
**/
public class UnpfGdsUnsendHisRSVImpl implements IUnpfGdsUnsendHisRSV {
	
	@Resource
	private IUnpfGdsUnsendHisSV unpfGdsUnsendHisSV;
	

	@Override
	public void saveGdsUnsendHis(UnpfGdsUnsendHisReqDTO unpfGdsUnsendHisReqDTO) throws BusinessException {
		
		if(unpfGdsUnsendHisReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendHisReqDTO.getShopId())){
			throw new BusinessException("unpf.100003");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendHisReqDTO.getGdsId())){
			throw new BusinessException("unpf.100020");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendHisReqDTO.getStatus())){
			throw new BusinessException("unpf.100018");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendHisReqDTO.getCreateTimeOld())){
			throw new BusinessException("unpf.100021");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendHisReqDTO.getCreateStaffOld())){
			throw new BusinessException("unpf.100022");
		}
		if(StringUtil.isEmpty(unpfGdsUnsendHisReqDTO.getCreateTime())){
			unpfGdsUnsendHisReqDTO.setCreateTime(DateUtil.getSysDate());
		}
		if(StringUtil.isEmpty(unpfGdsUnsendHisReqDTO.getCreateStaff())){
			unpfGdsUnsendHisReqDTO.setCreateStaff(unpfGdsUnsendHisReqDTO.getStaff().getId());
		}
		unpfGdsUnsendHisSV.saveGdsUnsendHis(unpfGdsUnsendHisReqDTO);
	}

}


