package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dao.mapper.common.CmsAppFloorMapper;
import com.zengshi.ecp.cms.dao.model.CmsAppFloor;
import com.zengshi.ecp.cms.dao.model.CmsAppFloorCriteria;
import com.zengshi.ecp.cms.dao.model.CmsAppFloorCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2016年2月23日下午4:58:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsAppFloorSVImpl extends GeneralSQLSVImpl implements ICmsAppFloorSV {

    @Resource(name = "SEQ_CMS_APP_FLOOR")
    private PaasSequence seqCmsAppFloor;
    
    @Resource
    private CmsAppFloorMapper cmsAppFloorMapper;
    
    @Resource
    private ICmsAppFloorDataSV cmsAppFloorDataSV;
    
    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsFloorTemplateSV cmsFloorTemplateSV;
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#addCmsAppFloor(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public CmsAppFloorRespDTO addCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException {
        List<CmsAppFloorDataRespDTO> cmsAppFloorDataRespDTOList = new ArrayList<CmsAppFloorDataRespDTO>();
        /*1.将入参组装成bo*/
        CmsAppFloor bo = new CmsAppFloor();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        Long id = seqCmsAppFloor.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口存appFloor数据*/
        cmsAppFloorMapper.insertSelective(bo);
        
        /*3.调服务存appFloorData数据*/
        if(CollectionUtils.isNotEmpty(dto.getCmsAppFloorDataReqDTOList())){
            for(CmsAppFloorDataReqDTO cmsAppFloorDataReqDTO : dto.getCmsAppFloorDataReqDTOList()){
                cmsAppFloorDataReqDTO.setAppFloorId(id);
                cmsAppFloorDataRespDTOList.add(cmsAppFloorDataSV.addCmsAppFloorData(cmsAppFloorDataReqDTO));
            }
        }
        
        /*4.将结果返回*/
        CmsAppFloorRespDTO respDTO = new CmsAppFloorRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        respDTO.setCmsAppFloorDataRespDtoList(cmsAppFloorDataRespDTOList);
        return respDTO;
    }

    /**
     * 
     * TODO 更新appFloor数据（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#updateCmsAppFloor(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public CmsAppFloorRespDTO updateCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException {
        /*1.将入参封装成bo*/
        CmsAppFloor bo = new CmsAppFloor();
        ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.调服务更新appFloorData数据*/
        /*3.调服务存appFloorData数据*/
        if(CollectionUtils.isNotEmpty(dto.getCmsAppFloorDataReqDTOList())){
            for(CmsAppFloorDataReqDTO cmsAppFloorDataReqDTO : dto.getCmsAppFloorDataReqDTOList()){
                if(StringUtil.isNotEmpty(cmsAppFloorDataReqDTO.getId())){
                    cmsAppFloorDataSV.updateCmsAppFloorData(cmsAppFloorDataReqDTO);
                }else{
                    cmsAppFloorDataReqDTO.setAppFloorId(dto.getId());
                    cmsAppFloorDataSV.addCmsAppFloorData(cmsAppFloorDataReqDTO);
                }
            }
        }
        
        /*3.更新appFloor数据  同时返回结果*/
        return this.updateCmsAppFloor(bo);
    }
    /**
     * 
     * TODO 查询appFloor列表，无分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#queryCmsAppFloorList(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public List<CmsAppFloorRespDTO> queryCmsAppFloorList(CmsAppFloorReqDTO dto)
            throws BusinessException {
        if(dto == null){
            dto = new CmsAppFloorReqDTO();
        }
        /* 1.组装查询条件 */ 
        CmsAppFloorCriteria cmsAppFloorCriteria = new CmsAppFloorCriteria();
        this.dtoToCriteria(cmsAppFloorCriteria.createCriteria(), dto);
        cmsAppFloorCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /* 2.获取查询结果 */
        List<CmsAppFloorRespDTO> cmsAppFloorRespDTOList = new ArrayList<CmsAppFloorRespDTO>();
        List<CmsAppFloor> cmsAppFloorList = cmsAppFloorMapper.selectByExample(cmsAppFloorCriteria);
        
        if(CollectionUtils.isNotEmpty(cmsAppFloorList)){
            for(CmsAppFloor bo : cmsAppFloorList){
                CmsAppFloorRespDTO cmsAppFloorRespDTO = conversionObject(bo);
                if(cmsAppFloorRespDTO != null && StringUtil.isNotEmpty(cmsAppFloorRespDTO.getId())){
                    cmsAppFloorRespDTOList.add(cmsAppFloorRespDTO);
                }
            }
        }
        return cmsAppFloorRespDTOList;
    }

    /**
     * 
     * TODO 查询appFloor分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#queryCmsAppFloorPage(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public PageResponseDTO<CmsAppFloorRespDTO> queryCmsAppFloorPage(CmsAppFloorReqDTO dto)
            throws BusinessException {
        if(dto == null){
            dto = new CmsAppFloorReqDTO();
        }
        /* 1.组装查询条件 */ 
        CmsAppFloorCriteria cmsAppFloorCriteria = new CmsAppFloorCriteria();
        this.dtoToCriteria(cmsAppFloorCriteria.createCriteria(), dto);
        cmsAppFloorCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsAppFloorCriteria.setLimitClauseCount(dto.getPageSize());
        cmsAppFloorCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        /* 2.返回数据 */
        return super.queryByPagination(dto, cmsAppFloorCriteria, false, 
                new PaginationCallback<CmsAppFloor, CmsAppFloorRespDTO>() {

                    @Override
                    public List<CmsAppFloor> queryDB(BaseCriteria criteria) {
                        return cmsAppFloorMapper.selectByExample((CmsAppFloorCriteria)criteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return cmsAppFloorMapper.countByExample((CmsAppFloorCriteria)criteria);
                    }

                    @Override
                    public CmsAppFloorRespDTO warpReturnObject(CmsAppFloor bo) {
                        return conversionObject(bo);
                    }
                });
    }
    /**
     * TODO 查询单个数据（可选）.
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#queryCmsAppFloor(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public CmsAppFloorRespDTO queryCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException {
        CmsAppFloorRespDTO respDto = new CmsAppFloorRespDTO();
        if(dto!=null && StringUtil.isNotEmpty(dto.getId())){
            CmsAppFloor bo = cmsAppFloorMapper.selectByPrimaryKey(dto.getId());
            respDto = this.conversionObject(bo);
        }
        return respDto;
    }

    /**
     * 
     * TODO 删除app楼层（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#deleteCmsAppFloor(java.lang.String)
     */
    @Override
    public void deleteCmsAppFloor(String id) throws BusinessException {
        /* 1.删除app楼层 */
        CmsAppFloor bo = new CmsAppFloor();
        if(StringUtil.isNotBlank(id)){
            bo.setId(Long.parseLong(id));
            bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
            this.updateCmsAppFloor(bo);
        }
        
        //使失效楼层模板时，需要更新楼层内容位置状态（删除）
        /* 2.查询对应的app楼层数据 */
        CmsAppFloorDataReqDTO cmsAppFloorDataReqDTO = new CmsAppFloorDataReqDTO();
        cmsAppFloorDataReqDTO.setAppFloorId(Long.parseLong(id));
        cmsAppFloorDataReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        List<CmsAppFloorDataRespDTO> cmsAppFloorDataRespDTOList = cmsAppFloorDataSV.queryCmsAppFloorDataList(cmsAppFloorDataReqDTO);
        
        /* 3.更新楼层内容位置 */
        if(CollectionUtils.isNotEmpty(cmsAppFloorDataRespDTOList)){
            for(CmsAppFloorDataRespDTO cmsAppFloorDataRespDTO : cmsAppFloorDataRespDTOList){
                cmsAppFloorDataSV.deleteCmsAppFloorData(cmsAppFloorDataRespDTO.getId().toString());
            }
        }
    }

    /**
     * 
     * TODO 批量删除app楼层（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#deleteCmsAppFloorBatch(java.util.List)
     */
    @Override
    public void deleteCmsAppFloorBatch(List<String> list) throws BusinessException {
        if(CollectionUtils.isNotEmpty(list)){
            for(int i = 0 ; i < list.size() ; i++){
                String id = list.get(i);
                this.deleteCmsAppFloor(id);
            }
        }
    }

    /**
     * 
     * TODO 修改app楼层状态（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#changeStatusCmsAppFloor(java.lang.String, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloor(String id, String status) throws BusinessException {
        CmsAppFloor bo = new CmsAppFloor();
        if(StringUtil.isNotBlank(id)){
            bo.setId(Long.parseLong(id));
            bo.setStatus(status);
            if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equalsIgnoreCase(status)){
                this.deleteCmsAppFloor(id);
            }else{
                this.updateCmsAppFloor(bo); 
            }
        }
    }

    /**
     * 
     * TODO 批量修改app楼层状态（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV#changeStatusCmsAppFloorBatch(java.util.List, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloorBatch(List<String> list, String status)
            throws BusinessException {
        if(CollectionUtils.isNotEmpty(list)){
            for(int i = 0 ; i < list.size() ; i++){
                String id = list.get(i);
                this.changeStatusCmsAppFloor(id, status);;
            }
        }
    }

    //---------私有方法---------//
    /**
     * 
     * updateCmsAppFloor:(原子方法，更新appFloor数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param bo
     * @return AppFloor对象
     * @since JDK 1.6
     */
    private CmsAppFloorRespDTO updateCmsAppFloor(CmsAppFloor bo) {
        CmsAppFloorRespDTO respDTO= new CmsAppFloorRespDTO();
        
        if (bo != null) {
            cmsAppFloorMapper.updateByPrimaryKeySelective(bo);
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        //返回结果
        return respDTO;
    }
    
    /**
     * 
     * dtoToCriteria:(将dto转换为查询条件Criteria). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param criteria
     * @param dto
     * @return 
     * @since JDK 1.6
     */
    private Criteria dtoToCriteria (Criteria criteria , CmsAppFloorReqDTO dto){
       
        if(criteria != null && dto != null){
            if(StringUtil.isNotEmpty(dto.getId())){
                criteria.andIdEqualTo(dto.getId());
            }
            if(StringUtil.isNotBlank(dto.getFloorName())){
                criteria.andFloorNameLike("%"+dto.getFloorName()+"%");
            }
            if(StringUtil.isNotBlank(dto.getStatus())){
                criteria.andStatusEqualTo(dto.getStatus());
            }
            if(StringUtil.isNotEmpty(dto.getSortNo())){
                criteria.andSortNoEqualTo(dto.getSortNo());
            }
            if(StringUtil.isNotBlank(dto.getRemark())){
                criteria.andRemarkLike("%"+dto.getRemark()+"%");
            }
            if(StringUtil.isNotEmpty(dto.getSiteId())){
                criteria.andSiteIdEqualTo(dto.getSiteId());
            }
            if(StringUtil.isNotEmpty(dto.getFloorTemplateId())){
                criteria.andFloorTemplateIdEqualTo(dto.getFloorTemplateId());
            }
        }
        
        return criteria;
    }
    
    /**
     * conversionObject:(将bo转换成DTO，同时翻译有关字段). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @param bo
     * @return
     * @since JDK 1.6
     */
    private CmsAppFloorRespDTO conversionObject(CmsAppFloor bo) {
        CmsAppFloorRespDTO dto = new CmsAppFloorRespDTO();
        if (bo != null) {
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        // 1. 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
            cmsSiteReqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO cmsSiteRespDTO = cmsSiteSV.queryCmsSite(cmsSiteReqDTO);
            if(cmsSiteRespDTO != null){
                dto.setSiteName(cmsSiteRespDTO.getSiteName());
            }
        }
        //2.查询楼层模板服务，获取楼层模板对应的名称  floorTemplateName
        if(StringUtil.isNotEmpty(dto.getFloorTemplateId())){
            CmsFloorTemplateReqDTO cmsFloorTemplateReqDTO = new CmsFloorTemplateReqDTO();
            cmsFloorTemplateReqDTO.setId(dto.getFloorTemplateId());
            CmsFloorTemplateRespDTO cmsFloorTemplateRespDTO = cmsFloorTemplateSV.queryCmsFloorTemplate(cmsFloorTemplateReqDTO);
            if(cmsFloorTemplateRespDTO != null){
                dto.setFloorTemplateName(cmsFloorTemplateRespDTO.getTemplateName());
            }
        }
        
        // 3.将状态编码转中文 
        if(StringUtil.isNotBlank(dto.getStatus())){
            String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
            dto.setStatusZH(statusZH);
        }
        
        return dto;
    }
}

