package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromGroup;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.util.PromSiteUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromGroupResponseDTOConverter extends AbstractConverter<PromGroup, PromGroupResponseDTO> {
    @Override
    public void populate(PromGroup promGroup, PromGroupResponseDTO promGroupResponseDTO) {
        ConversionHelper.copyProperties(promGroup, promGroupResponseDTO,null,false);
        if(!StringUtil.isEmpty(promGroupResponseDTO.getSiteId())){
            if(StringUtil.isEmpty(promGroupResponseDTO.getSiteName())){
            //siteName 根据siteId 获得展示
               //promGroupResponseDTO.setSiteName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.CMS_SITE, promGroupResponseDTO.getSiteId().toString()));
                promGroupResponseDTO.setSiteName(PromSiteUtil.getSiteInfo(promGroupResponseDTO.getSiteId()).getSiteName());  
            }
        }
        if(!StringUtil.isEmpty(promGroupResponseDTO.getStatus())){
            if(StringUtil.isEmpty(promGroupResponseDTO.getStatusName())){
            //StatusName( 根据Status 获得展示
            promGroupResponseDTO.setStatusName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_GROUP_STATUS, promGroupResponseDTO.getStatus()));
           }
        }
    }
}
