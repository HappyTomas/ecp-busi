/** 
 * Project Name:ecp-services-cms 
 * File Name:ICmsInfoRSV.java 
 * Package Name:com.zengshi.ecp.cms.dubbo.interfaces 
 * Date:2015-8-6下午2:41:35 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月17日下午9:12:00  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6
 */
public interface ICmsPlaceRSV {
    
    /** 
     * addCmsPlace:(添加内容位置). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsPlaceReqDTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsPlaceRespDTO addCmsPlace(CmsPlaceReqDTO cmsPlaceReqDTO) throws BusinessException;
    
    /** 
     * updateCmsPlace:(更新内容位置). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsPlaceReqDTO 
     * @since JDK 1.6 
     */ 
    public CmsPlaceRespDTO updateCmsPlace(CmsPlaceReqDTO cmsPlaceReqDTO);
    
    /** 
     * deleteCmsPlace:(通过ID删除内容位置). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsPlaceReqDTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsPlace(String id) throws BusinessException;
    
    /** 
     * deleteCmsPlaceBatch:(批量逻辑删除). <br/> 
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
    public void deleteCmsPlaceBatch(List<String> list) throws BusinessException;
    
    /** 
     * queryCmsPlace:(通过ID查询内容位置). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsPlaceReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsPlaceRespDTO queryCmsPlace(CmsPlaceReqDTO cmsPlaceReqDTO) throws BusinessException;
    
    /** 
     * queryCmsPlaceList:(查询内容位置，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsPlaceReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsPlaceRespDTO> queryCmsPlaceList(CmsPlaceReqDTO cmsPlaceReqDTO) throws BusinessException;
    
    /** 
     * queryCmsPlacePage:(查询内容位置，分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsPlaceReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<CmsPlaceRespDTO> queryCmsPlacePage(CmsPlaceReqDTO cmsPlaceReqDTO) throws BusinessException;
    
    
    /** 
     * changeStatusCmsFloor:(使生效、失效). <br/> 
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
    public void changeStatusCmsPlace(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsFloorBatch:(批量使生效、失效). <br/> 
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
    public void changeStatusCmsPlaceBatch(List<String> list,String status) throws BusinessException;
    
}

