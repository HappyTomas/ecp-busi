package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromSkuLimit;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromSkuLimitConverter extends AbstractConverter<PromSkuLimitDTO, PromSkuLimit> {
    @Override
    public void populate(PromSkuLimitDTO promSkuLimitDTO, PromSkuLimit promSkuLimit) {
        ConversionHelper.copyProperties(promSkuLimitDTO, promSkuLimit,null,false);
    }
}
