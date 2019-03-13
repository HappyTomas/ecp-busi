package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromTypeMsg;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromTypeMsgResponseDTOConverter extends
        AbstractConverter<PromTypeMsg, PromTypeMsgResponseDTO> {
    @Override
    public void populate(PromTypeMsg source, PromTypeMsgResponseDTO target) {
        ConversionHelper.copyProperties(source, target,null,false);
        if(!StringUtil.isEmpty(target.getStatus())){
            //StatusName( 根据Status 获得展示
            target.setStatusName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE_MSG_STATUS, target.getStatus()));
        }
        if(!StringUtil.isEmpty(target.getPosition())){
            target.setPositionName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE_MSG_POSITION, target.getPosition()));
        }
    }
}
