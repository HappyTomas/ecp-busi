/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsProp2GroupSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月19日下午4:40:20 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.mapper.common.GdsProp2GroupMapper;
import com.zengshi.ecp.goods.dao.mapper.common.manual.GdsProp2GroupManualMapper;
import com.zengshi.ecp.goods.dao.model.GdsProp2Group;
import com.zengshi.ecp.goods.dao.model.GdsProp2GroupCriteria;
import com.zengshi.ecp.goods.dao.model.GdsProp2GroupCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsProp2GroupSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月19日下午4:40:20  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */ 
public class GdsProp2GroupSVImpl extends AbstractSVImpl implements IGdsProp2GroupSV{
    
    @Resource
    private GdsProp2GroupMapper gdsProp2GroupMapper;
    @Resource
    private GdsProp2GroupManualMapper gdsProp2GroupManualMapper;
    @Resource
    private IGdsPropSV gdsPropSV;

    @Override
    public GdsProp2Group saveGdsProp2Group(GdsProp2Group gdsProp2Group) throws BusinessException {
    	Timestamp now = now();
        gdsProp2Group.setCreateTime(now);
        gdsProp2Group.setUpdateTime(now);
        gdsProp2GroupMapper.insert(gdsProp2Group);
        return gdsProp2Group;
    }

    @Override
    public void batchAddGdsProp2Groups(Long propGrupId, List<Long> propIds, Long createStaff,
            boolean skipWhenExits) throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if(null == propGrupId){
            errorMsg.append("propGrupId,");
        }
        if(null == propIds || propIds.isEmpty()){
            errorMsg.append("propIds,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        Timestamp now = now();
        for (Long propId : propIds) {
            if(queryExist(propGrupId, propId, GdsConstants.Commons.STATUS_VALID)){
                if(skipWhenExits){
                    continue;
                }else{
                    throw new BusinessException(GdsErrorConstants.GdsPropGroup.ERROR_GOODS_PROPGROUP_200506,new String[]{propId.toString()});
                }
            }
            GdsProp2Group obj = new GdsProp2Group();
            obj.setPropgroupId(propGrupId);
            obj.setPropId(propId);
            obj.setStatus(GdsConstants.Commons.STATUS_VALID);
            obj.setCreateStaff(createStaff);
            obj.setCreateTime(now);
            gdsProp2GroupMapper.insert(obj);
        }

    }

    @Override
    public PageResponseDTO<GdsPropRespDTO> queryConfigedPropsPaging(Long propgroupId,GdsPropReqDTO dto)
            throws BusinessException {
        List<Long> configedPropIds = queryConfigedPropIds(propgroupId, GdsConstants.Commons.STATUS_VALID);
        return gdsPropSV.queryGdsPropRespDTOPaging(dto, null, configedPropIds);
    }

    @Override
    public PageResponseDTO<GdsPropRespDTO> queryUnconfigedPropsPaging(Long propgroupId,GdsPropReqDTO dto)
            throws BusinessException {
        List<Long> configedPropIds = queryConfigedPropIds(propgroupId, GdsConstants.Commons.STATUS_VALID);
        return gdsPropSV.queryGdsPropRespDTOPaging(dto, configedPropIds, null);
    }

    @Override
    public int batchDeleteGdsProps(Long propgroupId, List<Long> propIds, Long updateStaff)
            throws BusinessException {
        return gdsProp2GroupManualMapper.batchDeleteGdsProps(propgroupId, propIds, updateStaff, now());
    }

    @Override
    public List<Long> queryConfigedPropIds(Long propgroupId, String... status)
            throws BusinessException {
        return gdsProp2GroupManualMapper.queryConfigedPropIds(propgroupId, status);
    }

    @Override
    public boolean queryExist(Long propgroupId, Long propId, String... status)
            throws BusinessException {
        GdsProp2GroupCriteria criteria = new GdsProp2GroupCriteria();
        Criteria c = criteria.createCriteria();
        c.andPropgroupIdEqualTo(propgroupId);
        c.andPropIdEqualTo(propId);
        initStatusCriteria(c, status);
        return gdsProp2GroupMapper.countByExample(criteria) > 0;
    }

	@Override
	public int batchDeleteGdsProps(Long propgroupId, Long updateStaff)
			throws BusinessException {
		GdsProp2GroupCriteria gdsProp2GroupCriteria=new GdsProp2GroupCriteria();
		GdsProp2GroupCriteria.Criteria criteria=gdsProp2GroupCriteria.createCriteria();
		criteria.andPropgroupIdEqualTo(propgroupId);
		
		GdsProp2Group gdsProp2Group=new GdsProp2Group();
		gdsProp2Group.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsProp2Group.setUpdateStaff(updateStaff);
		gdsProp2Group.setUpdateTime(now());
		return gdsProp2GroupMapper.updateByExampleSelective(gdsProp2Group, gdsProp2GroupCriteria);
	}
    
    
}

