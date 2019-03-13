package com.zengshi.ecp.search.index.ext.object;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import net.sf.json.JSONObject;

import org.apache.solr.client.solrj.SolrServer;

import com.zengshi.ecp.search.dubbo.dto.IndexReusltRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.index.ext.SecObjectIndexProcessor;

public class GoodObjectIndexProcessor extends SecObjectIndexProcessor {

    @Override
    public IndexReusltRespDTO process(SolrServer indexServer, SolrServer suggestServer,
                                      SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO, List<SecFieldRespDTO> secFieldRespDTOList,
                                      IndexReusltRespDTO indexReusltRespDTO) throws Exception {

        // 产生数据
        Map<String, Object> map = null;
        importIndex(indexServer, suggestServer,secConfigRespDTO, secObjectRespDTO, secFieldRespDTOList, map);

        // 产生数据
        JSONObject json = null;
        importIndex(indexServer, suggestServer,secConfigRespDTO, secObjectRespDTO, secFieldRespDTOList, json);

        return indexReusltRespDTO;
    }

}
