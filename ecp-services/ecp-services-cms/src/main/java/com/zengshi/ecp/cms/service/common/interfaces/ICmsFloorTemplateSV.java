package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface ICmsFloorTemplateSV extends IGeneralSQLSV{

	/** 
     * addCmsFloorTemplate:(新增). <br/> 
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
    public CmsFloorTemplateRespDTO addCmsFloorTemplate(CmsFloorTemplateReqDTO dto) throws BusinessException;
    
    /** 
     * updateCmsFloorTemplate:(更新). <br/> 
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
    public CmsFloorTemplateRespDTO updateCmsFloorTemplate(CmsFloorTemplateReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorTemplateList:(查询链接列表，无分页). <br/> 
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
    public List<CmsFloorTemplateRespDTO> queryCmsFloorTemplateList(CmsFloorTemplateReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorTemplatePage:(查询链接列表，分页). <br/> 
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
    public PageResponseDTO<CmsFloorTemplateRespDTO> queryCmsFloorTemplatePage(CmsFloorTemplateReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorTemplate:(这里用一句话描述这个方法的作用). <br/> 
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
    public CmsFloorTemplateRespDTO queryCmsFloorTemplate(CmsFloorTemplateReqDTO dto) throws BusinessException;
    
    /** 
     * deleteCmsFloorTemplate:(这里用一句话描述这个方法的作用). <br/> 
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
    public void deleteCmsFloorTemplate(String id) throws BusinessException;
    
    /** 
     * deleteCmsFloorTemplateBatch:(这里用一句话描述这个方法的作用). <br/> 
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
    public void deleteCmsFloorTemplateBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsFloorTemplate:(这里用一句话描述这个方法的作用). <br/> 
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
    public void changeStatusCmsFloorTemplate(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsFloorTemplateBatch:(这里用一句话描述这个方法的作用). <br/> 
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
    public void changeStatusCmsFloorTemplateBatch(List<String> list,String status) throws BusinessException;
}
