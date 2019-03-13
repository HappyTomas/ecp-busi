package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.facade.interfaces.eventual.ICompanyMainSV;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IShopMainSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyManageSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层  企业管理服务接口实现类<br>
 * Date:2015-8-21上午11:33:11  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class CompanyManageRSVImpl implements ICompanyManageRSV{

    @Resource
    private ICompanyManageSV companyManageSV;
    
    @Resource
    private ICompanyMainSV companyTxMainSV;
    
    @Resource
    private IShopMainSV shopMainSV;
 
    private static Logger logger = LoggerFactory.getLogger(CompanyManageRSVImpl.class);

    
    @Override
    public ShopInfoResDTO saveShopInfo(ShopInfoReqDTO shopInfo) throws BusinessException {
        ShopInfoResDTO dto = new ShopInfoResDTO();
        dto = shopMainSV.buildStorck(shopInfo);
        return dto;
    }

    @Override
    public PageResponseDTO<CompanyInfoResDTO> listCompanyInfo(CompanyInfoReqDTO companyInfo)
            throws BusinessException {
        PageResponseDTO<CompanyInfoResDTO> page = new PageResponseDTO<CompanyInfoResDTO>();
        try {
            page = companyManageSV.listCompanyInfo(companyInfo);
        } catch (Exception e) {
            logger.error("查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        return page;
    }

    @Override
    public long saveCompanyInfo(CompanyInfoReqDTO companyInfo) throws BusinessException {
        companyTxMainSV.companyBuildRep(companyInfo);
        return 1;
    }

    @Override
    public int updateCompanyInfo(CompanyInfoReqDTO companyInfo) throws BusinessException {
        return companyManageSV.updateCompanyInfo(companyInfo);
    }

    @Override
    public CompanyInfoResDTO findCompanyInfoById(Long id) throws BusinessException {
        CompanyInfoResDTO dto = new CompanyInfoResDTO();
        try {
            dto = companyManageSV.findCompanyInfoById(id);
        } catch (Exception e) {
            logger.error("查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{});
        }
        return dto;
    }

    @Override
    public CompanyInfoResDTO findCompanyInfoByName(String companyName) throws BusinessException {
        CompanyInfoResDTO dto = new CompanyInfoResDTO();
        try {
            dto = companyManageSV.findCompanyInfoByName(companyName);
        } catch (Exception e) {
            logger.error("查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{"T_COMPANY_INFO"});
        }
        return dto;
    }

    @Override
    public int updateCompanyStatus(CompanyInfoReqDTO req) throws BusinessException {
        //入参对象不能为空
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        //企业id不能为空
        if (req.getId() == null || req.getId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"企业id"});
        }
        //企业状态
        if (StringUtil.isBlank(req.getStatus())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"企业状态"});
        }
        return companyManageSV.updateCompanyStatus(req);
    }
    
    
}

