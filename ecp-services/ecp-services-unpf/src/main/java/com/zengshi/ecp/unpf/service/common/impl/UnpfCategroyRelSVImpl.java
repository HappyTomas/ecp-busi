package com.zengshi.ecp.unpf.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategorySyncRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.unpf.dao.mapper.common.UnpfCatgRelaMapper;
import com.zengshi.ecp.unpf.dao.mapper.common.UnpfPropRelaMapper;
import com.zengshi.ecp.unpf.dao.mapper.common.UnpfPropValueRelaMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfCatgRela;
import com.zengshi.ecp.unpf.dao.model.UnpfCatgRelaCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfPropRela;
import com.zengshi.ecp.unpf.dao.model.UnpfPropRelaCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfPropValueRela;
import com.zengshi.ecp.unpf.dao.model.UnpfPropValueRelaCriteria;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCategorySyncReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaRespDTO;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategorySV;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategroyRelSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
@Service(value="unpfCategroyRelSV")
public class UnpfCategroyRelSVImpl implements IUnpfCategroyRelSV {
    
    private final static String MODULE = UnpfCategroyRelSVImpl.class.getName();

    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    @Resource
    private IGdsCategorySyncRSV gdsCategorySyncRSV; 
    
    @Resource
    private UnpfCatgRelaMapper unpfCatgRelaMapper;
    @Resource
    private UnpfPropRelaMapper unpfPropRelaMapper;
    @Resource
    private UnpfPropValueRelaMapper unpfPropValueRelaMapper;

    @Resource(name = "seq_unpf_catg_rela_id")
    private PaasSequence seq_unpf_catg_rela_id;
    
    @Resource(name = "seq_unpf_prop_rela_id")
    private PaasSequence seq_unpf_prop_rela_id;
    
    @Resource(name = "seq_unpf_prop_value_rela_id")
    private PaasSequence seq_unpf_prop_value_rela_id;
      
