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
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
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
public class OrderAutoReceivingJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = OrderAutoReceivingJob.class.getName();
    
    private IOrdReceiptRSV ordReceiptRSV = QuartzContextHolder.getBean(IOrdReceiptRSV.class);
    
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
            ROrdReceiptRequest rOrdReceiptRequest = new ROrdReceiptRequest();
            rOrdReceiptRequest.setCount(count);
            rOrdReceiptRequest.setTableIndex(tableIndex);
            List<ROrdMainResponse> list = ordReceiptRSV.queryNotReceiptOrder(rOrdReceiptRequest);
            if(list!=null&&!list.isEmpty()){
                for(ROrdMainResponse ordMainResponse:list){
                    try{
                        ROrdReceiptRequest ordReceiptRequest = new ROrdReceiptRequest();
                        ordReceiptRequest.setOrderId(ordMainResponse.getId());
                        ordReceiptRequest.setStaffId(JobConstants.DEFAULT_STAFFID);
                        ordReceiptRequest.getStaff().setId(JobConstants.DEFAULT_STAFFID);
                        ordReceiptRSV.orderReceipt(ordReceiptRequest);
                    }catch(Exception e){
                        LogUtil.error(MODULE,"=============订单"+ordMainResponse.getId()+"自动收货异常=============",e);
                    }
                }
            }
        } catch(Exception err){
            LogUtil.error(MODULE,"=============OrderAutoReceivingJob error =============",err);
        }
        LogUtil.info(MODULE,"=============end OrderAutoReceivingJob job =============");
    }
    
    public static void main(String[] args) throws JobExecutionException {
        OrderAutoReceivingJob job = new OrderAutoReceivingJob();
        Map<String, String> extendParams = new HashMap<String, String>();
        extendParams.put(JobConstants.DealCount, "1");
        extendParams.put(JobConstants.TableIndex, "0");
        job.doJob(extendParams);
    }

}

