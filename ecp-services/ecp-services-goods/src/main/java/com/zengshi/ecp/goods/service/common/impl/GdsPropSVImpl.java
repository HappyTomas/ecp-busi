package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsPropMapper;
import com.zengshi.ecp.goods.dao.mapper.common.manual.GdsPropManualMapper;
import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dao.model.GdsPropCriteria;
import com.zengshi.ecp.goods.dao.model.GdsPropCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * 商品属性接口实现类.<br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月14日下午3:50:15 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsPropSVImpl extends AbstractSVImpl implements IGdsPropSV {

    @Resource(name = "seq_gds_prop")
    private PaasSequence seqGdsProp;

    @Resource
    private GdsPropMapper gdsPropMapper;

    @Resource
    private GdsPropManualMapper gdsPropManualMapper;
    
    @Resource
    private IGdsCatg2PropSV gdsCatg2PropSV;

    @Resource
    private IGdsPropValueSV iGdsPropValueSV;

    private static final String DEFAULT_ORDER_BY = "SORT_NO,ID DESC";

    @Override
    public int saveGdsProp(GdsPropReqDTO gdsPropReqDTO) throws BusinessException {
        Timestamp now = now();
        GdsProp gdsProp = new GdsProp();
        Long propId = seqGdsProp.nextValue();
        ObjectCopyUtil.copyObjValue(gdsPropReqDTO, gdsProp, null, false);
        gdsProp.setId(propId);
        gdsProp.setCreateStaff(gdsPropReqDTO.getStaff().getId());
        gdsProp.setCreateTime(now);
        gdsProp.setUpdateStaff(gdsPropReqDTO.getStaff().getId());
        gdsProp.setUpdateTime(now);
        gdsProp.setStatus(GdsConstants.Commons.STATUS_VALID);
        int i = gdsPropMapper.insert(gdsProp);
        if (!CollectionUtils.isEmpty(gdsPropReqDTO.getPropValues())) {
            for (GdsPropValueReqDTO gdsPropValueReqDTO : gdsPropReqDTO.getPropValues()) {
                gdsPropValueReqDTO.setPropId(propId);
                iGdsPropValueSV.saveGdsPropValue(gdsPropValueReqDTO);
            }
        }
        return i;
    }

    @Override
    public GdsProp queryGdsPropByPK(Long id) throws BusinessException {
        return gdsPropMapper.selectByPrimaryKey(id);
    }

    @Override
    public GdsProp queryGdsPropByPK(Long id, boolean isValid) throws BusinessException {
        GdsProp gdsProp = gdsPropMapper.selectByPrimaryKey(id);
        if (gdsProp != null) {
            if (isValid && !GdsConstants.Commons.STATUS_VALID.equals(gdsProp.getStatus())) {
                gdsProp = null;
            }
        }
        return gdsProp;
    }

    @Override
    public PageResponseDTO<GdsPropRespDTO> queryGdsPropRespDTOPaging(GdsPropReqDTO dto,
            List<Long> excludeIds, List<Long> includeIds) throws BusinessException {
        GdsPropCriteria criteria = new GdsPropCriteria();
        if(dto!=null){
            criteria.setLimitClauseStart(dto.getStartRowIndex());
            criteria.setLimitClauseCount(dto.getPageSize());
        }
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        if (dto!=null && dto.getId() != null && dto.getId() != 0L) {
            c.andIdEqualTo(dto.getId());
        }
        if (dto != null) {
            initCriteria(c, dto);
        }
        if (excludeIds != null && !excludeIds.isEmpty()) {
            c.andIdNotIn(excludeIds);
        }
        if (includeIds != null && !includeIds.isEmpty()) {
            c.andIdIn(includeIds);
        }
        return super
                .queryByPagination(dto, criteria, false, new GdsPropRespDTOPaginationCallback());
    }

    @Override
    public int editGdsProp(GdsProp gdsProp, Long updateStaff) throws BusinessException {
        gdsProp.setUpdateStaff(updateStaff);
        gdsProp.setUpdateTime(now());
        return gdsPropMapper.updateByPrimaryKeySelective(gdsProp);
    }

    @Override
    public int editGdsProp(GdsPropReqDTO reqDTO) throws BusinessException {
        GdsProp record = new GdsProp();
        ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
        preUpdate(reqDTO, record);
        if (!CollectionUtils.isEmpty(reqDTO.getPropValues())) {
            for (GdsPropValueReqDTO gdsPropValueReqDTO : reqDTO.getPropValues()) {
            	
            	if(null == gdsPropValueReqDTO.getPropId()){
            		gdsPropValueReqDTO.setPropId(reqDTO.getId());
            	}
            	// 如果属性值ID不为空，则执行更新。
            	if(null != gdsPropValueReqDTO.getId()){
            		if(null != reqDTO.getUpdateStaff()){
            			gdsPropValueReqDTO.setUpdateStaff(reqDTO.getUpdateStaff());
            		}else{
            			gdsPropValueReqDTO.setUpdateStaff(reqDTO.getStaff().getId());
            		}
            		iGdsPropValueSV.editGdsPropValue(gdsPropValueReqDTO);
            	}else{
            		if(null != reqDTO.getUpdateStaff()){
            			gdsPropValueReqDTO.setCreateStaff(reqDTO.getUpdateStaff());
            		}else{
            			gdsPropValueReqDTO.setCreateStaff(reqDTO.getStaff().getId());
            		}
            		// 反之执行新增。
                    iGdsPropValueSV.saveGdsPropValue(gdsPropValueReqDTO);
            	}
            }
        }
        return gdsPropMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public boolean queryExist(String propName, String... status) throws BusinessException {
        if (!StringUtils.hasText(propName)) {
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { "propName" });
        }
        GdsPropCriteria criteria = new GdsPropCriteria();
        Criteria c = criteria.createCriteria();
        c.andPropNameEqualTo(propName);
        initStatusCriteria(c, status);
        return gdsPropMapper.countByExample(criteria) > 0;
    }

    @Override
    public int deleteGdsProp(Long id, Long updateStaff) throws BusinessException {
        GdsProp prop = gdsPropMapper.selectByPrimaryKey(id);
        if (prop == null) {
            throw new BusinessException(GdsErrorConstants.GdsProp.ERROR_GOODS_PROP_200451);
        }
        if (GdsUtils.isEqualsInvalid(prop.getStatus())) {
            throw new BusinessException(GdsErrorConstants.GdsProp.ERROR_GOODS_PROP_200456);
        }
        prop.setStatus(GdsConstants.Commons.STATUS_INVALID);
        prop.setUpdateStaff(updateStaff);
        prop.setUpdateTime(now());
        return gdsPropMapper.updateByPrimaryKeySelective(prop);
    }

    @Override
    public int executeEnableGdsProp(Long id, Long updateStaff) throws BusinessException {
        GdsProp prop = gdsPropMapper.selectByPrimaryKey(id);
        if (prop == null) {
            throw new BusinessException(GdsErrorConstants.GdsProp.ERROR_GOODS_PROP_200451);
        }
        if (GdsUtils.isEqualsValid(prop.getStatus())) {
            throw new BusinessException(GdsErrorConstants.GdsProp.ERROR_GOODS_PROP_200455);
        }
        prop.setStatus(GdsConstants.Commons.STATUS_VALID);
        prop.setUpdateStaff(updateStaff);
        prop.setUpdateTime(now());
        return gdsPropMapper.updateByPrimaryKeySelective(prop);
    }

    @Override
	public List<Long> queryPropIds(GdsPropReqDTO reqDTO)
			throws BusinessException {
    	if(reqDTO == null){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"reqDTO"});
        }
        if(StringUtil.isBlank(reqDTO.getPropName())){
        	throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"reqDTO.propName"});
        }
    	GdsPropCriteria criteria = new GdsPropCriteria();
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        Criteria c = criteria.createCriteria();
        if(reqDTO != null){
          initCriteria(c,reqDTO);
        }
        return gdsPropManualMapper.queryPropIdsByExample(criteria);
	}


	@Override
	public boolean queryIsPropInUse(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		return gdsCatg2PropSV.queryIsInUse(reqDTO);
	}




	/**
     * 商品类型分页查询回调实现类。 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日下午3:36:33 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsTypeSVImpl
     * @since JDK 1.6
     */
    protected class GdsPropRespDTOPaginationCallback extends
            PaginationCallback<GdsProp, GdsPropRespDTO> {

        @Override
        public List<GdsProp> queryDB(BaseCriteria criteria) {
            return gdsPropMapper.selectByExample((GdsPropCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsPropMapper.countByExample((GdsPropCriteria) criteria);
        }

        @Override
        public GdsPropRespDTO warpReturnObject(GdsProp t) {
            GdsPropRespDTO dto = new GdsPropRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }

    }

    /*
     * 根据请求DTO设置查询条件. <br/>
     * 
     * @author liyong7
     * 
     * @param criteria
     * 
     * @param dto
     * 
     * @since JDK 1.6
     */
    private Criteria initCriteria(Criteria c, GdsPropReqDTO dto) {
        if (StringUtils.hasText(dto.getPropName())) {
            c.andPropNameLike("%" + dto.getPropName() + "%");
        }
        if (StringUtils.hasText(dto.getStatus())) {
            c.andStatusEqualTo(dto.getStatus());
        }
        return c;
    }

}
