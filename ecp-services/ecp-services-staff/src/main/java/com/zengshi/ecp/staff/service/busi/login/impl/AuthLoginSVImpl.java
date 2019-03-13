package com.zengshi.ecp.staff.service.busi.login.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.PasswordUtils;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffEIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffEIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffEIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.login.interfaces.IAuthLoginSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustCheckSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月14日上午10:45:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 平台用户登陆实现类
 */
public class AuthLoginSVImpl implements IAuthLoginSV {

    @Resource
    private AuthStaffMapper authStaffMapper;
    @Resource
    private CustInfoMapper custInfoMapper;
    @Resource
    private PasswordUtils passwordUtils;
    
    @Resource
    private ICustManageSV custManageSV;
    
    @Resource
    private ICustCheckSV custCheckSV;

    private static final String MODULE = AuthLoginSVImpl.class.getName();

    /**
     * 登陆账号与用户表吗索引表
     */
    @Resource
    private AuthStaffCIDXMapper cidxMapper;
    /**
     * 邮箱登陆索引表操作
     */
    @Resource
    private AuthStaffEIDXMapper eidxMapper;
    /**
     * 手机号登陆索引表操作
     */
    @Resource
    private AuthStaffMIDXMapper midxMapper;
    
    /**
     * 用户状态被锁定时长
     */
    private final int AUTH_LOCK_HOURS = 3;
    /**
     * 用户登陆状态正常
     */
    private final String AUTH_STATUS_NOMAL = "1";
    /**
     * 用户登陆状态人工锁定
     */
    private final String AUTH_STATUS_LOCK = "2";
    /**
     * 用户登陆失败过多被锁定
     */
    private final String AUTH_PASSWORD_LOCK = "4";
    /**
     * 登陆失败5次>4以上，账号将被锁定
     */
    private final BigDecimal LOGIN_ERROR_LOCK_COUNT = new BigDecimal(4);
    
