package com.zengshi.ecp.quartz.busi.job;

import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.paas.utils.LogUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;

import java.util.Map;

/**
 * Title: ECP <br>
 * Project Name:quartz-services <br>
 * Description: 索引重建任务地调度<br>
 * Date:2015年11月23日上午10:36:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
@DisallowConcurrentExecution
public class MallPointGdsReIndexQuartzJob extends AbstractCommonQuartzJob{
    
    private static final String MODULE = MallPointGdsReIndexQuartzJob.class.getName();
    
    private ISecConfigPlanRSV secConfigPlanRSV = QuartzContextHolder.getBean(ISecConfigPlanRSV.class);

    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        
        LogUtil.info(MODULE, "=============begin 积分商城商品索引重建 job =============");
        try{
            SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
            secConfigReqDTO.setId(2l);
            this.secConfigPlanRSV.reFullImportIndex(secConfigReqDTO,false);
            
        } catch(Exception err){
            LogUtil.error(MODULE,"=============积分商城商品索引重建 error =============",err);
        }
        LogUtil.info(MODULE,"=============end 积分商城商品索引重建 job =============");
        
    }

}

