package com.zengshi.ecp.prom.dubbo.impl.convert;

import com.zengshi.ecp.prom.dao.model.PromType4Shop;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromType4ShopResponseDTOConverter extends
        AbstractConverter<PromType4Shop, PromType4ShopResponseDTO> {
    @Override
    public void populate(PromType4Shop promType4Shop,
            PromType4ShopResponseDTO promType4ShopResponseDTO) {
        ConversionHelper.copyProperties(promType4Shop, promType4ShopResponseDTO, null, false);

        if (!StringUtil.isEmpty(promType4ShopResponseDTO.getPromClass())) {
            if (StringUtil.isEmpty(promType4ShopResponseDTO.getPromClassName())) {
                promType4ShopResponseDTO.setPromClassName(BaseParamUtil.fetchParamValue(
                        PromConstants.PromKey.PROM_TYPE_PROM_CLASS,
                        promType4ShopResponseDTO.getPromClass()));
            }
        }
        if (!StringUtil.isEmpty(promType4ShopResponseDTO.getStatus())) {
            if (StringUtil.isEmpty(promType4ShopResponseDTO.getStatusName())) {
                // StatusName( 根据Status 获得展示
                promType4ShopResponseDTO.setStatusName(BaseParamUtil.fetchParamValue(
                        PromConstants.PromKey.PROM_TYPE4SHOP_STATUS,
                        promType4ShopResponseDTO.getStatus()));
            }
        }
        if (!StringUtil.isEmpty(promType4ShopResponseDTO.getPromTypeCode())) {
            if (StringUtil.isEmpty(promType4ShopResponseDTO.getPromTypeName())) {
                promType4ShopResponseDTO
                        .setPromTypeName(BaseParamUtil.fetchParamValue(
                                PromConstants.PromKey.PROM_TYPE,
                                promType4ShopResponseDTO.getPromTypeCode()));
            }
        }
    }
}
