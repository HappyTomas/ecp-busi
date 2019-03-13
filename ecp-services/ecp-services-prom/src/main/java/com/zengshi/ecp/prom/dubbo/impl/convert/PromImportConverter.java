package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromImport;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
/**
 */
public class PromImportConverter extends AbstractConverter<PromImportReqDTO, PromImport> {
    @Override
    public void populate(PromImportReqDTO promImportReqDTO, PromImport promImport) {
        ConversionHelper.copyProperties(promImportReqDTO, promImport,null,false);
    }
}
