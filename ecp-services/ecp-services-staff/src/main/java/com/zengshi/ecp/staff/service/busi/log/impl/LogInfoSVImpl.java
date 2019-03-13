package com.zengshi.ecp.staff.service.busi.log.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyInfoLogMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoLogMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopInfoLogMapper;
import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CompanyInfoLog;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.CustInfoLog;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopInfoLog;
import com.zengshi.ecp.staff.service.busi.log.interfaces.ILogInfoSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年10月1日下午4:21:02  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */

public class LogInfoSVImpl implements ILogInfoSV{
    
    private static final String MODULE = LogInfoSVImpl.class.getName();
    
    @Resource
    private CustInfoLogMapper custInfoLogMapper;
    
    @Resource(name = "seq_cust_info_log_id")
    private Sequence seq_cust_info_log_id;
    
    @Resource
    private CompanyInfoLogMapper companyInfoLogMapper;
    
    @Resource(name = "seq_company_info_log_id")
    private Sequence seq_company_info_log_id;


    @Resource
    private ShopInfoLogMapper shopInfoLogMapper;
    
    @Resource(name="seq_shop_info_log_id")
    private Sequence seq_shop_info_log_id;
    
    @Override
    public void saveCustInfoLog(CustInfo custInfo) throws BusinessException {
        if(null!=custInfo&&0!=custInfo.getId()){
            CustInfoLog log = new CustInfoLog();
            ObjectCopyUtil.copyObjValue(custInfo, log, null, false);
            try {
                log.setId(seq_cust_info_log_id.nextValue());
                log.setStaffId(custInfo.getId());
                custInfoLogMapper.insertSelective(log);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.error(MODULE, "更新用户资料日志表错误", e);
            }
        }
    }


    @Override
    public void saveCompanyInfoLog(CompanyInfo companyInfo) throws BusinessException {
        CompanyInfoLog log = new CompanyInfoLog();
        ObjectCopyUtil.copyObjValue(companyInfo, log, null, true);
        try {
            log.setId(seq_company_info_log_id.nextValue());
            log.setCompanyId(companyInfo.getId());
            companyInfoLogMapper.insertSelective(log);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(MODULE, "更新企业资料日志表错误", e);
        }
    }


    @Override
    public void saveShopInfoLog(ShopInfo shopInfo) throws BusinessException {
        ShopInfoLog log = new ShopInfoLog();
        ObjectCopyUtil.copyObjValue(shopInfo, log, null, true);
        try {
            log.setId(seq_shop_info_log_id.nextValue());
            log.setShopId(shopInfo.getId());
            shopInfoLogMapper.insertSelective(log);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(MODULE, "更新店铺日志表错误", e);
        }
    }
    
}

