package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
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
public class PromInfoDTOConverter extends AbstractConverter<PromInfo, PromInfoDTO> {
    
    @Override
    public void populate(PromInfo promInfo, PromInfoDTO promInfoDTO) {
        ConversionHelper.copyProperties(promInfo, promInfoDTO,null,false);
        
         if(!StringUtil.isEmpty(promInfoDTO.getSiteId())){
             if(StringUtil.isEmpty(promInfoDTO.getSiteName())){
               //siteName 根据siteId 获得展示
               //promInfoDTO.setSiteName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.CMS_SITE, promInfoDTO.getSiteId().toString()));
               promInfoDTO.setSiteName(PromSiteUtil.getSiteInfo(promInfoDTO.getSiteId()).getSiteName()); 
             }
         }
         if(!StringUtil.isEmpty(promInfoDTO.getPromClass())){
             if(StringUtil.isEmpty(promInfoDTO.getPromClassName())){
                  promInfoDTO.setPromClassName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE_PROM_CLASS, promInfoDTO.getPromClass()));
            }
         }
         if(!StringUtil.isEmpty(promInfoDTO.getStatus())){
             if(StringUtil.isEmpty(promInfoDTO.getStatusName())){
             //StatusName( 根据Status 获得展示
             promInfoDTO.setStatusName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_INFO_STATUS_ALL, promInfoDTO.getStatus()));
            }
         }
         if(!StringUtil.isEmpty(promInfoDTO.getPromTypeCode())){
             if(StringUtil.isEmpty(promInfoDTO.getPromTypeName())){
                 promInfoDTO.setPromTypeName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE, promInfoDTO.getPromTypeCode()));
             }
         }
         //促销类型简写
         if(!StringUtil.isEmpty(promInfoDTO.getPromTypeCode())){
             if(StringUtil.isEmpty(promInfoDTO.getNameShort())){
                 PromTypeResponseDTO dto=PromCacheUtil.getPromTypeCache(promInfoDTO.getPromTypeCode());
                 if(dto!=null){
                     promInfoDTO.setNameShort(dto.getNameShort());
                 }
             }
         }
     
       
    }
}
