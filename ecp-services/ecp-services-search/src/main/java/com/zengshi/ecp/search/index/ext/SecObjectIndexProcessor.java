package com.zengshi.ecp.search.index.ext;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import org.apache.solr.client.solrj.SolrServer;

import com.zengshi.ecp.search.dubbo.dto.IndexReusltRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 服务调用异常统一抛出：BusinessException，扩展本身处理异常统一抛出IndexException<br>
 * Date:2015年8月12日上午11:35:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public abstract class SecObjectIndexProcessor {

    public abstract IndexReusltRespDTO process(SolrServer indexServer, SolrServer suggestServer,
                                               SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO, List<SecFieldRespDTO> secFieldRespDTOList,
                                               IndexReusltRespDTO indexReusltRespDTO) throws Exception;

    /**
     * 添加索引，无扩展字段处理
     * 
     * @param solrServer
     * @param suggestServer
     * @param secObjectRespDTO
     * @param secFieldRespDTOList
     * @param map
     * @throws Exception
     */
    public static void importIndex(SolrServer solrServer, SolrServer suggestServer,
                                   SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO, List<SecFieldRespDTO> secFieldRespDTOList,
            Map<String, Object> map) throws Exception {

    }

}
