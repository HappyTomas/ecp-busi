package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.util.PromSiteUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromSkuDTOConverter extends AbstractConverter<PromSku,PromSkuDTO> {
    @Override
    public void populate(PromSku promSku,PromSkuDTO promSkuDTO) {
        ConversionHelper.copyProperties(promSku,promSkuDTO,null,false);
        if(!StringUtil.isEmpty(promSkuDTO.getSiteId())){
            if(StringUtil.isEmpty(promSkuDTO.getSiteName())){
            //siteName 根据siteId 获得展示
                promSkuDTO.setSiteName(PromSiteUtil.getSiteInfo(promSkuDTO.getSiteId()).getSiteName());
          }
        }
        if(!StringUtil.isEmpty(promSkuDTO.getPromClass())){
            if(StringUtil.isEmpty(promSkuDTO.getPromClassName())){
            promSkuDTO.setPromClassName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE_PROM_CLASS, promSkuDTO.getPromClass()));
          }
        }
        if(!StringUtil.isEmpty(promSkuDTO.getStatus())){
            if(StringUtil.isEmpty(promSkuDTO.getStatusName())){
            //StatusName( 根据Status 获得展示
            promSkuDTO.setStatusName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_INFO_STATUS_ALL, promSkuDTO.getStatus()));
           }
        }
    }
}
