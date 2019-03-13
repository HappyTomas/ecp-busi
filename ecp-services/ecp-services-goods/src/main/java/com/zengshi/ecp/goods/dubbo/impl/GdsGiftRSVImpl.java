package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class GdsGiftRSVImpl extends AbstractRSVImpl implements IGdsGiftRSV {
    @Resource(name="gdsGiftSV")
    IGdsGiftSV iGdsGiftSV;
    
    private static String MODULE = GdsGiftRSVImpl.class.getName();
    
    /**
     * 
     * TODO 新增保存商品赠品（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#saveGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void saveGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException {
        try {
            iGdsGiftSV.saveGdsGift(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "新增赠品失败",e);
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200001);
        }
    }
    /**
     * 
     * TODO 删除商品赠品（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#delteGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void delteGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true, "reqDTO");
        paramNullCheck(reqDTO.getId(),"reqDTO.id");
        try {
            iGdsGiftSV.delteGdsGift(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "删除赠品失败",e);
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200002);
        }
    }
    /**
     * 
     * TODO 编辑商品赠品（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#editGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void editGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true, "reqDTO");
        paramNullCheck(reqDTO.getId(),"reqDTO.id");
        try {
            iGdsGiftSV.editGdsGift(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "编辑赠品失败",e);
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200003);
        }
    }
    
    /**
     * 
     * TODO 查询商品赠品列表（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#queryGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public PageResponseDTO<GdsGiftRespDTO> queryGdsGift(GdsGiftReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO,true, "reqDTO");
        paramNullCheck(reqDTO.getShopId(),true, "reqDTO.shopId");
        try {
            return iGdsGiftSV.queryGdsGift(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取赠品列表失败",e);
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200004);
        }
    }
    
    /**
     * 
     * TODO 获取单条赠品记录信息。用于编辑、查看（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#querySingleGiftInfo(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public GdsGiftRespDTO querySingleGiftInfo(GdsGiftReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true, "reqDTO");
        paramNullCheck(reqDTO.getId(),"reqDTO.id");
        try {
            return iGdsGiftSV.querySingleGiftInfo(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取单条赠品信息失败",e);
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200004);
        }
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（赠品调增、调减。即增品变化量，对外提供）. 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV#changeGiftAmount(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void changeGiftAmount(GdsGiftReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true, "reqDTO");
        try {
            iGdsGiftSV.changeGiftAmount(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "赠品数量变更失败！",e);
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200004);
        }
    }

}

