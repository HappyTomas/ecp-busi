package com.zengshi.ecp.unpf.service.busi.catg.interfaces;


import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月16日 上午9:29:34 
* @version 1.0 
*  */
public interface IUnpfStockCatgSV extends IGeneralSQLSV {

	/**
	* 分类保存
	* 
	* @author huangjx
	* @param 
	* @return 
	* @throws 
	*/
	public void saveCatg(CatgReqDTO catgReqDTO) throws BusinessException;
	

	/**
	* 分类 属性 属性值 保存
	* 
	* @author huangjx
	* @param 
	* @return 
	* @throws 
	*/
	public void saveCatgAndProp(CatgReqDTO catgReqDTO) throws BusinessException;
	
}


