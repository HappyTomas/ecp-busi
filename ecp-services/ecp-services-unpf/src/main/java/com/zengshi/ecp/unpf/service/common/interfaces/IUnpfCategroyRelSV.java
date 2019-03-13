package com.zengshi.ecp.unpf.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaRespDTO;

public interface IUnpfCategroyRelSV {

    public void insertCatgRela(UnpfCatgRelaReqDTO relaReqDTO)throws BusinessException;
    public void insertPropRela(UnpfPropRelaReqDTO propRelReqDTO)throws BusinessException;
    public void insertPropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)throws BusinessException;
    
    public void deleteCatgRela(UnpfCatgRelaReqDTO relaReqDTO)throws BusinessException;
    public void deletePropRela(UnpfPropRelaReqDTO propRelReqDTO)throws BusinessException;
    public void deletePropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)throws BusinessException;
    
    public void deleteCatgRelaByKey(Long key)throws BusinessException;
    public void deletePropRelaByKey(Long key)throws BusinessException;
    public void deletePropValueRelaByKey(Long key)throws BusinessException; 
    
    public List<UnpfCatgRelaRespDTO> selectCatgRelaList(UnpfCatgRelaReqDTO relaReqDTO)throws BusinessException;
    public List<UnpfPropRelaRespDTO> selectPropRelaList(UnpfPropRelaReqDTO propRelReqDTO)throws BusinessException;
    public List<UnpfPropValueRelaRespDTO> selectPropValueRelaList(UnpfPropValueRelaReqDTO propValueRelaReqDTO)throws BusinessException; 
    
}

