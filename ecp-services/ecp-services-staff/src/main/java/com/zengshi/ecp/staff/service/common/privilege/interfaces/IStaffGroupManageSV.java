package com.zengshi.ecp.staff.service.common.privilege.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroup;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 用户组管理功能服务接口<br>
 * Date:2015年8月29日上午10:00:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IStaffGroupManageSV extends IGeneralSQLSV {
    
    /**
     * 
     * saveStaffGroup:(新增用户组). <br/> 
     * 
     * @author linby 
     * @param asg
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteStaffGroupById:(根据用户组id删除). <br/> 
     * 
     * @author linby 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteStaffGroupById(AuthStaffGroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateStaffGroup:(修改用户组). <br/> 
     * 
     * @author linby 
     * @param asg
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findStaffGroupById:(根据用户组id查找). <br/> 
     * 
     * @author linby 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaffGroupResDTO findStaffGroupById(Long id) throws BusinessException;
    
    /**
     * 
     * listAuthStaffGroup:(根据既定条件查找用户组列表). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AuthStaffGroupResDTO> listAuthStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException;

}

