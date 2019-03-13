package com.zengshi.ecp.search.index.delta;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServer;

import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.index.delta.handler.DefaultDeltaImportHandler;
import com.zengshi.paas.utils.LogUtil;

public class DeltaIndexRunnable1 implements Runnable{
    
    private String message;
    
    public DeltaIndexRunnable1(String message){
        this.message=message;
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
    @Override
    public void run() {
        
        JSONObject json = JSONObject.fromObject(message);
        String objectNameEn = "";
        
        if(json.containsKey("objectNameEn")){
            objectNameEn=json.getString("objectNameEn");
        }
        
        if(StringUtils.isBlank(objectNameEn)){
            LogUtil.error(DeltaIndexManager.MODULE, "未指定数据对象英文名或主表名！");
        }
        
        // 增量索引，配置直接查缓存，不查表
        Map<String, SecConfigRespDTO> secConfigRespDTOMap = null;
        try {
            secConfigRespDTOMap = SearchCacheUtils
                    .getSecConfigReqDTOMap();
        } catch (SearchException e1) {
            LogUtil.error(DeltaIndexManager.MODULE, SearchUtils.getExceptionMessage(e1));
        }

        for (SecConfigRespDTO secConfigRespDTO : secConfigRespDTOMap.values()) {

            List<SecObjectRespDTO> secObjectRespDTOList = secConfigRespDTO
                    .getSecObjectRespDTOList();
            if (secObjectRespDTOList != null && secObjectRespDTOList.size() > 0) {
                for (SecObjectRespDTO secObjectRespDTO : secObjectRespDTOList) {

                    // 搜索数据对象未配置字段，跳过
                    if (secObjectRespDTO.getSecFieldRespDTOList() == null
                            || secObjectRespDTO.getSecFieldRespDTOList().isEmpty()) {
                        continue;
                    }

                    // 判断该表被那些索引数据对象关联，忽略大小写比较
                    if (StringUtils.equalsIgnoreCase(secObjectRespDTO.getObjectNameen(),
                            objectNameEn)) {
                        
                        // 数据对象检查器
                        String inspectorName = secObjectRespDTO.getObjectInspector();
                        boolean flag = true;

                        if (StringUtils.isNotBlank(inspectorName)) {
                            try {
                                Class clazz = Class.forName(inspectorName);
                                Class[] paramTypes = { SecObjectRespDTO.class, String.class };
                                Object[] params = { secObjectRespDTO, message };
                                Constructor con = clazz.getConstructor(paramTypes);
                                IObjectInspector inspector = (IObjectInspector) con.newInstance(params);

                                flag = inspector
                                        .inspecte(secObjectRespDTO, inspector.getDeltaMessage());
                            } catch (Exception e) {
                                LogUtil.error(DeltaIndexManager.MODULE, "数据对象检查器实例化错误【" + SearchUtils.getExceptionMessage(e) + "】", e);
                            }
                        }

                        if (!flag) {
                            continue;
                        }

                        // 增量索引zkHost直接查缓存，不查表
                        SolrServer indexServer = SearchUtils.getSolrServer(secConfigRespDTO.getConfigCollectionName(),false);

                        SolrServer suggestServer = SearchUtils.getSolrServer(secConfigRespDTO.getConfigCollectionName()
                                        + SearchConstants.COLLECTION_SUGGEST_SUFFIX,false);

                        // 数据对象是否自定义增量处理器
                        String handlerName = secObjectRespDTO.getObjectHandler();
                        IDeltaImportHandler handler = null;

                        try {

                            if (StringUtils.isBlank(handlerName)) {
                                handler = new DefaultDeltaImportHandler(indexServer, suggestServer,secConfigRespDTO,
                                        secObjectRespDTO, message);
                            } else {
                                try {
                                    Class clazz = Class.forName(handlerName);
                                    Class[] paramTypes = { SolrServer.class, SolrServer.class,SecConfigRespDTO.class,SecObjectRespDTO.class,String.class };
                                    Object[] params = { indexServer, suggestServer,secConfigRespDTO,secObjectRespDTO, message };
                                    Constructor con = clazz.getConstructor(paramTypes);
                                    handler = (IDeltaImportHandler) con.newInstance(params);
                                } catch (Exception e) {
                                    LogUtil.error(DeltaIndexManager.MODULE, "增量扩展处理器实例化错误【" + SearchUtils.getExceptionMessage(e) + "】", e);
                                }
                            }

                            // 搜索数据对象类型是数据库类型的，可以允许同时接受来源于数据库和非数据库的增量方式
                            if (StringUtils.equals(secObjectRespDTO.getObjectType(),
                                    EDeltaType.DBOBJECT.getType())) {

                                if (StringUtils.equals(handler.getDeltaType(),
                                        EDeltaType.DBOBJECT.getType())) {
                                    handler.deltaDbImport(handler.getDeltaMessage());
                                } else if (StringUtils.equals(handler.getDeltaType(),
                                        EDeltaType.CUSTOM.getType())) {
                                    handler.deltaCustomImport(handler.getDeltaMessage());
                                }

                            } else if (StringUtils.equals(secObjectRespDTO.getObjectType(),
                                    EDeltaType.CUSTOM.getType())) {

                                // 搜索数据对象类型是自定义类型的，只允许接受来源于非数据库的增量方式，因为未配置表信息所以不允许来源于数据库增量方式
                                if (StringUtils.equals(handler.getDeltaType(),
                                        EDeltaType.CUSTOM.getType())) {

                                    handler.deltaCustomImport(handler.getDeltaMessage());
                                }

                            }
                        } catch (Exception e) {
                            LogUtil.error(DeltaIndexManager.MODULE,  "增量索引失败【" + SearchUtils.getExceptionMessage(e) + "】", e);
                        }

                    }
                }
            }
        }
        
    }

}

