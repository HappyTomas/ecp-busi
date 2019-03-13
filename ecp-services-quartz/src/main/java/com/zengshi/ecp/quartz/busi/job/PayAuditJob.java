/** 
 * Project Name:quartz-services 
 * File Name:DemoQuartzJob.java 
 * Package Name:com.zengshi.ecp.quartz.busi.job 
 * Date:2015-11-15下午6:05:25 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.quartz.busi.job;

import java.util.HashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;

import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.quartz.busi.util.JobConstants;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-quartz-services <br>
 * Description: 对账定时任务<br>
 * Date:2015年12月28日下午5:22:34  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
@DisallowConcurrentExecution
public class PayAuditJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = PayAuditJob.class.getName();
    
    private IPaymentRSV paymentRSV = QuartzContextHolder.getBean(IPaymentRSV.class);
    
    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        LogUtil.info(MODULE, "=============begin PayAuditJob job =============");
        try{
            String payway = "";
            if(extendParams!=null){
                payway = extendParams.get(JobConstants.PAYWAY);
            }else{
                LogUtil.info(MODULE, "未获取到页面入参map");
            }
            paymentRSV.check(payway, extendParams);
        } catch(Exception err){
            LogUtil.error(MODULE,"=============PayAuditJob error =============",err);
        }
        LogUtil.info(MODULE,"=============end PayAuditJob job =============");
    }
    
    public static void main(String[] args) throws JobExecutionException {
        PayAuditJob job = new PayAuditJob();
        Map<String, String> extendParams = new HashMap<String, String>();
        extendParams.put(JobConstants.DealCount, "1");
//        extendParams.put(JobConstants.TableIndex, "0");
        job.doJob(extendParams);
    }
}

