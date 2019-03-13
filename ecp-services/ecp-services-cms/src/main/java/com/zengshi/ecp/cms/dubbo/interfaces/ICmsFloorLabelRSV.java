package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
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
public interface ICmsFloorLabelRSV {
    
    /** 
     * saveCmsFloor:(新增楼层标签). <br/> 
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
    public CmsFloorLabelRespDTO addCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException;
    
    /** 
     * deleteCmsFloorLabel:(逻辑删除楼层标签). <br/> 
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
    public void deleteCmsFloorLabel(String id) throws BusinessException;
    
    /** 
     * deleteCmsFloorLabelBatch:(批量逻辑删除). <br/> 
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
    public void deleteCmsFloorLabelBatch(List<String> list) throws BusinessException;
    
    /** 
     * changeStatusCmsFloorLabel:(使生效、失效). <br/> 
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
    public void changeStatusCmsFloorLabel(String id,String status) throws BusinessException;
    
    /** 
     * changeStatusCmsFloorLabelBatch:(批量使生效、失效). <br/> 
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
    public void changeStatusCmsFloorLabelBatch(List<String> list,String status) throws BusinessException;
    
    /** 
     * updateCmsFloorLabel:(更新楼层标签). <br/> 
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
    public CmsFloorLabelRespDTO updateCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorLabelPage:(检索楼层标签分页结果集). <br/> 
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
    public PageResponseDTO<CmsFloorLabelRespDTO> queryCmsFloorLabelPage(CmsFloorLabelReqDTO dto) throws BusinessException;

    /** 
     * queryCmsFloorLabel:(检索单个结果). <br/> 
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
    public CmsFloorLabelRespDTO queryCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException;
    
    /** 
     * queryCmsFloorLabelList:(查询楼层标签，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsFloorLabelReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsFloorLabelRespDTO> queryCmsFloorLabelList(CmsFloorLabelReqDTO cmsFloorLabelReqDTO) throws BusinessException;
    
}

