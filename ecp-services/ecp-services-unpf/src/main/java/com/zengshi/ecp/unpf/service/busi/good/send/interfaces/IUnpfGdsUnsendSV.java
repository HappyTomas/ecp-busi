package com.zengshi.ecp.unpf.service.busi.good.send.interfaces;

import java.util.List;

import org.drools.compiler.compiler.BoundIdentifiers;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsUnsend;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsUnsendRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午8:02:24 
* @version 1.0 
**/
public interface IUnpfGdsUnsendSV {

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
	* 根据id删除待推送商品
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
	* 根据GdsId查询待推送商品
	* 
	* @author lisp
	* @param queryGdsUnsendByGdsId
	* @return 
	* @throws BusinessException
	*/
	public List<UnpfGdsUnsend> queryGdsUnsendByGdsId(UnpfGdsUnsendReqDTO unpfGdsUnsendReqDTO) throws BusinessException;
	
}


