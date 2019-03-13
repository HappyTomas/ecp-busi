package com.zengshi.ecp.unpf.service.busi.catg.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitRespDTO;

public interface IUnpfShopStockLimitSV {

    public void insertGdsLimit(UnpfGdsLimitReqDTO gdsLimitReqDTO) throws BusinessException;
    public void insertCatgLimit(UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException;
    
    public void deleteGdsLimitByKey(Long key) throws BusinessException;
    public void deleteCatgLimitByKey(Long key) throws BusinessException; 
    
    public PageResponseDTO<UnpfGdsLimitRespDTO> queryGdsLimitPage(UnpfGdsLimitReqDTO gdsLimitReqDTO) throws BusinessException;
    public PageResponseDTO<UnpfGdsCatgLimitRespDTO> queryCatgLimitPage(UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException;
}

