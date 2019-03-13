package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dao.mapper.common.CmsAppFloorDataMapper;
import com.zengshi.ecp.cms.dao.model.CmsAppFloorData;
import com.zengshi.ecp.cms.dao.model.CmsAppFloorDataCriteria;
import com.zengshi.ecp.cms.dao.model.CmsAppFloorDataCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV;
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

public class CmsAppFloorDataSVImpl extends GeneralSQLSVImpl implements ICmsAppFloorDataSV {
    
    @Resource(name = "SEQ_CMS_APP_FLOOR_DATA")
    private PaasSequence seqCmsAppFloorData;
    
    @Resource
    private CmsAppFloorDataMapper cmsAppFloorDataMapper;
    
    @Resource 
    private ICmsAppFloorSV cmsAppFloorSV;
    /**
     * 
     * TODO 添加appFloorData  楼层数据（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#addCmsAppFloorData(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public CmsAppFloorDataRespDTO addCmsAppFloorData(CmsAppFloorDataReqDTO dto)
            throws BusinessException {
        /*1.将入参组装成bo*/
        CmsAppFloorData bo = new CmsAppFloorData();
        ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        
        Long id = seqCmsAppFloorData.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口存appFloorData数据*/
        cmsAppFloorDataMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsAppFloorDataRespDTO respDTO = new CmsAppFloorDataRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * 
     * TODO 更新appFloorData  楼层数据（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#updateCmsAppFloorData(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public CmsAppFloorDataRespDTO updateCmsAppFloorData(CmsAppFloorDataReqDTO dto)
            throws BusinessException {
        /*1.将入参封装成bo*/
        CmsAppFloorData bo = new CmsAppFloorData();
        ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        return this.updateCmsAppFloorData(bo);
    }

    /**
     * 
     * TODO 查询appFloorData列表，无分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#queryCmsAppFloorDataList(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public List<CmsAppFloorDataRespDTO> queryCmsAppFloorDataList(CmsAppFloorDataReqDTO dto)
            throws BusinessException {
        if(dto == null){
            dto = new CmsAppFloorDataReqDTO();
        }
        /* 1.组装查询条件 */
        CmsAppFloorDataCriteria cmsAppFloorDataCriteria = new CmsAppFloorDataCriteria();
        this.dtoToCriteria(cmsAppFloorDataCriteria.createCriteria(),dto);
        cmsAppFloorDataCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /* 2.获取查询结果 */
        List<CmsAppFloorDataRespDTO> CmsAppFloorDataRespDTOList = new ArrayList<CmsAppFloorDataRespDTO>();
        List<CmsAppFloorData> CmsAppFloorDataList = cmsAppFloorDataMapper.selectByExample(cmsAppFloorDataCriteria);
       
        if(CollectionUtils.isNotEmpty(CmsAppFloorDataList)){
            for(CmsAppFloorData bo : CmsAppFloorDataList){
                CmsAppFloorDataRespDTO cmsAppFloorDataRespDTO = conversionObject(bo);
                if(cmsAppFloorDataRespDTO!=null && StringUtil.isNotEmpty(cmsAppFloorDataRespDTO.getId())){
                    CmsAppFloorDataRespDTOList.add(cmsAppFloorDataRespDTO);
                }
            }
        }
        
