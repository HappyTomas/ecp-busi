package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromStockLimitConverter extends AbstractConverter<PromStockLimitDTO, PromStockLimit> {
    @Override
    public void populate(PromStockLimitDTO promStockLimitDTO, PromStockLimit promStockLimit) {
        ConversionHelper.copyProperties(promStockLimitDTO, promStockLimit,null,false);
    }
}
