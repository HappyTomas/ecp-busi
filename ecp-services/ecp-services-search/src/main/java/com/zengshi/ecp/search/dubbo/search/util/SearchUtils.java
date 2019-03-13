package com.zengshi.ecp.search.dubbo.search.util;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.*;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.DateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUtils {

    public final static String MODULE = "【搜索引擎】SearchUtils";
    
    private static final int ZK_CLIENT_TIMEOUT = Integer.MAX_VALUE;
    private static final int ZK_CONNECT_TIMEOUT = Integer.MAX_VALUE;
    private static Map<String, SolrServer> readServerMap = new HashMap<String, SolrServer>();
    private static Map<String, SolrServer> writeServerMap = new HashMap<String, SolrServer>();

    public static void cleanSolrSeverCache(){
        readServerMap = new HashMap<String, SolrServer>();
        writeServerMap = new HashMap<String, SolrServer>();
    }
    
    /**
     * 多用于查询操作，只用于单节点查询
     * @param collectionName
     * @param serverInfo
     * @return
     * @throws BusinessException
     */
    public static SolrServer createHttpSolrServer(final String collectionName,final String serverInfo) throws BusinessException {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 128);
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 32);
        params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false);
        params.set(HttpClientUtil.PROP_CONNECTION_TIMEOUT, Integer.MAX_VALUE);
        params.set(HttpClientUtil.PROP_SO_TIMEOUT, Integer.MAX_VALUE);
        params.set(HttpClientUtil.PROP_USE_RETRY, true);
        HttpClient httpClient =  HttpClientUtil.createClient(params);
        SolrServer solrServer = new HttpSolrServer("http://"+serverInfo+"/solr/"+collectionName,httpClient);
        return solrServer;
        
    }
    
    /**
     * 多用于更新删除索引操作，只用于单节点更新
     * @param collectionName
     * @param masterServer
     * @return
     * @throws BusinessException
     */
    public static SolrServer createConcurrentUpdateSolrServer(final String collectionName,final String masterServer) throws BusinessException {
        
        SolrServer solrServer = new ConcurrentUpdateSolrServer("http://"+masterServer+"/solr/"+collectionName, 100, 10);
        return solrServer;
        
    }
    
    /**
     * 用于有多个Solr服务器，负载均衡
     * @return
     * @throws BusinessException
     */
    public static SolrServer createLBHttpSolrServer(final String[] solrServerUrls) throws BusinessException {
        
        SolrServer solrServer = null; 
        try {
            solrServer = new LBHttpSolrServer(solrServerUrls);
        } catch (MalformedURLException e) {
            LogUtil.error(MODULE, "LBHttpSolrServer创建异常", e);
            throw new BusinessException(
                    SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{solrServerUrls.toString(),SearchUtils.getExceptionMessage(e)});
        }
        return solrServer;
    }

    /**
     * 用于cloud模式
     * @param collectionName
     * @return
     * @throws BusinessException
     */
    public static SolrServer createCloudSolrServer(final String collectionName) throws BusinessException {

        CloudSolrServer cloudSolrServer = null;
        try {

            /*DefaultMaxPerRoute就是CloudSolrServer中默认的maxConnectionsPerHost，MaxTotal就是maxTotalConnection。
            maxConnectionsPerHost是指针对一个目标服务器，httpclient客户端可以和它建立的并发连接数。之前的版本是2，现在是5。
            maxTotalConnections是指针对服务器集群，client可以保持的所有连接数，即同一客户端同时可连接多个目标主机。现在是
            maxConnectionsPerHost的2倍。*/
            ModifiableSolrParams params = new ModifiableSolrParams();
            params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 1000);//10
            params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 500);//5
            params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false);
            params.set(HttpClientUtil.PROP_CONNECTION_TIMEOUT, Integer.MAX_VALUE);
            params.set(HttpClientUtil.PROP_SO_TIMEOUT, Integer.MAX_VALUE);
            params.set(HttpClientUtil.PROP_USE_RETRY, true);
            HttpClient client = HttpClientUtil.createClient(params);
            LBHttpSolrServer lbServer = new LBHttpSolrServer(client);

            cloudSolrServer = new CloudSolrServer(SearchCacheUtils.getSecZkStr(),lbServer);
            cloudSolrServer.setDefaultCollection(collectionName);
            cloudSolrServer.setZkClientTimeout(ZK_CLIENT_TIMEOUT);
            cloudSolrServer.setZkConnectTimeout(ZK_CONNECT_TIMEOUT);
            cloudSolrServer.connect();
        } catch (Exception e) {
            LogUtil.error(MODULE, "CloudSolrServer创建异常", e);
            throw new BusinessException(
                    SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{collectionName,SearchUtils.getExceptionMessage(e)});
        }

        return cloudSolrServer;
    }
    
    public static SolrServer getSolrServer(final String collectionName,boolean readMode) throws BusinessException {
        
//        if(StringUtils.isBlank(collectionName)){
//            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{collectionName,"collectionName empty"});
//        }
        
        Map<String,String> args=SearchCacheUtils.getSecArgsMap();
        
        SolrServer solrServer=null;
        
        //判断部署模式
        if(StringUtils.equals(args.get("DEPLOY_TYPE"),SearchConstants.DeployType.STANDALONE)){//单点模式
            
            if(readMode){
                if(readServerMap.containsKey(collectionName)){
                    return readServerMap.get(collectionName);
                }
                List<String> serverList;
                try {
                    serverList = SearchCacheUtils.getSecSolrServerList();
                } catch (SearchException e) {
                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
                }
                
                if(serverList.size()>1){
                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{collectionName,"部署模式是单点模式，但是有多个节点配置记录"});
                }
                solrServer=createHttpSolrServer(collectionName, serverList.get(0));
                readServerMap.put(collectionName, solrServer);
                return solrServer;
            }else{
                if(writeServerMap.containsKey(collectionName)){
                    return writeServerMap.get(collectionName);
                }
                List<String> serverList;
                try {
                    serverList = SearchCacheUtils.getSecSolrServerList();
                } catch (SearchException e) {
                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
                }
                
                if(serverList.size()>1){
                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{collectionName,"部署模式是单点模式，但是有多个节点配置记录"});
                }
                solrServer=createConcurrentUpdateSolrServer(collectionName, serverList.get(0));
                writeServerMap.put(collectionName, solrServer);
                return solrServer;
            }
            
        }else if(StringUtils.equals(args.get("DEPLOY_TYPE"),SearchConstants.DeployType.REPLICATION)){//主从模式（副本模式）
            if(readMode){
                if(readServerMap.containsKey(collectionName)){
                    return readServerMap.get(collectionName);
                }
                List<String> serverList;
                try {
                    serverList = SearchCacheUtils.getSecSolrServerList();
                } catch (SearchException e) {
                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
                }
                
                String[] solrServerUrls=new String[serverList.size()];
                for(int i=0;i<serverList.size();i++){
                    solrServerUrls[i]="http://"+serverList.get(i)+"/solr/"+collectionName;
                }
                solrServer = createLBHttpSolrServer(solrServerUrls);
                readServerMap.put(collectionName, solrServer);
                return solrServer;
            }else{
                if(writeServerMap.containsKey(collectionName)){
                    return writeServerMap.get(collectionName);
                }
                String masterServer;
                try {
                    masterServer = SearchCacheUtils.getSecMasterSolrServer();
                } catch (SearchException e) {
                    throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRSERVER_CREATE,new String[]{collectionName,"部署模式是主从模式，但是没有主节点配置记录"});
                }
                solrServer=createConcurrentUpdateSolrServer(collectionName,masterServer);
                writeServerMap.put(collectionName, solrServer);
                return solrServer;
            }
        }else if(StringUtils.equals(args.get("DEPLOY_TYPE"),SearchConstants.DeployType.CLOUD)){//CLOUD模式
            if(writeServerMap.containsKey(collectionName)){
                return writeServerMap.get(collectionName);
            }
            solrServer=createCloudSolrServer(collectionName);
            writeServerMap.put(collectionName, solrServer);
            return solrServer;
        }
        
        throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_DEPLOYTYPE,new String[]
                {args.get("DEPLOY_TYPE")});
        
    }

    public static String createCollection(String collectionName) throws BusinessException{
        
        if(StringUtils.isBlank(collectionName)){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_CREATE,new String[]{collectionName,"collectionName empty"});
        }
        
        Map<String,String> args=SearchCacheUtils.getSecArgsMap();
        LogUtil.info("【搜索引擎】创建集合", "DEPLOY_TYPE："+args.get("DEPLOY_TYPE"));
        
        //判断部署模式
        if(StringUtils.equals(args.get("DEPLOY_TYPE"), SearchConstants.DeployType.CLOUD)){//CLOUD模式
            
            List<String> serverList;
            try {
                serverList = SearchCacheUtils.getSecSolrServerList();
            } catch (SearchException e) {
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
            }
            
            int shardNum=0;
            int repFactor=0;
            int maxShardsPerNode=0;
            
            //控制参数格式校验
            try{
                shardNum=Integer.parseInt(args.get("SHARD_NUM"));
                repFactor=Integer.parseInt(args.get("REPLICATION_FACTOR"));
                maxShardsPerNode=Integer.parseInt(args.get("MAXSHARDSPERNODE"));
            }catch(Exception e){
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_CREATE,new String[]{collectionName,SearchUtils.getExceptionMessage(e)});
            }
            
            //控制参数有效性校验
            if(shardNum<1||repFactor<1||maxShardsPerNode<1){
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_CREATE,new String[]{collectionName,"shardNum<1||repFactor<1||maxShardsPerNode<1"});
            }
            
            //认为一个节点上做多个分片没有意义
            if(serverList.size()<shardNum){
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_CREATE,new String[]{collectionName,"server num<shardNum"});
            }
            
            try{
                
                /*---------------------------方式1：自动分配shard和replica-----------------------------*/
                //创建主数据集合和分片
                String url="http://"+serverList.get(0)+"/solr/admin/collections?action=CREATE&name="+collectionName+"&numShards="+shardNum+"&replicationFactor="+repFactor+"&maxShardsPerNode="+maxShardsPerNode+"&collection="+collectionName;
                LogUtil.info("【搜索引擎】创建主数据集合和分片", url);
                String ret1=getHttpRequest(url);
                LogUtil.info("【搜索引擎】创建主数据集合和分片", ret1);
                
                //创建suggestion集合
                collectionName+=SearchConstants.COLLECTION_SUGGEST_SUFFIX;
                url="http://"+serverList.get(0)+"/solr/admin/collections?action=CREATE&name="+collectionName+"&numShards="+shardNum+"&replicationFactor="+repFactor+"&maxShardsPerNode="+maxShardsPerNode+"&collection="+collectionName;
                LogUtil.info("【搜索引擎】创建suggestion数据集合和分片", url);
                String ret2=getHttpRequest(url);
                LogUtil.info("【搜索引擎】创建suggestion数据集合和分片", ret2);
                
                return ret1+"\n"+ret2;
                
                /*---------------------------方式2：手动分配shard和replica，可控性强-----------------------------*/
                //几个server节点就做几个副本
//                String ret="";
//                
//                //主集合
//                for(int i=0;i<shardNum;i++){
//                    for(int j=1;j<=shardNum;j++){
//                        String url="http://"+serverList.get(i)+"/solr/admin/cores?action=CREATE&name="+collectionName+"-shard"+j+"-replica"+(i+1)+"&collection="+collectionName+"&shard=shard"+j;
//                        LogUtil.info("【搜索引擎】创建主数据集合和分片", url);
//                        ret=get(url);
//                    }
//                }
//                
//                //suggestion集合
//                collectionName+=SearchConstants.COLLECTION_SUGGEST_SUFFIX;
//                for(int i=0;i<shardNum;i++){
//                    for(int j=1;j<=shardNum;j++){
//                        String url="http://"+serverList.get(i)+"/solr/admin/cores?action=CREATE&name="+collectionName+"-shard"+j+"-replica"+(i+1)+"&collection="+collectionName+"&shard=shard"+j;
//                        LogUtil.info("【搜索引擎】创建suggestion数据集合和分片", url);
//                        ret=get(url);
//                    }
//                }
//                
//                return ret;
                
            } catch (Exception e) {
                LogUtil.error(MODULE, "创建集合异常", e);
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_CREATE,new String[]{collectionName,SearchUtils.getExceptionMessage(e)});
            }
            
        }
        
        throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_CREATE,new String[]{collectionName,"不支持的部署模式下的集合初始化，DEPLOY_TYPE："+args.get("DEPLOY_TYPE")});
    }
    
    public static String deleteCollection(String collectionName) throws BusinessException{
        
        if(StringUtils.isBlank(collectionName)){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_DELETE,new String[]{collectionName,"collectionName empty"});
        }
        
        Map<String,String> args=SearchCacheUtils.getSecArgsMap();
        LogUtil.info("【搜索引擎】删除集合", "DEPLOY_TYPE："+args.get("DEPLOY_TYPE"));
        
        //判断部署模式
        if(StringUtils.equals(args.get("DEPLOY_TYPE"), SearchConstants.DeployType.CLOUD)){//CLOUD模式
            
            List<String> serverList;
            try {
                serverList = SearchCacheUtils.getSecSolrServerList();
            } catch (SearchException e) {
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_SOLRCLOUD_EMPTY);
            }
            
            String ret1="";
            
            try{
                
                //删除主数据集合
                String url="http://"+serverList.get(0)+"/solr/admin/collections?action=DELETE&name="+collectionName;
                LogUtil.info("【搜索引擎】删除主数据集合和分片", url);
                ret1=getHttpRequest(url);
                LogUtil.info("【搜索引擎】删除主数据集合和分片", ret1);
                
            } catch (Exception e) {
                //删除第一个集合失败不处理异常
                LogUtil.error(MODULE, "删除主数据集合和分片异常", e);
            }
            
            String ret2="";
            
            try{
                
                //删除suggestion集合
                collectionName+=SearchConstants.COLLECTION_SUGGEST_SUFFIX;
                String url="http://"+serverList.get(0)+"/solr/admin/collections?action=DELETE&name="+collectionName;
                LogUtil.info("【搜索引擎】删除suggestion数据集合和分片", url);
                ret2=getHttpRequest(url);
                LogUtil.info("【搜索引擎】删除suggestion数据集合和分片", ret2);
                
            } catch (Exception e) {
                //删除第二个集合失败不处理异常
                LogUtil.error(MODULE, "删除suggestion数据集合和分片异常", e);
//                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_DELETE,new String[]{collectionName,SearchUtils.getExceptionMessage(e)});
            }
            
            return ret1+"\n"+ret2;
            
        }
        
        throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_COLLECTION_DELETE,new String[]{collectionName,"不支持的部署模式下的集合删除，DEPLOY_TYPE："+args.get("DEPLOY_TYPE")});

    }
    
    private static String getHttpRequest(String url) throws Exception{
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                LogUtil.error(MODULE, "HTTP请求后关闭BufferedReader异常", e2);
            }
        }
        return result;
    }
    
    public static String getExceptionMessage(Exception e){
        if(e instanceof BusinessException){
            if(StringUtils.isNotBlank(((BusinessException) e).getErrorMessage())){
                return ((BusinessException) e).getErrorMessage();
            }
        }
        if(e.getCause()!=null){
            if(StringUtils.isNotBlank(e.getCause().getMessage())){
                return e.getCause().getMessage();
            }
            if(StringUtils.isNotBlank(e.getCause().getLocalizedMessage())){
                return e.getCause().getLocalizedMessage();
            }
        }
        if(StringUtils.isNotBlank(e.getMessage())){
            return e.getMessage();
        }
        return e.getLocalizedMessage();
    }
    
    public static String getFuzzyQueryKeyword(String keyword){
    	return SearchConstants.STAR+keyword+SearchConstants.STAR;
    }
    
    public static String format(Date date) {
        // int newHours=date.getHours()+8;
        // date.setHours(newHours);
        // return DateUtil.getThreadLocalDateFormat().format(date).replace(":", "\\:");
        return DateUtil.getThreadLocalDateFormat().format(date);
    }
}
