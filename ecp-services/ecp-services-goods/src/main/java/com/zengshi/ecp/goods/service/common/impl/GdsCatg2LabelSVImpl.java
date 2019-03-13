/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCatg2LabelSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月20日上午9:44:42 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCatg2LabelMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsLabelMapper;
import com.zengshi.ecp.goods.dao.model.GdsCatg2Label;
import com.zengshi.ecp.goods.dao.model.GdsCatg2LabelCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatg2LabelCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2LabelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2LabelRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelReqDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2LabelSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月20日上午9:44:42  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsCatg2LabelSVImpl extends AbstractSVImpl implements IGdsCatg2LabelSV {
    @Resource
    private GdsCatg2LabelMapper gdsCatg2LabelMapper;
    @Resource
    private GdsLabelMapper gdsLabelMapper;
    
    private static final String DEFAULT_ORDER_BY = "CRATE_TIME";

    @Override
    public GdsCatg2Label saveGdsCatg2Label(GdsCatg2Label record) throws BusinessException {
        Timestamp now = now();
        record.setCreateTime(now);
        record.setUpdateTime(now);
        record.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsCatg2LabelMapper.insert(record);
        return record;
    }

    @Override
    public void batchAddGdsCatg2Label(String catgCode, List<Long> labelIds, Long createStaff,
            boolean skipWhenExist) throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        
        if (!StringUtils.hasText(catgCode)) {
            errorMsg.append("catgCode,");
        }
        if (labelIds == null || labelIds.isEmpty()) {
            errorMsg.append("labelIds,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        Timestamp now = now();
        // for begin!
        for (Long labelId : labelIds) {
            
                if (queryExist(catgCode, labelId, GdsConstants.Commons.STATUS_VALID))
                {
                    if(skipWhenExist){
                        continue; 
                    }else{
                        throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200308,new String[]{labelId.toString()});
                    }
                }
            
            GdsCatg2Label record = new GdsCatg2Label();
            record.setCatgCode(catgCode);
            record.setCreateStaff(createStaff);
            record.setCreateTime(now);
            record.setLabelId(labelId);
            record.setStatus(GdsConstants.Commons.STATUS_VALID);
            saveGdsCatg2Label(record);
        }
        // for end!
    }

    @Override
    public PageResponseDTO<GdsCatg2LabelRespDTO> queryConfigedGdsCatg2LabelRespDTOPaging(
            GdsCatg2LabelReqDTO dto) throws BusinessException {
        GdsCatg2LabelCriteria criteria = new GdsCatg2LabelCriteria();
        if(dto!=null){
            criteria.setLimitClauseStart(dto.getStartRowIndex());
            criteria.setLimitClauseCount(dto.getPageSize());
        }
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        criteria.createCriteria();
        Criteria c = criteria.createCriteria();
        if (dto != null) {
            initCriteria(c, dto);
        }
        return super.queryByPagination(dto, criteria, false,
                new GdsCatg2LabelRespDTOPaginationCallback());
    }

    @Override
    public PageResponseDTO<GdsCatg2PropRespDTO> queryUnconfigedGdsLabelRespDTOPaging(
            GdsLabelReqDTO dto) throws BusinessException {
        return null;
    }

    @Override
    public int batchDeleteCatg2Labels(String catgCode, List<Long> labelIds, Long updateStaff)
            throws BusinessException {
        // 参数判断。
        StringBuffer errorMsg = new StringBuffer();
        
        if (!StringUtils.hasText(catgCode)) {
            errorMsg.append("catgCode,");
        }
        if (null == labelIds || labelIds.isEmpty()) {
            errorMsg.append("labelIds,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        // 条件设置。
        GdsCatg2LabelCriteria criteria = new GdsCatg2LabelCriteria();
        Criteria c  = criteria.createCriteria();
        c.andCatgCodeEqualTo(catgCode);
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        if(labelIds.size() <= 1){
            c.andLabelIdEqualTo(labelIds.get(0));
        }else{
            c.andLabelIdIn(labelIds);
        }
        // 取样对象。
        GdsCatg2Label record = new GdsCatg2Label();
        record.setCatgCode(catgCode);
        record.setUpdateStaff(updateStaff);
        record.setUpdateTime(now());
        record.setStatus(GdsConstants.Commons.STATUS_INVALID);
        // 执行删除。
        return gdsCatg2LabelMapper.updateByExampleSelective(record, criteria);
        
    }

    @Override
    public int deleteAllCatg2LabelsByCatgCode(String catgCode, Long updateStaff)
            throws BusinessException {
        // 参数判断。
        StringBuffer errorMsg = new StringBuffer();
        if (!StringUtils.hasText(catgCode)) {
            errorMsg.append("catgCode,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        // 条件设置。
        GdsCatg2LabelCriteria criteria = new GdsCatg2LabelCriteria();
        Criteria c  = criteria.createCriteria();
        c.andCatgCodeEqualTo(catgCode);
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        // 取样对象。
        GdsCatg2Label record = new GdsCatg2Label();
        record.setCatgCode(catgCode);
        record.setUpdateStaff(updateStaff);
        record.setUpdateTime(now());
        record.setStatus(GdsConstants.Commons.STATUS_INVALID);
        // 执行删除 。
        return gdsCatg2LabelMapper.updateByExampleSelective(record, criteria);
    }

    @Override
    public List<Long> queryConfigedLabelIds(String catgCode, String... status)
            throws BusinessException {
        return null;
    }

    @Override
    public boolean queryExist(String catgCode, Long labelId, String... status)
            throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        
        if (!StringUtils.hasText(catgCode)) {
            errorMsg.append("catgCode,");
        }
        if (null == labelId) {
            errorMsg.append("labelId,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        GdsCatg2LabelCriteria criteria =  new GdsCatg2LabelCriteria();
        Criteria c  = criteria.createCriteria();
        initStatusCriteria(c,status);
        c.andCatgCodeEqualTo(catgCode);
        c.andLabelIdEqualTo(labelId);
        return gdsCatg2LabelMapper.countByExample(criteria) >  0;
    }
    
    /*
     * 分类与标签关联分页查询回调类。 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日下午3:36:33 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsTypeSVImpl
     * @since JDK 1.6
     */
    protected class GdsCatg2LabelRespDTOPaginationCallback
            extends PaginationCallback<GdsCatg2Label, GdsCatg2LabelRespDTO> {

        @Override
        public List<GdsCatg2Label> queryDB(BaseCriteria criteria) {
            return gdsCatg2LabelMapper.selectByExample((GdsCatg2LabelCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsCatg2LabelMapper.countByExample((GdsCatg2LabelCriteria) criteria);
        }

        @Override
        public GdsCatg2LabelRespDTO warpReturnObject(GdsCatg2Label t) {
            GdsCatg2LabelRespDTO dto = new GdsCatg2LabelRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            dto.setGdsLabel(gdsLabelMapper.selectByPrimaryKey(t.getLabelId()));
            return dto;
        }

    }
    
    /*
     * 初始化查询条件. <br/>
     * 
     * @author liyong7
     * 
     * @param criteria
     * 
     * @param dto
     * 
     * @since JDK 1.6
     */
    private Criteria initCriteria(Criteria c, GdsCatg2LabelReqDTO dto) {
        if (StringUtils.hasText(dto.getCatgCode())) {
            c.andCatgCodeEqualTo(dto.getCatgCode());
        }
        if (StringUtils.hasText(dto.getStatus())) {
            c.andStatusEqualTo(dto.getStatus());
        }
        if(null != dto.getLabelId()){
            c.andLabelIdEqualTo(dto.getLabelId());
        }
        return c;
    }
    

    
}

