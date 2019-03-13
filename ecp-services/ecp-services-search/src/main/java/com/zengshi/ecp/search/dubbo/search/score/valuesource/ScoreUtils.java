package com.zengshi.ecp.search.dubbo.search.score.valuesource;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by HDF on 2016/12/15.
 */
public class ScoreUtils {

    private final static Logger logger = LoggerFactory.getLogger(ScoreUtils.class);

    public final static String MODULE="【ECP-搜索引擎】ScoreUtils：";

    private static  ZooKeeper zookeeper;

    public final static String ZK_SCORE_DATE_PATH="/ecp/search/score/date";

    private static final int CONNECTION_TIMEOUT = 30000;

    //是否正在更新配置信息（Zookeeper监听触发）
    private static boolean ifUpdateing=false;

    /*===============================时间衰减系数=================================*/

    //是否启用时间衰减系数
    private static boolean ifDemoteRangeDay=true;

    //时间衰减处理的时间范围（默认一个月30=31-1）//TODO 注意了，考虑到加锁代价，范围值不允许动态修改
    //private static int demoteRangeDay =31;
    private static int demoteRangeDay =365;

    //按天衰减系数数组，数组下标为距离当天的天数
    private static float[] daysDampingFactor = new float[demoteRangeDay];

    //降级权重因子
    private static float demoteBoost = 0.95f;
    private static float demoteBoostDay = demoteBoost;
    private static float demoteBoostWeek = demoteBoost;
    private static float demoteBoostMonth = demoteBoost;
    private static float demoteBoostYear = demoteBoost;

    //对未来时间是否启用时间衰减系数
    private static boolean ifDemoteRangeFuture=false;

    //未来时间的权重因子（默认时间是未来则因素值设置为最小）
    private static float factorFuture =Float.MIN_VALUE;

    /*===============================时间提升系数(24小时内)=================================*/

    //是否启用时间提升系数(24小时内)//慎用
    private static boolean ifRemoteRangeHour=true;

    //时间提升处理的时间范围（23=24-1，默认23小时，最大也为23小时）//注意了，考虑到加锁代价，范围值不允许动态修改
    private static int remoteRangeHour =24;

    //按小时提升系数数组，数组下标为距离当时小时数
    private static float[] hourssRemotingFactor = new float[remoteRangeHour];

    //提升权重因子
    private static float remoteBoost = 1.03f;

    private static void init() {

        Long startMills=System.currentTimeMillis();

        /*===============================时间提升系数=================================*/

        //距离当时23小时，则因子为1（起始因子）
        hourssRemotingFactor[remoteRangeHour-1] = 1;

        //24小时内
        for (int i = remoteRangeHour-2; i >=0 ; i--) {
            //基于前一天的因子降级
            hourssRemotingFactor[i] = hourssRemotingFactor[i + 1] * remoteBoost;
        }

        /*===============================时间衰减系数=================================*/

        //当天，则因子为1
        daysDampingFactor[0] = 1;

        //一周内
        for (int i = 1; i < 7&&i<demoteRangeDay; i++) {
            //基于前一天的因子降级
            daysDampingFactor[i] = daysDampingFactor[i - 1] * demoteBoostDay;
        }

        //一周到一个月
        for (int i = 7; i < 31&&i<demoteRangeDay; i++) {
            //基于前一周的因子降级
            daysDampingFactor[i] = daysDampingFactor[i / 7 * 7 - 1]
                    * demoteBoostWeek;
        }

        //大于一个月
        for (int i = 31; i < 365&&i<demoteRangeDay; i++) {
            //基于前一月的因子降级
            daysDampingFactor[i] = daysDampingFactor[i / 31 * 31 - 1]
                    * demoteBoostMonth;
        }

        //大于一年
        for (int i = 365; i < demoteRangeDay; i++) {
            //基于前一月的因子降级
            daysDampingFactor[i] = daysDampingFactor[i / 365 * 365 - 1]
                    * demoteBoostYear;
        }

        Long endMills=System.currentTimeMillis();
        logger.error(MODULE+"时间衰减参数配置更新耗时："+(endMills-startMills));
    }

