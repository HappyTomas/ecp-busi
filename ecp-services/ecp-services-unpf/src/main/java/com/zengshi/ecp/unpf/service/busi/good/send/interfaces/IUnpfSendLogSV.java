package com.zengshi.ecp.unpf.service.busi.good.send.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午10:29:44 
* @version 1.0 
**/
public interface IUnpfSendLogSV {

	/**
	* 保存商品推送的日志记录
	* 
	* @author lisp
	* @param UnpfSendLogReqDTO
	* @return 
	* @throws 
	*/
	public void saveGdsSendLog(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException;
	
	/**
	* 根据Id查询商品推送日志记录
	* 
	* @author lisp
	* @param UnpfSendLogReqDTO
	* @return 
	* @throws 
	*/
	public UnpfSendLogRespDTO queryGdsSendLogById(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException;
	
	/**
	* 商品推送日志记录分页
	* 
	* @author lisp
	* @param UnpfSendLogReqDTO
	* @return 
	* @throws 
	*/
	public PageResponseDTO<UnpfSendLogRespDTO> queryGdsSendLogForPage(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException;
	
	/**
	* 商品编码+平台类型
	* 
	* @author huangjx
	* @param UnpfSendLogReqDTO
	* @return 
	* @throws 
	*/
	public Long  countGds(UnpfSendLogReqDTO unpfSendLogReqDTO) throws BusinessException;
}


