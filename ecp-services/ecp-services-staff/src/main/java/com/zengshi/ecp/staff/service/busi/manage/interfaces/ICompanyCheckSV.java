package com.zengshi.ecp.staff.service.busi.manage.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanyCheckResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月6日上午10:52:32 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version
 * @since JDK 1.7
 */

public interface ICompanyCheckSV{

    /**
     * 
     * queryCompanySignList:(查询待审请入驻企业). <br/>
     * 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException
     * @since JDK 1.7
     */
    public PageResponseDTO<CompanySignResDTO> queryCompanySignList(CompanySignReqDTO info)
            throws BusinessException;

    /**
     * 
     * queryCompanySign:(查询单个申请入驻企业). <br/>
     * 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException
     * @since JDK 1.7
     */

    public CompanySignResDTO queryCompanySign(CompanySignReqDTO info) throws BusinessException;
    
    
    /**
     * 
     * updateCheckCompany:(入驻企业审核通过). <br/> 
     * 
     * @author wangbh
     * @param info
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CompanyCheckResDTO updateCheckCompany(CompanySignReqDTO info) throws BusinessException;
    
    
    /**
     * 
     * updateNoCheckCompany:(入驻企业审核不通过). <br/> 
     * 
     * @author wangbh
     * @param info
     * @throws BusinessException 
     * @since JDK 1.7
     */
    
    public void updateNoCheckCompany(CompanySignReqDTO info) throws BusinessException;
    
    
    /**
     * 
     * getCheckCompanyCount:(获取待审核企业数). <br/> 
     * 
     * @author wangbh
     * @param info
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public Long getCheckCompanyCount() throws BusinessException;

}
