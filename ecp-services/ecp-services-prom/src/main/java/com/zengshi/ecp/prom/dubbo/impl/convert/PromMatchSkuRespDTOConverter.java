package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromMatchSku;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.service.util.PromSiteUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromMatchSkuRespDTOConverter extends
        AbstractConverter<PromMatchSku, PromMatchSkuRespDTO> {
    @Override
    public void populate(PromMatchSku promMatchSku, PromMatchSkuRespDTO promMatchSkuResponseDTO) {
        ConversionHelper.copyProperties(promMatchSku, promMatchSkuResponseDTO,null,false);
        if(!StringUtil.isEmpty(promMatchSkuResponseDTO.getSiteId())){
            if(StringUtil.isEmpty(promMatchSkuResponseDTO.getSiteName())){
              //siteName 根据siteId 获得展示
             // promMatchSkuResponseDTO.setSiteName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.CMS_SITE, promMatchSkuResponseDTO.getSiteId().toString()));
                promMatchSkuResponseDTO.setSiteName(PromSiteUtil.getSiteInfo(promMatchSkuResponseDTO.getSiteId()).getSiteName()); 
            }
        }
    }
}
