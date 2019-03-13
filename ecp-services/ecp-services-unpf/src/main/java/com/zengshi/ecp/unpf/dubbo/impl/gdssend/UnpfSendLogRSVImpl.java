package com.zengshi.ecp.unpf.dubbo.impl.gdssend;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfSendLogRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfSendLogSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午11:17:55 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfSendLogRSVImpl implements IUnpfSendLogRSV {
	
	@Resource
	private IUnpfSendLogSV unpfSendLogsv;

	@Override
	public void saveGdsSendLog(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException {

		if(unpfSendLogReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getShopAuthId())){
			throw new BusinessException("unpf.100014");
		}
		if(StringUtil.isBlank(unpfSendLogReqDTO.getPlatType())){
			throw new BusinessException("unpf.100015");
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getShopId())){
			throw new BusinessException("unpf.100003");
		}
		if(StringUtil.isBlank(unpfSendLogReqDTO.getAction())){
			throw new BusinessException("unpf.100016");
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getCreateStaff())){
			unpfSendLogReqDTO.setCreateStaff(unpfSendLogReqDTO.getStaff().getId());
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getCreateTime())){
			unpfSendLogReqDTO.setCreateTime(DateUtil.getSysDate());
		}
		unpfSendLogsv.saveGdsSendLog(unpfSendLogReqDTO);
	}

	@Override
	public UnpfSendLogRespDTO queryGdsSendLogById(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException {
		if(unpfSendLogReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getId())){
			throw new BusinessException("unpf.100002");
		}
		return unpfSendLogsv.queryGdsSendLogById(unpfSendLogReqDTO);
	}

	@Override
	public PageResponseDTO<UnpfSendLogRespDTO> queryGdsSendLogForPage(UnpfSendLogReqDTO unpfSendLogReqDTO)
			throws BusinessException {
		if(unpfSendLogReqDTO==null){
			unpfSendLogReqDTO =new UnpfSendLogReqDTO();
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getPageSize())){
			unpfSendLogReqDTO.setPageSize(UnpfConstants.PAGESIZE);
		}
		if(StringUtil.isEmpty(unpfSendLogReqDTO.getPageNo())){
			unpfSendLogReqDTO.setPageNo(UnpfConstants.PAGENO);
		}
		return unpfSendLogsv.queryGdsSendLogForPage(unpfSendLogReqDTO);
	}

}


