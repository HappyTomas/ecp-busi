package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromSkuConverter extends AbstractConverter<PromSkuDTO, PromSku> {
    @Override
    public void populate(PromSkuDTO promSkuDTO, PromSku promSku) {
        ConversionHelper.copyProperties(promSkuDTO, promSku,null,false);
    }
}
