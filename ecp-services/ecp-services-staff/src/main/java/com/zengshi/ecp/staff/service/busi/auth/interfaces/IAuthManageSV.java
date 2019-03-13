package com.zengshi.ecp.staff.service.busi.auth.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthRole;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月28日下午2:07:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAuthManageSV extends IGeneralSQLSV {
    /**
     * 
     * findAuthStaffById:(通过用户ID，查找用户登陆信息). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param pStaffID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaff findStaff(Long pStaffID) throws BusinessException; 
    /**
     * 
     * findStaffRoleByStaffId:(通过用户ID查询该用户的角色列表). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param pStaffID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthStaff2Role> listRole(Long pStaffID) throws BusinessException; 
    
    /**
     * 
     * findStaffGroupByStaffId:(通过用户ID查询该用户用户组列表). <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param pStaffID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AuthStaff2Group> listGroup(Long pStaffID) throws BusinessException;
    
    /**
     * 
     * findAuthRoleList:(获取会员的角色). <br/> 
     * 
     * @author wangbh
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public List<AuthRoleResDTO> findAuthRoleList(AuthStaffResDTO req) throws BusinessException;
}

