package com.zengshi.ecp.unpf.service.busi.token.interfaces;


import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月10日 上午9:29:34 
* @version 1.0 
*  */
public interface IUnpfTokenSV extends IGeneralSQLSV {

	/**
	* 创建token
	* 
	* @author huangjx
	* @param 
	* @return 
	* @throws 
	*/
	public void createToken(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;
	
}


