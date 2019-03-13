package com.zengshi.ecp.staff.service.busi.company.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CompanySign;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月25日下午5:44:51  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 企业申请入驻信息接口服务，用于企业申请资料管理
 */
public interface ICompanySignSV {
    /**
     * 
     * findCompanySignByStaffID:(根据用户ID查该用户企业申请记录). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param pStaffID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CompanySign find(Long pStaffID) throws BusinessException;
    
    /**
     * 
     * saveCompanySignInfo:(保存企业入驻申请信息). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param pSignInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int save(CompanySign pSignInfo)throws BusinessException;
    
    /**
     * 
     * updateCompanySignInfo:(更新企业入驻申请信息表). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param pSignInfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void update(CompanySign pSignInfo) throws BusinessException;
    
    /**
     * 
     * checkCompanyNameRepeat:(检查企业名称是否重复). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param pCompanyName
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean checkName(CompanySign companySign)throws BusinessException;
    
    /**
     * 
     * exist:(根据pSignInfo.staffCode判断该企业申请记录是否已经存在). <br/> 
     * 
     * @author PJieWin 
     * @param pSignInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean exist(CompanySign pSignInfo) throws BusinessException;
}

