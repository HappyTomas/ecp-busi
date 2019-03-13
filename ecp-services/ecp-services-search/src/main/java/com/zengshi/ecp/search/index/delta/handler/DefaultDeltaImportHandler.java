package com.zengshi.ecp.search.index.delta.handler;

import org.apache.solr.client.solrj.SolrServer;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.index.IndexException;
import com.zengshi.ecp.search.index.delta.AbstractDeltaImportHandler;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultDeltaImportHandler extends AbstractDeltaImportHandler<DeltaMessage> {

    public DefaultDeltaImportHandler(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO,String message) throws IndexException {
        super(indexServer, suggestServer, secConfigRespDTO,secObjectRespDTO, message,DeltaMessage.class);
    }
    
    public DefaultDeltaImportHandler(SolrServer indexServer, SolrServer suggestServer,SecConfigRespDTO secConfigRespDTO,
            SecObjectRespDTO secObjectRespDTO,DeltaMessage deltaMessage) throws IndexException {
        super(indexServer, suggestServer, secConfigRespDTO,secObjectRespDTO, deltaMessage);
    }

    @Override
    public void deltaDbImport(DeltaMessage deltaMessage) throws BusinessException, IndexException {
        this.deltaImportDbIndex(deltaMessage);
    }

    @Override
    public void deltaCustomImport(DeltaMessage deltaMessage) throws BusinessException, IndexException {
        this.deltaImportCustomIndex(deltaMessage);
    }

}

