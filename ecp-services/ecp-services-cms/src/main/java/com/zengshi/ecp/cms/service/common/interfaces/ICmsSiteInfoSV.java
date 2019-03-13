package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface ICmsSiteInfoSV extends IGeneralSQLSV{

	 /** 
     * saveCmsSiteInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsSiteInfoRespDTO addCmsSiteInfo(CmsSiteInfoReqDTO dto) throws BusinessException;
    
    /** 
     * updateCmsSiteInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsSiteInfoRespDTO updateCmsSiteInfo(CmsSiteInfoReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsSiteInfoList:(查询网站信息列表，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsSiteInfoRespDTO> queryCmsSiteInfoList(CmsSiteInfoReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsSiteInfoPage:(查询网站信息列表，分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<CmsSiteInfoRespDTO> queryCmsSiteInfoPage(CmsSiteInfoReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsSiteInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsSiteInfoRespDTO queryCmsSiteInfo(CmsSiteInfoReqDTO dto) throws BusinessException;
    
    /**
     * 
     * queryTopSiteInfoBySiteId:(通过站点id 获取顶级网站信息). <br/> 
     * 
     * @author zhanbh 
     * @param siteId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<CmsSiteInfoRespDTO> queryTopSiteInfoBySiteId(Long siteId)throws BusinessException;
    /** 
     * deleteCmsSiteInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsSiteInfo(String id) throws BusinessException;
    
    /** 
     * deleteCmsSiteInfoBatch:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param list
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsSiteInfoBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsSiteInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param id
     * @param status
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsSiteInfo(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsSiteInfoBatch:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param list
     * @param status
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsSiteInfoBatch(List<String> list,String status) throws BusinessException;
}
