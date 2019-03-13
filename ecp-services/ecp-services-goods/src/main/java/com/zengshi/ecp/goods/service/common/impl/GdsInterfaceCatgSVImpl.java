package com.zengshi.ecp.goods.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsInterfaceCatgMapper;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceCatg;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceCatgCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsInterfaceCatgSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class GdsInterfaceCatgSVImpl extends AbstractSVImpl implements
		IGdsInterfaceCatgSV {
	
	@Resource(name="seq_gds_interface_catgr")
	private PaasSequence seqGdsInterfaceCatgr;
	@Resource
	private GdsInterfaceCatgMapper gdsInterfaceCatgMapper;
	@Resource
	private IGdsCategorySV gdsCategorySV;
	

	@Override
	public GdsCategoryRespDTO queryCategoryByOriginCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException {
		GdsInterfaceCatg catg = queryInterfaceCatgByOriginAndOriginCatgCode(reqDTO);
        GdsCategoryRespDTO categoryRecord = null;
        if(null != catg){
        	GdsCategoryReqDTO condition = new GdsCategoryReqDTO();
        	condition.setCatgCode(catg.getCatgCode());
        	categoryRecord = gdsCategorySV.queryGdsCategoryByPK(condition);
        }
        return categoryRecord;
        
	}
	
	@Override
	public GdsInterfaceCatg queryInterfaceCatgByOriginAndOriginCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		paramCheck(new Object[]{reqDTO.getOrigin(),reqDTO.getOriginCatgCode()}, new String[]{"reqDTO.Origin","reqDTO.originCatgCode"});
		reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		GdsInterfaceCatgCriteria criteria = new GdsInterfaceCatgCriteria();
		GdsInterfaceCatgCriteria.Criteria c = criteria.createCriteria();
		initCriteria(c, reqDTO);
        List<GdsInterfaceCatg> lst = gdsInterfaceCatgMapper.selectByExample(criteria);
        GdsInterfaceCatg catg = null;
        if(CollectionUtils.isNotEmpty(lst)){
        	if(lst.size() > 1){
				throw new BusinessException(GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_13, new String[]{reqDTO.getOriginCatgCode()});
			}
        	catg = lst.get(0);
        }
       return catg;
	}
	

	@Override
	public void saveGdsInterfaceCatg(GdsInterfaceCatgReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getOrigin(),reqDTO.getOriginCatgCode(),reqDTO.getOriginCatgName()}, 
				   new String[]{"reqDTO.catgCode","reqDTO.origin","reqDTO.originCatgCode","reqDTO.originCatgName"});
		GdsInterfaceCatg record = new GdsInterfaceCatg();
		record.setId(seqGdsInterfaceCatgr.nextValue());
		record.setStatus(GdsConstants.Commons.STATUS_VALID);
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, true);
		preInsert(reqDTO, record);
		gdsInterfaceCatgMapper.insert(record);
		
	}
	
	
	@Override
	public GdsInterfaceCatg queryInterfaceCatgByCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		return queryInterfaceCatgByCatgCode(reqDTO.getCatgCode());
	}
	

	@Override
	public GdsInterfaceCatg queryInterfaceCatgByCatgCode(String catgCode)
			throws BusinessException {
		paramNullCheck(catgCode,"catgCode");
		GdsInterfaceCatgCriteria criteria = new GdsInterfaceCatgCriteria();
		GdsInterfaceCatgCriteria.Criteria c = criteria.createCriteria();
		c.andCatgCodeEqualTo(catgCode);
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		List<GdsInterfaceCatg> lst = gdsInterfaceCatgMapper.selectByExample(criteria);
		if(CollectionUtils.isNotEmpty(lst)){
			if(lst.size() > 1){
				throw new BusinessException(GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_14, new String[]{catgCode});
			}
			return lst.get(0);
		}
		return null;
	}


	@Override
	public void updateInterfaceCatg(GdsInterfaceCatgReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		GdsInterfaceCatg record = new GdsInterfaceCatg();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, true);
		preUpdate(reqDTO, record);
        gdsInterfaceCatgMapper.updateByPrimaryKeySelective(record);
	}
	
	public PageResponseDTO<GdsInterfaceCatgRespDTO> queryPaing(GdsInterfaceCatgReqDTO reqDTO)throws BusinessException{
		GdsInterfaceCatgCriteria criteria = new GdsInterfaceCatgCriteria();
		criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setLimitClauseCount(reqDTO.getPageSize());
		GdsInterfaceCatgCriteria.Criteria c = criteria.createCriteria();
		initCriteria(c, reqDTO);
		return super.queryByPagination(reqDTO, criteria, false, new GdsInterfaceCatgPaginationCallback());
	}
	
	
	
	@Override
	public void deleteInterfaceCatgByCatgCode(GdsInterfaceCatgReqDTO reqDTO)
			throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[]{reqDTO.getCatgCode()}, new String[]{"catgCode"});
        GdsInterfaceCatg record = new GdsInterfaceCatg();
        record.setCatgCode(reqDTO.getCatgCode());
        record.setStatus(GdsConstants.Commons.STATUS_INVALID);
        preUpdate(reqDTO, record);
        GdsInterfaceCatgCriteria criteria = new GdsInterfaceCatgCriteria();
		GdsInterfaceCatgCriteria.Criteria c = criteria.createCriteria();
		c.andCatgCodeEqualTo(record.getCatgCode());
		c.andStatusNotEqualTo(GdsConstants.Commons.STATUS_INVALID);
        gdsInterfaceCatgMapper.updateByExampleSelective(record, criteria);
	}
	
	
	 protected class GdsInterfaceCatgPaginationCallback extends PaginationCallback<GdsInterfaceCatg, GdsInterfaceCatgRespDTO>{

	        @Override
	        public List<GdsInterfaceCatg> queryDB(BaseCriteria criteria) {
	           return gdsInterfaceCatgMapper.selectByExample((GdsInterfaceCatgCriteria)criteria);
	        }

	        @Override
	        public long queryTotal(BaseCriteria criteria) {
	           return gdsInterfaceCatgMapper.countByExample((GdsInterfaceCatgCriteria)criteria);
	        }

	        @Override
	        public GdsInterfaceCatgRespDTO warpReturnObject(GdsInterfaceCatg t) {
	        	GdsInterfaceCatgRespDTO dto = new GdsInterfaceCatgRespDTO();
	            ObjectCopyUtil.copyObjValue(t, dto, null, true);
	            return dto;
	        }
	        
	    }
	
	

	private void initCriteria(GdsInterfaceCatgCriteria.Criteria c, GdsInterfaceCatgReqDTO reqDTO){
		if(StringUtil.isNotBlank(reqDTO.getOrigin())){
			c.andOriginEqualTo(reqDTO.getOrigin());
		}
		if(StringUtil.isNotBlank(reqDTO.getOriginCatgCode())){
			c.andOriginCatgCodeEqualTo(reqDTO.getOriginCatgCode());
		}
		if(StringUtil.isNotBlank(reqDTO.getOriginCatgName())){
			c.andOriginCatgNameEqualTo(reqDTO.getOriginCatgName());
		}
		if(StringUtil.isNotBlank(reqDTO.getStatus())){
			c.andStatusEqualTo(reqDTO.getStatus());
		}
		if(StringUtil.isNotBlank(reqDTO.getCatgCode())){
			c.andCatgCodeEqualTo(reqDTO.getCatgCode());
		}
		if(StringUtil.isNotBlank(reqDTO.getOriginCatgCodeExcludePrefix())){
			c.andOriginCatgCodeNotLike(reqDTO.getOriginCatgCodeExcludePrefix()+"%");
		}
		if(StringUtil.isNotBlank(reqDTO.getOriginCatgCodePrefixLike())){
			c.andOriginCatgCodeLike(reqDTO.getOriginCatgCodePrefixLike()+"%");
		}
	}

}

