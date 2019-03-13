package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustThirdCodeRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustThirdSV;
import com.zengshi.paas.utils.StringUtil;


public class CustThirdCodeRSVImpl implements ICustThirdCodeRSV{

  
    @Resource
    private ICustThirdSV custThirdSV;
    
    private static Logger logger = LoggerFactory.getLogger(CustThirdCodeRSVImpl.class);
    
    private static final String MODULE = CustThirdCodeRSVImpl.class.getName();

    @Override
    public void saveCustThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO) throws BusinessException {
        
        if(custThirdCodeReqDTO.getStaffId()==null||custThirdCodeReqDTO.getStaffId()==0){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"staffId"});
        }
        if(StringUtil.isBlank(custThirdCodeReqDTO.getThirdCode())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"thirdCode"});
        }
        custThirdSV.saveCustThirdCode(custThirdCodeReqDTO);
    }

    @Override
    public CustThirdCodeResDTO queryThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO)
            throws BusinessException {
        
        return  custThirdSV.queryCustThirdCode(custThirdCodeReqDTO);
    }
    
  

}

