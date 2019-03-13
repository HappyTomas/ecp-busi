/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsPropValueSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月14日上午11:12:14 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsPropValueMapper;
import com.zengshi.ecp.goods.dao.model.GdsPropValue;
import com.zengshi.ecp.goods.dao.model.GdsPropValueCriteria;
import com.zengshi.ecp.goods.dao.model.GdsPropValueCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月14日上午11:12:14  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsPropValueSVImpl extends AbstractSVImpl implements IGdsPropValueSV {
    
    @Resource(name="seq_gds_prop_value")
    private PaasSequence seqPropValue;
    @Resource
    private GdsPropValueMapper gdsPropValueMapper;
    
    private static final String DEFAULT_ORDER_BY = "SORT_NO";

    /** 
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV#saveGdsPropValue(com.zengshi.ecp.goods.dao.model.GdsPropValue) 
     */
    @Override
    public int saveGdsPropValue(GdsPropValueReqDTO gdsPropValueReqDTO) throws BusinessException {
        GdsPropValue record=new GdsPropValue();
        ObjectCopyUtil.copyObjValue(gdsPropValueReqDTO,record, null, false);
        record.setId(seqPropValue.nextValue());
        record.setStatus(GdsConstants.Commons.STATUS_VALID);
        preInsert(gdsPropValueReqDTO, record);
        return gdsPropValueMapper.insert(record);
    }

    /** 
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV#listGdsPropValuesByPropId(java.lang.Long, java.lang.String[]) 
     */
    @Override
    public List<GdsPropValue> listGdsPropValuesByPropId(Long propId, String... status)
            throws BusinessException {
        GdsPropValueCriteria criteria = new GdsPropValueCriteria();
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        c.andPropIdEqualTo(propId);
        initStatusCriteria(c, status);
        List<GdsPropValue> propValues = gdsPropValueMapper.selectByExample(criteria);
        return propValues;
    }

    /** 
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV#editGdsPropValue(com.zengshi.ecp.goods.dao.model.GdsPropValue) 
     */
    @Override
    public GdsPropValue editGdsPropValue(GdsPropValue gdsPropValue, Long updateStaff) throws BusinessException {
        Timestamp now = now();
        gdsPropValue.setUpdateTime(now);
        gdsPropValue.setUpdateStaff(updateStaff);
        int i = gdsPropValueMapper.updateByPrimaryKeySelective(gdsPropValue);
        if( i < 1 ){
            // 属性值编辑失败。
            throw new BusinessException(GdsErrorConstants.GdsPropValue.ERROR_GOODS_PROPVALUE_200401);
        }
        return gdsPropValue;
    }
    
    
    
    
    @Override
	public void editGdsPropValue(GdsPropValueReqDTO reqDTO)
			throws BusinessException {
    	GdsPropValue record = new GdsPropValue();
    	ObjectCopyUtil.copyObjValue(reqDTO, record, null, true);
    	preUpdate(reqDTO, record);
    	gdsPropValueMapper.updateByPrimaryKeySelective(record);
	}

	@Override
    public void saveGdsPropValue(List<GdsPropValueReqDTO> gdsPropValueReqDTOS) throws BusinessException {
        for(GdsPropValueReqDTO propValue : gdsPropValueReqDTOS){
            saveGdsPropValue(propValue);
        }
    }
    
    

    @Override
    public boolean queryExists(String propValue, long propId, String... status)
            throws BusinessException {
        if(!StringUtils.hasText(propValue)){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"propValue"});
        }
        GdsPropValueCriteria criteria = new GdsPropValueCriteria();
        Criteria c = criteria.createCriteria();
        c.andPropValueEqualTo(propValue);
        c.andPropIdEqualTo(propId);
        initStatusCriteria(c, status);
        return gdsPropValueMapper.countByExample(criteria) > 0;
    }
    
    @Override
    public GdsPropValue queryPropValueInfoByVal(String propValue, long propId, String... status)
            throws BusinessException {
        if(!StringUtils.hasText(propValue)){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"propValue"});
        }
        GdsPropValueCriteria criteria = new GdsPropValueCriteria();
        Criteria c = criteria.createCriteria();
        c.andPropValueEqualTo(propValue);
        c.andPropIdEqualTo(propId);
        initStatusCriteria(c, status);
       List<GdsPropValue> gdsPropValues = gdsPropValueMapper.selectByExample(criteria) ;
        if(!CollectionUtils.isEmpty(gdsPropValues)){
        	return gdsPropValues.get(0);
        }else{
        	return null;
        }
         
    }

    @Override
    public int deleteGdsPropValue(long propValueId,Long updateStaff) throws BusinessException {
        GdsPropValue propValue = gdsPropValueMapper.selectByPrimaryKey(propValueId);
        if(propValue == null){
            throw new BusinessException(GdsErrorConstants.GdsPropValue.ERROR_GOODS_PROPVALUE_200402);
        }
        propValue.setStatus(GdsConstants.Commons.STATUS_INVALID);
        propValue.setUpdateStaff(updateStaff);
        propValue.setUpdateTime(now());
        return gdsPropValueMapper.updateByPrimaryKeySelective(propValue);
    }
    
    
    

    @Override
    public PageResponseDTO<GdsPropValueRespDTO> queryGdsPropValueRespDTOPaging(
            GdsPropValueReqDTO dto) throws BusinessException {
        GdsPropValueCriteria criteria = new GdsPropValueCriteria();
        if(dto!=null){
            criteria.setLimitClauseStart(dto.getStartRowIndex());
            criteria.setLimitClauseCount(dto.getPageSize());
        }
        criteria.setOrderByClause(DEFAULT_ORDER_BY);

        if(dto != null){
          initCriteria(criteria,dto);
        }
        return super.queryByPagination(dto, criteria, false, new GdsPropValueRespDTOPaginationCallback());
    }
    
    
    /**
     * 商品属性之属性值分页查询回调类。
     * Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日下午3:36:33  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsTypeSVImpl 
     * @since JDK 1.6
     */
    protected class GdsPropValueRespDTOPaginationCallback extends PaginationCallback<GdsPropValue, GdsPropValueRespDTO>{

        @Override
        public List<GdsPropValue> queryDB(BaseCriteria criteria) {
           return gdsPropValueMapper.selectByExample((GdsPropValueCriteria)criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
           return gdsPropValueMapper.countByExample((GdsPropValueCriteria)criteria);
        }

        @Override
        public GdsPropValueRespDTO warpReturnObject(GdsPropValue t) {
            GdsPropValueRespDTO dto = new GdsPropValueRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }
        
    }
    
    

    @Override
    public GdsPropValue queryPropValueById(Long id) throws BusinessException {
        return gdsPropValueMapper.selectByPrimaryKey(id);
    }

    /** 
     * initCriteria:查询条件拼装. <br/> 
     * 
     * @author liyong7
     * @param criteria
     * @param dto 
     * @since JDK 1.6 
     */ 
    private void initCriteria(GdsPropValueCriteria criteria, GdsPropValueReqDTO dto) {
        Criteria c = criteria.createCriteria();
        if(dto.getPropId() != null){
            c.andPropIdEqualTo(dto.getPropId());
        }
        if(StringUtils.hasText(dto.getStatus())){
            c.andStatusEqualTo(dto.getStatus());
        }
    }

	@Override
	public List<GdsPropValueRespDTO> queryPropValuesByPropId(Long propId,
			String... status) throws BusinessException {
		
		List<GdsPropValue> valus=listGdsPropValuesByPropId(propId, status);
	    if(CollectionUtils.isEmpty(valus)){
        	return null;
        }
	    
        List<GdsPropValueRespDTO> categoryReqDTOs=new ArrayList<GdsPropValueRespDTO>();
        for (GdsPropValue gdsPropValue : valus) {
        	GdsPropValueRespDTO gdsPropValueRespDTO=new GdsPropValueRespDTO();
        	ObjectCopyUtil.copyObjValue(gdsPropValue, gdsPropValueRespDTO, null, false);
        	categoryReqDTOs.add(gdsPropValueRespDTO);
		}
		return categoryReqDTOs;
	}

}

