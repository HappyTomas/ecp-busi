package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsListMapper;
import com.zengshi.ecp.cms.dao.model.CmsList;
import com.zengshi.ecp.cms.dao.model.CmsListCriteria;
import com.zengshi.ecp.cms.dao.model.CmsListCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsListRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV;
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
 * @author huangxm9
 * @version
 * @since JDK 1.6
 */
@Service("cmsListSV")
public class CmsListSVImpl extends GeneralSQLSVImpl implements ICmsListSV {

    @Resource(name = "SEQ_CMS_LIST")
    private PaasSequence seqCmsList;

    @Resource
    private CmsListMapper cmsListMapper;

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#saveCmsList(com.zengshi.ecp.cms.dao.model.CmsList)
     */
    @Override
    public CmsListRespDTO addCmsList(CmsListReqDTO dto) throws BusinessException {
        /* 1.将入参组装成bo */
        CmsList bo = new CmsList();
        if (dto != null) {
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsList.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());

        /* 2.调dao层接口 */
        cmsListMapper.insertSelective(bo);

        /* 3.将结果返回 */
        CmsListRespDTO respDTO = new CmsListRespDTO();
        if (bo != null) {
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#updateCmsList(com.zengshi.ecp.cms.dao.model.CmsList)
     */
    @Override
    public CmsListRespDTO updateCmsList(CmsListReqDTO dto) throws BusinessException {
        /* 1.将入参组装成bo */
        CmsList bo = new CmsList();
        if (dto != null) {
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }

        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());

        /* 2.更新楼层的原子方法 */
        return this.updateCmsList(bo);
    }

    /**
     * updateCmsList:(更新楼层的原子方法). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author huangxm9
     * @param bo
     * @throws BusinessException
     * @since JDK 1.6
     */
    public CmsListRespDTO updateCmsList(CmsList bo) throws BusinessException {
        cmsListMapper.updateByPrimaryKeySelective(bo);
        CmsListRespDTO respDTO = new CmsListRespDTO();
        if (bo != null) {
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#deleteCmsList(java.lang.Long)
     */
    @Override
    public void deleteCmsList(String id) throws BusinessException {
        CmsList bo = new CmsList();
        bo.setId(Long.parseLong(id));
//        cmsListMapper.deleteByPrimaryKey(bo.getId());
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsList(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#deleteCmsListBatch(java.util.List)
     */
    @Override
    public void deleteCmsListBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsList(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#changeStatusCmsList(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsList(String id, String status) throws BusinessException {
        CmsList bo = new CmsList();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsList(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#changeStatusCmsListBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsListBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsList(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#queryCmsList(com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO)
     */
    @Override
    public CmsListRespDTO queryCmsList(CmsListReqDTO dto) throws BusinessException {
        CmsListRespDTO cmsListRespDTO = new CmsListRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询 */
            CmsList bo = cmsListMapper.selectByPrimaryKey(dto.getId());
            cmsListRespDTO = conversionObject(bo);
        }
        return cmsListRespDTO;
    }

    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#queryCmsListList(com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO)
     */
    @Override
    public List<CmsListRespDTO> queryCmsListList(CmsListReqDTO dto) throws BusinessException {

        /* 1.组装查询条件 */
        CmsListCriteria cmsListCriteria = new CmsListCriteria();
        Criteria criteria = cmsListCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getListClass())) {
            criteria.andListClassEqualTo(dto.getListClass());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsListCriteria.setOrderByClause("CREATE_TIME DESC");

        /* 2.迭代查询结果 */
        List<CmsListRespDTO> cmsListRespDTOList = new ArrayList<CmsListRespDTO>();
        List<CmsList> cmsListList = cmsListMapper.selectByExample(cmsListCriteria);
        if (CollectionUtils.isNotEmpty(cmsListList)) {
            for (CmsList bo : cmsListList) {
                CmsListRespDTO cmsListRespDTO = conversionObject(bo);
                cmsListRespDTOList.add(cmsListRespDTO);
            }
        }

        return cmsListRespDTOList;

    }

    /**
     * TODO 查询楼层，分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV#queryCmsListPage(com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO)
     */
    @Override
    public PageResponseDTO<CmsListRespDTO> queryCmsListPage(CmsListReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsListCriteria cmsListCriteria = new CmsListCriteria();
        Criteria criteria = cmsListCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getListClass())) {
            criteria.andListClassEqualTo(dto.getListClass());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsListCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsListCriteria.setLimitClauseCount(dto.getPageSize());
        cmsListCriteria.setLimitClauseStart(dto.getStartRowIndex());

        return super.queryByPagination(dto, cmsListCriteria, false,
                new PaginationCallback<CmsList, CmsListRespDTO>() {

                    @Override
                    public List<CmsList> queryDB(BaseCriteria criteria) {
                        return cmsListMapper.selectByExample((CmsListCriteria) criteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return cmsListMapper.countByExample((CmsListCriteria) criteria);
                    }

                    @Override
                    public CmsListRespDTO warpReturnObject(CmsList bo) {
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
     * @author huangxm9
     * @param bo
     * @return
     * @since JDK 1.6
     */
    private CmsListRespDTO conversionObject(CmsList bo) {
        CmsListRespDTO dto = new CmsListRespDTO();
        if (bo != null) {
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }

        // 1.遍历将编码转中文
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String listClassZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_LIST_CLASS, dto.getListClass());
        dto.setListClassZH(listClassZH);

        return dto;
    }

}
