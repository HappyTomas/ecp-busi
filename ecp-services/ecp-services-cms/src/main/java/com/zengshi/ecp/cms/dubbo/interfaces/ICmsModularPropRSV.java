package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropRespDTO;
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
public interface ICmsModularPropRSV {
    
    /** 
     * saveCmsModularProp:(新增模块与属性关系). <br/> 
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
    public CmsModularPropRespDTO addCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException;
    
    /** 
     * saveCmsModularProp:(批量新增模块与属性关系). <br/> 
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
    public void addCmsModularPropList(List<CmsModularPropReqDTO> list) throws BusinessException;
    
    /** 
     * deleteCmsModularProp:(逻辑删除模块与属性关系). <br/> 
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
    public void deleteCmsModularProp(String id) throws BusinessException;
    /** 
     * deleteCmsModularProp:(逻辑删除模块与属性关系). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param modularId:modularId
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsModularPropByModularId(String modularId) throws BusinessException;
    
    /** 
     * deleteCmsModularPropBatch:(批量逻辑删除). <br/> 
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
    public void deleteCmsModularPropBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsModularProp:(使生效、失效). <br/> 
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
    public void changeStatusCmsModularProp(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsModularPropBatch:(批量使生效、失效). <br/> 
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
    public void changeStatusCmsModularPropBatch(List<String> list,String status) throws BusinessException;
    
    /** 
     * updateCmsModularProp:(更新模块与属性关系). <br/> 
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
    public CmsModularPropRespDTO updateCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsModularParaPropList:(查询模块下的属性列表). <br/> 
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
    public List<CmsModularParaPropRespDTO> queryCmsModularParaPropList(CmsModularPropReqDTO dto)throws BusinessException;
    
    /** 
     * queryCmsModularPropPage:(检索模块与属性关系分页结果集). <br/> 
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
    public PageResponseDTO<CmsModularPropRespDTO> queryCmsModularPropPage(CmsModularPropReqDTO dto) throws BusinessException;

    /** 
     * queryCmsModularProp:(检索单个结果). <br/> 
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
    public CmsModularPropRespDTO queryCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsModularPropList:(查询模块与属性关系，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsModularPropReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsModularPropRespDTO> queryCmsModularPropList(CmsModularPropReqDTO cmsModularPropReqDTO) throws BusinessException;
    
}

