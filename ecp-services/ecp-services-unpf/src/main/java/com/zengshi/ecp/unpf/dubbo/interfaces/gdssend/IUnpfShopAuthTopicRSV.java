package com.zengshi.ecp.unpf.dubbo.interfaces.gdssend;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午11:06:54 
* @version 1.0 
**/
public interface IUnpfShopAuthTopicRSV {

	/**
	* 保存-店铺授权消息
	* 
	* @author lisp
	* @param UnpfShopAuthTopicReqDTO
	* @return 
	* @throws 
	*/
	public UnpfShopAuthTopicRespDTO saveShopAuthTopic (UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException;
	
	/**
	* 根据Id查询店铺授权消息
	* 
	* @author lisp
	* @param UnpfShopAuthTopicReqDTO
	* @return 
	* @throws 
	*/
	public UnpfShopAuthTopicRespDTO queryShopAuthTopicById(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException; 
	
	/**
	* 查询店铺授权消息分页列表
	* 
	* @author lisp
	* @param UnpfShopAuthTopicReqDTO
	* @return 
	* @throws 
	*/
	public PageResponseDTO<UnpfShopAuthTopicRespDTO> queryShopAuthTopicForPage(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException;
	
	/**
	* 更新店铺授权消息
	* 
	* @author lisp
	* @param UnpfShopAuthTopicReqDTO
	* @return 
	* @throws 
	*/
	public void updateShopAuthTopicByExample(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException;
	
	
	/**
	* 更新店铺授权消息
	* 
	* @author lisp
	* @param closeShopAuthTopicByExample
	* @return 
	* @throws 
	*/
	public void closeShopAuthTopicByExample(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException;
	
	
	
}


