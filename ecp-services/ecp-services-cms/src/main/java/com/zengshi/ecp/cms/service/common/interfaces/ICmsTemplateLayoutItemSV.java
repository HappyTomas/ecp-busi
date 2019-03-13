package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:43  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public interface ICmsTemplateLayoutItemSV extends IGeneralSQLSV{
    
    /** 
     * addCmsTemplateLayoutItem:(根据ID更新). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsTemplateLayoutItemRespDTO addCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException;
    
    /** 
     * updateCmsTemplateLayoutItem:(根据ID更新). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsTemplateLayoutItemRespDTO updateCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsTemplateLayoutItemList:(查询模板布局项列表，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsTemplateLayoutItemRespDTO> queryCmsTemplateLayoutItemList(CmsTemplateLayoutItemReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsTemplateLayoutItemPage:(查询模板布局项列表，分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<CmsTemplateLayoutItemRespDTO> queryCmsTemplateLayoutItemPage(CmsTemplateLayoutItemReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsTemplateLayoutItem:(根据ID查询模板布局项). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsTemplateLayoutItemRespDTO queryCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException;
    
    /** 
     * deleteCmsTemplateLayoutItem:(根据ID删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsTemplateLayoutItem(String id) throws BusinessException;
    
    /** 
     * deleteCmsTemplateLayoutItemBatch:(批量删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param list
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsTemplateLayoutItemBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsTemplateLayoutItem:(根据ID更新). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param id
     * @param status
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsTemplateLayoutItem(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsTemplateLayoutItemBatch:(批量更新状态). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param list
     * @param status
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsTemplateLayoutItemBatch(List<String> list,String status) throws BusinessException;
    
    /** 
     * deleteCmsTemplateLayoutItemByExample:(根据条件删除记录). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param list
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public int deleteCmsTemplateLayoutItemByExample(CmsTemplateLayoutItemReqDTO dto) throws BusinessException ;
    /**
     * 
     * updateTemplateLayoutItemSensitive:(将参数直接更新到库里面，对空敏感). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsTemplateLayoutItemRespDTO updateTemplateLayoutItemSensitive(CmsTemplateLayoutItemReqDTO dto)
            throws BusinessException;
    /** 
     * updateTemplateLayoutItemByExample:(根据条件更新结果). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param conditionDTO  查询条件DTO
     * @param resultDTO  结果DTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public int updateTemplateLayoutItemByExample(CmsTemplateLayoutItemReqDTO conditionDTO,CmsTemplateLayoutItemReqDTO resultDTO) throws BusinessException;
    /** 
     * updateCmsLayoutPreBatch. <br/> 
     * 批量更新布局(删除布局、对布局重新排序)
     * 当状态为2时：
     * TODO(1、更新布局项).<br/> 
     * TODO(2、根据布局更新布局项与属性关系).<br/> 
     * TODO(3、根据布局项与属性关系ID热点表).<br/>
     * 当状态为0时：
     * TODO(1、更新布局项，更新排序。).<br/> 
     * 
     * @author jiangzh 
     * @param list
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateTemplateLayoutItemBatch(List<CmsTemplateLayoutItemReqDTO> list) throws BusinessException;
}

