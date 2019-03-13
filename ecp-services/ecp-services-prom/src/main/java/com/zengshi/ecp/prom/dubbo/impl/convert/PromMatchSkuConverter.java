package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromMatchSku;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromMatchSkuConverter extends AbstractConverter<PromMatchSkuDTO, PromMatchSku> {
    @Override
    public void populate(PromMatchSkuDTO promMatchSkuDTO, PromMatchSku promMatchSku) {
        ConversionHelper.copyProperties(promMatchSkuDTO, promMatchSku,null,false);
    }
}
