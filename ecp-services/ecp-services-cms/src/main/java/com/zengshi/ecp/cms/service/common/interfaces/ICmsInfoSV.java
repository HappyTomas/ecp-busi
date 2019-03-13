/** 
 * Project Name:ecp-services-cms 
 * File Name:ICmsInfoSV.java 
 * Package Name:com.zengshi.ecp.cms.service.common.interfaces 
 * Date:2015-8-6下午2:24:52 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.cms.service.common.interfaces;



import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: 页面信息操作接口类<br>
 * Date:2015-8-6下午2:24:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version  
 * @since JDK 1.7 
 */
public interface ICmsInfoSV extends IGeneralSQLSV{
    
    /**
     * 页面信息新增操作，返回页面信息ID
     * saveCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CmsInfoRespDTO saveCmsInfo(CmsInfoReqDTO dto) throws BusinessException;
    
    /**
     * 根据ID修改页面信息
     * updateCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param cmsInfo
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CmsInfoRespDTO updateCmsInfo(CmsInfoReqDTO cmsInfoDTO) throws BusinessException;
    
    /**
     * 根据ID查询页面信息
     * queryCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CmsInfoRespDTO queryCmsInfo(long id) throws BusinessException;
    
    /**
     * 根据ID置页面信息为删除状态
     * deleteCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteCmsInfo(CmsInfoReqDTO dto) throws BusinessException;
    
    /**
     * 批量置页面信息为删除状态
     * deleteCmsInfosBatch:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param list
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteCmsInfosBatch(List<String> list) throws BusinessException;
    
    /**
     * 根据ID置页面信息为生效状态
     * activeCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void activeCmsInfo(CmsInfoReqDTO dto) throws BusinessException;
    
    /**
     * 根据ID置页面信息为失效状态
     * invalidCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void invalidCmsInfo(CmsInfoReqDTO dto)throws BusinessException;
    
    /**
     * 分页查询页面信息
     * findByPage:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param baseInfo
     * @return 
     * @since JDK 1.7
     */
    public PageResponseDTO<CmsInfoRespDTO> queryCmsInfoPage(CmsInfoReqDTO cmsInfoDTO);
    
    /** 
     * queryCmsInfoList:(根据查询条件查询页面信息列表). <br/> 
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
    public List<CmsInfoRespDTO> queryCmsInfoList(CmsInfoReqDTO dto) throws BusinessException;
    
}

