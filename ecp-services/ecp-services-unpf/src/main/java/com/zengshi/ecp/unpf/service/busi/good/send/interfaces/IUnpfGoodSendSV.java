 package com.zengshi.ecp.unpf.service.busi.good.send.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSend;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendBaseRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendLogRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendReqDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月17日 下午2:34:54 
* @version 1.0 
**/
public interface IUnpfGoodSendSV {

	//推送列表
	public List<UnpfGdsSend> querySends(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
	/**
	* 查询商品的推送结果
	* 
	* @author lisp
	* @param querySendResultForGds
	* @return 
	* @throws BusinessException
	*/
	public GdsSendBaseRespDTO querySendResultForGds(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
	
	/**
	* 查询商品的推送结果分页列表
	* 
	* @author lisp
	* @param querySendLogForPage
	* @return 
	* @throws BusinessException
	*/
	public PageResponseDTO<UnpfGdsSendLogRespDTO> querySendLogForPage(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
	/**
	* 商品编码+平台类型
	* 
	* @author huangjx
	* @param UnpfGdsSendReqDTO
	* @return 
	* @throws 
	*/
	public Long countGds(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
	

	/**
	* 推送成功 更新数据
	* 
	* @author huangjx
	* @param UnpfGdsSendReqDTO
	* @return 
	* @throws 
	*/
	public void saveSendSucess(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
	/**
	* 推送失败 更新数据
	* 
	* @author huangjx
	* @param UnpfGdsSendReqDTO
	* @return 
	* @throws 
	*/
	public void saveSendError(UnpfGdsSendReqDTO unpfGdsSendReqDTO,Boolean ifRemove) throws BusinessException;
	
	/**
	* 更新数据
	* 
	* @author huangjx
	* @param UnpfGdsSendReqDTO
	* @return 
	* @throws 
	*/
	public int updateById(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
	/**
	* save数据
	* 
	* @author huangjx
	* @param UnpfGdsSendReqDTO
	* @return 
	* @throws 
	*/
	public Long save(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;

	/**
	 * 根据外部商品编号查询数据
	 * @param unpfGdsSendReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public List<UnpfGdsSend> queryGdsSendByOuterId(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws  BusinessException;

	/**
	 * 修改对应关系提交
	 * @param unpfGdsSendReqDTO
	 * @throws BusinessException
	 */
	public void updateGdsSendSubmit(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
}


