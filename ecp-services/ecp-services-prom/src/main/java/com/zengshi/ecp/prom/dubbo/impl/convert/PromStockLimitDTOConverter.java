package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromStockLimitDTOConverter extends AbstractConverter<PromStockLimit, PromStockLimitDTO> {
    @Override
    public void populate(PromStockLimit promStockLimit, PromStockLimitDTO promStockLimitDTO) {
        ConversionHelper.copyProperties(promStockLimit, promStockLimitDTO,null,false);
    }
}
