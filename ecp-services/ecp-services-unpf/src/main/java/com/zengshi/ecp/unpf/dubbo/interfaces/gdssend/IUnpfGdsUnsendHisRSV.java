package com.zengshi.ecp.unpf.dubbo.interfaces.gdssend;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendHisReqDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:10:49 
* @version 1.0 
**/
public interface IUnpfGdsUnsendHisRSV {

	/**
	* 保存待推送商品的历史
	* 
	* @author lisp
	* @param saveGdsUnsendHis
	* @return 
	* @throws BusinessException
	*/
	public void saveGdsUnsendHis(UnpfGdsUnsendHisReqDTO unpfGdsUnsendHisReqDTO) throws BusinessException;
}