    static{

        //数据初始化，防止空指针
        init();

        //创建Zookeeper监听
        //创建一个与服务器的连接
        //无论是哪种SOLR部署模式都需要配置虚拟机参数zkHost或ecpSecZkHost
        //-DzkHost=192.168.170.27:12181,192.168.170.28:12181,192.168.170.8:12181"/-DecpSecZkHost=192.168.170.27:12181,192.168.170.28:12181,192.168.170.8:12181"
        //1、CLOUD模式
        String zkHost=System.getProperty("zkHost");
        if(zkHost==null||zkHost.equals("")){
            //2、非CLOUD模式，发现非CLOUD模式配置了zkHost会导致部署模式变成CLOUD模式
            zkHost=System.getProperty("ecpSecZkHost");
        }
        if(zkHost!=null&&!zkHost.equals("")){
            try {

                //System.out.println(MODULE+"zkHost："+zkHost);
                logger.error(MODULE+"连接的zkHost是："+zkHost);

                zookeeper = new ZooKeeper(zkHost,
                        CONNECTION_TIMEOUT, new Watcher() {
                    // 监控所有被触发的事件
                    public void process(WatchedEvent event) {
                        //System.out.println(MODULE+"已经触发了" + event.getType() + "事件！具体信息："+event);
                        logger.error(MODULE+"已经触发了" + event.getType() + "事件！具体信息："+event);
                        if(Event.EventType.NodeDataChanged.equals(event.getType())) {
                            String path = event.getPath();
                            if(ZK_SCORE_DATE_PATH.equals(path)){

                                String data=getConf(path);
                                if(data==null||"".equals(data)){
                                    //System.out.println(MODULE+"获取到的节点数据为空！节点路径："+path);
                                    logger.error(MODULE+"获取到的节点数据为空！节点路径："+path);
                                    return;
                                }

                                update(data);
                            }
                        }
                    }
                });
            } catch (IOException e) {
                //System.out.println(MODULE+"创建ZooKeeper监听失败！zkHost："+zkHost);
                logger.error(MODULE+"创建ZooKeeper监听失败！zkHost："+zkHost,e);
                e.printStackTrace();
            }
        }else{
            //System.out.println(MODULE+"请在配置虚拟机参数zkHost/ecpSecZkHost，无法通过虚拟机参数获取到ZK地址信息，从而更新date score配置信息");
            logger.error(MODULE+"请在配置虚拟机参数zkHost/ecpSecZkHost，无法通过虚拟机参数获取到ZK地址信息，从而更新date score配置信息");
        }

        //并且注意了，在创建Zookeeper实例之后需要先执行一次getConf来达到监听的目的//同时也达到从Zookeeper初始化配置的目的
        String data=getConf(ZK_SCORE_DATE_PATH);
        if(data==null||"".equals(data)){
            //System.out.println(MODULE+"获取到的节点数据为空！节点路径："+path);
            logger.error(MODULE+"获取到的节点数据为空！节点路径："+ZK_SCORE_DATE_PATH);
        }else{
            update(data);
        }
    }

    private static void update(String data){
        DateValueSourceParserParam dateValueSourceParserParam=null;
        try {
            dateValueSourceParserParam = JSON.parseObject(data,
                    DateValueSourceParserParam.class);
        }catch (Exception e){
            //System.out.println(MODULE+"节点数据反序列化异常！数据："+data);
            logger.error(MODULE+"节点数据反序列化异常！数据："+data,e);
            e.printStackTrace();
            return;
        }

        if(dateValueSourceParserParam==null){
            //System.out.println(MODULE+"节点数据反序列化后对象为空！数据："+data);
            logger.error(MODULE+"节点数据反序列化后对象为空！数据："+data);
            return;
        }

        //上次触发还没更新完则不再触发
        if(ifUpdateing){
            return;
        }

        ifUpdateing=true;

        //System.out.println(MODULE+"开始更新时间衰减参数配置！参数数据："+data);
        logger.error(MODULE+"开始更新时间衰减参数配置！参数数据："+data);

        //静态参数重新初始化
        ifDemoteRangeDay=dateValueSourceParserParam.isIfDemoteRangeDay();
        ifDemoteRangeFuture=dateValueSourceParserParam.isIfDemoteRangeFuture();
        ifRemoteRangeHour=dateValueSourceParserParam.isIfRemoteRangeHour();
        demoteBoostDay=dateValueSourceParserParam.getDemoteboostday();
        demoteBoostWeek=dateValueSourceParserParam.getDemoteboostweek();
        demoteBoostMonth=dateValueSourceParserParam.getDemoteboostmonth();
        demoteBoostYear=dateValueSourceParserParam.getDemoteboostyear();
        factorFuture=dateValueSourceParserParam.getFactorFuture();
        remoteBoost=dateValueSourceParserParam.getRemoteBoost();

        //重新初始化评分因子数组
        init();

        //System.out.println(MODULE+"时间衰减参数配置更新完毕！参数数据："+data);
        logger.error(MODULE+"时间衰减参数配置更新完毕！参数数据："+data);

        ifUpdateing=false;
    }

