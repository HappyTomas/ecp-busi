/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ShopAddrSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.impl 
 * Date:2015年9月16日下午7:59:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.staff.service.busi.sso.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffEIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffEIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDX;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoMsgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SsoUserInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.sso.interfaces.ISsoUserImportSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午7:59:56 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version
 * @since JDK 1.6
 */
public class SsoUserImportSVImpl extends GeneralSQLSVImpl implements ISsoUserImportSV {

    private static final String MODULE = SsoUserImportSVImpl.class.getName();

    @Resource
    private AuthStaffCIDXMapper authStaffCIDXMapper;

    @Resource
    private AuthStaffMapper authStaffMapper;

    @Resource
    private CustInfoMapper custInfoMapper;

    @Resource(name = "seq_staff_info_id")
    private PaasSequence seqStaffInfo; // 用户sequence

    @Resource
    private IAuthRelManageSV authRelManageSV;
    
    @Resource
    private AuthStaffMIDXMapper authStaffMIDXMapper;
    
    @Resource
    private AuthStaffEIDXMapper authStaffEIDXMapper;


    @Override
    public SsoUserInfoMsgResDTO saveStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException {
        Timestamp sysTime = DateUtil.getSysDate();
        // 消息dto
        SsoUserInfoMsgResDTO message = new SsoUserInfoMsgResDTO();
        Long staffid = seqStaffInfo.nextValue();
        // 判断是否已经存在
        List<AuthStaffCIDX> list = new ArrayList<AuthStaffCIDX>();
        AuthStaffCIDXCriteria c = new AuthStaffCIDXCriteria();
        c.createCriteria().andStaffCodeEqualTo(dto.getUserName());
        try {
             list = authStaffCIDXMapper.selectByExample(c);  
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询账号索引表异常，username或为空", e);
            throw new BusinessException("查询账号索引表异常，username或为空");
        }
    
        if (!CollectionUtils.isEmpty(list)) {
            message.setFlag(false);
            message.setMessage("用户数据已存在");
            message.setStaffCode(list.get(0).getStaffCode());
            return message;
        }
        // 插入账号与用户的索引表
        AuthStaffCIDX authc = new AuthStaffCIDX();
        authc.setStaffCode(dto.getUserName());
        authc.setStaffId(staffid);
        if(StringUtil.isNotBlank(dto.getMobile())){
        authc.setSerialNumber(Long.parseLong(dto.getMobile()));
        }
        try {
            authStaffCIDXMapper.insertSelective(authc);
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入账号索引表失败", e);
            throw new BusinessException("插入账号索引表失败");
        }
        // 插入auth登录表
        AuthStaff authStaff = new AuthStaff();
        authStaff.setId(staffid);
        authStaff.setStaffCode(dto.getUserName());
        authStaff.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
        authStaff.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
        authStaff.setStartDate(sysTime);
        authStaff.setCreateFrom(StaffConstants.authStaff.CREATE_FROM_MANAGER);
        authStaff.setCreateStaff(staffid);
        authStaff.setUpdateStaff(staffid);
        authStaff.setCreateTime(sysTime);
        authStaff.setUpdateTime(sysTime);
        try {
            authStaffMapper.insertSelective(authStaff);
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入authstaff表异常", e);
            throw new BusinessException("插入authstaff表异常");
        }
        if(StringUtil.isNotBlank(dto.getMobile())){
            AuthStaffMIDX m = new AuthStaffMIDX();
            m.setSerialNumber(dto.getMobile());
            m.setStaffCode(dto.getUserName());
            m.setStaffId(staffid);
            authStaffMIDXMapper.insertSelective(m);
        }
        if(StringUtil.isNotBlank(dto.getEmail())){
            AuthStaffEIDX e = new AuthStaffEIDX();
            e.setEmail(dto.getEmail());
            e.setStaffCode(dto.getUserName());
            e.setStaffId(staffid);
            authStaffEIDXMapper.insertSelective(e);
        }
        
        // 插入会员信息表
        CustInfo custInfo = new CustInfo();
        custInfo.setId(staffid);
        custInfo.setStaffCode(dto.getUserName());
        if(StringUtil.isNotBlank(dto.getRealName())){
        custInfo.setCustName(dto.getRealName());
        }
        if(StringUtil.isNotBlank(dto.getEmail())){
        custInfo.setEmail(dto.getEmail());
        }
        custInfo.setCustLevelCode("01");
        custInfo.setCustGrowValue(new Long(0));
        if(StringUtil.isNotBlank(dto.getMobile())){
        custInfo.setSerialNumber(dto.getMobile());
        }
        custInfo.setStatus(StaffConstants.custInfo.CUST_STATUS_NORMAL);
        custInfo.setSysType(StaffConstants.custInfo.CUST_SYS_TYPE_ZY);
        custInfo.setNickname(dto.getUserName());
        if (StringUtil.isNotBlank(dto.getOrgUserType())) {
            if ("Admin".equalsIgnoreCase(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_ADMIN);
            } else if ("Public".equalsIgnoreCase(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_PUBLIC);
            } else if ("Common".equals(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_COMMON);
            }
        } else {
            custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
        }
        if (StringUtil.isNotBlank(dto.getOrgID())) {
            custInfo.setOrgId(dto.getOrgID());
        }
        if (StringUtil.isNotBlank(dto.getOrgName())) {
            custInfo.setOrgName(dto.getOrgName());
        }
        custInfo.setCreateStaff(staffid);
        custInfo.setCreateTime(sysTime);
        try {
            custInfoMapper.insertSelective(custInfo);
            StaffTools.logInfo("saveCustInfoLog", custInfo);
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入会员信息表异常", e);
            throw new BusinessException("插入authstaff表异常");
        }
        // 插入权限表，会员分组，普通会员为：10001
        AuthStaff2Group group = new AuthStaff2Group();
        group.setStaffId(staffid);// 员工ID
        group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_GENERAL);// 会员分组，普通会员为：10001
        group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);// 普通会员
        group.setCreateStaff(staffid);
        group.setUpdateStaff(staffid);
        try {
            authRelManageSV.saveStaffGroupRel(group);
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入用户分组表异常", e);
            throw new BusinessException("插入用户分组表异常");
        }
        message.setFlag(true);
        message.setMessage("sso数据同步成功");
        message.setStaffCode(dto.getUserName());
        return message;
    }

    @Override
    public SsoUserInfoMsgResDTO updateStaffInfo(SsoUserInfoReqDTO dto) throws BusinessException {
        Timestamp sysTime = DateUtil.getSysDate();
        SsoUserInfoMsgResDTO message = new SsoUserInfoMsgResDTO();
        AuthStaffCIDXCriteria authstaffc = new AuthStaffCIDXCriteria();
        authstaffc.createCriteria().andStaffCodeEqualTo(dto.getUserName());
        List<AuthStaffCIDX> authList = authStaffCIDXMapper.selectByExample(authstaffc);
        if (CollectionUtils.isEmpty(authList) || authList.size() > 1) {
            message.setFlag(false);
            message.setMessage(dto.getUserName()+"不存在或者拥有两条记录集，无法修改用户信息");
            message.setStaffCode(dto.getUserName());
            CacheUtil.delItem("SSO_USER_"+dto.getUserName());
            return message;
        }

        Long staffId = authList.get(0).getStaffId();
        CustInfo custInfo = new CustInfo();
        custInfo.setId(staffId);
        if (StringUtil.isNotBlank(dto.getRealName())) {
            custInfo.setCustName(dto.getRealName());
        }
        if (StringUtil.isNotBlank(dto.getEmail())) {
            custInfo.setEmail(dto.getEmail());
        }
        if (StringUtil.isNotBlank(dto.getMobile())) {
            custInfo.setSerialNumber(dto.getMobile());
        }
        if (StringUtil.isNotBlank(dto.getOrgID())) {
            custInfo.setOrgId(dto.getOrgID());
        }
        if (StringUtil.isNotBlank(dto.getOrgName())) {
            custInfo.setOrgName(dto.getOrgName());
        }
        if (StringUtil.isNotBlank(dto.getOrgUserType())) {

            if ("Admin".equalsIgnoreCase(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_ADMIN);
            } else if ("Public".equalsIgnoreCase(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_PUBLIC);
            } else if ("Common".equals(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_COMMON);
            }

        }
        custInfo.setUpdateStaff(staffId);
        custInfo.setUpdateTime(sysTime);
        int i = 0;
        try {
          i =  custInfoMapper.updateByPrimaryKeySelective(custInfo);
            
        } catch (Exception e) {
           LogUtil.error(MODULE, "修改用户信息异常", e);
           throw new BusinessException("修改用户信息异常");
        }
        if(i>0){
         CustInfo info =  custInfoMapper.selectByPrimaryKey(custInfo.getId());
         StaffTools.logInfo("saveCustInfoLog", info);
        }
        message.setFlag(true);
        message.setMessage("sso"+dto.getUserName()+"用户信息修改成功");
        message.setStaffCode(dto.getUserName());
        CacheUtil.delItem("SSO_USER_"+dto.getUserName());
        return message;
    }

    @Override
    public SsoUserInfoMsgResDTO updateAndSaveStaffInfo(SsoUserInfoReqDTO dto)
            throws BusinessException {
        Timestamp sysTime = DateUtil.getSysDate();
        CacheUtil.addItem("SSO_USER_"+dto.getUserName(), dto);
        // 消息dto
        SsoUserInfoMsgResDTO message = new SsoUserInfoMsgResDTO();
        // 判断是否已经存在
        AuthStaffCIDXCriteria c = new AuthStaffCIDXCriteria();
        c.createCriteria().andStaffCodeEqualTo(dto.getUserName().trim());
        List<AuthStaffCIDX> list = new ArrayList<AuthStaffCIDX>();
        try {
            list = authStaffCIDXMapper.selectByExample(c); 
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询账号索引表异常。UserName或为空", e);
            throw new BusinessException("查询账号索引表异常。UserName或为空");
        }
        //不为空跳转修改
        if (!CollectionUtils.isEmpty(list)) {
            SsoUserInfoMsgResDTO messages =  this.updateStaffInfo(dto);
            return messages;
        }
        Long staffid = seqStaffInfo.nextValue();
        // 插入账号与用户的索引表
        AuthStaffCIDX authc = new AuthStaffCIDX();
        authc.setStaffCode(dto.getUserName());
        authc.setStaffId(staffid);
        if(StringUtil.isNotBlank(dto.getMobile())){
        authc.setSerialNumber(Long.parseLong(dto.getMobile()));
        }
        try {
            authStaffCIDXMapper.insertSelective(authc);
        } catch (Exception e) {
            list = authStaffCIDXMapper.selectByExample(c); 
            //不为空跳转修改
            if (!CollectionUtils.isEmpty(list)) {
                SsoUserInfoMsgResDTO messages =  this.updateStaffInfo(dto);
                return messages;
            }else{
                LogUtil.error(MODULE, "新增工号索引报错", e);
            }
        }
        // 插入auth登录表
        AuthStaff authStaff = new AuthStaff();
        authStaff.setId(staffid);
        authStaff.setStaffCode(dto.getUserName());
        authStaff.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
        authStaff.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
        authStaff.setStartDate(sysTime);
        authStaff.setCreateFrom(StaffConstants.authStaff.CREATE_FROM_MANAGER);
        authStaff.setCreateStaff(staffid);
        authStaff.setUpdateStaff(staffid);
        authStaff.setCreateTime(sysTime);
        authStaff.setUpdateTime(sysTime);
        try {
            authStaffMapper.insertSelective(authStaff);
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入authstaff表异常", e);
            throw new BusinessException("插入authstaff表异常");
        }
        if(StringUtil.isNotBlank(dto.getMobile())){
            AuthStaffMIDX m = new AuthStaffMIDX();
            m.setSerialNumber(dto.getMobile());
            m.setStaffCode(dto.getUserName());
            m.setStaffId(staffid);
            authStaffMIDXMapper.insertSelective(m);
        }
        if(StringUtil.isNotBlank(dto.getEmail())){
            AuthStaffEIDX e = new AuthStaffEIDX();
            e.setEmail(dto.getEmail());
            e.setStaffCode(dto.getUserName());
            e.setStaffId(staffid);
            authStaffEIDXMapper.insertSelective(e);
        }
        
        // 插入会员信息表
        CustInfo custInfo = new CustInfo();
        custInfo.setId(staffid);
        custInfo.setStaffCode(dto.getUserName());
        if(StringUtil.isNotBlank(dto.getRealName())){
        custInfo.setCustName(dto.getRealName());
        }
        if(StringUtil.isNotBlank(dto.getEmail())){
        custInfo.setEmail(dto.getEmail());
        }
        custInfo.setCustLevelCode("01");
        custInfo.setCustGrowValue(new Long(0));
        if(StringUtil.isNotBlank(dto.getMobile())){
        custInfo.setSerialNumber(dto.getMobile());
        }
        custInfo.setStatus(StaffConstants.custInfo.CUST_STATUS_NORMAL);
        custInfo.setSysType(StaffConstants.custInfo.CUST_SYS_TYPE_ZY);
        custInfo.setNickname(dto.getUserName());
        if (StringUtil.isNotBlank(dto.getOrgUserType())) {
            if ("Admin".equalsIgnoreCase(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_ADMIN);
            } else if ("Public".equalsIgnoreCase(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_PUBLIC);
            } else if ("Common".equals(dto.getOrgUserType())) {
                custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_COMMON);
            }
        } else {
            custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
        }
        if (StringUtil.isNotBlank(dto.getOrgID())) {
            custInfo.setOrgId(dto.getOrgID());
        }
        if (StringUtil.isNotBlank(dto.getOrgName())) {
            custInfo.setOrgName(dto.getOrgName());
        }
        custInfo.setCreateStaff(staffid);
        custInfo.setCreateTime(sysTime);
        try {
            custInfoMapper.insertSelective(custInfo);
            StaffTools.logInfo("saveCustInfoLog", custInfo);
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入会员信息表异常", e);
            throw new BusinessException("插入authstaff表异常");
        }
        // 插入权限表，会员分组，普通会员为：10001
        AuthStaff2Group group = new AuthStaff2Group();
        group.setStaffId(staffid);// 员工ID
        group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_GENERAL);// 会员分组，普通会员为：10001
        group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);// 普通会员
        group.setCreateStaff(staffid);
        group.setUpdateStaff(staffid);
        try {
            authRelManageSV.saveStaffGroupRel(group);
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入用户分组表异常", e);
            throw new BusinessException("插入用户分组表异常");
        }
        message.setFlag(true);
        message.setMessage("sso数据同步成功");
        message.setStaffCode(dto.getUserName());
        return message;
        
        
        
    }

}
