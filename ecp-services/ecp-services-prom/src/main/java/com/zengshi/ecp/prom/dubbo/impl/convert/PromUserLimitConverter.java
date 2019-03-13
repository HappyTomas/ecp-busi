package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromUserLimit;
import com.zengshi.ecp.prom.dubbo.dto.PromUserLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromUserLimitConverter extends AbstractConverter<PromUserLimitDTO, PromUserLimit> {
    @Override
    public void populate(PromUserLimitDTO promUserLimitDTO, PromUserLimit promUserLimit) {
        ConversionHelper.copyProperties(promUserLimitDTO, promUserLimit,null,false);
    }
}
