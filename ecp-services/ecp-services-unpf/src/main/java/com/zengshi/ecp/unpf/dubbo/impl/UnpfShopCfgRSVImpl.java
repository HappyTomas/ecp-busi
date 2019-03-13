package com.zengshi.ecp.unpf.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopCfgRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfShopCfgSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
* @author  lisp: 
* @date 创建时间：2016年11月25日 上午11:27:51 
* @version 1.0 
**/
public class UnpfShopCfgRSVImpl implements IUnpfShopCfgRSV {
	
	@Resource
	private IUnpfShopCfgSV unpfShopCfgSV;
	
	@Override
	public void saveShopCfg(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {

		if(unpfShopCfgReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getShopAuthId())){
			throw new BusinessException("unpf.100014");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getShopId())){
			throw new BusinessException("unpf.100003");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getPlatType())){
			throw new BusinessException("unpf.100015");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getInputValue())){
			throw new BusinessException("unpf.100024");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getStatus())){
			unpfShopCfgReqDTO.setStatus(UnpfConstants.ShopCfg.STATUS_1);
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getCreateStaff())){
			unpfShopCfgReqDTO.setCreateStaff(unpfShopCfgReqDTO.getStaff().getId());
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getCreateTime())){
			unpfShopCfgReqDTO.setCreateTime(DateUtil.getSysDate());
		}
		unpfShopCfgSV.saveShopCfg(unpfShopCfgReqDTO);
	}

	@Override
	public void updateShopCfg(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {

		if(unpfShopCfgReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getId())){
			throw new BusinessException("unpf.100002");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getInputValue())){
			throw new BusinessException("unpf.100024");
		}
		unpfShopCfgReqDTO.setUpdateTime(DateUtil.getSysDate());
		unpfShopCfgReqDTO.setUpdateStaff(unpfShopCfgReqDTO.getStaff().getId());
		unpfShopCfgSV.updateShopCfg(unpfShopCfgReqDTO);
	}

	@Override
	public UnpfShopCfgRespDTO queryShopCfgById(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {
		
		if(unpfShopCfgReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getId())){
			throw new BusinessException("unpf.100002");
		}

		return unpfShopCfgSV.queryShopCfgById(unpfShopCfgReqDTO);
	}

	@Override
	public List<UnpfShopCfgRespDTO> queryShopCfgList(UnpfShopCfgReqDTO unpfShopCfgReqDTO) throws BusinessException {
		if(unpfShopCfgReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		return unpfShopCfgSV.queryShopCfgList(unpfShopCfgReqDTO);
	}

	@Override
	public PageResponseDTO<UnpfShopCfgRespDTO> queryShopCfgForPage(UnpfShopCfgReqDTO unpfShopCfgReqDTO)
			throws BusinessException {
		if(unpfShopCfgReqDTO==null){
			throw new BusinessException("unpf.100001");
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getPageSize())){
			unpfShopCfgReqDTO.setPageSize(10);
		}
		if(StringUtil.isEmpty(unpfShopCfgReqDTO.getPageNo())){
			unpfShopCfgReqDTO.setPageNo(1);
		}
		return unpfShopCfgSV.queryShopCfgForPage(unpfShopCfgReqDTO);
	}

}


