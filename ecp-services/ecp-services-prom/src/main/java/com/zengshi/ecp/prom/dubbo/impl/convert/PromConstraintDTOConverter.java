package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromConstraint;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromConstraintDTOConverter extends AbstractConverter<PromConstraint, PromConstraintDTO> {
    @Override
    public void populate(PromConstraint promConstraint, PromConstraintDTO promConstraintDTO) {
        ConversionHelper.copyProperties(promConstraint, promConstraintDTO,null,false);
    }
}
