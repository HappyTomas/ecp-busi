package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromConstraint;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromConstraintConverter extends AbstractConverter<PromConstraintDTO, PromConstraint> {
    @Override
    public void populate(PromConstraintDTO promConstraintDTO, PromConstraint promConstraint) {
        ConversionHelper.copyProperties(promConstraintDTO, promConstraint,null,false);
    }
}
