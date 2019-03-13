package com.zengshi.ecp.unpf.dubbo.interfaces.gdssend;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:03:31 
* @version 1.0 
**/
public interface IUnpfGdsUnsendRSV {



	/**
	* 保存待推送商品
	* 
	* @author lisp
	* @param saveGdsUnsend
	* @return 
	* @throws BusinessException
	*/
	public void saveGdsUnsend(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException;
	
	/**
	* 根据删除待推送商品
	* 
	* @author lisp
	* @param deleteGdsUnsendById
	* @return 
	* @throws BusinessException
	*/
	public void deleteGdsUnsendById(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException;
	
	/**
	* 根据商品id删除待推送商品
	* 
	* @author lisp
	* @param deleteGdsUnsendByGdsId
	* @return 
	* @throws BusinessException
	*/
	public void deleteGdsUnsendByGdsId(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException;
	
	
	/**
	* 根据Id查询待推送商品
	* 
	* @author lisp
	* @param queryGdsUnsendById
	* @return 
	* @throws BusinessException
	*/
	public UnpfGdsUnsendRespDTO queryGdsUnsendById(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException;
	
	/**
	* 查询待推送商品分页列表
	* 
	* @author lisp
	* @param queryGdsUnsendForPage
	* @return 
	* @throws BusinessException
	*/
	public PageResponseDTO<UnpfGdsUnsendRespDTO> queryGdsUnsendForPage(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException;

	/**
	* 根据Id查询待推送商品
	* 
	* @author lisp
	* @param saveUnsendGds
	* @return 
	* @throws BusinessException
	*/
	public void saveUnsendGds(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException;
	
	
}


