package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginResultResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.login.interfaces.IAuthLoginSV;
import com.zengshi.ecp.staff.service.common.loginlog.interfaces.ILoginAccessLogSV;
import com.zengshi.ecp.staff.service.common.loginlog.interfaces.ILoginLogSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月14日上午10:33:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 平台用户登陆验证实现函数
 */
public class AuthLoginRSVImpl implements IAuthLoginRSV {

    /**
     * 邮箱验证正则表达式
     */
    String _eMail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$ ";
    /**
     * 验证手机号正则表达式
     */
    String _PhoneNumber = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0-9])|(18[0,5-9]))\\d{8}$";
    
    @Resource
    private IAuthLoginSV authLoginService;
    private static final String MODULE = AuthLoginRSVImpl.class.getName();
    
    @Resource
    private ILoginLogSV loginLogSV;
    
    @Resource
    private ILoginAccessLogSV loginAccessLogSV;

    @Override
    public LoginResultResDTO checkLogin(String loginpara, String password) throws BusinessException{
        
        Long staffID = null;
        //1.首先判断是用哪种方式进行登陆：1.用户编码，2.用户手机号，3.用户邮箱
        
        if(StaffTools.checkRegx(loginpara, _eMail))
        {
            //如果是邮箱登陆，根据邮箱找到用户ID
            staffID = authLoginService.findIDByeEmail(loginpara);
        }
        //2.由于用户编码与手机号码存在相同的可能，所以先匹配手机号的正则表达式
        if(StaffTools.checkRegx(loginpara, _PhoneNumber))
        {
            //匹配手机号成功,根据手机号找到用户ID
            staffID = authLoginService.findIDByPhoneNumber(loginpara);
        }
        if(staffID == null)
        {
            //根据用户账号找到用户ID
            staffID = authLoginService.findIDByStaffCode(loginpara);
        }
        
        //获取用户ID成功，验证密码
        if(staffID != null)
        {
            //密码验证成功
            String resultCode = authLoginService.checkLogin(staffID, password);
            if(resultCode.equals("SUCCESS"))
            {
                //登陆成功，通过用户ID获取信息
                CustInfo info = authLoginService.getCustInfoByID(staffID);
                if(info == null)
                {
                    throw new BusinessException("用户名或者密码错误");
                }
                    
                LoginResultResDTO dto = new LoginResultResDTO();
                ObjectCopyUtil.copyObjValue(info, dto, null, false);
                //返回结果
                return dto;
            }
            else 
            {
            	//账户已锁定
            	if(StaffConstants.Login.LOCK_ERROR.equals(resultCode)){
            		LogUtil.debug(MODULE, "=======会员["+staffID+"]账户已锁定，登录失败========");
            		throw new BusinessException(resultCode);
            	}
            	
            	//密码已失效
            	if(StaffConstants.Login.PASSWORD_INVALID_ERROR.equals(resultCode)){
            		LogUtil.debug(MODULE, "=======会员["+staffID+"]密码已失效，登录失败========");
            		throw new BusinessException(resultCode);
            	}
            		
                //抛出登陆失败代码
                LogUtil.info(MODULE, "=======会员["+staffID+"]密码验证错误，登录失败========");
                if(StaffConstants.Login.NOEXITS_ERROR.equals(resultCode))
                {
                    throw new BusinessException("用户名或者密码错误");
                }
                throw new BusinessException("用户名或者密码错误");
            }
        }
        else 
        {
            //不存在此用户
            LogUtil.info(MODULE, "=======不存在该会员["+staffID+"]，登录失败========");
            throw new BusinessException("用户名或者密码错误");
        }
    }


    /**
     * 
     * TODO 插入登陆日志表信息T_LOGIN_LOG（可选）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV#insertLoginLog(com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO)
     */
    @Override
    public int insertLoginLog(LoginAccessLogReqDTO loginAccessLogReqDTO, LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException {
        
        //1.参数校验
        if(null == loginLogInfoReqDTO ||loginAccessLogReqDTO==null|| loginLogInfoReqDTO.getStaffId() == null||loginAccessLogReqDTO.getStaffId()==null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"staffId不能为空"});
        }
        //新增登陆日志
        loginLogSV.insert(loginLogInfoReqDTO);
        //新增登陆操作日志
        loginAccessLogSV.insert(loginAccessLogReqDTO);
        
        return 0;
    }
    /**
     * 
     * TODO 更新退出登陆日志（可选）. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV#updateOutLoginLog(com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO)
     */
    @Override
    public int updateOutLoginLog(LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException
    {
        if(null == loginLogInfoReqDTO || loginLogInfoReqDTO.getStaffId() == null || StringUtil.isBlank(loginLogInfoReqDTO.getSessionId()))
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"loginLogInfoReqDTO:staffId,sessionId不能为空"});
        }
        
        loginLogSV.update(loginLogInfoReqDTO);
        
        return 0;
    }


	@Override
	public void registerCompany(CustInfoReqDTO custInfoReqDTO,
			CustAuthstrReqDTO custAuthstrReqDTO) throws BusinessException {
		authLoginService.updateRegisterCompany(custInfoReqDTO, custAuthstrReqDTO);
	}


	@Override
	public boolean checkPassword(String pass, String rawPass) throws BusinessException {
		return authLoginService.checkPassword(pass, rawPass);
	}

}

