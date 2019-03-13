package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
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
public interface ICmsAdvertiseRSV {
    
    /** 
     * saveCmsAdvertise:(新增广告). <br/> 
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
    public CmsAdvertiseRespDTO addCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException;
    
    /** 
     * deleteCmsAdvertise:(逻辑删除广告). <br/> 
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
    public void deleteCmsAdvertise(String id) throws BusinessException;
    
    /** 
     * deleteCmsAdvertiseBatch:(批量逻辑删除). <br/> 
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
    public void deleteCmsAdvertiseBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsAdvertise:(使生效、失效). <br/> 
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
    public void changeStatusCmsAdvertise(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsAdvertiseBatch:(批量使生效、失效). <br/> 
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
    public void changeStatusCmsAdvertiseBatch(List<String> list,String status) throws BusinessException;
    
    /** 
     * queryCmsAdvertiseList:(查询楼层广告，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsAdvertiseReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsAdvertiseRespDTO> queryCmsAdvertiseList(CmsAdvertiseReqDTO cmsAdvertiseReqDTO) throws BusinessException;
    
    /** 
     * updateCmsAdvertise:(更新广告). <br/> 
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
    public CmsAdvertiseRespDTO updateCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsAdvertisePage:(检索广告分页结果集). <br/> 
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
    public PageResponseDTO<CmsAdvertiseRespDTO> queryCmsAdvertisePage(CmsAdvertiseReqDTO dto) throws BusinessException;

    /** 
     * queryCmsAdvertise:(检索单个结果). <br/> 
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
    public CmsAdvertiseRespDTO queryCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException;
    
}

