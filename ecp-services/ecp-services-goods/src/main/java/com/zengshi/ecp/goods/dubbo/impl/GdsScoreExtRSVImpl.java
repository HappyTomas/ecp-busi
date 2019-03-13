package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsScoreExtRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsScoreExtSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class GdsScoreExtRSVImpl implements IGdsScoreExtRSV {
    private static final String MODULE = GdsScoreExtRSVImpl.class.getName();

    @Resource
    IGdsScoreExtSV gdsScoreExtSV;


    @Override
    public List<GdsScoreExtRespDTO> queryGdsScoreExtByGds(GdsScoreExtReqDTO gdsScoreExtReqDTO)
            throws BusinessException {
        try {
            if (gdsScoreExtReqDTO == null) {
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                        new String[] { "gdsScoreExtReqDTO" });
            }
            if (gdsScoreExtReqDTO.getShopId() == null || gdsScoreExtReqDTO.getShopId() == 0L) {
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                        new String[] { "shopId" });
            }

            if (gdsScoreExtReqDTO.getGdsId() == null || gdsScoreExtReqDTO.getGdsId() == 0L) {
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                        new String[] { "gdsId" });
            }

            return gdsScoreExtSV.queryGdsScoreExtByGds(gdsScoreExtReqDTO);
        } catch (BusinessException e) {
            throw e;
        } 
    }



}