    /**
     * 注意了，由于Zookeeper设计的监听都是一次听监听，因此exists和getData都设置成要监听该节点
     * 并且注意了，在创建Zookeeper实例之后需要先执行一次getConf来达到监听的目的//同时也达到从Zookeeper初始化配置的目的
     * @param confPath
     * @return
     */
    private static String getConf(String confPath) {
        String conf = null;
        if(zookeeper==null){
            logger.error(MODULE+"Zookeeper对象为空");
            return conf;
        }
        try {
            Stat stat=zookeeper.exists(confPath,true);
            conf = new String(zookeeper.getData(confPath, true, stat));
        } catch (Exception e) {
            //System.out.println(MODULE+"获取ZK节点数据失败！节点路径："+confPath);
            logger.error(MODULE+"获取ZK节点数据失败！节点路径："+confPath,e);
            e.printStackTrace();
        }
        return conf;
    }

    private static float dayDamping(int delta) {

        //若时间差在处理范围之内则根据数组下标获取到因子值做处理，否则以最后一个系数值处理
        return delta < demoteRangeDay ? daysDampingFactor[delta]
                : daysDampingFactor[demoteRangeDay - 1];
    }

    private static float hourRemoting(int delta) {

        //若时间差在处理范围之内则根据数组下标获取到因子值做处理，否则以最后一个系数值处理
        return delta < remoteRangeHour ? hourssRemotingFactor[delta]
                : hourssRemotingFactor[remoteRangeHour - 1];
    }

    //now应该由外部生成统一值
    //检索语句示例：
    // 1、{!boost b=dateValueSourceParser()}title:哈哈 keyword:哈哈
    // 2、{!boost b=dateFieldCacheSourceParser()}title:哈哈 keyword:哈哈
    //{!boost b=dateFieldCacheSourceParser(publishDate_date_sv_none)}gdsName_textcn_sv_spellcheck_df:习近平
    public static float getNewsScoreFactor(long now, long time,String args) {

        //默认因子值为1，即无时间衰减影响
        float factor = 1;

        //正在更新配置信息（Zookeeper监听触发）直接返回1
        /*if(ifUpdateing){
            return factor;
        }*/

        //这样的计算方式对于24小时内的时间递增处理会有影响
        /*int day = (int) (time / MiscConstants.DAY_MILLIS);
        int nowDay = (int) (now / MiscConstants.DAY_MILLIS);*/

        //新的计算方式
        long deltaMills=now-time;
        int deltaDay= (int) (deltaMills/MiscConstants.DAY_MILLIS);

        //1、数天之前
        if (deltaDay>0) {
            if(ifDemoteRangeDay){
                factor = dayDamping(deltaDay);
            }
        } else if (deltaDay<0) {

            //2、时间是未来则因素值设置为最小
            if(ifDemoteRangeFuture){
                factor = factorFuture;
            }
        }else{
            if(ifRemoteRangeHour){
                int hours=(int)((now - time)/MiscConstants.HOUR_MILLIS);
                factor=hourRemoting(hours);
            }
        }

        //System.out.println(MODULE+"接收到检索DateValueSourceParser参数args："+args+"，返回的因子值factor为："+factor);
        logger.info(MODULE+"接收到检索DateValueSourceParser参数args："+args+"，返回的因子值factor为："+factor);

        return factor;
    }

    //now应该由外部生成统一值
    public static float getNewsScoreFactor(long now,long time) {
        return getNewsScoreFactor(now,time,null);
    }
}

class MiscConstants {
    /**
     * 24x60x60x1000
     */
    public static final long DAY_MILLIS = 86400000;
    /**
     * 24x60x60x1000
     */
    public static final long DAY_SECONDS = 86400;
    /**
     * 60x1000
     */
    public static final int MINUTE_MILLIS = 60000;
    /**
     * 1x60x60x1000
     */
    public static final int HOUR_MILLIS = 3600000;
    /**
     * 0.5x60x60x1000
     */
    public static final int HALF_HOUR_MILLIS = 1800000;
    /**
     * 60
     */
    public static final int MINUTE_SECONDS = 60;
}
