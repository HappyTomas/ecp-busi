package com.zengshi.ecp.unpf.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCategorySyncReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCategorySyncRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRespDTO;

public interface IUnpfCategorySV {

    public List<UnpfPropRespDTO> queryUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException;
    public List<UnpfPropValueRespDTO> queryUnpfPropValue(UnpfPropValueReqDTO reqDTO)throws BusinessException;

    public Long insertUnpfProp(UnpfPropReqDTO reqDTO) throws BusinessException;
    public void insertUnpfPropValue(UnpfPropValueReqDTO reqDTO) throws BusinessException;
    
    public void deleteUnpfPropTable(UnpfPropReqDTO reqDTO) throws BusinessException;
    public void deleteUnpfPropValueTable(UnpfPropValueReqDTO reqDTO) throws BusinessException;  

}

