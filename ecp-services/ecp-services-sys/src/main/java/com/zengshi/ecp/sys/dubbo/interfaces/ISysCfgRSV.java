package com.zengshi.ecp.sys.dubbo.interfaces;


import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgResDTO;
public interface ISysCfgRSV {
	/**
	 * 系统参数 查询 分页
	 * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public PageResponseDTO<SysCfgResDTO> querySysCfgForPage(SysCfgReqDTO sysCfgReqDTO) throws BusinessException ;
    
    /**
     * fetchByparaCode:<br/>
     * 根据 请求参数中的paraCode获取 单条系统参数信息；
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException 
     */
    public SysCfgResDTO fetchByparaCode(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;
    /**
     * saveSysCfg:<br/>
     * 保存系统参数信息； 
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
     */
    public int saveSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;
    
    /**
     * updateByparaCode:<br/>
     * 根据 请求参数中更新系统参数信息； 
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
     */
    public int updateSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException;
    /**
     * removeSysCfg:<br/>
     * 根据 请求参数删除系统参数信息； 
     * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
     */
    public void removeSysCfg (SysCfgReqDTO sysCfgReqDTO) throws BusinessException;
}
