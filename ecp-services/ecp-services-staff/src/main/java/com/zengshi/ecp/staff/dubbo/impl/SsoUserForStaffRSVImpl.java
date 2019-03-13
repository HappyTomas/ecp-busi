package com.zengshi.ecp.staff.dubbo.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoMsgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ISsoUserForStaffRSV;
import com.zengshi.ecp.staff.service.busi.sso.interfaces.ISsoUserImportSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层：sso登录数据同步接口<br>
 * Date:2015-10-9下午5:43:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class SsoUserForStaffRSVImpl implements ISsoUserForStaffRSV{
    
    private static final String MODULE = SsoUserForStaffRSVImpl.class.getName();
    @Resource
    private ISsoUserImportSV ssoUserImportSV;


    @Override
    public void receive(Map<String, Object> data)throws BusinessException {
        LogUtil.info(MODULE, "==============开始导入数据===============");
        SsoUserInfoReqDTO dto = new SsoUserInfoReqDTO();
        if(data!=null){
            if(null!=data.get("UserName")){
                dto.setUserName(String.valueOf(data.get("UserName")));
            }
            if(null!=data.get("Email")){
                dto.setEmail(String.valueOf(data.get("Email")));
            }
            if(null!=data.get("RealName")){
                dto.setRealName(String.valueOf(data.get("RealName")));
            }
            if(null!=data.get("Mobile")){
                dto.setMobile(String.valueOf(data.get("Mobile")));
            }
            if(null!=data.get("OrgID")){
                dto.setOrgID(String.valueOf(data.get("OrgID")));
            }
            if(null!=data.get("OrgName")){
                dto.setOrgName(String.valueOf(data.get("OrgName")));
            }
            if(null!=data.get("OrgUserType")){
                dto.setOrgUserType(String.valueOf(data.get("OrgUserType")));
            }
        SsoUserInfoMsgResDTO message =  ssoUserImportSV.saveStaffInfo(dto);
        LogUtil.info(MODULE, "================新增"+message.getStaffCode()+"成功====================");
        }else{
            LogUtil.error(MODULE, "=========传入数据为空=========");
        }
    }

   
}