    @Override
    public Long findIDByStaffCode(String sStaffCode) {
        
        AuthStaffCIDXCriteria criteria = new AuthStaffCIDXCriteria();
        criteria.createCriteria().andStaffCodeEqualTo(sStaffCode);
        List<AuthStaffCIDX> result = null;
        try {
            result = cidxMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======账号：["+sStaffCode+"]查询会员ID出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{sStaffCode});
           }
        return CollectionUtils.isEmpty(result)?null:result.get(0).getStaffId();
    }

    @Override
    public Long findIDByeEmail(String eEmail) {
        AuthStaffEIDXCriteria criteria = new AuthStaffEIDXCriteria();
        criteria.createCriteria().andEmailEqualTo(eEmail);
        
        List<AuthStaffEIDX> result = null;
        try {
            result = eidxMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======邮箱：["+eEmail+"]查询会员ID出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{eEmail});
        }
        
        return CollectionUtils.isEmpty(result)?null:result.get(0).getStaffId();
    }

    @Override
    public Long findIDByPhoneNumber(String sPhoneNumber) {
        AuthStaffMIDXCriteria criteria = new AuthStaffMIDXCriteria();
        criteria.createCriteria().andSerialNumberEqualTo(sPhoneNumber);
        
        List<AuthStaffMIDX> result = null;
        try {
            result = midxMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======手机号：["+sPhoneNumber+"]查询会员ID出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{sPhoneNumber});
         }
        
        return CollectionUtils.isEmpty(result)?null:result.get(0).getStaffId();
    }

    @Override
    public String checkLogin(Long sStaffID, String password) {
        
        AuthStaff authStaff = null;
        try {
            authStaff = authStaffMapper.selectByPrimaryKey(sStaffID);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======查询会员["+sStaffID+"]登录信息出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{sStaffID.toString()});
        }
        if(authStaff == null)
        {
            return StaffConstants.Login.NOEXITS_ERROR;
        }
        //获取当前时间，该时间用于判断用户被锁定时间
        Calendar calendar = Calendar.getInstance();
        //当前时间当前推移AUTH_LOCK_HOURS（3）小时数，如果用户最后登陆失败时间已经超过3个小时，则解除锁定
        calendar.add(Calendar.HOUR, -StaffConstants.authStaff.LOGIN_FREEZE_TIME.intValue());
        //解除锁定时间
        Timestamp unLockTime = new Timestamp(calendar.getTimeInMillis());

        //1.判断用户当前状态，如果当前状态正常/或者状态被锁定（由于密码输错被锁定的），但锁定时间已超过3个小时，则继续验证密码
        if(authStaff.getStaffFlag().equals(AUTH_STATUS_NOMAL)
                ||(authStaff.getStaffFlag().equals(AUTH_PASSWORD_LOCK) 
                && unLockTime.after(authStaff.getLastLoginFailureTime())))
        {
            Timestamp nowtime = new Timestamp(System.currentTimeMillis());
            //判断用户密码是否失效
            if(nowtime.before(authStaff.getInvalidTime()))
            {
                //1.1未失效，判断用户密码
//                if(password.equals(authStaff.getStaffPasswd()))
                if(passwordUtils.checkPassword(password, authStaff.getStaffPasswd()))
                {
                    //验证通过
                    authStaff.setStaffFlag(AUTH_STATUS_NOMAL);
                    //1.清理失败次数
                    authStaff.setLoginFailureCntToday(new BigDecimal(0));
                    //2.登陆次数+1
                    authStaff.setLoginSuccessCnt(authStaff.getLoginSuccessCnt()==null?new BigDecimal(1):authStaff.getLoginSuccessCnt().add(new BigDecimal(1)));
                    
                    //3.更新登陆时间
                    authStaff.setLastLoginTime(nowtime);
                    //4.登陆用户登陆日志
                    try {
                        authStaffMapper.updateByPrimaryKey(authStaff);
                    } catch (Exception e) {
                        // TODO: handle exception
                        LogUtil.info(MODULE, "=======更新会员["+sStaffID+"]登录信息出现异常:", e);
                        throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{sStaffID.toString()});
                    }
                    //5.日志输出
                    return "SUCCESS";
                }
                else
                {
                    //验证不通过，更新登陆日志
                    
                    //1.登陆失败次数+1
                    authStaff.setLoginFailureCntToday(authStaff.getLoginFailureCntToday()==null?new BigDecimal(1):authStaff.getLoginFailureCntToday().add(new BigDecimal(1)));
                    
                    //2.更新登陆失败时间
                    authStaff.setLastLoginFailureTime(nowtime);
                    
                    //3.如果失败超过规定数次，更新用户状态为锁定状态
                  /*  if(authStaff.getLoginFailureCntToday().compareTo(LOGIN_ERROR_LOCK_COUNT) > 0)
                    {
                        authStaff.setStaffFlag(AUTH_PASSWORD_LOCK);
                    }*/
                    //4.更新用户登陆日志
                    try {
                        authStaffMapper.updateByPrimaryKey(authStaff);
                    } catch (Exception e) {
                        // TODO: handle exception
                        LogUtil.info(MODULE, "=======更新会员["+sStaffID+"]登录信息出现异常:", e);
                        throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{sStaffID.toString()});
                     }
                    //5.日志输出
                    
                    return StaffConstants.Login.PASSWORD_ERROR;
                }
            }
            else
            {
                //密码失效返回失效代码
                return StaffConstants.Login.PASSWORD_INVALID_ERROR;
            }
        }
        //1.2锁定状态，则返回错误代码
        return StaffConstants.Login.LOCK_ERROR;
    }

    @Override
    public CustInfo getCustInfoByID(Long sStaffID) {
        CustInfo info = null;
        try {
            info = custInfoMapper.selectByPrimaryKey(sStaffID);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "=======查询会员["+sStaffID+"]信息出现异常:", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{sStaffID.toString()});

        }
        return info;
    }

	@Override
	public void updateRegisterCompany(CustInfoReqDTO custInfoReqDTO,
			CustAuthstrReqDTO custAuthstrReqDTO)throws BusinessException {
		Long staffId = custManageSV.saveCustInfoForRSV(custInfoReqDTO);
		custAuthstrReqDTO.setStaffId(staffId);
		custCheckSV.saveCustAuthstr(custAuthstrReqDTO);
	}

	@Override
	public boolean checkPassword(String pass, String rawPass) throws BusinessException {
		return passwordUtils.checkPassword(pass, rawPass);
	}
}

