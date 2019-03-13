package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromUserLimit;
import com.zengshi.ecp.prom.dubbo.dto.PromUserLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromUserLimitDTOConverter extends AbstractConverter<PromUserLimit, PromUserLimitDTO> {
    @Override
    public void populate(PromUserLimit promUserLimit, PromUserLimitDTO promUserLimitDTO) {
        ConversionHelper.copyProperties(promUserLimit, promUserLimitDTO,null,false);
    }
}
