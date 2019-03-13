package com.zengshi.ecp.unpf.dubbo.interfaces.catg;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月10日 上午9:08:23 
* @version 1.0 
* Copyright (c) 2016 AI 
*  */
public interface IUnpfCatgRSV {

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


