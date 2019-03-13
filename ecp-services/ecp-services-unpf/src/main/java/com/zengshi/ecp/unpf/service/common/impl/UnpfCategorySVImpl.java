package com.zengshi.ecp.unpf.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.unpf.dao.mapper.common.UnpfPropMapper;
import com.zengshi.ecp.unpf.dao.mapper.common.UnpfPropValueMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfProp;
import com.zengshi.ecp.unpf.dao.model.UnpfPropCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfPropValue;
import com.zengshi.ecp.unpf.dao.model.UnpfPropValueCriteria;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCategorySyncReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCategorySyncRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRespDTO;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategorySV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class UnpfCategorySVImpl extends GeneralSQLSVImpl implements IUnpfCategorySV {

    private final static String MODULE = UnpfCategorySVImpl.class.getName();

    @Resource
    private UnpfPropMapper unpfPropMapper;
    @Resource
    private UnpfPropValueMapper unpfPropValueMapper;
    
    @Resource(name = "seq_unpf_prop_id")
    private PaasSequence seq_unpf_prop_id;
    
    @Resource(name = "seq_unpf_prop_value_id")
    private PaasSequence seq_unpf_prop_value_id;

    @Override
    public List<UnpfPropRespDTO> queryUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException {
        
        if (reqDTO == null) {
            return null;
        }
        UnpfPropCriteria criteria = new UnpfPropCriteria();
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setOrderByClause("SORT_NO ASC");
        
        com.zengshi.ecp.unpf.dao.model.UnpfPropCriteria.Criteria conditon = criteria.createCriteria();
        
        if (StringUtil.isNotEmpty(reqDTO.getShopId())) {
            conditon.andShopIdEqualTo(reqDTO.getShopId());
        }
        
        if (StringUtil.isNotBlank(reqDTO.getCatgCode())) {
            conditon.andCatgCodeEqualTo(reqDTO.getCatgCode());
        }
        if (StringUtil.isNotBlank(reqDTO.getPlatType())) {
            conditon.andPlatTypeEqualTo(reqDTO.getPlatType());
        }
        if (StringUtil.isNotEmpty(reqDTO.getShopAuthId())) {
            conditon.andShopAuthIdEqualTo(reqDTO.getShopAuthId());
        }
        
        List<UnpfProp> records = null;
        try {
            records = unpfPropMapper.selectByExample(criteria);

        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
        }
        
        if (CollectionUtils.isNotEmpty(records)) {
            List<UnpfPropRespDTO> result = new ArrayList<UnpfPropRespDTO>(records.size());
            for(UnpfProp row : records)
            {
                UnpfPropRespDTO one = new UnpfPropRespDTO();
                ObjectCopyUtil.copyObjValue(row, one, null, false);
                result.add(one);
            }
            return result;
        }
        
        return null;
    }

    @Override
    public List<UnpfPropValueRespDTO> queryUnpfPropValue(UnpfPropValueReqDTO reqDTO)
            throws BusinessException {
        
        if (reqDTO == null) {
            return null;
        }
        UnpfPropValueCriteria criteria = new UnpfPropValueCriteria();
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setOrderByClause("SORT_NO ASC");
        
        com.zengshi.ecp.unpf.dao.model.UnpfPropValueCriteria.Criteria conditon = criteria.createCriteria();
        
        
        if (StringUtil.isNotEmpty(reqDTO.getShopId())) {
            conditon.andShopIdEqualTo(reqDTO.getShopId());
        }
        
        if (StringUtil.isNotBlank(reqDTO.getCatgCode())) {
            conditon.andCatgCodeEqualTo(reqDTO.getCatgCode());
        }
        if (StringUtil.isNotBlank(reqDTO.getPlatType())) {
            conditon.andPlatTypeEqualTo(reqDTO.getPlatType());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropCode())) {
            conditon.andPropCodeEqualTo(reqDTO.getPropCode());
        }
        if (StringUtil.isNotEmpty(reqDTO.getShopAuthId())) {
            conditon.andShopAuthIdEqualTo(reqDTO.getShopAuthId());
        }
        List<UnpfPropValue> records = null;
        try {
            records = unpfPropValueMapper.selectByExample(criteria);

        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
        }
        
        if (CollectionUtils.isNotEmpty(records)) {
            List<UnpfPropValueRespDTO> result = new ArrayList<UnpfPropValueRespDTO>(records.size());
            for(UnpfPropValue row : records)
            {
                UnpfPropValueRespDTO one = new UnpfPropValueRespDTO();
                ObjectCopyUtil.copyObjValue(row, one, null, false);
                result.add(one);
            }
            return result;
        }
        
        return null;
    }

    @Override
    public Long insertUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException {
        
        if (reqDTO == null) {
            return null;
        }
        UnpfProp record = new UnpfProp();
        ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
        record.setId(seq_unpf_prop_id.nextValue());
        record.setStatus("1");
        record.setCreateStaff(StaffLocaleUtil.getStaff().getId());
        record.setCreateTime(DateUtil.getSysDate());
        record.setUpdateStaff(StaffLocaleUtil.getStaff().getId());
        record.setUpdateTime(DateUtil.getSysDate());
        
        try {
            unpfPropMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
        return record.getId();
    }

    @Override
    public void insertUnpfPropValue(UnpfPropValueReqDTO reqDTO) throws BusinessException {
        
        if (reqDTO == null) {
            return;
        }
        UnpfPropValue record = new UnpfPropValue();
        ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
        record.setId(seq_unpf_prop_value_id.nextValue());
        record.setStatus("1");
        record.setCreateStaff(StaffLocaleUtil.getStaff().getId());
        record.setCreateTime(DateUtil.getSysDate());
        record.setUpdateStaff(StaffLocaleUtil.getStaff().getId());
        record.setUpdateTime(DateUtil.getSysDate());
        
        try {
            unpfPropValueMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteUnpfPropTable(UnpfPropReqDTO reqDTO) throws BusinessException {
        
        UnpfPropCriteria criteria = new UnpfPropCriteria();
        
        com.zengshi.ecp.unpf.dao.model.UnpfPropCriteria.Criteria condition = criteria.createCriteria();
        condition.andStatusEqualTo("1");
        condition.andShopAuthIdEqualTo(reqDTO.getShopAuthId());
        condition.andShopIdEqualTo(reqDTO.getShopId());
        condition.andPlatTypeEqualTo(reqDTO.getPlatType());
        
        try {
            unpfPropMapper.deleteByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteUnpfPropValueTable(UnpfPropValueReqDTO reqDTO) throws BusinessException {
        
        UnpfPropValueCriteria criteria = new UnpfPropValueCriteria();
        
        com.zengshi.ecp.unpf.dao.model.UnpfPropValueCriteria.Criteria condition = criteria.createCriteria();
        condition.andStatusEqualTo("1");
        condition.andShopAuthIdEqualTo(reqDTO.getShopAuthId());
        condition.andShopIdEqualTo(reqDTO.getShopId());
        condition.andPlatTypeEqualTo(reqDTO.getPlatType());
        
        try {
            unpfPropValueMapper.deleteByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }



}