        return CmsAppFloorDataRespDTOList;
    }

    /**
     * 
     * TODO 查询appFloorData分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#queryCmsAppFloorDataPage(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public PageResponseDTO<CmsAppFloorDataRespDTO> queryCmsAppFloorDataPage(
            CmsAppFloorDataReqDTO dto) throws BusinessException {
        if(dto == null){
            dto = new CmsAppFloorDataReqDTO();
        }
        /* 1.组装查询条件 */ 
        CmsAppFloorDataCriteria cmsAppFloorDataCriteria = new CmsAppFloorDataCriteria();
        this.dtoToCriteria(cmsAppFloorDataCriteria.createCriteria(), dto);
        cmsAppFloorDataCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsAppFloorDataCriteria.setLimitClauseCount(dto.getPageSize());
        cmsAppFloorDataCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        /* 2.返回数据 */
        return super.queryByPagination(dto, cmsAppFloorDataCriteria, false, 
                new PaginationCallback<CmsAppFloorData, CmsAppFloorDataRespDTO>() {

                    @Override
                    public List<CmsAppFloorData> queryDB(BaseCriteria criteria) {
                        return cmsAppFloorDataMapper.selectByExample((CmsAppFloorDataCriteria)criteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return cmsAppFloorDataMapper.countByExample((CmsAppFloorDataCriteria)criteria);
                    }

                    @Override
                    public CmsAppFloorDataRespDTO warpReturnObject(CmsAppFloorData bo) {
                        return conversionObject(bo);
                    }
                });
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#queryCmsAppFloorData(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public CmsAppFloorDataRespDTO queryCmsAppFloorData(CmsAppFloorDataReqDTO dto)
            throws BusinessException {
        CmsAppFloorDataRespDTO respDto = new CmsAppFloorDataRespDTO();
        if(dto!=null && StringUtil.isNotEmpty(dto.getId())){
            CmsAppFloorData bo = cmsAppFloorDataMapper.selectByPrimaryKey(dto.getId());
            respDto = this.conversionObject(bo);
        }
        return respDto;
    }

    /**
     * 
     * TODO 删除记录，逻辑删除（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#deleteCmsAppFloorData(java.lang.String)
     */
    @Override
    public void deleteCmsAppFloorData(String id) throws BusinessException {
        CmsAppFloorData bo = new CmsAppFloorData();
        if(StringUtil.isNotBlank(id)){
            bo.setId(Long.parseLong(id));
            bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
            this.updateCmsAppFloorData(bo);
        }
    }

    /**
     * 
     * TODO 批量删除记录，逻辑删除（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#deleteCmsAppFloorDataBatch(java.util.List)
     */
    @Override
    public void deleteCmsAppFloorDataBatch(List<String> list) throws BusinessException {
        if(CollectionUtils.isNotEmpty(list)){
            for(int i = 0 ; i < list.size() ; i++){
                String id = list.get(i);
                this.deleteCmsAppFloorData(id);
            }
        }
    }

    /**
     * 
     * TODO 修改状态（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#changeStatusCmsAppFloorData(java.lang.String, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloorData(String id, String status) throws BusinessException {
        CmsAppFloorData bo = new CmsAppFloorData();
        if(StringUtil.isNotBlank(id)){
            bo.setId(Long.parseLong(id));
            bo.setStatus(status);
            this.updateCmsAppFloorData(bo);
        }
    }

    /**
     * 
     * TODO 批量修改状态（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV#changeStatusCmsAppFloorDataBatch(java.util.List, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloorDataBatch(List<String> list, String status)
            throws BusinessException {
        if(CollectionUtils.isNotEmpty(list)){
            for(int i = 0 ; i < list.size() ; i++){
                String id = list.get(i);
                this.changeStatusCmsAppFloorData(id, status);
            }
        }
    }

  //---------私有方法---------//
    /**
     * 
     * updateCmsAppFloorData:(原子方法，更新appFloor数据). <br/> 
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
    private CmsAppFloorDataRespDTO updateCmsAppFloorData(CmsAppFloorData bo) {
        CmsAppFloorDataRespDTO respDTO= new CmsAppFloorDataRespDTO();
        
        if (bo != null) {
            cmsAppFloorDataMapper.updateByPrimaryKeySelective(bo);
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
    private Criteria dtoToCriteria (Criteria criteria , CmsAppFloorDataReqDTO dto){
        if(criteria != null && dto != null){
            if(StringUtil.isNotEmpty(dto.getId())){
                criteria.andIdEqualTo(dto.getId());
            }
            if(StringUtil.isNotEmpty(dto.getAppFloorId())){
                criteria.andAppFloorIdEqualTo(dto.getAppFloorId());
            }
            if(StringUtil.isNotEmpty(dto.getFloorPlaceId())){
                criteria.andFloorPlaceIdEqualTo(dto.getFloorPlaceId());
            }
            if(StringUtil.isNotBlank(dto.getName())){
                criteria.andNameLike("%"+dto.getName()+"%");
            }
            if(StringUtil.isNotBlank(dto.getLinkType())){
                criteria.andLinkTypeEqualTo(dto.getLinkType());
            }
            if(StringUtil.isNotBlank(dto.getStatus())){
                criteria.andStatusEqualTo(dto.getStatus());
            }
            if(StringUtil.isNotBlank(dto.getRemark())){
                criteria.andRemarkLike("%"+dto.getRemark()+"%");
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
    private CmsAppFloorDataRespDTO conversionObject(CmsAppFloorData bo){
        CmsAppFloorDataRespDTO respDto = new CmsAppFloorDataRespDTO();
        if (bo != null) {
            ObjectCopyUtil.copyObjValue(bo, respDto, null, false);
        }
        
        // 1.将状态编码转中文 
        if(StringUtil.isNotBlank(respDto.getStatus())){
            String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, respDto.getStatus());
            respDto.setStatusZH(statusZH);
        }
        
        //2.appFloorName 获取
        if(StringUtil.isNotEmpty(respDto.getAppFloorId())){
            CmsAppFloorReqDTO cmsAppFloorReqDTO= new CmsAppFloorReqDTO();
            cmsAppFloorReqDTO.setId(respDto.getAppFloorId());
            CmsAppFloorRespDTO cmsAppFloorRespDTO = cmsAppFloorSV.queryCmsAppFloor(cmsAppFloorReqDTO);
            if(cmsAppFloorRespDTO != null){
                respDto.setAppFloorName(cmsAppFloorRespDTO.getFloorName()); 
            }
        }
        
        //3.链接类型翻译
        if(StringUtil.isNotBlank(respDto.getLinkType())){
            String linkTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_ADVERTISE_LINK_TYPE, respDto.getLinkType());
            respDto.setLinkTypeZH(linkTypeZH);
        }
       
        return respDto;
    }
}

