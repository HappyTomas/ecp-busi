package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustSubInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopSubAuthStaffRSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopSubAuthStaffSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 店铺子帐号rsv 实现类<br>
 * Date:2016-4-13下午11:46:36  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ShopSubAuthStaffRSVImpl implements IShopSubAuthStaffRSV {
    
    @Resource
    private IShopSubAuthStaffSV shopSubAuthStaffSV;

    @Override
    public long saveShopSubAuthStaff(CustInfoReqDTO custReqDto, CustSubInfoReqDTO custSubReqDto)
            throws BusinessException {
        return shopSubAuthStaffSV.saveShopSubAuthStaff(custReqDto, custSubReqDto);
    }

    @Override
    public void deleteShopSubAuthStaff(Long staffId) throws BusinessException {
        this.shopSubAuthStaffSV.deleteShopSubAuthStaff(staffId);
    }

    @Override
    public void disableShopSubAuthStaff(Long staffId) throws BusinessException {
        this.shopSubAuthStaffSV.disableShopSubAuthStaff(staffId);
    }

    @Override
    public void enableShopSubAuthStaff(Long staffId) throws BusinessException {
        this.shopSubAuthStaffSV.enableShopSubAuthStaff(staffId);
    }

    @Override
    public PageResponseDTO<CustInfoResDTO> listShopSubAuthStaff(CustInfoReqDTO custReqDto,
            CustSubInfoReqDTO custSubReqDto) throws BusinessException {
        return this.shopSubAuthStaffSV.listShopSubAuthStaff(custReqDto, custSubReqDto);
    }

    @Override
    public int updateSubAcctRole(CustSubInfoReqDTO req) throws BusinessException {
        return this.shopSubAuthStaffSV.updateSubAcctRole(req);
    }

    @Override
    public long countShopSubAuthStaff(Long shopId) throws BusinessException {
        return this.shopSubAuthStaffSV.countShopSubAuthStaff(shopId);
    }

}