    @Override
    public void insertCatgRela(UnpfCatgRelaReqDTO relaReqDTO) throws BusinessException {
        
        if (relaReqDTO == null) {
            return;
        }
        UnpfCatgRela model = new UnpfCatgRela(); 
        ObjectCopyUtil.copyObjValue(relaReqDTO, model, null, false);
        model.setId(seq_unpf_catg_rela_id.nextValue());
        model.setStatus("1");
        model.setCreateStaff(StaffLocaleUtil.getStaff().getId());
        model.setCreateTime(DateUtil.getSysDate());
        model.setUpdateStaff(StaffLocaleUtil.getStaff().getId());
        model.setUpdateTime(DateUtil.getSysDate());
        
        try {
            unpfCatgRelaMapper.insert(model);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void insertPropRela(UnpfPropRelaReqDTO propReqDTO) throws BusinessException {
        
        if (propReqDTO == null) {
            return;
        }
        UnpfPropRela model = new UnpfPropRela(); 
        ObjectCopyUtil.copyObjValue(propReqDTO, model, null, false);
        model.setId(seq_unpf_prop_rela_id.nextValue());
        model.setStatus("1");
        model.setCreateStaff(StaffLocaleUtil.getStaff().getId());
        model.setCreateTime(DateUtil.getSysDate());
        model.setUpdateStaff(StaffLocaleUtil.getStaff().getId());
        model.setUpdateTime(DateUtil.getSysDate());
        
        try {
            unpfPropRelaMapper.insert(model);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void insertPropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)
            throws BusinessException {
        
        if (propValueRelaReqDTO == null) {
            return;
        }
        UnpfPropValueRela model = new UnpfPropValueRela(); 
        ObjectCopyUtil.copyObjValue(propValueRelaReqDTO, model, null, false);
        model.setId(seq_unpf_prop_value_rela_id.nextValue());
        model.setStatus("1");
        model.setCreateStaff(StaffLocaleUtil.getStaff().getId());
        model.setCreateTime(DateUtil.getSysDate());
        model.setUpdateStaff(StaffLocaleUtil.getStaff().getId());
        model.setUpdateTime(DateUtil.getSysDate());
        
        try {
            unpfPropValueRelaMapper.insert(model);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteCatgRela(UnpfCatgRelaReqDTO relaReqDTO) throws BusinessException {
        
        if (relaReqDTO == null) {
            return;
        }

    }

    @Override
    public void deletePropRela(UnpfPropRelaReqDTO propRelReqDTO) throws BusinessException {
    }

    @Override
    public void deletePropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)
            throws BusinessException {
    }

    @Override
    public List<UnpfCatgRelaRespDTO> selectCatgRelaList(UnpfCatgRelaReqDTO relaReqDTO) throws BusinessException {
        UnpfCatgRelaCriteria criteria = new UnpfCatgRelaCriteria();
        com.zengshi.ecp.unpf.dao.model.UnpfCatgRelaCriteria.Criteria condition = criteria.createCriteria();
        
        if (StringUtil.isNotBlank(relaReqDTO.getCatgCode())) {
            condition.andCatgCodeEqualTo(relaReqDTO.getCatgCode());
        }
        if (StringUtil.isNotBlank(relaReqDTO.getOutCatgCode())) {
           condition.andOutCatgCodeEqualTo(relaReqDTO.getOutCatgCode());
        }
        if (StringUtil.isNotBlank(relaReqDTO.getPlatType())) {
            condition.andPlatTypeEqualTo(relaReqDTO.getPlatType());
        }
        if (StringUtil.isNotEmpty(relaReqDTO.getShopId())) {
            condition.andShopIdEqualTo(relaReqDTO.getShopId());
        }
        if (StringUtil.isNotEmpty(relaReqDTO.getShopAuthId())) {
            condition.andShopAuthIdEqualTo(relaReqDTO.getShopAuthId());
        }
        if (StringUtil.isNotBlank(relaReqDTO.getStatus())) {
            condition.andStatusEqualTo(relaReqDTO.getStatus());
        }
        List<UnpfCatgRela> records = null;
        try {
            records = unpfCatgRelaMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
        
        if (CollectionUtils.isNotEmpty(records)) {
            List<UnpfCatgRelaRespDTO> results = new ArrayList<UnpfCatgRelaRespDTO>(records.size());
            for(UnpfCatgRela row : records){
                UnpfCatgRelaRespDTO one = new UnpfCatgRelaRespDTO();
                ObjectCopyUtil.copyObjValue(row, one, null, false);
                addCategoryName(row, one);
                results.add(one);
            }
            return results;
        }
        return null;
        
    }
    private void addCategoryName(UnpfCatgRela row, UnpfCatgRelaRespDTO one){
        
        GdsCategoryReqDTO gdsCateReqDTO = new GdsCategoryReqDTO();
        gdsCateReqDTO.setCatgCode(row.getCatgCode());
        GdsCategoryRespDTO gdsCateInfo = gdsCategoryRSV.queryGdsCategoryByPK(gdsCateReqDTO);
        if (gdsCateInfo != null) {
            one.setCatgCodeName(gdsCateInfo.getCatgName());
        }
        GdsCatgSyncReqDTO catgSyncReqDTO = new GdsCatgSyncReqDTO();
        catgSyncReqDTO.setCatgCode(row.getOutCatgCode());
        catgSyncReqDTO.setSrcSystem(row.getPlatType());
        if (StringUtil.isNotEmpty(row.getShopId())) {
            catgSyncReqDTO.setShopId(row.getShopId());
        }
        if (StringUtil.isNotEmpty(row.getShopAuthId())) {
            catgSyncReqDTO.setShopAuthId(row.getShopAuthId());
        }
        GdsCatgSyncRespDTO catgSyncInfo = gdsCategorySyncRSV.queryGdsCategorySyncByPK(catgSyncReqDTO);
        if (catgSyncInfo != null) {
            one.setOutCatgCodeName(catgSyncInfo.getCatgName());
        }
    }
    @Override
    public List<UnpfPropRelaRespDTO> selectPropRelaList(UnpfPropRelaReqDTO propRelReqDTO) throws BusinessException {
    
        if (propRelReqDTO == null) {
            return null;
        }
        
        UnpfPropRelaCriteria criteria = new UnpfPropRelaCriteria();
        com.zengshi.ecp.unpf.dao.model.UnpfPropRelaCriteria.Criteria condition = criteria.createCriteria();
        
        if (StringUtil.isNotEmpty(propRelReqDTO.getPropId())) {
            condition.andPropIdEqualTo(propRelReqDTO.getPropId());
        }
        if (StringUtil.isNotBlank(propRelReqDTO.getOutPropId())) {
            condition.andOutPropIdEqualTo(propRelReqDTO.getOutPropId());
        }
        if (StringUtil.isNotEmpty(propRelReqDTO.getShopId())) {
            condition.andShopIdEqualTo(propRelReqDTO.getShopId());
        }
        if (StringUtil.isNotEmpty(propRelReqDTO.getShopAuthId())) {
            condition.andShopAuthIdEqualTo(propRelReqDTO.getShopAuthId());
        }
        if (StringUtil.isNotBlank(propRelReqDTO.getPlatType())) {
            condition.andPlatTypeEqualTo(propRelReqDTO.getPlatType());
        }
        if (StringUtil.isNotBlank(propRelReqDTO.getStatus())) {
            condition.andStatusEqualTo(propRelReqDTO.getStatus());
        }
        
        List<UnpfPropRela> records = null;
        try {
            records = unpfPropRelaMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
        if (CollectionUtils.isNotEmpty(records)) {
            List<UnpfPropRelaRespDTO> results = new ArrayList<UnpfPropRelaRespDTO>(records.size());
            for(UnpfPropRela row : records){
                UnpfPropRelaRespDTO one = new UnpfPropRelaRespDTO();
                ObjectCopyUtil.copyObjValue(row, one, null, false);
                results.add(one);
            }
            return results;
        }
        
        return null;
    }

    @Override
    public List<UnpfPropValueRelaRespDTO> selectPropValueRelaList(UnpfPropValueRelaReqDTO propValueRelaReqDTO)
            throws BusinessException {
        
        UnpfPropValueRelaCriteria criteria = new UnpfPropValueRelaCriteria();
        com.zengshi.ecp.unpf.dao.model.UnpfPropValueRelaCriteria.Criteria condition = criteria.createCriteria();
        
        if (StringUtil.isNotEmpty(propValueRelaReqDTO.getOutPropId())) {
            condition.andOutPropIdEqualTo(propValueRelaReqDTO.getOutPropId());
        }
        if (StringUtil.isNotBlank(propValueRelaReqDTO.getOutVid())) {
            condition.andOutVidEqualTo(propValueRelaReqDTO.getOutVid());
        }
        if (StringUtil.isNotBlank(propValueRelaReqDTO.getOutVidName())) {
            condition.andOutVidNameEqualTo(propValueRelaReqDTO.getOutVidName());
        }
        if (StringUtil.isNotBlank(propValueRelaReqDTO.getPropId())) {
            condition.andPropIdEqualTo(propValueRelaReqDTO.getPropId());
        }
        if (StringUtil.isNotBlank(propValueRelaReqDTO.getVid())) {
            condition.andVidEqualTo(propValueRelaReqDTO.getVid());
        }
        if (StringUtil.isNotBlank(propValueRelaReqDTO.getVidName())) {
            condition.andVidNameEqualTo(propValueRelaReqDTO.getVidName());
        }
        if (StringUtil.isNotEmpty(propValueRelaReqDTO.getShopId())) {
            condition.andShopIdEqualTo(propValueRelaReqDTO.getShopId());
        }
        if (StringUtil.isNotEmpty(propValueRelaReqDTO.getShopAuthId())) {
            condition.andShopAuthIdEqualTo(propValueRelaReqDTO.getShopAuthId());
        }
        if (StringUtil.isNotBlank(propValueRelaReqDTO.getPlatType())) {
            condition.andPlatTypeEqualTo(propValueRelaReqDTO.getPlatType());
        }
        if (StringUtil.isNotBlank(propValueRelaReqDTO.getStatus())) {
            condition.andStatusEqualTo(propValueRelaReqDTO.getStatus());
        }
        List<UnpfPropValueRela> records = null;
        try {
            records = unpfPropValueRelaMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
        if (CollectionUtils.isNotEmpty(records)) {
            List<UnpfPropValueRelaRespDTO> results = new ArrayList<UnpfPropValueRelaRespDTO>(records.size());
            for(UnpfPropValueRela row : records){
                UnpfPropValueRelaRespDTO one = new UnpfPropValueRelaRespDTO();
                ObjectCopyUtil.copyObjValue(row, one, null, false);
                results.add(one);
            }
            return results;
        }
        
        return null;
    }

    @Override
    public void deleteCatgRelaByKey(Long key) throws BusinessException {
        
        if (key == null) {
            return;
        }
        
        try {
            unpfCatgRelaMapper.deleteByPrimaryKey(key);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void deletePropRelaByKey(Long key) throws BusinessException {
        
        if (key == null) {
            return;
        }
        
        try {
            unpfPropRelaMapper.deleteByPrimaryKey(key);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

    @Override
    public void deletePropValueRelaByKey(Long key) throws BusinessException {
        
        if (key == null) {
            return;
        }
        
        try {
            unpfPropValueRelaMapper.deleteByPrimaryKey(key);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }
}

