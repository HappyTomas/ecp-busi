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
import java.util.List;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;

import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoResponse;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayQuartzInfoForOrderRSV;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.quartz.busi.util.JobConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:quartz-services <br>
 * Description: <br>
 * Date:2015-11-15下午6:05:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@DisallowConcurrentExecution
public class PayScoreJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = PayScoreJob.class.getName();
    
    private IPayQuartzInfoForOrderRSV payQuartzInfoForOrderRSV = QuartzContextHolder.getBean(IPayQuartzInfoForOrderRSV.class);
    
    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        LogUtil.info(MODULE, "=============begin PayScoreJob job =============");
        try{
            int count = JobConstants.DEFAULT_DEALCOUNT;
            if(extendParams!=null){
                String countStr = extendParams.get(JobConstants.DealCount);
                if(!StringUtil.isBlank(countStr)){
                    count = Integer.parseInt(countStr);
                }
            }else{
                LogUtil.info(MODULE, "未获取到页面入参map");
            }
            RPayQuartzInfoRequest payQuartzInfoRequest = new RPayQuartzInfoRequest();
            payQuartzInfoRequest.setCount(count);
            List<RPayQuartzInfoResponse> list = payQuartzInfoForOrderRSV.queryNotDealScoreOrder(payQuartzInfoRequest);
            if(list!=null&&!list.isEmpty()){
                for(RPayQuartzInfoResponse quartzInfoResponse:list){
                    try{
                        RPayQuartzInfoRequest rPayQuartzInfoRequest= new RPayQuartzInfoRequest();
                        rPayQuartzInfoRequest.setOrderId(quartzInfoResponse.getOrderId());
                        rPayQuartzInfoRequest.setStaffId(JobConstants.DEFAULT_STAFFID);
                        payQuartzInfoForOrderRSV.dealScoreOrder(rPayQuartzInfoRequest);
                    }catch(Exception e){
                        LogUtil.error(MODULE,"=============订单"+quartzInfoResponse.getId()+"处理积分异常=============",e);
                    }
                }
            }
        } catch(Exception err){
            LogUtil.error(MODULE,"=============PayScoreJob error =============",err);
        }
        LogUtil.info(MODULE,"=============end PayScoreJob job =============");
    }
    
    public static void main(String[] args) throws JobExecutionException {
        PayScoreJob job = new PayScoreJob();
        Map<String, String> extendParams = new HashMap<String, String>();
        extendParams.put(JobConstants.DealCount, "1");
//        extendParams.put(JobConstants.TableIndex, "0");
        job.doJob(extendParams);
    }
}

