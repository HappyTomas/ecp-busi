package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromChk;
import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromChkConverter extends AbstractConverter<PromChkDTO, PromChk> {
    @Override
    public void populate(PromChkDTO promChkDTO, PromChk promChk) {
        ConversionHelper.copyProperties(promChkDTO, promChk,null,false);
    }
}
