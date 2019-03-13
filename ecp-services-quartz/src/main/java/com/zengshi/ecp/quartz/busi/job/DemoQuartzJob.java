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

import javax.annotation.Resource;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.demo.dubbo.dto.DemoInfoDTO;
import com.zengshi.ecp.demo.dubbo.interfaces.IDemoInfoRSV;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
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
public class DemoQuartzJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = DemoQuartzJob.class.getName();
    
    private IDemoInfoRSV demoInfoRSV = QuartzContextHolder.getBean(IDemoInfoRSV.class);

    @Override
    protected String getModule() {
        return MODULE;
    }

    /** 
     * 调用测试
     * @throws JobExecutionException 
     * @see com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob#doJob(java.util.Map) 
     */
    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        LogUtil.info(MODULE, "=============begin demo job =============");
        try{
            Thread.sleep(10*1000);
            DemoInfoDTO dto = new DemoInfoDTO();
            dto.setId(1L);
            DemoInfoDTO demoInfoDTO = demoInfoRSV.fetchDemoInfoById(dto);
            if(demoInfoDTO == null){
                LogUtil.info(MODULE, "未获取到编码为1的数据");
            } else {
                LogUtil.info(MODULE, demoInfoDTO.getId()+";"+demoInfoDTO.getName());
            }
        } catch(Exception err){
            LogUtil.error(MODULE,"=============error =============",err);
        }
        LogUtil.info(MODULE,"=============end demo job =============");
    }

}

