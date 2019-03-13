package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromCalRule;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;

/**
 */
public class PromCalRuleDTOConverter extends AbstractConverter<PromCalRule, PromCalRuleDTO> {
    @Override
    public void populate(PromCalRule promCalRule, PromCalRuleDTO promCalRuleDTO) {
        ConversionHelper.copyProperties(promCalRule, promCalRuleDTO,null,false);
    }
}
