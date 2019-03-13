package com.zengshi.ecp.search.index;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataDupPagerSupport;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataSupport;
import com.zengshi.ecp.search.dubbo.dto.*;
import com.zengshi.ecp.search.dubbo.search.util.PatternMatcher;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.index.ext.FieldProcessor;
import com.zengshi.ecp.search.index.ext.MulFieldValFieldProcessor;
import com.zengshi.ecp.search.index.ext.MulValFieldProcessor;
import com.zengshi.ecp.search.index.ext.SecObjectIndexProcessor;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfigSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.cloud.ClusterState;
import org.apache.solr.common.cloud.ZkStateReader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 索引管理工具，索引配置实时查询，不取缓存<br>
 * Date:2015年8月18日上午10:54:39 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
@Service
public class IndexManager {

    public final static String MODULE = "【搜索引擎】全量索引";

    private static ISecConfigSV secConfigSV;

    private static ReentrantLock rlock = new ReentrantLock();

    //fieldProcessorMap不做并发同步控制，目的是可以减少同步锁占用时间。原因是虽然即使在极少可能出现fieldProcessorMap put两次的情况，但Map的哈希特性（hashcode一致）任然可以保持唯一性。
    private static Map<String, Object> fieldProcessorMap = new HashMap<String, Object>();

    private static int nthreads;

    private static ExecutorService pool;

    private static CompletionService completionService;

    public static List<String> indexingConfigIdList = Collections
            .synchronizedList(new ArrayList<String>());

    @Resource
    public void setSecConfigSV(ISecConfigSV secConfigSV) {
        IndexManager.secConfigSV = secConfigSV;
    }

    public static ISecConfigSV getSecConfigSV() {
        return secConfigSV;
    }

    private static CompletionService getCompletionService(int newthreads) {

        try {
            rlock.lock();
            if (pool == null) {
                nthreads = Integer.parseInt(SearchCacheUtils.getSecArgsMap().get("NTHREADS"));
                pool = Executors.newFixedThreadPool(nthreads,new SearchThreadFactory("全量索引"));
                completionService=new ExecutorCompletionService<String>(
                        pool);
            } else {

                boolean nthreadsNotChanged = (newthreads == nthreads);
                if (!nthreadsNotChanged) {
                    shutdown();
                    nthreads = newthreads;
                    pool = Executors.newFixedThreadPool(nthreads,new SearchThreadFactory("全量索引"));
                    completionService=new ExecutorCompletionService<String>(
                            pool);
                }
            }
        }finally {
            rlock.unlock();
        }

        return completionService;

    }

    private static CompletionService getCompletionService() {

        try{
            rlock.lock();
            if (pool == null) {
                nthreads = Integer.parseInt(SearchCacheUtils.getSecArgsMap().get("NTHREADS"));
                pool = Executors.newFixedThreadPool(nthreads,new SearchThreadFactory("全量索引"));
                completionService=new ExecutorCompletionService<String>(
                        pool);
            }
        }finally {
            rlock.unlock();
        }

        return completionService;

    }

    public static void shutdown() {
        if (pool != null) {
            if (!pool.isShutdown() || !pool.isTerminated()) {
                //立即结束所有执行任务
                pool.shutdownNow();
                try {
                    pool.awaitTermination(5, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    LogUtil.error(MODULE, "awaitTermination终止线程池异常", e);
                }
            }
        }
    }

    /**
     * 清除索引数据
     * 
     * @param secConfigReqDTO
     * @throws BusinessException
     */
    public static void cleanIndex(SecConfigReqDTO secConfigReqDTO) throws BusinessException {

        SecConfigRespDTO secConfigRespDTO = secConfigSV.querySecConfigByIdAll(secConfigReqDTO);

        secConfigReqDTO.setConfigCollectionName(secConfigRespDTO.getConfigCollectionName());

        // 集合是否存在
        if (!checkCollectionExist(secConfigReqDTO.getConfigCollectionName())) {
            throw new BusinessException(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTCREATED,
                    new String[] { secConfigReqDTO.getConfigCollectionName() });
        }

        try {

            SolrServer solrServer;

            solrServer = SearchUtils.getSolrServer(secConfigReqDTO.getConfigCollectionName(),false);
            solrServer.deleteByQuery(SearchConstants.QUERY_ALL);

            solrServer = SearchUtils.getSolrServer(secConfigReqDTO.getConfigCollectionName()
                            + SearchConstants.COLLECTION_SUGGEST_SUFFIX,false);
            solrServer.deleteByQuery(SearchConstants.QUERY_ALL);

        } catch (Exception e) {
            LogUtil.error(MODULE, "cleanIndex删除索引数据异常", e);
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_INDEX_CLEAN,
                    new String[] { secConfigReqDTO.getConfigCollectionName(),
                            SearchUtils.getExceptionMessage(e) });
        } 
    }

