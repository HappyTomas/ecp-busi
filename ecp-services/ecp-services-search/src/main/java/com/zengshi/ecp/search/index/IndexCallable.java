package com.zengshi.ecp.search.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.index.IndexManager.DocCreateReuslt;
import com.zengshi.paas.utils.LogUtil;

public class IndexCallable implements Callable<IndexResult> {

    private SolrServer indexServer;

    private SolrServer suggestServer;
    
    private SecConfigRespDTO secConfigRespDTO;

    private SecObjectRespDTO secObjectRespDTO;

    private List<Map<String, Object>> dataList;

    public IndexCallable(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO, List<Map<String, Object>> dataList) {
        this.indexServer = indexServer;
        this.suggestServer = suggestServer;
        this.secConfigRespDTO=secConfigRespDTO;
        this.secObjectRespDTO = secObjectRespDTO;
        this.dataList = dataList;
    }

    @Override
    public IndexResult call() throws Exception {
        return this.importIndexDbBatch();
    }

    public IndexResult importIndexDbBatch() throws Exception {

        //当前作业开始创建文档
        LogUtil.info(IndexManager.MODULE, Thread.currentThread().getName()+"：当前作业开始创建文档");
        Long startTime=System.currentTimeMillis();

        IndexResult indexResult = new IndexResult();

        if (CollectionUtils.isEmpty(dataList)) {
            return indexResult;
        }

        List<SolrInputDocument> indexDocList = new ArrayList<SolrInputDocument>();
        List<SolrInputDocument> suggestDocList = new ArrayList<SolrInputDocument>();
        DocCreateReuslt docCreateReuslt = null;

        // long nanotime=System.nanoTime();

        indexResult.setDataListCount(dataList.size());
        for (Map<String, Object> map : dataList) {
            try {
                // map.put("gdsId", String.valueOf(map.get(uniqueFieldName)));
                // map.put(uniqueFieldName, String.valueOf(map.get(uniqueFieldName))+"-"+nanotime);
                docCreateReuslt = IndexManager.createDocDb(secConfigRespDTO,secObjectRespDTO, map);
                indexDocList.add(docCreateReuslt.getIndexDoc());
                suggestDocList.addAll(docCreateReuslt.getSuggestDocList());
            } catch (IndexException e) {// 当前一条录入失败
                
                // 索引录入如果出现部分数据有异常或因为异常数据导致服务调用失败，不中断整个执行过程。
                LogUtil.error(IndexManager.MODULE, "数据录入索引失败【" + map.toString() + "】", e);

                indexResult.increaseFailCountBy(1);
                //indexResult.addFailRecord(map.toString());
                indexResult.addFailId(String.valueOf(map.get(secObjectRespDTO.getObjectUniquefieldName())));
            } catch (Exception e) {// 中断所有录入

                LogUtil.error(IndexManager.MODULE, "createDocDb异常，异常类型【"+e.getClass()+"】", e);
                // 中断执行，抛出异常
                throw new IndexException("createDocDb异常，异常类型【"+e.getClass()+"】，具体错误信息:"+SearchUtils.getExceptionMessage(e));
            }
        }

        // 批量添加文档
        try {
            if (indexDocList.size() > 0) {
                indexServer.add(indexDocList);
            }
            if (suggestDocList.size() > 0) {
                suggestServer.add(suggestDocList);
            }
        } catch (SolrServerException e) {
            LogUtil.error(IndexManager.MODULE, "文档批量提交到搜索引擎服务器抛出异常", e);
            throw new IndexException("文档批量提交到搜索引擎服务器抛出异常:"
                    + SearchUtils.getExceptionMessage(e));
        } catch (IOException e) {
            LogUtil.error(IndexManager.MODULE, "文档批量提交到搜索引擎服务器抛出异常", e);
            throw new IndexException("文档批量提交到搜索引擎服务器抛出异常:"
                    + SearchUtils.getExceptionMessage(e));
        }

        Long qCost=System.currentTimeMillis()-startTime;
        //当前作业执行完毕日志
        LogUtil.info(IndexManager.MODULE, Thread.currentThread().getName()+"：文档批量重建索引并提交到搜索引擎服务器成功。耗时（毫秒）："+qCost);

        return indexResult;

    }

}
