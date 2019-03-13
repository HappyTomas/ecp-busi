/** 
 * Project Name:ecp-services-staff 
 * File Name:McUserRSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.dubbo.impl 
 * Date:2016年3月18日上午10:08:29 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.general.sys.mc.dto.McUserInfoReqDTO;
import com.zengshi.ecp.general.sys.mc.interfaces.IMcUserRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdmin;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAdminManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAuthStaffManageSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2016年3月18日上午10:08:29  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class McUserRSVImpl implements IMcUserRSV {
    
    @Resource
    private IAuthStaffManageSV authStaffManageSV;
    
    @Resource
    private ICustInfoSV custInfoSV;
    
    @Resource
    private IAdminManageSV adminManageSV;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.general.sys.mc.interfaces.IMcUserRSV#fetchUserInfoByUserId(com.zengshi.ecp.general.sys.mc.dto.McUserInfoReqDTO) 
     */
    @Override
    public McUserInfo fetchUserInfoByUserId(McUserInfoReqDTO userInfoReq) throws BusinessException {
        if(userInfoReq == null || userInfoReq.getUserId() < 1 ){
            return null;
        }
        
        //获取用户信息；
        AuthStaffResDTO staffDto =  authStaffManageSV.findAuthStaffById(userInfoReq.getUserId());
        if(staffDto == null){
            return null;
        }
        McUserInfo userInfo = null;
       
        //普通会员
        if(StaffConstants.authStaff.STAFF_CLASS_N.equalsIgnoreCase(staffDto.getStaffClass())){
            
            userInfo = this.buildCustUser(userInfoReq.getUserId());
            
        } else {
            //管理平台
            userInfo = this.buildAdminUser(userInfoReq.getUserId());
        }
        
        return userInfo;
    }
    
    /**
     * 
     * buildCustUser: 根据会员信息构建UserInfo <br/> 
     * 
     * @author yugn 
     * @param staffId
     * @return 
     * @since JDK 1.6
     */
    private McUserInfo buildCustUser(Long staffId){
        McUserInfo info = new McUserInfo();
        info.setUserId(staffId);
        
        CustInfo custInfo =   custInfoSV.findCustInfoById(staffId);
        if(custInfo == null){
            
        } else {
            info.setEmail(custInfo.getEmail());
            info.setPhoneNo(custInfo.getSerialNumber());
        }
        
        return info;
    }
    
    /**
     * 
     * buildAdminUser:管理平台账号构建 UserInfo <br/> 
     * 
     * @author yugn 
     * @param staffId
     * @return 
     * @since JDK 1.6
     */
    private McUserInfo buildAdminUser(Long staffId){
        McUserInfo info = new McUserInfo();
        info.setUserId(staffId);
        
        AuthStaffAdmin authStaff = adminManageSV.findAuthStaffAdminById(staffId);
        if(authStaff == null){
            
        } else {
            info.setEmail(authStaff.getStaffEmail());
            info.setPhoneNo(authStaff.getSerialNumber());
        }
        return info;
    }

}

