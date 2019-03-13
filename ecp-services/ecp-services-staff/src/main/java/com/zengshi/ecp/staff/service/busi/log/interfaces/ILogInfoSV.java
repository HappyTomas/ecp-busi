package com.zengshi.ecp.staff.service.busi.log.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ShopInfo;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年10月1日下午4:19:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface ILogInfoSV {

    
    
    /**
     * 
     * saveCustInfoLog:(保存用户资料日志). <br/> 
     * 
     * @author wangbh
     * @param cusInfo
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveCustInfoLog(CustInfo cusInfo) throws BusinessException;
    
    
    /**
     * 
     * saveCompanyInfoLog:(保存企业资料日志). <br/> 
     * 
     * @author huangxl 
     * @param companyInfo
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveCompanyInfoLog(CompanyInfo companyInfo) throws BusinessException;
    
    /**
     * 
     * savaShopInfoLog:(保存店铺日志). <br/> 
     * 
     * @author PJieWin 
     * @param shopInfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveShopInfoLog(ShopInfo shopInfo)throws BusinessException;
}

