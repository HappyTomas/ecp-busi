package com.zengshi.ecp.unpf.service.busi.msgSyc.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.unpf.dubbo.dto.msgSyc.UnpfMsgSycReqDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月15日 上午10:27:35 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public interface IUnpfMsgSycSV extends IGeneralSQLSV{
	
  /**
    * 保存同步信息
	* @author lisp
	* @param UnpfMsgSycReqDTO
	* @throws BusinessException
	*/
	public void saveUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException; 
	
 /**
    * 更新同步信息
	* @author lisp
	* @param UnpfMsgSycReqDTO
	* @throws BusinessException
	*/
	public void updateUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException; 
		
/**
    * 删除同步信息
	* @author lisp
	* @param UnpfMsgSycReqDTO
	* @throws BusinessException
	*/
	public void deleteUnpfMsgSyc(UnpfMsgSycReqDTO unpfMsgSycReqDTO) throws BusinessException; 
		
/**
    * 获取Id
	* @author lisp
	* @param 
	* @throws BusinessException
	*/
	public Long getMsgSycId() throws BusinessException; 
		
}


