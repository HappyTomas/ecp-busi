package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroup;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo用户组管理功能服务接口<br>
 * Date:2015年9月17日下午3:59:46  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IStaffGroupManageRSV {
    
    /**
     * 
     * saveStaffGroup:(新增用户组). <br/> 
     * 
     * @author linby 
     * @param reqDto
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
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteStaffGroupById(AuthStaffGroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateStaffGroup:(修改用户组). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateStaffGroup(AuthStaffGroupReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findStaffGroupById:(根据用户组id查找). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AuthStaffGroupResDTO findStaffGroupById(AuthStaffGroupReqDTO reqDto) throws BusinessException;
    
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

