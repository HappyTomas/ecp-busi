package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustSubInfoReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 店铺子帐号rsv<br>
 * Date:2016-4-13下午11:45:48  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface IShopSubAuthStaffRSV {

    /**
     * 
     * saveShopSubAuthStaff:(新增店铺子账户[auth_staff]). <br/> 
     * 
     * @author linby 
     * @param custReqDto  登录客户基本信息
     * @param custSubReqDto  注册子帐户信息
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveShopSubAuthStaff(CustInfoReqDTO custReqDto, CustSubInfoReqDTO custSubReqDto) throws BusinessException;
    
    /**
     * 
     * deleteShopSubAuthStaff:(删除店铺子账户[auth_staff]). <br/> 
     * 物理删除
     * 
     * @author linby 
     * @param staffId
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteShopSubAuthStaff(Long staffId) throws BusinessException;
    
    /**
     * 
     * disableShopSubAuthStaff:(停用店铺子账户[auth_staff]). <br/> 
     * 
     * @author linby 
     * @param staffId
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void disableShopSubAuthStaff(Long staffId) throws BusinessException;
    
    /**
     * 
     * enableShopSubAuthStaff:(启用店铺子账户[auth_staff]). <br/> 
     * 
     * @author linby 
     * @param staffId
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void enableShopSubAuthStaff(Long staffId) throws BusinessException;
    
    /**
     * 
     * listShopSubAuthStaff:(查询店铺子账户). <br/> 
     * 
     * @author linby 
     * @param custReqDto  主帐号信息
     * @param custSubReqDto  子帐号（查询）信息
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<CustInfoResDTO> listShopSubAuthStaff(CustInfoReqDTO custReqDto, CustSubInfoReqDTO custSubReqDto) throws BusinessException;
    
    /**
     * 
     * updateSubAcctRole:(更新用户的角色信息). <br/> 
     * 先删除角色，再新增新的角色
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateSubAcctRole(CustSubInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * countShopSubAuthStaff:(查询店铺的子帐号数量). <br/> 
     * 
     * @author huangxl5
     * @param shopId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long countShopSubAuthStaff(Long shopId) throws BusinessException;
}

