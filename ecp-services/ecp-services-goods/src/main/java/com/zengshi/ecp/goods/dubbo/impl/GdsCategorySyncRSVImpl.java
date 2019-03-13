package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategorySyncRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySyncSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class GdsCategorySyncRSVImpl implements IGdsCategorySyncRSV {
    private static final String MODULE = GdsCategorySyncRSVImpl.class.getName();

    @Resource
    private IGdsCategorySyncSV gdsCategorySyncSV;

    @Override
    public void addGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException {
        LogUtil.info(MODULE, "入参信息为：" + catgSyncReqDTO.toString());
        try {
            gdsCategorySyncSV.addGdsCatgSyncInfo(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200318);
        }
    }

    @Override
    public void updateGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException {
        try {
            gdsCategorySyncSV.updateGdsCatgSyncInfo(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200319);
        }
    }

    @Override
    public void deleteGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException {
        try {
            gdsCategorySyncSV.deleteGdsCatgSyncInfo(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200320);
        }
    }

    @Override
    public void cancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException {
        try {
            gdsCategorySyncSV.cancelGdsCatgSyncInfo(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200320);
        }
    }

    @Override
    public PageResponseDTO<GdsCatgSyncRespDTO> queryGdsCatgSyncPageInfo(
            GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException {
        try {
            return gdsCategorySyncSV.queryGdsCatgSyncPageInfo(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200321);
        }
    }

    @Override
    public void batchCancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        if (catgSyncReqDTO.getIds() == null || catgSyncReqDTO.getIds().size() == 0) {
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { "ids" });

        }
        LogUtil.info(MODULE, "需要删除的ids为：" + catgSyncReqDTO.getIds().toString());
        try {
            gdsCategorySyncSV.batchCancelGdsCatgSyncInfo(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200322);
        }

    }

    @Override
    public GdsCatgSyncRespDTO queryGdsCategoryInfoByOriginCatgCode(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        try {
            return gdsCategorySyncSV.queryGdsCategoryInfoByOriginCatgCode(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200323);
        }
    }

    @Override
    public List<GdsCatgSyncRespDTO> queryRootCatgSyncBySrcSys(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        try {
            return gdsCategorySyncSV.queryRootCatgSyncBySrcSys(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200324);
        }
    }

    @Override
    public List<GdsCatgSyncRespDTO> querySubCatgSyncByParent(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        try {
            return gdsCategorySyncSV.querySubCatgSyncByParent(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200325);
        }
    }

    @Override
    public List<GdsCatgSyncRespDTO> queryCatgSyncTraceUpon(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        try {
            return gdsCategorySyncSV.queryCatgSyncTraceUpon(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200327);
        }
    }

    @Override
    public GdsCatgSyncRespDTO queryGdsCategorySyncByPK(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        try {
            return gdsCategorySyncSV.queryGdsCategorySyncByPK(catgSyncReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "",e);
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200326);
        }
    }

    @Override
    public void emptyGdsCategorySyncTable(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        if (catgSyncReqDTO == null || catgSyncReqDTO.getShopAuthId() == null || catgSyncReqDTO.getShopId() == null || StringUtil.isBlank(catgSyncReqDTO.getSrcSystem())) {
            return ;
        }
        gdsCategorySyncSV.deleteGdsCategorySyncTable(catgSyncReqDTO);
    }
}
