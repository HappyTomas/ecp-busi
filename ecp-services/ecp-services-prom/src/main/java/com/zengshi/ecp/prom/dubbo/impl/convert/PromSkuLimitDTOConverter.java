package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromSkuLimit;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromSkuLimitDTOConverter extends AbstractConverter<PromSkuLimit,PromSkuLimitDTO> {
    @Override
    public void populate(PromSkuLimit promSkuLimit,PromSkuLimitDTO promSkuLimitDTO) {
        ConversionHelper.copyProperties(promSkuLimit,promSkuLimitDTO,null,false);
    }
}
