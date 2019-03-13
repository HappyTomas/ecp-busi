package com.zengshi.ecp.staff.service.busi.register.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月6日上午10:52:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */

public interface IRegisterSV {
    
   
    
	/**
	 * 
	 * saveAuthStaff:(新增用户表)
	 * 
	 * @author  wangbh
	 * @param authStaff
	 * @return 
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public long saveAuthStaff(AuthStaffReqDTO info) throws BusinessException;
	
	
	
	/**
	 * 
	 * saveCustInfo:(新增custinfo). <br/> 
	 * 
	 * @author wangbh
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void saveCustInfo(CustInfoReqDTO info) throws BusinessException;
	
	
	
	
	/**
	 * 
	 * updateAuthStaff:(修改用户表). <br/> 
	 * 
	 * @author wangbh
	 * @param authStaff
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	
	public void updateAuthStaff(AuthStaffReqDTO info) throws BusinessException;
	
    /**
     * 
     * updateAuthStaff:(查询用户表). <br/> 
     * 
     * @author wangbh
     * @param authStaff
     * @throws BusinessException 
     * @since JDK 1.7
     */
	
	public PageResponseDTO<AuthStaffResDTO> queryAuthStaff(AuthStaffReqDTO info) throws BusinessException;
	
	
	/**
	 * 
	 * queryAuthStaffName:(查询用户工号). <br/> 
	 * 
	 * @author wangbh
	 * @param info
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */

	public String queryAuthStaffName(long staffId) throws BusinessException;
	

	/**
     * 
     * queryAuthStaffName:(注册). <br/> 
     * 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
	public Long sign(AuthStaffReqDTO info)  throws BusinessException;
	
	/**
	 * 
	 * findAuthStaffById:(通过staffId查询authstaff信息). <br/> 
	 * 
	 * @author huangxl 
	 * @param id
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public AuthStaffResDTO findAuthStaffById(Long id) throws BusinessException;
	
	/**
     * 
     * checkExist:(校验是否存在：员工编码、手机号码、邮箱、用户昵称). <br/> 
     * 说明：用于校验的4个字段，都可以用来登录，所以必须保持唯一性
     * @author huangxl 
     * @param check 
     * @since JDK 1.7
     */
    public void checkExist(String check, Long staffId,String tipCode) throws BusinessException;

}
