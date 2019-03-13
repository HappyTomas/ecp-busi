package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.util.PromCacheUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.util.PromSiteUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromInfoResponseDTOConverter extends AbstractConverter<PromInfo, PromInfoResponseDTO> {
    @Override
    public void populate(PromInfo promInfo, PromInfoResponseDTO promInfoResponseDTO) {
        ConversionHelper.copyProperties(promInfo, promInfoResponseDTO,null,false);
        if(!StringUtil.isEmpty(promInfoResponseDTO.getSiteId())){
            if(StringUtil.isEmpty(promInfoResponseDTO.getSiteName())){
               //siteName 根据siteId 获得展示
               //promInfoResponseDTO.setSiteName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.CMS_SITE, promInfoResponseDTO.getSiteId().toString()));
                promInfoResponseDTO.setSiteName(PromSiteUtil.getSiteInfo(promInfoResponseDTO.getSiteId()).getSiteName()); 
            }
        }
        if(!StringUtil.isEmpty(promInfoResponseDTO.getPromClass())){
            if(StringUtil.isEmpty(promInfoResponseDTO.getPromClassName())){
            promInfoResponseDTO.setPromClassName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE_PROM_CLASS, promInfoResponseDTO.getPromClass()));
          }
        }
        if(!StringUtil.isEmpty(promInfoResponseDTO.getStatus())){
            if(StringUtil.isEmpty(promInfoResponseDTO.getStatusName())){
            //StatusName( 根据Status 获得展示
            promInfoResponseDTO.setStatusName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_INFO_STATUS_ALL, promInfoResponseDTO.getStatus()));
            }
        }
        if(!StringUtil.isEmpty(promInfoResponseDTO.getPromTypeCode())){
            if(StringUtil.isEmpty(promInfoResponseDTO.getPromTypeName())){
                promInfoResponseDTO.setPromTypeName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE, promInfoResponseDTO.getPromTypeCode()));
            }
        }
        //促销类型简写
        if(!StringUtil.isEmpty(promInfoResponseDTO.getPromTypeCode())){
            if(StringUtil.isEmpty(promInfoResponseDTO.getNameShort())){
                PromTypeResponseDTO dto=PromCacheUtil.getPromTypeCache(promInfoResponseDTO.getPromTypeCode());
                if(dto!=null){
                    promInfoResponseDTO.setNameShort(dto.getNameShort());
                }
            }
        }
    }
}
