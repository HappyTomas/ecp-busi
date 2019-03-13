package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:24:37  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6
 */
public interface ICmsItemPropPubRSV {
    
    /** 
     * saveCmsItemPropPub:(新增布局项属性). <br/> 
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
     * deleteCmsItemPropPub:(逻辑删除布局项属性). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param id:主键
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsItemPropPub(String id) throws BusinessException;
    
    /** 
     * deleteCmsItemPropPubBatch:(批量逻辑删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param list：主键LIST集合
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsItemPropPubBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsItemPropPub:(使生效、失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param id：主键
     * @param status：状态
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsItemPropPub(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsItemPropPubBatch:(批量使生效、失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param list：主键的LIST集合
     * @param status：状态
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsItemPropPubBatch(List<String> list,String status) throws BusinessException;
    
    /** 
     * updateCmsItemPropPub:(更新布局项属性). <br/> 
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
     * queryCmsItemPropPubPage:(检索布局项属性分页结果集). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return 分布结果集
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<CmsItemPropPubRespDTO> queryCmsItemPropPubPage(CmsItemPropPubReqDTO dto) throws BusinessException;

    /** 
     * queryCmsItemPropPub:(检索单个结果). <br/> 
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
     * queryCmsItemPropPubList:(查询布局项属性，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPubReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropPubRespDTO> queryCmsItemPropPubList(CmsItemPropPubReqDTO cmsItemPropPubReqDTO) throws BusinessException;
    
    /** 
     * queryCmsItemPropValuePubListForWap:(查询布局项属性，无分页). <br/> 
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
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubListForWap(CmsItemPropPubReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsItemPropValuePubList:(查询布局项属性，无分页). <br/> 
     * TODO(1、查看布局项属性关系记录).<br/> 
     * TODO(2、根据属性ID，将属性ID的记录组装成LIST.作为属性的值).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPreReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubList(CmsItemPropPubReqDTO cmsItemPropPubReqDTO) throws BusinessException;
}

