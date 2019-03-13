package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromGroup;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromGroupConverter extends AbstractConverter<PromGroupDTO, PromGroup> {
    @Override
    public void populate(PromGroupDTO promGroupDTO, PromGroup promGroup) {
        ConversionHelper.copyProperties(promGroupDTO, promGroup,null,false);
    }
}
