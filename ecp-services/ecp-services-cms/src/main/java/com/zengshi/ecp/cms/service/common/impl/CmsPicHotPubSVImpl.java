package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPicHotPubMapper;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPub;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPubCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPicHotPubCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV;
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
@Service("cmsPicHotPubSV")
public class CmsPicHotPubSVImpl extends GeneralSQLSVImpl implements ICmsPicHotPubSV {

    @Resource(name = "SEQ_CMS_PIC_HOT_PUB")
    private PaasSequence seqCmsPicHotPub;
    @Resource
    private CmsPicHotPubMapper cmsPicHotPubMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#saveCmsPicHotPub(com.zengshi.ecp.cms.dao.model.CmsPicHotPub)
     */
    @Override
    public CmsPicHotPubRespDTO addCmsPicHotPub(CmsPicHotPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPicHotPub bo = new CmsPicHotPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPicHotPub.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPicHotPubMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsPicHotPubRespDTO respDTO = new CmsPicHotPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#updateCmsPicHotPub(com.zengshi.ecp.cms.dao.model.CmsPicHotPub)
     */
    @Override
    public CmsPicHotPubRespDTO updateCmsPicHotPub(CmsPicHotPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPicHotPub bo = new CmsPicHotPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsPicHotPub(bo);
    }
    
    /** 
     * updateCmsPicHotPub:(更新楼层的原子方法). <br/> 
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
    public CmsPicHotPubRespDTO updateCmsPicHotPub(CmsPicHotPub bo) throws BusinessException {
        cmsPicHotPubMapper.updateByPrimaryKeySelective(bo);
        CmsPicHotPubRespDTO respDTO = new CmsPicHotPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#deleteCmsPicHotPub(java.lang.Long)
     */
    @Override
    public void deleteCmsPicHotPub(String id) throws BusinessException {
        CmsPicHotPub bo = new CmsPicHotPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPicHotPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#deleteCmsPicHotPubBatch(java.util.List)
     */
    @Override
    public void deleteCmsPicHotPubBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPicHotPub(id);
            }
        }
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#deleteCmsPicHotPubByExample(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO)
     */
    @Override
    public int deleteCmsPicHotPubByExample(CmsPicHotPubReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsPicHotPub bo = new CmsPicHotPub();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsPicHotPubCriteria cmsPicHotPubCriteria = new CmsPicHotPubCriteria();
        Criteria criteria = cmsPicHotPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getHotId())) {
            criteria.andHotIdEqualTo(dto.getHotId());
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
        
        return cmsPicHotPubMapper.updateByExampleSelective(bo, cmsPicHotPubCriteria);
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#changeStatusCmsPicHotPub(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPicHotPub(String id, String status) throws BusinessException {
        CmsPicHotPub bo = new CmsPicHotPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPicHotPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#changeStatusCmsPicHotPubBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPicHotPubBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPicHotPub(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#queryCmsPicHotPub(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO)
     */
    @Override
    public CmsPicHotPubRespDTO queryCmsPicHotPub(CmsPicHotPubReqDTO dto) throws BusinessException {
        CmsPicHotPubRespDTO cmsPicHotPubRespDTO = new CmsPicHotPubRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPicHotPub bo = cmsPicHotPubMapper.selectByPrimaryKey(dto.getId());
            cmsPicHotPubRespDTO = conversionObject(bo);
        }
        
        return cmsPicHotPubRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#queryCmsPicHotPubList(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO)
     */
    @Override
    public List<CmsPicHotPubRespDTO> queryCmsPicHotPubList(CmsPicHotPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPicHotPubCriteria cmsPicHotPubCriteria = new CmsPicHotPubCriteria();
        Criteria criteria = cmsPicHotPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getHotId())) {
            criteria.andHotIdEqualTo(dto.getHotId());
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
        cmsPicHotPubCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsPicHotPubRespDTO> cmsPicHotPubRespDTOList =  new ArrayList<CmsPicHotPubRespDTO>();
        List<CmsPicHotPub> cmsPicHotPubList = cmsPicHotPubMapper.selectByExample(cmsPicHotPubCriteria);
        if(CollectionUtils.isNotEmpty(cmsPicHotPubList)){
            for(CmsPicHotPub bo :cmsPicHotPubList){
                CmsPicHotPubRespDTO cmsPicHotPubRespDTO = conversionObject(bo);
                cmsPicHotPubRespDTOList.add(cmsPicHotPubRespDTO);
            }
        }
        
        return cmsPicHotPubRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV#queryCmsPicHotPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPicHotPubRespDTO> queryCmsPicHotPubPage(CmsPicHotPubReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPicHotPubCriteria cmsPicHotPubCriteria = new CmsPicHotPubCriteria();
        Criteria criteria = cmsPicHotPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getHotId())) {
            criteria.andHotIdEqualTo(dto.getHotId());
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
        cmsPicHotPubCriteria.setOrderByClause("CREATE_TIME ASC");
        cmsPicHotPubCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPicHotPubCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPicHotPubCriteria,false,new PaginationCallback<CmsPicHotPub, CmsPicHotPubRespDTO>(){

            @Override
            public List<CmsPicHotPub> queryDB(BaseCriteria criteria) {
                return cmsPicHotPubMapper.selectByExample((CmsPicHotPubCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPicHotPubMapper.countByExample((CmsPicHotPubCriteria)criteria);
            }

            @Override
            public CmsPicHotPubRespDTO warpReturnObject(CmsPicHotPub bo) {
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
    private CmsPicHotPubRespDTO conversionObject(CmsPicHotPub bo){
        CmsPicHotPubRespDTO dto = new CmsPicHotPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        /*String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);*/
        
        return dto;
    }


}
