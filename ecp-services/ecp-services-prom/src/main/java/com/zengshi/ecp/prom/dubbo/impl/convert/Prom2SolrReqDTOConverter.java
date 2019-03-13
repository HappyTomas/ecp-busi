package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
/**
 */
public class Prom2SolrReqDTOConverter extends AbstractConverter<PromInfo, Prom2SolrReqDTO> {
    @Override
    public void populate(PromInfo promInfo, Prom2SolrReqDTO prom2SolrReqDTO) {
        ConversionHelper.copyProperties(promInfo, prom2SolrReqDTO,null,false);
    }
}
