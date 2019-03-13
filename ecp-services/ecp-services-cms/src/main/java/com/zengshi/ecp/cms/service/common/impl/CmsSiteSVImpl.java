package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsSiteMapper;
import com.zengshi.ecp.cms.dao.model.CmsSite;
import com.zengshi.ecp.cms.dao.model.CmsSiteCriteria;
import com.zengshi.ecp.cms.dao.model.CmsSiteCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:59 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Service("cmsSiteSV")
public class CmsSiteSVImpl extends GeneralSQLSVImpl implements ICmsSiteSV {

    @Resource(name = "SEQ_CMS_SITE")
    private PaasSequence seqCmsSite;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private CmsSiteMapper cmsSiteMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#saveCmsSite(com.zengshi.ecp.cms.dao.model.CmsSite)
     */
    @Override
    public CmsSiteRespDTO addCmsSite(CmsSiteReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsSite bo = new CmsSite();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsSite.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsSiteMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsSiteRespDTO respDTO = new CmsSiteRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        /*4.将有效的站点刷入缓存*/
        if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equals(respDTO.getStatus())){
            CmsCacheUtil.addCmsSiteCache(respDTO.getId());
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#updateCmsSite(com.zengshi.ecp.cms.dao.model.CmsSite)
     */
    @Override
    public CmsSiteRespDTO updateCmsSite(CmsSiteReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsSite bo = new CmsSite();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsSite(bo);
    }
    
    /** 
     * updateCmsSite:(更新楼层的原子方法). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param bo
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsSiteRespDTO updateCmsSite(CmsSite bo) throws BusinessException {
        cmsSiteMapper.updateByPrimaryKeySelective(bo);
        CmsSiteRespDTO respDTO = new CmsSiteRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        /*4.将有效的站点刷入缓存*/
        if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equals(respDTO.getStatus())){
            CmsCacheUtil.addCmsSiteCache(respDTO.getId());
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#deleteCmsSite(java.lang.Long)
     */
    @Override
    public void deleteCmsSite(String id) throws BusinessException {
        CmsSite bo = new CmsSite();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsSite(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#deleteCmsSiteBatch(java.util.List)
     */
    @Override
    public void deleteCmsSiteBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsSite(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#changeStatusCmsSite(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsSite(String id, String status) throws BusinessException {
        CmsSite bo = new CmsSite();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsSite(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#changeStatusCmsSiteBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsSiteBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsSite(id, status);
            }
        }
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#changeStatusCmsSite(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void doDefaultCmsSite(String id, String isdefault) throws BusinessException {
        CmsSite bo = new CmsSite();
        bo.setId(Long.parseLong(id));
        bo.setIsdefault(isdefault);
        this.updateCmsSite(bo);
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#changeStatusCmsSiteBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void doDefaultCmsSiteBatch(List<String> list, String isdefault)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.doDefaultCmsSite(id, isdefault);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#queryCmsSite(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO)
     */
    @Override
    public CmsSiteRespDTO queryCmsSite(CmsSiteReqDTO dto) throws BusinessException {
        CmsSiteRespDTO cmsSiteRespDTO = new CmsSiteRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsSite bo = cmsSiteMapper.selectByPrimaryKey(dto.getId());
            cmsSiteRespDTO = conversionObject(bo);
        }
        
        return cmsSiteRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#queryCmsSiteList(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO)
     */
    @Override
    public List<CmsSiteRespDTO> queryCmsSiteList(CmsSiteReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsSiteCriteria cmsSiteCriteria = new CmsSiteCriteria();
        Criteria criteria = cmsSiteCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getSiteName())) {
            criteria.andSiteNameLike("%"+dto.getSiteName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getIsdefault())) {
            criteria.andIsdefaultEqualTo(dto.getIsdefault());
        }
        cmsSiteCriteria.setOrderByClause("ISDEFAULT DESC,CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsSiteRespDTO> cmsSiteRespDTOList =  new ArrayList<CmsSiteRespDTO>();
        List<CmsSite> cmsSiteList = cmsSiteMapper.selectByExample(cmsSiteCriteria);
        if(CollectionUtils.isNotEmpty(cmsSiteList)){
            for(CmsSite bo :cmsSiteList){
                CmsSiteRespDTO cmsSiteRespDTO = conversionObject(bo);
                cmsSiteRespDTOList.add(cmsSiteRespDTO);
            }
        }
        
        return cmsSiteRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV#queryCmsSitePage(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsSiteRespDTO> queryCmsSitePage(CmsSiteReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsSiteCriteria cmsSiteCriteria = new CmsSiteCriteria();
        Criteria criteria = cmsSiteCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getSiteName())) {
            criteria.andSiteNameLike("%"+dto.getSiteName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getIsdefault())) {
            criteria.andIsdefaultEqualTo(dto.getIsdefault());
        }
        cmsSiteCriteria.setOrderByClause("ISDEFAULT DESC,CREATE_TIME DESC");
        cmsSiteCriteria.setLimitClauseCount(dto.getPageSize());
        cmsSiteCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsSiteCriteria,false,new PaginationCallback<CmsSite, CmsSiteRespDTO>(){

            @Override
            public List<CmsSite> queryDB(BaseCriteria criteria) {
                return cmsSiteMapper.selectByExample((CmsSiteCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsSiteMapper.countByExample((CmsSiteCriteria)criteria);
            }

            @Override
            public CmsSiteRespDTO warpReturnObject(CmsSite bo) {
                return conversionObject(bo);
            }
        
        });

    }
    
    /** 
     * conversionObject:(将bo转换成DTO，同时翻译有关字段). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param bo 
     * @return 
     * @since JDK 1.6 
     */ 
    private CmsSiteRespDTO conversionObject(CmsSite bo){
        CmsSiteRespDTO dto = new CmsSiteRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String isdefaultZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsdefault());
        dto.setIsdefaultZH(isdefaultZH);
        
        return dto;
    }

}
