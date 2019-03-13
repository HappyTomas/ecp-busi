package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromGift;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromGiftConverter extends AbstractConverter<PromGiftDTO,PromGift> {
    @Override
    public void populate(PromGiftDTO promGiftDTO,PromGift promGift) {
        ConversionHelper.copyProperties(promGiftDTO,promGift,null,false);
    }
}
