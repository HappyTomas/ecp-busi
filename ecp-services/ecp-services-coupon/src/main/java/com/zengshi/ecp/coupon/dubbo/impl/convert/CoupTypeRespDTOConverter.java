package com.zengshi.ecp.coupon.dubbo.impl.convert;

import java.util.Iterator;
import java.util.Map;

import com.zengshi.ecp.coupon.dao.model.CoupType;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 */
public class CoupTypeRespDTOConverter extends AbstractConverter<CoupType, CoupTypeRespDTO> {
    
    private static final String MODULE = CoupTypeRespDTOConverter.class.getName();
    
    @Override
    public void populate(CoupType coupType, CoupTypeRespDTO coupTypeRespDTO) {
        ConversionHelper.copyProperties(coupType, coupTypeRespDTO,null,false);
        if(StringUtil.isEmpty(coupTypeRespDTO.getStatusName())){
            coupTypeRespDTO.setStatusName(BaseParamUtil.fetchParamValue(CouponConstants.CoupKey.COUP_TYPE_STATUS, coupTypeRespDTO.getStatus()));
        }
        if(StringUtil.isEmpty(coupTypeRespDTO.getTypeLimitName())){
            coupTypeRespDTO.setTypeLimitName(BaseParamUtil.fetchParamValue(CouponConstants.CoupKey.COUP_TYPE_TYPE_LIMIT, coupTypeRespDTO.getTypeLimit()));
        }
        //使用转为中文描述
        if(StringUtil.isEmpty(coupTypeRespDTO.getUseRuleCodeDesc())){
            if(!StringUtil.isEmpty(coupTypeRespDTO.getUseRuleCode())){
                try{
                    Map map = (Map<String, Object>) JSON.parse(coupTypeRespDTO.getUseRuleCode());
                    //描述
                    String desc="";
                    //获得配置参数值
                    Iterator i=map.entrySet().iterator();
                    while(i.hasNext()){
                        Map.Entry e=(Map.Entry)i.next();
                        String key=e.getKey().toString();
                        String value=e.getValue().toString();
                        if("1".equals(value)){
                            desc=BaseParamUtil.fetchParamValue(CouponConstants.CoupKey.COUP_PARAM_3, key)+";"+desc;
                        }
                    }
                    coupTypeRespDTO.setUseRuleCodeDesc(desc);
                    
             }catch(Exception ex){
                 LogUtil.error(MODULE, ex.toString());
              }
            }
        }
        //领取转为中文描述
        if(StringUtil.isEmpty(coupTypeRespDTO.getGetRuleCodeDesc())){
            if(!StringUtil.isEmpty(coupTypeRespDTO.getGetRuleCode())){
                try{
                    Map map = (Map<String, Object>) JSON.parse(coupTypeRespDTO.getGetRuleCode());
                    //描述
                    String desc="";
                    //获得配置参数值
                    Iterator i=map.entrySet().iterator();
                    while(i.hasNext()){
                        Map.Entry e=(Map.Entry)i.next();
                        String key=e.getKey().toString();
                        String value=e.getValue().toString();
                        if("1".equals(value)){
                            desc=BaseParamUtil.fetchParamValue(CouponConstants.CoupKey.COUP_PARAM_4, key)+";"+desc;
                        }
                    }
                    coupTypeRespDTO.setGetRuleCodeDesc(desc);
                    
             }catch(Exception ex){
                 LogUtil.error(MODULE, ex.toString());
              }
            }
        }
    }
}
