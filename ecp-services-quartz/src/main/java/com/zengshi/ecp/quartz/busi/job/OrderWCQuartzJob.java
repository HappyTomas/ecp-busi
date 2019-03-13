package com.zengshi.ecp.quartz.busi.job;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.DisallowConcurrentExecution;

import com.zengshi.ecp.order.dubbo.dto.WOrderCountInfo;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderWorkBrancRSV;
import com.zengshi.ecp.quartz.QuartzContextHolder;
import com.zengshi.ecp.quartz.busi.common.AbstractCommonQuartzJob;
import com.zengshi.ecp.quartz.ex.JobBusinessException;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IReportItemRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

import scala.util.parsing.combinator.testing.Str;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-quartz <br>
 * Description: <br>
 * Date:2016年5月20日上午10:32:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 订单域 定时数据统计
 */
@DisallowConcurrentExecution
public class OrderWCQuartzJob extends AbstractCommonQuartzJob{

    private static final String MODULE = OrderWCQuartzJob.class.getName();
    
    private IOrderWorkBrancRSV orderworkBrancRSV = QuartzContextHolder.getBean(IOrderWorkBrancRSV.class);
    
    private IShopCacheRSV shopCacheRSV = QuartzContextHolder.getBean(IShopCacheRSV.class);
    
    private IReportItemRSV reportItemRSV = QuartzContextHolder.getBean(IReportItemRSV.class);
    
    @Override
    protected void doJob(Map<String, String> extendParams) throws JobBusinessException {
        //统计日期
        Timestamp collectDate = null;
        
        if (extendParams != null && extendParams.size() > 0) {
            //页面有传入参，根据参数执行统计动作
            String caclDateString = extendParams.get("caclDate");

            if (!DateUtil.isValidDate(caclDateString, DateUtil.DATE_FORMAT)) {
                LogUtil.error(MODULE, "传入时间不符合格式："+DateUtil.DATE_FORMAT);
                return;
            }
            collectDate = DateUtil.getTimestamp(caclDateString, DateUtil.DATE_FORMAT);
            
            LogUtil.info(MODULE, "设定统计日期"+collectDate.toString());
        }
        else
        {
            //2.统计昨天的订单数据
            //2.1获取昨天的日期
            collectDate = DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -1);
            //2.2去掉时分秒
            collectDate = DateUtil.getTimestamp(DateUtil.getDateString(collectDate, DateUtil.DATE_FORMAT), DateUtil.DATE_FORMAT);
            
        }

        LogUtil.info(MODULE, "订单域每日交易数据统计定时任务 开始...");
        
        long start = DateUtil.getCurrentTimeMillis();
        //1.取平台所有店铺列表,店铺数量多的情况下，改造成使用多线程统计
        Map<Long, ShopInfoResDTO> mapShops = shopCacheRSV.getCacheShop();
        //平台统计数据
        WOrderCountInfo platOrderCountInfo = new WOrderCountInfo();

        
        for(ShopInfoResDTO shop :  mapShops.values())
        {
            //店铺失效，则不统计
            if ("0".equals(shop.getShopStatus())) {
                continue;
            }
            

            LogUtil.info(MODULE, "订单域统计店铺["+shop.getId()+"]交易数据"+collectDate.toString()+"开始");
            
            WOrderCountInfo countInfo = orderworkBrancRSV.collectOrder(shop.getId(), collectDate);
            addCountInfoToReport(shop.getId(), shop.getShopName(),countInfo,collectDate);
            platOrderCountInfo.addCountInfo(countInfo);
            LogUtil.info(MODULE, "订单域统计店铺["+shop.getId()+"]交易数据"+collectDate.toString()+"结束");
        }
        addCountInfoToReport(-1L, "平台统计", platOrderCountInfo, collectDate);
        long end = DateUtil.getCurrentTimeMillis();
        LogUtil.info(MODULE, "订单域每日交易数据统计定时任务 完成...耗时"+(end-start)+"ms");
     
        
    }

    private void addCountInfoToReport(Long shopId, String shopName, WOrderCountInfo countInfo, Timestamp collectDate)
    {
        addToReport("ITEM_ORD_SUM_MONEY", String.valueOf(countInfo.getAllOrderMoney()), "订单额", shopId, shopName, countInfo.getDataSource(), collectDate);
        addToReport("ITEM_ORD_SALE_MONEY", String.valueOf(countInfo.getAllOrderSellMoney()), "销售额", shopId, shopName, countInfo.getDataSource(), collectDate);
        addToReport("ITEM_ORD_SUM_COUNT", String.valueOf(countInfo.getAllOrderCount()), "订单总量", shopId, shopName, countInfo.getDataSource(), collectDate);
        addToReport("ITEM_ORD_PAY_COUNT", String.valueOf(countInfo.getAllPayedCount()), "支付成功量", shopId, shopName, countInfo.getDataSource(), collectDate);
        addToReport("ITEM_ORD_SUCESS_RATE", String.valueOf(countInfo.getPlacePrecent()), "下单成功率", shopId, shopName, countInfo.getDataSource(), collectDate);
  
    }
    
    private void addToReport(String itemCode, String itemValue, String itemDesc,Long shopId, String shopName, String itemSource, Timestamp calDate) {
        ReportItemReqDTO record = new ReportItemReqDTO();
        record.setItemCode(itemCode);
        record.setItemDesc(shopName+"["+shopId+"]-"+DateUtil.getDateString(calDate, DateUtil.YYYYMMDD)+"-"+itemDesc);
        record.setItemValue(itemValue);
        record.setShopId(shopId);
        record.setItemSource(itemSource);
        record.setCalDate(calDate);
        
        try {
            reportItemRSV.addReportItem(record);
        } catch (BusinessException e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "店铺["+shopId+"]"+"添加统计项["+itemCode+"]"+"到统计表中出现异常");
            try {
                reportItemRSV.delReportItem(record);
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
            reportItemRSV.addReportItem(record);   
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    @Override
    protected String getModule() {
        return MODULE;
    }

    public static void main(String[] args) throws JobBusinessException{
        
        OrderWCQuartzJob job = new OrderWCQuartzJob();
        
        job.doJob(null);
    }
}

