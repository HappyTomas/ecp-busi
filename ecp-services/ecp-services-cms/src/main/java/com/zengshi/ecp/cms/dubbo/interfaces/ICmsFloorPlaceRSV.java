package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ICmsFloorPlaceRSV {

	/** 
     * addCmsFloorPlace:(新增). <br/> 
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
    public CmsFloorPlaceRespDTO addCmsFloorPlace(CmsFloorPlaceReqDTO dto) throws BusinessException;
    
    /** 
     * updateCmsFloorPlace:(更新). <br/> 
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
    public CmsFloorPlaceRespDTO updateCmsFloorPlace(CmsFloorPlaceReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorPlaceList:(查询链接列表，无分页). <br/> 
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
    public List<CmsFloorPlaceRespDTO> queryCmsFloorPlaceList(CmsFloorPlaceReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorPlacePage:(查询链接列表，分页). <br/> 
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
    public PageResponseDTO<CmsFloorPlaceRespDTO> queryCmsFloorPlacePage(CmsFloorPlaceReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorPlace:(这里用一句话描述这个方法的作用). <br/> 
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
    public CmsFloorPlaceRespDTO queryCmsFloorPlace(CmsFloorPlaceReqDTO dto) throws BusinessException;
    
    /** 
     * deleteCmsFloorPlace:(这里用一句话描述这个方法的作用). <br/> 
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
    public void deleteCmsFloorPlace(String id) throws BusinessException;
    
    /** 
     * deleteCmsFloorPlaceBatch:(这里用一句话描述这个方法的作用). <br/> 
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
    public void deleteCmsFloorPlaceBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsFloorPlace:(这里用一句话描述这个方法的作用). <br/> 
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
    public void changeStatusCmsFloorPlace(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsFloorPlaceBatch:(这里用一句话描述这个方法的作用). <br/> 
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
    public void changeStatusCmsFloorPlaceBatch(List<String> list,String status) throws BusinessException;
}
