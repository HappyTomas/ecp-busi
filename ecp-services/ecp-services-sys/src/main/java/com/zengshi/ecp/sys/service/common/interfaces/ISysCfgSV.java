package com.zengshi.ecp.sys.service.common.interfaces;


import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgResDTO;

public interface ISysCfgSV extends IGeneralSQLSV {

	/**
	 * 
	 * fetchByparaCode: 查找系统参数信息 <br/>
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public SysCfgResDTO fetchByparaCode(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;

	/**
	 * 
	 * saveSysCfg: 保存系统参数信息 <br/>
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public int saveSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;

	/**
	 * 
	 * updateSysCfg: 更新系统参数信息 <br/>
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public int updateSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;

	/**
	 * 搭配系统参数列表 分页功能
	 * 
	 * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public PageResponseDTO<SysCfgResDTO> querySysCfgForPage(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;
	/**
	 * 删除系统参数
	 * 
	 * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public void removeSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;
}