    public static boolean ifIndexing(long secConfigId) {

        // 索引重建中
        if (indexingConfigIdList.contains(secConfigId + "")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 重建索引
     * 
     * @param secConfigReqDTO
     * @param flag
     * @throws BusinessException
     */
    public static IndexReusltRespDTO reFullImportIndex(SecConfigReqDTO secConfigReqDTO, boolean flag)
            throws BusinessException {

        SecConfigRespDTO secConfigRespDTO = secConfigSV.querySecConfigByIdAll(secConfigReqDTO);

        IndexReusltRespDTO indexReusltRespDTO = null;

        if (!checkCollectionExist(secConfigRespDTO.getConfigCollectionName())) {
            throw new BusinessException(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTCREATED,
                    new String[] { secConfigRespDTO.getConfigCollectionName() });
        }

        try {
            if (flag) {
                cleanIndex(secConfigReqDTO);
            }
            indexReusltRespDTO = fullImportIndex(secConfigRespDTO, flag);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "reFullImportIndex全量索引异常", e);
            throw e;
        }

        return indexReusltRespDTO;

    }

    /**
     * 判断集合是否存在
     * 
     * @param collectionName
     * @return
     */
    private static boolean checkCollectionExist(String collectionName)
            throws BusinessException {

        SolrServer solrServer = SearchUtils.getSolrServer(collectionName,false);

        if (solrServer instanceof CloudSolrServer) {
            CloudSolrServer cloudSolrServer = (CloudSolrServer) solrServer;
            ZkStateReader zkStateReader = cloudSolrServer.getZkStateReader();
            ClusterState cloudState = zkStateReader.getClusterState();

            if (cloudState.getCollections().contains(collectionName)) {
                return true;
            }

            return false;
        }

        return true;

    }

    /**
     * 全量索引
     * 
     * @param secConfigRespDTO
     * @param flag
     */
    private static IndexReusltRespDTO fullImportIndex(SecConfigRespDTO secConfigRespDTO, boolean flag) throws BusinessException {

        List<SecObjectRespDTO> secObjectRespDTOList = secConfigRespDTO.getSecObjectRespDTOList();

        if (secObjectRespDTOList == null || secObjectRespDTOList.isEmpty()) {
            throw new BusinessException(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECOBJECTREGISTERD,
                    new String[] { secConfigRespDTO.getConfigCollectionName() });
        }

        SolrServer indexServer = SearchUtils.getSolrServer(secConfigRespDTO.getConfigCollectionName(),false);

        SolrServer suggestServer = SearchUtils.getSolrServer(secConfigRespDTO.getConfigCollectionName()
                        + SearchConstants.COLLECTION_SUGGEST_SUFFIX,false);

        int pageSize = Integer.parseInt(SearchCacheUtils.getSecArgsMap().get("PAGE_FULLIMPORT"));
        int newthreads = Integer.parseInt(SearchCacheUtils.getSecArgsMap().get("NTHREADS"));

        completionService = getCompletionService(newthreads);

        IndexReusltRespDTO indexReusltRespDTO = new IndexReusltRespDTO();
        indexReusltRespDTO.setArgs("pageSize:" + pageSize + ",nthreads:" + nthreads);
        indexReusltRespDTO.setStart(DateUtil.getSysDate());
        long start = System.currentTimeMillis();

        boolean hasField = false;

        // 配置多个表生成多条索引记录
        for (SecObjectRespDTO secObjectRespDTO : secObjectRespDTOList) {

            // 搜索数据对象未配置字段，不影响正常执行，跳过
            if (secObjectRespDTO.getSecFieldRespDTOList() == null
                    || secObjectRespDTO.getSecFieldRespDTOList().isEmpty()) {
                continue;
            }

            hasField = true;

            // 数据库搜索数据对象
            if (StringUtils.equals(SearchConstants.STATUS_1, secObjectRespDTO.getObjectType())) {
                indexReusltRespDTO = fullImportIndexDb(indexServer, suggestServer,secConfigRespDTO,
                        secObjectRespDTO, indexReusltRespDTO, flag, pageSize, nthreads);
            } else if (StringUtils.equals(SearchConstants.STATUS_2,
                    secObjectRespDTO.getObjectType())) {// 自定义搜索数据对象

                try {
                    SecObjectIndexProcessor secObjectIndexProcessor = EcpFrameContextHolder
                            .getBean(secObjectRespDTO.getObjectProcessorName(),
                                    SecObjectIndexProcessor.class);

                    indexReusltRespDTO = secObjectIndexProcessor.process(indexServer,
                            suggestServer, secConfigRespDTO,secObjectRespDTO,
                            secObjectRespDTO.getSecFieldRespDTOList(), indexReusltRespDTO);
                } catch (Exception e) {
                    LogUtil.error(MODULE, "自定义数据对象处理器SecObjectIndexProcessor全量索引异常", e);
                    throw new BusinessException(
                            SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL, new String[] {
                                    secObjectRespDTO.getObjectNameen(),
                                    SearchUtils.getExceptionMessage(e) });
                }
            }

            if (!indexReusltRespDTO.isSuccess()) {
                break;
            }

        }

        if (!hasField) {
            throw new BusinessException(
                    SearchMessageKeyContants.Info.KEY_INFO_COLLECTION_NOTSECFIELDREGISTERD,
                    new String[] { secConfigRespDTO.getConfigCollectionName() });
        }

        if (indexReusltRespDTO.isSuccess()) {
            indexReusltRespDTO.setFinished();
        }

        indexReusltRespDTO.setEnd(DateUtil.getSysDate());
        long end = System.currentTimeMillis();
        indexReusltRespDTO.setTimeCost((end - start) / 1000);
        if (indexReusltRespDTO.getTimeCost() > 0) {
            Short s = (short) (indexReusltRespDTO.getDataListCount() / indexReusltRespDTO
                    .getTimeCost());
            indexReusltRespDTO.setTps(s);
        }

        return indexReusltRespDTO;
    }

    public static Future<IndexResult> submit(SolrServer indexServer, SolrServer suggestServer,
            SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO, List<Map<String, Object>> dataList){
        Future<IndexResult> future = getCompletionService().submit(
                new IndexCallable(indexServer, suggestServer, secConfigRespDTO,secObjectRespDTO, dataList));
        return future;

    }

    /**
     * 数据库搜索数据对象全量索引
     * 
     * @param indexServer
     * @param suggestServer
     * @param secObjectRespDTO
     * @param indexReusltRespDTO
     * @param flag
     * @param pageSize
     * @param nthreads
     * @return
     * @throws BusinessException
     */
    private static IndexReusltRespDTO fullImportIndexDb(SolrServer indexServer,
            SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO, SecObjectRespDTO secObjectRespDTO,
            IndexReusltRespDTO indexReusltRespDTO, boolean flag, int pageSize, int nthreads)
            throws BusinessException {

        String dubboServiceName = secObjectRespDTO.getDubboServicename();

        if (StringUtils.isBlank(dubboServiceName)) {
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL,
                    new String[] { secObjectRespDTO.getObjectNameen(),
                            "not point out dubboServiceName" });
        }

        int count=0;
        Future<IndexResult> future;

        //int mulriple = 1 * nthreads;
        //change issue by huangdf@20170224：使用无界队列，可以直接一直submit，后再统一阻塞获取IndexResult。
        int mulriple=Integer.MAX_VALUE;
        int pageNo = 0;
        int dbIndex = 1;
        Map<String, Object> xParams = null;

        // 二级分页
        if (StringUtils.equals(secObjectRespDTO.getInsidepager(), SearchConstants.STATUS_1)) {

            ISearchDataDupPagerSupport dubboServiceObject = null;
            try {

                // 创建其它域提供的DubboService注入实例
                dubboServiceObject = EcpFrameContextHolder.getBean(dubboServiceName,
                        ISearchDataDupPagerSupport.class);
            } catch (Exception e) {
                LogUtil.error(MODULE, "动态获取dubboService实例失败", e);
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL,
                        new String[] { secObjectRespDTO.getObjectNameen(),
                                "动态获取dubboService实例失败：" + SearchUtils.getExceptionMessage(e) });
            }

            SearchDataDupPagerPageReqDTO searchDataPageReqDTO = new SearchDataDupPagerPageReqDTO();
            searchDataPageReqDTO.setJsonParams(secObjectRespDTO.getObjectParams());

            // 二级分页目前外部分页设置只支持1
            searchDataPageReqDTO.setPageSize(1);

            int insidePageNo = 0;
            int insideDbIndex = 1;
            searchDataPageReqDTO.setInsidePageSize(pageSize);

            // 逐表扫描放到业务域中控制
            while (true) {
                pageNo++;
                searchDataPageReqDTO.setPageNo(pageNo);
                searchDataPageReqDTO.setDbIndex(dbIndex);
                searchDataPageReqDTO.setxParams(xParams);

                SearchDataDupPagerPageRespDTO searchDataPageRespDTO = null;

                boolean firstOutsideRow = true;

                // 数据源内部分页页码重置
                insidePageNo = 0;

                while (true) {
                    insidePageNo++;

                    // 数据源内部分页
                    searchDataPageReqDTO.setInsidePageNo(insidePageNo);
                    searchDataPageReqDTO.setInsideDbIndex(insideDbIndex);
                    searchDataPageReqDTO.setxParams(xParams);

                    Long qCost=0l;
                    try {
                        Long startTime=System.currentTimeMillis();
                        searchDataPageRespDTO = dubboServiceObject
                                .getDataPage(searchDataPageReqDTO);
                        qCost=System.currentTimeMillis()-startTime;
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "dubbo服务调用失败【getDataPage】", e);
                        throw new BusinessException(
                                SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL, new String[] {
                                        secObjectRespDTO.getObjectNameen(),
                                        "dubbo服务调用失败【getDataPage】："
                                                + SearchUtils.getExceptionMessage(e) });
                    }

                    if (searchDataPageRespDTO == null) {
                        throw new BusinessException(
                                SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                                new String[] { secObjectRespDTO.getObjectNameen(),
                                        "return result searchDataPageRespDTO null" });
                    }

                    xParams = searchDataPageRespDTO.getxParams();

                    if (searchDataPageRespDTO.getDataList() != null
                            && !searchDataPageRespDTO.getDataList().isEmpty()) {

                        if (firstOutsideRow && (!flag)) {
                            firstOutsideRow = false;
                            String id = String.valueOf(searchDataPageRespDTO.getDataList().get(0)
                                    .get(secObjectRespDTO.getObjectUniquefieldName()));
                            IndexManager.deleteByQuery(indexServer, SearchConstants.ID_PARENT
                                    + SearchConstants.COLON + id);
                            QuggestManager.deleteIndex(suggestServer, id);
                        }

                        LogUtil.info(MODULE, "--------------------【搜索引擎】数据库全量调用业务域数据源服务返回数据："
                                + searchDataPageRespDTO.getDataList().size() + "条。dbIndex："
                                + dbIndex + "。pageNo： " + pageNo + "。耗时（毫秒）："+qCost+"。--------------------");

                        submit(indexServer, suggestServer,secConfigRespDTO,
                                secObjectRespDTO, searchDataPageRespDTO.getDataList());

                        count++;

                        try {

                            // 达到池最大并发处理能力的时候等待数据处理完毕后继续，避免内存溢出
                            // nowFutureList是先提交的任务列表，先执行
                            // nextFutureList是后提交的预备任务列表，后执行
                            if (count==2*mulriple) {
                                for (int j=1;j<=mulriple;j++) {

                                    // 阻塞等待
                                    future= getCompletionService().take();
                                    IndexResult indexResult=future.get() ;

                                    indexReusltRespDTO.increaseDataListCountBy(indexResult
                                            .getDataListCount());
                                    indexReusltRespDTO.increaseFailCountBy(indexResult
                                            .getFailCount());
                                    /*indexReusltRespDTO.getFailRecordList().addAll(
                                            indexResult.getFailRecordList());*/
                                    indexReusltRespDTO.setFailIdList(
                                            indexResult.getFailIdList());
                                }
                                count=mulriple;
                            }
                        } catch (Exception e) {
                            LogUtil.error(MODULE, "阻塞等待获取线程索引创建执行结果发生异常", e);

                            // 抛出异常立即中断执行
                            throw new BusinessException(
                                    SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL,
                                    new String[] { secObjectRespDTO.getObjectNameen(),
                                            SearchUtils.getExceptionMessage(e) });
                        }

                    }

                    // 单表分页完毕
                    if (searchDataPageRespDTO.isInsidePagerOver()) {

                        // 重新初始化查询参数
                        insideDbIndex++;
                        insidePageNo = 0;
                    }

                    // 全表扫描完毕
                    if (searchDataPageRespDTO.isInsideDbIndexOver()) {

                        // 重新初始化查询参数
                        insideDbIndex = 1;
                        insidePageNo = 0;
                        break;
                    }

                }

                // 单表分页完毕
                if (searchDataPageRespDTO.isPagerOver()) {

                    // 重新初始化查询参数
                    dbIndex++;
                    pageNo = 0;
                }

                // 全表扫描完毕
                if (searchDataPageRespDTO.isDbIndexOver()) {
                    break;
                }

            }

        } else {// 一级分页

            ISearchDataSupport dubboServiceObject = null;
            try {

                // 创建其它域提供的DubboService注入实例
                dubboServiceObject = EcpFrameContextHolder.getBean(dubboServiceName,
                        ISearchDataSupport.class);
            } catch (Exception e) {
                LogUtil.error(MODULE, "动态获取dubboService实例失败", e);
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL,
                        new String[] { secObjectRespDTO.getObjectNameen(),
                                "动态获取dubboService实例失败：" + SearchUtils.getExceptionMessage(e) });
            }

            SearchDataPageReqDTO searchDataPageReqDTO = new SearchDataPageReqDTO();
            searchDataPageReqDTO.setJsonParams(secObjectRespDTO.getObjectParams());

            // 细粒度分页参数控制
            if (StringUtils.isNotBlank(secObjectRespDTO.getPagesize())) {
                searchDataPageReqDTO.setPageSize(Integer.parseInt(secObjectRespDTO.getPagesize()));
            } else {// 全局分页参数控制
                searchDataPageReqDTO.setPageSize(pageSize);
            }

            // 逐表扫描放到业务域中控制
            while (true) {
                pageNo++;
                searchDataPageReqDTO.setPageNo(pageNo);
                searchDataPageReqDTO.setDbIndex(dbIndex);
                searchDataPageReqDTO.setxParams(xParams);

                SearchDataPageRespDTO searchDataPageRespDTO = null;

                Long qCost=0l;
                try {
                    Long startTime=System.currentTimeMillis();
                    searchDataPageRespDTO = dubboServiceObject.getDataPage(searchDataPageReqDTO);
                    qCost=System.currentTimeMillis()-startTime;
                } catch (Exception e) {
                    LogUtil.error(MODULE, "dubbo服务调用失败【getDataPage】", e);
                    throw new BusinessException(
                            SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL, new String[] {
                                    secObjectRespDTO.getObjectNameen(),
                                    "dubbo服务调用失败【getDataPage】："
                                            + SearchUtils.getExceptionMessage(e) });
                }

                if (searchDataPageRespDTO == null) {
                    throw new BusinessException(
                            SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA, new String[] {
                                    secObjectRespDTO.getObjectNameen(),
                                    "return result searchDataPageRespDTO null" });
                }

                xParams = searchDataPageRespDTO.getxParams();

                if (searchDataPageRespDTO.getDataList() != null
                        && !searchDataPageRespDTO.getDataList().isEmpty()) {

                    if (!flag) {

                        for (Map<String, Object> map : searchDataPageRespDTO.getDataList()) {

                            // 覆盖方式重建索引，商品doc能自动覆盖。商品doc可能被映射为多条suggest doc，无法自动覆盖，需要先删除。
                            String id = String.valueOf(map.get(secObjectRespDTO
                                    .getObjectUniquefieldName()));
                            QuggestManager.deleteIndex(suggestServer, id);
                        }

                    }

                    LogUtil.info(MODULE, "--------------------【搜索引擎】数据库全量调用业务域数据源服务返回数据："
                            + searchDataPageRespDTO.getDataList().size() + "条。dbIndex：" + dbIndex
                            + "。pageNo： " + pageNo + "。耗时（毫秒）："+qCost+"。--------------------");

                    submit(indexServer, suggestServer,secConfigRespDTO,
                            secObjectRespDTO, searchDataPageRespDTO.getDataList());

                    count++;

                    try {

                        if (count==2*mulriple) {
                            for (int j=1;j<=mulriple;j++) {

                                // 阻塞等待
                                future= getCompletionService().take();
                                IndexResult indexResult=future.get() ;

                                indexReusltRespDTO.increaseDataListCountBy(indexResult
                                        .getDataListCount());
                                indexReusltRespDTO.increaseFailCountBy(indexResult
                                        .getFailCount());
                                /*indexReusltRespDTO.getFailRecordList().addAll(
                                        indexResult.getFailRecordList());*/
                                indexReusltRespDTO.setFailIdList(
                                        indexResult.getFailIdList());
                            }
                            count=mulriple;
                        }
                    } catch (Exception e) {

                        LogUtil.error(MODULE, "阻塞等待获取线程索引创建执行结果发生异常", e);

                        // 抛出异常立即中断执行
                        throw new BusinessException(
                                SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL, new String[] {
                                        secObjectRespDTO.getObjectNameen(),
                                        SearchUtils.getExceptionMessage(e) });
                    }
                }

                // 单表分页完毕
                if (searchDataPageRespDTO.isPagerOver()) {

                    // 重新初始化查询参数
                    dbIndex++;
                    pageNo = 0;
                }

                // 全表扫描完毕
                if (searchDataPageRespDTO.isDbIndexOver()) {
                    break;
                }

            }

        }

        //取出剩余执行结果
        try {
            for (int j = 1; j <= count; j++) {

                // 阻塞等待
                future = getCompletionService().take();
                IndexResult indexResult = future.get();

                indexReusltRespDTO.increaseDataListCountBy(indexResult
                        .getDataListCount());
                indexReusltRespDTO.increaseFailCountBy(indexResult
                        .getFailCount());
                /*indexReusltRespDTO.getFailRecordList().addAll(
                        indexResult.getFailRecordList());*/
                indexReusltRespDTO.setFailIdList(
                        indexResult.getFailIdList());
            }
        }catch (Exception e) {

            LogUtil.error(MODULE, "阻塞等待获取线程索引创建执行结果发生异常", e);

            // 抛出异常立即中断执行
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL,
                    new String[] { secObjectRespDTO.getObjectNameen(),
                            SearchUtils.getExceptionMessage(e) });
        }

        return indexReusltRespDTO;

    }

    /**
     * 创建文档，<b>包含扩展字段处理</b>
     * 
     * @param secObjectRespDTO
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static DocCreateReuslt createDocDb(SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO,
            Map<String, Object> map) throws Exception {

        // Map中不存在主键字段
        if (!map.containsKey(secObjectRespDTO.getObjectUniquefieldName())) {
            throw new IndexException("map not contains Key uniqueField: "
                    + secObjectRespDTO.getObjectUniquefieldName());
        }

        String id = String.valueOf(map.get(secObjectRespDTO.getObjectUniquefieldName()));

        // 主键值不能为空
        if (StringUtils.isBlank(id)) {
            throw new IndexException("empty value of uniqueField:"
                    + secObjectRespDTO.getObjectUniquefieldName());
        }

        SolrInputDocument indexDoc = new SolrInputDocument();

        // 内部分页
        if (StringUtils.equals(secObjectRespDTO.getInsidepager(), SearchConstants.STATUS_1)) {

            // Map中不存在主键字段
            if (!map.containsKey(secObjectRespDTO.getInsidepagerUniquefieldName())) {
                throw new IndexException("map not contains Key inside uniqueField: "
                        + secObjectRespDTO.getInsidepagerUniquefieldName());
            }

            String insideId = String.valueOf(map.get(secObjectRespDTO
                    .getInsidepagerUniquefieldName()));

            // 主键值不能为空
            if (StringUtils.isBlank(insideId)) {
                throw new IndexException("empty value of inside uniqueField:"
                        + secObjectRespDTO.getInsidepagerUniquefieldName());
            }

            indexDoc.setField(SearchConstants.ID_PARENT, id);
            indexDoc.setField(SearchConstants.ID_CHILD, insideId);

            id = id + SearchConstants.SEPERATOR + insideId;
        }

        DocCreateReuslt docCreateReuslt = new DocCreateReuslt();
        List<SolrInputDocument> suggestDocList = new ArrayList<SolrInputDocument>();
        Object processorBean = null;
        int i = 0;

        // 设置唯一索引字段值,为字符串
        indexDoc.setField(SearchConstants.ID, id);

        // 字段多语言
        boolean multiLan = false;
        String langs[] = null;

        // 多字段处理器只处理一次，理论上配置中也应该只存在一次。为了防止用户配置了多次导致性能下降，程序内部做查重处理
        List<String> mulFieldProcessors=new ArrayList<String>();

        //数据对象处理器的前置多字段处理器
        //初始化顺序先于普通字段，可用于一些高优先级字段处理，如多语言语言字段初始化一定要放在数据对象处理器的前置多字段处理器（这是强制性要求）。
        if(StringUtils.isNotBlank(secObjectRespDTO.getMulvalfieldProcessorName())){
            if (fieldProcessorMap.containsKey(secObjectRespDTO.getMulvalfieldProcessorName())) {

                processorBean = fieldProcessorMap.get(secObjectRespDTO.getMulvalfieldProcessorName());

            } else {

                try {

                    // 获取通过Spring注册并初始化的Processor实例进行处理
                    processorBean = EcpFrameContextHolder.getBean(
                            secObjectRespDTO.getMulvalfieldProcessorName(), Object.class);
                    fieldProcessorMap.put(secObjectRespDTO.getMulvalfieldProcessorName(),
                            processorBean);
                } catch (Exception e) {

                    LogUtil.error(MODULE, "数据对象的前置扩展多字段处理器中【动态获取springbean实例失败】，扩展处理器名:"
                            + secObjectRespDTO.getMulvalfieldProcessorName() + "。数据【" + map.toString()
                            + "】", e);

                    // 中断执行
                    throw new InterruptedException("数据对象的前置扩展多字段处理器中【动态获取springbean实例失败】，扩展处理器名:"
                            + secObjectRespDTO.getMulvalfieldProcessorName() + "。数据【" + map.toString()
                            + "】，具体错误信息："
                            + SearchUtils.getExceptionMessage(e));
                }

            }

            if (processorBean == null) {

                // 中断执行
                throw new InterruptedException("object mulfield processorbean not registerd:"
                        + secObjectRespDTO.getMulvalfieldProcessorName());
            }

            //数据对象处理器的前置字段处理器只能是多值字段扩展处理器类型
            if(processorBean instanceof MulFieldValFieldProcessor){
                mulFieldProcessors.add(secObjectRespDTO.getMulvalfieldProcessorName());

                //对于数据对象处理器的前置多字段处理器中入参curSecFieldRespDTO可以为空（没有使用到）。
                Map<String, Object> _map = ((MulFieldValFieldProcessor) processorBean)
                        .process(secConfigRespDTO,secObjectRespDTO, null,
                                secObjectRespDTO.getSecFieldRespDTOList(), map);
                if (MapUtils.isNotEmpty(_map)) {
                    //直接往数据源Map中覆盖或追加数据对象处理器的前置多字段处理器返回的Map
                    map.putAll(_map);
                }
            }
        }

        // 记录多语言需要设置语言字段
        if(StringUtils.equals(secConfigRespDTO.getConfigIfMultilan(),
                SearchConstants.STATUS_1)){
            //记录语言字段
            if(StringUtils.isNotBlank(secConfigRespDTO.getLanField())){
                if(map.containsKey(secConfigRespDTO.getLanField())&&StringUtils.isNotBlank(String.valueOf(map.get(secConfigRespDTO.getLanField())))){
                    indexDoc.setField(SearchConstants.FIELD_LANG, String.valueOf(map.get(secConfigRespDTO.getLanField())));
                }
            }
            //记录语言分词类型字段
            if(StringUtils.isNotBlank(secConfigRespDTO.getLanFieldFieldType())){
                if(map.containsKey(secConfigRespDTO.getLanFieldFieldType())&&StringUtils.isNotBlank(String.valueOf(map.get(secConfigRespDTO.getLanFieldFieldType())))){
                    indexDoc.setField(SearchConstants.FIELD_LANG_FIELD_TYPE, String.valueOf(map.get(secConfigRespDTO.getLanFieldFieldType())));
                }
            }
        }

        for (SecFieldRespDTO secFieldRespDTO : secObjectRespDTO.getSecFieldRespDTOList()) {

            multiLan = false;

            if (StringUtils.equals(secFieldRespDTO.getFieldIfMultilan(), SearchConstants.STATUS_1)) {

                //记录多语言//若字段配置为多语言，但是数据对象没有配置为多语言，则当成普通字段处理。
                if(StringUtils.equals(secConfigRespDTO.getConfigIfMultilan(),
                        SearchConstants.STATUS_1)&& StringUtils.isNotBlank(secConfigRespDTO.getLanFieldFieldType())){

                    //记录多语言需要判断是否有返回语言字段
                    if(map.containsKey(secConfigRespDTO.getLanFieldFieldType())&&StringUtils.isNotBlank(String.valueOf(map.get(secConfigRespDTO.getLanFieldFieldType())))){
                        multiLan = true;
                    }
                }else if(StringUtils.equals(secConfigRespDTO.getConfigIfMultilan(),
                        SearchConstants.STATUS_2)&&StringUtils.isNotBlank(secConfigRespDTO.getLans())){//字段多语言//若字段配置为多语言，但是数据对象没有配置为多语言，则当成普通字段处理。
                    multiLan = true;

                    // 字段多语言，语言数据只初始化一次
                    if (ArrayUtils.isEmpty(langs)) {
                        langs = new String[] { secConfigRespDTO.getLans() };
                        if (secConfigRespDTO.getLans().contains(SearchConstants.COMMA)) {
                            langs = secConfigRespDTO.getLans().split(SearchConstants.COMMA);
                        }
                    }
                }
            }

            // 扩展功能，字段处理器
            if (StringUtils.isNotBlank(secFieldRespDTO.getFieldProcessorName())) {

                if (fieldProcessorMap.containsKey(secFieldRespDTO.getFieldProcessorName())) {

                    processorBean = fieldProcessorMap.get(secFieldRespDTO.getFieldProcessorName());

                } else {

                    try {

                        // 获取通过Spring注册并初始化的Processor实例进行处理
                        processorBean = EcpFrameContextHolder.getBean(
                                secFieldRespDTO.getFieldProcessorName(), Object.class);
                        fieldProcessorMap.put(secFieldRespDTO.getFieldProcessorName(),
                                processorBean);
                    } catch (Exception e) {

                        LogUtil.error(MODULE, "扩展字段处理器中【动态获取springbean实例失败】，扩展处理器名:"
                                        + secFieldRespDTO.getFieldProcessorName() + "。数据【" + map.toString()
                                        + "】，索引字段【" + secFieldRespDTO.getFieldBeanFieldName(), e);
                        
                        // 中断执行
                        throw new InterruptedException("扩展字段处理器中【动态获取springbean实例失败】，扩展处理器名:"
                                + secFieldRespDTO.getFieldProcessorName() + "。数据【" + map.toString()
                                + "】，索引字段【" + secFieldRespDTO.getFieldBeanFieldName() + "】，具体错误信息："
                                + SearchUtils.getExceptionMessage(e));
                    }

                }

                if (processorBean == null) {

                    // 中断执行
                    throw new InterruptedException("processorbean not registerd:"
                            + secFieldRespDTO.getFieldProcessorName());
                }

                try {

                    // 多值字段处理器//不支持通配符字段
                    if (processorBean instanceof MulValFieldProcessor) {
                        Object fieldValue = ((MulValFieldProcessor) processorBean).process(secConfigRespDTO,
                                secObjectRespDTO, secFieldRespDTO,
                                secObjectRespDTO.getSecFieldRespDTOList(), map);

                        String indexName = "";
                        if (multiLan) {

                            //记录多语言
                            if(StringUtils.equals(SearchConstants.STATUS_1,secConfigRespDTO.getConfigIfMultilan())){
                                indexName = secFieldRespDTO.getFieldIndexName().replace(
                                        SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                                + SearchConstants.UNDERLINE,
                                        SearchConstants.UNDERLINE + map.get(secConfigRespDTO.getLanFieldFieldType())
                                                + SearchConstants.UNDERLINE);
                            }else if(StringUtils.equals(SearchConstants.STATUS_2,secConfigRespDTO.getConfigIfMultilan())){//字段多语言

                                // 多值字段处理器配置了多语言，则默认是第一个语言
                                indexName = secFieldRespDTO.getFieldIndexName().replace(
                                        SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                                + SearchConstants.UNDERLINE,
                                        SearchConstants.UNDERLINE + langs[0]
                                                + SearchConstants.UNDERLINE);
                            }
                        } else {
                            indexName = secFieldRespDTO.getFieldIndexName();
                        }

                        indexDoc.setField(indexName, fieldValue);

                        // 配置为拼写检查的字段
                        if (StringUtils.equals(secFieldRespDTO.getFieldIfSpellcheck(),
                                SearchConstants.STATUS_1)) {
                            for (String s : getStringValus(fieldValue)) {
                                if (StringUtils.isNotBlank(s)) {
                                    suggestDocList.addAll(QuggestManager.createDocAll(id
                                            + SearchConstants.SEPERATOR + (++i),map.get(secConfigRespDTO.getLanField()), s));
                                }
                            }
                        }

                    } else if (processorBean instanceof MulFieldValFieldProcessor) {// 多字段处理器//支持通配符字段

                        if(mulFieldProcessors.contains(secFieldRespDTO.getFieldProcessorName())){
                            continue;
                        }

                        mulFieldProcessors.add(secFieldRespDTO.getFieldProcessorName());

                        Map<String, Object> _map = ((MulFieldValFieldProcessor) processorBean)
                                .process(secConfigRespDTO,secObjectRespDTO, secFieldRespDTO,
                                        secObjectRespDTO.getSecFieldRespDTOList(), map);
                        if (MapUtils.isNotEmpty(_map)) {
                            //直接往数据源Map中覆盖或追加数据对象处理器的前置多字段处理器返回的Map
                            map.putAll(_map);
                        }

                        //对于在字段中配置了多字段扩展处理器中，需要分别遍历返回的字段值（_map，而不是map）处理，因为多字段扩展处理器无法保证会在配置的第一个字段中处理到。
                        for (Entry<String, Object> entry : _map.entrySet()) {

                            for (SecFieldRespDTO _secFieldRespDTO : secObjectRespDTO
                                    .getSecFieldRespDTOList()) {

                                String indexName = "";
                                if (multiLan) {

                                    //记录多语言
                                    if(StringUtils.equals(SearchConstants.STATUS_1,secConfigRespDTO.getConfigIfMultilan())){
                                        indexName = _secFieldRespDTO.getFieldIndexName().replace(
                                                SearchConstants.UNDERLINE + _secFieldRespDTO.getFieldTypeName()
                                                        + SearchConstants.UNDERLINE,
                                                SearchConstants.UNDERLINE + map.get(secConfigRespDTO.getLanFieldFieldType())
                                                        + SearchConstants.UNDERLINE);
                                    }else if(StringUtils.equals(SearchConstants.STATUS_2,secConfigRespDTO.getConfigIfMultilan())){//字段多语言

                                        String key = "";
                                        for (String lang : langs) {
                                            key = _secFieldRespDTO.getFieldBeanFieldName()
                                                    + SearchConstants.LANG_SEPERATOR + lang;

                                            if (PatternMatcher.match(key, entry.getKey())) {
                                                indexName = _secFieldRespDTO
                                                        .getFieldIndexName()
                                                        .replace(
                                                                SearchConstants.UNDERLINE
                                                                        + _secFieldRespDTO.getFieldTypeName()
                                                                        + SearchConstants.UNDERLINE,
                                                                SearchConstants.UNDERLINE + lang
                                                                        + SearchConstants.UNDERLINE);

                                                //支持通配符字段
                                                indexName.replace(
                                                        _secFieldRespDTO.getFieldBeanFieldName()+SearchConstants.UNDERLINE,
                                                        entry.getKey()+SearchConstants.UNDERLINE);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (PatternMatcher.match(_secFieldRespDTO.getFieldBeanFieldName(),
                                            entry.getKey())) {

                                        //支持通配符字段
                                        indexName = _secFieldRespDTO.getFieldIndexName().replace(
                                                _secFieldRespDTO.getFieldBeanFieldName()+SearchConstants.UNDERLINE,
                                                entry.getKey()+SearchConstants.UNDERLINE);
                                    }
                                }

                                if (StringUtils.isNotBlank(indexName)) {
                                    indexDoc.setField(indexName, entry.getValue());

                                    // 配置为拼写检查的字段
                                    if (StringUtils.equals(_secFieldRespDTO.getFieldIfSpellcheck(),
                                            SearchConstants.STATUS_1)) {
                                        for (String s : getStringValus(entry.getValue())) {
                                            if (StringUtils.isNotBlank(s)) {
                                                suggestDocList.addAll(QuggestManager.createDocAll(id
                                                        + SearchConstants.SEPERATOR + (++i),map.get(secConfigRespDTO.getLanField()), s));
                                            }
                                        }
                                    }

                                    break;
                                }

                            }

                        }
                    } else if (processorBean instanceof FieldProcessor) {// 单值字段//最后匹配//不支持通配符字段
                        Object fieldValue = ((FieldProcessor) processorBean).process(secConfigRespDTO,
                                secObjectRespDTO, secFieldRespDTO,
                                secObjectRespDTO.getSecFieldRespDTOList(), map);

                        String indexName = "";
                        if (multiLan) {

                            //记录多语言
                            if(StringUtils.equals(SearchConstants.STATUS_1,secConfigRespDTO.getConfigIfMultilan())){
                                indexName = secFieldRespDTO.getFieldIndexName().replace(
                                        SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                                + SearchConstants.UNDERLINE,
                                        SearchConstants.UNDERLINE + map.get(secConfigRespDTO.getLanFieldFieldType())
                                                + SearchConstants.UNDERLINE);
                            }else if(StringUtils.equals(SearchConstants.STATUS_2,secConfigRespDTO.getConfigIfMultilan())){//字段多语言

                                // 多值字段处理器配置了多语言，则默认是第一个语言
                                indexName = secFieldRespDTO.getFieldIndexName().replace(
                                        SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                                + SearchConstants.UNDERLINE,
                                        SearchConstants.UNDERLINE + langs[0]
                                                + SearchConstants.UNDERLINE);
                            }

                        } else {
                            indexName = secFieldRespDTO.getFieldIndexName();
                        }

                        indexDoc.setField(indexName, fieldValue);

                        // 配置为拼写检查的字段
                        if (StringUtils.equals(secFieldRespDTO.getFieldIfSpellcheck(),
                                SearchConstants.STATUS_1)) {
                            for (String s : getStringValus(fieldValue)) {
                                if (StringUtils.isNotBlank(s)) {
                                    suggestDocList.addAll(QuggestManager.createDocAll(id
                                            + SearchConstants.SEPERATOR + (++i),map.get(secConfigRespDTO.getLanField()), s));
                                }
                            }
                        }
                    }

                } catch (BusinessException e) {// 当前一条录入失败
                    LogUtil.error(MODULE,"扩展字段处理器中【RSV服务调用异常】，扩展实现类:"
                            + processorBean.getClass() + "。数据【" + map.toString() + "】，索引字段【"
                            + secFieldRespDTO.getFieldBeanFieldName(), e);
                    throw new IndexException("扩展字段处理器中【RSV服务调用异常】，扩展实现类:"
                            + processorBean.getClass() + "。数据【" + map.toString() + "】，索引字段【"
                            + secFieldRespDTO.getFieldBeanFieldName() + "】，具体错误信息："
                            + SearchUtils.getExceptionMessage(e));
                } catch (IndexException e) {// 当前一条录入失败
                    LogUtil.error(MODULE, "扩展字段处理器中【索引业务处理异常】，扩展实现类:"
                            + processorBean.getClass() + "。数据【" + map.toString() + "】，索引字段【"
                            + secFieldRespDTO.getFieldBeanFieldName(), e);
                    throw new IndexException("扩展字段处理器中【索引业务处理异常】，扩展实现类:"
                            + processorBean.getClass() + "。数据【" + map.toString() + "】，索引字段【"
                            + secFieldRespDTO.getFieldBeanFieldName() + "】，具体错误信息："
                            + SearchUtils.getExceptionMessage(e));
                } catch (Exception e) {

                    // 中断执行--->修改为不中断运行
                    LogUtil.error(MODULE,"扩展字段处理器中【代码运行异常】，扩展实现类:"
                            + processorBean.getClass() + "。数据【" + map.toString() + "】，索引字段【"
                            + secFieldRespDTO.getFieldBeanFieldName(), e);
                    throw new IndexException("扩展字段处理器中【代码运行异常】，扩展实现类:"
                            + processorBean.getClass() + "。数据【" + map.toString() + "】，索引字段【"
                            + secFieldRespDTO.getFieldBeanFieldName() + "】，具体错误信息："
                            + SearchUtils.getExceptionMessage(e));
                }

            } else {
                
                //TODO 数据源来源字段暂不支持通配符(containsKey,性能考虑),通配符字段暂时只支持在多字段扩展处理器中做。

                if (multiLan) {

                    //记录多语言
                    if(StringUtils.equals(SearchConstants.STATUS_1,secConfigRespDTO.getConfigIfMultilan())){
                        String indexName = secFieldRespDTO.getFieldIndexName().replace(
                                SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                        + SearchConstants.UNDERLINE,
                                SearchConstants.UNDERLINE + map.get(secConfigRespDTO.getLanFieldFieldType())
                                        + SearchConstants.UNDERLINE);

                        Object fieldValue = map.get(secFieldRespDTO.getFieldBeanFieldName());

                        indexDoc.setField(indexName, fieldValue);

                        // 配置为拼写检查的字段
                        if (StringUtils.equals(secFieldRespDTO.getFieldIfSpellcheck(),
                                SearchConstants.STATUS_1)) {
                            for (String s : getStringValus(fieldValue)) {
                                if (StringUtils.isNotBlank(s)) {
                                    suggestDocList.addAll(QuggestManager.createDocAll(id
                                            + SearchConstants.SEPERATOR + (++i), map.get(secConfigRespDTO.getLanField()), s));
                                }
                            }
                        }
                    }else if(StringUtils.equals(SearchConstants.STATUS_2,secConfigRespDTO.getConfigIfMultilan())){//字段多语言

                        String key = "";
                        String indexName = "";
                        for (String lang : langs) {
                            key = secFieldRespDTO.getFieldBeanFieldName()
                                    + SearchConstants.LANG_SEPERATOR + lang;
                            if (map.containsKey(key)) {
                                Object fieldValue = map.get(key);
                                indexName = secFieldRespDTO.getFieldIndexName().replace(
                                        SearchConstants.UNDERLINE + secFieldRespDTO.getFieldTypeName()
                                                + SearchConstants.UNDERLINE,
                                        SearchConstants.UNDERLINE + lang + SearchConstants.UNDERLINE);
                                indexDoc.setField(indexName, fieldValue);

                                // 配置为拼写检查的字段
                                if (StringUtils.equals(secFieldRespDTO.getFieldIfSpellcheck(),
                                        SearchConstants.STATUS_1)) {
                                    for (String s : getStringValus(fieldValue)) {
                                        if (StringUtils.isNotBlank(s)) {
                                            suggestDocList.addAll(QuggestManager.createDocAll(id
                                                    + SearchConstants.SEPERATOR + (++i), lang, s));
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {

                    // 索引配置中，需要扩展取值的索引字段名最好不要和数据源Map Key冲突，否则无法保证不出现值相互覆盖的情况发生。
                    // 不需要扩展取值类型，扩展类型是多字段扩展实现无需处理
                    if (map.containsKey(secFieldRespDTO.getFieldBeanFieldName())) {
                        Object fieldValue = map.get(secFieldRespDTO.getFieldBeanFieldName());
                        indexDoc.setField(secFieldRespDTO.getFieldIndexName(), fieldValue);

                        // 配置为拼写检查的字段
                        if (StringUtils.equals(secFieldRespDTO.getFieldIfSpellcheck(),
                                SearchConstants.STATUS_1)) {
                            for (String s : getStringValus(fieldValue)) {
                                if (StringUtils.isNotBlank(s)) {
                                    suggestDocList.addAll(QuggestManager.createDocAll(id
                                            + SearchConstants.SEPERATOR + (++i),map.get(secConfigRespDTO.getLanField()), s));
                                }
                            }
                        }
                    }
                }

            }

        }

        docCreateReuslt.setIndexDoc(indexDoc);
        docCreateReuslt.setSuggestDocList(suggestDocList);

        return docCreateReuslt;

    }

    /**
     * 添加索引，<b>包含扩展字段处理</b>
     * 
     * @param indexServer
     * @param suggestServer
     * @param secObjectRespDTO
     * @param map
     * @throws IndexException
     */
    public static void importIndexDb(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO, Map<String, Object> map) throws IndexException {

        DocCreateReuslt docCreateReuslt = null;
        try {
            docCreateReuslt = createDocDb(secConfigRespDTO,secObjectRespDTO, map);
        } catch (Exception e1) {

            LogUtil.error(MODULE, "根据索引配置创建文档失败", e1);

            // 中断执行，抛出异常
            throw new IndexException("根据索引配置创建文档失败:" + SearchUtils.getExceptionMessage(e1));
        }
        SolrInputDocument indexDoc = docCreateReuslt.getIndexDoc();
        List<SolrInputDocument> suggestDocList = docCreateReuslt.getSuggestDocList();

        try {
            indexServer.add(indexDoc);
            if (suggestDocList.size() > 0) {
                suggestServer.add(suggestDocList);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "文档提交到搜索引擎服务器抛出异常", e);
            throw new IndexException("文档提交到搜索引擎服务器抛出异常:"
                    + SearchUtils.getExceptionMessage(e));
        }

    }

    /**
     * 清除索引数据
     * 
     * @param solrServer
     * @param id
     */
    public static void deleteById(SolrServer solrServer, String id) {

        try {
            solrServer.deleteById(id);

        } catch (Exception e) {
            
            //特殊情况下的索引删除异常不应该中断索引重建过程
            LogUtil.error(MODULE, "索引删除异常", e);
//            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_INDEX_DELETE,
//                    new String[] { id, SearchUtils.getExceptionMessage(e) });
        }
    }

    public static void deleteByQuery(SolrServer solrServer, String query) {

        try {
            solrServer.deleteByQuery(query);

        } catch (Exception e) {
            
            //特殊情况下的索引删除异常不应该中断索引重建过程
            LogUtil.error(MODULE, "索引删除异常", e);
//            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_INDEX_DELETE,
//                    new String[] { query, SearchUtils.getExceptionMessage(e) });
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<String> getStringValus(Object o) {
        List<String> stringList = new ArrayList<String>();
        if (o != null) {
            if (o.getClass().isArray()) {
                Object[] os = (Object[]) o;
                if (os != null && os.length > 0 && os[0] instanceof String) {
                    String[] ss = (String[]) o;
                    for (String ssc : ss) {
                        stringList.add(ssc);
                    }
                }
            } else if (o instanceof List) {
                List os = (List) o;
                if (os != null && os.size() > 0 && os.get(0) instanceof String) {
                    List<String> ss = (List<String>) o;
                    for (String ssc : ss) {
                        stringList.add(ssc);
                    }
                }
            } else {
                if (o instanceof String) {
                    stringList.add(String.valueOf(o));
                }
            }
        }
        return stringList;
    }

    public static class DocCreateReuslt {

        private SolrInputDocument indexDoc;

        private List<SolrInputDocument> suggestDocList;

        public SolrInputDocument getIndexDoc() {
            return indexDoc;
        }

        public void setIndexDoc(SolrInputDocument indexDoc) {
            this.indexDoc = indexDoc;
        }

        public List<SolrInputDocument> getSuggestDocList() {
            return suggestDocList;
        }

        public void setSuggestDocList(List<SolrInputDocument> suggestDocList) {
            this.suggestDocList = suggestDocList;
        }

    }

}
