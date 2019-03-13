package com.zengshi.ecp.quartz.busi.job;

import java.util.HashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.PromCommDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:quartz-services <br>
 * Description: 促销更新生效为无效任务地调度<br>
 * Date:2015年11月23日上午10:36:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7
 */
@DisallowConcurrentExecution
public class updatePromStatusQuartzJob extends AbstractCommonQuartzJob{
    
    private static final String MODULE = updatePromStatusQuartzJob.class.getName();
    
    private IPromRSV promRSV = QuartzContextHolder.getBean(IPromRSV.class);
    
    private IPromQueryRSV promQueryRSV = QuartzContextHolder.getBean(IPromQueryRSV.class);

    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        
        LogUtil.info(MODULE, "=============begin 促销更新生效为无效 job =============");
        try{
            //传入参数 站点编码 如果为空即空
            //传入参数 店铺编码 如果为空即为空
            PromInfoDTO  dto=new PromInfoDTO();
            //当前系统时间
            dto.setEndTime(DateUtil.getSysDate());
            //默认处理 条数 100
            dto.setPageSize(50);
            
            //参数传入
            if(!CollectionUtils.isEmpty(extendParams)){
                  if(StringUtil.isNotEmpty(extendParams.get("shopId"))){
                      dto.setShopId(Long.valueOf(extendParams.get("shopId")));
                  }
                  if(StringUtil.isNotEmpty(extendParams.get("siteId"))){
                      dto.setSiteId(Long.valueOf(extendParams.get("siteId")));
                  }  
                  //可处理页面传入条数
                  if(StringUtil.isNotEmpty(extendParams.get("pageSize"))){
                      dto.setPageSize(new Integer(extendParams.get("pageSize")));
                  } 
            }
            
            //查询数据
            dto.setStatus(PromConstants.PromInfo.STATUS_10);
            PageResponseDTO<PromInfoResponseDTO>  page=promQueryRSV.queryPromInfoPage(dto);
            
            if(page==null){
                LogUtil.error(MODULE,"没有需要处理的数据");
                return;
            }
            if(page.getCount()<=0){
                LogUtil.error(MODULE,"没有需要处理的数据");
                return;
            }
            
            //用户信息
            BaseStaff  staff=new BaseStaff();
            staff.setId(0);
            //更新列表数据
            for(PromInfoResponseDTO d:page.getResult()){
                 PromCommDTO  updateDto=new PromCommDTO();
                 updateDto.setPromId(d.getId());
                 updateDto.setStaff(staff);
                //更新列表数据
                 promRSV.invalidProm(updateDto);
            }
            
        }
        catch(BusinessException err){
            LogUtil.error(MODULE,"=============促销更新生效为无效 BusinessException =============",err);
        }catch(Exception err){
            LogUtil.error(MODULE,"=============促销更新生效为无效 Exception =============",err);
        }
        LogUtil.info(MODULE,"=============end 促销更新生效为无效 job =============");
        
    }
    
    public  static void main(String[] args){
        updatePromStatusQuartzJob job=new updatePromStatusQuartzJob();
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

