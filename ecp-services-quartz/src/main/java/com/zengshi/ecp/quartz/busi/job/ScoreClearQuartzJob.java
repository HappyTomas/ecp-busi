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
import com.zengshi.ecp.staff.dubbo.dto.ScoreClearReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:quartz-services <br>
 * Description: 超过有效期的积分 定时清零任务<br>
 * Date:2015-11-24下午7:52:44  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@DisallowConcurrentExecution
public class ScoreClearQuartzJob extends AbstractCommonQuartzJob {
    
    private static final String MODULE = ScoreClearQuartzJob.class.getName();
    
    private IScoreInfoRSV scoreInfoRSV = QuartzContextHolder.getBean(IScoreInfoRSV.class);

    @Override
    protected String getModule() {
        return MODULE;
    }

    /**
     * 
     * 积分超过有效期定时清零任务 
     * @see com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob#doJob(java.util.Map)
     */
    @Override
    protected void doJob(Map<String, String> extendParams) throws JobExecutionException {
        LogUtil.info(MODULE, "=============begin 超过有效期的积分清零任务开始  job =============");
        /*1、参数有效性校验*/
        boolean flag = true;//参数有效标识
        //调度界面入参
        if (extendParams == null) {
            flag = false;
            LogUtil.info(MODULE, "未获取页面传入的参数。");
        } 
        //起始员工id
        if (StringUtil.isBlank(extendParams.get("staffIdStart"))) {
            flag = false;
            LogUtil.info(MODULE, "未获取页面传入的参数：起始员工id");
        }
        //截止员工id
        if (StringUtil.isBlank(extendParams.get("staffIdEnd"))) {
            flag = false;
            LogUtil.info(MODULE, "未获取页面传入的参数：截止员工id");
        }
        //积分有效截止时间，取小于，入参可用：实际截止日期 +1 来入参
        if (StringUtil.isBlank(extendParams.get("invalidDate"))) {
            flag = false;
            LogUtil.info(MODULE, "未获取页面传入的参数：积分有效截止时间");
        }
        try{
            /*2、参数有效，进行业务逻辑处理*/
            if (flag) {
                long staffIdStart = Long.parseLong(extendParams.get("staffIdStart"));//起始员工id
                long staffIdEnd = Long.parseLong(extendParams.get("staffIdEnd"));//截止员工id
                String invalidDate = extendParams.get("invalidDate");//积分有效截止日期
                for (long i = staffIdStart; i < staffIdEnd + 1; i++) {
                    ScoreClearReqDTO req = new ScoreClearReqDTO();
                    req.setStaffId(i);
                    req.setDeadLineTime(DateUtil.getTimestamp(invalidDate));
                    //调用积分清零方法
                    try {
                        scoreInfoRSV.saveScoreClear(req);
                    } catch (Exception err) {
                        LogUtil.error(MODULE,"=============超过有效期的积分清零任务  业务处理异常,用户id:"+i+",有效期截止日期"+invalidDate+" =============",err);
                    }
                }
            } 
        } catch(Exception err){
            LogUtil.error(MODULE,"=============超过有效期的积分清零任务  业务处理异常 =============",err);
        }
        LogUtil.info(MODULE,"=============end 超过有效期的积分清零任务结束 job =============");
    }

}

