package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPicHotPreMapper;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPre;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPreCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPreCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
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
@Service("cmsPicHotPreSV")
public class CmsPicHotPreSVImpl extends GeneralSQLSVImpl implements ICmsPicHotPreSV {

    @Resource(name = "SEQ_CMS_PIC_HOT_PRE")
    private PaasSequence seqCmsPicHotPre;
    @Resource
    private CmsPicHotPreMapper cmsPicHotPreMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#saveCmsPicHotPre(com.zengshi.ecp.cms.dao.model.CmsPicHotPre)
     */
    @Override
    public CmsPicHotPreRespDTO addCmsPicHotPre(CmsPicHotPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPicHotPre bo = new CmsPicHotPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPicHotPre.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPicHotPreMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsPicHotPreRespDTO respDTO = new CmsPicHotPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#updateCmsPicHotPre(com.zengshi.ecp.cms.dao.model.CmsPicHotPre)
     */
    @Override
    public CmsPicHotPreRespDTO updateCmsPicHotPre(CmsPicHotPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPicHotPre bo = new CmsPicHotPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsPicHotPre(bo);
    }
    
    /** 
     * updateCmsPicHotPre:(更新楼层的原子方法). <br/> 
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
    public CmsPicHotPreRespDTO updateCmsPicHotPre(CmsPicHotPre bo) throws BusinessException {
        cmsPicHotPreMapper.updateByPrimaryKeySelective(bo);
        CmsPicHotPreRespDTO respDTO = new CmsPicHotPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#deleteCmsPicHotPre(java.lang.Long)
     */
    @Override
    public void deleteCmsPicHotPre(String id) throws BusinessException {
        CmsPicHotPre bo = new CmsPicHotPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPicHotPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#deleteCmsPicHotPreBatch(java.util.List)
     */
    @Override
    public void deleteCmsPicHotPreBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPicHotPre(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#changeStatusCmsPicHotPre(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPicHotPre(String id, String status) throws BusinessException {
        CmsPicHotPre bo = new CmsPicHotPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPicHotPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#changeStatusCmsPicHotPreBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPicHotPreBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPicHotPre(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#queryCmsPicHotPre(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO)
     */
    @Override
    public CmsPicHotPreRespDTO queryCmsPicHotPre(CmsPicHotPreReqDTO dto) throws BusinessException {
        CmsPicHotPreRespDTO cmsPicHotPreRespDTO = new CmsPicHotPreRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPicHotPre bo = cmsPicHotPreMapper.selectByPrimaryKey(dto.getId());
            cmsPicHotPreRespDTO = conversionObject(bo);
        }
        
        return cmsPicHotPreRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#queryCmsPicHotPreList(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO)
     */
    @Override
    public List<CmsPicHotPreRespDTO> queryCmsPicHotPreList(CmsPicHotPreReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPicHotPreCriteria cmsPicHotPreCriteria = new CmsPicHotPreCriteria();
        Criteria criteria = cmsPicHotPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getItemPropId())) {
            criteria.andItemPropIdEqualTo(dto.getItemPropId());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsPicHotPreCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsPicHotPreRespDTO> cmsPicHotPreRespDTOList =  new ArrayList<CmsPicHotPreRespDTO>();
        List<CmsPicHotPre> cmsPicHotPreList = cmsPicHotPreMapper.selectByExample(cmsPicHotPreCriteria);
        if(CollectionUtils.isNotEmpty(cmsPicHotPreList)){
            for(CmsPicHotPre bo :cmsPicHotPreList){
                CmsPicHotPreRespDTO cmsPicHotPreRespDTO = conversionObject(bo);
                cmsPicHotPreRespDTOList.add(cmsPicHotPreRespDTO);
            }
        }
        
        return cmsPicHotPreRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV#queryCmsPicHotPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPicHotPreRespDTO> queryCmsPicHotPrePage(CmsPicHotPreReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPicHotPreCriteria cmsPicHotPreCriteria = new CmsPicHotPreCriteria();
        Criteria criteria = cmsPicHotPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getItemPropId())) {
            criteria.andItemPropIdEqualTo(dto.getItemPropId());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsPicHotPreCriteria.setOrderByClause("CREATE_TIME ASC");
        cmsPicHotPreCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPicHotPreCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPicHotPreCriteria,false,new PaginationCallback<CmsPicHotPre, CmsPicHotPreRespDTO>(){

            @Override
            public List<CmsPicHotPre> queryDB(BaseCriteria criteria) {
                return cmsPicHotPreMapper.selectByExample((CmsPicHotPreCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPicHotPreMapper.countByExample((CmsPicHotPreCriteria)criteria);
            }

            @Override
            public CmsPicHotPreRespDTO warpReturnObject(CmsPicHotPre bo) {
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
    private CmsPicHotPreRespDTO conversionObject(CmsPicHotPre bo){
        CmsPicHotPreRespDTO dto = new CmsPicHotPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        /*String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);*/
        
        return dto;
    }
    
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#deleteCmsLayoutTypeSelective(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO)
     */
    @Override
    public void deleteCmsPicHotPreByExample(CmsPicHotPreReqDTO dto) throws BusinessException {
        /* 1.根据条件检索楼层 */
        CmsPicHotPreCriteria cmsPicHotPreCriteria = new CmsPicHotPreCriteria();
        Criteria criteria = cmsPicHotPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getItemPropId())) {
            criteria.andItemPropIdEqualTo(dto.getItemPropId());
        }
        
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        
        CmsPicHotPre bo = new CmsPicHotPre();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        
        cmsPicHotPreMapper.updateByExampleSelective(bo, cmsPicHotPreCriteria);
    }

}
