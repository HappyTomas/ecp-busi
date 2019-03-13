package com.zengshi.ecp.staff.service.busi.login.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月14日上午10:41:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 平台用户登陆验证服务类
 */
public interface IAuthLoginSV {
    
    /**
     * 
     * findIDByStaffCode:(根据用户账号，查找索引表，获取用户ID). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param sStaffCode
     * @return 
     * @since JDK 1.6
     */
    public Long findIDByStaffCode(String sStaffCode);
    /**
     * 
     * findIDByeEmail:(根据用户邮箱，查找索引表，获取用户ID). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param eEmail
     * @return 
     * @since JDK 1.6
     */
    public Long findIDByeEmail(String eEmail);
    /**
     * 
     * findIDByPhoneNumber:(根据用户手机号查找索引表，获取用户ID). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param sPhoneNumber
     * @return 
     * @since JDK 1.6
     */
    public Long findIDByPhoneNumber(String sPhoneNumber);
    /**
     * 
     * checkLogin:(验证用户ID与密码接口). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param sStaffID
     * @param password
     * @return 
     * @since JDK 1.6
     */
    public String checkLogin(Long sStaffID, String password);
    /**
     * 
     * getCustInfoByID:(根据用户ID找到用户详细信息). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param sStaffID
     * @return 
     * @since JDK 1.6
     */
    public CustInfo getCustInfoByID(Long sStaffID);
    
    /**
     * 
     * updateRegisterCompany:(企业会员注册). <br/> 
     * service服务
     * 
     * @author wangbh 
     * @param sStaffID
     * @return 
     * @since JDK 1.6
     */
    public void updateRegisterCompany(CustInfoReqDTO custInfoReqDTO,CustAuthstrReqDTO custAuthstrReqDTO)throws BusinessException;
    
    /**
     * 
     * checkPassword:(判断密码是否正确). <br/> 
     * @author huangxl 
     * @param pass 明文密码
     * @param rawPass 数据库里面加过密的密码
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean checkPassword(String pass,String rawPass) throws BusinessException;
}

