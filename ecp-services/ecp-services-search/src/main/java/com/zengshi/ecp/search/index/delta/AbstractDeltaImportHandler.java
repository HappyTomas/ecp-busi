package com.zengshi.ecp.search.index.delta;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServer;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowRowReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowRowRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataDupPagerSupport;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataSupport;
import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.index.IndexException;
import com.zengshi.ecp.search.index.IndexManager;
import com.zengshi.ecp.search.index.QuggestManager;
import com.zengshi.ecp.search.index.ext.SecObjectIndexProcessor;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public abstract class AbstractDeltaImportHandler<T extends DeltaMessage> extends
        MessageInitializer<T> implements IDeltaImportHandler<T> {

    protected final static String MODULE = "【搜索引擎】增量扩展处理器接口";

    private SolrServer indexServer;

    private SolrServer suggestServer;
    
    private SecConfigRespDTO secConfigRespDTO;

    private SecObjectRespDTO secObjectRespDTO;

    @Override
    public String getDeltaType() throws IndexException {
        return this.getDeltaMessage().getDeltaType();
    }

    public AbstractDeltaImportHandler(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO, String message, Class<T> clazz)
            throws IndexException {
        super(message, clazz);
        this.indexServer = indexServer;
        this.suggestServer = suggestServer;
        this.secConfigRespDTO=secConfigRespDTO;
        this.secObjectRespDTO = secObjectRespDTO;
        this.secObjectRespDTO.setDeltaMessage(this.getDeltaMessage());
    }
    
    public AbstractDeltaImportHandler(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO, T t)
            throws IndexException {
        super(t);
        this.indexServer = indexServer;
        this.suggestServer = suggestServer;
        this.secConfigRespDTO=secConfigRespDTO;
        this.secObjectRespDTO = secObjectRespDTO;
        this.secObjectRespDTO.setDeltaMessage(this.getDeltaMessage());
    }

    public boolean deltaImportDbIndex(DeltaMessage deltaMessage) throws BusinessException,
            IndexException {

        LogUtil.info(MODULE, "--------------------【搜索引擎】开始执行数据库增量--------------------");

        boolean match = false;

        // 删除索引数据
        if (StringUtils.equals(deltaMessage.getOperType(), EOperType.DELETE.getType())) {
            match = true;

            // 数据源接口内部分页
            if (StringUtils.equals(secObjectRespDTO.getInsidepager(), SearchConstants.STATUS_1)) {
                // IndexManager.deleteByQuery(indexServer, SearchConstants.ID +
                // SearchConstants.COLON + deltaMessage.getId()
                // + SearchConstants.SEPERATOR + SearchConstants.STAR);

                // 删除指定的索引数据
                if (CollectionUtils.isNotEmpty(deltaMessage.getIds())
                        && CollectionUtils.isNotEmpty(deltaMessage.getInsideIds())) {
                    for(String id:deltaMessage.getIds()){
                        for(String insideId:deltaMessage.getInsideIds()){
                            IndexManager.deleteByQuery(indexServer, SearchConstants.ID
                                    + SearchConstants.COLON + id
                                    + SearchConstants.SEPERATOR + insideId);
                            QuggestManager.deleteIndex(suggestServer, id
                                    + SearchConstants.SEPERATOR + insideId);
                        }
                    }
                } else if (CollectionUtils.isNotEmpty(deltaMessage.getIds())
                        && CollectionUtils.isEmpty(deltaMessage.getInsideIds())) {// 删除外部编码关联的索引数据
                    for(String id:deltaMessage.getIds()){
                        IndexManager.deleteByQuery(indexServer, SearchConstants.ID_PARENT
                                + SearchConstants.COLON + id);
                        QuggestManager.deleteIndex(suggestServer, id);
                    }
                } else if (CollectionUtils.isEmpty(deltaMessage.getIds())
                        && CollectionUtils.isNotEmpty(deltaMessage.getInsideIds())) {// 删除内部编码关联的索引数据
                    for(String insideId:deltaMessage.getInsideIds()){
                        IndexManager.deleteByQuery(indexServer, SearchConstants.ID_CHILD
                                + SearchConstants.COLON + insideId);
                        QuggestManager.deleteByQuery(suggestServer, SearchConstants.ID
                                + SearchConstants.COLON + SearchConstants.STAR
                                + SearchConstants.SEPERATOR + insideId
                                + SearchConstants.SEPERATOR + SearchConstants.STAR);
                    }
                } else {

                    // 增量删除参数不足
                    throw new BusinessException(
                            SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA, new String[] {
                                    secObjectRespDTO.getObjectNameen(), "增量删除参数不足，主键字段全为空！" });
                }

            } else {
                if(CollectionUtils.isNotEmpty(deltaMessage.getIds())){
                    for(String id:deltaMessage.getIds()){
                        IndexManager.deleteById(indexServer, id);
                        QuggestManager.deleteIndex(suggestServer, id);
                    }
                }
            }

        } else if (StringUtils.equals(deltaMessage.getOperType(), EOperType.UPDATE.getType())) {// 添加或更新索引数据
            match = true;

            String dubboServiceName = secObjectRespDTO.getDubboServicename();

            if (StringUtils.isBlank(dubboServiceName)) {
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA,
                        new String[] { secObjectRespDTO.getObjectNameen(),
                                "not point out dubboServiceName" });
            }

            if (CollectionUtils.isEmpty(deltaMessage.getIds())) {
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA,
                        new String[] { secObjectRespDTO.getObjectNameen(), "增量外部主键未提供！" });
            }

            // 数据源接口内部分页
            if (StringUtils.equals(secObjectRespDTO.getInsidepager(), SearchConstants.STATUS_1)) {

                // 创建其它域提供的DubboService注入实例
                ISearchDataDupPagerSupport dubboServiceObject = EcpFrameContextHolder.getBean(
                        dubboServiceName, ISearchDataDupPagerSupport.class);

                // 更新指定的数据
                if (CollectionUtils.isNotEmpty(deltaMessage.getInsideIds())) {

                    SearchDataDupPagerRowRowReqDTO searchDataRowReqDTO = new SearchDataDupPagerRowRowReqDTO();
                    searchDataRowReqDTO.setJsonParams(secObjectRespDTO.getObjectParams());

                    // 暂时只支持主键类型是Number类型的数据
                    SearchDataDupPagerRowRowRespDTO searchDataRowRespDTO = null;
                    
                    for(String id:deltaMessage.getIds()){
                        for(String insideId:deltaMessage.getInsideIds()){
                            searchDataRowReqDTO.setId(id);
                            searchDataRowReqDTO.setInsideId(insideId);
                            
                            try {

                                searchDataRowRespDTO = dubboServiceObject.getDataRow(searchDataRowReqDTO);
                            } catch (Exception e) {
                                throw new BusinessException(
                                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                                        new String[] { secObjectRespDTO.getObjectNameen(),
                                                SearchUtils.getExceptionMessage(e) });
                            }

                            if (searchDataRowRespDTO == null) {
                                throw new BusinessException(
                                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                                        new String[] { secObjectRespDTO.getObjectNameen(),
                                                "return result searchDataRowRespDTO null" });
                            }

                            if (MapUtils.isEmpty(searchDataRowRespDTO.getData())) {
                                throw new BusinessException(
                                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                                        new String[] { secObjectRespDTO.getObjectNameen(),
                                                "return result searchDataRowRespDTO.data null" });
                            }

                            try {

                                // 先删除suggest索引数据
                                QuggestManager.deleteIndex(suggestServer, id
                                        + SearchConstants.SEPERATOR + insideId);

                                IndexManager.importIndexDb(indexServer, suggestServer,secConfigRespDTO, secObjectRespDTO,
                                        searchDataRowRespDTO.getData());

                            } catch (Exception e) {
                                throw new BusinessException(
                                        SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA,
                                        new String[] { secObjectRespDTO.getObjectNameen(),
                                                SearchUtils.getExceptionMessage(e) });
                            }
                        }
                    }

                } else {// 更新外部编码关联的数据

                    SearchDataDupPagerRowPageReqDTO searchDataRowReqDTO = new SearchDataDupPagerRowPageReqDTO();
                    searchDataRowReqDTO.setJsonParams(secObjectRespDTO.getObjectParams());

                    // 暂时只支持主键类型是Number类型的数据
                    SearchDataDupPagerRowPageRespDTO searchDataRowRespDTO = null;

                    int insidePageSize = Integer.parseInt(SearchCacheUtils.getSecArgsMap().get(
                            "PAGE_FULLIMPORT"));
                    searchDataRowReqDTO.setInsidePageSize(insidePageSize);
                    
                    for(String id:deltaMessage.getIds()){
                        Map<String, Object> xParams = null;
                        int insidePageNo = 0;
                        int insideDbIndex = 1;
                        searchDataRowReqDTO.setId(id);
                        // 先删除suggest索引数据
                        IndexManager.deleteByQuery(indexServer, SearchConstants.ID_PARENT
                                + SearchConstants.COLON + id);
                        QuggestManager.deleteIndex(suggestServer, id);

                        while (true) {
                            insidePageNo++;
                            searchDataRowReqDTO.setInsidePageNo(insidePageNo);
                            searchDataRowReqDTO.setInsideDbIndex(insideDbIndex);
                            searchDataRowReqDTO.setxParams(xParams);

                            Long qCost=0l;
                            try {
                                Long startTime=System.currentTimeMillis();
                                searchDataRowRespDTO = dubboServiceObject
                                        .getDataRowPage(searchDataRowReqDTO);
                                qCost=System.currentTimeMillis()-startTime;
                            } catch (Exception e) {
                                throw new BusinessException(
                                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                                        new String[] { secObjectRespDTO.getObjectNameen(),
                                                SearchUtils.getExceptionMessage(e) });
                            }

                            if (searchDataRowRespDTO == null) {
                                throw new BusinessException(
                                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                                        new String[] { secObjectRespDTO.getObjectNameen(),
                                                "return result searchDataRowRespDTO null" });
                            }

                            xParams = searchDataRowRespDTO.getxParams();

                            if (searchDataRowRespDTO.getDataList() != null
                                    && !searchDataRowRespDTO.getDataList().isEmpty()) {

                                LogUtil.info(MODULE, "--------------------【搜索引擎】数据库增量调用业务域数据源服务返回数据："
                                        + searchDataRowRespDTO.getDataList().size()
                                        + "条。耗时（毫秒）："+qCost+"。--------------------");

                                IndexManager.submit(indexServer, suggestServer, secConfigRespDTO,secObjectRespDTO,
                                        searchDataRowRespDTO.getDataList());
                            }

                            // 单表分页完毕
                            if (searchDataRowRespDTO.isInsidePagerOver()) {

                                // 重新初始化查询参数
                                insideDbIndex++;
                                insidePageNo = 0;
                            }

                            // 全表扫描完毕
                            if (searchDataRowRespDTO.isInsideDbIndexOver()) {
                                break;
                            }

                        }
                    }

                }

            } else {// 一级分页

                SearchDataRowReqDTO searchDataRowReqDTO = new SearchDataRowReqDTO();
                searchDataRowReqDTO.setJsonParams(secObjectRespDTO.getObjectParams());

                // 创建其它域提供的DubboService注入实例
                ISearchDataSupport dubboServiceObject = EcpFrameContextHolder.getBean(
                        dubboServiceName, ISearchDataSupport.class);

                // 暂时只支持主键类型是Number类型的数据
                SearchDataRowRespDTO searchDataRowRespDTO = null;
                
                for(String id:deltaMessage.getIds()){
                    searchDataRowReqDTO.setId(id);
                    
                    try {

                        searchDataRowRespDTO = dubboServiceObject.getDataRow(searchDataRowReqDTO);
                    } catch (Exception e) {
                        throw new BusinessException(
                                SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA, new String[] {
                                        secObjectRespDTO.getObjectNameen(),
                                        SearchUtils.getExceptionMessage(e) });
                    }

                    if (searchDataRowRespDTO == null) {
                        throw new BusinessException(
                                SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA, new String[] {
                                        secObjectRespDTO.getObjectNameen(),
                                        "return result searchDataRowRespDTO null" });
                    }

                    if (MapUtils.isEmpty(searchDataRowRespDTO.getData())) {
                        throw new BusinessException(
                                SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                                new String[] { secObjectRespDTO.getObjectNameen(),
                                        "return result searchDataRowRespDTO.data null" });
                    }

                    try {

                        // 先删除suggest索引数据
                        QuggestManager.deleteIndex(suggestServer, id);

                        IndexManager.importIndexDb(indexServer, suggestServer, secConfigRespDTO,secObjectRespDTO,
                                searchDataRowRespDTO.getData());

                    } catch (Exception e) {
                        throw new BusinessException(
                                SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA, new String[] {
                                        secObjectRespDTO.getObjectNameen(),
                                        SearchUtils.getExceptionMessage(e) });
                    }
                }

            }

        }

        LogUtil.info(MODULE, "--------------------【搜索引擎】数据库增量执行完毕--------------------");

        return match;

    }

    public boolean deltaImportCustomIndex(DeltaMessage deltaMessage) throws BusinessException,
            IndexException {

        LogUtil.info(MODULE, "--------------------【搜索引擎】开始执行自定义增量--------------------");

        if (CollectionUtils.isEmpty(deltaMessage.getIds())) {
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA,
                    new String[] { secObjectRespDTO.getObjectNameen(), "增量外部主键未提供！" });
        }

        boolean match = false;

        // 删除索引数据
        if (StringUtils.equals(deltaMessage.getOperType(), EOperType.DELETE.getType())) {
            match = true;

            for(String id:deltaMessage.getIds()){
                IndexManager.deleteById(indexServer, id);
                QuggestManager.deleteIndex(suggestServer, id);
            }
        } else if (StringUtils.equals(deltaMessage.getOperType(), EOperType.UPDATE.getType())) {// 添加或更新索引数据
            match = true;

            try {

                for(Map<String,Object> map:deltaMessage.getDataList()){
                    
                    // Map中不存在主键字段
                    if (!map.containsKey(secObjectRespDTO.getObjectUniquefieldName())) {
                        throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA,
                                new String[] { secObjectRespDTO.getObjectNameen(),
                                "map not contains Key uniqueField: "
                                        + secObjectRespDTO.getObjectUniquefieldName() });
                    }

                    String id = String.valueOf(map.get(secObjectRespDTO.getObjectUniquefieldName()));

                    // 主键值不能为空
                    if (StringUtils.isBlank(id)) {
                        throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA,
                                new String[] { secObjectRespDTO.getObjectNameen(),
                                "empty value of uniqueField:"
                                        + secObjectRespDTO.getObjectUniquefieldName() });
                    }
                    
                    // 先删除suggest索引数据
                    QuggestManager.deleteIndex(suggestServer,id);

                    SecObjectIndexProcessor.importIndex(indexServer, suggestServer,
                            secConfigRespDTO,secObjectRespDTO,secObjectRespDTO.getSecFieldRespDTOList(),map);
                }

            } catch (Exception e) {
                throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA,
                        new String[] { secObjectRespDTO.getObjectNameen(),
                                SearchUtils.getExceptionMessage(e) });
            }
        }

        LogUtil.info(MODULE, "--------------------【搜索引擎】自定义增量执行完毕--------------------");

        return match;

    }

}
