package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromChk;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromChkResponseDTOConverter extends AbstractConverter<PromChk, PromChkResponseDTO> {
    @Override
    public void populate(PromChk promChk, PromChkResponseDTO promChkResponseDTO) {
        ConversionHelper.copyProperties(promChk, promChkResponseDTO,null,false);
        if(!StringUtil.isEmpty(promChkResponseDTO.getStatus())){
            if(StringUtil.isEmpty(promChkResponseDTO.getStatusName())){
            //StatusName( 根据Status 获得展示
            promChkResponseDTO.setStatusName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_CHK_STATUS, promChkResponseDTO.getStatus()));
           }
        }
    }
}
