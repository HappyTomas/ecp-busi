package com.zengshi.ecp.unpf.dubbo.impl.catg;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.interfaces.catg.IUnpfCatgRSV;
import com.zengshi.ecp.unpf.service.busi.catg.interfaces.IUnpfCatgSV;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月16日 上午9:10:38 
* @version 1.0 
* Copyright (c) 2016 AI <br>
* */
public class UnpfCatgRSVImpl implements IUnpfCatgRSV {

	 @Resource
	 private IUnpfCatgSV unpfCatgSV;


	/**
	* 分类保存
	* 
	* @author huangjx
	* @param 
	* @return 
	* @throws 
	*/
	@Override
	public void saveCatg(CatgReqDTO catgReqDTO) throws BusinessException{
		unpfCatgSV.saveCatg(catgReqDTO);
	}
	

	/**
	* 分类 属性 属性值 保存
	* 
	* @author huangjx
	* @param 
	* @return 
	* @throws 
	*/
	@Override
	public void saveCatgAndProp(CatgReqDTO catgReqDTO) throws BusinessException{
		unpfCatgSV.saveCatgAndProp(catgReqDTO);
	}
}


