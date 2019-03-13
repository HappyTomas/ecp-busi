package com.zengshi.ecp.unpf.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月25日 上午10:22:19 
* @version 1.0 
**/
public interface IUnpfShopCfgSV {

  /**
    * 保存店铺基本参数配置信息 saveShopCfg
	* @author lisp
	* @param UnpfShopCfgReqDTO
	* @throws BusinessException
	*/
	public void saveShopCfg(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException;
	
  /**
    * 更新店铺基本参数配置信息 updateShopCfg
	* @author lisp
	* @param UnpfShopCfgReqDTO
	* @throws BusinessException
	*/
	public void updateShopCfg(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException;
	
  /**
    * 根据ID查询店铺基本参数配置信息 queryShopCfgById
	* @author lisp
	* @param UnpfShopCfgReqDTO
	* @throws BusinessException
	*/
	public UnpfShopCfgRespDTO queryShopCfgById(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException;
	
  /**
    * 查询店铺基本参数配置信息列表 queryShopCfgList
	* @author lisp
	* @param UnpfShopCfgReqDTO
	* @throws BusinessException
	*/
	public List<UnpfShopCfgRespDTO> queryShopCfgList(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException;
	
  /**
    * 查询店铺基本参数配置信息分页列表
	* @author lisp
	* @param UnpfShopCfgReqDTO
	* @throws BusinessException
	*/
	public PageResponseDTO<UnpfShopCfgRespDTO> queryShopCfgForPage(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException;
	
	
}


