/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ShopShipperRSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.dubbo.impl 
 * Date:2015年9月16日下午8:46:15 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ShopShipper;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopAddrSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午8:46:15  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 * 
 * 店铺发货地址信息管理类
 */
public class ShopShipperAddrRSVImpl implements IShopShipperAddrRSV {
    
    private static final String MODULE = ShopShipperAddrRSVImpl.class.getName();

    /**
     * 店铺发货地址服务类
     */
    @Resource
    private IShopAddrSV shopAddrService;
    /** 
     * TODO 设置默认发货地址. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV#installShipperAddr(java.lang.Long) 
     */
    @Override
    public int installShipperAddr(ShopShipperReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null || reqDTO.getId() < 0)
        {
            String errorMessage = "======设置店铺默认发货信息入参不能为null======";
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{errorMessage});
        }
        try {
            shopAddrService.installShipperAddr(reqDTO.getId(), reqDTO.getShopId());
        } catch (Exception e) {
            // TODO: handle exception
            String errorMessage = "======设置店铺默认退货操作出现异常，店铺信息："+reqDTO.toString();
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{errorMessage});        
        }
        return 0;
    }

    /** 
     * TODO 设置默认退货地址信息. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV#installReturnBack(java.lang.Long) 
     */
    @Override
    public int installReturnBack(ShopShipperReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null || reqDTO.getId() < 0 || reqDTO.getShopId() < 0)
        {
            String errorMessage = "======设置店铺默认退货信息入参不能为null======";
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{errorMessage});
        }
        
        try {
            shopAddrService.installReturnAddr(reqDTO.getId(), reqDTO.getShopId());
        } catch (Exception e) {
            // TODO: handle exception
            String errorMessage = "======设置店铺默认退货操作出现异常，店铺信息："+reqDTO.toString();
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{errorMessage});        
        }
        return 0;
    }

    /** 
     * TODO 删除发货地址信息. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV#deleteShipperAddr(java.lang.Long) 
     */
    @Override
    public int deleteShipperAddr(ShopShipperReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null || reqDTO.getId() < 0)
        {
            String errorMessage = "======删除店铺发货信息入参不能为null======";
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{errorMessage});
        }
        Long id = reqDTO.getId();
        try {
            shopAddrService.delete(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{"======删除店铺发货信息出现异常======"});
        }
        return 0;
    }

    /** 
     * TODO 保存发货地址信息. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV#saveShipperAddr(com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO) 
     */
    @Override
    public int saveShipperAddr(ShopShipperReqDTO shipper) throws BusinessException {
        if(shipper == null || shipper.getShopId() <= 0)
        {
            String errorMessage = "======保存店铺发货信息入参不能为null======";
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{errorMessage});
        }
        //统计当前店铺发货地址总数
        long count = shopAddrService.count(shipper.getShopId());
        if(count > 20)
        {
            throw new BusinessException(StaffConstants.ShopShipper.SHOP_SHIPPER_NUMBER_OVER);
        }
        
        ShopShipper record = new ShopShipper();
        ObjectCopyUtil.copyObjValue(shipper, record, null, false);
        try {
            shopAddrService.save(record, count);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{"======保存店铺发货信息出现异常:"+record.toString()});
        }
        return 0;
    }

    /** 
     * TODO 查看地址信息列表. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV#listShipperAddr(com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO) 
     */
    @Override
    public PageResponseDTO<ShopShipperResDTO> listShipperAddr(ShopShipperReqDTO shipper)
            throws BusinessException {
        if(shipper == null || shipper.getShopId() == null)
        {
            String errorMessage = "======保存店铺发货信息入参不能为null======";
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{errorMessage});
        }
        return shopAddrService.list(shipper);
    }

    /** 
     * TODO 取默认发货地址信息（dubbo）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV#selectShipperAddr(com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO) 
     */
    @Override
    public ShopShipperResDTO selectShipperAddr(ShopShipperReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null || reqDTO.getShopId() <= 0)
        {
            String errorMessage = "======取默认店铺发货地址信息入参不能为null======";
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{errorMessage});
        }
        ShopShipper shipper = null;
        try {
            shipper = shopAddrService.select(reqDTO.getShopId(), StaffConstants.ShopShipper.SHOP_SHIPPER_ADDR_FLAG);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"======取默认店铺发货地址信息出现异常："+reqDTO.toString()});
        }
        if(shipper != null)
        {
            ShopShipperResDTO resDto = new ShopShipperResDTO();
            ObjectCopyUtil.copyObjValue(shipper, resDto, null, false);
            return resDto;
        }
        return null;
    }

    /** 
     * TODO 取默认退货地址信息（dubbo）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV#selectReturnAddr(com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO) 
     */
    @Override
    public ShopShipperResDTO selectReturnAddr(ShopShipperReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null || reqDTO.getShopId() <= 0)
        {
            String errorMessage = "======取默认店铺退货信息入参不能为null======";
            LogUtil.info(MODULE, errorMessage);
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{errorMessage});
        }
        ShopShipper shipper = null;
        try {
            shipper = shopAddrService.select(reqDTO.getShopId(), StaffConstants.ShopShipper.SHOP_RETURN_ADDR_FLAG);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"======取默认店铺退货地址信息出现异常："+reqDTO.toString()});
        }
        if(shipper != null)
        {
            ShopShipperResDTO resDto = new ShopShipperResDTO();
            ObjectCopyUtil.copyObjValue(shipper, resDto, null, false);
            return resDto;
        }
        return null;
    }

}

