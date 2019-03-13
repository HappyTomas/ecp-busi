/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsPropGroupSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月19日上午11:21:52 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsPropGroupMapper;
import com.zengshi.ecp.goods.dao.mapper.common.manual.GdsPropGroupManualMapper;
import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dao.model.GdsPropGroup;
import com.zengshi.ecp.goods.dao.model.GdsPropGroupCriteria;
import com.zengshi.ecp.goods.dao.model.GdsPropGroupCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsProp2GroupSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropGroupSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 属性组服务接口实现类.<br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月19日上午11:21:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsPropGroupSVImpl extends AbstractSVImpl implements IGdsPropGroupSV{
    @Resource(name="seq_gds_prop_group")
    private PaasSequence seqGdsPropGroup;
    @Resource
    private GdsPropGroupMapper gdsPropGroupMapper;
    @Resource
    private IGdsProp2GroupSV gdsProp2GroupSV;
    @Resource
    private IGdsPropSV gdsPropSV;
    @Resource
    private GdsPropGroupManualMapper gdsPropGroupManualMapper;
    
    private static final String DEFAULT_ORDER_BY = "CREATE_TIME DESC";
    
    
    
    @Override
    public void createGdsPropGroup(GdsPropGroupReqDTO dto) throws BusinessException {
        GdsPropGroup gdsPropGroup = new GdsPropGroup();
        ObjectCopyUtil.copyObjValue(dto, gdsPropGroup, null, false);
        if(StringUtil.isBlank(dto.getStatus())){
        	dto.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
        preInsert(dto, gdsPropGroup);
        saveGdsPropGroup(gdsPropGroup);
        if(null != dto.getPropIds() && !dto.getPropIds().isEmpty()){
            gdsProp2GroupSV.batchAddGdsProp2Groups(gdsPropGroup.getId(), dto.getPropIds(), gdsPropGroup.getCreateStaff(), true);
        }
    }
    

    @Override
    public GdsPropGroupRespDTO queryGdsPropGroup(Long id, String... status)
            throws BusinessException {
    	GdsPropGroup record = gdsPropGroupMapper.selectByPrimaryKey(id);
        if (record != null) {
        	GdsPropGroupRespDTO respDTO = new GdsPropGroupRespDTO();
            ObjectCopyUtil.copyObjValue(record, respDTO, null, true);
        }
        return null;
    }
    
    
    @Override
    public GdsPropGroupRespDTO queryGdsPropGroupAndProps(Long id)
            throws BusinessException {
    	GdsPropGroup record = gdsPropGroupMapper.selectByPrimaryKey(id);
        if (record != null) {
        	GdsPropGroupRespDTO respDTO = new GdsPropGroupRespDTO();
            ObjectCopyUtil.copyObjValue(record, respDTO, null, true);
            List<Long> propIds=gdsProp2GroupSV.queryConfigedPropIds(id, GdsConstants.Commons.STATUS_VALID);
            List<GdsPropRespDTO> props=null;
            if(CollectionUtils.isNotEmpty(propIds)){
            	props=new ArrayList<GdsPropRespDTO>();
            	for (Long propId : propIds) {
            		GdsProp prop=gdsPropSV.queryGdsPropByPK(propId,true);
            		if(prop!=null){
            			GdsPropRespDTO gdsPropRespDTO=new GdsPropRespDTO();
            			ObjectCopyUtil.copyObjValue(prop, gdsPropRespDTO, null, false);
            			props.add(gdsPropRespDTO);
            		}
				}
            	respDTO.setProps(props);
            }
            return respDTO;
        }
        return null;
    }

    



    @Override
    public int editGdsPropGroup(GdsPropGroupReqDTO dto) throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if(null == dto.getId()){
            errorMsg.append("dto.id,");
        }
        if(!StringUtils.hasText(dto.getGroupName())){
            errorMsg.append("dto.groupName,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        if(queryExist(dto.getGroupName(),new Long[]{dto.getId()}, GdsConstants.Commons.STATUS_VALID)){
            throw new BusinessException(GdsErrorConstants.GdsPropGroup.ERROR_GOODS_PROPGROUP_200504);
        }
        
        gdsProp2GroupSV.batchDeleteGdsProps(dto.getId(), dto.getStaff().getId());
        if(null != dto.getPropIds() && !dto.getPropIds().isEmpty()){
            gdsProp2GroupSV.batchAddGdsProp2Groups(dto.getId(), dto.getPropIds(), dto.getCreateStaff(), true);
        }
        GdsPropGroup propGroup = new GdsPropGroup();
        ObjectCopyUtil.copyObjValue(dto, propGroup, null, false);
        editGdsPropGroup(propGroup, dto.getUpdateStaff());
        return 0;
    }



    @Override
    public void saveGdsPropGroup(GdsPropGroup gdsPropGroup) throws BusinessException {
        
    	Timestamp now = now();
    	gdsPropGroup.setId(seqGdsPropGroup.nextValue());
        
        if(null ==  gdsPropGroup.getCreateTime()){
        	gdsPropGroup.setCreateTime(now);
        }
        if(null == gdsPropGroup.getUpdateTime()){
        	gdsPropGroup.setUpdateTime(now);
        }
        if(StringUtil.isBlank(gdsPropGroup.getStatus())){
        	 gdsPropGroup.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
        gdsPropGroupMapper.insert(gdsPropGroup);
    }

    @Override
    public GdsPropGroup queryGdsPropGroupByPK(Long id) throws BusinessException {
       return gdsPropGroupMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public GdsPropGroup queryGdsPropGroupByPK(Long id, boolean isValid) throws BusinessException {
        GdsPropGroup gdsPropGroup = gdsPropGroupMapper.selectByPrimaryKey(id);
        if(gdsPropGroup != null){
            if(isValid && !GdsConstants.Commons.STATUS_VALID.equals(gdsPropGroup.getStatus())){
                gdsPropGroup = null;
            }
        }
        return gdsPropGroup;
    }

    @Override
    public int editGdsPropGroup(GdsPropGroup gdsPropGroup, Long updateStaff)
            throws BusinessException {
        Timestamp now = now();
        gdsPropGroup.setUpdateStaff(updateStaff);
        gdsPropGroup.setUpdateTime(now);
        return gdsPropGroupMapper.updateByPrimaryKeySelective(gdsPropGroup);
    }

    @Override
    public boolean queryExist(String groupName, String... status) throws BusinessException {
        return queryExist(groupName, null, status);
    }
    
    @Override
    public boolean queryExist(String groupName, Long[] excludeGroupId, String... status)
            throws BusinessException {
        if(!StringUtils.hasText(groupName)){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"groupName"});
        }
        GdsPropGroupCriteria criteria = new GdsPropGroupCriteria();
        Criteria c = criteria.createCriteria();
        c.andGroupNameEqualTo(groupName);
        initStatusCriteria(c, status);
        
        if(null != excludeGroupId){
            switch (excludeGroupId.length) {
            case 1:
                c.andIdNotEqualTo(excludeGroupId[0]);
                break;
            default:
                c.andIdNotIn(Arrays.asList(excludeGroupId));
                break;
            }
        }
        
        return gdsPropGroupMapper.countByExample(criteria) > 0;
    }



    @Override
    public int executeDisableGdsPropGroup(Long id, Long updateStaff) throws BusinessException {
        GdsPropGroup gdsPropGroup = gdsPropGroupMapper.selectByPrimaryKey(id);
        if(gdsPropGroup == null){
            throw new BusinessException(GdsErrorConstants.GdsPropGroup.ERROR_GOODS_PROPGROUP_200500);
        }
        if(GdsConstants.Commons.STATUS_INVALID.equals(gdsPropGroup.getStatus())){
            throw new BusinessException(GdsErrorConstants.GdsPropGroup.ERROR_GOODS_PROPGROUP_200502);
        }
        gdsPropGroup.setStatus(GdsConstants.Commons.STATUS_INVALID);
        gdsPropGroup.setUpdateStaff(updateStaff);
        gdsPropGroup.setUpdateTime(now());
        return gdsPropGroupMapper.updateByPrimaryKeySelective(gdsPropGroup);
    }
    
    @Override
    public int executeEnableGdsPropGroup(Long id, Long updateStaff) throws BusinessException {
        GdsPropGroup gdsPropGroup = gdsPropGroupMapper.selectByPrimaryKey(id);
        if(gdsPropGroup == null){
            throw new BusinessException(GdsErrorConstants.GdsPropGroup.ERROR_GOODS_PROPGROUP_200500);
        }
        if(GdsConstants.Commons.STATUS_VALID.equals(gdsPropGroup.getStatus())){
            throw new BusinessException(GdsErrorConstants.GdsPropGroup.ERROR_GOODS_PROPGROUP_200505);
        }
        gdsPropGroup.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsPropGroup.setUpdateStaff(updateStaff);
        gdsPropGroup.setUpdateTime(now());
        return gdsPropGroupMapper.updateByPrimaryKeySelective(gdsPropGroup);
    }

    @Override
    public PageResponseDTO<GdsPropGroupRespDTO> queryGdsPropGroupRespDTOPaging(
            GdsPropGroupReqDTO dto) throws BusinessException {
    	return queryGdsPropGroupRespDTOPaging(dto,null,null);
    }
    
    @Override
    public PageResponseDTO<GdsPropGroupRespDTO> queryGdsPropGroupRespDTOPaging(
            GdsPropGroupReqDTO dto,List<Long> excludeIds, List<Long> includeIds) throws BusinessException {
        GdsPropGroupCriteria criteria = new GdsPropGroupCriteria();
        criteria.setLimitClauseStart(dto.getStartRowIndex());
        criteria.setLimitClauseCount(dto.getPageSize());
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        if (excludeIds != null && !excludeIds.isEmpty()) {
            c.andIdNotIn(excludeIds);
        }
        if (includeIds != null && !includeIds.isEmpty()) {
            c.andIdIn(includeIds);
        }
        initCriteria(c,dto);
        return super.queryByPagination(dto, criteria, false, new GdsPropGroupRespDTOPaginationCallback());
    }
    
    
    
    @Override
	public List<Long> queryGroupIds(GdsPropGroupReqDTO reqDTO)
			throws BusinessException {
    	if(reqDTO == null){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"reqDTO"});
        }
        if(StringUtil.isBlank(reqDTO.getGroupName())){
        	throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"reqDTO.groupName"});
        }
        GdsPropGroupCriteria criteria = new GdsPropGroupCriteria();
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        Criteria c = criteria.createCriteria();
        initCriteria(c,reqDTO);
        return gdsPropGroupManualMapper.queryPropGroupIdsByExample(criteria);
	}




	/**
     * 
     * 商品属性组分页查询回调类。 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月19日下午3:12:11  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsPropGroupSVImpl 
     * @since JDK 1.6
     */
    protected class GdsPropGroupRespDTOPaginationCallback extends PaginationCallback<GdsPropGroup, GdsPropGroupRespDTO>{

        @Override
        public List<GdsPropGroup> queryDB(BaseCriteria criteria) {
           return gdsPropGroupMapper.selectByExample((GdsPropGroupCriteria)criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
           return gdsPropGroupMapper.countByExample((GdsPropGroupCriteria)criteria);
        }

        @Override
        public GdsPropGroupRespDTO warpReturnObject(GdsPropGroup t) {
            GdsPropGroupRespDTO dto = new GdsPropGroupRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }
        
    }
    
    
    /** 
     * 初始化查询条件. <br/> 
     * 
     * @author liyong7
     * @param criteria
     * @param dto 
     * @since JDK 1.6 
     */ 
    private Criteria initCriteria(Criteria c, GdsPropGroupReqDTO dto) {
        if(null != dto.getId()){
            c.andIdEqualTo(dto.getId());
        }
        if(StringUtils.hasText(dto.getGroupName())){
            c.andGroupNameLike("%"+dto.getGroupName()+"%");
        }
        if(StringUtils.hasText(dto.getStatus())){
            c.andStatusEqualTo(dto.getStatus());
        }
        return c;
        
    }
    
    
}

