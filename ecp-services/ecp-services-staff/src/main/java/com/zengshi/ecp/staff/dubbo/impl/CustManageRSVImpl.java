package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层 会员管理服务接口实现类<br>
 * Date:2015-8-12下午9:46:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class CustManageRSVImpl implements ICustManageRSV{

    @Resource
    private ICustManageSV custManageSV;
    
    private static Logger logger = LoggerFactory.getLogger(CustManageRSVImpl.class);
    
    @Override
    public Long saveCustInfo(CustInfoReqDTO custInfo) throws BusinessException {
        //入参对象为空
        if (custInfo == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        //登录账号不能为空
        if (StringUtil.isBlank(custInfo.getStaffCode())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"登录账户"});
        }
        //昵称不能为空
//        if (StringUtil.isBlank(custInfo.getNickname())) {
//            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"昵称"});
//        }
        //生日不能为空
      /*  if (StringUtil.isEmpty(custInfo.getCustBirthday())) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"出生年月"});
        }*/
        //手机号码不能为空
//        if (StringUtil.isBlank(custInfo.getSerialNumber())) {
//            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"手机号码"});
//        }
        //真实姓名不能为空
//        if (StringUtil.isBlank(custInfo.getCustName())) {
//            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"真实姓名"});
//        }
        //省份不能为空
//        if (StringUtil.isBlank(custInfo.getProvinceCode())) {
//            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"省份"});
//        }
//        //地市不能为空
//        if (StringUtil.isBlank(custInfo.getCityCode())) {
//            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"省份"});
//        }
//        //区县不能为空
//        if (StringUtil.isBlank(custInfo.getCountyCode())) {
//            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"省份"});
//        }
            
        return custManageSV.saveCustInfoForRSV(custInfo);
    }

    @Override
    public int updateCustInfo(CustInfoReqDTO custInfo) throws BusinessException {
        //入参对象为空
        if (custInfo == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        return custManageSV.updateCustInfo(custInfo);
    }

    @Override
    public int updateCustStatus(CustInfoReqDTO custInfo) throws BusinessException {
        return custManageSV.updateCustStatus(custInfo);
    }

    @Override
    public PageResponseDTO<CustInfoResDTO> listCustInfo(CustInfoReqDTO info)
            throws BusinessException {
        PageResponseDTO<CustInfoResDTO> page = new PageResponseDTO<CustInfoResDTO>();
        try {
            page = custManageSV.listCustInfo(info);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{""});
        }
        return page;
    }

    @Override
    public CustInfoResDTO findCustInfoById(Long id) throws BusinessException {
        CustInfoResDTO dto = new CustInfoResDTO();
        try {
            dto = custManageSV.findCustInfoById(id);
        } catch (Exception e) {
            logger.error("查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{""});
        }
        return dto;
    }

    @Override
    public int resetPwd(AuthStaffReqDTO req) throws BusinessException {
        try {
            custManageSV.resetPwd(req);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public CustInfoResDTO findCustInfo(CustInfoReqDTO req) throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        return custManageSV.findCustInfo(req);
    }

    @Override
    public int updatePhoneByStaffId(CustInfoReqDTO req) throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        return custManageSV.updatePhoneByStaffId(req);
    }

	@Override
	public int updateCustInfoForEmpty(CustInfoReqDTO custInfo) throws BusinessException {
		return custManageSV.updateCustInfoForEmpty(custInfo);
	}

	@Override
	public boolean checkCodeExist(String code, Long staffId, String type) throws BusinessException {
		return custManageSV.checkCodeExist(code, staffId, type);
	}

	@Override
	public int updateScust(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
		return custManageSV.updateScust(custInfoReqDTO);
	}

	@Override
	public int delScust(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
		return custManageSV.delScust(custInfoReqDTO);
	}

}

