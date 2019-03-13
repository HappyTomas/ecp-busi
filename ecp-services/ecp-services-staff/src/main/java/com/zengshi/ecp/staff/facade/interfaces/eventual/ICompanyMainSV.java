package com.zengshi.ecp.staff.facade.interfaces.eventual;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanyCheckResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 企业相关的分式事务接口<br>
 * Date:2015-9-4下午4:04:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface ICompanyMainSV {

    /**
     * 
     * companyBuildRep:(新建企业时，建立相应的仓库). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CompanyInfoResDTO companyBuildRep(final CompanyInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * companyCheckBuildStock:(企业审核通过创建仓库). <br/> 
     * 
     * @author wangbh
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    
    
    public CompanyCheckResDTO companyCheckBuildStock(CompanySignReqDTO req) throws BusinessException;
    
    /**
     * 
     * CustCompanyBuildStock:(普通企业审核通过新增仓库). <br/> 
     * 
     * @author wangbh
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    
    public CustAuthstrResDTO CustCompanyBuildStock(CustAuthstrReqDTO req) throws BusinessException;
    
    
    
}

