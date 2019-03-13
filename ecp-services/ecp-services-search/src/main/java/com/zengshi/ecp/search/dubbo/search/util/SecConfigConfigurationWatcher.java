package com.zengshi.ecp.search.dubbo.search.util;

import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.paas.ConfigurationCenter;
import com.zengshi.paas.ConfigurationWatcher;
import com.zengshi.paas.PaasException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.PaasContextHolder;

/**
 * Created by HDF on 2016/11/22.
 */
public class SecConfigConfigurationWatcher implements ConfigurationWatcher {

    public final static String MODULE = "【搜索引擎】SecConfigConfigurationWatcher";

    public final static String CONFPATH = "/ecp/search/config";

    private ConfigurationCenter cc = null;

    //Zookeeper节点数据，0:已变更，1：未变更。
    //private String data;

    public SecConfigConfigurationWatcher(){

    }

    public void init(){
        try {
            this.cc= PaasContextHolder.getContext().getBean(ConfigurationCenter.class);
            this.process(this.cc.getConfAndWatch(this.CONFPATH, this));
            LogUtil.info(MODULE, "Watcher注册成功，cc.getConfAndWatch()成功");
        } catch (PaasException e) {
            LogUtil.error(MODULE, "Watcher注册失败，cc.getConfAndWatch()异常", e);
        }
    }

    @Override
    public void process(String s) {

        //节点数据发生变更，则直接清空索引配置静态缓存（注意了Redis分布式缓存清空不放在Watchers处理（重复处理多次），放在数据变更处统一发起）
        SearchCacheUtils.cleanLocale();

        //清空SolrServer缓存
        SearchUtils.cleanSolrSeverCache();

        LogUtil.info(MODULE, "Watcher触发执行成功");
    }
}
