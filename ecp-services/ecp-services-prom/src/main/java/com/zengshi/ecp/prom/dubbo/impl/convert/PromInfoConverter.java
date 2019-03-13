package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromInfoConverter extends AbstractConverter<PromDTO, PromInfo> {
    @Override
    public void populate(PromDTO promDTO, PromInfo promInfo) {
        ConversionHelper.copyProperties(promDTO, promInfo,null,false);
    }
}
