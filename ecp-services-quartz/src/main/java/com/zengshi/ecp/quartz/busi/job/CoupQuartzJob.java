package com.zengshi.ecp.quartz.busi.job;

import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;

import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-quartz <br>
 * Description: <br>
 * Date:2016年3月3日上午10:44:33  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
@DisallowConcurrentExecution
public class CoupQuartzJob extends AbstractCommonQuartzJob{
    
    private static final String MODULE = updatePromStatusQuartzJob.class.getName();
    
    private ICoupDetailRSV coupDetailRSV = QuartzContextHolder.getBean(ICoupDetailRSV.class);
    

    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        
        LogUtil.info(MODULE, "=============begin 过期优惠券设置为无效 job =============");
        try{
        	coupDetailRSV.editCoupStatus();
        }
        catch(BusinessException err){
            LogUtil.error(MODULE,"=============过期优惠券设置为无效 BusinessException =============",err);
        }catch(Exception err){
            LogUtil.error(MODULE,"=============过期优惠券设置为无效 Exception =============",err);
        }
        LogUtil.info(MODULE,"=============end 过期优惠券设置为无效 job =============");
        
    }
    
    public  static void main(String[] args){
    	CoupQuartzJob job=new CoupQuartzJob();
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

