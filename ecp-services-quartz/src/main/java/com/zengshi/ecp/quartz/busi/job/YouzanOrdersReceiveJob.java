package com.zengshi.ecp.quartz.busi.job;

import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.unpf.dubbo.dto.order.RYouzanOrderReq;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IYouzanSynOrdersRSV;
import com.zengshi.paas.utils.LogUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.DisallowConcurrentExecution;

import java.util.Date;
import java.util.Map;

/**
 * Created by guojingman on 2017/2/24.
 * 从有赞批量获取订单数据，同步保存到统一平台定时任务
 */
@DisallowConcurrentExecution
public class YouzanOrdersReceiveJob extends AbstractCommonQuartzJob {
    private static final String MODULE = OrderAutoReceivingJob.class.getName();

    private IYouzanSynOrdersRSV ordersRSV = QuartzContextHolder.getBean(IYouzanSynOrdersRSV.class);
    /**
     * 页大小
     */
    private static final int PAGE_SIZE = 30;

    @Override
    protected String getModule() {
        return MODULE;
    }

    @Override
    protected void doJob(Map<String, String> map) throws Exception {
        LogUtil.info(MODULE, "=============begin YouzanOrdersReceiveJob job =============");
        try {
            RYouzanOrderReq req = new RYouzanOrderReq();
            //查询24小时内订单数据
            Date nowDate = new Date();
            Date yesterday = DateUtils.addDays(nowDate, -1);
            req.setStartUpdate(yesterday);
            req.setEndUpdate(nowDate);
            req.setPageNo(1);
            req.setPageSize(PAGE_SIZE);
            ordersRSV.saveOrdersFromYouzan(req);
        } catch (Exception e) {
            LogUtil.error(MODULE, "执行有赞批量同步订单异常", e);
        }

        LogUtil.info(MODULE, "=============end YouzanOrdersReceiveJob job =============");
    }


}
