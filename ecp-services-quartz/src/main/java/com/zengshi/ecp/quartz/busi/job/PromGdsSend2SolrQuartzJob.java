package com.zengshi.ecp.quartz.busi.job;

import java.util.HashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromSolrRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:quartz-services <br>
 * Description: 促销单品发送solr任务地调度<br>
 * Date:2016年03月07日上午10:36:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7
 */
@DisallowConcurrentExecution
public class PromGdsSend2SolrQuartzJob extends AbstractCommonQuartzJob{
    
    private static final String MODULE = PromGdsSend2SolrQuartzJob.class.getName();
    
    private IPromSolrRSV promSolrRSV = QuartzContextHolder.getBean(IPromSolrRSV.class);

    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        
        LogUtil.info(MODULE, "=============begin 促销单品通知solr job =============");
        try{
            //传入参数 站点编码 如果为空即空
            //传入参数 店铺编码 如果为空即为空
            //传入参数 状态 10  20 如果为空即后场默认10 和20
            //传入促销编码 如果为空 即为空
            Prom2SolrReqDTO  dto=new Prom2SolrReqDTO();
            //当前系统时间
            //dto.setShowStartTime(DateUtil.getSysDate());
            //默认处理 条数 100
            dto.setPageSize(100);
            
            if(!CollectionUtils.isEmpty(extendParams)){
                  if(StringUtil.isNotEmpty(extendParams.get("shopId"))){
                      dto.setShopId(Long.valueOf(extendParams.get("shopId")));
                  }
                  if(StringUtil.isNotEmpty(extendParams.get("siteId"))){
                      dto.setSiteId(Long.valueOf(extendParams.get("siteId")));
                  }  
                  if(StringUtil.isNotEmpty(extendParams.get("status"))){
                      dto.setStatus(extendParams.get("status"));
                  } 
                  if(StringUtil.isNotEmpty(extendParams.get("promId"))){
                      dto.setPromId(Long.valueOf(extendParams.get("promId")));
                  } 
                  //可处理页面传入条数
                  if(StringUtil.isNotEmpty(extendParams.get("pageSize"))){
                      dto.setPageSize(new Integer(extendParams.get("pageSize")));
                  } 
            }
            dto.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_2);
            promSolrRSV.sendData(dto);
            
        }
        catch(BusinessException err){
            LogUtil.error(MODULE,"=============促销单品通知solr BusinessException =============",err);
        }catch(Exception err){
            LogUtil.error(MODULE,"=============促销单品通知solr Exception =============",err);
        }
        LogUtil.info(MODULE,"=============end 促销单品通知solr job =============");
        
    }
    
    public  static void main(String[] args){
        PromGdsSend2SolrQuartzJob job=new PromGdsSend2SolrQuartzJob();
        try {
            
            //job.doJob(null);
            
            Map<String, String> extendParams=new HashMap<String,String>();
            //extendParams.put("pageSize", "100");
            //extendParams.put("shopId", "35");
            //extendParams.put("status", "10");
            //extendParams.put("siteId", "1");
            job.doJob(extendParams);
        } catch (JobExecutionException e) {
            e.printStackTrace();
        }
    }

}

