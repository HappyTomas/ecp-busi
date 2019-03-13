package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromImport;
import com.zengshi.ecp.prom.dubbo.dto.PromImportRespDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
/**
 */
public class PromImportRespDTOConverter extends AbstractConverter<PromImport, PromImportRespDTO> {
    @Override
    public void populate(PromImport promImport, PromImportRespDTO promImportRespDTO) {
        ConversionHelper.copyProperties(promImport, promImportRespDTO,null,false);
    }
}
