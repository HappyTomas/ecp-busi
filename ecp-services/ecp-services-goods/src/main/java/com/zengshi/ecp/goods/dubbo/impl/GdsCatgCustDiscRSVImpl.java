package com.zengshi.ecp.goods.dubbo.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.CalCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscBatchDelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscListReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustDiscRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustDiscSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class GdsCatgCustDiscRSVImpl extends AbstractRSVImpl implements IGdsCatgCustDiscRSV {
    @Resource(name = "gdsCatgCustDiscSV")
    IGdsCatgCustDiscSV gdsCatgCustDiscSV;

    @Override
    public void addGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO) throws BusinessException {
        try {
            gdsCatgCustDiscSV.addGdsCatgCustDisc(catgCustDiscListReqDTO);
            //发刷折扣价商品索引消息
            GdsUtils.sendGdsIndexMsg(null, catgCustDiscListReqDTO.getCatgCustDiscReqDTOs().get(0).getCatgCode(), MODULE);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsCatgCustDisc.ERROR_GOODS_CATG_CUST_DISC_240305);
        }
    }

    @Override
    public void editGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO) throws BusinessException {
        try {
            gdsCatgCustDiscSV.editGdsCatgCustDisc(catgCustDiscListReqDTO);
            //发刷折扣价商品索引消息
            GdsUtils.sendGdsIndexMsg(null, catgCustDiscListReqDTO.getCatgCustDiscReqDTOs().get(0).getCatgCode(), MODULE);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsCatgCustDisc.ERROR_GOODS_CATG_CUST_DISC_240303);
        }
    }

    @Override
    public void deleteGdsCatgCustDisc(GdsCatgCustDiscReqDTO catgCustDiscReqDTO) throws BusinessException {
        paramNullCheck(catgCustDiscReqDTO, "reqDTO");
        paramNullCheck(catgCustDiscReqDTO.getCatgCode(), "CatgCode");
        paramNullCheck(catgCustDiscReqDTO.getShopId(), "shopId");
        paramNullCheck(catgCustDiscReqDTO.getCustLevelCode(), "CustLevelCode");
        try {
            gdsCatgCustDiscSV.deleteGdsCatgCustDisc(catgCustDiscReqDTO);
            //发刷折扣价商品索引消息
            GdsUtils.sendGdsIndexMsg(null, catgCustDiscReqDTO.getCatgCode(), MODULE);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsCatgCustDisc.ERROR_GOODS_CATG_CUST_DISC_240302);
        }
    }

    @Override
    public PageResponseDTO<GdsCatgCustDiscRespDTO> queryGdsCatgCustDiscByPage(GdsCatgCustDiscReqDTO catgCustDiscReqDTO) throws BusinessException {
        paramNullCheck(catgCustDiscReqDTO, "reqDTO");
        paramNullCheck(catgCustDiscReqDTO.getShopId(), "shopId");
        try {
            return gdsCatgCustDiscSV.queryGdsCatgCustDiscByPage(catgCustDiscReqDTO);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException(GdsErrorConstants.GdsCatgCustDisc.ERROR_GOODS_CATG_CUST_DISC_240304);
        }
    }

    @Override
    public BigDecimal calCatgCustDisc(CalCatgCustDiscReqDTO calCatgCustDiscReqDTO) throws BusinessException {
        paramNullCheck(calCatgCustDiscReqDTO, "reqDTO");
        paramNullCheck(calCatgCustDiscReqDTO.getGdsId(), "gdsId");
        try {
            return gdsCatgCustDiscSV.calCatgCustDisc(calCatgCustDiscReqDTO);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException();
        }
    }

    @Override
    public void deleteGdsCatgCustDiscByGroup(GdsCatgCustDiscBatchDelReqDTO batchDelReqDTO) throws Exception {
        try {
            gdsCatgCustDiscSV.deleteGdsCatgCustDiscByGroup(batchDelReqDTO);
            if(batchDelReqDTO.getCatgCustDiscReqDTOs() != null){
                for (GdsCatgCustDiscReqDTO catgCustDiscReqDTO : batchDelReqDTO.getCatgCustDiscReqDTOs()) {
                    //发刷折扣价商品索引消息
                    GdsUtils.sendGdsIndexMsg(null, catgCustDiscReqDTO.getCatgCode(), MODULE);
                }
            }
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "", e);
            throw new BusinessException();
        }
    }
}
