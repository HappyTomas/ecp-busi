package com.zengshi.ecp.unpf.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopLimitRSV;
import com.zengshi.ecp.unpf.service.busi.catg.interfaces.IUnpfShopLimitSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class UnpfShopLimitRSVImpl implements IUnpfShopLimitRSV {
    
    @Resource
    private IUnpfShopLimitSV shopLimitSV;

    @Override
    public void insertGdsLimit(UnpfGdsLimitReqDTO gdsLimitReqDTO) throws BusinessException {
        shopLimitSV.insertGdsLimit(gdsLimitReqDTO);
    }

    @Override
    public void insertCatgLimit(UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException {
        shopLimitSV.insertCatgLimit(catgLimitReqDTO);
    }

    @Override
    public void deleteGdsLimitByKey(Long key) throws BusinessException {
        shopLimitSV.deleteGdsLimitByKey(key);
    }

    @Override
    public void deleteCatgLimitByKey(Long key) throws BusinessException {
        shopLimitSV.deleteCatgLimitByKey(key);
    }

    @Override
    public PageResponseDTO<UnpfGdsLimitRespDTO> queryGdsLimitPage(UnpfGdsLimitReqDTO gdsLimitReqDTO)
            throws BusinessException {
        return shopLimitSV.queryGdsLimitPage(gdsLimitReqDTO);
    }

    @Override
    public PageResponseDTO<UnpfGdsCatgLimitRespDTO> queryCatgLimitPage(
            UnpfGdsCatgLimitReqDTO catgLimitReqDTO) throws BusinessException {
        return shopLimitSV.queryCatgLimitPage(catgLimitReqDTO);
    }

    @Override
    public boolean checkLimitExits(UnpfGdsLimitReqDTO gdsLimitReqDTO) throws BusinessException {
        
        if (gdsLimitReqDTO == null) {
            return false;
        }
        PageResponseDTO<UnpfGdsLimitRespDTO>  resultPage = shopLimitSV.queryGdsLimitPage(gdsLimitReqDTO);
        
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
        PageResponseDTO<UnpfGdsCatgLimitRespDTO>  resultPage = shopLimitSV.queryCatgLimitPage(catgLimitReqDTO);
        
        if (resultPage != null && CollectionUtils.isNotEmpty(resultPage.getResult())) {
            return true;
        }
        
        return false;
    }

}

