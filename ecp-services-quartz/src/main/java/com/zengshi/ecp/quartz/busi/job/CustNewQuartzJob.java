/** 
 * Project Name:quartz-services 
 * File Name:DemoQuartzJob.java 
 * Package Name:com.zengshi.ecp.quartz.busi.job 
 * Date:2015-11-15下午6:05:25 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.quartz.busi.job;

import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;

import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 计算前一天的会员新增数
 * @author wangbh
 *
 */
@DisallowConcurrentExecution
public class CustNewQuartzJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = CustNewQuartzJob.class.getName();
    
    private ICustInfoRSV CustInfoRSV = QuartzContextHolder.getBean(ICustInfoRSV.class);

    @Override
    protected String getModule() {
        return MODULE;
    }

    /**
     * 
     * 获取前一天的新镇的会数
     * @see com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob#doJob(java.util.Map)
     */
    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
    	LogUtil.info(MODULE, "=============begin cust job =============");
        int day= 0;
        if(null!=extendParams){
    	 if (StringUtil.isBlank(extendParams.get("day"))) {
                LogUtil.info(MODULE, "====================未获取页面传入的参数:day 默认为1===============");
         }else{
        	 String daya = extendParams.get("day");
        	 day = Integer.parseInt(daya);
         }
        }
    		try {
       		 CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
       		 custInfoReqDTO.setCustDayTime(day);
       		 CustInfoRSV.getCustNewCount(custInfoReqDTO);
   		} catch (Exception e) {
   			 LogUtil.error(MODULE,"=============error =============",e);
   		}
    	
    
    	  LogUtil.info(MODULE,"=============end cust job =============");   
    	
    }
    public  static void main(String[] args){
    	CustNewQuartzJob job=new CustNewQuartzJob();
        try {
            
            job.doJob(null);
            
       /*     Map<String, String> extendParams=new HashMap<String,String>();
            extendParams.put("pageSize", "100");
            extendParams.put("shopId", "35");
            extendParams.put("siteId", "1");
            job.doJob(extendParams);*/
        } catch (JobExecutionException e) {
            e.printStackTrace();
        }
    }

}

