package com.zengshi.ecp.unpf.service.busi.good.send.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuth;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthTopic;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午10:52:44 
* @version 1.0 
**/
public interface IUnpfShopAuthTopicSV {

	/**
	* 保存-店铺授权消息
	* 
	* @author lisp
	* @param UnpfShopAuthTopicReqDTO
	* @return 
	* @throws 
	*/
	public void saveShopAuthTopic(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException; 
	
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
	* 校验店铺授权消息
	* 
	* @author lisp
	* @param UnpfShopAuthTopicReqDTO
	* @return  UnpfShopAuthTopicReqDTO
	* @throws 
	*/
	public UnpfShopAuthTopicReqDTO validShopAuthTopic(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException;

	/**
	* 校验店铺授权消息
	* 
	* @author lisp
	* @param UnpfShopAuthTopicReqDTO
	* @return  
	* @throws 
	*/
	public List<UnpfShopAuthTopic> shopAuthTopicList(UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO) throws BusinessException;

	
}


