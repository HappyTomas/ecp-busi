package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015.9.25 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version
 * @since JDK 1.6
 */
public interface ICmsRecommendSV extends IGeneralSQLSV {

    /**
     * saveCmsExpertRecommend:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param dto
     * @throws BusinessException
     * @since JDK 1.6
     */
    public CmsRecommendRespDTO addCmsRecommend(CmsRecommendReqDTO dto)
            throws BusinessException;

    /**
     * updateCmsExpertRecommend:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param dto
     * @throws BusinessException
     * @since JDK 1.6
     */
    public CmsRecommendRespDTO updateCmsRecommend(CmsRecommendReqDTO dto)
            throws BusinessException;
    
    /** 
     * updateCmsRecommendByExample:(根据非主键更新). <br/> 
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
    public CmsRecommendRespDTO updateCmsRecommendByExample(CmsRecommendReqDTO dto) 
            throws BusinessException;

    /**
     * queryCmsExpertRecommendList:(查询推荐列表，无分页). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public List<CmsRecommendRespDTO> queryCmsRecommendList(CmsRecommendReqDTO dto)
            throws BusinessException;

    /**
     * queryCmsExpertRecommendPage:(查询推荐列表，分页). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public PageResponseDTO<CmsRecommendRespDTO> queryCmsRecommendPage(
            CmsRecommendReqDTO dto) throws BusinessException;

    /**
     * queryCmsExpertRecommend:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public CmsRecommendRespDTO queryCmsRecommend(CmsRecommendReqDTO dto)
            throws BusinessException;

    /**
     * deleteCmsExpertRecommend:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param id
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteCmsRecommend(String id) throws BusinessException;

    /**
     * deleteCmsExpertRecommendBatch:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param list
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteCmsRecommendBatch(List<String> list) throws BusinessException;

    /**
     * changeStatusCmsExpertRecommend:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param id
     * @param status
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void changeStatusCmsRecommend(String id, String status) throws BusinessException;

    /**
     * changeStatusCmsExpertRecommendBatch:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param list
     * @param status
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void changeStatusCmsRecommendBatch(List<String> list, String status)
            throws BusinessException;

}
