package com.zengshi.ecp.sys.dubbo.impl;


import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.ISysCfgRSV;
import com.zengshi.ecp.sys.service.common.interfaces.ISysCfgSV;
import com.zengshi.paas.utils.StringUtil;

public class SysCfgRSVImpl implements ISysCfgRSV {
	@Resource
	private ISysCfgSV sysCfgSV;
	 /**
     * fetchByparaCode:<br/>
     * 根据 请求参数中的paraCode获取 单条系统参数信息；
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException 
     */
	@Override
	public SysCfgResDTO fetchByparaCode(SysCfgReqDTO sysCfgReqDTO) {
		if(StringUtil.isEmpty(sysCfgReqDTO)){
			 throw new BusinessException("sys.cfg.param.null");
		}
		if(StringUtil.isEmpty(sysCfgReqDTO.getParaCode())){
			 throw new BusinessException("sys.cfg.param.paraCode.null");
		}
		return sysCfgSV.fetchByparaCode(sysCfgReqDTO);
	}
	/**
     * saveSysCfg:<br/>
     * 保存系统参数信息； 
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
     */
	@Override
	public int saveSysCfg(SysCfgReqDTO sysCfgReqDTO) {
		
		if(StringUtil.isEmpty(sysCfgReqDTO)){
			 throw new BusinessException("sys.cfg.param.null");
		}
		if(StringUtil.isEmpty(sysCfgReqDTO.getParaCode())){
			 throw new BusinessException("sys.cfg.param.paraCode.null");
		}
		if(StringUtil.isEmpty(sysCfgReqDTO.getParaValue())){
			 throw new BusinessException("sys.cfg.param.paraValue.null");
		}
		return sysCfgSV.saveSysCfg(sysCfgReqDTO);
	}
	 /**
     * updateByparaCode:<br/>
     * 根据 请求参数中更新系统参数信息； 
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
     */
	@Override
	public int updateSysCfg(SysCfgReqDTO sysCfgReqDTO) {
		
		if(StringUtil.isEmpty(sysCfgReqDTO)){
			 throw new BusinessException("sys.cfg.param.null");
		}
		if(StringUtil.isEmpty(sysCfgReqDTO.getParaCode())){
			 throw new BusinessException("sys.cfg.param.paraCode.null");
		}
		if(StringUtil.isEmpty(sysCfgReqDTO.getParaValue())){
			 throw new BusinessException("sys.cfg.param.paraValue.null");
		}
		return sysCfgSV.updateSysCfg(sysCfgReqDTO);
		
	}
	/**
	 * 系统参数 查询 分页
	 * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public PageResponseDTO<SysCfgResDTO> querySysCfgForPage(SysCfgReqDTO sysCfgReqDTO) throws BusinessException {

		if(StringUtil.isEmpty(sysCfgReqDTO)){
			 throw new BusinessException("sys.cfg.param.null");
		}
		/*if(StringUtil.isEmpty(sysCfgReqDTO.getParaCode())){
			 throw new BusinessException("sys.cfg.param.paraCode.null");
		}*/
		return sysCfgSV.querySysCfgForPage(sysCfgReqDTO);
	}
	   /**
     * removeSysCfg:<br/>
     * 根据 请求参数删除系统参数信息； 
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
     */
	@Override
	public void removeSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException {
		if(StringUtil.isEmpty(sysCfgReqDTO)){
			 throw new BusinessException("sys.cfg.param.null");
		}
		if(StringUtil.isEmpty(sysCfgReqDTO.getParaCode())){
			 throw new BusinessException("sys.cfg.param.paraCode.null");
		}
		try {
			sysCfgSV.removeSysCfg(sysCfgReqDTO);
		} catch (Exception e) {
			throw new BusinessException("sys.cfg.param.delete.failure");
		}
	}

}
