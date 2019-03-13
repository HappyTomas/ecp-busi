package com.zengshi.ecp.prom.dubbo.impl.convert;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.prom.dao.model.PromGift;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class PromGiftDTOConverter extends AbstractConverter<PromGift, PromGiftDTO> {
    
    @Resource
    private IGdsGiftRSV gdsGiftRSV;
    
    @Override
    public void populate(PromGift promGift, PromGiftDTO promGiftDTO) {
        ConversionHelper.copyProperties(promGift, promGiftDTO,null,false);
        //赠品编码 转名称
        if(StringUtil.isEmpty(promGiftDTO.getGiftName())||StringUtil.isEmpty(promGiftDTO.getGiftTypeName())){
            if(!StringUtil.isEmpty(promGiftDTO.getGiftId())){
                GdsGiftReqDTO gdsGiftReqDTO=new GdsGiftReqDTO();
                gdsGiftReqDTO.setId(promGiftDTO.getGiftId());
                GdsGiftRespDTO gdsGiftRespDTO= gdsGiftRSV.querySingleGiftInfo(gdsGiftReqDTO);
                if(gdsGiftRespDTO!=null){
                    promGiftDTO.setGiftName(gdsGiftRespDTO.getGiftName());
                    promGiftDTO.setGiftType(gdsGiftRespDTO.getGiftType());
                    promGiftDTO.setGiftTypeName(gdsGiftRespDTO.getGiftTypeName());
                }
            }
        }
    }
}
