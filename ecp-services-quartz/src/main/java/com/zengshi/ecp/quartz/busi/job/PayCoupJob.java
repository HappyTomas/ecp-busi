/** 
 * Project Name:quartz-services 
 * File Name:DemoQuartzJob.java 
 * Package Name:com.zengshi.ecp.quartz.busi.job 
 * Date:2015-11-15下午6:05:25 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.quartz.busi.job;

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
public class PayCoupJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = PayCoupJob.class.getName();
    
    private IPayQuartzInfoForOrderRSV payQuartzInfoForOrderRSV = QuartzContextHolder.getBean(IPayQuartzInfoForOrderRSV.class);
    
    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        LogUtil.info(MODULE, "=============begin PayCoupJob job =============");
        try{
            int count = 0;
            if(extendParams!=null){
                String countStr = extendParams.get(JobConstants.DealCount);
                count = Integer.parseInt(countStr);
            }else{
                LogUtil.info(MODULE, "未获取到页面入参map");
            }
            RPayQuartzInfoRequest payQuartzInfoRequest = new RPayQuartzInfoRequest();
            payQuartzInfoRequest.setCount(count);
            List<RPayQuartzInfoResponse> list = payQuartzInfoForOrderRSV.queryNotDealCoupOrder(payQuartzInfoRequest);
            if(list!=null&&!list.isEmpty()){
                for(RPayQuartzInfoResponse quartzInfoResponse:list){
                    try{
                    	RPayQuartzInfoRequest rPayQuartzInfoRequest= new RPayQuartzInfoRequest();
                        rPayQuartzInfoRequest.setOrderId(quartzInfoResponse.getOrderId());
                        rPayQuartzInfoRequest.setStaffId(JobConstants.DEFAULT_STAFFID);
                    	payQuartzInfoForOrderRSV.dealCoupOrder(rPayQuartzInfoRequest);
                    }catch(Exception e){
                        LogUtil.error(MODULE,"=============订单"+quartzInfoResponse.getId()+"处理优惠券异常=============",e);
                    }
                }
            }
        } catch(Exception err){
            LogUtil.error(MODULE,"=============PayCoupJob error =============",err);
        }
        LogUtil.info(MODULE,"=============end PayCoupJob job =============");
    }

}

