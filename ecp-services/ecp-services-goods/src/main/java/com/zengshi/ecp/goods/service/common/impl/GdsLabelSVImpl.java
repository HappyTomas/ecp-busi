package com.zengshi.ecp.goods.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsLabelMapper;
import com.zengshi.ecp.goods.dao.model.GdsLabel;
import com.zengshi.ecp.goods.dao.model.GdsLabelCriteria;
import com.zengshi.ecp.goods.dao.model.GdsLabelCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsLabelSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月19日下午9:19:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsLabelSVImpl extends AbstractSVImpl implements IGdsLabelSV {

    @Resource(name="seq_gds_label")
    private PaasSequence seqGdsLabel;
    @Resource
    private GdsLabelMapper gdsLabelMapper;
    
    private static final String DEFAULT_ORDER_BY = "CREATE_TIME";
    
    
    @Override
    public GdsLabelReqDTO saveGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws BusinessException {
        GdsLabel gdsLabel = new GdsLabel();
        gdsLabel.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsLabel.setId(seqGdsLabel.nextValue());
        ObjectCopyUtil.copyObjValue(gdsLabelReqDTO, gdsLabel, null, false);
        preInsert(gdsLabelReqDTO, gdsLabel);
        gdsLabelMapper.insert(gdsLabel);
        gdsLabelReqDTO.setId(gdsLabel.getId());
        return gdsLabelReqDTO;
    }

    @Override
    public PageResponseDTO<GdsLabelRespDTO> queryGdsLabelRespDTOPaging(GdsLabelReqDTO dto)
            throws BusinessException {
        GdsLabelCriteria criteria = new GdsLabelCriteria();
        if(dto!=null){
            criteria.setLimitClauseStart(dto.getStartRowIndex());
            criteria.setLimitClauseCount(dto.getPageSize());
        }
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        if(dto != null){
          initCriteria(c,dto);
        }
        return super.queryByPagination(dto, criteria, false, new GdsLabelRespDTOPaginationCallback());
    }

    @Override
    public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabelReqDTO, Long updateStaff) throws BusinessException {
        GdsLabel gdsLabel = new GdsLabel();
        ObjectCopyUtil.copyObjValue(gdsLabelReqDTO, gdsLabel, null, false);
        preUpdate(gdsLabelReqDTO, gdsLabel);
        int i = gdsLabelMapper.updateByPrimaryKeySelective(gdsLabel);
        if(i == 0){
            throw new BusinessException(GdsErrorConstants.GdsLabel.ERROR_GOODS_LABEL_200151);
        }
        return gdsLabelReqDTO;
    }
    
    @Override
    public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws BusinessException {
        GdsLabel gdsLabel = new GdsLabel();
        ObjectCopyUtil.copyObjValue(gdsLabelReqDTO, gdsLabel, null, false);
        preUpdate(gdsLabelReqDTO, gdsLabel);
        int i = gdsLabelMapper.updateByPrimaryKeySelective(gdsLabel);
        if(i == 0){
            throw new BusinessException(GdsErrorConstants.GdsLabel.ERROR_GOODS_LABEL_200151);
        }
        return gdsLabelReqDTO;
    }
    

    @Override
    public int editStatus(Long labelId, String status, Long updateStaff) throws BusinessException {
        GdsLabel label = new GdsLabel();
        label.setId(labelId);
        label.setStatus(status);
        label.setUpdateStaff(updateStaff);
        label.setUpdateTime(now());
        return gdsLabelMapper.updateByPrimaryKeySelective(label);
    }

    @Override
    public int executeDisableGdsLabel(Long labelId, Long updateStaff) throws BusinessException {
        return editStatus(labelId, GdsConstants.Commons.STATUS_INVALID, updateStaff);
    }

    @Override
    public int executeEnableGdsLabel(Long labelId, Long updateStaff) throws BusinessException {
        return editStatus(labelId, GdsConstants.Commons.STATUS_VALID, updateStaff);
    }
    
    
    @Override
    public boolean queryExist(String labelType, String labelTitle, String... status)
            throws BusinessException {
         StringBuffer errorMsg = new StringBuffer();
        
        if (!StringUtils.hasText(labelType)) {
            errorMsg.append("labelType,");
        }
        if (!StringUtils.hasText(labelTitle)) {
            errorMsg.append("labelTitle,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        GdsLabelCriteria criteria = new GdsLabelCriteria();
        Criteria c = criteria.createCriteria();
        c.andLabelTypeEqualTo(labelType);
        c.andLabelTitleEqualTo(labelTitle);
        initStatusCriteria(c, status);
        return gdsLabelMapper.countByExample(criteria) > 0;
    }
    

    @Override
	public GdsLabelRespDTO queryByPK(Long id) throws BusinessException {
    	paramCheck(new Object[]{id}, new String[]{"id"});
    	GdsLabel record = gdsLabelMapper.selectByPrimaryKey(id);
    	if(null != record){
    		return GdsUtils.doObjConvert(record, GdsLabelRespDTO.class, null, null);
    	}
		return null;
	}




	/**
     * 
     * 标签分页查询回调实现类.<br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月20日上午9:22:15  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsLabelSVImpl 
     * @since JDK 1.6
     */
    protected class GdsLabelRespDTOPaginationCallback extends PaginationCallback<GdsLabel, GdsLabelRespDTO>{

        @Override
        public List<GdsLabel> queryDB(BaseCriteria criteria) {
           return gdsLabelMapper.selectByExample((GdsLabelCriteria)criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
           return gdsLabelMapper.countByExample((GdsLabelCriteria)criteria);
        }

        @Override
        public GdsLabelRespDTO warpReturnObject(GdsLabel t) {
            GdsLabelRespDTO dto = new GdsLabelRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }
        
    }
    
    
    /* 
     * 根据请求DTO设置查询条件. <br/> 
     * @author liyong7
     * @param criteria
     * @param dto 
     * @since JDK 1.6 
     */ 
    private Criteria initCriteria(Criteria c, GdsLabelReqDTO dto) {
        if(null != dto.getId()){
           c.andIdEqualTo(dto.getId());
        }
        if(StringUtils.hasText(dto.getLabelTitle())){
            c.andLabelTitleLike("%"+dto.getLabelTitle()+"%");
        }
        if(StringUtils.hasText(dto.getLabelType())){
            c.andLabelTypeEqualTo(dto.getLabelType());
        }
        return c;
    }

}

