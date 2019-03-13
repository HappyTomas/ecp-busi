package com.zengshi.ecp.unpf.dubbo.interfaces;

import java.util.List;

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

public interface IUnpfCategoryRSV {
    public List<UnpfPropRespDTO> queryUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException;
    public List<UnpfPropValueRespDTO> queryUnpfPropValue(UnpfPropValueReqDTO reqDTO)throws BusinessException;

    public void insertCatgRela(UnpfCatgRelaReqDTO relaReqDTO)throws BusinessException;
    public void insertPropRela(UnpfPropRelaReqDTO propRelReqDTO)throws BusinessException;
    public void insertPropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)throws BusinessException;
    
    public void deleteCatgRelaByKey(Long key)throws BusinessException;
    public void deletePropRelaByKey(Long key)throws BusinessException;
    public void deletePropValueRelaByKey(Long key)throws BusinessException; 
    
    public List<UnpfCatgRelaRespDTO> selectCatgRelaList(UnpfCatgRelaReqDTO relaReqDTO)throws BusinessException;
    public List<UnpfPropRelaRespDTO> selectPropRelaList(UnpfPropRelaReqDTO propRelReqDTO)throws BusinessException;
    public List<UnpfPropValueRelaRespDTO> selectPropValueRelaList(UnpfPropValueRelaReqDTO propValueRelaReqDTO)throws BusinessException; 

    public UnpfCatgRelaRespDTO checkCatgRela(UnpfCatgRelaReqDTO relaReqDTO)throws BusinessException;
    public UnpfPropRelaRespDTO checkPropRela(UnpfPropRelaReqDTO propRelReqDTO)throws BusinessException;
    public UnpfPropValueRelaRespDTO checkPropValueRela(UnpfPropValueRelaReqDTO propValueRelaReqDTO)throws BusinessException;
       
    
    public void insertUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException;
    public void insertUnpfPropValue(UnpfPropValueReqDTO reqDTO) throws BusinessException;
    
    public void emptyUnpfPropTable(UnpfPropReqDTO reqDTO) throws BusinessException;
    public void emptyUnpfPropValueTable(UnpfPropValueReqDTO reqDTO) throws BusinessException;  
    
}

