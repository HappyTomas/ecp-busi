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

import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: 页面信息操作对外服务类接口<br>
 * Date:2015-8-6下午2:41:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version  
 * @since JDK 1.7
 */
public interface ICmsInfoRSV {
    
    /**
     * 
     * saveCmsInfo:(这里用一句话描述这个方法的作用).页面信息新增方法 <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param cmsInfoDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CmsInfoRespDTO saveCmsInfo(CmsInfoReqDTO cmsInfoDTO) throws BusinessException;
    
    /**
     * 
     * updateCmsInfo:(这里用一句话描述这个方法的作用).修改页面信息 <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param cmsInfoDTO 
     * @since JDK 1.7
     */
    public CmsInfoRespDTO updateCmsInfo(CmsInfoReqDTO cmsInfoDTO);
    
    /**
     * 
     * queryCmsInfo:(这里用一句话描述这个方法的作用). 根据ID查询页面信息<br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param cmsInfoDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CmsInfoRespDTO queryCmsInfo(CmsInfoReqDTO cmsInfoDTO) throws BusinessException;
    
    /**
     * 
     * deleteCmsInfo:(这里用一句话描述这个方法的作用). 根据ID置页面信息为删除状态<br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param cmsInfoDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteCmsInfo(CmsInfoReqDTO cmsInfoDTO) throws BusinessException;
    
    /**
     * 根据条件查询页面信息列表（分页）
     * queryCmsInfoList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param cmsInfoDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<CmsInfoRespDTO> queryCmsInfoPage(CmsInfoReqDTO cmsInfoDTO) throws BusinessException;

    /** 
     * cmsInfoReqDTO:(查询页面信息，无分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsInfoRespDTO> queryCmsInfoList(CmsInfoReqDTO cmsInfoReqDTO) throws BusinessException;
    
    /** 使页面信息生效
     * activeCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7 
     */ 
    public void activeCmsInfo(CmsInfoReqDTO dto) throws BusinessException;

    /** 
     * 使页面信息失效
     * invalidCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7 
     */ 
    public void invalidCmsInfo(CmsInfoReqDTO dto) throws BusinessException;
    
}

