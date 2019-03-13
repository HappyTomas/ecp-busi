package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromMatchSku;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.service.util.PromSiteUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromMatchSkuDTOConverter extends
        AbstractConverter<PromMatchSku, PromMatchSkuDTO> {
    @Override
    public void populate(PromMatchSku promMatchSku, PromMatchSkuDTO promMatchSkuDTO) {
        ConversionHelper.copyProperties(promMatchSku, promMatchSkuDTO,null,false);
        if(!StringUtil.isEmpty(promMatchSkuDTO.getSiteId())){
            if(StringUtil.isEmpty(promMatchSkuDTO.getSiteName())){
                //siteName 根据siteId 获得展示
                //promMatchSkuDTO.setSiteName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.CMS_SITE, promMatchSkuDTO.getSiteId().toString()));
                promMatchSkuDTO.setSiteName(PromSiteUtil.getSiteInfo(promMatchSkuDTO.getSiteId()).getSiteName()); 
            }
        }
    }
}
