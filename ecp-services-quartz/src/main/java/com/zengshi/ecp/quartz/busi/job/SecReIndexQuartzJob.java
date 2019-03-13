package com.zengshi.ecp.quartz.busi.job;

import java.util.HashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;

import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.quartz.busi.util.JobConstants;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:quartz-services <br>
 * Description: 店铺索引重建调度任务<br>
 * Date:2015年11月23日上午10:36:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
@DisallowConcurrentExecution
public class SecReIndexQuartzJob extends AbstractCommonQuartzJob{
    
    private static final String MODULE = SecReIndexQuartzJob.class.getName();
    
    private ISecConfigPlanRSV secConfigPlanRSV = QuartzContextHolder.getBean(ISecConfigPlanRSV.class);
    
    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        
        LogUtil.info(MODULE, "=============begin 索引重建 job =============");
        try{
            String reindexCfgId;
            if(null == extendParams || StringUtil.isBlank(extendParams.get(JobConstants.SEC_CONFIG_ID))){
                LogUtil.info(MODULE, "未获取到页面secConfigId入参！");
            }else{
                reindexCfgId = extendParams.get(JobConstants.SEC_CONFIG_ID);
                Long cfgId = Long.valueOf(reindexCfgId);
                SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
                secConfigReqDTO.setId(cfgId);
                this.secConfigPlanRSV.reFullImportIndex(secConfigReqDTO,false);
            }

        } catch(Exception err){
            LogUtil.error(MODULE,"=============索引重建 error =============",err);
        }
        LogUtil.info(MODULE,"=============end 索引重建 job =============");
        
    }
    
    public static void main(String[] args) throws JobExecutionException {
        SecReIndexQuartzJob job = new SecReIndexQuartzJob();
        Map<String, String> extendParams = new HashMap<>();
        extendParams.put(JobConstants.SEC_CONFIG_ID, "254");
        job.doJob(extendParams);
    }

}

