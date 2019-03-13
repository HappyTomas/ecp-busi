package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2016年2月24日下午4:54:34  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public interface ICmsAppFloorRSV {
    /** 
     * addCmsAppFloor:(新增app楼层). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsAppFloorRespDTO addCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException;
    
    /** 
     * deleteCmsAppFloor:(逻辑删除app楼层). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param id:主键
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsAppFloor(String id) throws BusinessException;
    
    /** 
     * deleteCmsAppFloorBatch:(批量逻辑删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jzhanbh 
     * @param list：主键LIST集合
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void deleteCmsAppFloorBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsAppFloor:(使生效、失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh iangzh 
     * @param id：主键
     * @param status：状态
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsAppFloor(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsAppFloorBatch:(批量使生效、失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param list：主键的LIST集合
     * @param status：状态
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void changeStatusCmsAppFloorBatch(List<String> list,String status) throws BusinessException;
    
    /** 
     * updateCmsAppFloor:(更新楼层). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsAppFloorRespDTO updateCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsAppFloorPage:(检索楼层分页结果集). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @return 分布结果集
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<CmsAppFloorRespDTO> queryCmsAppFloorPage(CmsAppFloorReqDTO dto) throws BusinessException;

    /** 
     * queryCmsAppFloor:(检索单个结果). <br/> 
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
    public CmsAppFloorRespDTO queryCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsAppFloorList:(查询楼层，无分页). <br/> 
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
    public List<CmsAppFloorRespDTO> queryCmsAppFloorList(CmsAppFloorReqDTO dto) throws BusinessException;
}

