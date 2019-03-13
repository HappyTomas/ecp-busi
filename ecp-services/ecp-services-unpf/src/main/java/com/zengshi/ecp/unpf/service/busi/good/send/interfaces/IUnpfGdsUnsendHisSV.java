package com.zengshi.ecp.unpf.service.busi.good.send.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendHisReqDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:09:17 
* @version 1.0 
**/
public interface IUnpfGdsUnsendHisSV {

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


