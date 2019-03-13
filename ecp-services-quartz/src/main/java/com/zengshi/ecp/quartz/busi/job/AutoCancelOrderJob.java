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

import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.DealFrom;
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
public class AutoCancelOrderJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = AutoCancelOrderJob.class.getName();
    
    private IOrdMainRSV  ordMainRSV = QuartzContextHolder.getBean(IOrdMainRSV.class);
    
    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        LogUtil.info(MODULE, "=============begin OrderAutoReceivingJob job =============");
        try{
            int count = JobConstants.DEFAULT_DEALCOUNT;
            int tableIndex = 0;
            if(extendParams!=null){
                String countStr = extendParams.get(JobConstants.DealCount);
                if(!StringUtil.isBlank(countStr)){
                    count = Integer.parseInt(countStr);
                }
                String tableIndexStr = extendParams.get(JobConstants.TableIndex);
                if(!StringUtil.isBlank(tableIndexStr)){
                    tableIndex = Integer.parseInt(tableIndexStr);
                }
                
            }else{
                LogUtil.info(MODULE, "未获取到页面入参map");
            }
            RQueryOrderRequest rQueryOrderRequest = new RQueryOrderRequest();
            rQueryOrderRequest.setCount(count);
            rQueryOrderRequest.setTableIndex(tableIndex);
            List<ROrdMainResponse> list = ordMainRSV.queryNeedCancelOrder(rQueryOrderRequest);
            if(list!=null&&!list.isEmpty()){
                for(ROrdMainResponse ordMainResponse:list){
                    try{
                        ROrderDetailsRequest info = new ROrderDetailsRequest();
                        info.setOrderId(ordMainResponse.getId());
                        info.setDelFrom(DealFrom.FROM_AUTO);
                        info.getStaff().setId(JobConstants.DEFAULT_STAFFID);
                        info.setStaffId(JobConstants.DEFAULT_STAFFID);
                        ordMainRSV.removeOrd(info);
                    }catch(Exception e){
                        LogUtil.error(MODULE,"=============订单"+ordMainResponse.getId()+"自动取消订单异常=============",e);
                    }
                    
                }
            }
        } catch(Exception err){
            LogUtil.error(MODULE,"=============OrderAutoReceivingJob error =============",err);
        }
        LogUtil.info(MODULE,"=============end OrderAutoReceivingJob job =============");
    }
    
    public static void main(String[] args) throws JobExecutionException {
        AutoCancelOrderJob job = new AutoCancelOrderJob();
        Map<String, String> extendParams = new HashMap<String, String>();
        extendParams.put(JobConstants.DealCount, "1");
        extendParams.put(JobConstants.TableIndex, "0");
        job.doJob(extendParams);
    }

}

