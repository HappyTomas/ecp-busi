package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.Prom2Solr;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrRespDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
/**
 */
public class Prom2SolrRespDTOConverter extends AbstractConverter<Prom2Solr, Prom2SolrRespDTO> {
    @Override
    public void populate(Prom2Solr prom2Solr, Prom2SolrRespDTO prom2SolrRespDTO) {
        ConversionHelper.copyProperties(prom2Solr, prom2SolrRespDTO,null,false);
    }
}
