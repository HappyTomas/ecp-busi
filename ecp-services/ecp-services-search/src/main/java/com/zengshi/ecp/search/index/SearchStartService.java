package com.zengshi.ecp.search.index;

import com.zengshi.ecp.search.dubbo.search.score.valuesource.DateValueSourceParserParam;
import com.zengshi.ecp.search.dubbo.search.score.valuesource.ScoreUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.index.delta.DeltaIndexManager;
import com.zengshi.paas.utils.LogUtil;

import java.util.HashMap;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月21日下午8:12:48  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SearchStartService {
    
    private final static String MODULE = "【搜索引擎】SearchStartService";
    
    public void start(){
        
        LogUtil.info(MODULE,"添加正常退出资源销毁钩子！");
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

        //由于暂时没提供数据库配置和Zookeeper节点配置双向映射，暂时不提供从数据库初始化功能，以免造成重启后覆盖已有配置的问题
        /*LogUtil.info(MODULE,"初始化时间衰减配置到Zookeeper！");
        try{

            HashMap<String,String> secArgsMap=SearchCacheUtils.getSecArgsMap();
            DateValueSourceParserParam dateValueSourceParserParam=new DateValueSourceParserParam();
            dateValueSourceParserParam.setDemoteboostday(Float.parseFloat(secArgsMap.get("DEMOTEBOOSTDAY")));
            dateValueSourceParserParam.setDemoteboostweek(Float.parseFloat(secArgsMap.get("DEMOTEBOOSTWEEK")));
            dateValueSourceParserParam.setDemoteboostmonth(Float.parseFloat(secArgsMap.get("DEMOTEBOOSTMONTH")));
            dateValueSourceParserParam.setDemoteboostyear(Float.parseFloat(secArgsMap.get("DEMOTEBOOSTYEAR")));
            dateValueSourceParserParam.setIfDemoteRangeDay(Boolean.parseBoolean(secArgsMap.get("IFDEMOTERANGEDAY")));
            dateValueSourceParserParam.setIfDemoteRangeFuture(Boolean.parseBoolean(secArgsMap.get("IFDEMOTERANGEFUTURE")));
            dateValueSourceParserParam.setIfRemoteRangeHour(Boolean.parseBoolean(secArgsMap.get("IFREMOTERANGEHOUR")));
            dateValueSourceParserParam.setRemoteBoost(Float.parseFloat(secArgsMap.get("REMOTEBOOST")));
            SearchCacheUtils.setZkData(ScoreUtils.ZK_SCORE_DATE_PATH,com.alibaba.fastjson.JSON.toJSONString(dateValueSourceParserParam));
        }catch (Exception e){
            LogUtil.error(MODULE,"初始化时间衰减配置到Zookeeper失败！",e);
        }*/
    }
    
    public class ShutdownHook extends Thread{
        
        @Override
        public void run() {
            IndexManager.shutdown();
            
            DeltaIndexManager.shutdown();
        }

    }

}

