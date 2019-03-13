package com.zengshi.ecp.unpf.dubbo.impl;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopStockLimitRSV;
import com.zengshi.ecp.unpf.service.busi.catg.interfaces.IUnpfShopStockLimitSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;

import javax.annotation.Resource;

public class UnpfShopStockLimitRSVImpl implements IUnpfShopStockLimitRSV {
    
    @Resource
    private IUnpfShopStockLimitSV shopStockLimitSV;

    @Override
    public void insertGdsLimit(UnpfGdsLimitReqDTO gdsLimitReqDTO) throws BusinessException {
        shopStockLimitSV.insertGdsLimit(gdsLimitReqDTO);
    }

    @Override
    public void insertCatgLimit(UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException {
        shopStockLimitSV.insertCatgLimit(catgLimitReqDTO);
    }

    @Override
    public void deleteGdsLimitByKey(Long key) throws BusinessException {
        shopStockLimitSV.deleteGdsLimitByKey(key);
    }

    @Override
    public void deleteCatgLimitByKey(Long key) throws BusinessException {
        shopStockLimitSV.deleteCatgLimitByKey(key);
    }

    @Override
    public PageResponseDTO<UnpfGdsLimitRespDTO> queryGdsLimitPage(UnpfGdsLimitReqDTO gdsLimitReqDTO)
            throws BusinessException {
        return shopStockLimitSV.queryGdsLimitPage(gdsLimitReqDTO);
    }

    @Override
    public PageResponseDTO<UnpfGdsCatgLimitRespDTO> queryCatgLimitPage(
            UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException {
        return shopStockLimitSV.queryCatgLimitPage(catgLimitReqDTO);
    }

    @Override
    public boolean checkLimitExits(UnpfGdsLimitReqDTO gdsLimitReqDTO) throws BusinessException {
        
        if (gdsLimitReqDTO == null) {
            return false;
        }
        PageResponseDTO<UnpfGdsLimitRespDTO>  resultPage = shopStockLimitSV.queryGdsLimitPage(gdsLimitReqDTO);
        
        if (resultPage != null && CollectionUtils.isNotEmpty(resultPage.getResult())) {
            return true;
        }
        
        return false;
    }

    @Override
    public boolean checkLimitExits(UnpfGdsCatgLimitReqDTO catgLimitReqDTO)
            throws BusinessException {
        
        if (catgLimitReqDTO == null) {
            return false;
        }
        PageResponseDTO<UnpfGdsCatgLimitRespDTO>  resultPage = shopStockLimitSV.queryCatgLimitPage(catgLimitReqDTO);
        
        if (resultPage != null && CollectionUtils.isNotEmpty(resultPage.getResult())) {
            return true;
        }
        
        return false;
    }

}

