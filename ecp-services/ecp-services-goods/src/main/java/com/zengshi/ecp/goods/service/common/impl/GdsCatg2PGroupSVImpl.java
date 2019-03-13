package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCatg2PGroupMapper;
import com.zengshi.ecp.goods.dao.mapper.common.manual.GdsCatg2PGroupManualMapper;
import com.zengshi.ecp.goods.dao.model.GdsCatg2PGroup;
import com.zengshi.ecp.goods.dao.model.GdsCatg2PGroupCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatg2PGroupCriteria.Criteria;
import com.zengshi.ecp.goods.dao.model.GdsPropGroup;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PGroupSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropGroupSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-10上午9:23:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCatg2PGroupSVImpl extends AbstractSVImpl implements
		IGdsCatg2PGroupSV {
	
	@Resource
	private GdsCatg2PGroupMapper gdsCatg2PGroupMapper;
	@Resource
	private IGdsPropGroupSV gdsPropGroupSV;
	@Resource
	private GdsCatg2PGroupManualMapper gdsCatg2PGroupManualMapper;
	
	private static final String DEFAULT_ORDER_BY = "CREATE_TIME DESC";

	@Override
	public void saveGdsCatg2PGroup(GdsCatg2PGroupReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO,reqDTO.getCatgCode(),reqDTO.getPropGroupId()}, new String[]{"reqDTO","reqDTO.catgCode","reqDTO.propGroupId"});
		GdsCatg2PGroup record = new GdsCatg2PGroup();
		preInsert(reqDTO, record);
		gdsCatg2PGroupMapper.insert(record);
	}

	@Override
	public void batchAddGdsCatg2PGroups(String catgCode,
			List<Long> propGroupIds, Long createStaff, boolean skipWhenExist)
			throws BusinessException {
		paramCheck(new Object[]{catgCode,propGroupIds}, new String[]{"catgCode","propGroupIds"});
        Timestamp now = now();
        for (Long propGroupId : propGroupIds) {
                if (queryExist(catgCode, propGroupId, GdsConstants.Commons.STATUS_VALID))
                {
                    if(skipWhenExist){
                        continue; 
                    }else{
                        throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200311,new String[]{propGroupId.toString()});
                    }
                }
            GdsCatg2PGroup record = new GdsCatg2PGroup();
            record.setCatgCode(catgCode);
            record.setCreateStaff(createStaff);
            record.setCreateTime(now);
            record.setUpdateStaff(createStaff);
            record.setUpdateTime(now);
            record.setPropGroupId(propGroupId);
            record.setStatus(GdsConstants.Commons.STATUS_VALID);
            
            gdsCatg2PGroupMapper.insert(record);
        }
	}

	@Override
	public PageResponseDTO<GdsCatg2PGroupRespDTO> queryConfigedGdsPropGroupRespDTOPaging(
			GdsCatg2PGroupReqDTO reqDTO) throws BusinessException {
		GdsCatg2PGroupCriteria criteria = new GdsCatg2PGroupCriteria();
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        
        Criteria c = criteria.createCriteria();
        
        // 属性名查询条件.
        if(StringUtil.isNotBlank(reqDTO.getGroupName())){
        	GdsPropGroupReqDTO groupReqDTO = new GdsPropGroupReqDTO();
        	groupReqDTO.setGroupName(reqDTO.getGroupName());
        	groupReqDTO.setPageSize(Integer.MAX_VALUE);
        	List<Long> ids = gdsPropGroupSV.queryGroupIds(groupReqDTO);
        	
        	if(!CollectionUtils.isEmpty(ids)){
        		if(ids.size() == 1){
        			c.andPropGroupIdEqualTo(ids.get(0));
        		}else{
        			c.andPropGroupIdIn(ids);
        		}
        	}else{
        		c.andPropGroupIdEqualTo(-99999L);
        		
        	}
        }
        initCriteria(c, reqDTO);
        return super.queryByPagination(reqDTO, criteria, false,
                new GdsCatg2PGroupRespDTOPaginationCallback());
	}

	
	@Override
	public int batchDeleteCatg2Prop(String catgCode, List<Long> propGroupIds,
			Long updateStaff) throws BusinessException {
		paramCheck(new Object[]{catgCode,propGroupIds}, new String[]{"catgCode","propGroupIds"});
		GdsCatg2PGroupCriteria criteria = new GdsCatg2PGroupCriteria();
		Criteria c = criteria.createCriteria();
		if(1 == propGroupIds.size()){
			c.andPropGroupIdEqualTo(propGroupIds.get(0));
		}else{
			c.andPropGroupIdIn(propGroupIds);
		}
		c.andCatgCodeEqualTo(catgCode);
		
		GdsCatg2PGroup record = new GdsCatg2PGroup();
		record.setUpdateStaff(updateStaff);
		record.setUpdateTime(now());
		record.setStatus(GdsConstants.Commons.STATUS_INVALID);
		return gdsCatg2PGroupMapper.updateByExampleSelective(record, criteria);
		
	}

	@Override
	public List<Long> queryConfigedPropgroupsIds(String catgCode,
			String... status) throws BusinessException {
		return gdsCatg2PGroupManualMapper.queryConfigedPropGroupIds(catgCode, GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public boolean queryExist(String catgCode, Long propGroupId,
			String... status) throws BusinessException {
		GdsCatg2PGroupCriteria criteria = new GdsCatg2PGroupCriteria();
        Criteria c = criteria.createCriteria();
        c.andCatgCodeEqualTo(catgCode);
        c.andPropGroupIdEqualTo(propGroupId);
        initStatusCriteria(c, status);
        return gdsCatg2PGroupMapper.countByExample(criteria) > 0;
	}

	protected class GdsCatg2PGroupRespDTOPaginationCallback extends
			PaginationCallback<GdsCatg2PGroup, GdsCatg2PGroupRespDTO> {

		@Override
		public List<GdsCatg2PGroup> queryDB(BaseCriteria criteria) {
			return gdsCatg2PGroupMapper
					.selectByExample((GdsCatg2PGroupCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsCatg2PGroupMapper
					.countByExample((GdsCatg2PGroupCriteria) criteria);
		}

		@Override
		public GdsCatg2PGroupRespDTO warpReturnObject(GdsCatg2PGroup t) {
			GdsCatg2PGroupRespDTO dto = new GdsCatg2PGroupRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			GdsPropGroup group = gdsPropGroupSV.queryGdsPropGroupByPK(t.getPropGroupId());
			dto.setGroupName(group.getGroupName());
			return dto;
		}

}
	
	private void initCriteria(Criteria c, GdsCatg2PGroupReqDTO reqDTO) {
		if(StringUtil.isNotBlank(reqDTO.getCatgCode())){
			c.andCatgCodeEqualTo(reqDTO.getCatgCode());
		}
		if(null != reqDTO.getPropGroupId()){
			c.andPropGroupIdEqualTo(reqDTO.getPropGroupId());
		}
		if(StringUtil.isNotBlank(reqDTO.getStatus())){
			c.andStatusEqualTo(reqDTO.getStatus());
		}
	}

	
	
}

