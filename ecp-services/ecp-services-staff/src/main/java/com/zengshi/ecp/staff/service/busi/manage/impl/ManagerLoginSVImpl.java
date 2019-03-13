package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.PasswordUtils;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IManagerLoginSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 管理人员登录服务接口实现类<br>
 * Date:2015-8-11下午2:19:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ManagerLoginSVImpl implements IManagerLoginSV{
    
    @Resource
    private AuthStaffCIDXMapper mapper;
    
    @Resource
    private AuthStaffMapper authStaffMapper;
    
    @Resource
    private PasswordUtils passwordUtils;
    
    @Resource
    private IRegisterSV registerSV;

    @Override
    public AuthStaffResDTO queryStaffInfo(AuthStaffReqDTO authStaff) throws BusinessException {
        AuthStaffResDTO dto = new AuthStaffResDTO();
        
        AuthStaffCriteria creteria = new AuthStaffCriteria();
        com.zengshi.ecp.staff.dao.model.AuthStaffCriteria.Criteria sql = creteria.createCriteria();
        //用户ID
        sql.andIdEqualTo(authStaff.getId());
        //用户账号必须未超过失效时间
        sql.andInvalidTimeGreaterThan(new Timestamp(System.currentTimeMillis()));
        //生效时间早于系统时间
        sql.andStartDateLessThan(new Timestamp(System.currentTimeMillis()));
        AuthStaff result = null;
        try {
            List<AuthStaff> list = authStaffMapper.selectByExample(creteria);
            if (!StringUtil.isEmpty(list)) {
                result = list.get(0);
                ObjectCopyUtil.copyObjValue(result, dto, null, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dto;
    }

    @Override
    public AuthStaffCIDX queryStaffByCIDX(AuthStaffCIDX authStaffCIDX)
            throws BusinessException {
        AuthStaffCIDXCriteria criteria = new AuthStaffCIDXCriteria();
        Criteria sql = criteria.createCriteria();
        //用户名
        sql.andStaffCodeEqualTo(authStaffCIDX.getStaffCode());
        AuthStaffCIDX result = null;
        try {
            List<AuthStaffCIDX> resultList = mapper.selectByExample(criteria);
            if (CollectionUtils.isNotEmpty(resultList)) {
                result = resultList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public int updateAuthStaff(AuthStaffReqDTO authStaff) throws BusinessException {
        AuthStaff staff = new AuthStaff();
        ObjectCopyUtil.copyObjValue(authStaff, staff, null, true);
        return authStaffMapper.updateByPrimaryKeySelective(staff);
    }

    @Override
    public AuthStaffResDTO findAuthStaffById(Long id) throws BusinessException {
        AuthStaffResDTO dto = new AuthStaffResDTO();
        AuthStaff staff = authStaffMapper.selectByPrimaryKey(id);
        ObjectCopyUtil.copyObjValue(staff, dto, null, true);
        return dto;
    }

    @Override
    public AuthStaffResDTO managerLogin(AuthStaffReqDTO authStaffReqDTO) throws BusinessException {
        AuthStaffResDTO result = new AuthStaffResDTO();
        /*1、校验登录参数是否为空：staffCode、staffPassword*/
        if (StringUtil.isEmpty(authStaffReqDTO.getStaffCode())) {
            result.setResultCode("2");
            result.setResultInfo("用户名不能为空！");
            return result;
        } else if (StringUtil.isEmpty(authStaffReqDTO.getStaffPasswd())) {
            result.setResultCode("3");
            result.setResultInfo("密码不能为空！");
            return result;
        }
        /*2、根据staffCode去索引表中查出用户ID*/
        AuthStaffCIDX authStaffCIDX = new AuthStaffCIDX();
        //条件复制
        StaffTools.coverBean2Bean(authStaffReqDTO, authStaffCIDX);
        //调用方法查询
        AuthStaffCIDX queryResult = this.queryStaffByCIDX(authStaffCIDX);
        //查询结果为空的话，直接返回
        if (StringUtil.isEmpty(queryResult)) {
            result.setResultCode("4");
            result.setResultInfo("用户名不存在！");
            return result;
        }
        
        /*3、用户名存在的情况下：根据用户ID查询用户信息*/
        AuthStaffResDTO authStaff = new AuthStaffResDTO();
        AuthStaffReqDTO info = new AuthStaffReqDTO();
        //调用方法查询，如果找不到匹配的结果，会返回null
        authStaff = this.findAuthStaffById(queryResult.getStaffId());
        ObjectCopyUtil.copyObjValue(authStaff, info, null, true);
        if (StringUtil.isEmpty(authStaff)) {
            result.setResultCode("4");
            result.setResultInfo("用户名不存在！");
            return result;
        } 
        /*4、查询到用户信息，则校验用户信息的有效性*/
        /*4-1、验证用户账户是否为管理员帐号*/
        if (!StaffConstants.authStaff.STAFF_CLASS_M.equals(authStaff.getStaffClass())) {
            result.setResultCode("8");
            result.setResultInfo("不是管理员平台帐号，无法登录！");
            return result;
        }
        /*4-2、验证用户账户的状态是否正常*/
        //用户账户已失效
        if (StaffConstants.authStaff.STAFF_FLAG_INVALID.equals(authStaff.getStaffFlag())) {
            result.setResultCode("6");
            result.setResultInfo("帐号已失效，请联系管理员！");
            return result;
        }
        //用户帐号因多次密码错误，被临时锁定，临时锁定是否已超时
        boolean freeze_time_out = false;
        if (StaffConstants.authStaff.STAFF_FLAG_LOCK.equals(authStaff.getStaffFlag())) {
            //判断最后登录失败时间与当前时间差，如果在冻结时间之内，则方法直接返回。如果超过了时间，则方法往下走
            Long freeze_time = DateUtil.getSysDate().getTime()- authStaff.getLastLoginFailureTime().getTime();
            if (freeze_time < StaffConstants.authStaff.LOGIN_FREEZE_TIME) {
                result.setResultCode("6");
                result.setResultInfo("帐号密码错误次数过多，已被临时锁定，请联系管理员！");
                return result;
            } else {
                freeze_time_out = true;
            }
        }
        /*4-3、验证用户的账户是否在有效期内*/
        if (StringUtil.isEmpty(this.queryStaffInfo(info))) {
            result.setResultCode("6");
            result.setResultInfo("用户帐号不在有效期内！");
            return result;
        }
        /*4-4、验证密码是否正确*/
        /*4-4-1、密码不正确*/
        //前店传入的为原始密码，数据库取出来的为经过加密的密码
        if (!passwordUtils.checkPassword(authStaffReqDTO.getStaffPasswd(), authStaff.getStaffPasswd())) {
            result.setResultCode("5");
            result.setResultInfo("密码不正确！");
            //当天登录失败次数
            BigDecimal loginFailCnt = info.getLoginFailureCntToday();
            //次数加1
            loginFailCnt = loginFailCnt.add(new BigDecimal(1));
            info.setLoginFailureCntToday(loginFailCnt);
            //判断最后一次登录失败时间与当前时间差如果超过了冻结时间，则次数设为1，账户自动解锁
            if (freeze_time_out) {
                info.setLoginFailureCntToday(new BigDecimal(1));
                info.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
            }
            //更新最后登录失败时间
            info.setLastLoginFailureTime(new Timestamp(System.currentTimeMillis()));
            info.setUpdateStaff(queryResult.getStaffId());
            //如果当天登录失败次数大于最大失败限制次数，则把登录表的状态设为：2，临时锁定
            if (loginFailCnt.intValue() >= StaffConstants.authStaff.LOGIN_FAIL_MAX_CNT) {
               info.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG_LOCK); 
            }
            //另启一个线程，更新用户相关数据
            //更新用户信息表登录失败次数及最后登录失败时间
            this.updateStaffInfo(info);
            return result;
        } 
        /*4-4-2、密码正确*/
        if (passwordUtils.checkPassword(authStaffReqDTO.getStaffPasswd(), authStaff.getStaffPasswd())) {
            //当天登录失败次数置0
            info.setLoginFailureCntToday(new BigDecimal(0));
            //登录成功次数加1
            BigDecimal succCnt = info.getLoginSuccessCnt();
            info.setLoginSuccessCnt(succCnt.add(new BigDecimal(1)));
            //这里是为了把状态为4时，超过了冻结时间，登录后，把状态重置为正常。
            info.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
            //更新上次登录时间
            info.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            //更新最后操作时间
            info.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            info.setUpdateStaff(queryResult.getStaffId());
            //另启一个线程，更新用户相关数据
            this.updateStaffInfo(info);
            ObjectCopyUtil.copyObjValue(authStaff, result, null, true);
            result.setResultCode("1");
            result.setResultInfo("登录成功！");
            return result;
        } 
        return result;
    }
    /**
     * 
     * updateStaffInfo:(另启一个线程，更新用户登录相关信息). <br/> 
     * 
     * @author huangxl 
     * @param authStaff 
     * @since JDK 1.7
     */
    private void updateStaffInfo(final AuthStaffReqDTO authStaff) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                try {
                    ManagerLoginSVImpl.this.updateAuthStaff(authStaff);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int loginSuccess(Long staffId) throws BusinessException {
        //根据ID，查询出用户信息
        AuthStaffResDTO authStaff = registerSV.findAuthStaffById(staffId);
        if (authStaff != null) {
            AuthStaffReqDTO req = new AuthStaffReqDTO();
            ObjectCopyUtil.copyObjValue(authStaff, req, null, false);
            req.setUpdateTime(DateUtil.getSysDate());//最后更新时间
            req.setLastLoginTime(DateUtil.getSysDate());//最后成功登录时间
            //登录成功次数加1
            BigDecimal succCnt = req.getLoginSuccessCnt();
            if (succCnt == null) {
                succCnt = new BigDecimal(0);
            }
            succCnt = succCnt.add(new BigDecimal(1));
            req.setLoginSuccessCnt(succCnt);
            registerSV.updateAuthStaff(req);
            return 1;
        } else {
            throw new BusinessException("对不起，用户不存在！");
        }
    }
}

