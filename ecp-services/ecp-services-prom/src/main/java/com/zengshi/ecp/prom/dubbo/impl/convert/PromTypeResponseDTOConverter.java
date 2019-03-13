package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromType;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromTypeResponseDTOConverter extends AbstractConverter<PromType, PromTypeResponseDTO> {
    @Override
    public void populate(PromType promType, PromTypeResponseDTO promTypeResponseDTO) {
        ConversionHelper.copyProperties(promType, promTypeResponseDTO,null,false);
    }
}
