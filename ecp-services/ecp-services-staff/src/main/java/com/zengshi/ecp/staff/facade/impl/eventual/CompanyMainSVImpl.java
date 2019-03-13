package com.zengshi.ecp.staff.facade.impl.eventual;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanyCheckResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.facade.interfaces.eventual.ICompanyMainSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyCheckSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustCheckSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 企业相关的分布式事务实现类<br>
 * Date:2015-9-4下午5:19:43  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@Service("companyTxMainSV")
public class CompanyMainSVImpl implements ICompanyMainSV{

    @Resource(name="transactionManager1")
    private TransactionManager companyTransactionManager;
    
    @Resource
    private ICompanyManageSV companyManageSV;
    
    @Resource
    private ICompanyCheckSV companyCheckSV;
    
    @Resource
    private ICustCheckSV custCheckSV;
    
    private static final String MODULE = CompanyMainSVImpl.class.getName();
    
    @Override
    public CompanyInfoResDTO companyBuildRep(final CompanyInfoReqDTO req) throws BusinessException {
    	
        LogUtil.debug(MODULE, "=========新建企业，同时创建仓库的分布式事务开始========");
        long result = 0;
        CompanyInfoResDTO resDTO = new CompanyInfoResDTO();
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent(JSON.toJSONString(req));
        txMsg.setName("business-topic-company-rep");
        //保存企业信息
        result = companyManageSV.saveCompanyInfo(req);
        //返回刚才的企业对象
        CompanyInfoResDTO res = companyManageSV.findCompanyInfoById(result);
        final long saveFlag = result;
        txMsg.setContent(JSON.toJSONString(res));
        //启动主事务
        CompanyInfoResDTO companyInfo = (CompanyInfoResDTO)companyTransactionManager.startTransaction(txMsg, new TransactionCallback() {
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                
                if (saveFlag < 1) {
                    status.setRollbackOnly();
                    LogUtil.debug(MODULE, "=========新建企业，同时创建仓库的分布式事务，主事务异常，事务回滚========");
                }
                return null;
            }
        });
        LogUtil.debug(MODULE, "=========新建企业，同时创建仓库的分布式事务，主事务完成========");
        return resDTO;
    }

    @Override
    public CompanyCheckResDTO companyCheckBuildStock(final CompanySignReqDTO req)
            throws BusinessException {
    	LogUtil.debug(MODULE, "=========企业审核通过，同时创建仓库的分布式事务开始========");
        CompanyCheckResDTO res = new CompanyCheckResDTO();  
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setName("business-topic-company-check");
        
        //保存企业信息
        try {
            res = companyCheckSV.updateCheckCompany(req);
        } catch (Exception e) {
            LogUtil.error(MODULE, "企业审核异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
        //把对象传到子事务中
        txMsg.setContent(JSONObject.fromObject(res).toString());
        //启动主事务
        CompanyCheckResDTO check = (CompanyCheckResDTO)companyTransactionManager.startTransaction(txMsg, new TransactionCallback() {
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                
                return null;
            }
        });
        LogUtil.debug(MODULE, "=========企业审核通过，同时创建仓库的分布式事务，主事务完成========");
        return res;
    }

    @Override
    public CustAuthstrResDTO CustCompanyBuildStock(final CustAuthstrReqDTO req) throws BusinessException {
    	LogUtil.debug(MODULE, "=========企业审核通过，同时创建仓库的分布式事务开始========");
        CustAuthstrResDTO dto = new CustAuthstrResDTO();
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setName("business-topic-cust-check");
        //保存企业信息
        try {
            dto =   custCheckSV.updateCustToCompanyCust(req);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(MODULE, "企业用户审核异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
        //把对象传到子事务中
        txMsg.setContent(JSONObject.fromObject(dto).toString());
        //启动主事务
        CustAuthstrResDTO auth = (CustAuthstrResDTO)companyTransactionManager.startTransaction(txMsg, new TransactionCallback() {
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return null;
            }
        });
        
        LogUtil.debug(MODULE, "=========企业审核通过，同时创建仓库的分布式事务，主事务完成========");
        return dto;
    }

}

