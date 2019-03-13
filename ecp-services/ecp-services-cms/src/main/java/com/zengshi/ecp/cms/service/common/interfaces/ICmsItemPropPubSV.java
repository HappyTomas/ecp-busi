package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubRespDTO;
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
public interface ICmsItemPropPubSV extends IGeneralSQLSV{
    
    /** 
     * addCmsItemPropPub:(添加布局项属性). <br/> 
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
    public CmsItemPropPubRespDTO addCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /** 
     * updateCmsItemPropPub:(根据ID更新). <br/> 
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
    public CmsItemPropPubRespDTO updateCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsItemPropPubList:(查询布局项属性列表，无分页). <br/> 
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
    public List<CmsItemPropPubRespDTO> queryCmsItemPropPubList(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /**
     * TODO 查询布局属性关系列表，无分页（可选）.
     * 1、根据属性ID，将属性ID一致的记录组装成LIST。
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#queryCmsItemPropPubList(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO)
     */
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubList(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /**
     * TODO 查询布局属性关系列表，无分页（可选）.
     * 1、根据属性ID，将属性ID一致的记录组装成LIST。
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#queryCmsItemPropPubList(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO)
     */
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubListForWap(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsItemPropPubPage:(查询布局项属性列表，分页). <br/> 
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
    public PageResponseDTO<CmsItemPropPubRespDTO> queryCmsItemPropPubPage(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsItemPropPub:(根据ID查询). <br/> 
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
    public CmsItemPropPubRespDTO queryCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /** 
     * deleteCmsItemPropPub:(根据ID删除). <br/> 
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
    public void deleteCmsItemPropPub(String id) throws BusinessException;
    
    /** 
     * deleteCmsItemPropPubBatch:(批量删除). <br/> 
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
    public void deleteCmsItemPropPubBatch(List<String> list) throws BusinessException;
    /** 
     * deleteCmsItemPropPubByExample:(根据条件删除记录). <br/> 
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
    public int deleteCmsItemPropPubByExample(CmsItemPropPubReqDTO dto) throws BusinessException ;
    /** 
     * changeStatusCmsItemPropPub:(根据ID更新状态). <br/> 
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
    public void changeStatusCmsItemPropPub(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsItemPropPubBatch:(批量更新状态). <br/> 
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
    public void changeStatusCmsItemPropPubBatch(List<String> list,String status) throws BusinessException;
    
}

