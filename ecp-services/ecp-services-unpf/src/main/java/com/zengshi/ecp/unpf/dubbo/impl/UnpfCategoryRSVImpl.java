package com.zengshi.ecp.unpf.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCategorySyncReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCategorySyncRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfCategoryRSV;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategorySV;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategroyRelSV;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class UnpfCategoryRSVImpl implements IUnpfCategoryRSV{
    
    @Resource
    private IUnpfCategorySV unpfCategorySV;
    @Resource
    private IUnpfCategroyRelSV unpfCategroyRelSV;


    @Override
    public List<UnpfPropRespDTO> queryUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException {
        return unpfCategorySV.queryUnpfProp(reqDTO);
    }

    @Override
    public List<UnpfPropValueRespDTO> queryUnpfPropValue(UnpfPropValueReqDTO reqDTO)
            throws BusinessException {
        return unpfCategorySV.queryUnpfPropValue(reqDTO);
    }

    @Override
    public void insertCatgRela(UnpfCatgRelaReqDTO relaReqDTO) throws BusinessException {
        if (relaReqDTO == null) {
            return;
        }
        unpfCategroyRelSV.insertCatgRela(relaReqDTO);
    }

    @Override
    public void insertPropRela(UnpfPropRelaReqDTO propRelReqDTO) throws BusinessException {
        if (propRelReqDTO == null) {
            return ;
        }
        unpfCategroyRelSV.insertPropRela(propRelReqDTO);
    }

    @Override
    public void insertPropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)
            throws BusinessException {
        if (propValueRelaReqDTO == null) {
            return ;
        }
        unpfCategroyRelSV.insertPropValueRela(propValueRelaReqDTO);

    }

    @Override
    public void deleteCatgRelaByKey(Long key) throws BusinessException {
        if (key == null) {
            return;
        }
        unpfCategroyRelSV.deleteCatgRelaByKey(key);
    }

    @Override
    public void deletePropRelaByKey(Long key) throws BusinessException {
        if (key == null) {
            return;
        }
        unpfCategroyRelSV.deletePropRelaByKey(key);
    }

    @Override
    public void deletePropValueRelaByKey(Long key) throws BusinessException {
        if (key ==null) {
            return ;
        }
        unpfCategroyRelSV.deletePropValueRelaByKey(key);
    }

    @Override
    public List<UnpfCatgRelaRespDTO> selectCatgRelaList(UnpfCatgRelaReqDTO relaReqDTO)
            throws BusinessException {
        return unpfCategroyRelSV.selectCatgRelaList(relaReqDTO);
    }

    @Override
    public List<UnpfPropRelaRespDTO> selectPropRelaList(UnpfPropRelaReqDTO propRelReqDTO)
            throws BusinessException {
        return unpfCategroyRelSV.selectPropRelaList(propRelReqDTO);
    }

    @Override
    public List<UnpfPropValueRelaRespDTO> selectPropValueRelaList(
            UnpfPropValueRelaReqDTO propValueRelaReqDTO) throws BusinessException {
        return unpfCategroyRelSV.selectPropValueRelaList(propValueRelaReqDTO);
    }

    @Override
    public UnpfCatgRelaRespDTO checkCatgRela(UnpfCatgRelaReqDTO relaReqDTO)
            throws BusinessException {
        
        if (relaReqDTO == null) {
            throw new BusinessException();
        }
        
        UnpfCatgRelaReqDTO param = new UnpfCatgRelaReqDTO();
        
        param.setCatgCode(relaReqDTO.getCatgCode());
        param.setShopId(relaReqDTO.getShopId());
        param.setShopAuthId(relaReqDTO.getShopAuthId());
        param.setPlatType(relaReqDTO.getPlatType());
        param.setOutCatgCode(relaReqDTO.getOutCatgCode());
        
        List<UnpfCatgRelaRespDTO> records = unpfCategroyRelSV.selectCatgRelaList(param);
        if (CollectionUtils.isNotEmpty(records)) {
            return records.get(0);
        }
        
        return null;
    }

    @Override
    public UnpfPropRelaRespDTO checkPropRela(UnpfPropRelaReqDTO propRelReqDTO)
            throws BusinessException {
        
        if (propRelReqDTO == null) {
            throw new BusinessException();
        }
        
        UnpfPropRelaReqDTO param = new UnpfPropRelaReqDTO();
        param.setPropId(propRelReqDTO.getPropId());
        param.setShopId(propRelReqDTO.getShopId());
        param.setShopAuthId(propRelReqDTO.getShopAuthId());
        param.setPlatType(propRelReqDTO.getPlatType());
        
        List<UnpfPropRelaRespDTO> records = unpfCategroyRelSV.selectPropRelaList(param);
        
        if (CollectionUtils.isNotEmpty(records)) {
            return records.get(0);
        }
        
        return null;
    }

    @Override
    public UnpfPropValueRelaRespDTO checkPropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)
            throws BusinessException {
        
        if (propValueRelaReqDTO == null) {
            throw new BusinessException();
        }
        
        UnpfPropValueRelaReqDTO param = new UnpfPropValueRelaReqDTO();
        
        param.setPropId(propValueRelaReqDTO.getPropId());
        param.setVid(propValueRelaReqDTO.getVid());
        param.setShopId(propValueRelaReqDTO.getShopId());
        param.setShopAuthId(propValueRelaReqDTO.getShopAuthId());
        param.setPlatType(propValueRelaReqDTO.getPlatType());
        
        List<UnpfPropValueRelaRespDTO> records = unpfCategroyRelSV.selectPropValueRelaList(param);
        
        if (CollectionUtils.isNotEmpty(records)) {
            return records.get(0);
        }
        
        return null;
    }

    @Override
    public void insertUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException {
        unpfCategorySV.insertUnpfProp(reqDTO);
    }

    @Override
    public void insertUnpfPropValue(UnpfPropValueReqDTO reqDTO) throws BusinessException {
        unpfCategorySV.insertUnpfPropValue(reqDTO);
    }

    @Override
    public void emptyUnpfPropTable(UnpfPropReqDTO reqDTO) throws BusinessException {
        if (reqDTO == null || reqDTO.getShopAuthId() == null || reqDTO.getShopId() == null || StringUtil.isBlank(reqDTO.getPlatType())) {
            return ;
        }
        unpfCategorySV.deleteUnpfPropTable(reqDTO);
    }

    @Override
    public void emptyUnpfPropValueTable(UnpfPropValueReqDTO reqDTO) throws BusinessException {
        if (reqDTO == null || reqDTO.getShopAuthId() == null || reqDTO.getShopId() == null || StringUtil.isBlank(reqDTO.getPlatType())) {
            return ;
        }
        unpfCategorySV.deleteUnpfPropValueTable(reqDTO);
    }


}

