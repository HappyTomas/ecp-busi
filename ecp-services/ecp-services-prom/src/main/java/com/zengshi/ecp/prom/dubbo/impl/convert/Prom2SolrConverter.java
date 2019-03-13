package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.Prom2Solr;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
/**
 */
public class Prom2SolrConverter extends AbstractConverter<Prom2SolrReqDTO, Prom2Solr> {
    @Override
    public void populate(Prom2SolrReqDTO prom2SolrReqDTO, Prom2Solr prom2Solr) {
        ConversionHelper.copyProperties(prom2SolrReqDTO, prom2Solr,null,false);
    }
}
