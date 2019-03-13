package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.util.PromSiteUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromSkuRespDTOConverter extends
        AbstractConverter<PromSku, PromSkuRespDTO> {
    @Override
    public void populate(PromSku promSku, PromSkuRespDTO promSkuRespDTO) {
        ConversionHelper.copyProperties(promSku, promSkuRespDTO,null,false);
        if(!StringUtil.isEmpty(promSkuRespDTO.getSiteId())){
            if(StringUtil.isEmpty(promSkuRespDTO.getSiteName())){
               //siteName 根据siteId 获得展示
                promSkuRespDTO.setSiteName(PromSiteUtil.getSiteInfo(promSkuRespDTO.getSiteId()).getSiteName());
          }
        }
        if(!StringUtil.isEmpty(promSkuRespDTO.getPromClass())){
            if(StringUtil.isEmpty(promSkuRespDTO.getPromClassName())){
                promSkuRespDTO.setPromClassName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE_PROM_CLASS, promSkuRespDTO.getPromClass()));
          }
        }
        if(!StringUtil.isEmpty(promSkuRespDTO.getStatus())){
            if(StringUtil.isEmpty(promSkuRespDTO.getStatusName())){
            //StatusName( 根据Status 获得展示
                promSkuRespDTO.setStatusName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_INFO_STATUS_ALL, promSkuRespDTO.getStatus()));
           }
        }
    }
}
