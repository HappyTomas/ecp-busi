package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromType4Shop;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromType4ShopConverter extends AbstractConverter<PromType4ShopDTO, PromType4Shop> {
    @Override
    public void populate(PromType4ShopDTO promType4ShopDTO, PromType4Shop promType4Shop) {
        ConversionHelper.copyProperties(promType4ShopDTO, promType4Shop,null,false);
    }
}
